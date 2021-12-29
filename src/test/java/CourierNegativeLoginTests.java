import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CourierNegativeLoginTests {

    public CourierClient courierClient = new CourierClient();

    @Test
    public void courierNegativeLoginTest(){
        Courier courierWithoutLogin = new Courier(null, "F4G5H6", "123");
        ValidatableResponse response = courierClient.login(CourierCredentials.getCourierCredentials(courierWithoutLogin));

        int statusCode = response.extract().statusCode();
        String errorMessage = response.extract().path("message");

        assertThat("message error","Недостаточно данных для входа", equalTo(errorMessage));
        assertThat("code error",statusCode , equalTo(400));
    }

    @Test
    public void courierNegativePasswordTest(){
        Courier courierWithoutLogin = new Courier("F4G5H6", null, "123");
        ValidatableResponse response = courierClient.login(CourierCredentials.getCourierCredentials(courierWithoutLogin));

        int statusCode = response.extract().statusCode();
        String errorMessage = response.extract().path("message");

        assertThat("message error","Недостаточно данных для входа", equalTo(errorMessage));
        assertThat("code error",statusCode , equalTo(400));
    }
}
