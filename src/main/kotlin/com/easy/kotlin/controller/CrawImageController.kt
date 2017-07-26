package com.easy.kotlin.controller

import com.easy.kotlin.biz.CrawImagesService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by jack on 2017/7/22.
 */

@Controller
class CrawImageController {
    @GetMapping("doCraw")
    @ResponseBody
    fun doCraw() {
        CrawImagesService.doCraw2()
    }
}
