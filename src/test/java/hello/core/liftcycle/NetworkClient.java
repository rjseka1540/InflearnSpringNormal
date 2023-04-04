package hello.core.liftcycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//InitializingBean : DI 끝나면 호출하는 메소드 사용
//DisposableBean : 빈종료될 시 호출하는 메소드 사용
// implements InitializingBean, DisposableBean spring 초창기에 나와서 지금 사용 X
public class NetworkClient {

    private String Url;

    public NetworkClient(){
        System.out.println("Url = " + Url);
        connect();
        call("초기화 연결 메세지");
    }

    public void setUrl(String url) {
        Url = url;
    }

    public void connect()
    {
        System.out.println("connect = " + Url);
    }

    public void call(String message){
        System.out.println("call = " + Url +"message = " + message);
    }

    public void disconnect(){
        System.out.println("close = " + Url);
    }

    //의존관계호출이 끝나면 함수 호출하겠다.
    @PostConstruct //최신 Spring에서 권장
    public void init(){
        connect();
        call("초기화 연결 메세지");
    }
    @PreDestroy //최신 Spring에서 권장
    public void close(){
        disconnect();
    }
}
