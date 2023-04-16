import client.UserClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.UserCreateRandomApi;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.Header;
import pages.Registration;

import static pages.BurgerAssembling.BURGER_ASSEMBLING_PAGE;


public class CabinetSignInTest {
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
    @DisplayName("Sign in for Enter button on main page")
    public void enterOnMainPageTest() {
        Registration registration = new Registration(driver);
        registration.loginButtonOnMainPageClick();
        registration.inputEmail(email);
        registration.inputPassword(password);
        registration.loginButtonOnEnterPageClick();
        registration.userAccountButtonClick();
        Assert.assertTrue(registration.isLogoutButtonVisible(driver));
    }

    @Test
    @DisplayName("Sign in for private cabinet button")
    public void enterOnPrivateCabinetTest() {
        Registration registration = new Registration(driver);
        registration.userAccountButtonClick();
        registration.inputEmail(email);
        registration.inputPassword(password);
        registration.loginButtonOnEnterPageClick();
        registration.userAccountButtonClick();
        Assert.assertTrue(registration.isLogoutButtonVisible(driver));
    }

    @Test
    @DisplayName("SignIn for registration page")
    public void enterOnRegistrationPageTest() {
        Registration registration = new Registration(driver);
        registration.userAccountButtonClick();
        registration.clickSighInRegistrationButton(driver);
        registration.clickSignInButton(driver);
        registration.inputEmail(email);
        registration.inputPassword(password);
        registration.loginButtonOnEnterPageClick();
        registration.userAccountButtonClick();
        Assert.assertTrue(registration.isLogoutButtonVisible(driver));

    }

    @Test
    @DisplayName("SignIn for forgot password page")
    public void enterOnRestorePasswordPageTest() {
        Registration registration = new Registration(driver);
        registration.userAccountButtonClick();
        registration.forgotPasswordButtonClick();
        registration.clickSignInButton(driver);
        registration.inputEmail(email);
        registration.inputPassword(password);
        registration.loginButtonOnEnterPageClick();
        registration.userAccountButtonClick();
        Assert.assertTrue(registration.isLogoutButtonVisible(driver));

    }

    @Test
    public void privateCabinClickTest() {
        Registration registration = new Registration(driver);
        registration.userAccountButtonClick();
        registration.loginButtonIsDisplayedCheck();
    }

    @Test
    public void goToConstructorTest() {
        Registration registration = new Registration(driver);
        registration.userAccountButtonClick();
        registration.forgotPasswordButtonClick();
        registration.clickSignInButton(driver);
        registration.inputEmail(email);
        registration.inputPassword(password);
        registration.loginButtonOnEnterPageClick();
        Header header = new Header(driver);
        header.checkConstructorMenuIsVisible();
    }

    @After
    public void deleteUser() {
        if (auth != null) {
            user.deleteUser(auth); // Удалние пользователя
        }
        driver.quit();

    }


}
