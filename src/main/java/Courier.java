import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

public class Courier {
    public final String login;
    public final String password;
    public final String firstName;

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    @Step("создание рандомного курьера")
    public static Courier getRandom(){
        final String login = RandomStringUtils.randomAlphabetic(5);
        final String password = RandomStringUtils.randomNumeric(5);
        final String firstName = RandomStringUtils.randomAlphabetic(5);
        return new Courier(login, password, firstName);
    }


}
