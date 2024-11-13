package com.example.todoapi.member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public Member findByLoginId(String loginId) {
        try {
            return em.createQuery("SELECT m FROM Member m WHERE m.loginId = :loginId", Member.class)
                    .setParameter("loginId", loginId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // 결과가 없을 때 null 반환
        }
    }

    public void deleteById(Long id) {
        Member member = em.find(Member.class, id);
        em.remove(member);
    }

    public void deleteByLoginId(String loginId) {
        Member member = em.find(Member.class, loginId);
        em.remove(member);
    }

    // only for test!
    public void flushAndClear() {
        em.flush();
        em.clear();
    }
}
