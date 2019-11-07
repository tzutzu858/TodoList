package com.macroviz.todolist;

public class Memo {
    private int id;
    private String memo;
    private String date;
    private String bg_color;
    private int remind;

    public Memo(int id, String memo, String date, int remind,String bg_color) {
        this.id = id;
        this.memo = memo;
        this.date = date;
        this.remind = remind;
        this.bg_color = bg_color;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBg_color() {
        return bg_color;
    }

    public void setBg_color(String bg_color) {
        this.bg_color = bg_color;
    }

    public int isRemind() {
        return remind;
    }

    public void setRemind(int remind) {
        this.remind = remind;
    }
}
