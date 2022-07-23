package hello.spring;

import hello.spring.discount.DiscountPolicy;
import hello.spring.discount.RateDiscountPolicy;
import hello.spring.member.MemberRepository;
import hello.spring.member.MemberService;
import hello.spring.member.MemberServiceImpl;
import hello.spring.member.MemoryMemberRepository;
import hello.spring.order.OrderService;
import hello.spring.order.OrderServiceImpl;

public class AppConfig2 {


    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }


    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }


    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }


    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
