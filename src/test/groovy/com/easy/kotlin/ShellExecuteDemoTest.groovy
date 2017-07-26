package com.easy.kotlin

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4)
class ShellExecuteDemoTest {
    @Test
    def void testShellExecute() {
        def p = "ls -R".execute()
        def output = p.inputStream.text
        println(output)
        def fname = "我图.url"
        def f = new File(fname)
        def lines = f.readLines()
        lines.forEach({
            println(it)
        })
        println(f.text)
    }
}
