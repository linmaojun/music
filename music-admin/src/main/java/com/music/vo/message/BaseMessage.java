package com.music.vo.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * @author linmaojun
 * 类名称: BaseMessage
 * 类描述:
 * 创建时间: 2019年03月12日  16:20
 */
public class BaseMessage {

    public BaseMessage(Map<String, String> requestMap) {
        this.toUserName=requestMap.get("FromUserName");
        this.fromUserName=requestMap.get("ToUserName");
        this.createTime=System.currentTimeMillis()/1000;
    }

    /**
     * 接收方帐号（收到的OpenID）
     */
    @XStreamAlias("ToUserName")
    private String toUserName;
    /**
     * 开发者微信号
     */
    @XStreamAlias("FromUserName")
    private String fromUserName;
    /**
     * 消息创建时间 （整型）
     */
    @XStreamAlias("CreateTime")
    private long createTime;
    /**
     * 消息类型
     */
    @XStreamAlias("MsgType")
    private String msgType;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

}
