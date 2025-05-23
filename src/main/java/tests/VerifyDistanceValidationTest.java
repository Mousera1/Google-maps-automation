package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyDistanceValidationTest {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.get("https://www.google.com/maps");

           
            Thread.sleep(3000);

            
            WebElement directionsBtn = driver.findElement(By.id("hArJGc"));
            directionsBtn.click();
            Thread.sleep(2000);

           
            WebElement sourceInput = driver.findElement(By.xpath("//input[@aria-label='Choose starting point, or click on the map...']"));
            sourceInput.sendKeys("Chennai");
            Thread.sleep(2000);

            
            WebElement destInput = driver.findElement(By.xpath("//input[@aria-label='Choose destination, or click on the map...']"));
            destInput.sendKeys("Bangalore");
            Thread.sleep(3000);
            destInput.sendKeys(Keys.ENTER);
            Thread.sleep(3000);
            System.out.println("done");

            
            WebElement distanceElement = driver.findElement(By.xpath("//div[contains(text(),'km')]")); // //div[contains(@class,'section-directions-trip-distance')] | 
            String distance = distanceElement.getText();

            System.out.println("Distance between Chennai and Bangalore: " + distance);

           
            if (distance.contains("km") || distance.contains("mi")) {
                System.out.println("PASS: Distance is shown correctly.");
            } else {
                System.out.println("FAIL: Distance not shown correctly.");
            }

        } catch (Exception e) {
            System.out.println("FAIL: An error occurred.");
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}

