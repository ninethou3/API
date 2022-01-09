import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)

public class CourierCreateParametrizedTest {
    private final Courier courier;
    private final int expectedStatus;
    private final String expectedMessage;

    public CourierCreateParametrizedTest(Courier courier, int expectedStatus, String expectedMessage) {
        this.courier = courier;
        this.expectedStatus = expectedStatus;
        this.expectedMessage = expectedMessage;
    }
    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {Courier.getWithOnlyLogin(), 400, "Недостаточно данных для создания учетной записи"},
                {Courier.getWithOnlyPassword(), 400, "Недостаточно данных для создания учетной записи"},
                {Courier.getWithOnlyName(), 400, "Недостаточно данных для создания учетной записи"},
                {Courier.getWithOnlyLoginPass(), 400, "Недостаточно данных для создания учетной записи"},
                {Courier.getWithOnlyPassName(), 400, "Недостаточно данных для создания учетной записи"},
                {Courier.getWithOnlyNameLogin(), 400, "Недостаточно данных для создания учетной записи"}
        };
    }
    @Test
    public void courierNotCreatedTests() {

        ValidatableResponse response = new CourierClient().create(courier);

        int statusCode = response.extract().statusCode();
        assertThat("Ошибка при создании курьера", statusCode, equalTo(expectedStatus));

        String message = response.extract().path("message");
        assertThat("Ошибка при создании курьера", message, equalTo(expectedMessage));
    }
}
