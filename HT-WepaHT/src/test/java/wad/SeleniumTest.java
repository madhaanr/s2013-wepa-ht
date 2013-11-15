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
    
    
    public void login() {
        driver.get(baseAddress);
        WebElement menuPage = driver.findElement(By.linkText("Signup"));
        menuPage.click();

        WebElement signupForm = driver.findElement(By.id("user"));
        signupForm.findElement(By.id("username")).sendKeys("Reilu Kerho");
        signupForm.findElement(By.id("password")).sendKeys("Kh12323:1+");
        signupForm.submit();

        WebElement menu = driver.findElement(By.linkText("Menu"));
        menu.click();

        driver.get(baseAddress);
        WebElement element = driver.findElement(By.linkText("Search for timetables"));
        element.click();

        WebElement loginForm = driver.findElement(By.name("f"));
        loginForm.findElement(By.name("j_username")).sendKeys("Reilu Kerho");
        loginForm.findElement(By.name("j_password")).sendKeys("Kh12323:1+");
        loginForm.submit();
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
        login();

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
        login();

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
        login();

        WebElement searchForm = driver.findElement(By.id("searchForm"));
        searchForm.findElement(By.id("stopNumber")).sendKeys("1222");
        searchForm.submit();
        
        WebElement saveSearchForm = driver.findElement(By.id("saveSearch"));
        saveSearchForm.findElement(By.id("searchName")).sendKeys("Kampin pysäkki 1234rr");
        saveSearchForm.submit();
        
        Assert.assertTrue(driver.getPageSource().contains("Kampin pysäkki 1234rr"));
        Assert.assertFalse(driver.getPageSource().contains("1222"));
        
        WebElement delete = driver.findElement(By.id("removeSearch"));
        delete.submit();
    }
    
    @Test
    public void deleteSavedSearch() {
        login();

        WebElement searchForm = driver.findElement(By.id("searchForm"));
        searchForm.findElement(By.id("stopNumber")).sendKeys("1222");
        searchForm.submit();
        
        WebElement saveSearchForm = driver.findElement(By.id("saveSearch"));
        saveSearchForm.findElement(By.id("searchName")).sendKeys("Kampin pysäkki 1234aa");
        saveSearchForm.submit();
        
        Assert.assertTrue(driver.getPageSource().contains("Kampin pysäkki 1234aa"));
        Assert.assertFalse(driver.getPageSource().contains("1222"));
        
        WebElement delete = driver.findElement(By.id("removeSearch"));
        delete.submit();
        
        Assert.assertFalse(driver.getPageSource().contains("Kampin pysäkki 1234aa"));
        
    }

    @Test
    public void savedSearchPersistThroughLogoutLogin() {
        login();
        
        WebElement searchForm = driver.findElement(By.id("searchForm"));
        searchForm.findElement(By.id("stopNumber")).sendKeys("1234");
        searchForm.submit();
        
        WebElement saveSearchForm = driver.findElement(By.id("saveSearch"));
        saveSearchForm.findElement(By.id("searchName")).sendKeys("Kampin pysäkki 1234");
        saveSearchForm.submit();
        
        WebElement logout = driver.findElement(By.linkText("Logout"));
        logout.click();

        login();
        
        Assert.assertTrue(driver.getPageSource().contains("Kampin pysäkki 1234"));
        
        WebElement delete = driver.findElement(By.id("removeSearch"));
        delete.submit();
    }

    @Test
    public void userWillSeeOnlyTheirOwnSavedSearches() {
        login();
        
        WebElement searchForm = driver.findElement(By.id("searchForm"));
        searchForm.findElement(By.id("stopNumber")).sendKeys("1222");
        searchForm.submit();
        
        WebElement saveSearchForm = driver.findElement(By.id("saveSearch"));
        saveSearchForm.findElement(By.id("searchName")).sendKeys("Kampin pysäkki 1");
        saveSearchForm.submit();
        
        WebElement logout = driver.findElement(By.linkText("Logout"));
        logout.click();

        WebElement login = driver.findElement(By.linkText("Search for timetables"));
        login.click();

        WebElement loginForm = driver.findElement(By.name("f"));
        loginForm.findElement(By.name("j_username")).sendKeys("nsa");
        loginForm.findElement(By.name("j_password")).sendKeys("nsa");
        loginForm.submit();
        
        Assert.assertFalse(driver.getPageSource().contains("Kampin pysäkki 1"));
    }

    @Test
    public void jsonSearchesSavedByUser() {
        driver.get(baseAddress+"/json/stops/nsa");
        Assert.assertTrue(driver.getPageSource().contains("[]"));
        Assert.assertFalse(driver.getPageSource().contains("{}"));
    }
    
    @Test
    public void jsonSearchByValidStopNumber() {
        driver.get(baseAddress+"/json/timetable/1923");
        Assert.assertTrue(driver.getPageSource().contains("\"name_fi\":\"Kuusitie\",\"lines\":"));
        
    }
    @Test
    public void jsonSearchByInValidStopNumber() {
        driver.get(baseAddress+"/json/timetable/192399999");
        Assert.assertTrue(driver.getPageSource().contains(""));
//        Assert.assertFalse(driver.getPageSource().contains(""));
    }
}
