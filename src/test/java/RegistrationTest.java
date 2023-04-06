import io.qameta.allure.junit4.DisplayName;
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

   @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize(); //вызов метода для открытия окна браузера во весь экран
        driver.get(REGISTER_PAGE_PATH); //открываем сайт
    }

    @Test
    @DisplayName("Success registration test")
    public void successRegistrationTest(){
        Registration registration = new Registration(driver);
        registration.inputName("Вася");
        registration.inputEmail("zxc@zxc.com");
        registration.inputPassword("123456");
        registration.clickRegisterButton();
        assertTrue(registration.loginButtonIsDisplayedCheck());
    }

    @Test
    @DisplayName("Validation for short password")
    public void validationForShortPassword(){
        Registration registration = new Registration(driver);
        registration.inputName("Вася");
        registration.inputEmail("zxc@zxc.com");
        registration.inputPassword("123");
        registration.clickRegisterButton();
        assertTrue(registration.showError());
    }





    @After
    public void closeWebSite() {
        driver.quit();
    }
}
