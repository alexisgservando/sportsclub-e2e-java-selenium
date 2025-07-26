package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page Object for the Dashboard page. Used to verify successful login.
 */
public class DashboardPage {
	// Declare the WebDriver
	private WebDriver driver;

    // Locators
    private By dashboardLink = By.cssSelector("a.nav-link[href='#/dashboard']");

    // Constructor
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to validate that the script has reached the Dashboard screen
    public boolean isAtDashboard() {
        try {
            System.out.println("Waiting for dashboard link: 'Dashboard'");
            new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(dashboardLink));
            System.out.println("Dashboard link is visible!");
            return true;
        } catch (TimeoutException e) {
            System.out.println("Dashboard not detected within expected time.");
            return false;
        }
    }
}
