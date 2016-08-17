package com.eyecreate.miceandmystics.miceandmystics.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

import java.util.UUID;

public class Achievement extends RealmObject {
    @PrimaryKey
    @Required
    private String uuid;
    @Required
    private String achievementName;

    public Achievement() {}

    public Achievement(String achievementName) {setAchievementName(achievementName);setUuid(UUID.randomUUID().toString());}

    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
