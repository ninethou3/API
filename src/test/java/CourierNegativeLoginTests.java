import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CourierNegativeLoginTests {

    public CourierClient courierClient;
    private Courier courier;

    @Before
    public void setUp() {
        courier = Courier.getRandom();
        courierClient = new CourierClient();
    }

    @Test
    public void courierNullLoginTest() {
        Courier courierWithoutLogin = new Courier(null, "F4G5H6", "123");
        ValidatableResponse response = courierClient.login(CourierCredentials.getCourierCredentials(courierWithoutLogin));

        int statusCode = response.extract().statusCode();
        assertThat("ошибка кода", statusCode, equalTo(400));
        String errorMessage = response.extract().path("message");
        assertThat("ошибка в месседже", "Недостаточно данных для входа", equalTo(errorMessage));
    }

    @Test
    public void courierNullPasswordTest() {
        Courier courierWithoutLogin = new Courier("F4G5H6", null, "123");
        ValidatableResponse response = courierClient.login(CourierCredentials.getCourierCredentials(courierWithoutLogin));

        int statusCode = response.extract().statusCode();
        assertThat("ошибка кода", statusCode, equalTo(400));
        String errorMessage = response.extract().path("message");
        assertThat("ошибка в месседже", "Недостаточно данных для входа", equalTo(errorMessage));
    }

    @Test
    public void negativeLoginTest() {
        courierClient.create(courier);
        ValidatableResponse response = courierClient.login
                (new CourierCredentials(courier.login + "0", courier.password));
        int statusCode = response.extract().statusCode();
        assertThat("ошибка кода", statusCode, equalTo(404));
        String errorMessage = response.extract().path("message");
        assertThat("ошибка в месседже", errorMessage, equalTo("Учетная запись не найдена"));
    }

    @Test
    public void negativePasswordTest() {
        courierClient.create(courier);
        ValidatableResponse response = courierClient.login
                (new CourierCredentials(courier.login, courier.password + "0"));
        int statusCode = response.extract().statusCode();
        assertThat("ошибка кода", statusCode, equalTo(404));
        String errorMessage = response.extract().path("message");
        assertThat("ошибка в месседже", errorMessage, equalTo("Учетная запись не найдена"));
    }
}
