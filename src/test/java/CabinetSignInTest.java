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


public class Cabinet {
    private WebDriver driver;
    private String accessToken;
    private String name;
    private String email;
    private String password;
    private UserClient user;
    private UserCreateRandomApi userCreateRandomApi;
    private String auth;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize(); //вызов метода для открытия окна браузера во весь экран
        driver.get("https://stellarburgers.nomoreparties.site/"); //открываем сайт
        user = new UserClient();
        userCreateRandomApi = UserCreateRandomApi.getRandomUser();
        ValidatableResponse response = user.createUser(userCreateRandomApi);
        auth = response.extract().path("accessToken");

    }

    @Test
    @DisplayName("Sign in for Enter button on main page")
    public void enterOnMainPage() {
        Registration registration = new Registration(driver);
        registration.loginButtonOnMainPageClick();
        registration.inputEmail(userCreateRandomApi.getEmail());
        registration.inputPassword(userCreateRandomApi.getPassword());
        registration.loginButtonOnEnterPageClick();
        registration.userAccountButtonClick();
        Assert.assertTrue(registration.isLogoutButtonVisible(driver));
    }

    @Test
    @DisplayName("Sign in for private cabinet button")
    public void enterOnPrivateCabinet() {
        Registration registration = new Registration(driver);
        registration.userAccountButtonClick();
        registration.inputEmail(userCreateRandomApi.getEmail());
        registration.inputPassword(userCreateRandomApi.getPassword());
        registration.loginButtonOnEnterPageClick();
        registration.userAccountButtonClick();
        Assert.assertTrue(registration.isLogoutButtonVisible(driver));
    }

    @Test
    @DisplayName("SignIn for registration page")
    public void enterOnRegistrationPage() {
        Registration registration = new Registration(driver);
        registration.userAccountButtonClick();
        registration.clickSighInRegistrationButton(driver);
        registration.clickSignInButton(driver);
        registration.inputEmail(userCreateRandomApi.getEmail());
        registration.inputPassword(userCreateRandomApi.getPassword());
        registration.loginButtonOnEnterPageClick();
        registration.userAccountButtonClick();
        Assert.assertTrue(registration.isLogoutButtonVisible(driver));

    }

    @Test
    @DisplayName("SignIn for forgot password page")
    public void enterOnRemakePasswordPage() {
        Registration registration = new Registration(driver);
        registration.userAccountButtonClick();
        registration.forgotPasswordButtonClick();
        registration.clickSignInButton(driver);
        registration.inputEmail(userCreateRandomApi.getEmail());
        registration.inputPassword(userCreateRandomApi.getPassword());
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
    public void goToConstructor() {
        Registration registration = new Registration(driver);
        registration.userAccountButtonClick();
        registration.forgotPasswordButtonClick();
        registration.clickSignInButton(driver);
        registration.inputEmail(userCreateRandomApi.getEmail());
        registration.inputPassword(userCreateRandomApi.getPassword());
        registration.loginButtonOnEnterPageClick();
        Header header = new Header(driver);
        header.checkConstructorMenuIsVisible();
    }

    @After
    public void deleteUser() {
        if (auth != null) {
            user.deleteUser(auth);
        }
        driver.quit();

    }


}
