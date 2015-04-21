package com.eyecreate.miceandmystics.miceandmystics.model.Enums;

import com.eyecreate.miceandmystics.miceandmystics.R;

public enum Achievement {

    Miz_Maggie("Miz Maggie", R.mipmap.mizmaggie),
    Vanestras_Brush("Vanestra's Brush",R.mipmap.vanbrush),
    King("King",R.mipmap.king),
    Cats_Horn_Claw("Cat's Horn Claw",R.mipmap.catclaw), //not sure if it matters this is the claw and not the horn side.
    Catnip("Catnip",R.mipmap.catnip);

    private String displayName;
    private int displayResource;

    Achievement(String displayName,int resource) {
        this.displayName = displayName;
        this.displayResource = resource;
    }

    public String displayName() { return displayName; }

    public int getDisplayResource() { return displayResource; }

    @Override public String toString() { return displayName; }
    public static Achievement valueOfDisplayName(String valuedDisplayName) {return Achievement.valueOf(valuedDisplayName.replace(" ","_").replace("'",""));}
}
