package com.example.socialnetwork.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String contento;
    private Integer users_id;
    private Date date;
    private Time time;

    @JsonManagedReference
    @OneToMany(mappedBy = "post")
    private Set<Likes> likes = new HashSet<>();
    @JsonManagedReference
    @OneToMany(mappedBy = "post")
    private Set<Commentarie> comment = new HashSet<>();

    public Set<Commentarie> getComment() {
        return comment;
    }

    public void setComment(Set<Commentarie> comment) {
        this.comment = comment;
    }

    public Set<Likes> getLikes() {
        return likes;
    }

    public void setLikes(Set<Likes> likes) {
        this.likes = likes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContento() {
        return contento;
    }

    public void setContento(String contento) {
        this.contento = contento;
    }

    public Integer getUsers_id() {
        return users_id;
    }

    public void setUsers_id(Integer users_id) {
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
//    Возвращать все комментарии, которые оставил пользователь в нашей социальной сети.
//        В результате вернуть информацию об id комментария, полная информация о посте и тело комментария
//
//        /user/comments/{userId}