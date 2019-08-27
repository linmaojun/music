package com.music.vo.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author linmaojun
 * 类名称: Video
 * 类描述:
 * 创建时间: 2019年03月12日  17:34
 */
public class Video {

    public Video(String mediaId, String title, String description) {
        this.mediaId = mediaId;
        this.title = title;
        this.description = description;
    }

    /**
     * 通过素材管理中的接口上传多媒体文件
     */
    @XStreamAlias("MediaId")
    private String mediaId;
    /**
     * 视频消息的标题
     */
    private String title;
    /**
     * 视频消息的描述
     */
    private String description;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
