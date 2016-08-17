package com.eyecreate.miceandmystics.miceandmystics.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Campaign extends RealmObject {
    @PrimaryKey
    @Required
    private String campaignName;
    @Required
    private String campaignType;
    private RealmList<Character> currentCharacters;
    private RealmList<Achievement> partyStoryAchievements;
    private int campaignChapter;

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

    public String getCampaignType() {
        return campaignType;
    }

    public void setCampaignType(String campaignType) {
        this.campaignType = campaignType;
    }

    public int getCampaignChapter() {
        return campaignChapter;
    }

    public void setCampaignChapter(int campaignChapter) {
        this.campaignChapter = campaignChapter;
    }
}
