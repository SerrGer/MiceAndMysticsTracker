package com.eyecreate.miceandmystics.miceandmystics.model;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Campaign extends RealmObject {
    private RealmList<Character> currentCharacters;
    private RealmList<Achievement> partyStoryAchievments;

    public RealmList<Character> getCurrentCharacters() {
        return currentCharacters;
    }

    public void setCurrentCharacters(RealmList<Character> currentCharacters) {
        this.currentCharacters = currentCharacters;
    }

    public RealmList<Achievement> getPartyStoryAchievments() {
        return partyStoryAchievments;
    }

    public void setPartyStoryAchievments(RealmList<Achievement> partyStoryAchievments) {
        this.partyStoryAchievments = partyStoryAchievments;
    }
}
