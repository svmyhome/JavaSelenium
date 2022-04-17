import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//TODO Create method that is checked color of the button ""
public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By heading = By.xpath("//h1[text()='Sign in to GitHub']");
    private By userField = By.xpath("//input[@id='login_field']");
    private By passField = By.xpath("//input[@id='password']");
    private By signInButton = By.xpath("//input[@name='commit']");
    private By error = By.xpath("//div[@class='px-2']");
    private By linkSignUp = By.xpath("//a[text()='Create an account']");

    public LoginPage typeUserName(String username) {
        driver.findElement(userField).sendKeys(username);
        return this;
    }
    public LoginPage typeUserPass(String pass) {
        driver.findElement(passField).sendKeys(pass);
        return this;
    }

    public String getHeadingText() {
        return driver.findElement(heading).getText();
    }

    public String getErrorText() {
        return driver.findElement(error).getText();
    }

    public String getColorButton() {
        return driver.findElement(signInButton).getCssValue("background-color");
    }
    public SignUpPage createAnAccount() {
        driver.findElement(linkSignUp).click();
        return new SignUpPage(driver);
    }

    public LoginPage LoginWithInvalidCreds(String username, String pass) {
        this.typeUserName(username);
        this.typeUserPass(pass);
        driver.findElement(signInButton).click();
        return new LoginPage(driver);
    }
}
