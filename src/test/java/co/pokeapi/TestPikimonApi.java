package co.pokeapi;

import co.pokeapi.model.Pikimon;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TestPikimonApi {

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void ComparePikimonsTest() throws IOException {
        RestAssured.baseURI = "https://pokeapi.co/api/v2/";
        String json=  given()
                .when()
                .get("pokemon/pidgeotto")
                .then()
               .assertThat()
               .statusCode(200)
                .extract().asString();

      Pikimon pidgeotto = objectMapper.readValue(json, Pikimon.class);

        System.out.println(pidgeotto.getAbilities().get(0).getAbility().getName());
    }


}
