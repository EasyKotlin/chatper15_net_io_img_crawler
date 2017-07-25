package com.easy.kotlin.view

import kotlinx.html.*
import kotlinx.html.stream.createHTML
import org.springframework.stereotype.Service

/**
 * Created by jack on 2017/7/22.
 */
@Service
class HelloDSLView {
    fun html(): String {
        return createHTML().html {
            head {
                meta {
                    charset = "utf-8"
                    httpEquiv = "X-UA-Compatible"
                    content = "IE=edge"
                }
                title("百度一下")
                link {
                    href = "https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
                    rel = "stylesheet"
                }
                script {
                    src = "https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"
                }
                link {
                    href = "dsl.css"
                    rel = "stylesheet"
                }
                script {
                    src = "dsl.js"
                }
            }

            body {
                div(classes = "container") {
                    div(classes = "ipad center") {
                        img {
                            src = "https://www.baidu.com/img/bd_logo1.png"
                            width = "270"
                            height = "129"
                        }
                    }

                    form(classes = "form") {
                        input(InputType.text, classes = "form-control ipad") {
                            id = "wd"
                        }
                        button(classes = "btn btn-primary form-control ipad") {
                            id = "baiduBtn"
                            type = ButtonType.submit
                            text("百度一下")

                        }
                    }

                }
            }
        }
    }
}







