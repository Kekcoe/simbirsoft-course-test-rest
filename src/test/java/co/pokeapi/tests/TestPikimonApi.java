package co.pokeapi.tests;

import co.pokeapi.model.Pikimon;
import co.pokeapi.steps.StepsForTest;
import co.pokeapi.utils.ConfigurationProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

@Epic("Test https://pokeapi.co/")
public class TestPikimonApi extends BaseTest {
    private static Pikimon pidgeotto;
    private static Pikimon rattata;
    private static final StepsForTest stepsForTest = new StepsForTest();

    @BeforeEach
    void setUp() {
        try {
            pidgeotto = getPikimon(ConfigurationProperties.getProperty("pidgeotto"));
            rattata = getPikimon(ConfigurationProperties.getProperty("rattata"));
        } catch (JsonProcessingException e) {
            System.out.println("Error processing JSON: " + e.getMessage());
        }
    }

    @Test
    @Step("testName")
    void testName() {
        Assertions.assertEquals("pidgeotto", pidgeotto.getName(), "Name of Pidgeotto does not match");
        Assertions.assertEquals("rattata", rattata.getName(), "Name of Rattata does not match");
    }

    @Test
    @Description("Проверить, что у покемона rattata, в отличие от покемона pidgeotto, меньше вес")
    @Step("testWeight")
    void ComparisionWeightTest() {
        Assertions.assertTrue(rattata.getWeight() < pidgeotto.getWeight(), "weight of Rattata more than Pidgeotto");
    }

    @Test
    @Description("Проверить, что у покемона rattata, в отличие от покемона pidgeotto есть умение\n" +
            "(ability) побег (run-away)")
    @Step("testAbility")
    void abilityRunAwayTest() {
        Boolean isPidgeottoHas = stepsForTest.checkAbility(pidgeotto.getAbilities(), "run-away");
        Boolean isRattataHas = stepsForTest.checkAbility(rattata.getAbilities(), "run-away");
        Assertions.assertFalse(isPidgeottoHas, "Pidgeoto has run-away");
        Assertions.assertTrue(isRattataHas, "Rattata hasnt run-away");
    }

    @Test
    @Description("Проверить ограничение списка (limit) покемонов и то, что у каждого покемона в\n" +
            "ограниченном списке есть имя (name)")
    @Step("pikimonListTest")
    void pikimonListTest() {
        Response response = requestSpecification.get();
        int countOfPikimons = response.jsonPath().getList("results").size();
        List<String> pokemonNames = response.jsonPath().getList("results.name");
        pokemonNames.removeAll(Collections.singleton(null));
        Assertions.assertEquals(20, countOfPikimons, "List of pikimons must contains 20 pics");
        Assertions.assertEquals(20, pokemonNames.size(), "not all Pikemon have a name");
    }

    @Step("Create Pikimon")
    private Pikimon getPikimon(String pikoName) throws JsonProcessingException {
        String jsonPikimon = requestSpecification
                .get(pikoName).then()
                .assertThat()
                .statusCode(200)
                .extract().asString();
        Allure.addAttachment("jsonPikimon", "application/json", jsonPikimon);
        return stepsForTest.getPikimon(jsonPikimon);
    }
}