package tests.SportActivity;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Utils.ConfigReader;

import java.io.FileReader;

import static io.restassured.RestAssured.given;

public class DeleteSportActivityByIdTest {

    private String token;

    @BeforeClass
    public void setup() throws Exception {
        RestAssured.baseURI = ConfigReader.getProperty("baseUrl");

        FileReader reader = new FileReader("src/test/resources/json/token.json");
        JSONObject tokenJson = new JSONObject(new JSONTokener(reader));
        token = tokenJson.getString("token");
        reader.close();

        System.out.println("Token loaded: " + token);
    }

    @Test
    public void deleteSportActivityById() throws Exception {
        FileReader reader = new FileReader("src/test/resources/json/activity_id.json");
        JSONObject json = new JSONObject(new JSONTokener(reader));
        reader.close();

        int activityId = json.getInt("activity_id");
        System.out.println("Activity ID: " + activityId);

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .delete("/sport-activities/delete/" + activityId)
                .then()
                .extract().response();

        System.out.println("Response: " + response.asString());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertFalse(response.jsonPath().getBoolean("error"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Data deleted successfully");
    }
}