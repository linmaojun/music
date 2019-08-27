package com.music.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @author linmaojun
 * 类名称: ReadFile
 * 类描述:
 * 创建时间: 2019年07月18日  14:29
 */
public class ReadFile {

    public static String readTxt(String filePath){
        StringBuffer sb = null;
        try {
            File file = new File(filePath);
            if(file.isFile() && file.exists()) {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
                String lineTxt = null;
                sb = new StringBuffer();
                BufferedReader br = new BufferedReader(isr);
                while ((lineTxt = br.readLine()) != null) {
                    sb.append(lineTxt);
                }
                br.close();
            } else {
                System.out.println("文件不存在!");
            }
        } catch (Exception e) {
            System.out.println("文件读取错误!");
        }
        String data = new String(sb);
        return data;
    }
}
