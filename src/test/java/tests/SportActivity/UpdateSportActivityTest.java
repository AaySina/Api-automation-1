package tests.SportActivity;

import base.BaseTest;
import body.SportActivity.UpdateSportActivityBody;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import Utils.Utils;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class UpdateSportActivityTest extends BaseTest {

    @Test(dependsOnMethods = "tests.SportActivity.CreateSportActivityTest.createSportActivity")
    public void updateSportActivityFromJson() throws Exception {
        // Load token
        String tokenPath = "src/test/resources/json/token.json";
        Assert.assertTrue(Files.exists(Paths.get(tokenPath)), "token.json tidak ditemukan");
        JSONObject tokenJson = new JSONObject(Files.readString(Paths.get(tokenPath)));
        String token = tokenJson.getString("token");

        // Load activity ID
        String activityPath = "src/test/resources/json/activity_id.json";
        Assert.assertTrue(Files.exists(Paths.get(activityPath)), "activity_id.json tidak ditemukan");
        JSONObject activityJson = new JSONObject(Files.readString(Paths.get(activityPath)));
        int activityId = activityJson.getInt("activity_id");

        // Generate tanggal sesuai format API (yyyy-MM-dd)
        String activityDate = Utils.getDateAfterSevenDays();

        // Build request body sesuai dokumentasi API
        JSONObject requestBody = new JSONObject();
        requestBody.put("sport_category_id", 79);
        requestBody.put("city_id", 3172);
        requestBody.put("title", "Updated " + Utils.generateRandomTitle());
        requestBody.put("description", "Updated description for activity");
        requestBody.put("slot", 9);
        requestBody.put("price", 70000);
        requestBody.put("address", "Lapangan Revo, Jakarta Timur");
        requestBody.put("activity_date", activityDate);
        requestBody.put("start_time", "06:00");
        requestBody.put("end_time", "07:00");
        requestBody.put("map_url", "https://maps.app.goo.gl/h1AV4bfB2cojJMxK7");

        logger.info("Request Body:\n{}", requestBody.toString(4));

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(requestBody.toString())
                .when()
                .post("/sport-activities/update/" + activityId)
                .then()
                .extract().response();

        logger.info("Response:\n{}", response.asString());

        // Assertion sesuai kontrak API
        Assert.assertEquals(response.getStatusCode(), 200, "Status code tidak sesuai");
        Assert.assertFalse(response.jsonPath().getBoolean("error"), "Error flag harus false");
        Assert.assertEquals(response.jsonPath().getString("message"), "data saved", "Message tidak sesuai");
        Assert.assertEquals(response.jsonPath().getInt("result.id"), activityId);
    }
}
