package ru.netologu.card_odrer;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardOrder {


    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
    }

    @BeforeEach
    void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }


    @Test
    void inputPositiveCase() {

        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("span[data-test-id='name'] input")).sendKeys("Иван");
        driver.findElement(By.cssSelector("span[data-test-id='phone'] input")).sendKeys("+79998887744");
        driver.findElement(By.cssSelector("label[data-test-id=\"agreement\"]")).click();
        driver.findElement(By.cssSelector("button[type=\"button\"]")).click();
        String actualName = driver.findElement(By.cssSelector("p[data-test-id=\"order-success\"]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualName.trim());

    }

    @Test
    void inputPositiveCaseNameWithHyphen() {

        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("span[data-test-id='name'] input")).sendKeys("Иван-Чай");
        driver.findElement(By.cssSelector("span[data-test-id='phone'] input")).sendKeys("+79998887744");
        driver.findElement(By.cssSelector("label[data-test-id=\"agreement\"]")).click();
        driver.findElement(By.cssSelector("button[type=\"button\"]")).click();
        String actualName = driver.findElement(By.cssSelector("p[data-test-id=\"order-success\"]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualName.trim());

    }

    @Test
    void inputPositiveCaseNameWitSpace() {

        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("span[data-test-id='name'] input")).sendKeys("Иван Чай");
        driver.findElement(By.cssSelector("span[data-test-id='phone'] input")).sendKeys("+79998887744");
        driver.findElement(By.cssSelector("label[data-test-id=\"agreement\"]")).click();
        driver.findElement(By.cssSelector("button[type=\"button\"]")).click();
        String actualName = driver.findElement(By.cssSelector("p[data-test-id=\"order-success\"]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualName.trim());

    }

    @Test
    void inputEmptyName() {

        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("label[data-test-id=\"agreement\"]")).click();
        driver.findElement(By.cssSelector("button[type=\"button\"]")).click();
        String actualName = driver.findElement(By.cssSelector(".input_invalid[data-test-id='name']")).getText();
        assertEquals("Фамилия и имя\n" +
                "Поле обязательно для заполнения", actualName.trim());

    }

    @Test
    void inputEmptyPhone() {

        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("span[data-test-id='name'] input")).sendKeys("Иван");
        driver.findElement(By.cssSelector("label[data-test-id=\"agreement\"]")).click();
        driver.findElement(By.cssSelector("button[type=\"button\"]")).click();
        String actualPhone = driver.findElement(By.cssSelector(".input_invalid[data-test-id='phone']")).getText();
        assertEquals("Мобильный телефон\n" +
                "Поле обязательно для заполнения", actualPhone.trim());

    }

    @Test
    void inputEmptyCheckbox() {

        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("span[data-test-id='name'] input")).sendKeys("Иван");
        driver.findElement(By.cssSelector("span[data-test-id='phone'] input")).sendKeys("+79998887744");
        driver.findElement(By.cssSelector("button[type=\"button\"]")).click();
        String actualCheckbox = driver.findElement(By.cssSelector(".input_invalid")).getText();
        assertEquals("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй", actualCheckbox.trim());

    }

    @Test
    void inputWrongNameWithLatin() {

        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("span[data-test-id='name'] input")).sendKeys("Ivan");
        driver.findElement(By.cssSelector("span[data-test-id='phone'] input")).sendKeys("+79998887744");
        driver.findElement(By.cssSelector("label[data-test-id=\"agreement\"]")).click();
        driver.findElement(By.cssSelector("button[type=\"button\"]")).click();
        String actualName = driver.findElement(By.cssSelector(".input_invalid")).getText();
        assertEquals("Фамилия и имя\n" +
                "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actualName.trim());

    }


    @Test
    void inputWrongNameWithCharacters() {

        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("span[data-test-id='name'] input")).sendKeys("@$#");
        driver.findElement(By.cssSelector("span[data-test-id='phone'] input")).sendKeys("+79998887744");
        driver.findElement(By.cssSelector("label[data-test-id=\"agreement\"]")).click();
        driver.findElement(By.cssSelector("button[type=\"button\"]")).click();
        String actualName = driver.findElement(By.cssSelector(".input_invalid")).getText();
        assertEquals("Фамилия и имя\n" +
                "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actualName.trim());

    }


    @Test
    void inputWrongPhoneShort() {

        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("span[data-test-id='name'] input")).sendKeys("Иван");
        driver.findElement(By.cssSelector("span[data-test-id='phone'] input")).sendKeys("+7999888774");
        driver.findElement(By.cssSelector("label[data-test-id=\"agreement\"]")).click();
        driver.findElement(By.cssSelector("button[type=\"button\"]")).click();
        String actualPhone = driver.findElement(By.cssSelector(".input_invalid")).getText();
        assertEquals("Мобильный телефон\n" +
                "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actualPhone.trim());

    }

    @Test
    void inputWrongPhoneLong() {

        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("span[data-test-id='name'] input")).sendKeys("Иван");
        driver.findElement(By.cssSelector("span[data-test-id='phone'] input")).sendKeys("+799988877444");
        driver.findElement(By.cssSelector("label[data-test-id=\"agreement\"]")).click();
        driver.findElement(By.cssSelector("button[type=\"button\"]")).click();
        String actualPhone = driver.findElement(By.cssSelector(".input_invalid")).getText();
        assertEquals("Мобильный телефон\n" +
                "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actualPhone.trim());
    }

    @Test
    void inputWrongPhoneWithoutCharPlus() {

        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("span[data-test-id='name'] input")).sendKeys("Иван");
        driver.findElement(By.cssSelector("span[data-test-id='phone'] input")).sendKeys("79998887744");
        driver.findElement(By.cssSelector("label[data-test-id=\"agreement\"]")).click();
        driver.findElement(By.cssSelector("button[type=\"button\"]")).click();
        String actualPhone = driver.findElement(By.cssSelector(".input_invalid")).getText();
        assertEquals("Мобильный телефон\n" +
                "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actualPhone.trim());

    }




    @AfterEach
    void tearDown() {

        driver.quit();
        driver = null;

    }

}
