package com.portfolio.webpos.repository;

import com.portfolio.webpos.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepositoryImpl implements MemberRepository {


    @PersistenceContext
    EntityManager em;

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Member findByEmail(String email) {
        try {
            return em.createQuery("select m from Member m where m.email =: email", Member.class)
                    .setParameter("email", email)
                    .getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    @Override
    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    @Override
    public boolean findAuthStatus(String mail) {
        return em.createQuery("select m from Member m where m.email =:mail", Member.class)
                .setParameter("mail", mail)
                .getSingleResult().isMailCertified();

    }

    @Override
    public void modifyMemberAuth(Long id) {
        Member member = em.find(Member.class, id);
        member.setMailCertified(true);
    }


}
