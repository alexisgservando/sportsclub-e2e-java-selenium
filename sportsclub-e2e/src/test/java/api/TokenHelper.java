package api;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

/* Helper class to capture the JWT token upon logging in with the user credentials
 * The method returns the token 
 */
public class TokenHelper {
    public static String getToken(String email, String password) {
        String requestBody = "{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("https://api.club-administration.qa.qubika.com/api/auth/login");

        if (response.statusCode() != 200) {
            throw new RuntimeException("Login failed: " + response.statusCode());
        }

        return response.jsonPath().getString("token");
    }
}