package com.music.test;

import com.music.util.FtpTest;

/**
 * @author linmaojun
 * 类名称: FtpFile
 * 类描述:
 * 创建时间: 2019年07月17日  10:08
 */
public class FtpFile {
    public static void main(String[] args) {
        FtpTest.ftpLogin();
        FtpTest.downLoadDirectory("D:\\测试", "/MSG/DOWN/");
//        FtpTest.deleteFile("/test","103_T340_2106899276_G2S0CDZZRH_20190702211336_1026.txt");
        FtpTest.ftpLogOut();
    }
}
