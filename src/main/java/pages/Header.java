package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header {
    private final WebDriver driver;

    public Header(WebDriver driver) {
        this.driver = driver;
    }
    private static final By CONSTRUCTOR_BUTTON = By.xpath("//p[text()='Конструктор']");
    @Step("Constructor button click")
    public void constructorButtonClick() {
        driver.findElement(CONSTRUCTOR_BUTTON).click();
    }
}
