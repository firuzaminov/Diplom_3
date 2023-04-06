import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BurgerAssembling;
import pages.Header;
import pages.Registration;

public class Cabinet {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize(); //вызов метода для открытия окна браузера во весь экран
        driver.get("https://stellarburgers.nomoreparties.site/"); //открываем сайт
    }
    @Test
    @DisplayName("Go to user cabinet by user cabinet button test")
    public void goToUserCabinetByUserCabinetButtonTest() {
        Registration registration = new Registration(driver);
        registration.loginButtonOnMainPageClick();
        registration.inputEmail("qwe@qwe.com");
        registration.inputPassword("123456");
        registration.loginButtonOnMainPageClick_2();
        registration.userAccountButtonClick();
        registration.logoutUserButtonIsDisplayed();
    }

    @Test
    @DisplayName("Go to constructor by constructor button test")
    public void goToConstructorByConstructorButtonTest() {
        Registration registration = new Registration(driver);
        registration.loginButtonOnMainPageClick();
        registration.inputEmail("qwe@qwe.com");
        registration.inputPassword("123456");
         registration.loginButtonOnMainPageClick_2();
        Header header = new Header(driver);
        header.constructorButtonClick();
        BurgerAssembling burgerAssembling = new BurgerAssembling(driver);
        burgerAssembling.constructorTabsAreDisplayedCheck();
    }
}
