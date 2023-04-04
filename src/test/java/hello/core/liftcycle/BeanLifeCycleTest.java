package hello.core.liftcycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        //close 는 ConfigurableApplicationContext 안에 있음
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig{
        //@Bean(initMethod = "init", destroyMethod = "close")
        //destroyMethod는 close, shotdown 메소드를 자동으로 추론하여 호출한다(기본값이 (inferred)로 되어있어서 가능하다)
        @Bean
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://naver.com");
            return networkClient;
        }
    }
}
