package hello.core.liftcycle;

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
}
