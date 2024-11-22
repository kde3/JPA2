package com.example.basic2.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class EntityTest {

    @PersistenceContext
    private EntityManager em;

    User testUser;
    Board testBoard1;
    Board testBoard2;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setName("aaa");
        testUser.setPassword("1234");
        testUser.setLoginId("aaa");
        testUser.setAge(22);

        testBoard1 = new Board();
        testBoard1.setTitle("test1");
        testBoard1.setContent("content1");

        testBoard2 = new Board();
        testBoard2.setTitle("test2");
        testBoard2.setContent("content2");
    }

    @Test
    public void insert() {
        em.persist(testUser);

        testBoard1.setUser(testUser);   // 연관관계 설정
        testBoard2.setUser(testUser);

        em.persist(testBoard1);
        em.persist(testBoard2);
    }

    @Test
    public void select1() {
        User user = em.find(User.class, 1L);
        System.out.println("user = " + user);
    }

    @Test
    public void select2() {
        Board board = em.find(Board.class, 1L);
        System.out.println("board = " + board);
    }

    @Test
    public void update() {
        User user = new User();
        user.setName("이유리");
        user.setPassword("1234");
        user.setLoginId("이유리");
        user.setAge(22);

        em.persist(user);

        Board foundBoard = em.find(Board.class, 2L);
        foundBoard.setUser(user); // 유저가 바뀌고 변경 감지가 일어나면서 update 쿼리가 날라간다.
    }

    // 삭제 안됨. 연관된 board 있기 때문.
    @Test
    public void delete() {
        User foundUser = em.find(User.class, 1L);
        em.remove(foundUser);
    }

    // 삭제됨.
    @Test
    public void delete2() {
        User foundUser = em.find(User.class, 1L);
        Board foundBoard = em.find(Board.class, 1L);

        foundBoard.setUser(null);   // 연관 관계 끊음(FK에 null 넣은 것).
        em.remove(foundUser);
    }

}