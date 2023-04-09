package co.pokeapi.steps;

import co.pokeapi.model.Ability;
import co.pokeapi.model.Pikimon;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;

import java.util.List;

public class StepsForTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Step("check ability")
    public Boolean checkAbility(List<Ability> abilities, String abilityName){
        for (Ability ability :abilities) {
            if(ability.getAbility().getName().equals(abilityName)){
               return true;
            }
        }
        return false;
    }
}