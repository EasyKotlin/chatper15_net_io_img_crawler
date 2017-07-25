package com.easy.kotlin.controller

import com.easy.kotlin.view.HelloDSLView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

/**
 * Created by jack on 2017/7/22.
 */

@Controller
class HelloDSLController {
    @Autowired
    var helloDSLView: HelloDSLView? = null

    @GetMapping("hello")
    fun helloDSL(model: Model): ModelAndView {
        model.addAttribute("hello", helloDSLView?.html())
        return ModelAndView("hello")
    }
}
