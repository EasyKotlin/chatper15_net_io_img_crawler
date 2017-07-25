package com.easy.kotlin.controller

import com.easy.kotlin.biz.CrawImagesService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

/**
 * Created by jack on 2017/7/22.
 */

@Controller
class WotuController {

    @GetMapping(value = *arrayOf("wotu", "/"))
    fun wotu(model: Model): ModelAndView {
        model.addAttribute("imageUrls", CrawImagesService.getImageUrls())
        return ModelAndView("wotu")
    }

}
