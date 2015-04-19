package com.eyecreate.miceandmystics.miceandmystics.model;

import io.realm.RealmObject;

public class Ability extends RealmObject {
    private String abilityName;

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }
}
