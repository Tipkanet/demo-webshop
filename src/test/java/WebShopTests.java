import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

public class WebShopTests {
    @Test
    @DisplayName("Successful authorization: should return status code 302")
    void successfulLoginTest() {
        Response response =
                given()
                        .contentType("application/x-www-form-urlencoded")
                        .body("Email=qa%40qaqa.com&Password=123456&RememberMe=false")
                        .cookie("Nop.customer=4982d5ec-b850-4aef-ba2e-73abbeeb18e5")
                        .when()
                        .post("http://demowebshop.tricentis.com/login/")
                        .then()
                        .statusCode(302)
                        .extract().response();
        assertThat(response.contentType()).isEqualTo("text/html; charset=utf-8");
    }

    @Test
    @DisplayName("Successful authorization: should return status code 302")
    void successfulLoginWithNonEmptyCartTest() {
        Response response =
                given()
                        .contentType("application/x-www-form-urlencoded")
                        .body("Email=qa%40qaqa.com&Password=123456&RememberMe=false")
                        .cookie("Nop.customer=4982d5ec-b850-4aef-ba2e-73abbeeb18e5")
                        .when()
                        .post("http://demowebshop.tricentis.com/login/")
                        .then()
                        .statusCode(302)
                        .extract().response();
        assertThat(response.contentType()).isEqualTo("text/html; charset=utf-8");
    }
}
