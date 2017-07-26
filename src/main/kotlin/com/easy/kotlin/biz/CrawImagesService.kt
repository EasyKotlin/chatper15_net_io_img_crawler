package com.easy.kotlin.biz

import com.easy.kotlin.我图URL文件名
import java.io.File
import java.net.URL
import java.nio.charset.Charset
import java.util.regex.Pattern
import kotlin.concurrent.thread

/**
 * Created by jack on 2017/7/25.
 */

object CrawImagesService {
    fun doCraw(): String {
        val words = arrayOf(
                "孙允珠",
                "朱韵淇",
                "孙文婷",
                "李麦文",
                "陈玟予",
                "申善雅",
                "李梦皎",
                "燕子沁",
                "马艺文",
                "手绘森系小清新插画",
                "水彩画",
                "手绘建筑设计",
                "油画",
                "国家地理摄影精品",
                "震撼人心的摄影作品",
                "世界金奖摄影作品",
                "设计作品欣赏",
                "校花",
                "欧阳娜娜",
                "90后美女",
                "00后小美女",
                "清纯美女"
        )

        words.forEach {
            thread(start = true) {
                CrawImagesService.writeImgUrls(it)
            }
        }

        return "任务已启动"
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
        return KFileUtil.getFileLines(我图URL文件名)
    }


    fun writeImgUrls(word: String) {
        var pn = 30
        for (i in 1..10) {
            val imgUrlQuery = "http://image.baidu.com/search/acjson?tn=resultjson_com&ipn=rj&ct=201326592&is=&fp=result&queryWord=${word}C&cl=2&lm=-1&ie=utf-8&oe=utf-8&adpicid=&st=&z=&ic=&word=${word}&s=&se=&tab=&width=&height=&face=&istype=&qc=&nc=1&fr=&pn=${pn}&rn=30&gsm=1e&1500950950035="
            println("关键字：${word}")
            println("imgUrlQuery=${imgUrlQuery}")
            pn += 30

            val objImgUrlRegex = "\\{\"ObjURL\":(.+),\"FromURL\""
            val imgUrlJsonString = getUrlContent(imgUrlQuery)
            println(imgUrlJsonString)

            val p = Pattern.compile(objImgUrlRegex)
            val lines = imgUrlJsonString.split("\n")
            lines.forEach {
                val m = p.matcher(it)
                while (m.find()) {
                    try {
                        val result = m.group()
                        val startIndex = result.indexOf("{\"ObjURL\":\"") + "{\"ObjURL\":\"".length
                        val endIndex = result.indexOf("\",\"FromURL\"")
                        var imgUrl = result.substring(startIndex, endIndex)
                        imgUrl = imgUrl.replace("\\", "")
                        if (isOk(imgUrl)) {
                            KFileUtil.appendFile(imgUrl + "\n", 我图URL文件名)
                        }
                    } catch (ex: Exception) {
                    }
                }
            }
        }
    }

    fun isOk(imgUrl: String): Boolean {
        println(imgUrl)
//        val urlConnection = URL(imgUrl).openConnection() as HttpURLConnection
//        urlConnection.connectTimeout = 1000
//        val size = urlConnection.contentLength
//        println("size=${size}")
//        val responseCode = urlConnection.responseCode
//        println("responseCode=${responseCode}")
        val 我图文件 = File(我图URL文件名)
        if (!我图文件.exists()) {
            我图文件.createNewFile()
        }
        val 美女文件所有行 = KFileUtil.getFileLines(我图URL文件名)
        return !美女文件所有行.contains(imgUrl) // 重复的 url 不写
                && imgUrl.endsWith(".jpg")
                && !imgUrl.contains("baidu.com/")
                && !imgUrl.contains("126.net/")
                && !imgUrl.contains("pconline.com")
    }
}
