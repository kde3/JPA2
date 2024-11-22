package com.practice.jpa.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
@Transactional
@Commit
public class EntityTest2 {
    @PersistenceContext
    EntityManager em;

    Member member;

    @BeforeEach
    void setUp() {
        member = new Member();
        member.setName("김철수");
        member.setAge(20);
        member.setEmail("test@naver.com");
        member.setPassword("1234");

        em.persist(member);
        em.flush();
        em.clear();
    }

    // 쿼리 순서 보기
    @Test
    void sqlOrder() {
        Member foundMember = em.find(Member.class, member.getId());
    }
}
