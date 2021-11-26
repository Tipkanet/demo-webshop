import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WebShopTests {

    static WebStep steps = new WebStep();

    @Test
    @DisplayName("Successful log in")
    void successfulLoginTest() {

        String loginCookie =
                steps.logInForGetCookie("sigwist@qa.com", "#Pojo9");
        steps.openMostMinimalPage("Themes/DefaultClean/Content/images/logo.png");
        steps.feedBrowserCookie(loginCookie);
        steps.openMainPage();
        steps.checkLoggedCustomerEmail("sigwist@qa.com");
    }
}
