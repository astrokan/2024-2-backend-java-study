package com.example.todoapi.todo;

import com.example.todoapi.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Todo todo) {
        em.persist(todo);
    }

    public Todo findById(Long id) {
        return em.find(Todo.class, id);
    }

    public List<Todo> findAll() {
        return em.createQuery("select t from Todo as t", Todo.class)
                .getResultList();
    }
    public List<Todo> findAllByMember(Member member) {
        return em.createQuery("select t from Todo as t where t.member = :member", Todo.class)
                .setParameter("member", member)
                .getResultList();
    }
    public void deleteById(Long id) {
        Todo todo = em.find(Todo.class, id);
        em.remove(todo);
    }

    // test 용도로만 사용!
    public void flushAndClear() {
        em.flush();
        em.clear();
    }
}
