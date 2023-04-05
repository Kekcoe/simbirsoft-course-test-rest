package co.pokeapi.tests;

import co.pokeapi.model.Pikimon;
import co.pokeapi.steps.StepsForTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPikimonApi extends BaseTest {
    private static Pikimon pidgeotto;
    private static Pikimon rattata;
    private static final StepsForTest stepsForTest = new StepsForTest();

    @BeforeEach
    void setUp() {
        try {
            pidgeotto = getPikimon(PIDGEOTTO);
            rattata = getPikimon(RATTATA);
        } catch (JsonProcessingException e) {
            System.out.println("Error processing JSON: " + e.getMessage());
        }
    }

    @Test
    void testName() {
        String expectedNamePidgeotto = PIDGEOTTO;
        String expectedNameRATTATA = RATTATA;
        Assertions.assertEquals(expectedNamePidgeotto, pidgeotto.getName(), "Name of Pidgeotto does not match");
        Assertions.assertEquals(expectedNameRATTATA, rattata.getName(), "Name of Rattata does not match");
    }

    @Test
    void ComparisionWeightTest() {
        Assertions.assertTrue(rattata.getWeight() < pidgeotto.getWeight(),"weight of Rattata more than Pidgeotto");
    }

    @Test
    void abilityRunAwayTest(){
        Assertions.assertFalse(pidgeotto.getAbilities().contains("run-away"), "Pidgeoto has run-away");
        Assertions.assertFalse(rattata.getAbilities().contains("run-away"),"Rattata hasnt run-away" );
    }

    @Test
    void pikimonListTest(){
        String countOfPikimons =  requestSpecification.get().then().extract().path("count").toString();
        System.out.println(countOfPikimons);
    }

    private Pikimon getPikimon(String pikoName) throws JsonProcessingException {
        String jsonPikimon = requestSpecification
                .get(pikoName).then()
                .assertThat()
                .statusCode(200)
                .extract().asString();
        return stepsForTest.getPikimon(jsonPikimon);
    }

}