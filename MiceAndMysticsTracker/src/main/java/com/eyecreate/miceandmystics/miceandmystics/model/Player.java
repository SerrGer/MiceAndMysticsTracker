package com.eyecreate.miceandmystics.miceandmystics.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import java.util.UUID;

public class Player extends RealmObject {
    @PrimaryKey
    private String uuid;
    private String playerName;

    public Player() {}

    public Player(String playerName) {setPlayerName(playerName);setUuid(UUID.randomUUID().toString());}

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
