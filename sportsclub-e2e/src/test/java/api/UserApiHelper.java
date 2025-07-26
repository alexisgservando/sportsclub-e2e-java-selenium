package api;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * Helper class to create a new user using the /api/auth/register endpoint.
 * The method returns the email, password, and timestamp separated by colons.
 * Example: test123456789@test.com:password123.:123456789
 */
public class UserApiHelper {

    static {
        RestAssured.baseURI = "https://api.club-administration.qa.qubika.com";
    }

	// Method that creates a new user | a timestamp is created and concatenated to the email so that each user is unique
    public static String registerUser() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String email = "user" + timestamp + "@test.com";
        String password = "password123.";

        String requestBody = "{ \"email\": \"" + email + "\", " +
                             "\"password\": \"" + password + "\", " +
                             "\"roles\": [\"ROLE_ADMIN\"] }";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("/api/auth/register");

        if (response.statusCode() != 201) {
            throw new RuntimeException("Failed to register user. Status: " +
                response.statusCode() + " | Response: " + response.getBody().asString());
        }

        return email + ":" + password + ":" + timestamp;
    }
}