import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;


public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp()throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.1");
        capabilities.setCapability("automationName","UiAutomator2");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/nickolay/Lesson_1/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
    }

    @After
    public void tearDown ()
    {
        driver.quit();
    }


    @Test
    public void ex2_method_creation()
    {

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );


        WebElement title_search_element = waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Cant find Search... Title",
                20
        );

        String search_input_title = title_search_element.getAttribute("text");
        Assert.assertEquals(
                "Title('Search...') is not equal to founded title",
                "Search…",
                search_input_title
        );


    }


    @Test
    public void ex3_cancel_search()
    {

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Cyprus",
                "Cannot find search input",
                10
        );

        //тут проверим наличие нескольких статей
         waitForElementPresent(
                By.xpath("//*[@class='android.widget.LinearLayout' and @index='0']"),
                "Can't finds articles - search result is empty",
                10

        );

        waitForElementPresent(
                By.xpath("//*[@class='android.widget.LinearLayout' and @index='1']"),
                "Can't finds articles - search result is empty",
                10

        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Can't clear search input",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find close button",
                5
        );

        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "close button was founded",
                5
        );


    }

    @Test
    public void ex4_check_words_in_search()
    {

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Limassol",
                "Cannot find search input",
                10
        );


    }














//    @Test
//    public void FirstTest()
//    {
//
//        waitForElementAndClick(
//                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
//                "Cannot find search input",
//                5
//        );
//
//        waitForElementAndSendKeys(
//                By.xpath("//*[contains(@text,'Search…')]"),
//                "Java",
//                "Cannot find search input",
//                10
//        );
//
//        waitForElementPresent(
//                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
//                "Cant found 'Object-oriented programming language' text",
//                30
//        );
//    }
//
//    @Test
//    public void testCancelSearch()
//    {
//        waitForElementAndClick(
//                By.id("org.wikipedia:id/search_container"),
//                "Cannot find search input",
//                5
//        );
//
//        waitForElementAndSendKeys(
//                By.xpath("//*[contains(@text,'Search…')]"),
//                "Java",
//                "Cannot find search input",
//                10
//        );
//
//        waitForElementAndClear(
//                By.id("org.wikipedia:id/search_src_text"),
//                "Can't clear",
//                5
//        );
//
//        waitForElementAndClick(
//                By.id("org.wikipedia:id/search_close_btn"),
//                "Cannot find close button",
//                5
//        );
//
//        waitForElementNotPresent(
//                By.id("org.wikipedia:id/search_close_btn"),
//                "close button was founded",
//                5
//        );
//
//    }
//
//    @Test
//    public void testCompareArticleTitle()
//    {
//        waitForElementAndClick(
//                By.id("org.wikipedia:id/search_container"),
//                "Cannot find search input",
//                5
//        );
//
//        waitForElementAndSendKeys(
//                By.xpath("//*[contains(@text,'Search…')]"),
//                "Java",
//                "Cannot find search input",
//                10
//        );
//
//        waitForElementAndClick(
//                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
//                "Cannot find search input",
//                30
//        );
//
//        WebElement title_element = waitForElementPresent(
//                By.id("org.wikipedia:id/view_page_title_text"),
//                "Cant find Java Title",
//                20
//        );
//
//        String article_title = title_element.getAttribute("text");
//        Assert.assertEquals(
//                "Title is not equal to expected title",
//                "Java (programming language)",
//                article_title
//        );
//
//
//    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                        ExpectedConditions.presenceOfElementLocated(by)
                );
    }

    private WebElement waitForElementPresent(By by, String error_message)
    {
       return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
      WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
      element.click();
      return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver,timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

}