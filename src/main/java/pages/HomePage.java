package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "searchboxinput")
    WebElement searchBox;

    @FindBy(id = "widget-zoom-in")
    WebElement zoomInButton;

    @FindBy(id = "widget-zoom-out")
    WebElement zoomOutButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean isMapLoaded() {
        return driver.getTitle().contains("Google Maps");
    }

    public void searchLocation(String location) {
        wait.until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.clear();
        searchBox.sendKeys(location);
        searchBox.sendKeys(Keys.ENTER);
    }

    public void zoomIn() {
        wait.until(ExpectedConditions.elementToBeClickable(zoomInButton)).click();
    }

    public void zoomOut() {
        wait.until(ExpectedConditions.elementToBeClickable(zoomOutButton)).click();
    }

    public void openDirections() {
        By directionsLocator = By.id("hArJGc");
        wait.until(ExpectedConditions.elementToBeClickable(directionsLocator)).click();
    }

    public boolean isMarkerVisible(String locationKeyword) {
        return driver.getPageSource().toLowerCase().contains(locationKeyword.toLowerCase());
    }
}
