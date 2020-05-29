package com.lrmin.utils;

/**
 * @author lirongmin
 * @date 2020/5/27 0027
 */
public class Message {
    private String text;
    private String type;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Message(String text, String type, String title) {
        this.text = text;
        this.type = type;
        this.title = title;
    }

    public static Message build(String type, String text,String title){
        return new Message(text, type, title);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
