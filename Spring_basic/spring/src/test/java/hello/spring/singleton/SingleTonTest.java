package hello.spring.singleton;

import hello.spring.AppConfig;
import hello.spring.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingleTonTest {

    @Test
    @DisplayName("스프링 없는 순수 DiContainer")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("스프링컨테이너와 싱클톤")
    void springContainer() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // 호출할 때 마다 객체를 생성
        MemberService memberService1 = applicationContext.getBean(MemberService.class);
        MemberService memberService2 = applicationContext.getBean(MemberService.class);

        // 참조값이 같은 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}
