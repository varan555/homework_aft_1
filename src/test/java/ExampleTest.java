import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ExampleTest {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void startUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        //driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 20);
    }

    @Test
    public void exampleTest() {
        driver.get("http://www.rgs.ru");
        String menuXpath = "//div[@id='main-navbar-collapse']//a[@data-toggle and @class='hidden-xs']";
        WebElement menuElement = driver.findElement(By.xpath(menuXpath));
        menuElement.click();

        String DmsXpath = "//a[@href='https://www.rgs.ru/products/private_person/health/dms/generalinfo/index.wbp']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(DmsXpath)));
        driver.findElement(By.xpath(DmsXpath)).click();
// часто бывает org.openqa.selenium.TimeoutException: timeout: Timed out receiving message from renderer: 20.000


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//h1")));
        Assert.assertEquals("Заголовок не соответствует ожидаемому", "ДМС — добровольное медицинское страхование",
                driver.findElement(By.xpath("//div//h1")).getText());

        String sendRequest = "//a[@data-toggle ='page-common-popup-form' and contains(text(), 'Отправить заявку')]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(sendRequest)));
        driver.findElement(By.xpath(sendRequest)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[@class='modal-title']")));
        Assert.assertEquals("Заголовок не соответствует ожидаемому", "Заявка на добровольное медицинское страхование",
                driver.findElement(By.xpath("//h4[@class='modal-title']")).getText());

        String inputLastName = "//input[contains(@data-bind, 'LastName')]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(inputLastName)));
        driver.findElement(By.xpath(inputLastName)).sendKeys("Иванов");

        String inputFirstName = "//input[contains(@data-bind, 'FirstName')]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(inputFirstName)));
        driver.findElement(By.xpath(inputFirstName)).sendKeys("Иван");

        String inputMiddleName = "//input[contains(@data-bind, 'MiddleName')]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(inputMiddleName)));
        driver.findElement(By.xpath(inputMiddleName)).sendKeys("Иванович");

        String selectRegion = "//select/option[@value='77']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(selectRegion)));
        driver.findElement(By.xpath(selectRegion)).click();

        String inputPhone = "//input[contains(@data-bind, 'Phone')]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(inputPhone)));
        driver.findElement(By.xpath(inputPhone)).sendKeys("9999999999");

        String inputEmail = "//input[contains(@data-bind, 'Email')]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(inputEmail)));
        driver.findElement(By.xpath(inputEmail)).sendKeys("Its_not_email");

        String ContactDate = "//input[contains(@data-bind, 'ContactDate')]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ContactDate)));
        driver.findElement(By.xpath(ContactDate)).sendKeys("26062020");

        String ContactDateC = "//input[contains(@data-bind, 'ContactDate')]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ContactDateC)));
        driver.findElement(By.xpath(ContactDateC)).click();

        String Comments = "//textarea";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Comments)));
        driver.findElement(By.xpath(Comments)).sendKeys("test");

        String agree = "//input[@class='checkbox']";
       // wait.until(ExpectedConditions.elementToBeClickable(By.xpath(agree)));
        driver.findElement(By.xpath(agree)).click();

        String send = "//button[@id]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(send)));
        driver.findElement(By.xpath(send)).click();

        Assert.assertEquals("Не заполняется Имя", "Иван",
                driver.findElement(By.xpath("//input[contains(@data-bind, 'FirstName')]")).getAttribute("value"));

        Assert.assertEquals("Не заполняется Фамилия", "Иванов",
                driver.findElement(By.xpath("//input[contains(@data-bind, 'LastName')]")).getAttribute("value"));

        Assert.assertEquals("Не заполняется отчество", "Иванович",
                driver.findElement(By.xpath("//input[contains(@data-bind, 'MiddleName')]")).getAttribute("value"));

        Assert.assertEquals("Не выбирается регион", "77",
                driver.findElement(By.xpath("//select/option[@value='77']")).getAttribute("value"));

//        Assert.assertEquals("Не вводится телефон", "79999999999",
//                driver.findElement(By.xpath("//input[contains(@data-bind, 'Phone')]")).getAttribute("value"));

        Assert.assertEquals("Не вводится email", "Its_not_email",
                driver.findElement(By.xpath("//input[contains(@data-bind, 'Email')]")).getAttribute("value"));

        Assert.assertEquals("Не вводится дата", "26.06.2020",
                driver.findElement(By.xpath("//input[contains(@data-bind, 'ContactDate')]")).getAttribute("value"));

        Assert.assertEquals("Не вводится комментарий", "test",
                driver.findElement(By.xpath("//textarea")).getAttribute("value"));

    }

    @After
    public void close() {
        driver.quit();
    }
}
