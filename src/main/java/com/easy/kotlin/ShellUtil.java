package com.easy.kotlin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by jack on 2017/7/25.
 */
public class ShellUtil {
    public static String execute(String cmd) {
        String output = "";
        try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(cmd);
            //	等待 Shell 执行的结果，0 为正常执行完毕
            int exitValue = process.waitFor();
            if (exitValue != 0) {
                System.out.println("exit with " + exitValue);
                return output;
            }
            //	输出 Shell 执行的结果
            InputStream in = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader reader = new BufferedReader(isr);
            String line;
            while ((line = reader.readLine()) != null) {
                // System.out.println(line);
                output += line + "\n";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    public static String execute(String[] cmds) {
        String output = "";
        try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(cmds);
            //	等待 Shell 执行的结果，0 为正常执行完毕
            int exitValue = process.waitFor();
            if (exitValue != 0) {
                System.out.println("exit with " + exitValue);
                return output;
            }
            //	输出 Shell 执行的结果
            try (InputStream in = process.getInputStream();
                 InputStreamReader isr = new InputStreamReader(in);
                 BufferedReader reader = new BufferedReader(isr)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    output += line + "\n";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    //public static void main(String... args) {
    //    String[] cmds = {"/bin/sh", "-c", "ls -al .. | grep .kt$"};
    //    execute(cmds);
    //}
}
