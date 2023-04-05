package co.pokeapi.tests;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseTest {
    protected final static String PIDGEOTTO = "pidgeotto";
    protected final static String RATTATA = "rattata";

    protected final RequestSpecification requestSpecification = given().baseUri("https://pokeapi.co/api/v2/pokemon/");

}
