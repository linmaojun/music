package com.music.test;

import com.music.util.FtpUtilsTest;

/**
 * @author linmaojun
 * 类名称: FtpTest
 * 类描述:
 * 创建时间: 2019年05月30日  19:47
 */
public class FtpTest {
    public static void main(String[] args) {
        String ftpHost = "47.104.187.152";
        String ftpUserName = "linmaojun";
        String ftpPassword = "Lmj334482";
        int ftpPort = 21;
        String ftpPath = "/";
        String localPath = "D:\\";
        String fileName = "测试.pdf";
        //下载一个文件
//        FtpUtil.downloadFtpFile(ftpHost, ftpUserName, ftpPassword, ftpPort, ftpPath, localPath, fileName);

        FtpUtilsTest.downloadFile(ftpPath,fileName,localPath);

//        FtpUtil.downFile(ftpHost,ftpPort,ftpUserName,ftpPassword,ftpPath,fileName,localPath);
    }

}
