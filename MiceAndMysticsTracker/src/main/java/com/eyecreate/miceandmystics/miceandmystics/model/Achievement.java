package com.eyecreate.miceandmystics.miceandmystics.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Achievement extends RealmObject {
    @PrimaryKey
    private String achievementName;

    public Achievement() {}

    public Achievement(String achievementName) {setAchievementName(achievementName);}

    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }
}
