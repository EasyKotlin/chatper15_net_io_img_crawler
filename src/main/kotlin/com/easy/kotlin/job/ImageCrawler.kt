package com.easy.kotlin.job

import com.easy.kotlin.biz.CrawImagesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*

/**
 * Created by jack on 2017/7/25.
 */
@Component
class ImageCrawler {
    @Autowired val CrawImagesService: CrawImagesService? = null
    @Scheduled(fixedDelay = 1000 * 60 * 60)
    fun job() {
        println("开始执行定时任务： ${Date()}")
        CrawImagesService?.doCraw()
    }
}
