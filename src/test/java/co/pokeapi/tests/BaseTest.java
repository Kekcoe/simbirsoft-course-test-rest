package co.pokeapi.tests;

import co.pokeapi.utils.ConfigurationProperties;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected final RequestSpecification requestSpecification = given().baseUri(ConfigurationProperties.getProperty("pikomonpage"));

}
