package com.easy.kotlin

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling

val 我图URL文件名 = "我图.url"

@SpringBootApplication
@EnableScheduling
class Chapter15ImgCrawlerApplication

fun main(args: Array<String>) {
    SpringApplication.run(Chapter15ImgCrawlerApplication::class.java, *args)
}


