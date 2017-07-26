package com.easy.kotlin

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Created by jack on 2017/7/25.
 */

fun String.execute(): Process {
    val runtime = Runtime.getRuntime()
    return runtime.exec(this)
}

fun Process.text(): String {
    var output = ""
    //	输出 Shell 执行的结果
    val inputStream = this.inputStream
    val isr = InputStreamReader(inputStream)
    val reader = BufferedReader(isr)
    var line: String? = ""
    while (line != null) {
        line = reader.readLine()
        output += line + "\n"
    }
    return output
}

