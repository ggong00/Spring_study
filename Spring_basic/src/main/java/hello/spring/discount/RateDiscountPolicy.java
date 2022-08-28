package hello.spring.discount;

import hello.spring.annotation.MainDiscountAnnotation;
import hello.spring.member.Grade;
import hello.spring.member.Member;
import org.springframework.stereotype.Component;

@Component
@MainDiscountAnnotation
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
