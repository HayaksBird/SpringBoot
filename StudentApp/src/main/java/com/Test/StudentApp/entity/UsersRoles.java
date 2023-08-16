package com.Test.StudentApp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users_role")
public class UsersRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "role")
    private String role;


    //CONSTRUCTORS
    public UsersRoles() {}


    public UsersRoles(String username, String role) {
        this.username = username;
        this.role = role;
    }

    //Setters & Getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
