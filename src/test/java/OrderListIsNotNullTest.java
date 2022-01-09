import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrderListIsNotNullTest {

    public CourierClient courierClient = new CourierClient();
    int courierId;

    @After
    public void tearDown(){
        courierClient.delete(courierId);
    }

    @Test
    public void orderListIsNotNullTest() {

        Courier courier = Courier.getRandom();
        courierClient.create(courier);
        ValidatableResponse response =  courierClient.login(CourierCredentials.getCourierCredentials(courier));
        courierId = response.extract().path("id");
        ArrayList actual = courierClient.getOrderList(courierId);
        assertNotNull(actual);
        int statusCode = response.extract().statusCode();
        assertEquals("неправильный код ответа", 200, statusCode);
    }
}
