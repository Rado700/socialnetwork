package com.example.socialnetwork.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
//@Getter
//@Setter
//@RequiredArgsConstructor
//@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Можно ли менять структуру уже в созданной таблице (отсюда)
    private String name;
    private String surname;
    private String url;
    private Boolean premium;
    @JsonManagedReference
    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private Set<Likes> likes = new HashSet<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "users",fetch = FetchType.EAGER)
    private Set<Commentarie>commentaries = new HashSet<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "users",fetch = FetchType.EAGER)
    private Set<Post>posts = new HashSet<>();

    @JsonBackReference
    @OneToOne(mappedBy = "users",fetch = FetchType.EAGER)
    private Authorisation authorisation;


    @JsonBackReference
    @OneToOne(mappedBy = "users",fetch = FetchType.EAGER)
    private Registration registration;

    public Users() {

    }

    public Users(String name, String surname, String url, Boolean premium) {
        this.name = name;
        this.surname = surname;
        this.url = url;
        this.premium = premium;
    }

    public Authorisation getAuthorisation() {
        return authorisation;
    }

    public void setAuthorisation(Authorisation authorisation) {
        this.authorisation = authorisation;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Commentarie> getCommentaries() {
        return commentaries;
    }

    public void setCommentaries(Set<Commentarie> commentaries) {
        this.commentaries = commentaries;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getPremium() {
        return premium;
    }

    public void setPremium(Boolean premium) {
        this.premium = premium;
    }

    public Set<Likes> getLikes() {
        return likes;
    }

    public void setLikes(Set<Likes> likes) {
        this.likes = likes;
    }
}
