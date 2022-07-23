package hello.spring.member;

import hello.spring.AppConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();

    @Test
    void join(){
        Member member = new Member(1L,"memberA",Grade.VIP);

        memberService.join(member);
        Member member1 = memberService.findMember(1L);

        Assertions.assertEquals(member,member1);
    }
}
