package sample;


import static io.restassured.RestAssured.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignUpApiTests {

  @LocalServerPort private Integer port;

  @Test public void signUp() {

    // @formatter:off
    given()
        .port(port)
   .when()
        .contentType(ContentType.JSON)
        .body("{\"login\":\"user\",\"password\":\"pwd\",\"phone\":\"123\"}")
        .post("/signup")
   .then()
      .statusCode(200);
    // @formatter:on

  }

}
