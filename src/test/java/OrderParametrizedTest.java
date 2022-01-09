
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import java.util.List;
import static org.junit.Assert.assertNotNull;


@RunWith(Parameterized.class)
public class OrderParametrizedTest extends RestAssuredClient{

    private final Order order;
    private int trackNumber;

    @After
    public void tearDown(){CourierClient.cancel(trackNumber);}


    public OrderParametrizedTest(Order order) {
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
    public void ordersCreatedTest() {
    CourierClient orderClient = new CourierClient();
    ValidatableResponse response = orderClient.createOrder(order);
    trackNumber = response.extract().path("track");

    assertNotNull(trackNumber);
    }
}
