package hello.spring.order;

import hello.spring.annotation.MainDiscountAnnotation;
import hello.spring.discount.DiscountPolicy;
import hello.spring.member.MemberRepository;
import org.springframework.stereotype.Component;


@Component
public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountAnnotation DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        int discount = discountPolicy.discount(memberRepository.findById(memberId),itemPrice);
        Order order = new Order(memberId,itemName,itemPrice,discount);
        return order;
    }

}
