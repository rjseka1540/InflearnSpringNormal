package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(testConfig.class);


    @Test
    @DisplayName("부모 타입으로 조회시 자식이 둘이상 있으면 중복오류 발생")
    void findBeanByParentTypeDuplicate()
    {
        //DiscountPolicy bean = ac.getBean(DiscountPolicy.class);

        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                ()-> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시 자식이 둘이상 있으면 중복오류 발생, 빈 이름을 지정하면 됨")
    void findBeanByParentTypeBeanName()
    {
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy",DiscountPolicy.class);

        Assertions.assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 조회시 자식이 둘이상 있으면 중복오류 발생, 특정 하위 타입으로 조회하면 됨")
    void findBeanBySubType()
    {
        RateDiscountPolicy rateDiscountPolicy = ac.getBean(RateDiscountPolicy.class);

        Assertions.assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회")
    void findAllBeanByParentType()
    {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);

        Assertions.assertThat(beansOfType.size()).isEqualTo(2);
        for(String key : beansOfType.keySet())
        {
            System.out.println("Key = " + key + "/ value = " + beansOfType.get(key));
        }
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회 -> Object")
    void findAllBeanByObject()
    {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for(String key : beansOfType.keySet())
        {
            System.out.println("Key = " + key + "/ value = " + beansOfType.get(key));
        }
    }
    
    static class testConfig
    {
        @Bean
        public DiscountPolicy rateDiscountPolicy()
        {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy()
        {
            return new FixDiscountPolicy();
        }
    }
}
