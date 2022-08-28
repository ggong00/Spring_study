package hello.spring.discount;

import hello.spring.member.Grade;
import hello.spring.member.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("vip 고객은 10퍼 할인되어야 한다.")
    void vip_o() {
        Long memberId = 1L;
        Member member = new Member(memberId,"memberA", Grade.VIP);
        int discount = rateDiscountPolicy.discount(member, 10000);

        Assertions.assertEquals(discount,1000);
    }

    @Test
    @DisplayName("vip 고객이 아니면 할인이 되어서는 안된다.")
    void vip_x() {
        Long memberId = 2L;
        Member member = new Member(memberId,"memberB", Grade.BASIC);
        int discount = rateDiscountPolicy.discount(member, 10000);

        Assertions.assertEquals(discount,0);
    }


}