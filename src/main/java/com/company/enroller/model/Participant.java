package com.company.enroller.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "participant")
public class Participant {

    @Id
    private String login;

    @Column
    private String password;

    @Autowired
    @Transient
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "meeting_participant", joinColumns = {
            @JoinColumn(name = "participant_login")}, inverseJoinColumns = {@JoinColumn(name = "meeting_id")})
    Set<Meeting> meetings = new HashSet<>();

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
        
    	this.password = bCryptPasswordEncoder.encode(password);
    }
}
