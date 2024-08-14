import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ChromeKioskTest {
    private WebDriver webDriver;

    private static final Logger logger = LogManager.getLogger(ChromeKioskTest.class);

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
    void clickPicture() {
        logger.trace("Устанавливаем режим kiosk");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--kiosk");
        logger.trace("Устанавили режим kiosk");
        logger.trace("Открываем браузер");
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        logger.trace("Открыли браузер");
        logger.trace("Заходим на страницу https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818 ");
        webDriver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");
        logger.trace("Зашли на страницу https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818 ");
        logger.trace("Ищем элемент первой картинки и кликаем на неё");
        webDriver.findElement(new By.ByXPath("//li[@data-id='id-1']")).click();
        WebElement picture = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(new By.ByXPath("//div[@class='pp_overlay']")));
        logger.trace("Картинка открылась");
        logger.trace("Убеждаемся, что картинка открылась");
        Assertions.assertTrue(picture.isDisplayed());
        logger.trace("Тест завершён успешно");
    }
}
