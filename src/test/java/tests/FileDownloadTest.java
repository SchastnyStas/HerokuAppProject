package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;

public class FileDownloadTest {

    @Test
    public void checkFileDownLoad() throws InterruptedException {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        WebDriver driver = new ChromeDriver(options);
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver.get("https://the-internet.herokuapp.com/download");
        driver.findElement(By.xpath("//*[@href='download/name.txt']")).click();
        Thread.sleep(3000);
        File folder = new File(System.getProperty("user.dir"));
        File[] listOfFiles = folder.listFiles();
        boolean isFound = false;
        File f = null;
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                String fileName = listOfFile.getName();
                if (fileName.matches("name.txt")) {
                    f = new File(fileName);
                    isFound = true;
                }
            }
        }
        Assert.assertTrue(isFound, "Downloaded document is not found");
        f.deleteOnExit();
        driver.quit();
    }
}