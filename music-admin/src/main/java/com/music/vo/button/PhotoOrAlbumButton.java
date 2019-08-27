package com.music.vo.button;

import java.util.ArrayList;
import java.util.List;

public class PhotoOrAlbumButton extends ChildrenButton {

	/**
	 * 按钮类型
	 */
	private String type="pic_photo_or_album";
	/**
	 * key值，通过自定义的key值与用户进行交互
	 */
	private String key;
	/**
	 *	集合
	 */
	private List<ChildrenButton> sub_button = new ArrayList<>();

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

	public List<ChildrenButton> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<ChildrenButton> sub_button) {
		this.sub_button = sub_button;
	}

	public PhotoOrAlbumButton(String name, String key) {
		super(name);
		this.key=key;
	}

}
