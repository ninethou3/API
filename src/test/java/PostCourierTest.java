import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class PostCourierTest extends RestAssuredClient{

    public CourierClient courierClient;
    public int courierId;

    @Before
    public void setup(){
        courierClient = new CourierClient();
    }

    @After
    public void tearDown(){
        courierClient.delete(courierId);
    }

    @Test
    public void createCourier() {
        Courier courier = Courier.getRandom();
        ValidatableResponse response = courierClient.create(courier);

        boolean okMessage = response.extract().path("ok");
        assertEquals(okMessage, true);

        int statusCode = response.extract().statusCode();
        assertEquals("неправильный код ответа", 201, statusCode);

        ValidatableResponse response1 = courierClient.login(CourierCredentials.getCourierCredentials(courier));
        courierId = response1.extract().path("id");
        assertThat("не получен ID объекта", courierId, is(not(0)));
    }
}
