package com.music.util;

import com.music.vo.*;
import com.music.vo.message.*;
import com.thoughtworks.xstream.XStream;
import net.dongliu.requests.Requests;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author linmaojun
 * 类名称: MessageUtil
 * 类描述:
 * 创建时间: 2019年03月12日  10:16
 */
public class MessageUtil {
    /**
     * 第三方用户唯一凭证
     */
    private static final String APPID="wxa6ceffe407065f95";
    /**
     * 第三方用户唯一凭证密钥，即appsecret
     */
    private static final String APPSECRET="58e9bf9028727c5aeb63bcb4c5670b77";
    /**
     * 获取token请求的地址
     */
    private static final String GET_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    /**
     * 用于存储token
     */
    private static AccessToken accessToken;

    /**
     * 获取token
     */
    public static void getToken(){
        String url = GET_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
        String info = Requests.get(url).send().readToText();
        JSONObject jsonObject = JSONObject.fromObject(info);
        String token = jsonObject.getString("access_token");
        String expireIn = jsonObject.getString("expires_in");
        //创建token对象,并存起来。
        accessToken = new AccessToken(token, expireIn);
    }

    /**
     * 获取token的方法
     * @return
     */
    public static String getAccessToken() {
        if(accessToken==null||accessToken.isExpired()) {
            getToken();
        }
        return accessToken.getAccessToken();
    }

    /**
     * 解析xml
     * @param inputStream
     * @return
     */
    public static Map<String,String> parseRequest(InputStream inputStream){
        Map<String,String> map = new HashMap<>();
        SAXReader reader = new SAXReader();
        try {
            //读取输入流，获取文档对象
            Document document = reader.read(inputStream);
            //根据文档对象获取根节点
            Element root = document.getRootElement();
            //获取根节点中的所有子节点
            List<Element> elements = root.elements();
            for (Element element : elements) {
                map.put(element.getName(),element.getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 用于处理所有的事件和消息的回复
     * @param map
     * @return
     */
    public static String getRespose(Map<String,String> map){
        BaseMessage msg = null;
        String msgType = map.get("MsgType");
        switch (msgType) {
            //文本消息处理
            case MessageType.RESP_MESSAGE_TYPE_TEXT :
                msg= dealTextMessage(map);
                break;
            //图片消息处理
            case MessageType.REQ_MESSAGE_TYPE_IMAGE:
                msg=dealImage(map);
                break;
            //语音消息处理
            case "voice":

                break;
            //视频消息处理
            case "video":

                break;
            //小视频消息处理
            case "shortvideo":

                break;
            //地理位置消息处理
            case "location":

                break;
            //链接消息处理
            case "link":

                break;
            //事件推送处理
            case "event":
                break;
            default:
                break;
        }
        //把消息对象处理为xml数据包
        if(msg!=null) {
            return beanToXml(msg);
        }
        return null;
    }

    /**
     * 进行图片处理
     * @param map
     * @return
     */
    private static BaseMessage dealImage(Map<String,String> map) {

        return null;
    }

    /**
     * 处理文本信息
     * @param map
     * @return
     */
    private static BaseMessage dealTextMessage(Map<String,String> map) {
        //用户发来的内容
        String msg = map.get("Content");
        if(msg.equals("图文")) {
            List<Article> articles = new ArrayList<>();
            articles.add(new Article("标题", "详细介绍", "http://mmbiz.qpic.cn/mmbiz_jpg/qFicRjgaMkg3O7wPnEujiaeRKZVO8WZyzTr6T567OHDITt0VLs7kkoa1zyibs7cb5FykHLicFvib8GNQn7nRoiajx0Qg/0", "http://linmaojun123.nat300.top/"));
            articles.add(new Article("这是图文消息的标题", "这是图文消息的详细介绍", "http://mmbiz.qpic.cn/mmbiz_jpg/qFicRjgaMkg3O7wPnEujiaeRKZVO8WZyzToTcVzjyZ2qT4lAZ6e1nmwj1EdnBNanYTgICFR7qO5Hza0JGqs34t4A/0", "http://linmaojun123.nat300.top/"));
            NewsMessage nm = new NewsMessage(map, articles);
            return nm;
        }
        if(msg.equals("登录")) {
            String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb6777fffdf5b64a4&redirect_uri=http://www.6sdd.com/weixin/GetUserInfo&response_type=code&scope=snsapi_userinfo#wechat_redirect";
            TextMessage tm = new TextMessage(map, "点击<a href=\""+url+"\">这里</a>登录");
            return tm;
        }
        //调用方法返回聊天的内容
//        String resp = chat(msg);
//        TextMessage tm = new TextMessage(map, resp);
        return null;
    }


    private static String beanToXml(BaseMessage msg) {
        XStream stream = new XStream();
        //设置需要处理XStreamAlias("xml")注释的类
        stream.processAnnotations(TextMessage.class);
        stream.processAnnotations(ImageMessage.class);
        stream.processAnnotations(MusicMessage.class);
        stream.processAnnotations(NewsMessage.class);
        stream.processAnnotations(VideoMessage.class);
        stream.processAnnotations(VoiceMessage.class);
        String xml = stream.toXML(msg);
        return xml;
    }



}
