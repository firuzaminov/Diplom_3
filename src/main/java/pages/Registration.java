package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Registration {
    public static final String REGISTER_PAGE_PATH = "https://stellarburgers.nomoreparties.site/register";
    private final WebDriver driver;
    private final By NAME = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private final By EMAIL = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By PASSWORD = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By ERROR_MESSAGE = By.xpath(".//p[@class='input__error text_type_main-default']");
    private final By REGISTRATION_BUTTON_ON_SIGN_IN_PAGE = By.className("button_button__33qZ0");
    private final By SING_IN = By.xpath(".//div[@class='Auth_login__3hAey']//a[@class='Auth_link__1fOlj']");
    private static final By LOGOUT_BUTTON = By.xpath("(//button[@type='button'])[1]");
    private static final By USER_CABINET_BUTTON = By.xpath("//p[text()='Личный Кабинет']");
    private static final By LOGIN_BUTTON_SIGN_IN_ACCOUNT = By.xpath("//button[text()='Войти в аккаунт']");
    private static final By LOGIN_BUTTON_SIGN_IN = By.xpath("//button[text()='Войти']");
    private By MAKE_ORDER_BUTTON = By.xpath(".//button[text()='Оформить заказ']");
    private By SIGN_IN_BUTTON = By.xpath(".//a[text()='Войти']");
    private By FORGOT_PASSWORD_BUTTON = By.xpath(".//a[text()='Восстановить пароль']");
    private By SIGN_IN_FROM_REGISTRATION_PAGE_BUTTON = By.className("Auth_link__1fOlj");

    public Registration(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Input name")
    public void inputName(String name) {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(NAME)).sendKeys(name);
    }

    @Step("Sign in button click")
    public void clickSignInButton(WebDriver driver) {
        driver.findElement(SIGN_IN_BUTTON).click();
    }

    @Step("Sign in from registration page button")
    public void clickSighInRegistrationButton(WebDriver driver) {
        driver.findElement(SIGN_IN_FROM_REGISTRATION_PAGE_BUTTON).click();
    }

    @Step("Input email")
    public void inputEmail(String email) {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(EMAIL)).sendKeys(email);
    }

    @Step("Input password")
    public void inputPassword(String password) {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(PASSWORD)).sendKeys(password);
    }

    @Step("Click registration button")
    public void clickRegisterButton() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(REGISTRATION_BUTTON_ON_SIGN_IN_PAGE)).click();
    }

    @Step("Show error")
    public boolean showError() {
        return driver.findElement(ERROR_MESSAGE).isDisplayed();
    }

    @Step("Login button is displayed check")
    public boolean loginButtonIsDisplayedCheck() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(SING_IN));
        return driver.findElement(SING_IN).isDisplayed();

    }

    @Step("User account button check")
    public void userAccountButtonClick() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(USER_CABINET_BUTTON));
        driver.findElement(USER_CABINET_BUTTON).click();
    }

    @Step("Logout user")
    public void logoutUser() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(LOGOUT_BUTTON));
        driver.findElement(LOGOUT_BUTTON).click();
    }

    @Step("Login button on main page click Enter in account")
    public void loginButtonOnMainPageClick() {
        driver.findElement(LOGIN_BUTTON_SIGN_IN_ACCOUNT).click();
    }

    @Step("Login button on main page click Enter")
    public void loginButtonOnEnterPageClick() {
        driver.findElement(LOGIN_BUTTON_SIGN_IN).click();
    }

    public boolean isLogoutButtonVisible(WebDriver driver) {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(LOGOUT_BUTTON));
        return driver.findElement(LOGOUT_BUTTON).isDisplayed();
    }

    public void forgotPasswordButtonClick() {
        driver.findElement(FORGOT_PASSWORD_BUTTON).click();
    }

}
