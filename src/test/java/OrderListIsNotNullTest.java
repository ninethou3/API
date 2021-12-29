import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

public class OrderListIsNotNullTest {

    public CourierClient courierClient = new CourierClient();

    @Test
    public void orderListIsNotNullTest() {

        Courier courier = Courier.getRandom();
        courierClient.create(courier);
        ValidatableResponse response =  courierClient.login(CourierCredentials.getCourierCredentials(courier));
        int courierId = response.extract().path("id");
        ArrayList actual = courierClient.getOrderList(courierId);
        assertNotNull(actual);
    }
}
