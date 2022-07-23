package hello.spring.discount;

import hello.spring.member.Grade;
import hello.spring.member.Member;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000; //1000원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return 1000;
        } else {
            return 0;
        }
    }
}
