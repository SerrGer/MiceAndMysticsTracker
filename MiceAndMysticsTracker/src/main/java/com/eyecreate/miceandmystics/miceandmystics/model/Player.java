package com.eyecreate.miceandmystics.miceandmystics.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import java.util.List;
import java.util.UUID;

public class Player extends RealmObject {
    @PrimaryKey
    private String playerName;

    public Player() {}

    public Player(String playerName) {setPlayerName(playerName);}

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public static String[] convertPlayerListToStringArray(List<Player> playerList) {
        String[] outputPlayers = new String[playerList.size()];
        int iter = 0;
        for(Player player:playerList){
            outputPlayers[iter] = player.getPlayerName();
            iter++;
        }
        return outputPlayers;
    }
}
