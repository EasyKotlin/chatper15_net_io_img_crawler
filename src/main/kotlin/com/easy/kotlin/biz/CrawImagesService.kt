package com.easy.kotlin.biz

import com.easy.kotlin.美女文件名
import java.io.File
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset
import java.util.regex.Pattern

/**
 * Created by jack on 2017/7/25.
 */

object CrawImagesService {
    fun doCraw(): List<String> {
        val words = arrayOf("90后美女", "00后小美女", "嫩模", "性感", "诱惑", "清纯", "校花", "林允儿", "欧阳娜娜", "林允", "美女", "尤物")
        words.forEach {
            CrawImagesService.writeImgUrls(it)
        }
        return KFileUtil.getFileLines(美女文件名)
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

    fun getImageUrls(): List<String> {
        return KFileUtil.getFileLines(美女文件名)
    }


    fun writeImgUrls(word: String) {
        var pn = 30
        for (i in 1..10) {
            val imgUrlQuery = "http://image.baidu.com/search/acjson?tn=resultjson_com&ipn=rj&ct=201326592&is=&fp=result&queryWord=${word}C&cl=2&lm=-1&ie=utf-8&oe=utf-8&adpicid=&st=&z=&ic=&word=${word}&s=&se=&tab=&width=&height=&face=&istype=&qc=&nc=1&fr=&pn=${pn}&rn=30&gsm=1e&1500950950035="
            println("关键字：${word}")
            println("imgUrlQuery=${imgUrlQuery}")
            pn += 30
            val imgUrlJson = File("imgs.json")
            imgUrlJson.writeBytes(getUrlBytes(imgUrlQuery))

            val objImgUrlRegex = "\\{\"ObjURL\":(.+),\"FromURL\""
            val imgUrlJsonString = getUrlContent(imgUrlQuery)
            println(imgUrlJsonString)

            val p = Pattern.compile(objImgUrlRegex)
            val lines = KFileUtil.getFileLines("imgs.json")

            lines.forEach {
                val m = p.matcher(it)
                while (m.find()) {

                    try {
                        val result = m.group()
                        val startIndex = result.indexOf("{\"ObjURL\":\"") + "{\"ObjURL\":\"".length
                        val endIndex = result.indexOf("\",\"FromURL\"")
                        var imgUrl = result.substring(startIndex, endIndex)
                        imgUrl = imgUrl.replace("\\", "")
                        println(imgUrl)
                        if (isOk(imgUrl)) {
                            KFileUtil.appendFile(imgUrl + "\n", 美女文件名)
                        }

                    } catch (ex: Exception) {

                    }
                }
            }
        }
    }

    fun isOk(imgUrl: String): Boolean {
        val urlConnection = URL(imgUrl).openConnection() as HttpURLConnection
        urlConnection.connectTimeout = 1000
        val size = urlConnection.contentLength
        println(size)
        val responseCode = urlConnection.responseCode
        val 美女文件 = File(美女文件名)
        if (!美女文件.exists()) {
            美女文件.createNewFile()
        }
        val 美女文件所有行 = KFileUtil.getFileLines(美女文件名)
        return !美女文件所有行.contains(imgUrl) && size > 100 && responseCode == 200 && imgUrl.endsWith(".jpg")  // 重复的 url 不写
    }
}
