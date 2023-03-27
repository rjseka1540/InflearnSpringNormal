package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {


        //MemberService memberService = new MemberServiceImpl();
        //OrderService orderService = new OrderServiceImpl(memberRepository, discountPolicy);

        //Java로 직접 DI 적용
        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();
        //OrderService orderService = appConfig.orderService();

        //Spring으로 DI 적용
        //Spring Container == ApplicationContext
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "MemberA", Grade.VIP);

        memberService.join(member);

        Order order = orderService.createOrder(member.getId(), "itemA", 1500000);

        System.out.println("Order : " + order);
        System.out.println("Price : " + order.calculatePrice());
    }
}
