package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
@Test
    void createOrder(){
    Member member = new Member(1L, "Member1", Grade.VIP);
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    memberRepository.save(member);
    OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
    orderService.createOrder(1L, "itemA", 10000);
}
}