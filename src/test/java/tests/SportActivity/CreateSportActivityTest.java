package tests.SportActivity;

import base.BaseTest;
import body.SportActivity.CreateSportActivityWithParamBody;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Utils.ConfigReader;
import Utils.Utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class CreateSportActivityTest extends BaseTest {

    private String token;

    @BeforeClass
    public void setupToken() throws Exception {
        String tokenPath = "src/test/resources/json/token.json";
        Assert.assertTrue(Files.exists(Paths.get(tokenPath)), "token.json tidak ditemukan. Jalankan LoginTest terlebih dahulu.");

        try (FileReader reader = new FileReader(tokenPath)) {
            JSONObject tokenJson = new JSONObject(new JSONTokener(reader));
            token = tokenJson.getString("token");
        }
        logger.info("Token loaded: {}", token);
    }

    @Test
    public void createSportActivity() throws Exception {
        // Generate body dengan random title
        CreateSportActivityWithParamBody bodyObj = new CreateSportActivityWithParamBody();
        JSONObject requestBody = bodyObj.getBodyCreateWithParam(Utils.generateRandomTitle());

        logger.info("Request Body:\n{}", requestBody.toString(4));

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(requestBody.toString())
                .when()
                .post("/sport-activities/create")
                .then()
                .extract().response();

        logger.info("Response:\n{}", response.asString());

        // Assertion sesuai response API
        Assert.assertEquals(response.getStatusCode(), 200, "Expected 200 OK");
        Assert.assertFalse(response.jsonPath().getBoolean("error"), "Error flag harus false");

        String message = response.jsonPath().getString("message");
        Assert.assertTrue(message.equalsIgnoreCase("data saved") || message.contains("success"),
                "Message tidak sesuai: " + message);

        // Ambil activity_id
        int activityId = response.jsonPath().getInt("result.id");
        logger.info("Activity ID: {}", activityId);

        // Simpan activity_id.json
        JSONObject activityJson = new JSONObject();
        activityJson.put("activity_id", activityId);

        try (FileWriter file = new FileWriter("src/test/resources/json/activity_id.json")) {
            file.write(activityJson.toString(4));
            file.flush();
        }
        logger.info("Activity ID disimpan ke activity_id.json");

        // otomasi buat update_activity.json
        JSONObject updatePayload = new JSONObject();
        updatePayload.put("title", "Updated " + Utils.generateRandomTitle());
        updatePayload.put("description", "Updated description for activity");
        updatePayload.put("activity_date", Utils.getDateAfterFourDays());
        updatePayload.put("location", "Jakarta");
        updatePayload.put("capacity", 50);

        try (FileWriter file = new FileWriter("src/test/resources/json/update_activity.json")) {
            file.write(updatePayload.toString(4));
            file.flush();
        }
        logger.info("update_activity.json otomatis dibuat untuk UpdateSportActivityTest");
    }

    @Test
    public void createSportActivityInvalidTitle() throws Exception {
        // Body dengan title kosong
        CreateSportActivityWithParamBody bodyObj = new CreateSportActivityWithParamBody();
        JSONObject requestBody = bodyObj.getBodyCreateWithParam("");

        logger.info("Request Body (invalid):\n{}", requestBody.toString(4));

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(requestBody.toString())
                .when()
                .post("/sport-activities/create")
                .then()
                .extract().response();

        logger.info("Response (invalid):\n{}", response.asString());

        // Assertion untuk invalid case
        Assert.assertEquals(response.getStatusCode(), 406, "Expected 406 Not Acceptable");
        String message = response.jsonPath().getString("message");
        Assert.assertTrue(message.contains("title") || message.contains("required"),
                "Message tidak sesuai: " + message);
    }
}