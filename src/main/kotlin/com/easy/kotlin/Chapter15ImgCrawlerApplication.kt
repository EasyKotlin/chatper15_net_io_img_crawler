package com.easy.kotlin

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling

val 美女文件名 = "美女.url"

@SpringBootApplication
@EnableScheduling
class Chapter15ImgCrawlerApplication

fun main(args: Array<String>) {
    SpringApplication.run(Chapter15ImgCrawlerApplication::class.java, *args)
}


