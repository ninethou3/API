import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;


import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.util.ArrayList;

public class CourierClient extends RestAssuredClient{

    private static final String COURIER_PATH = "/api/v1/courier";

    @Step("создание курьера с кодом 200")
    public ValidatableResponse create(Courier courier){
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then()
                .log().all()
                .assertThat();

    }
    @Step("логин метод с кодом 200")
    public ValidatableResponse login (CourierCredentials courierCredentials) {
        return given()
                .spec(getBaseSpec())
                .body(courierCredentials)
                .when()
                .post(COURIER_PATH + "/login")
                .then()
                .log().all()
                .assertThat();
    }
    @Step("метод удаления курьера")
    public boolean delete(int courierId){
        return given()
                .spec(getBaseSpec())
                .when()
                .delete(COURIER_PATH+ "/" + courierId)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("ok");
    }

    @Step("метод проверки получения списка заказов")
    public ArrayList getOrderList(int ID){
        return given()
                .spec(getBaseSpec())
                .when()
                .get("api/v1/orders?courierId=" + ID)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .and().extract()
                .path("orders");
    }
}
