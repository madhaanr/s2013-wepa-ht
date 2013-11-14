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

        WebElement loginForm = driver.findElement(By.name("f"));
        loginForm.findElement(By.name("j_username")).sendKeys("nsa");
        loginForm.findElement(By.name("j_password")).sendKeys("nsa");
        loginForm.submit();

        Assert.assertTrue(driver.getPageSource().contains("Search stop timetables!"));
    }

    @Test
    public void loginFailsWithInCorrectDetails() {
        driver.get(baseAddress);
        WebElement element = driver.findElement(By.linkText("Search for timetables"));
        element.click();

        WebElement loginForm = driver.findElement(By.name("f"));
        loginForm.findElement(By.name("j_username")).sendKeys("nsa");
        loginForm.findElement(By.name("j_password")).sendKeys("mmm");
        loginForm.submit();

        Assert.assertTrue(driver.getPageSource().contains("Reason: Wrong username or password!"));
    }

    @Test
    public void signUpPageWorks() {
        driver.get(baseAddress);
        WebElement element = driver.findElement(By.linkText("Signup"));
        element.click();

        WebElement signupForm = driver.findElement(By.id("user"));
        signupForm.findElement(By.id("username")).sendKeys("fifi");
        signupForm.findElement(By.id("password")).sendKeys("guru666");
        signupForm.submit();

        Assert.assertTrue(driver.getPageSource().contains("Signup succeeded!"));
    }

    @Test
    public void newUserCanLogin() {
        driver.get(baseAddress);
        WebElement menuPage = driver.findElement(By.linkText("Signup"));
        menuPage.click();

        WebElement signupForm = driver.findElement(By.id("user"));
        signupForm.findElement(By.id("username")).sendKeys("fifi");
        signupForm.findElement(By.id("password")).sendKeys("guru666");
        signupForm.submit();

        WebElement menu = driver.findElement(By.linkText("Menu"));
        menu.click();

        driver.get(baseAddress);
        WebElement element = driver.findElement(By.linkText("Search for timetables"));
        element.click();

        WebElement loginForm = driver.findElement(By.name("f"));
        loginForm.findElement(By.name("j_username")).sendKeys("fifi");
        loginForm.findElement(By.name("j_password")).sendKeys("guru666");
        loginForm.submit();

        Assert.assertTrue(driver.getPageSource().contains("Search stop timetables!"));

    }

    @Test
    public void searchingByStopName() {
        newUserCanLogin();

        WebElement searchForm = driver.findElement(By.id("searchForm"));
        searchForm.findElement(By.id("stopName")).sendKeys("kuusitie");
        searchForm.submit();

        Assert.assertTrue(driver.getPageSource().contains("Stops with the name used in search"));
        Assert.assertTrue(driver.getPageSource().contains("4720227"));
        Assert.assertTrue(driver.getPageSource().contains("Koivukylän Puistotie"));
        Assert.assertTrue(driver.getPageSource().contains("Mannerheimintie 93"));
        Assert.assertTrue(driver.getPageSource().contains("1180103"));
    }

    @Test
    public void searchingByStopNumber() {
        newUserCanLogin();

        WebElement searchForm = driver.findElement(By.id("searchForm"));
        searchForm.findElement(By.id("stopNumber")).sendKeys("1222");
        searchForm.submit();

        Assert.assertTrue(driver.getPageSource().contains("Information about the Stop"));
        Assert.assertTrue(driver.getPageSource().contains("Fredrikinkatu"));
        Assert.assertTrue(driver.getPageSource().contains("Next ten lines that stop at the Stop"));
        Assert.assertTrue(driver.getPageSource().contains("1040112"));
        Assert.assertTrue(driver.getPageSource().contains("60.16830,24.93123"));
        Assert.assertTrue(driver.getPageSource().contains("Kamppi(M)"));
    }

    @Test
    public void saveSearch() {
    }
    
    @Test
    public void deleteSavedSearch() {
    }

    @Test
    public void savedSearchPersistLogout() {
    }

    @Test
    public void userCanSeeOnlyTheirOwnSavedSearches() {
    }

    @Test
    public void jsonByStopNumberWorks() {
    }

    @Test
    public void jsonByUserWorks() {
    }
}
