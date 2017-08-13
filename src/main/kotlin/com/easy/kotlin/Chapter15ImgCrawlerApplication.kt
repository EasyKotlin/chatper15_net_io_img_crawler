package com.easy.kotlin

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer
import java.io.File


@SpringBootApplication
@EnableScheduling
class Chapter15ImgCrawlerApplication : WebMvcConfigurerAdapter() {
    override fun configureContentNegotiation(config: ContentNegotiationConfigurer?) {
        config!!.favorPathExtension(true)
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Chapter15ImgCrawlerApplication::class.java, *args)
}


var 百度搜索关键词列表 = File("百度搜索关键词列表.data").readLines()