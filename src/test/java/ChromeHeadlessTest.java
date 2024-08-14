import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;

public class ChromeHeadlessTest {

    private static final Logger logger = LogManager.getLogger(ChromeHeadlessTest.class);

    private final String text = "Онлайн‑курсы для профессионалов, дистанционное обучение современным ...";
    private WebDriver webDriver;

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
    void otusSearch() {
        logger.trace("Устанавливаем режим headless");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        logger.trace("Устанавили режим headless");
        logger.trace("Открываем браузер");
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        logger.trace("Открыли брузер");
        logger.trace("Переходим на страницу https://duckduckgo.com/");
        webDriver.get("https://duckduckgo.com/");
        logger.trace("Перешли на страницу https://duckduckgo.com/");
        logger.trace("Ищем поле ввода запроса поиска");
        WebElement search = webDriver.findElement(new By.ByXPath("//input[@class='searchbox_input__bEGm3']"));
        logger.trace("Нашли поле ввода запроса поиска");
        logger.trace("Кликаем на поле ввода запроса поиска");
        search.click();
        logger.trace("Вводим в поле запроса поиска текст: ОТУС");
        search.sendKeys("ОТУС");
        logger.trace("Жмём ENTER в подтверждение запроса");
        Actions actions = new Actions(webDriver);
        actions.sendKeys(Keys.ENTER).perform();
        logger.trace("Ищем элемент с текстом первого результата поиска");
        WebElement heading = webDriver.findElement(new By.ByXPath("//ol[@class='react-results--main']/li[1]//a[@data-testid='result-title-a']//span"));
        logger.trace("Фиксируем текст первого результата поиска");
        String result = heading.getText();
        logger.trace("Сравниваем исходный текст с текстом первого результата поиска ");
        Assertions.assertEquals(text, result);
        logger.trace("Тест завершён успешно");
    }
}
