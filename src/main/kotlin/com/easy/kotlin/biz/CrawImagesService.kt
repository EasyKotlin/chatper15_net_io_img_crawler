package com.easy.kotlin.biz

import com.easy.kotlin.chapter11_kotlin_springboot.dao.ImageRepository
import com.easy.kotlin.chapter11_kotlin_springboot.entity.Image
import com.easy.kotlin.百度搜索关键词列表
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.File
import java.net.URL
import java.nio.charset.Charset
import java.util.*

/**
 * Created by jack on 2017/7/25.
 */
@Service
class CrawImagesService {
    val logger = LoggerFactory.getLogger(CrawImagesService::class.java)
    @Autowired val ImageRepository: ImageRepository? = null

    fun doCraw() = runBlocking {
        // Randomly permutes the specified list
        Collections.shuffle(百度搜索关键词列表)
        百度搜索关键词列表.forEach {
            launch(CommonPool) {
                writeBaiduImgUrlsToDB(it)
            }
        }
    }


    fun writeBaiduImgUrlsToDB(word: String) {
        var pn = 30
        for (i in 1..10) {
            val imgUrlQuery = "http://image.baidu.com/search/acjson?tn=resultjson_com&ipn=rj&ct=201326592&is=&fp=result&queryWord=${word}&cl=2&lm=-1&ie=utf-8&oe=utf-8&adpicid=&st=&z=3&ic=&word=${word}&s=&se=&tab=&width=0&height=0&face=&istype=&qc=&nc=&fr=&pn=${pn}&rn=30&gsm=b4&1501086462487="
            println("关键字：${word}")
            println("imgUrlQuery=${imgUrlQuery}")
            pn += 30
            val regex = "\\{\"ObjURL\":(.+),\"FromURL\"".toRegex()
            val imgjson = getUrlContent(imgUrlQuery)
            regex.findAll(imgjson).forEach {
                try {
                    val result = it.value
                    val startIndex = result.indexOf("{\"ObjURL\":\"") + "{\"ObjURL\":\"".length
                    val endIndex = result.indexOf("\",\"FromURL\"")
                    var imgUrl = result.substring(startIndex, endIndex)
                    imgUrl = imgUrl.replace("\\", "")
                    if (passFilter(imgUrl)) {
                        // Write to DB
                        val image = Image()
                        image.category = word
                        image.url = imgUrl
                        if (ImageRepository?.countByUrl(imgUrl)!! < 1) {
                            logger.info("image {}", image)
                            ImageRepository?.save(image)
                        }
                    }
                } catch(ex: Exception) {
                }
            }
        }
    }

    fun writeSogouImgUrlsToDB(category: String) {
        var start = 0
        var len = 20
        for (i in 1..10) {
            val imgUrlQuery = "http://pic.sogou.com/pics/channel/getAllRecomPicByTag.jsp?category=${category}&tag=全部&start=${start}&len=${len}"
            println("imgUrlQuery=${imgUrlQuery}")
            start += len
            // "ori_pic_url":"(.+)","ext_url"
            val regex = "\"ori_pic_url\":\"(.+)\",\"ext_url\"".toRegex()
            val imgjson = getUrlContent(imgUrlQuery)
            println(imgjson)
            regex.findAll(imgjson).forEach {
                try {
                    val result = it.value
                    val startIndex = result.indexOf("\"ori_pic_url\":\"") + "\"ori_pic_url\":\"".length
                    val endIndex = result.indexOf("\",\"ext_url\"")
                    var imgUrl = result.substring(startIndex, endIndex)
                    println("writeSogouImgUrlsToDB: ${imgUrl}")
                    if (passFilter(imgUrl)) {
                        // Write to DB
                        val image = Image()
                        image.category = category
                        image.url = imgUrl
                        if (ImageRepository?.countByUrl(imgUrl)!! < 1) {

                            ImageRepository?.save(image)
                        }
                    }
                } catch(ex: Exception) {
                }
            }
        }
    }

    fun passFilter(imgUrl: String): Boolean {
        println(imgUrl)
        return imgUrl.endsWith(".jpg")
                && !imgUrl.contains("baidu.com/")
                && !imgUrl.contains("126.net")
                && !imgUrl.contains("pconline.com")
                && !imgUrl.contains("nipic.com")
                && !imgUrl.contains("zol.com")
    }


    fun getUrlContent(url: String): String {
        return URL(url).readText(Charset.defaultCharset())
    }

    fun getUrlBytes(url: String): ByteArray {
        return URL(url).readBytes()
    }

    fun writeUrlBytesTo(filename: String, url: String) {
        val bytes = URL(url).readBytes()
        File(filename).writeBytes(bytes)
    }


}
