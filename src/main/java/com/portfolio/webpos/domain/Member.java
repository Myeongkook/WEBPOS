package com.portfolio.webpos.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String salt;

    @Column(name = "member_mail_certified")
    @ColumnDefault("0")
    private boolean mailCertified;

    private String shop;

    public Member(){}

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
