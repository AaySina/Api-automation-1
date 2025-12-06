package tests.SportCategory;

import base.BaseTest;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class DeleteSportCategoryByIdTest extends BaseTest {

    private String token;

    @BeforeClass
    public void setup() throws IOException {
        String tokenPath = "src/test/resources/json/token.json";
        Assert.assertTrue(Files.exists(Paths.get(tokenPath)), "token.json tidak ditemukan");

        try (FileReader reader = new FileReader(tokenPath)) {
            JSONObject tokenJson = new JSONObject(new JSONTokener(reader));
            token = tokenJson.getString("token");
        }

        logger.info("Token loaded: {}", token);
    }

    @Test(dependsOnMethods = "tests.SportCategory.CreateSportCategoryTest.createSportCategory")
    public void deleteSportCategoryById() throws Exception {
        String categoryPath = "src/test/resources/json/category_id.json";
        Assert.assertTrue(Files.exists(Paths.get(categoryPath)), "category_id.json tidak ditemukan");

        try (FileReader reader = new FileReader(categoryPath)) {
            JSONObject json = new JSONObject(new JSONTokener(reader));
            int categoryId = json.getInt("category_id");

            logger.info("Category ID: {}", categoryId);

            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .when()
                    .delete("/sport-categories/delete/" + categoryId)
                    .then()
                    .extract().response();

            logger.info("Response:\n{}", response.asString());

            Assert.assertEquals(response.getStatusCode(), 200);
            Assert.assertFalse(response.jsonPath().getBoolean("error"));
            Assert.assertEquals(response.jsonPath().getString("message"), "Data deleted successfully");
        }
    }
}