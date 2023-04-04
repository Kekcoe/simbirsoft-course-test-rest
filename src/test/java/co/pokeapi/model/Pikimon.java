package co.pokeapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pikimon {
    Map<String, Object> abilities;

    public Pikimon() {}

    public Map<String, Object> getAbilities() {
        return abilities;
    }

    public void setAbilities(Map<String, Object> abilities) {
        this.abilities = abilities;
    }
}
