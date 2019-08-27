package com.music.util;

import java.io.*;
import java.net.SocketException;

import com.music.model.Ftp;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

public class FtpUtils {

    private static Logger logger=Logger.getLogger(FtpUtils.class);
    private static FTPClient ftp;
    /**
     * 获取ftp连接
     * @param f
     * @return
     * @throws Exception
     */
    public static boolean connectFtp(Ftp f){
        boolean flag=false;
        try {
            ftp=new FTPClient();
            ftp.setControlEncoding("utf-8");
            int reply;
            System.out.println("开始连接ftp服务器:"  + f.getIpAddr() + ":" + f.getPort());
            // 连接ftp服务器
            if (f.getPort()==null) {
                ftp.connect(f.getIpAddr(),21);
            }else{
                ftp.connect(f.getIpAddr(),f.getPort());
            }
            // 登录ftp服务器
            ftp.login(f.getUserName(), f.getPwd());
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);

            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                System.out.println("ftp服务器连接失败");
                return flag;
            }
            ftp.changeWorkingDirectory(f.getPath());
            flag = true;
            System.out.println("ftp服务器连接成功！");
        } catch (SocketException e) {
            e.printStackTrace();
            flag = false;
            System.out.println("FTP的IP地址可能错误，请正确配置。");
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
            System.out.println("FTP的端口错误,请正确配置。");
        }
        return flag;
    }

    /**
     * 关闭ftp连接
     */
    public static void closeFtp(){
        if (ftp!=null && ftp.isConnected()) {
            try {
                ftp.logout();
                ftp.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ftp上传文件
     * @param f
     * @throws Exception
     */
    public static void upload(File f){
        try {
            if (f.isDirectory()) {
                ftp.makeDirectory(f.getName());
                ftp.changeWorkingDirectory(f.getName());
                String[] files=f.list();
                for(String fstr : files){
                    File file1=new File(f.getPath()+"/"+fstr);
                    if (file1.isDirectory()) {
                        upload(file1);
                        ftp.changeToParentDirectory();
                    }else{
                        File file2=new File(f.getPath()+"/"+fstr);
                        FileInputStream input=new FileInputStream(file2);
                        ftp.storeFile(file2.getName(),input);
                        input.close();
                    }
                }
            }else{
                File file2=new File(f.getPath());
                FileInputStream input=new FileInputStream(file2);
                ftp.storeFile(file2.getName(),input);
                input.close();
            }
            System.out.println("上传文件成功！");
        } catch (IOException e) {
            System.out.println("上传文件失败！");
            e.printStackTrace();
        }
    }

    /**
     * 下载链接配置
     * @param f
     * @param localBaseDir 本地目录
     * @param remoteBaseDir 远程目录
     * @throws Exception
     */
    public static void startDown(Ftp f,String localBaseDir,String remoteBaseDir,String alone ){
        if (FtpUtils.connectFtp(f)) {
            try {
                FTPFile[] files = null;
                boolean changedir = ftp.changeWorkingDirectory(remoteBaseDir);
                if (changedir) {
                    files = ftp.listFiles();
                    if(null!=alone&&!("".equals(alone))){
                        for(int i=0;i<files.length;i++){
                            if(files[i].getName().contains(alone)){
                                downloadFile(files[i], localBaseDir, f.getPath());
                            }
                        }
                    }else{
                        for (int i = 0; i < files.length; i++) {
                            downloadFile(files[i], localBaseDir, f.getPath());
                        }
                    }
                }
                System.out.println("下载"+alone+"文件成功！");
            } catch (FileNotFoundException e) {
                System.out.println("没有找到" + alone + "文件");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("下载"+alone+"文件成功！");
            }
        }else{
            System.out.println("连接ftp失败");
        }

    }


    /**
     *
     * 下载FTP文件
     * 当你需要下载FTP文件的时候，调用此方法
     * 根据<b>获取的文件名，本地地址，远程地址</b>进行下载
     *
     * @param ftpFile
     * @param relativeLocalPath 本地目录
     * @param relativeRemotePath
     */
    private  static void downloadFile(FTPFile ftpFile, String relativeLocalPath,String relativeRemotePath) {
        if (ftpFile.isFile()) {
            if (ftpFile.getName().indexOf("?") == -1) {
                OutputStream outputStream = null;
                try {
                    File locaFile= new File(relativeLocalPath+ ftpFile.getName());
                    //判断文件是否存在，存在则返回
                    if(locaFile.exists()){
                        return;
                    }else{
                        outputStream = new FileOutputStream(relativeLocalPath+ ftpFile.getName());
                        ftp.retrieveFile(ftpFile.getName(), outputStream);
                        outputStream.flush();
                        outputStream.close();
                    }
                } catch (Exception e) {
                    logger.error(e);
                } finally {
                    try {
                        if (outputStream != null){
                            outputStream.close();
                        }
                    } catch (IOException e) {
                        logger.error("输出文件流异常");
                    }
                }
            }
        } else {
            String fileName = new File(relativeRemotePath).getName();
            relativeLocalPath = relativeLocalPath + fileName + "//";
            new File(relativeLocalPath).mkdirs();
            try {
                FTPFile[] allFile = ftp.listFiles(relativeRemotePath);
                for (int currentFile = 0;currentFile < allFile.length;currentFile++) {
                    if (!allFile[currentFile].isDirectory()) {
                        downloadFile(allFile[currentFile],relativeLocalPath, relativeRemotePath);
                    }else{
                        String strremoteDirectoryPath = relativeRemotePath + "/"+ allFile[currentFile].getName();
                        downloadFile(allFile[currentFile],relativeLocalPath, strremoteDirectoryPath);
                    }
                }
            } catch (Exception e) {
                logger.error(e);
            }
        }
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
            ftp.changeWorkingDirectory(remoteDownLoadPath);
            outStream = new BufferedOutputStream(new FileOutputStream(
                    strFilePath));
            System.out.println(remoteFileName + "开始下载....");
            success = ftp.retrieveFile(remoteFileName, outStream);
            if (success == true) {
                System.out.println(remoteFileName + "成功下载到" + strFilePath);
                return success;
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(remoteFileName + "下载失败");
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
            System.out.println(remoteFileName + "下载失败!!!");
        }
        return success;
    }

    /***
     * @下载文件夹
     * @param localDirectoryPath 本地地址
     * @param remoteDirectory 远程文件夹
     * */
    public static boolean downLoadDirectory(String localDirectoryPath,String remoteDirectory) {
        try {
            String fileName = new File(remoteDirectory).getName();
            System.out.println(fileName);
            localDirectoryPath = localDirectoryPath + fileName + File.separator;
            new File(localDirectoryPath).mkdirs();
            FTPFile[] allFile = ftp.listFiles(remoteDirectory);
            for (int currentFile = 0;currentFile < allFile.length;currentFile++) {
                if (!allFile[currentFile].isDirectory()) {
                    downloadFile(allFile[currentFile].getName(),localDirectoryPath, remoteDirectory);
                }
            }
            for (int currentFile = 0;currentFile < allFile.length;currentFile++) {
                if (allFile[currentFile].isDirectory()) {
                    System.out.println(allFile[currentFile].getName());
                    String strremoteDirectoryPath = remoteDirectory + "/"+ allFile[currentFile].getName();
                    downLoadDirectory(localDirectoryPath,strremoteDirectoryPath);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("下载文件夹失败");
            return false;
        }
        return true;
    }
}
