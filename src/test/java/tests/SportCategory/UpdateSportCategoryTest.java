package tests.SportCategory;

import body.SportCategory.UpdateSportCategoryWithParamBody;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import Utils.ConfigReader;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class UpdateSportCategoryTest {

    @Test
    public void updateSportCategory() throws Exception {
        RestAssured.baseURI = ConfigReader.getProperty("baseUrl");

        // Ambil token dari file token.json
        String tokenContent = new String(Files.readAllBytes(Paths.get("src/test/resources/json/token.json")));
        JSONObject tokenJson = new JSONObject(tokenContent);
        String token = tokenJson.getString("token");

        // Ambil category_id dari file category_id.json
        String categoryContent = new String(Files.readAllBytes(Paths.get("src/test/resources/json/category_id.json")));
        JSONObject categoryJson = new JSONObject(categoryContent);
        int categoryId = categoryJson.getInt("category_id");

        Assert.assertTrue(categoryId > 0, "Invalid category ID: " + categoryId);

        // Buat body update dengan nama kategori baru
        UpdateSportCategoryWithParamBody bodyHelper = new UpdateSportCategoryWithParamBody();
        JSONObject requestBody = bodyHelper.UpdateSportCategoryWithParam("Updated Category Name");

        System.out.println("Request Body: " + requestBody.toString(4));

        // Kirim request POST (sesuai kontrak API Laravel)
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(requestBody.toString())
                .when()
                .post("/sport-categories/update/" + categoryId)
                .then()
                .extract().response();

        System.out.println("Response: " + response.asString());
        System.out.println("Category ID: " + categoryId);

        // Validasi
        Assert.assertEquals(response.getStatusCode(), 200, "Status code tidak sesuai");
        Assert.assertEquals(response.jsonPath().getString("message"), "data saved", "Message tidak sesuai");
        Assert.assertFalse(response.jsonPath().getBoolean("error"), "Error flag harus false");

        // Validasi tambahan jika API mengembalikan data kategori
        if (response.jsonPath().get("data") != null) {
            Assert.assertEquals(response.jsonPath().getInt("data.id"), categoryId, "Category ID tidak sesuai");
            Assert.assertEquals(response.jsonPath().getString("data.name"), "Updated Category Name", "Nama kategori tidak sesuai");
        }
    }
}