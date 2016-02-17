package com.eyecreate.miceandmystics.miceandmystics.model.Enums;

import com.eyecreate.miceandmystics.miceandmystics.MiceAndMysticsApplication;
import com.eyecreate.miceandmystics.miceandmystics.R;

public enum Achievement {

    Miz_Maggie(R.string.ach_name_maggie, R.mipmap.mizmaggie),
    Vanestras_Brush(R.string.ach_name_van_brush,R.mipmap.vanbrush),
    King(R.string.ach_name_king,R.mipmap.king),
    Cats_Horn_Claw(R.string.ach_name_horn_claw,R.mipmap.catclaw), //not sure if it matters this is the claw and not the horn side.
    Catnip(R.string.ach_name_catnip,R.mipmap.catnip);

    private int displayName;
    private int displayResource;

    Achievement(int displayName,int resource) {
        this.displayName = displayName;
        this.displayResource = resource;
    }

    public String displayName() { return MiceAndMysticsApplication.getInstance().getString(displayName); }

    public int getDisplayResource() { return displayResource; }

    @Override public String toString() { return displayName(); }
}
