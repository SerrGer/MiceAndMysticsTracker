package com.eyecreate.miceandmystics.miceandmystics;

import android.app.Application;
import io.realm.Realm;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MiceAndMysticsApplication extends Application {

    private static MiceAndMysticsApplication singletonApplication;
    private static Realm singletonRealm;

    @Override
    public void onCreate() {
        super.onCreate();
        singletonApplication = this;
        singletonRealm = Realm.getInstance(this);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("ArchitectsDaughter.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public static MiceAndMysticsApplication getInstance() {
        return singletonApplication;
    }

    public static Realm getRealmInstance() {
        return singletonRealm;
    }
}
