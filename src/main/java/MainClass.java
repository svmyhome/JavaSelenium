import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class MainClass {
    public static WebDriver driver;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        driver.manage().window().maximize(); // установка максимального размера экрана
   //     driver.get("https://github.com/");
//        MainPage mainPage = new MainPage(driver);
//        mainPage.clickSignIn();
//        driver.get("https://github.com/login");
     /*   LoginPage loginPage = new LoginPage(driver);
        loginPage.LoginWithInvalidCreds("123", "dfffd");
        System.out.println(loginPage.getErrorText());
        loginPage.createAnAccount();*/
        driver.get("https://github.com/signup?ref_cta=Sign+up&ref_loc=header+logged+out&ref_page=%2F&source=header-home");

       SignUpPage signUpPage = new SignUpPage(driver);
        System.out.println(signUpPage.getAdventure());

//       signUpPage.typeEmailField("helloWorld123321@mail.ru");
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        signUpPage.clickEmailButton("helloWorld123321@mail.ru");
//        signUpPage.typePassField("s21c12@com.ru", "cjkdnjsdncjndj");
        signUpPage.clickpassButton("s21c12@com.ru", "Vjkdnjsd1!ncjndj");

    }
}
