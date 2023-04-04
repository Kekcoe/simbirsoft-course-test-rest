package co.pokeapi.model;

public class Ability {

    private Ability1 ability;
    private boolean is_hidden;
    private Integer slot;

    public Ability1 getAbility() {
        return ability;
    }
    public void setAbility(Ability1 ability) {
        this.ability = ability;
    }

    public boolean isIs_hidden() {
        return is_hidden;
    }

    public void setIs_hidden(boolean is_hidden) {
        this.is_hidden = is_hidden;
    }

    public Integer getSlot() {
        return slot;
    }
    public void setSlot(Integer slot) {
        this.slot = slot;
    }
}