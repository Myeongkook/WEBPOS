package com.portfolio.webpos.repository;

import com.portfolio.webpos.domain.Mail;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MailRepositoryImpl implements MailRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    public Mail save(Mail mail) {
        em.persist(mail);
        return mail;
    }

    @Override
    public Mail findByMemberId(Long id) {
        List<Mail> resultList = em.createQuery("select m from Mail m where m.member.id =: id", Mail.class)
                .setParameter("id", id)
                .getResultList();
        return resultList.get(resultList.size()-1);
    }

}
