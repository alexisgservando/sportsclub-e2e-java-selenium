package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

/*
 * Page Object for interacting with the "Tipos de Categorias" module.
 */
public class CategoryPage {
	// Declare the WebDriver
	private WebDriver driver;

	// Locators
	private By categoryMenu = By.xpath("//a[normalize-space(text())='Tipos de Categorias']");
	private By addButton = By.xpath("//button[contains(text(), 'Adicionar')]");
	private By categoryInput = By.id("input-username");
	private By isSubCategoryCheckboxLabel = By.cssSelector("label[for='customCheckMain']");
	private By parentDropdown = By.className("ng-select-container");
	private By dropdownOptions = By.className("ng-option");
	private By acceptButton = By.xpath("//button[contains(text(),'Aceptar')]");

	// Constructor
	public CategoryPage(WebDriver driver) {
		this.driver = driver;
	}

	// Method to navigate to the "Tipos de Categorias" screen
	public void goToCategoryScreen() {
		driver.findElement(categoryMenu).click();
	}

	// Method to open the "Adicionar tipo de categoria" modal window
	public void openAddCategoryModal() {
		driver.findElement(addButton).click();
	}

	// Fill in the form to add a category or sub-category in the "Adicionar tipo de categoria" modal window
	public void fillCategoryForm(String name, boolean isSubCategory, String parentName) {
		driver.findElement(categoryInput).sendKeys(name);
		if (isSubCategory) {
			driver.findElement(isSubCategoryCheckboxLabel).click();
			driver.findElement(parentDropdown).click();
			List<WebElement> options = driver.findElements(dropdownOptions);
			for (WebElement option : options) {
				if (option.getText().contains(parentName)) {
					option.click();
					break;
				}
			}
		}
	}

	// Method to click on the "Aceptar" button on the "Adicionar tipo de categoria" modal window
	public void submitCategory() {
		driver.findElement(acceptButton).click();
	}
}
