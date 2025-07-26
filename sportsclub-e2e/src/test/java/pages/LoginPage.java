package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	// Declare the WebDriver
	WebDriver driver; 
	
	//Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	// Navigate to the Login page
	public void goToLoginPage() { 
		driver.get("https://club-administration.qa.qubika.com/#/auth/login");
	}
	
	// Fill the login form and submit
	public void login(String email, String password) {
		// Email field by name
        driver.findElement(By.name("email")).sendKeys(email);
        // Password field by name
        driver.findElement(By.name("password")).sendKeys(password);
        // Click login button using visible text
        driver.findElement(By.xpath("//button[contains(text(), 'Autenticar')]")).click();
	}
}
