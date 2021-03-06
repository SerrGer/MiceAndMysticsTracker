package com.eyecreate.miceandmystics.miceandmystics.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

import java.util.UUID;

public class Ability extends RealmObject {
    @PrimaryKey
    @Required
    private String uuid;
    @Required
    private String abilityName;

    public Ability() {}

    public Ability(String abilityName) {setAbilityName(abilityName);setUuid(UUID.randomUUID().toString());}

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
