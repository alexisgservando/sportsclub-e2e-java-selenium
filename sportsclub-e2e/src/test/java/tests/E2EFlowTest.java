package tests;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import api.CategoryApiHelper;
import api.TokenHelper;
import api.UserApiHelper;
import pages.CategoryPage;
import pages.DashboardPage;

/**
 * End-to-end test class covering the following items:
 * 1. UI Login and asserting the script has reached the Dashboard screen
 * 2. Category and Sub-category creation via API
 * 3. Adicionar tipo de categoria modal window submitted via the UI
 */

public class E2EFlowTest {
    private WebDriver driver;
    private String email;
    private String password;
    private String token;
    private String timestamp;

    @BeforeClass
    public void setup() {
        // Launch browser
        driver = new ChromeDriver();

        // Create a new user via the API and extract credentials
        String[] credentials = UserApiHelper.registerUser().split(":");
        email = credentials[0];
        password = credentials[1];
        timestamp = credentials[2]; 

        // Generate the JWT token using the new credentials
        token = TokenHelper.getToken(email, password);
    }

    @Test(priority = 1)
    public void testLoginAndDashboardAccess() {
        // Navigate to the login page
        driver.get("https://club-administration.qa.qubika.com/#/login");

        //  Wait for email field to load before interacting
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[formcontrolname='email']")));

        // Enter login credentials and submit
        driver.findElement(By.cssSelector("input[formcontrolname='email']")).sendKeys(email);
        driver.findElement(By.cssSelector("input[formcontrolname='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[contains(text(),'Autenticar')]")).click();

        // Verify login success by checking the Dashboard screen is loaded
        DashboardPage dashboard = new DashboardPage(driver);
        Assert.assertTrue(dashboard.isAtDashboard(), "User is not at dashboard");
    }

    @Test(priority = 2)
    public void testCreateCategoryAndSubcategory() {
        // Create a category via API
    	String parentCategoryName = "TestCategoryRoot_" + timestamp;
    	String subCategoryName = "TestCategoryChild_" + timestamp;
        CategoryApiHelper.createCategory(parentCategoryName, token, true, null);
        System.out.println("Parent category '" + parentCategoryName + "' created via API");

        // Navigate to category screen
        CategoryPage categoryPage = new CategoryPage(driver);
        categoryPage.goToCategoryScreen();

        // Waiting for the 'Adicionar' button and opening the modal window
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Adicionar')]")));
        categoryPage.openAddCategoryModal();
        System.out.println("Opened modal to create the sub-category");

        // Fill and submit sub-category form
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("input-username")));
        categoryPage.fillCategoryForm(subCategoryName, true, parentCategoryName);
        categoryPage.submitCategory();
        System.out.println("Sub-category form submitted, waiting for UI to finish processing.");

        //Wait for processing indicator to disappear
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Procesando...')]")));
        System.out.println("Sub-category '" + subCategoryName + "' submitted successfully");
    }

    @AfterClass
    public void teardown() {
        // Close browser
        if (driver != null) {
            driver.quit();
        }
    }
}
