import client.UserClient;
import io.qameta.allure.junit4.DisplayName;
import model.UserCreateRandom;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.Registration;

import static org.junit.Assert.assertTrue;
import static pages.Registration.REGISTER_PAGE_PATH;


public class RegistrationTest {
    private WebDriver driver;
    private String name;
    private String email;
    private String password;
    private String shortPassword;
    private String auth;
    private UserClient user;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(REGISTER_PAGE_PATH);
        UserCreateRandom userCreateRandom = new UserCreateRandom(); // Создание рандомного пользоваателя
        name = userCreateRandom.getRandomName();
        email = userCreateRandom.getRandomEmail();
        password = userCreateRandom.getRandomPassword();
        shortPassword = userCreateRandom.getRandomShortPassword();

    }

    @Test
    @DisplayName("Success registration test")
    public void successRegistrationTest() {
        Registration registration = new Registration(driver);
        registration.inputName(name);
        registration.inputEmail(email);
        registration.inputPassword(password);
        registration.clickRegisterButton();
        assertTrue(registration.loginButtonIsDisplayedCheck());
    }

    @Test
    @DisplayName("Validation for short password test")
    public void validationForShortPassword() {
        Registration registration = new Registration(driver);
        registration.inputName(name);
        registration.inputEmail(email);
        registration.inputPassword(shortPassword);
        registration.clickRegisterButton();
        assertTrue(registration.showError());
    }


    @After
    public void deleteUser() {
        if (auth != null) {
            user.deleteUser(auth); // Удаление пользователя
        }
        driver.quit();

    }
}
