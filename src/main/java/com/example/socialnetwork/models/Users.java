package com.example.socialnetwork.models;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
//@Getter
//@Setter
//@RequiredArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //Можно ли менять структуру уже в созданной таблице (отсюда)
    private String name;
    private String surname;
    private String url;
    private Boolean premium;
    @OneToMany(mappedBy = "users")
    private Set<Likes> likes = new HashSet<>();


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
