package tests.auth;

import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import Utils.ConfigReader;

import static io.restassured.RestAssured.given;

public class BasicLoginTest extends BaseTest {

    @Test
    public void loginWithValidCredentials() {
        String requestBody = "{ \"email\":\"" + ConfigReader.getProperty("email") +
                "\", \"password\":\"" + ConfigReader.getProperty("password") + "\" }";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/login")
                .then()
                .extract().response();

        System.out.println("Positive Case Response: " + response.asString());

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 for valid login");
        Assert.assertNotNull(response.jsonPath().getString("data.token"), "Token should not be null");
    }

    @Test
    public void loginWithInvalidPassword() {
        String requestBody = "{ \"email\":\"" + ConfigReader.getProperty("email") +
                "\", \"password\":\"wrongPassword123\" }";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/login")
                .then()
                .extract().response();

        System.out.println("Negative Case Response: " + response.asString());

        Assert.assertEquals(response.getStatusCode(), 404, "Expected 404 Unauthorized for invalid login");
        Assert.assertEquals(response.jsonPath().getString("message"), "Unauthorised.", "Error message mismatch");
    }
}