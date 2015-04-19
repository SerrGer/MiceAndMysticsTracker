package com.eyecreate.miceandmystics.miceandmystics;

import android.app.Application;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MiceAndMysticsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //TODO:init realm here
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("Eadui.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
