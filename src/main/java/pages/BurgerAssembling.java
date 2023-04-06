package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class BurgerAssembling {

    private final WebDriver driver;

    public BurgerAssembling(WebDriver driver) {
        this.driver = driver;
    }

    public static final String BURGER_ASSEMBLING_PAGE = "https://stellarburgers.nomoreparties.site/";
    private static final By BUNS_SECTION = By.xpath("(//div[contains(@class,'tab_tab__1SPyG ')])[1]");
    private static final By SAUCES_SECTION = By.xpath("(//div[contains(@class,'tab_tab__1SPyG ')])[2]");
    private static final By FILLINGS_SECTION = By.xpath("(//div[contains(@class,'tab_tab__1SPyG ')])[3]");
    private static final By TAB_ACTIVE = By.xpath("//div[contains(@class,'tab_tab__1SPyG tab_tab_type_current__2BEPc')]//span[1]");

    @Step("Sauces section click")
    public void saucesSectionClick() {
        driver.findElement(SAUCES_SECTION).click();
    }

    @Step("Buns section click")
    public void bunsSectionClick() {
        driver.findElement(BUNS_SECTION).click();
    }

    @Step("Filling section click")
    public void fillingsSectionClick() {
        driver.findElement(FILLINGS_SECTION).click();
    }


    @Step("Constructor tabs are displayed check")
    public void constructorTabsAreDisplayedCheck() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(BUNS_SECTION));
        driver.findElement(BUNS_SECTION).isDisplayed();
        driver.findElement(SAUCES_SECTION).isDisplayed();
        driver.findElement(FILLINGS_SECTION).isDisplayed();
    }
    @Step("Get current active text")
    public String currentActiveText() {
        return driver.findElement(TAB_ACTIVE).getText();
    }
}
