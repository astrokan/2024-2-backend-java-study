package com.example.todoapi.friend;

import com.example.todoapi.member.Member;
import com.example.todoapi.todo.Todo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FriendRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Friend friend) {
        em.persist(friend);
    }

    public Friend findOneById(Long id) {
        return em.find(Friend.class, id);
    }

    public List<Friend> findAllReceiver(Member sender) {
        return em.createQuery("select t from Friend as t where t.sender = :sender and t.status = 'accepted'", Friend.class)
                .setParameter("sender", sender)
                .getResultList();
    }

    public void deleteById(Long id) {
        em.remove(findOneById(id));
    }

    public void deleteByMember(Member member1, Member member2) {
        String jpql = "select t from Friend as t where t.sender = :member1 and t.receiver = :member2";
        Friend friend = em.createQuery(jpql, Friend.class)
                .setParameter("member1", member1)
                .setParameter("member2", member2)
                .getSingleResult();
        em.remove(friend);
    }


}
