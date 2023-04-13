import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BurgerAssembling;

import static org.junit.Assert.assertEquals;
import static pages.BurgerAssembling.BURGER_ASSEMBLING_PAGE;


public class BurgerAssemblingTest {
    private WebDriver driver;


    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BURGER_ASSEMBLING_PAGE);
    }

    @Test
    @DisplayName("Transition buns constructor section test")
    public void transitionBunsConstructorSectionTest() {
        BurgerAssembling burgerAssembling = new BurgerAssembling(driver);
        burgerAssembling.saucesSectionClick();
        burgerAssembling.bunsSectionClick();
        assertEquals(burgerAssembling.currentActiveText(), "Булки");
    }

    @Test
    @DisplayName("Transition sauces constructor section test")
    public void transitionSaucesConstructorSectionTest() {
        BurgerAssembling burgerAssembling = new BurgerAssembling(driver);
        burgerAssembling.saucesSectionClick();
        assertEquals(burgerAssembling.currentActiveText(), "Соусы");
    }

    @Test
    @DisplayName("Transition fillings constructor section test")
    public void transitionFillingsConstructorSectionTest() {
        BurgerAssembling burgerAssembling = new BurgerAssembling(driver);
        burgerAssembling.fillingsSectionClick();
        assertEquals(burgerAssembling.currentActiveText(), "Начинки");
    }

    @After
    public void closeWebSite() {
        driver.quit();
    }


}
