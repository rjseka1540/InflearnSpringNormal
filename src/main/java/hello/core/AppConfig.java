package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Java -> Spring
@Configuration
//@Configuration 을 주석처리 시 스프링컨테이너에서 관리하는 모든 싱글톤 기능이 없어진다.
public class AppConfig {

    //질문.
    //MemberService 호출 -> MemoryMemberRepository 리턴
    //OrderService 호출 -> MemoryMemberRepository 리턴
    //위 두개 호출 되는데 싱글톤 깨지는거 아닌가.
    //답 : 아니다. 다 같은 인스턴스가 리턴된다.
    //이유는 @Configuration 에서 AppConfig 가 Spring Bean 에 등록 될때
    //AppConfig@CGLIB가 AppConfig 의 자식이되며 클래스들을 호출 할 시 AppConfig@CGLIB에서 반환한다.
    //그리고 이미 호출된 클래스면 spring container 에서 찾고 아니면 등록하는 식으로 동작한다.

    //Spring Container 에 등록
    @Bean
    public MemberService memberService(){
        //생성자 주입
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy()
    {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
