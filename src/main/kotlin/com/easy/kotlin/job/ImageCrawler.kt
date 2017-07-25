package com.easy.kotlin.job

import com.easy.kotlin.biz.CrawImagesService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*

/**
 * Created by jack on 2017/7/25.
 */
@Component
class ImageCrawler {

    @Scheduled(fixedDelay = 1000 * 60 * 1)
    fun job() {
        println("开始执行定时任务： ${Date()}")
        CrawImagesService.doCraw()
    }
}
