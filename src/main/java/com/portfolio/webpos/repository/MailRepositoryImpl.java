package com.portfolio.webpos.repository;

import com.portfolio.webpos.domain.Mail;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MailRepositoryImpl implements MailRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    public Mail save(Mail mail) {
        em.persist(mail);
        return mail;
    }
}
