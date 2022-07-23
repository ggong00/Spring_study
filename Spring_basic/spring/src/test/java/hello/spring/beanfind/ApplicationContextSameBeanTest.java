package hello.spring.beanfind;

import hello.spring.member.MemberRepository;
import hello.spring.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(sameBeanTestConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복오류가 발생한다.")
    void findBeanByDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemoryMemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 이름을 지정해준다.")
    void findBeanByName() {
        MemberRepository memberRepository1 = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository1).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정타입 전부 조회")
    void findALLBeanByType() {
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (Map.Entry<String, MemberRepository> stringMemberRepositoryEntry : beansOfType.entrySet()) {
            System.out.println("stringMemberRepositoryEntry = " + stringMemberRepositoryEntry);
        }
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Configuration
    static class sameBeanTestConfig {
        @Bean
        MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
