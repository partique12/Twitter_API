import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import modules.Tweets;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertTrue;

public class APITests {
    @Test
    public void GetExistingUserTweetsAndResponseCode200(){

         String ConsumerKey = "XGAxTF3OfffKiEmX512t10mgt";
         String ConsumerSecretKey = "QjuNhIqZgWAO2THrJsLkRC8DRU9RAIpqYKViFdpgcClitAR4MX";
         String AccessToken = "1001887471-MqKJUPkvFQgHc08SsKuSbYD7zNbTW1a3SzLUcRZ";
         String AccessTokenSecret = "YwCQ1UBcZB8zSWFiAZs0tNBpkiW38ndRGofNIDVIFz3Vk";

        RestAssured.baseURI="https://api.twitter.com";
        Response res = given().
                auth().
                oauth(ConsumerKey, ConsumerSecretKey, AccessToken, AccessTokenSecret).
                param("count",3).log().all().

                when().
                get("/1.1/search/tweets.json?q=ZelenskyyUa").
                then().
                assertThat().statusCode(200).
                extract().response();
        System.out.println(res);
    }

    @Test
    public void Verify401IfAccessTokenSecretInvalid(){

        String ConsumerKey = "XGAxTF3OfffKiEmX512t10mgt";
        String ConsumerSecretKey = "QjuNhIqZgWAO2THrJsLkRC8DRU9RAIpqYKViFdpgcClitAR4MX";
        String AccessToken = "1001887471-MqKJUPkvFQgHc08SsKuSbYD7zNbTW1a3SzLUcRZ";
        String AccessTokenSecret = "test_invalid_token";

        RestAssured.baseURI="https://api.twitter.com";

        Response res = given().
                auth().
                oauth(ConsumerKey, ConsumerSecretKey, AccessToken, AccessTokenSecret).
                param("count",3).log().all().

                when().
                get("/1.1/search/tweets.json?q=ZelenskyyUa").
                then().
                assertThat().statusCode(401).
                extract().response();

    }

    @Test
    public void Verify404IfBadUri(){

        String ConsumerKey = "XGAxTF3OfffKiEmX512t10mgt";
        String ConsumerSecretKey = "QjuNhIqZgWAO2THrJsLkRC8DRU9RAIpqYKViFdpgcClitAR4MX";
        String AccessToken = "1001887471-MqKJUPkvFQgHc08SsKuSbYD7zNbTW1a3SzLUcRZ";
        String AccessTokenSecret = "test_invalid_token";

        RestAssured.baseURI="https://api.twitter.com/";
        Response res = given().
                auth().
                oauth(ConsumerKey, ConsumerSecretKey, AccessToken, AccessTokenSecret).
                param("count",3).log().all().

                when().
                get("/1.1/test/users/search").
                then().
                assertThat().statusCode(404).
                extract().response();

    }
}
