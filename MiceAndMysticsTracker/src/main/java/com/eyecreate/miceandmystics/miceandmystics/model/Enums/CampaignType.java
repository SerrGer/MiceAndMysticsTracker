package com.eyecreate.miceandmystics.miceandmystics.model.Enums;

public enum CampaignType {
    Sorrow_and_Remembrance("Sorrow and Remembrance");

    private String displayName;

    CampaignType(String name) {
        this.displayName = name;
    }

    public String displayName() { return displayName; }

    @Override public String toString() { return displayName; }
    public static CampaignType valueOfDisplayName(String valuedDisplayName) {return CampaignType.valueOf(valuedDisplayName.replace(" ","_"));}
}
