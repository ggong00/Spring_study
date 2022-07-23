package hello.spring.autowired;

import hello.spring.AutoAppConfig;
import hello.spring.discount.DiscountPolicy;
import hello.spring.member.Grade;
import hello.spring.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class AllBeanTest {

    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService1 = ac.getBean(DiscountService.class);
        Member memberA = new Member(1L, "A", Grade.VIP);
        int rateDiscountPolicy = discountService1.join(memberA, 20000, "rateDiscountPolicy");

        Assertions.assertThat(rateDiscountPolicy).isEqualTo(2000);

    }

    private static class DiscountService {

        private final Map<String, DiscountPolicy> policyMap;

        public DiscountService(Map<String, DiscountPolicy> policyMap) {
            this.policyMap = policyMap;
            System.out.println("policyMap = " + policyMap);
        }

        public int join(Member memberA, int i, String rateDiscountPolicy) {
            DiscountPolicy discountPolicy = policyMap.get(rateDiscountPolicy);
            return discountPolicy.discount(memberA, i);
        }
    }
}
