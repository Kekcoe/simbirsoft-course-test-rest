package co.pokeapi.model.steps;

import co.pokeapi.model.Pikimon;

import static io.restassured.RestAssured.given;

public class Step {

    public String getJson(String pikiName){
        String json=  given()
                .when()
                .get("pokemon/"+ pikiName)
                .then()
                .statusCode(200)
                .extract().asString();
        return json;
    }

    public Pikimon getPikimon(String pikiName){

        return new Pikimon();
    }

}
