package com.eyecreate.miceandmystics.miceandmystics.model;

import io.realm.RealmObject;

public class Player extends RealmObject {
    private String playerName;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
