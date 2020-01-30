package modules;

public class Tweets {
    public static  String screen_name;

    public Tweets(){

    }
    public Tweets (String screen_name){

    }

    @Override
    public String toString() {
        return "Tweets{" +
                "screen_name='" + screen_name + '\'' +
                '}';
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }
}
