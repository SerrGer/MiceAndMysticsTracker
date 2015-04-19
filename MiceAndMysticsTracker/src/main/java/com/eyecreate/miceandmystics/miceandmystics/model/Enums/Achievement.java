package com.eyecreate.miceandmystics.miceandmystics.model.Enums;

public enum Achievement {

    Miz_Maggie("Miz Maggie"),
    Vanestras_Brush("Vanestra's Brush"),
    King("King"),
    Cats_Horn_Claw("Cat's Horn Claw"),
    Catnip("Catnip");

    private String displayName;

    Achievement(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() { return displayName; }

    @Override public String toString() { return displayName; }
    public Achievement valueOfDisplayName(String valuedDisplayName) {return Achievement.valueOf(valuedDisplayName.replace(" ","_").replace("'",""));}
}
