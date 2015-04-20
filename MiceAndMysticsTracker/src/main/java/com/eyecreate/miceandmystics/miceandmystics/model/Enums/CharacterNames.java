package com.eyecreate.miceandmystics.miceandmystics.model.Enums;

import com.eyecreate.miceandmystics.miceandmystics.R;

public enum CharacterNames {

    Collin(new CharacterType[]{CharacterType.Warrior,CharacterType.Leader}, R.mipmap.collin),
    Filch(new CharacterType[]{CharacterType.Scamp},R.mipmap.filch),
    Lily(new CharacterType[]{CharacterType.Archer},R.mipmap.lily),
    Maginos(new CharacterType[]{CharacterType.Mystic,CharacterType.Maginos},R.mipmap.maginos),
    Nez(new CharacterType[]{CharacterType.Tinkerer,CharacterType.Warrior},R.mipmap.nez),
    Tilda(new CharacterType[]{CharacterType.Healer},R.mipmap.tilda);

    private CharacterType[] types;
    private int drawableIcon;

    CharacterNames(CharacterType[] types,int drawableIcon) {
        this.types = types;
        this.drawableIcon = drawableIcon;
    }

    public CharacterType[] characterTypes() { return types; }
    public int getDrawableRes() {return drawableIcon;}
}
