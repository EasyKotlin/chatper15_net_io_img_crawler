package com.easy.kotlin.view

import kotlinx.html.*
import kotlinx.html.stream.createHTML
import org.springframework.stereotype.Service

/**
 * Created by jack on 2017/7/22.
 */
@Service
class ImageTableView {
    fun html(): String {
        return createHTML().html {
            head {
                meta {
                    charset = "utf-8"
                    httpEquiv = "X-UA-Compatible"
                    content = "IE=edge"
                }
                title("我图")
                link {
                    href = "https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
                    rel = "stylesheet"
                }
                script {
                    src = "https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"
                }

                script {
                    src = "/DataTables/media/js/jquery.dataTables.js"
                }
                link {
                    href = "/DataTables/media/css/jquery.dataTables.css"
                    rel = "stylesheet"
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
                    table {
                        id = "imgTable"
                        classes = setOf("table", "table-hover")
                        thead {
                            tr {
                                td {
                                    text("序号")
                                }
                                td {
                                    text("我图")
                                }
                                td {
                                    text("文件名")
                                }
                            }
                        }
                        tbody {
                            tr {
                                td {
                                    text(1)
                                }
                                td {
                                    img {
                                        src = "http://img1.imgtn.bdimg.com/it/u=4095264292,1268726081&fm=214&gp=0.jpg"
                                        width = "100%"

                                    }
                                }
                                td {
                                    text(1)
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}







