package com.example.socialnetwork.dto;

import com.example.socialnetwork.models.Users;

public class RegistrationDTO {
    private String login ;
    private String password;
    private UsersDTO usersDTO;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UsersDTO getUsersDTO() {
        return usersDTO;
    }

    public void setUsersDTO(UsersDTO usersDTO) {
        this.usersDTO = usersDTO;
    }
}
