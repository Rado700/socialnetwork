package com.example.socialnetwork.models;

import java.sql.Date;
import java.sql.Time;

public class Post {
    private String contento;
    private int users_id;
    private Date date;
    private Time time;

    public String getContento() {
        return contento;
    }

    public void setContento(String contento) {
        this.contento = contento;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
