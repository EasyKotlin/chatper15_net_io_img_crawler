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
import javax.servlet.http.HttpServletRequest


/**
 * Created by jack on 2017/7/22.
 */

@Controller
class MeituController {
    @Autowired
    var imageRepository: ImageRepository? = null

    @RequestMapping(value = "meituJson", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    fun wotuJson(@RequestParam(value = "page", defaultValue = "0") page: Int,
                 @RequestParam(value = "size", defaultValue = "10") size: Int): Page<Image>? {
        return getPageResult(page, size)
    }

    @RequestMapping(value = "meituSearchJson", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    fun wotuSearchJson(@RequestParam(value = "page", defaultValue = "0") page: Int,
                       @RequestParam(value = "size", defaultValue = "10") size: Int,
                       @RequestParam(value = "searchText", defaultValue = "") searchText: String): Page<Image>? {
        return getPageResult(page, size, searchText)
    }

    @RequestMapping(value = "meituSearchFavoriteJson", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    fun wotuSearchFavoriteJson(@RequestParam(value = "page", defaultValue = "0") page: Int,
                               @RequestParam(value = "size", defaultValue = "10") size: Int,
                               @RequestParam(value = "searchText", defaultValue = "") searchText: String): Page<Image>? {
        return getFavoritePageResult(page, size, searchText)
    }

    @RequestMapping(value = "meituView", method = arrayOf(RequestMethod.GET))
    fun meituView(model: Model, request: HttpServletRequest): ModelAndView {
        model.addAttribute("requestURI" , request.requestURI)
        return ModelAndView("meituView")
    }

    @RequestMapping(value = "meituFavoriteView", method = arrayOf(RequestMethod.GET))
    fun meituFavoriteView(model: Model, request: HttpServletRequest): ModelAndView {
        model.addAttribute("requestURI" , request.requestURI)
        return ModelAndView("meituFavoriteView")
    }

    private fun getPageResult(page: Int, size: Int): Page<Image>? {
        val sort = Sort(Sort.Direction.DESC, "id")
        val pageable = PageRequest(page, size, sort)
        return imageRepository?.findAll(pageable)
    }

    private fun getPageResult(page: Int, size: Int, searchText: String): Page<Image>? {
        val sort = Sort(Sort.Direction.DESC, "id")
        // 注意：PageRequest page 默认是从0开始
        val pageable = PageRequest(page, size, sort)
        if (searchText == "") {
            return imageRepository?.findAll(pageable)
        } else {
            return imageRepository?.search(searchText, pageable)
        }
    }

    private fun getFavoritePageResult(page: Int, size: Int, searchText: String): Page<Image>? {
        val sort = Sort(Sort.Direction.DESC, "id")
        val pageable = PageRequest(page, size, sort)
        if (searchText == "") {
            val allFavorite = imageRepository?.findAllFavorite(pageable)
            return allFavorite
        } else {
            val searchFavorite = imageRepository?.searchFavorite(searchText, pageable)
            return searchFavorite
        }
    }

    @RequestMapping(value = "addFavorite", method = arrayOf(RequestMethod.POST))
    @ResponseBody
    fun addFavorite(@RequestParam(value = "id") id: Long): Boolean {
        imageRepository!!.addFavorite(id)
        return true
    }

    @RequestMapping(value = "delete", method = arrayOf(RequestMethod.POST))
    @ResponseBody
    fun delete(@RequestParam(value = "id") id: Long): Boolean {
        imageRepository!!.delete(id)
        return true
    }

}
