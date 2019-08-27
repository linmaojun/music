package com.music.vo.button;

public class ClickButton extends ChildrenButton {
	/**
	 * 按钮类型
	 */
	private String type = "click";
	/**
	 * key值，通过自定义的key值与用户进行交互
	 */
	private String key;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public ClickButton(String name, String key) {
		super(name);
		this.key = key;
	}

}
