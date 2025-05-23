package tests;

import base.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Util.TestUtil;
import pages.HomePage;

public class GeoMapTests extends BaseTest {

	@Test(priority = 1)
	public void testMapLoading() {
	    TestUtil.createTest("Test 1 - Verify Google Map Load");
	    HomePage home = new HomePage(driver);
	    Assert.assertTrue(home.isMapLoaded(), "Map did not load properly.");
	    TestUtil.logPass("Google Map loaded successfully.");
	}

    

	@Test(priority = 2)
	public void testZoomControls() throws InterruptedException {
	    TestUtil.createTest("Test 2 - Zoom In and Zoom Out Functionality");
	    HomePage home = new HomePage(driver);
	    home.zoomIn();
	    Thread.sleep(3000);
	    home.zoomOut();
	    TestUtil.logPass("Zoom In and Zoom Out actions performed successfully.");
	}


    @Test(priority = 3)
    public void testSearchLocation() throws InterruptedException {
        TestUtil.createTest("Test 3 - Search for Location (Bengaluru)");
        HomePage home = new HomePage(driver);
        
        home.searchLocation("Bengaluru");
        Thread.sleep(3000);
        
        Assert.assertTrue(home.isMarkerVisible("Bengaluru"), "Marker for location not visible.");
        TestUtil.logPass("Bengaluru location marker is visible.");
    }

    @Test(priority = 4)
    public void testMarkerValidation() throws InterruptedException {
        TestUtil.createTest("Test 4 - Marker Validation (Bengaluru)");
        HomePage home = new HomePage(driver);

        home.searchLocation("Bengaluru");
        Thread.sleep(3000);

        Assert.assertTrue(home.isMarkerVisible("Bengaluru"), "Marker for location not found.");
        TestUtil.logPass("Bengaluru location marker is visible.");
    }


    @Test(priority = 5)
    public void testRouteSearch() throws InterruptedException {
        TestUtil.createTest("Test 5 - Route Search from New York to Boston");
        HomePage home = new HomePage(driver);
        home.openDirections();
        Assert.assertTrue(home.isMarkerVisible("Bengaluru"), "Marker for location not found.");
        TestUtil.logPass("Bengaluru location marker is visible.");
        
    }


    }
    
