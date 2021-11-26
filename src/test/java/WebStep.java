import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;

public class WebStep {
    private final static String WEB_RESOURCE = "http://demowebshop.tricentis.com/";
    private final static String LOGIN_COOKIE_NAME = "NOPCOMMERCE.AUTH";
    private final static String LOGIN_CONTENT_TYPE = "application/x-www-form-urlencoded; charset=UTF-8";

    @Step("Open the main page")
    public void openMainPage() {
        open(WEB_RESOURCE);
    }

    @Step("Open the most minimal page ")
    public void openMostMinimalPage(String minimal) {
        open(WEB_RESOURCE + minimal);
    }
    @Step("Feed the browser with cookie")
    public void feedBrowserCookie(String loginCookie) {
        getWebDriver()
                .manage()
                .addCookie(new Cookie(LOGIN_COOKIE_NAME, loginCookie));
    }

    @Step("Log in for getting cookie")
    public String logInForGetCookie(String email, String password) {
        return
                given()
                    .contentType(LOGIN_CONTENT_TYPE)
                    .formParam("Email", email)
                    .formParam("Password", password)
                .when()
                    .post(WEB_RESOURCE + "login/")
                .then()
                    .statusCode(302)
                    .extract().cookie(LOGIN_COOKIE_NAME);
    }

    @Step("Check that customer has logged")
    public void checkLoggedCustomerEmail(String email) {
        $(".account").shouldHave(text(email));
    }
}
