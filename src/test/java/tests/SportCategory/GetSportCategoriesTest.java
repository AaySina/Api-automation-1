package tests.SportCategory;

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

public class GetSportCategoriesTest {

    private String token;

    @BeforeClass
    public void setup() throws Exception {
        RestAssured.baseURI = ConfigReader.getProperty("baseUrl");

        try (FileReader reader = new FileReader("src/test/resources/json/token.json")) {
            JSONObject tokenJson = new JSONObject(new JSONTokener(reader));
            token = tokenJson.getString("token");
        }
        System.out.println("Token loaded: " + token);
    }

    @Test
    public void getSportCategories() throws Exception {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .get("/sport-categories?is_paginate=true&per_page=10&page=1")
                .then()
                .extract().response();

        System.out.println("Response: " + response.asString());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertFalse(response.jsonPath().getBoolean("error"));

        // Validasi list categories tidak kosong
        int size = response.jsonPath().getList("result.data").size();
        Assert.assertTrue(size > 0, "Expected at least one category in result");
    }
}