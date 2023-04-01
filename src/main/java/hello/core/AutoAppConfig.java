package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        //권장
        //프로젝트 시작 루트에 AppConfig 같은 메인설정을 두고
        //그 Config 클래스에 @ComponentScan 에노테이션을 붙이고
        //basePackages 를 생략한다.

        //탐색할 패키지의 시작 위치 지정
        //basePackages = "hello.core.member",

        //지정한 클래스의 패키지를 탐색 시작위치로 지정
        //basePackageClasses = AutoAppConfig.class,

        //Configration 타입을 제외(기존 예제코드 유지를 위해)
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    @Bean(name = "memoryMemberRepository")
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

}
