package com.eyecreate.miceandmystics.miceandmystics;

import android.app.Application;
import android.content.SharedPreferences;

import com.eyecreate.miceandmystics.miceandmystics.model.*;
import com.eyecreate.miceandmystics.miceandmystics.model.Character;
import com.eyecreate.miceandmystics.miceandmystics.model.Enums.Abilities;
import com.eyecreate.miceandmystics.miceandmystics.model.Enums.CampaignType;
import com.eyecreate.miceandmystics.miceandmystics.model.Enums.CharacterNames;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MiceAndMysticsApplication extends Application {

    private static MiceAndMysticsApplication singletonApplication;
    private static Realm singletonRealm;
    private RealmConfiguration realmConfig;

    @Override
    public void onCreate() {
        super.onCreate();
        singletonApplication = this;
        realmConfig = new RealmConfiguration.Builder(this).schemaVersion(1).migration(new RealmMigrator()).build();
        singletonRealm = Realm.getInstance(realmConfig);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("ArchitectsDaughter.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
        //checkForPreLocalization();
    }

    private void checkForPreLocalization() {
        //Check if there are DB items and migration check flag isn't set yet.
        //If so, take all abilities, achievements, campaign names, and character names and make them enum names.
        //Optionally, be sad I didn't think of this before now.
        if(!getSharedPreferences("dbcheck", MODE_PRIVATE).contains("localizationchecked") && getRealmInstance().where(Campaign.class).findAll().size()>0) {
            getRealmInstance().beginTransaction();
            RealmResults<Ability> abilities = getRealmInstance().where(Ability.class).findAll();
            for(int i = 0; i<abilities.size();i++) {
                Ability ability = abilities.get(i);
                for(Abilities enums:Abilities.values()) {
                    if(enums.displayName().equals(ability.getAbilityName())) {
                        ability.setAbilityName(enums.name());
                    }
                }
            }
            RealmResults<Achievement> achievements = getRealmInstance().where(Achievement.class).findAll();
            for(int i = 0; i<achievements.size();i++) {
                Achievement achievement = achievements.get(i);
                for(com.eyecreate.miceandmystics.miceandmystics.model.Enums.Achievement enums: com.eyecreate.miceandmystics.miceandmystics.model.Enums.Achievement.values()) {
                    if(enums.displayName().equals(achievement.getAchievementName())) {
                        achievement.setAchievementName(enums.name());
                    }
                }
            }
            RealmResults<Campaign> campaigns = getRealmInstance().where(Campaign.class).findAll();
            for(int i = 0;i<campaigns.size();i++) {
                Campaign campaign = campaigns.get(i);
                if(campaign.getCampaignType().equals(CampaignType.Sorrow_and_Remembrance.displayName())) {
                    campaign.setCampaignType(CampaignType.Sorrow_and_Remembrance.name());
                }
            }
            RealmResults<Character> characters = getRealmInstance().where(Character.class).findAll();
            for(int i = 0;i<characters.size();i++) {
                Character character = characters.get(i);
                for(CharacterNames enums:CharacterNames.values()) {
                    if(enums.displayName().equals(character.getCharacterName())) {
                        character.setCharacterName(enums.name());
                    }
                }
            }
            getRealmInstance().commitTransaction();
        }
        getSharedPreferences("dbcheck",MODE_PRIVATE).edit().putBoolean("localizationchecked",true).apply();
    }

    public static MiceAndMysticsApplication getInstance() {
        return singletonApplication;
    }

    public static Realm getRealmInstance() {
        return singletonRealm;
    }
}
