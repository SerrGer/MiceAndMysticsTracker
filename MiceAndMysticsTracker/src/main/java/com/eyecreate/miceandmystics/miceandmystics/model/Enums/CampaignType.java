package com.eyecreate.miceandmystics.miceandmystics.model.Enums;

import com.eyecreate.miceandmystics.miceandmystics.MiceAndMysticsApplication;
import com.eyecreate.miceandmystics.miceandmystics.R;

public enum CampaignType {
    Sorrow_and_Remembrance(R.string.campaign_name_s_a_r);

    private int displayName;

    CampaignType(int name) {
        this.displayName = name;
    }

    public String displayName() { return MiceAndMysticsApplication.getInstance().getString(displayName); }

    @Override public String toString() { return displayName(); }
}
