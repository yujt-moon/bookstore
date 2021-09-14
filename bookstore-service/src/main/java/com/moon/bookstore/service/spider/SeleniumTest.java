package com.moon.bookstore.service.spider;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * chromedriver http://chromedriver.storage.googleapis.com/index.html
 * @author yujiangtao
 * @date 2020/7/19 上午10:35
 */
public class SeleniumTest {

    @Test
    public void testSelenium() {
        System.getProperties().setProperty("webdriver.chrome.driver", "/home/yujt/software/chrome-driver/chromedriver");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://huaban.com/");
        WebElement webElement = webDriver.findElement(By.xpath("/html"));
        System.out.println(webElement.getAttribute("outerHTML"));
        String text = webDriver.findElement(By.xpath("//*/div[@class='title']")).getText();
        System.out.println("--------" + text);
        webDriver.close();
    }

    @Test
    public void testIqiyi() throws Exception {
        System.getProperties().setProperty("webdriver.chrome.driver", "/home/yujt/software/chrome-driver/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.iqiyi.com/v_19rrsozwik.html");
        TimeUnit.SECONDS.sleep(5L);
        System.out.println(driver.getTitle());
        List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"rightPlayList\"]/div[1]/div/div[1]/div[3]/ul/li/a"));
        for (WebElement element : elements) {
            System.out.println("每集标题：" + element.getText());
            element.click();
            TimeUnit.SECONDS.sleep(2L);
            System.out.println("-------------" + driver.getCurrentUrl());
        }
        driver.close();
    }
}
