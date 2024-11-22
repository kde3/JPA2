package com.practice.jpa.domain;

import com.practice.jpa.type.MemberGender;
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
@Commit         // 강제 커밋. test에서는 @Transactional이 있어도 롤백되기 때문이다.
class EntityTest {

    @PersistenceContext
    EntityManager em;

    // create
    @Test
    public void memberTest() {
        Member member = new Member();
        member.setName("홍길동");
        member.setAge(22);
        member.setEmail("aaa@naver.com");
        member.setPassword("1234");
        member.setMemberGender(MemberGender.MALE);

        em.persist(member);
        em.close();
    }

    // select
    @Test
    public void select() {
        Member member = new Member();
        member.setName("test");
        member.setAge(21);
        member.setEmail("test@naver.com");
        member.setPassword("1234");
        member.setMemberGender(MemberGender.MALE);

        em.persist(member);

        Member foundMember = em.find(Member.class, member.getId());

        System.out.println("foundMember = " + foundMember);

        em.close();
    }

    // update : 영속 상태 entity 변경으로 update 쿼리 날라감.
    @Test
    public void update() {
        Member foundMember = em.find(Member.class, 1L);
        foundMember.setAge(0);
    }

    // update X : 준영속 상태 entity 변경으로 update 쿼리 날라가지 않음.
    @Test
    public void update2() {
        Member member = new Member();
        member.setName("test");
        member.setAge(21);
        member.setEmail("test@naver.com");
        member.setPassword("1234");
        member.setMemberGender(MemberGender.FEMALE);

        em.persist(member);

        em.flush();
        em.clear(); // 준영속 됨.

        member.setAge(99);  // 변경해도 업데이트 쿼리 날라가지 X.
    }

    // 영속상태일 때 delete : 잘됨!
    @Test
    public void delete() {
        Member foundMember = em.find(Member.class, 1L);
        em.remove(foundMember);
    }

    // 비영속일 때 delete : 에러
    @Test
    public void delete2() {
        Member member = new Member();
        member.setId(1L);
        member.setName("test");
        member.setAge(21);
        member.setEmail("test@naver.com");
        member.setPassword("1234");
        member.setMemberGender(MemberGender.FEMALE);

        em.remove(member);
    }

    // 준영속일 때 delete : 에러
    @Test
    public void delete3() {
        Member member = new Member();
        member.setName("test");
        member.setAge(21);
        member.setEmail("test@naver.com");
        member.setPassword("1234");
        member.setMemberGender(MemberGender.FEMALE);

        em.persist(member);
        em.flush();
        em.clear();

        em.remove(member);
    }

    @Test
    public void detach() {
        Member member = new Member();
        member.setName("test");
        member.setAge(21);
        member.setEmail("test@naver.com");
        member.setPassword("1234");
        member.setMemberGender(MemberGender.FEMALE);

        em.persist(member);
        em.flush();
        member.setAge(88);

        em.detach(member);
    }

    @Test
    public void merge() {
        Member member = new Member();
        member.setName("test");
        member.setAge(21);
        member.setEmail("test@naver.com");
        member.setPassword("1234");
        member.setMemberGender(MemberGender.FEMALE);

        em.persist(member);
        em.flush();         // insert 날림.
        em.clear();

        Member mergedMember = em.merge(member);// 준영속 상태를 영속 상태로 변경
        mergedMember.setAge(99);
    }

    @Test
    public void merge2() {
        Member foundMember = em.find(Member.class, 1L); // 조회했으니 영속
        em.detach(foundMember); // 준영속으로 만듦.
        foundMember.setName("아무무"); // 조회해온 entity를 변경

        // merge 시 DB에서 조회함. entity와 snapshot에 저장함.
        // 조회한 entity를 아규먼트로 넣어준 entity의 값으로 merge함. 이때 entity와 snapshot이 달라짐.
        Member mergedMember = em.merge(foundMember);
        System.out.println("mergedMember = " + mergedMember); // update 쿼리 날라가기 전에 이미 이름이 아무무로 변경되어 있음.

        // test 메소드가 끝나면서 flush가 발생하는데, 변경감지가 일어나면서 update 쿼리가 날라감.
    }

    @Test
    public void merge3() {
        Member member = new Member();
        member.setName("test");
        member.setAge(21);
        member.setEmail("test2@naver.com");
        member.setPassword("1234");
        member.setMemberGender(MemberGender.FEMALE);

        Member mergedMember = em.merge(member);
        System.out.println("mergedMember = " + mergedMember);
    }
}