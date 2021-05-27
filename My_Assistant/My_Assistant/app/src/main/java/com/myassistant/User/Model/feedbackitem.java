package com.myassistant.User.Model;

public class feedbackitem {
    String text,title,datetime;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public feedbackitem(String text, String title, String datetime) {
        this.text = text;
        this.title = title;
        this.datetime = datetime;
    }
}
