package com.practice.jpa.repository;

import com.practice.jpa.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// 순수 repository
@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager em;

    public Member save(Member member) {
        em.persist(member);
        return member; // 그대로 반환하는 건데 의미있나? -> ㅇㅇ. 영속화되었기 때문에 id가 있기 때문이다.
    }

    // 단건 조회는 무조건 Optional로 감싸서 반환해야 한다.
    public Optional<Member> findOne(Long memberId) {
        return Optional.ofNullable(em.find(Member.class, memberId));
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public void delete(Member member) {
        em.remove(member);
    }
}
