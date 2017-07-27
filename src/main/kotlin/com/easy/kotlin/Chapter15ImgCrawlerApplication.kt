package com.easy.kotlin

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer




@SpringBootApplication
@EnableScheduling
class Chapter15ImgCrawlerApplication: WebMvcConfigurerAdapter() {
    override fun configureContentNegotiation(config: ContentNegotiationConfigurer?) {
        config!!.favorPathExtension(true)
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Chapter15ImgCrawlerApplication::class.java, *args)
}

val 我图URL文件名 = "我图.url"
val 百度搜索关键词列表 = arrayOf(
        "美女",
        "清纯",
        "清纯性感",
        "清纯可爱",
        "萝莉",
        "超清纯气质美女",
        "嫩模",
        "尤物",
        "孙允珠",
        "朱韵淇",
        "孙文婷",
        "李麦文",
        "陈玟予",
        "申善雅",
        "李梦皎",
        "燕子沁",
        "马艺文",
        "欧阳娜娜",
        "手绘森系小清新插画",
        "水彩画",
        "手绘建筑设计",
        "油画",
        "国家地理摄影精品",
        "震撼人心的摄影作品",
        "世界金奖摄影作品",
        "设计作品欣赏",
        "校花",
        "清纯校花",
        "90后美女",
        "00后小美女",
        "清纯美女"
)

val 搜狗关键词列表 = arrayOf(
        "美女",
        "艺术云图",
        "壁纸",
        "家居"
)

