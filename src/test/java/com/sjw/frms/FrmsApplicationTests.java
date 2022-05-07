package com.sjw.frms;

import com.sjw.frms.dao.MemberMapper;
import com.sjw.frms.model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FrmsApplicationTests {

    @Autowired
    MemberMapper memberMapper;
    @Test
    void contextLoads() {
        Member member = new Member();
        member.setMemberName("李强");
        memberMapper.insert(member);
    }

}
