package com.practice.jpa.domain;

import com.practice.jpa.type.CommentStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class CommentTest {
    @PersistenceContext
    EntityManager em;

    Comment comment;

    @BeforeEach
    void setUp() {
        comment = new Comment();
        comment.setWriter("홍길동");
        comment.setContent("안녕하세요");
        comment.setCommentStatus(CommentStatus.PUBLIC);
    }

    @Test
    public void insert() {
        em.persist(comment);
    }

    @Test
    public void select() {
        em.persist(comment);
        em.flush();
        em.clear();

        Comment foundComment = em.find(Comment.class, comment.getId());
        System.out.println("foundComment = " + foundComment);
    }

    @Test
    public void update() {
        em.persist(comment);
        em.flush();
        em.clear();

        Comment foundComment = em.find(Comment.class, comment.getId());
        foundComment.setContent("반가워요??");
    }

    @Test
    public void delete() {
        em.persist(comment);
        em.flush();
        em.clear();

        Comment foundComment = em.find(Comment.class, comment.getId());
        em.remove(foundComment);
    }

    // JPQL 사용하기
    @Test
    public void selectAll() {
        em.persist(comment);
        em.flush();
        em.clear();

        List<Comment> commentList = em.createQuery("select c from Comment c", Comment.class).getResultList();
        System.out.println("commentList = " + commentList);
    }

    // 일부 필드만 모두 조회하기
    @Test
    public void selectContent() {
        em.persist(comment);
        em.flush();
        em.clear();

        String jpql = "select c.content from Comment c";
        em.createQuery(jpql, String.class).getResultList();
    }
}