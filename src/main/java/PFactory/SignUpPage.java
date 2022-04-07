package PFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    public SignUpPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailField;
    @FindBy(xpath = "//span[@class='text-mono text-gray-mktg']")
    private WebElement textAdventure;
    @FindBy(xpath = "//button[contains(@class,'js-continue-button signup-continue-button')]")
    private WebElement emailButton; //TODO create class for fille field abd before check for disabling
    @FindBy(xpath = "//input[@id='password']")
    private WebElement passField;
    @FindBy(xpath = "//button[@data-continue-to='username-container']")
    private WebElement passButton;
    @FindBy(xpath = "//input[@id='login']")
    private WebElement nameField;
    @FindBy(xpath = "//input[@id='login']")
    private WebElement nameButton;
    @FindBy(xpath = "//label[@for='opt_in']")
    private WebElement textNotification;
    @FindBy(xpath = "//input[@name='opt_in']")
    private WebElement NotificationField;
    @FindBy(xpath = "//button[@data-optimizely-event='click.signup_continue.opt-in']")
    private WebElement NotificationButton;


    public String getAdventure() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return textAdventure.getText();
    }

    public SignUpPage typeEmailField(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public SignUpPage waitField(WebElement field) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(field));
        return this;
    }

    public SignUpPage clickEmailButton(String email) {
        this.typeEmailField(email);
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.elementToBeClickable(emailButton));
        waitField(emailButton);
        emailButton.click();
        return new SignUpPage(driver);
    }

    public SignUpPage typePassField(String email, String pass) {
        this.clickEmailButton(email);
        passField.sendKeys(pass);
        return this;
    }

    public SignUpPage clickpassButton(String email, String pass) {
        this.typePassField(email, pass);
        waitField(passButton);
        passButton.click();
        return new SignUpPage(driver);
    }





}
