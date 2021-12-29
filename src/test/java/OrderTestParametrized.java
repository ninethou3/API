
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import java.util.List;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.notNullValue;


@RunWith(Parameterized.class)
public class OrderTestParametrized{

        private final Order order;

        public OrderTestParametrized(Order order) {
            this.order = order;
        }

        @Parameterized.Parameters
        public static Object[][] getParameters(){
            return new Object[][]{
                    {Order.getRandomOrder(List.of("BLACK"))},
                    {Order.getRandomOrder(List.of("GREY"))},
                    {Order.getRandomOrder(List.of("GREY", "BLACK"))},
                    {Order.getRandomOrder(List.of())}
            };
        }

    @Test
    public void parametrizedTest(){
        given()
                .baseUri("http://qa-scooter.praktikum-services.ru")
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .log().all()
                .post("/api/v1/orders")
                .then()
                .log().all()
                .statusCode(SC_CREATED)
                .assertThat().body("track", notNullValue());
    }
}
