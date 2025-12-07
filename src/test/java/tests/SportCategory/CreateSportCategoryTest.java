package tests.SportCategory;

import body.SportCategory.CreateSportCategoryWithParamBody;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Utils.ConfigReader;
import Utils.Utils;

import java.io.FileReader;
import java.io.FileWriter;

import static io.restassured.RestAssured.given;

public class CreateSportCategoryTest {

    private String token;

    @BeforeClass
    public void setup() throws Exception {
        RestAssured.baseURI = ConfigReader.getProperty("baseUrl");

        try (FileReader reader = new FileReader("src/test/resources/json/token.json")) {
            JSONObject tokenJson = new JSONObject(new org.json.JSONTokener(reader));
            token = tokenJson.getString("token");
        }
    }

    @Test
    public void createSportCategory() throws Exception {
        CreateSportCategoryWithParamBody bodyObj = new CreateSportCategoryWithParamBody();
        JSONObject requestBody = bodyObj.getBodyCreateCategoryWithParam(Utils.generateRandomTitle());

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(requestBody.toString())
                .when()
                .post("/sport-categories/create/")
                .then()
                .extract().response();

        System.out.println("Response: " + response.asString());

        Assert.assertEquals(response.getStatusCode(), 200, "Expected 200 Created");
        Assert.assertFalse(response.jsonPath().getBoolean("error"));
        Assert.assertEquals(response.jsonPath().getString("message"), "data saved");

        String category_id = response.jsonPath().getString("result.id");
        System.out.println("Category ID: " + category_id);

        JSONObject categoryJson = new JSONObject();
        categoryJson.put("category_id", category_id);

        try (FileWriter file = new FileWriter("src/test/resources/json/category_id.json")) {
            file.write(categoryJson.toString(4));
            file.flush();
        }
    }

    @Test
    public void createSportCategoryInvalidName() throws Exception {
        CreateSportCategoryWithParamBody bodyObj = new CreateSportCategoryWithParamBody();
        JSONObject requestBody = bodyObj.getBodyCreateCategoryWithParam("");

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(requestBody.toString())
                .when()
                .post("/sport-categories/create")
                .then()
                .extract().response();

        System.out.println("Response: " + response.asString());

        Assert.assertEquals(response.getStatusCode(), 406);
        Assert.assertEquals(response.jsonPath().getString("message"), "The name field is required.");
    }
}