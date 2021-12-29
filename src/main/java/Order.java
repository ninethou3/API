import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;

public class Order {
    public final String firstName;
    public final String lastName;
    public final String address;
    public final String metroStation;
    public final String phone;
    public final String rentTime;
    public final String deliveryDate;
    public final String comment;
    public final List<String> color;

    public Order(String firstName, String lastName, String address, String metroStation, String phone,
                 String rentTime, String deliveryDate, String comment, List<String> color) {
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
        this.firstName = firstName;
    }

    @Step("метод генерирующий рандомный заказ")
    public static Order getRandomOrder(List<String> color){
        final String lastName = RandomStringUtils.randomAlphabetic(5);
        final String address = RandomStringUtils.randomNumeric(5);
        final String metroStation = RandomStringUtils.randomAlphabetic(5);
        final String phone = RandomStringUtils.randomAlphabetic(5);
        final String rentTime = RandomStringUtils.randomNumeric(1);
        final String deliveryDate = "2020-06-06";
        final String comment = RandomStringUtils.randomAlphabetic(5);
        final String firstName = RandomStringUtils.randomAlphabetic(5);

        return new Order(lastName, firstName, address, metroStation, phone, rentTime, deliveryDate,
                comment, color);
    }


}


