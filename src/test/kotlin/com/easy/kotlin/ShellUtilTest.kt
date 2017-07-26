package com.easy.kotlin

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by jack on 2017/7/26.
 */
@RunWith(JUnit4::class)
class ShellUtilTest {
    @Test
    fun testShellUtil() {
        var result = ShellUtil.execute("ls -al")
        println(result)
        val cmds = arrayOf("/bin/sh", "-c", "ls -al .. | grep .kt$")
        ShellUtil.execute(cmds)

        val p = "ls -al".execute()

        val exitCode = p.waitFor()
        val text = p.text()

        println(exitCode)
        println(text)

        val input = readLine()
    }
}
