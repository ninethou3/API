import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

public class Courier {
    public String login;
    public String password;
    public String firstName;

    public Courier(){
    }

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


    public Courier setLogin(String login) {
        this.login = login;
        return this;
    }

    @Step("создание курьера только с логином")
    public static Courier getWithOnlyLogin() {
        return new Courier().setLogin(RandomStringUtils.randomAlphabetic(10));
    }

    public Courier setPassword(String password) {
        this.password = password;
        return this;
    }
    @Step("создание курьера только с паролем")
    public static Courier getWithOnlyPassword() {
        return new Courier().setPassword(RandomStringUtils.randomAlphabetic(10)); }

    public Courier setName(String firstName) {
        this.firstName = firstName;
        return this;
    }
    @Step("создание курьера только с именем")
    public static Courier getWithOnlyName() {
        return new Courier().setName(RandomStringUtils.randomAlphabetic(10));
    }

    public Courier setLoginPass(String login, String password) {
        this.login = login;
        this.password = password;
        return this;
    }
    @Step("создание курьера только с логином и паролем")
    public static Courier getWithOnlyLoginPass() {
        return new Courier().setLoginPass(RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));
    }

    public Courier setPassName(String password, String firstName) {
        this.password = password;
        this.firstName = firstName;
        return this;
    }

    @Step("создание курьера только с паролем и именем")
    public static Courier getWithOnlyPassName() {
        return new Courier().setPassName(RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));
    }

    public Courier setNameLogin(String firstName, String login) {
        this.firstName = firstName;
        this.login = login;
        return this;
    }
    @Step("создание курьера только с именем и логином")
    public static Courier getWithOnlyNameLogin() {
        return new Courier().setNameLogin(RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));
    }
}
