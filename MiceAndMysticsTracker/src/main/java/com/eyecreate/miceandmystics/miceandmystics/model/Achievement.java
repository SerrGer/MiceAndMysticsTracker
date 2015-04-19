package com.eyecreate.miceandmystics.miceandmystics.model;

import io.realm.RealmObject;

public class Achievement extends RealmObject {
    private String achievementName;

    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }
}
