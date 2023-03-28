package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입 조회 시 같은 타입이 둘 이상 있으면 오류 발생")
    void findBeanByTypeDuplicate()
    {
        //MemberRepository memberRepository = ac.getBean(MemberRepository.class);
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                ()-> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입 조회 시 같은 타입이 둘 이상 있으면 오류 발생, 빈 이름을 지정하면 됨")
    void findBeanByType()
    {
        MemberRepository memberRepository = ac.getBean("memberRepository1" ,MemberRepository.class);
        org.assertj.core.api.Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입 모드 조회하기")
    void findAllBeanByType()
    {
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);

        for(String Name : beansOfType.keySet())
        {
            System.out.println("key = " + Name + "/ value = " + beansOfType.get(Name));
        }
        System.out.println("beanType = " + beansOfType);
        org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);
    }

    static class SameBeanConfig
    {
        @Bean
        public MemberRepository memberRepository1()
        {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2()
        {
            return new MemoryMemberRepository();
        }
    }
}
