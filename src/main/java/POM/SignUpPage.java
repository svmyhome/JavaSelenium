package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

    private By emailField = By.xpath("//input[@id='email']");
    private By textAdventure = By.xpath("//span[@class='text-mono text-gray-mktg']");
    private By emailButton = By.xpath("//button[contains(@class,'js-continue-button signup-continue-button')]"); //TODO create class for fille field abd before check for disabling
    private By passField = By.xpath("//input[@id='password']");
    private By passButton = By.xpath("//button[@data-continue-to='username-container']");
    private By nameField = By.xpath("//input[@id='login']");
    private By nameButton = By.xpath("//input[@id='login']");
    private By textNotification = By.xpath("//label[@for='opt_in']");
    private By NotificationField = By.xpath("//input[@name='opt_in']");
    private By NotificationButton = By.xpath("//button[@data-optimizely-event='click.signup_continue.opt-in']");

    public String getAdventure() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return driver.findElement(textAdventure).getText();
    }

    public SignUpPage typeEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public SignUpPage waitField(By field) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(field));
        return this;
    }

    public SignUpPage clickEmailButton(String email) {
        this.typeEmailField(email);
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.elementToBeClickable(emailButton));
        waitField(emailButton);
        driver.findElement(emailButton).click();
        return new SignUpPage(driver);
    }

    public SignUpPage typePassField(String email, String pass) {
        this.clickEmailButton(email);
        driver.findElement(passField).sendKeys(pass);
        return this;
    }

    public SignUpPage clickpassButton(String email, String pass) {
        this.typePassField(email, pass);
        waitField(passButton);
        driver.findElement(passButton).click();
        return new SignUpPage(driver);
    }





}
