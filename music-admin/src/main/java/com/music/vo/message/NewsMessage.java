package com.music.vo.message;

import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("xml")
public class NewsMessage extends BaseMessage {

	public NewsMessage(Map<String, String> requestMap, List<Article> articles) {
		super(requestMap);
		setMsgType("news");
		this.articleCount=articles.size()+"";
		this.articles = articles;
	}

	/**
	 *	图文消息个数；当用户发送文本、图片、视频、图文、地理位置这五种消息时，开发者只能回复1条图文消息；
	 *	其余场景最多可回复8条图文消息
	 */
	@XStreamAlias("ArticleCount")
	private String articleCount;

	/**
	 * 图文集合
	 */
	@XStreamAlias("Articles")
	private List<Article> articles;


	public String getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(String articleCount) {
		this.articleCount = articleCount;
	}
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	
}
