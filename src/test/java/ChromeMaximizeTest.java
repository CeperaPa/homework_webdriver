import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class ChromeMaximizeTest {
    private WebDriver webDriver;
    private static final Logger logger = LogManager.getLogger(ChromeMaximizeTest.class);

    @BeforeAll
    public static void init() {
        logger.trace("Загружаем WebDriverManager");
        WebDriverManager.chromedriver().setup();
        logger.trace("Загрузили WebDriverManager");
    }

    @AfterEach
    public void close() {
        logger.trace("Закрываем браузер");
        if (webDriver != null) {
            //webDriver.close();
            webDriver.quit();
        }
        logger.trace("Закрыли браузер");
    }

    @Test
    void authorizationOtus() {
        logger.trace("Устанавливаем режим fullscreen");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-fullscreen");
        logger.trace("Устанавили режим fullscreen");
        logger.trace("Открываем браузер");
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //webDriver.manage().window().
        logger.trace("Открыли браузер");
        logger.trace("Заходим на сайт http://otus.ru");
        webDriver.get("http://otus.ru");
        logger.trace("Зашли на сайт http://otus.ru");
        logger.trace("Находим элемент кнопки 'Войти' (на домашней странице) и кликаем на неё");
        webDriver.findElement(new By.ByXPath("//button[text()='Войти']")).click();
        logger.trace("Находим элемент поля ввода 'Электронная почта' и кликаем на поле");
        webDriver.findElement(new By.ByXPath("//div[./input[@name='email']]")).click();
        logger.trace("Заполняем поле 'Электронная почта'");
        webDriver.findElement(new By.ByXPath("//input[@name='email']")).sendKeys("kypca4kc3u@gmail.com");
        logger.trace("Находим элемент поля ввода 'Пароль' и кликаем на поле");
        webDriver.findElement(new By.ByXPath("//div[./input[@type='password']]")).click();
        logger.trace("Заполняем поле 'Пароль'");
        webDriver.findElement(new By.ByXPath("//input[@type='password']")).sendKeys("Gfhjkm12345.");
        logger.trace("Находим элемент кнопки 'Войти' (на странице авторизации) и кликаем на неё");
        webDriver.findElement(new By.ByXPath("//button[./*[text()='Войти']]")).click();
        logger.trace("Выводим куки");
        System.out.println(webDriver.manage().getCookies());
        logger.trace("Тест завершён успешно");
    }

}
