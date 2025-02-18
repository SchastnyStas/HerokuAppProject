package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FileUploadTest {

    @Test
    public void checkFileUpload() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/upload");
        String pathToFile = System.getProperty("user.dir") + "/src/test/resources/text.txt";
        driver.findElement(By.xpath("//*[@id='file-upload']")).sendKeys(pathToFile);
        driver.findElement(By.xpath("//*[@id='file-submit']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id='uploaded-files']")).getText(), "text.txt");
        driver.quit();
    }
}
