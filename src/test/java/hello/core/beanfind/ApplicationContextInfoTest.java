package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("Print All Bean")
    void findAllBean()
    {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String Name : beanDefinitionNames)
        {
            Object bean = ac.getBean(Name);
            System.out.println("bean = " + Name + " / Object : " + bean);
        }
    }

    @Test
    @DisplayName("Print Application Bean")
    void findApplicationBean()
    {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String Name : beanDefinitionNames)
        {
            BeanDefinition beanDefinition = ac.getBeanDefinition(Name);

            //bean의 Role 검색
            //Role ROLE_APPLICATION : 직접 등록한 어플리케이션 빈
            //Role ROLE_INFRASTRUCTURE : 스프링의 내부에서 사용하는 빈
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION)
            {
                Object bean = ac.getBean(Name);
                System.out.println("bean = " + Name + " / Object : " + bean);
            }
        }
    }
}
