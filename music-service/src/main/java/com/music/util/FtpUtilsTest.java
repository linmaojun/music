package com.music.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;

/**
 * @author linmaojun
 * 类名称: FtpUtilsTest
 * 类描述:
 * 创建时间: 2019年05月30日  20:13
 */
public class FtpUtilsTest {
    /**
     * ftp服务器地址
     */
    public static String hostname = "47.104.187.152";
    /**
     * ftp服务器端口号
     */
    public static Integer port = 21;
    /**
     * ftp登录账号
     */
    public static String username = "linmaojun";
    /**
     * ftp登录密码
     */
    public static String password = "Lmj334482";

    public static FTPClient ftpClient = null;


    /**
     * 初始化ftp服务器
     */
    public static void initFtpClient() {
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");
        try {
            System.out.println("connecting...ftp服务器:" + hostname + ":" + port);
            // 连接ftp服务器
            ftpClient.connect(hostname, port);
            // 登录ftp服务器
            ftpClient.login(username, password);
            // 是否成功登录服务器
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                System.out.println("ftp服务器连接失败");
            }
            System.out.println("connect successfu...ftp服务器:" + hostname + ":" + port);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * * 下载文件 *
     *
     * @param pathname FTP服务器文件目录
     * @param filename 文件名称
     * @param localpath 下载后的文件路径
     * @return
     */
    public static boolean downloadFile(String pathname, String filename, String localpath) {
        boolean flag = false;
        ftpClient = new FTPClient();
        try {
            System.out.println("开始下载文件");
            initFtpClient();
            // 切换FTP目录
            boolean changeFlag = ftpClient.changeWorkingDirectory(pathname);
//            System.err.println("changeFlag==" + changeFlag);
//
//            ftpClient.enterLocalPassiveMode();
//            ftpClient.setRemoteVerificationEnabled(false);
//            // 查看有哪些文件夹 以确定切换的ftp路径正确
//            String[] a = ftpClient.listNames();
//            System.err.println(a[0]);

            FTPFile[] ftpFiles = ftpClient.listFiles();
            for (FTPFile file : ftpFiles) {
                if (filename.equalsIgnoreCase(file.getName())) {
                    File localFile = new File(localpath + "/" + file.getName());
                    OutputStream os =os = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(file.getName(), os);
                    os.close();
                }
            }
            ftpClient.logout();
            flag = true;
            System.out.println("下载文件成功");
        } catch (Exception e) {
            System.out.println("下载文件失败");
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            if (null != os) {
//                try {
//                    os.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        }
        return flag;
    }

}
