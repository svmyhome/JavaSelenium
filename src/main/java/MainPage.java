import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    private By signInButton = By.xpath("//a[@href='/login']");
    private By signUpButton = By.xpath("//a[contains(text(),'Sign up')]");
    private By signUpGithub = By.xpath("//button[contains(text(),'Sign up for GitHub')]");
    private By emailField = By.xpath("//input[@id='user_email']");

    public LoginPage clickSignIn() {
        driver.findElement(signInButton).click();
        return new LoginPage(driver);
    }

    public SignUpPage clickSignUp() {
        driver.findElement(signUpButton).click();
        return new SignUpPage(driver);
    }

    public SignUpPage clickSignUpGitHub() {
        driver.findElement(signUpGithub).click();
        return new SignUpPage(driver);
    }

    public MainPage typeEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public SignUpPage register(String email) {
        this.typeEmailField(email);
        this.clickSignUpGitHub();
        return new SignUpPage(driver);
    }

}
