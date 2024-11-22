package com.example.basic2.domain2;

import org.junit.jupiter.api.Test;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional @Commit
class EntityTest {

    @PersistenceContext
    EntityManager em;

    Community community;
    Comment comment1;
    Comment comment2;

    @BeforeEach
    void setUp() {
        community = new Community();
        community.setTitle("test");
        community.setContent("test content");

        comment1 = new Comment();
        comment1.setContent("comment1");

        comment2 = new Comment();
        comment2.setContent("comment2");
    }

    @Test
    public void insert() {
        em.persist(comment1);
        em.persist(comment2);

        community.getComment().add(comment1);   // 리스트에 comment를 넣음.
        community.getComment().add(comment2);

        em.persist(community);
    }

    @Test
    public void select() {
        Community foundCommunity = em.find(Community.class, 1L);
        List<Comment> commentList = foundCommunity.getComment();
        System.out.println(commentList.get(0));
    }
}