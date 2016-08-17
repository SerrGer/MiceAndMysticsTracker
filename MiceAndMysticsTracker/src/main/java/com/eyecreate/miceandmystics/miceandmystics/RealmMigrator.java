package com.eyecreate.miceandmystics.miceandmystics;

import com.eyecreate.miceandmystics.miceandmystics.model.Campaign;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class RealmMigrator implements RealmMigration {
    @Override
    public void migrate(DynamicRealm dynamicRealm, long oldVersion, long newVersion) {
        if(oldVersion == 0) {
            //Nothing
            oldVersion++;
        }
        if(oldVersion == 1) {
            dynamicRealm.getSchema().get("Campaign")
                    .addField("campaignChapter",int.class);
            oldVersion++;
        }
    }
}
