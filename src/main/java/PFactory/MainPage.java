package PFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//a[@href='/login']")
    private WebElement signInButton;
    @FindBy(xpath = "//a[contains(text(),'Sign up')]")
    private WebElement signUpButton;
    @FindBy(xpath = "//button[contains(text(),'Sign up for GitHub')]")
    private WebElement signUpGithub;
    @FindBy(xpath = "//input[@id='user_email']")
    private WebElement emailField;

    public LoginPage clickSignIn() {
        signInButton.click();
        return new LoginPage(driver);
    }

    public SignUpPage clickSignUp() {
        signUpButton.click();
        return new SignUpPage(driver);
    }

    public SignUpPage clickSignUpGitHub() {
        signUpGithub.click();
        return new SignUpPage(driver);
    }

    public MainPage typeEmailField(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public SignUpPage register(String email) {
        this.typeEmailField(email);
        this.clickSignUpGitHub();
        return new SignUpPage(driver);
    }

}
