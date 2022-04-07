package PFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//h1[text()='Sign in to GitHub']")
    private WebElement heading;
    @FindBy(xpath = "//input[@id='login_field']")
    private WebElement userField;
    @FindBy(xpath = "//input[@id='password']")
    private WebElement passField;
    @FindBy(xpath = "//input[@name='commit']")
    private WebElement signInButton;
    @FindBy(xpath = "//div[@class='px-2']")
    private WebElement error;
    @FindBy(xpath = "//a[text()='Create an account']")
    private WebElement linkSignUp;



    public LoginPage typeUserName(String username) {
        userField.sendKeys(username);
        return this;
    }
    public LoginPage typeUserPass(String pass) {
        passField.sendKeys(pass);
        return this;
    }

    public String getHeadingText() {
        return heading.getText();
    }

    public String getErrorText() {
        return error.getText();
    }

    public SignUpPage createAnAccount() {
        linkSignUp.click();
        return new SignUpPage(driver);
    }

    public LoginPage LoginWithInvalidCreds(String username, String pass) {
        this.typeUserName(username);
        this.typeUserPass(pass);
        signInButton.click();
        return new LoginPage(driver);
    }
}
