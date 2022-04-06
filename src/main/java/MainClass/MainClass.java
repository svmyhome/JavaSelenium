package MainClass;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class MainClass {

    public static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        //  StartBrowser();
        // FirstFindElements();
        //  FirsButtonClick();
//        FirstSendKeys();
  // FirstCheckBoxSelected("ATLANT");
//        FirstCheckBoxDeselected("ATLANT");
//        FirstRidioButtonSelected();
//        FirstCheckBox();
        FirstCheckBoxList();
    }

    public static void StartBrowser() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\MyPetProject\\FistAutomatization\\src\\main\\resources\\webdriver\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        // Установка размера браузера
        driver.manage().window().setSize(new Dimension(500, 600)); // установка конкретного разрешения
        Thread.sleep(1000);
        driver.manage().window().maximize(); // установка максимального размера экрана

        // Переход по ссылкам и навигация
        driver.get("https:\\yandex.ru");

        driver.navigate().to("https:\\google.com");
        Thread.sleep(1000);
        driver.navigate().back(); //переход назад

        driver.navigate().forward(); //переход вперед
        driver.navigate().refresh(); //перезагружает страницу
        Thread.sleep(1000);

        // Получение информации
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        driver.quit();
    }

    public static void FirstFindElements() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\MyPetProject\\FistAutomatization\\src\\main\\resources\\webdriver\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        driver.manage().window().maximize(); // установка максимального размера экрана
        driver.get("https://ru.wikipedia.org/");

//        WebElement[] webarr = new WebElement[8];
//        webarr[0] = driver.findElement(By.linkText("Войти"));
//        webarr[1] = driver.findElement(By.partialLinkText("Создать учётную запись"));
//        webarr[2] = driver.findElement(By.name("search"));
//        webarr[3] = driver.findElement(By.cssSelector("div#main-tfa div.main-box-subtitle"));
        WebElement elem = driver.findElement(By.xpath("//div[contains('text()', 'новых материалов'"));
        driver.quit();
//
//        for (WebElement index : webarr) {
//            System.out.println(index);
//        }


    }

    public static void FirsButtonClick() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\MyPetProject\\FistAutomatization\\src\\main\\resources\\webdriver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        driver.manage().window().maximize(); // установка максимального размера экрана
        driver.get("https://www.wikipedia.org/");
        String el = driver.findElement(By.xpath("//a[@id='js-link-box-en']//strong[text()='English']")).getText();
        driver.findElement(By.xpath("//a[@id='js-link-box-en']")).click();
        System.out.println("=========================================");
        System.out.println(el);
        driver.get("https://github.com/");
        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
        System.out.println("=========================================");
        System.out.println(button.getText()); //Забирает текст где то webelement где-то String не со всех элементов (button)
        button.submit();
        Thread.sleep(5000);
        driver.quit();
    }

    public static void FirstSendKeys() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\MyPetProject\\FistAutomatization\\src\\main\\resources\\webdriver\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        driver.manage().window().maximize(); // установка максимального размера экрана
        driver.get("https://www.wikipedia.org/");
        driver.findElement(By.xpath("//input[@id= 'searchInput']")).sendKeys("webdriver");
        System.out.println("=======");
        System.out.println(driver.findElement(By.xpath("//input[@id= 'searchInput']")).getAttribute("value"));
        System.out.println(driver.findElement(By.xpath("//input[@id= 'searchInput']")).getAttribute("type"));
        System.out.println(driver.findElement(By.xpath("//input[@id= 'searchInput']")).getAttribute("name"));
        System.out.println("=======");
        driver.findElement(By.xpath("//input[@id= 'searchInput']")).clear();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@id= 'searchInput']")).sendKeys("webdriver");
        driver.findElement(By.xpath("//button[@class='pure-button pure-button-primary-progressive']")).click();
        Thread.sleep(2000);
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        WebElement el1 = driver.findElement(By.xpath("//a[@title = 'Portal:The arts']"));
        System.out.println("=======");
        System.out.println(el1.getText());
        System.out.println("=======");
        el1.click();
        driver.quit();
    }

    public static void FirstCheckBoxSelected(String string) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\MyPetProject\\FistAutomatization\\src\\main\\resources\\webdriver\\chromedriver.exe");
        String xPath = "//span[text()='%s']/parent::div";
        String xPath1 = "//input[@name='Производитель %s']";
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        driver.manage().window().maximize(); // установка максимального размера экрана
        driver.get("https://market.yandex.ru/catalog--kholodilniki/71639/list?hid=15450081&onstock=0&local-offers-first=0");
        driver.findElement(By.xpath(String.format(xPath, string))).click();
        System.out.println(driver.findElement(By.xpath(String.format(xPath1, string))).isSelected());
        Thread.sleep(3000);
    }

    public static void FirstCheckBoxDeselected(String string) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\MyPetProject\\FistAutomatization\\src\\main\\resources\\webdriver\\chromedriver.exe");
        String xPath = "//span[text()='%s']/parent::div";
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        driver.manage().window().maximize(); // установка максимального размера экрана
        driver.get("https://market.yandex.ru/catalog--kholodilniki/71639/list?hid=15450081&onstock=0&local-offers-first=0");
        driver.findElement(By.xpath(String.format(xPath, string))).click();
        Thread.sleep(3000);
    }

    public static void FirstRidioButtonSelected() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\MyPetProject\\FistAutomatization\\src\\main\\resources\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        driver.manage().window().maximize(); // установка максимального размера экрана
        driver.get("https://market.yandex.ru/catalog--kholodilniki/71639/list?hid=15450081&onstock=0&local-offers-first=0");
        driver.findElement(By.xpath("//span[text()='До 5 дней']")).click();
        driver.findElement(By.xpath("//span[text()='Сегодня или завтра']")).click();
        System.out.println(driver.findElement(By.xpath("//span[text()='До 5 дней']/../preceding-sibling::input")).isSelected());
        System.out.println(driver.findElement(By.xpath("//span[text()='Сегодня или завтра']/../preceding-sibling::input")).isSelected());
        Thread.sleep(3000);
    }

    public static void FirstCheckBox() {
        System.setProperty("webdriver.chrome.driver", "D:\\MyPetProject\\FistAutomatization\\src\\main\\resources\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        driver.manage().window().maximize(); // установка максимального размера экрана
        driver.get("https://accounts.google.com/signin/v2/identifier?flowEntry=AddSession&service=mail&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&flowName=GlifWebSignIn");
        driver.findElement(By.xpath("//div[@role='presentation' and @jsname='LgbsSe']")).click();
        driver.findElement(By.xpath("//span[contains('text()', 'Afrikaans')]/parent::div")).click();
    }

    public static void FirstCheckBoxList() {
        System.setProperty("webdriver.chrome.driver", "D:\\MyPetProject\\FistAutomatization\\src\\main\\resources\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        driver.manage().window().maximize(); // установка максимального размера экрана
        driver.get("https://www.mvideo.ru/stiralnye-i-sushilnye-mashiny-2427/stiralnye-mashiny-89/f/category=uzkie-stiralnye-mashiny-2446?from=under_search");
        List<WebElement> listEl = driver.findElements(By.xpath("(//div[@class='filter-checkbox-list__container filter-checkbox-list__container--with-show-btn'])[2]//div[@class='checkbox']"));

        for (WebElement index: listEl) {
            index.click();
        }
    }

}
