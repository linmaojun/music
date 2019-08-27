package com.music.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.net.URLConnection;

/**
 * @author linmaojun
 * 类名称: SendMailTask
 * 类描述:
 * 创建时间: 2019年05月17日  10:06
 */
@Slf4j
@Component
@EnableScheduling
public class SendMailTask {

    public static void testUrlWithTimeOut(String urlString,int timeOutMillSeconds,Integer type){
        long lo = System.currentTimeMillis();
        URL url;
        try {
            url = new URL(urlString);
            URLConnection co =  url.openConnection();
            co.setConnectTimeout(timeOutMillSeconds);
            co.connect();
            log.info("网站连接正常");
        } catch (Exception e1) {
            log.error("网站连接异常");
            SendMailJob.genSengMailJob(type);
        }
        System.out.println(System.currentTimeMillis()-lo);
    }

    @Scheduled(cron = "0 0/3 * * * ? ")
    public void sendMail(){

        testUrlWithTimeOut("https://marketaccess.igskapp.com/gsk/adminLogin", 3000,1);
    }

    public static void main(String[] args) {
        testUrlWithTimeOut("http://cms.igskapp.com/cms/sys/login", 3000,0);
    }
}
