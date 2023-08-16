package com.Test.StudentApp.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    /*
    All the roles of the current user will be contained here

    in OneToMany:
    We specify that the object should be initialized completely (without the proxy object)
    Also the child entities should be persisted with this entity

    in JoinColumn:
    Specify the foreign key column and the primary key column
    */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private List<UsersRoles> usersRoles;


    //CONSTRUCTORS
    public User() {}


    @JsonCreator
    public User(@JsonProperty("role") String role,
                @JsonProperty("username") String username,
                @JsonProperty("password") String password) {

        usersRoles = new ArrayList<>();

        usersRoles.add(new UsersRoles(username, "ROLE_STUDENT"));   //Given by default to any

        switch (role.toUpperCase()) {
            case "TEACHER" -> usersRoles.add(new UsersRoles(username, "ROLE_TEACHER"));
            case "PRINCIPAL" -> {
                usersRoles.add(new UsersRoles(username, "ROLE_TEACHER"));
                usersRoles.add(new UsersRoles(username, "ROLE_PRINCIPAL"));
            }
        }

        this.username = username;
        this.password = password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();

        for (UsersRoles userRole : usersRoles) {
            roles.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        return roles;
    }


    @Override
    public String getPassword() { return password; }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UsersRoles> getUsersRoles() {
        return usersRoles;
    }

    public void setUsersRoles(List<UsersRoles> usersRoles) {
        this.usersRoles = usersRoles;
    }
}
