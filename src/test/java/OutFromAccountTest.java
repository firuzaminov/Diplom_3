import client.UserClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import model.UserCreateRandomApi;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.Registration;

import static org.junit.Assert.assertTrue;
import static pages.BurgerAssembling.BURGER_ASSEMBLING_PAGE;

public class OutFromAccountTest {
    private final String URL_API = "https://stellarburgers.nomoreparties.site";
    private WebDriver driver;
    private UserClient user;
    private UserCreateRandomApi userCreateRandomApi;
    private String auth;
    private String email;
    private String password;


    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BURGER_ASSEMBLING_PAGE);
        user = new UserClient();
        userCreateRandomApi = UserCreateRandomApi.getRandomUser(); // Создание рандомного пользователя через API
        email = userCreateRandomApi.getEmail();
        password = userCreateRandomApi.getPassword();
        ValidatableResponse response = user.createUser(userCreateRandomApi);
        auth = response.extract().path("accessToken");

    }

    @Test
    @DisplayName("Logout test")
    public void logoutTest() {
        Registration registration = new Registration(driver);
        registration.loginButtonOnMainPageClick();
        registration.inputEmail(email);
        registration.inputPassword(password);
        registration.loginButtonOnEnterPageClick();
        registration.userAccountButtonClick();
        registration.logoutUser();
        assertTrue(registration.loginButtonIsDisplayedCheck());
    }

    @After
    public void deleteUser() {
        if (auth != null) {
            user.deleteUser(auth); // Удалние пользователя
        }
        driver.quit();

    }
}
