import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class WebShopTests {

    @Test
    @DisplayName("Successfully adding the fist item to a new cart")
    void addFirstItemToCardTest() {
        Response response =
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("product_attribute_72_5_18=53&product_attribute_72_6_19=54&" +
                        "product_attribute_72_3_20=57&addtocart_72.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                .body("updatetopcartsectionhtml", is("(1)"))
                .extract().response();
        System.out.println(response.asString());
        System.out.println(response.path("updatetopcartsectionhtml").toString());
    }

    @Test
    @DisplayName("Successfully adding the items to the cart, with Cookie")
    void addItemsToCardTest() {
        Response response =
                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .body("product_attribute_72_5_18=53&product_attribute_72_6_19=54&" +
                                "product_attribute_72_3_20=57&addtocart_72.EnteredQuantity=1")
                        .cookie("Nop.customer=af338eec-9318-487c-8632-abaa053eb9ee; " +
                                "NopCommerce.RecentlyViewedProducts=RecentlyViewedProductIds=72; " +
                                "__utmz=78382081.1637072055.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); " +
                                "ARRAffinity=7f10010dd6b12d83d6aefe199065b2e8fe0d0850a7df2983b482815225e42439; " +
                                "__utmc=78382081; " +
                                "__utma=78382081.1119391476.1637072055.1637162878.1637165106.5; " +
                                "__atuvc=5%7C46; __atuvs=61952832de76133b001; __utmb=78382081.2.10.1637165106")
                        .when()
                        .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                        .then()
                        .statusCode(200)
                        .body("success", is(true))
                        .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
//                        .body("updatetopcartsectionhtml", is("(1)")) increase +1
                        .extract().response();
        System.out.println(response.asString());
        System.out.println(response.path("updatetopcartsectionhtml").toString());
    }

}
