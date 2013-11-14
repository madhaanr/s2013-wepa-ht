package wad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/* @author mhaanran */
public class SeleniumTest {

    private WebDriver driver;
    private String baseAddress;
    
    @Before
    public void setUp() {
        this.driver = new HtmlUnitDriver();
        this.baseAddress = "http://localhost:8080/WepaHT/app";
    }
    
    @Test
    public void menuPageIsAtBaseAddress() {
        driver.get(baseAddress);
        Assert.assertTrue(driver.getPageSource().contains("Menu"));
    }
    
    @Test
    public void searchForTimetablesLinkRedirectsToLogin() {
        driver.get(baseAddress);
        WebElement element = driver.findElement(By.linkText("Search for timetables"));
        element.click();
        Assert.assertTrue(driver.getPageSource().contains("Login with Username and Password"));
    }
    
    @Test
    public void loginWorks() {
        driver.get(baseAddress);
        WebElement element = driver.findElement(By.linkText("Search for timetables"));
        element.click();
        WebElement loginPage = driver.findElement(By.name("f"));
        loginPage.findElement(By.name("j_username")).sendKeys("nsa");
        loginPage.findElement(By.name("j_password")).sendKeys("nsa");
        loginPage.submit();
        
        Assert.assertTrue(driver.getPageSource().contains("Search stop timetables!"));
    }
    
    @Test
    public void signUpPageWorks() {
        
    }
    
    @Test
    public void newUserCanLogin() {
        
    }
    
    @Test
    public void jsonByStopNumberWorks() {
        
    }
    
    @Test
    public void jsonByUserWorks() {
        
    }
    
    @Test
    public void searchingByStopName() {
        
    }
    
    @Test
    public void searchingByStopNumber() {
        
    }
    
    @Test
    public void savingSearch() {
        
    }
    
    @Test
    public void savedSearchesPersist() {
        
    }
    
    @Test
    public void userCanSeeOnlyTheirOwnSavedSearches() {
        
    }
    
}
