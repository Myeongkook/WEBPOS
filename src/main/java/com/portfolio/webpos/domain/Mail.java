package com.portfolio.webpos.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Mail {

    @Id @GeneratedValue
    @Column(name = "mail_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private int code;

    @CreationTimestamp
    private LocalDateTime sendTime;

    public Mail(Member member, int code) {
        this.member = member;
        this.code = code;
    }

    public Mail() {
    }
}
