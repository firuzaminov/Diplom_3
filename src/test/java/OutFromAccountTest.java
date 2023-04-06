import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.Registration;

import static pages.Registration.REGISTER_PAGE_PATH;

public class OutFromAccountTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize(); //вызов метода для открытия окна браузера во весь экран
        driver.get("https://stellarburgers.nomoreparties.site/"); //открываем сайт
    }
    @Test
    @DisplayName("Logout user test")
    public void logoutUserTest() {
        Registration registration = new Registration(driver);
        registration.loginButtonOnMainPageClick();
        registration.inputEmail("qwe@qwe.com");
        registration.inputPassword("123456");
        //loginPage.loginUser("arxipkin@gmail.com", "1234567");
        registration.loginButtonOnMainPageClick_2();
        //UserCabinetPage userCabinet = new UserCabinetPage(driver);
        registration.userAccountButtonClick();
        registration.logoutUser();
        registration.loginButtonIsDisplayedCheck();
    }
}
