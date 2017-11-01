package com.wzes.wechat.dao;

/**
 * @author Create by xuantang
 * @date on 11/1/17
 */
public class TextMessage extends BaseWechatMessage {
    /**
     * 文本消息内容
     */
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
