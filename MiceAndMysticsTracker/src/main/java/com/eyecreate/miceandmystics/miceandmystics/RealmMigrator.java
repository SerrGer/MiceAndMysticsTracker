package com.eyecreate.miceandmystics.miceandmystics;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;

public class RealmMigrator implements RealmMigration {
    @Override
    public void migrate(DynamicRealm dynamicRealm, long oldVersion, long newVersion) {
        if(oldVersion == 0 && newVersion == 1) {
            //Nothing
        }
    }
}
