import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class CourierNegativeCreateTests extends RestAssuredClient{

    public CourierClient courierClient = new CourierClient();
    Courier courier = Courier.getRandom();

    @Test
    public void duplicateCourierTest() {

        courierClient.create(courier);
        ValidatableResponse response = courierClient.create(courier);

        int statusCode = response.extract().statusCode();
        String errorMessage = response.extract().path("message");

        assertThat("Этот логин уже используется", equalTo(errorMessage));
        assertThat("code error",statusCode , equalTo(409));
    }

    @Test
    public void courierNegativeCreateTest(){
        Courier withoutLogin = new Courier("", "F4G5H6", "CName");
        ValidatableResponse response = courierClient.create(withoutLogin);

        int statusCode = response.extract().statusCode();
        String errorMessage = response.extract().path("message");

        assertThat("Недостаточно данных для создания учетной записи", equalTo(errorMessage));
        assertThat("code error", statusCode , equalTo(400));
    }
}
