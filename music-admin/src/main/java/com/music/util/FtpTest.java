package com.music.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.TimeZone;

/**
 * @author linmaojun
 * 类名称: FtpTest
 * 类描述:
 * 创建时间: 2019年07月17日  9:09
 */
@Slf4j
public class FtpTest {
    private static FTPClient ftpClient = new FTPClient();
    private static String strIp = "140.207.240.61";
    private static int intPort = 8821;
    private static String user = "SPL_GSK_VIEWer_Test";
    private static String password = "SPL_GSK_VieWer_t";

//    private static String strIp = "47.104.187.152";
//    private static int intPort = 21;
//    private static String user = "linmaojun";
//    private static String password = "lmj334482";

    /**
     *
     * 判断是否登入成功
     * @return
     */
    public static boolean ftpLogin() {
        boolean isLogin = false;
        FTPClientConfig ftpClientConfig = new FTPClientConfig();
        ftpClientConfig.setServerTimeZoneId(TimeZone.getDefault().getID());
        ftpClient.setControlEncoding("GBK");
        ftpClient.configure(ftpClientConfig);
        try {
            if (intPort > 0) {
                ftpClient.connect(strIp, intPort);
            }else {
                ftpClient.connect(strIp);
            }
            // FTP服务器连接回答  
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                log.error("登录FTP服务失败！");
                return isLogin;
            }
            ftpClient.login(user, password);
            // 设置传输协议  
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            log.info("恭喜" + user + "成功登陆FTP服务器");
            isLogin = true;
        }catch (Exception e) {
            e.printStackTrace();
            log.error(user + "登录FTP服务失败！" + e.getMessage());
        }
        ftpClient.setBufferSize(1024 * 2);
        ftpClient.setDataTimeout(30 * 1000);
        return isLogin;
    }

    /**
     * 退出关闭服务器链接
     */
    public static void ftpLogOut() {
        if (null != ftpClient && ftpClient.isConnected()) {
            try {
                // 退出FTP服务器  
                boolean reuslt = ftpClient.logout();
                if (reuslt) {
                    log.info("成功退出服务器");
                }
            }catch (IOException e) {
                e.printStackTrace();
                log.warn("退出FTP服务器异常！" + e.getMessage());
            }finally {
                try {
                    // 关闭FTP服务器的连接
                    ftpClient.disconnect();  
                }catch (IOException e) {
                    e.printStackTrace();
                    log.warn("关闭FTP服务器的连接异常！");
                }
            }
        }
    }

    /*** 
     * 上传Ftp文件 
     * @param localFile 当地文件 
     * @param romotUpLoadePath 上传服务器路径 - 应该以/结束
     * */
    public static boolean uploadFile(File localFile, String romotUpLoadePath) {
        BufferedInputStream inStream = null;
        boolean success = false;
        try {
            // 改变工作路径
            ftpClient.changeWorkingDirectory(romotUpLoadePath);
            inStream = new BufferedInputStream(new FileInputStream(localFile));
            log.info(localFile.getName() + "开始上传.....");
            success = ftpClient.storeFile(localFile.getName(), inStream);
            if (success == true) {
                log.info(localFile.getName() + "上传成功");
                return success;
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error(localFile + "未找到");
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inStream != null) {
                try {
                    inStream.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return success;
    }

    /*** 
     * 下载文件 
     * @param remoteFileName   待下载文件名称 
     * @param localDires 下载到当地那个路径下 
     * @param remoteDownLoadPath remoteFileName所在的路径 
     * */

    public static boolean downloadFile(String remoteFileName, String localDires,String remoteDownLoadPath) {
        String strFilePath = localDires + remoteFileName;
        BufferedOutputStream outStream = null;
        boolean success = false;
        try {
            ftpClient.changeWorkingDirectory(remoteDownLoadPath);
            outStream = new BufferedOutputStream(new FileOutputStream(strFilePath));
            log.info(remoteFileName + "开始下载....");
            success = ftpClient.retrieveFile(remoteFileName, outStream);
            if (success == true) {
                log.info(remoteFileName + "成功下载到" + strFilePath);
                return success;
            }
        }catch (Exception e) {
            e.printStackTrace();
            log.error(remoteFileName + "下载失败");
        }finally {
            if (null != outStream) {
                try {
                    outStream.flush();
                    outStream.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (success == false) {
            log.error(remoteFileName + "下载失败!!!");
        }
        return success;
    }

    /*** 
     * @上传文件夹
     * @param localDirectory 当地文件夹 
     * @param remoteDirectoryPath Ftp 服务器路径 以目录"/"结束 
     * */
    public static boolean uploadDirectory(String localDirectory,String remoteDirectoryPath) {
        File src = new File(localDirectory);
        try {
            remoteDirectoryPath = remoteDirectoryPath + src.getName() + "/";
            boolean makeDirFlag = ftpClient.makeDirectory(remoteDirectoryPath);
            System.out.println("localDirectory : " + localDirectory);
            System.out.println("remoteDirectoryPath : " + remoteDirectoryPath);
            System.out.println("src.getName() : " + src.getName());
            System.out.println("remoteDirectoryPath : " + remoteDirectoryPath);
            System.out.println("makeDirFlag : " + makeDirFlag);
            // ftpClient.listDirectories();
        }catch (IOException e) {
            e.printStackTrace();
            log.info(remoteDirectoryPath + "目录创建失败");
        }
        File[] allFile = src.listFiles();
        for (int i = 0;i < allFile.length;i++) {
            if (!allFile[i].isDirectory()) {
                String srcName = allFile[i].getPath();
                uploadFile(new File(srcName), remoteDirectoryPath);
            }
        }
        for (int j = 0;j < allFile.length;j++) {
            if (allFile[j].isDirectory()) {
                // 递归  
                uploadDirectory(allFile[j].getPath(),remoteDirectoryPath);
            }
        }
        return true;
    }

    /*** 
     * @下载文件夹
     * @param localDirectoryPath 本地地址
     * @param remoteDirectory 远程文件夹 
     * */
    public static boolean downLoadDirectory(String localDirectoryPath,String remoteDirectory) {
        try {
            localDirectoryPath = localDirectoryPath + "//";
            new File(localDirectoryPath).mkdirs();
//            ftpClient.changeWorkingDirectory(remoteDirectory);
//            ftpClient.enterLocalPassiveMode();
//            ftpClient.configure(new FTPClientConfig("com.example.ftp.UnixFTPEntryParser"));
            FTPFile[] allFile = ftpClient.listFiles(remoteDirectory);
            for (int i = 0;i < allFile.length;i++) {
                if(!allFile[i].getName().equals(".") && !allFile[i].getName().equals("..")){
                    if (!allFile[i].isDirectory()) {
                        downloadFile(allFile[i].getName(),localDirectoryPath, remoteDirectory);
                    }
                }
            }
            for (int j = 0;j < allFile.length;j++) {
                if(!allFile[j].getName().equals(".") && !allFile[j].getName().equals("..")){
                    if (allFile[j].isDirectory()) {
                        String strremoteDirectoryPath = remoteDirectory + "/"+ allFile[j].getName();
                        downLoadDirectory(localDirectoryPath,strremoteDirectoryPath);
                    }
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
            log.info("下载文件夹失败");
            return false;
        }
        return true;
    }

    /**
     * 删除文件
     * @param pathname FTP服务器保存目录
     * @param filename 要删除的文件名称
     * @return
     */
    public static boolean deleteFile(String pathname, String filename){
        try {
            ftpLogin();
            ftpClient.changeWorkingDirectory(pathname);
            log.info(filename + "正在删除....");
            boolean falg = ftpClient.deleteFile( filename);
            if(falg){
                log.info(filename + "删除成功....");
            }
            ftpLogOut();
        } catch (IOException e) {
            e.printStackTrace();
            log.info("下载文件夹失败");
        }
        return true;
    }
}
