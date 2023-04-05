package co.pokeapi.steps;

import co.pokeapi.model.Pikimon;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StepsForTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Pikimon getPikimon(String jsonPiki) throws JsonProcessingException {
        return objectMapper.readValue(jsonPiki, Pikimon.class);
    }

}