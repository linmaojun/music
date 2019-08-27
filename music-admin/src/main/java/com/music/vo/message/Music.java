package com.music.vo.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author linmaojun
 * 类名称: Music
 * 类描述:
 * 创建时间: 2019年03月12日  16:59
 */
public class Music {

    public Music(String title, String description, String musicURL, String hQMusicUrl, String thumbMediaId) {
        super();
        this.title = title;
        this.description = description;
        this.musicURL = musicURL;
        this.hQMusicUrl = hQMusicUrl;
        this.thumbMediaId = thumbMediaId;
    }

    /**
     * 音乐标题
     */
    @XStreamAlias("Title")
    private String title;

    /**
     * 音乐描述
     */
    @XStreamAlias("Description")
    private String description;

    /**
     * 音乐链接
     */
    @XStreamAlias("MusicURL")
    private String musicURL;

    /**
     * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
     */
    @XStreamAlias("HQMusicUrl")
    private String hQMusicUrl;

    /**
     * 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
     */
    @XStreamAlias("ThumbMediaId")
    private String thumbMediaId;

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

    public String getMusicURL() {
        return musicURL;
    }

    public void setMusicURL(String musicURL) {
        this.musicURL = musicURL;
    }

    public String gethQMusicUrl() {
        return hQMusicUrl;
    }

    public void sethQMusicUrl(String hQMusicUrl) {
        this.hQMusicUrl = hQMusicUrl;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }
}
