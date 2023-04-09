package co.pokeapi.tests;

import co.pokeapi.model.Pikimon;
import co.pokeapi.utils.ConfigurationProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected final RequestSpecification requestSpecification = given()
            .baseUri(ConfigurationProperties.getProperty("pikomonpage"))
            .filter(new AllureRestAssured());

    @Step("Create Pikimon")
    protected Pikimon getPikimon(String pikoName) throws JsonProcessingException {
        return requestSpecification
                .get(pikoName).then()
                .assertThat()
                .statusCode(200)
                .extract().as(Pikimon.class);
    }
}