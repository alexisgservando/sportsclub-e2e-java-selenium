# Sports Club Management - E2E Automation

This project implements an end-to-end (E2E) test automation flow that combines **API** and **UI testing** using:
- **Selenium WebDriver**
- **RestAssured**
- **TestNG**

The goal is to validate key features of the [Qubika Sports Club Management System](https://club-administration.qa.qubika.com), such as user creation, login, and category management.

---

## What This Project Does

This test suite executes the following automated workflow:

1. Creates a new user using the **API** (`/api/auth/register`)
2. Opens the **Login page** and checks for proper display
3. Logs in via the **UI** with the created user
4. Verifies successful login by asserting the user reaches the **Dashboard**
5. Creates a **new root category** via the **API**
6. Opens the **"Tipos de Categorías"** module via the **UI**
7. Creates a **sub-category** using the **UI** and assigns the previously created root category as its parent
8. Validates that the sub-category was submitted correctly

---

## Tech Stack

| Tool         | Description                             |
|--------------|-----------------------------------------|
| Java         | Java SE 23.0.2 (Oracle)                 |
| Selenium     | Browser automation                      |
| RestAssured  | API testing                             |
| TestNG       | Test framework                          |
| Maven        | Project build tool (v3.9.11)            |
| Browser      | Google Chrome (v138.0.7204.169)         |
| OS           | Windows 11 (64-bit)                     |

> Platform encoding: UTF-8  
> Maven home: `C:\Program Files\Apache\Maven\apache-maven-3.9.11`  
> Java home: `C:\Program Files\Java\jdk-23`

---

## Project Structure

src/
├── main/
│ └── java/
│ ├── api/
│ │ ├── UserApiHelper.java
│ │ ├── TokenHelper.java
│ │ └── CategoryApiHelper.java
│ └── pages/
│ ├── LoginPage.java
│ ├── DashboardPage.java
│ └── CategoryPage.java
├── test/
│ └── java/
│ └── tests/
│ └── E2EFlowTest.java

---

## Enhancements Made

- **Timestamp**-based user and category names to ensure test data uniqueness
- Used **GSON** as the JSON serializer for compatibility with RestAssured
- Added **explicit waits** (WebDriverWait) for more reliable UI automation
- Included **console logging** at key steps to track test flow
- Clean **Page Object Model** structure for maintainability

---

## Prerequisites

- Java 17 or higher (you are using Java 23.0.2)
- Maven 3.9+
- Chrome browser installed
- Internet access (tests run on Qubika’s QA environment)

---

## How to Run the Tests

1. **Clone the repository** (replace with your URL):
   ```bash
   git clone https://github.com/alexisgservando/sportsclub-e2e-java-selenium.git
   cd sportsclub-e2e-java-selenium
2. Open the project in Eclipse and wait for Maven to resolve dependencies.
3. Navigate to the sportsclub-e2e/src/test/java/tests/E2EFlowTest.java file.
4. Right-click the E2EFlowTest.java class → Run As → TestNG Test

## Notes
1. The test uses a modular helper structure to keep API and UI logic separated
2. Every user and category is automatically unique to avoid conflicts in repeated executions
3. You may ignore SLF4J warnings; they do not affect test execution