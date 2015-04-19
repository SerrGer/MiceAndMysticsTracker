package com.eyecreate.miceandmystics.miceandmystics.model;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Campaign extends RealmObject {
    private RealmList<Character> currentCharacters;
    private RealmList<Achievement> partyStoryAchievements;

    public RealmList<Character> getCurrentCharacters() {
        return currentCharacters;
    }

    public void setCurrentCharacters(RealmList<Character> currentCharacters) {
        this.currentCharacters = currentCharacters;
    }

    public RealmList<Achievement> getPartyStoryAchievements() {
        return partyStoryAchievements;
    }

    public void setPartyStoryAchievements(RealmList<Achievement> partyStoryAchievements) {
        this.partyStoryAchievements = partyStoryAchievements;
    }
}
