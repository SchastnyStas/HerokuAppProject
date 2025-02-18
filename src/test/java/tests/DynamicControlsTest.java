package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class DynamicControlsTest {

    @Test
    public void checkVerifyDynamicControls() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        driver.findElement(By.xpath("//*[@onclick='swapCheckbox()']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        String afterRemoveText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='message']"))).getText();
        softAssert.assertEquals(afterRemoveText, "It's gone!");
        int numberOfElements = driver.findElements(By.xpath("//*[@id='checkbox']")).size();
        softAssert.assertEquals(numberOfElements, 0, "Элемент присутствует на странице");
        assertFalse("Поле ввода должно быть недоступно.", driver.findElement(By.xpath("//*[@type='text']")).isEnabled());
        driver.findElement(By.xpath("//*[@onclick='swapInput()']")).click();
        String afterEnableText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='message']"))).getText();
        softAssert.assertEquals(afterEnableText, "It's enabled!");
        assertTrue("Поле ввода должно быть доступно.", driver.findElement(By.xpath("//*[@type='text']")).isEnabled());
        softAssert.assertAll();
        driver.quit();
    }
}