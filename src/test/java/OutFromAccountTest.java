import client.UserClient;
import io.qameta.allure.junit4.DisplayName;
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
    private WebDriver driver;
    private UserClient user;
    private UserCreateRandomApi userCreateRandomApi;
    private String auth;


    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BURGER_ASSEMBLING_PAGE);
        user = new UserClient();
        userCreateRandomApi = UserCreateRandomApi.getRandomUser(); // Создание рандомного пользователя через API
        ValidatableResponse response = user.createUser(userCreateRandomApi);
        auth = response.extract().path("accessToken");

    }

    @Test
    @DisplayName("Logout test")
    public void logoutTest() {
        Registration registration = new Registration(driver);
        registration.loginButtonOnMainPageClick();
        registration.inputEmail(userCreateRandomApi.getEmail());
        registration.inputPassword(userCreateRandomApi.getPassword());
        registration.loginButtonOnEnterPageClick();
        registration.userAccountButtonClick();
        registration.logoutUser();
        assertTrue(registration.loginButtonIsDisplayedCheck());
    }

    @After
    public void deleteUser() {
        if (auth != null) {
            user.deleteUser(auth); // Удаление пользователя
        }
        driver.quit();

    }
}
