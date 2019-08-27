package com.music.vo.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * @author linmaojun
 * 类名称: TextMessage
 * 类描述:
 * 创建时间: 2019年03月12日  16:27
 */
@XStreamAlias("xml")
public class TextMessage extends BaseMessage{
    /**
     * 回复的消息内容
     * @return
     */
    @XStreamAlias("Content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TextMessage(Map<String, String> requestMap, String content) {
        super(requestMap);
        // 设置文本消息的msgtype为text
        this.setMsgType("text");
        this.content = content;
    }
}
