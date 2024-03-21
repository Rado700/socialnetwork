package com.example.socialnetwork.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
//@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String contento;
    private Date date;
    private Time time;

    @JsonManagedReference
    @OneToMany(mappedBy = "post",fetch = FetchType.EAGER)
    private Set<Likes> likes = new HashSet<>();
    @JsonManagedReference
    @OneToMany(mappedBy = "post",fetch = FetchType.EAGER)
    private Set<Commentarie> comment = new HashSet<>();

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;



    public Post(String contento, Date date, Time time, Users users) {
        this.contento = contento;
        this.date = date;
        this.time = time;
        this.users = users;

    }



    public Post() {

    }

    public Set<Commentarie> getComment() {
        return comment;
    }

//    public Users getUsers() {
//        return users;
//    }
//
//    public void setUsers(Users users) {
//        this.users = users;
//    }

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