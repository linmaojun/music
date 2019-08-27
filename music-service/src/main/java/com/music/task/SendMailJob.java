package com.music.task;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author lmj
 * @version 1.0.0
 * @date 2018/10/22 10:03
 */
public class SendMailJob {
    /**
     * 发送人邮箱
     */
    private static String myEmailAccount = "13637125641@163.com";

    /**
     * 发送人密码（SMTP密码）
     */
    private static String myEmailPassword = "lmj1015";

    /**
     * 网易163邮箱的 SMTP
     */
    private static String myEmailSMTPHost = "smtp.163.com";

    /**
     * 接收人邮箱
     */
    private static String receiveMailAccount = "1136520063@qq.com";

    private static String fileAddress = "";

    public static void genSengMailJob(Integer type) {
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        // 参数配置
        Properties props = new Properties();
        // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.transport.protocol", "smtp");
        // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.host", myEmailSMTPHost);
        // 需要请求认证
        props.setProperty("mail.smtp.auth", "true");

        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getInstance(props);
        // 设置为debug模式, 可以查看详细的发送 log
        session.setDebug(true);
        Map<String,String> receiveMailMap = new HashMap<>();
        // 3. 创建一封邮件
        if(type == 0){
            receiveMailMap.put("林安生","1136520063@qq.com");
            receiveMailMap.put("林茂君","3005642851@qq.com");
        }else{
            receiveMailMap.put("张波","772977181@qq.com");
        }
        try {
            MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailMap,type);

            // 4. 根据 Session 获取邮件传输对象
            Transport transport = session.getTransport();

            transport.connect(myEmailAccount, myEmailPassword);

            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(message, message.getAllRecipients());

            // 7. 关闭连接
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建一封只包含文本的简单邮件
     *
     * @param session     和服务器交互的会话
     * @param sendMail    发件人邮箱
     * @param receiveMailMap 收件人邮箱
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, Map<String,String> receiveMailMap,Integer type) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail,sendMail,"UTF-8"));

        // 3. 设置收件人地址（可以增加多个收件人、抄送、密送），即下面这一行代码书写多行
        // MimeMessage.RecipientType.TO:发送
        // MimeMessage.RecipientType.CC：抄送
        // MimeMessage.RecipientType.BCC：密送
//        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail,receiveMail,"UTF-8"));
        for (String key : receiveMailMap.keySet()) {
            message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMailMap.get(key),key,"UTF-8"));
        }
        // 4. Subject: 邮件主题
        message.setSubject("葛兰素项目监控提醒", "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）如果标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败
        // 创建附件"节点"
        MimeBodyPart attachment = new MimeBodyPart();
        // 读取本地文件
//        DataHandler dh2 = new DataHandler(new FileDataSource(fileAddress));
//        // 将附件数据添加到"节点"
//        attachment.setDataHandler(dh2);
//        // 设置附件的文件名（需要编码）
//        attachment.setFileName(MimeUtility.encodeText(dh2.getName()));
//        // 设置（文本+图片）和 附件 的关系（合成一个大的混合"节点" / Multipart ）
//        MimeMultipart mm = new MimeMultipart();
//        // 如果有多个附件，可以创建多个多次添加
//        mm.addBodyPart(attachment);
//        mm.setSubType("mixed");
//        message.setContent(mm);
        if(type == 0){
            message.setContent("葛兰素电子订单系统运行异常！！！！！", "text/html;charset=UTF-8");
        }else{
            message.setContent("葛兰素招标管理平台系统运行异常！！！！！", "text/html;charset=UTF-8");
        }
        //6.设置发件的时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }
}
