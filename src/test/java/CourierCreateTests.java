import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class CourierCreateTests extends RestAssuredClient{

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
        assertTrue(okMessage);

        int statusCode = response.extract().statusCode();
        assertEquals("неправильный код ответа", 201, statusCode);

        ValidatableResponse response1 = courierClient.login(CourierCredentials.getCourierCredentials(courier));
        courierId = response1.extract().path("id");
        assertThat("не получен ID объекта", courierId, is(not(0)));
    }

    @Test
    public void duplicateCourierTest() {
        Courier courier = Courier.getRandom();
        courierClient.create(courier);
        ValidatableResponse response = courierClient.login(CourierCredentials.getCourierCredentials(courier));
        courierId = response.extract().path("id");

        ValidatableResponse response1 = courierClient.create(courier);
        int statusCode = response1.extract().statusCode();
        assertThat("code error",statusCode , equalTo(409));
        String errorMessage = response1.extract().path("message");
        assertThat("Этот логин уже используется", equalTo(errorMessage));
    }


}
