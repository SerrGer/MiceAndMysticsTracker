package com.eyecreate.miceandmystics.miceandmystics.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Character extends RealmObject {
    @PrimaryKey
    @Required
    private String uuid;
    @Required
    private String characterName;
    private RealmList<Ability> abilities;
    private RealmList<BackpackItem> storedItems; //Not sure why I made this a list when rulebook says you can only have one xD
    private Player controllingPlayer;

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public RealmList<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(RealmList<Ability> abilities) {
        this.abilities = abilities;
    }

    public RealmList<BackpackItem> getStoredItems() {
        return storedItems;
    }

    public void setStoredItems(RealmList<BackpackItem> storedItems) {
        this.storedItems = storedItems;
    }

    public Player getControllingPlayer() {
        return controllingPlayer;
    }

    public void setControllingPlayer(Player controllingPlayer) {
        this.controllingPlayer = controllingPlayer;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
