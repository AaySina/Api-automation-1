package tests.auth;

import base.BaseTest;
import body.auth.LoginBody;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import Utils.ConfigReader;

import java.io.FileWriter;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class LoginTest extends BaseTest {

    @Test
    public void loginAndSaveToken() throws IOException {
        LoginBody loginBody = new LoginBody(
                ConfigReader.getProperty("email"),
                ConfigReader.getProperty("password")
        );

        Response response = given()
                .header("Content-Type", "application/json")
                .body(loginBody.loginData().toString())
                .when()
                .post("/login")
                .then()
                .extract().response();

        System.out.println("Response: " + response.asString());

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 for valid login");

        String token = response.jsonPath().getString("data.token");
        Assert.assertNotNull(token, "Token should not be null");
        Assert.assertFalse(token.isEmpty(), "Token should not be empty");

        String message = response.jsonPath().getString("message");
        Assert.assertEquals(message, "User login successfully.", "Message mismatch");

        JSONObject tokenJson = new JSONObject();
        tokenJson.put("token", token);

        try (FileWriter file = new FileWriter("src/test/resources/json/token.json")) {
            file.write(tokenJson.toString(4));
            file.flush();
        }

        System.out.println("Token berhasil disimpan di src/test/resources/json/token.json");
    }
}