package com.music.vo;

public class AccessToken {

	/**
	 * 获取到的凭证
	 */
	private String accessToken;
	/**
	 * 据到期时间
	 */
	private long expireTime;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}

	public AccessToken(String accessToken, String expireIn) {
		super();
		this.accessToken = accessToken;
		expireTime = System.currentTimeMillis()+Integer.parseInt(expireIn)*1000;
	}

	/**
	 * 判断token是否过期
	 * @return
	 */
	public boolean isExpired() {
		return System.currentTimeMillis()>expireTime;
	}

}
