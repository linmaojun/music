package com.music.vo.button;

import java.util.List;

/**
 * @author linmaojun
 * 类名称: SubButton
 * 类描述:
 * 创建时间: 2019年03月15日  15:46
 */
public class SubButton extends ChildrenButton{
    /**
     * 子级按钮集合
     */
    private List<ChildrenButton> sub_button;

    public List<ChildrenButton> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<ChildrenButton> sub_button) {
        this.sub_button = sub_button;
    }

    public SubButton(String name) {
        super(name);
    }
}
