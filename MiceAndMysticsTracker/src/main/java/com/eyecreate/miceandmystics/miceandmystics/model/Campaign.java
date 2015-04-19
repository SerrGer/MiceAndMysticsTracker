package com.eyecreate.miceandmystics.miceandmystics.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Campaign extends RealmObject {
    @PrimaryKey
    private String campaignName;
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

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }
}
