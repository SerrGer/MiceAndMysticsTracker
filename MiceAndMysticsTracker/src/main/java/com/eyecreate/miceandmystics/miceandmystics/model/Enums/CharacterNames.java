package com.eyecreate.miceandmystics.miceandmystics.model.Enums;

import com.eyecreate.miceandmystics.miceandmystics.MiceAndMysticsApplication;
import com.eyecreate.miceandmystics.miceandmystics.R;

public enum CharacterNames {

    Collin(R.string.char_name_collin,new CharacterType[]{CharacterType.Warrior,CharacterType.Leader}, R.mipmap.collin),
    Filch(R.string.char_name_filch,new CharacterType[]{CharacterType.Scamp},R.mipmap.filch),
    Lily(R.string.char_name_lily,new CharacterType[]{CharacterType.Archer},R.mipmap.lily),
    Maginos(R.string.char_name_maginos,new CharacterType[]{CharacterType.Mystic,CharacterType.Maginos},R.mipmap.maginos),
    Nez(R.string.char_name_nez,new CharacterType[]{CharacterType.Tinkerer,CharacterType.Warrior},R.mipmap.nez),
    Tilda(R.string.char_name_tilda,new CharacterType[]{CharacterType.Healer},R.mipmap.tilda);

    private CharacterType[] types;
    private int drawableIcon;
    private int displayName;

    CharacterNames(int displayName,CharacterType[] types,int drawableIcon) {
        this.displayName = displayName;
        this.types = types;
        this.drawableIcon = drawableIcon;
    }

    public CharacterType[] characterTypes() { return types; }
    public int getDrawableRes() {return drawableIcon;}
    public String displayName() { return MiceAndMysticsApplication.getInstance().getString(displayName); }

    @Override public String toString() { return displayName(); }
}
