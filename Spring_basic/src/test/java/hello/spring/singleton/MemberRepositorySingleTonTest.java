package hello.spring.singleton;

import hello.spring.AppConfig;
import hello.spring.member.MemberRepository;
import hello.spring.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberRepositorySingleTonTest {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    void singleTonTestOfMemberRepository() {
        MemberServiceImpl memberService = ac.getBean(MemberServiceImpl.class);
        MemberRepository memberRepository = ac.getBean(MemberRepository.class);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
    }
}
