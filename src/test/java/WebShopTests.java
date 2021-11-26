import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;

public class WebShopTests {
    @Test
    @DisplayName("Successful authorization")
    void successfulLoginTest() {
        String loginCookie =
                given()
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("Email", "sigwist@qa.com")
                        .formParam("Password", "#Pojo9")
                        .when()
                        .post("http://demowebshop.tricentis.com/login/")
                        .then()
                        .statusCode(302)
                        .extract().cookie("NOPCOMMERCE.AUTH");
        open("http://demowebshop.tricentis.com/Themes/DefaultClean/Content/images/logo.png");
        getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", loginCookie));
        open("http://demowebshop.tricentis.com");
        $(".account").shouldHave(text("sigwist@qa.com"));
    }
}
