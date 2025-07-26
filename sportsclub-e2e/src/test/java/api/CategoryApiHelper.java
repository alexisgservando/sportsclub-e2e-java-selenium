package api;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

/* Helper class to create categories and sub-categories via the API
 * The method returns the category id  
 */
public class CategoryApiHelper {

    public static String createCategory(String name, String token, boolean isRoot, String parentId) {
        Map<String, Object> body = new HashMap<>();
        body.put("name", name);
        body.put("root", isRoot);
        if (!isRoot && parentId != null) body.put("parentId", parentId);

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(body)
                .post("https://api.club-administration.qa.qubika.com/api/category-type/create");

        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to create category. Status: " + response.statusCode());
        }

        return response.jsonPath().getString("id");
    }
}