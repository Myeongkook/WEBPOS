package com.portfolio.webpos.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    private Long id;
    private String email;
    private String password;
    private String salt;

    public Member(){}

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
