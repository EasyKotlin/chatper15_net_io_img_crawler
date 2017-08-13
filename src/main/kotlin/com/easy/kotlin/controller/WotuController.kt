package com.easy.kotlin.controller

import com.easy.kotlin.chapter11_kotlin_springboot.dao.ImageRepository
import com.easy.kotlin.chapter11_kotlin_springboot.entity.Image
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView


/**
 * Created by jack on 2017/7/22.
 */

@Controller
class WotuController {
    @Autowired
    var imageRepository: ImageRepository? = null

    @RequestMapping(value = "wotuJson", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    fun wotuJson(@RequestParam(value = "page", defaultValue = "0") page: Int,
                 @RequestParam(value = "size", defaultValue = "10") size: Int): Page<Image>? {
        return getPageResult(page, size)
    }

    @RequestMapping(value = *arrayOf("", "/", "wotuView"), method = arrayOf(RequestMethod.GET))
    fun wotuView(@RequestParam(value = "page", defaultValue = "0", required = false) page: Int,
                 @RequestParam(value = "size", defaultValue = "10", required = false) size: Int,
                 model: Model): ModelAndView {
        model.addAttribute("pageResult", getPageResult(page, size))
        return ModelAndView("wotuView")
    }


    @RequestMapping(value = "meituView", method = arrayOf(RequestMethod.GET))
    fun meituView(): ModelAndView {
        return ModelAndView("meituView")
    }


    private fun getPageResult(page: Int, size: Int): Page<Image>? {
        val sort = Sort(Sort.Direction.DESC, "id")
        val pageable = PageRequest(page, size, sort)
        return imageRepository?.findAll(pageable)
    }

}
