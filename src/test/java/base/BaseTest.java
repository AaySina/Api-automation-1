package base;

import Utils.ConfigReader;
import io.restassured.RestAssured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class BaseTest {
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeClass
    public void setup() throws Exception {
        RestAssured.baseURI = ConfigReader.getProperty("baseUrl");
        logger.info("Base URI set to: {}", RestAssured.baseURI);
    }

    @AfterClass
    public void tearDown() {
        RestAssured.reset();
        logger.info("RestAssured configuration reset.");
    }
}