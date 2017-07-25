package com.easy.kotlin.controller

import com.easy.kotlin.view.ImageTableView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

/**
 * Created by jack on 2017/7/22.
 */

@Controller
class ImageTableController {
    @Autowired
    var imageTableView: ImageTableView? = null

    @GetMapping("image")
    fun helloDSL(model: Model): ModelAndView {
        model.addAttribute("image", imageTableView?.html())
        return ModelAndView("image")
    }

}
