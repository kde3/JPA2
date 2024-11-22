package com.practice.jpa.repository;

import com.practice.jpa.domain.Member;
import com.practice.jpa.type.MemberGender;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    Member member;

    @BeforeEach
    void setUp() {
        member = new Member();
        member.setName("test");
        member.setPassword("1234");
        member.setEmail("test@naver.com");
        member.setAge(20);
        member.setMemberGender(MemberGender.MALE);
    }

    @Test
    void save() {
        Member savedMember = memberRepository.save(member);

        assertThat(savedMember.getId()).isNotNull();
    }

    @Test
    void findOne() {
        memberRepository.save(member);
        Optional<Member> opt = memberRepository.findOne(1L);
        Member foundMember = opt.orElse(null);

        assertThat(foundMember).isNotNull();
    }

    @Test
    void findAll() {
        memberRepository.save(member);
        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList).isNotEmpty();    // 리스트의 요소가 하나라도 있어야 함.
    }

    @Test
    void delete() {
        Member savedMember = memberRepository.save(member);
        memberRepository.delete(savedMember);
        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList).isEmpty();
    }
}