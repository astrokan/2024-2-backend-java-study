package com.example.todoapi.friend;

import com.example.todoapi.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FriendRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Friend friend) {
        em.persist(friend);
    }

    public List<Friend> findAllByMember(Member member1) {
        return em.createQuery("select t from Friend as t where t.member1 = :member1", Friend.class)
                .setParameter("member1", member1)
                .getResultList();
    }

    public void deleteByMember(Member member1, Member member2) {
        String jpql = "select t from Friend as t where t.member1 = :member1 and t.member2 = :member2";
        Friend friend = em.createQuery(jpql, Friend.class)
                .setParameter("member1", member1)
                .setParameter("member2", member2)
                .getSingleResult();

        em.remove(friend);
    }
}
