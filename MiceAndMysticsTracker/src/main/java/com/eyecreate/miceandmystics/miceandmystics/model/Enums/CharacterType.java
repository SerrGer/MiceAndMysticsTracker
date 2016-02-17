package com.eyecreate.miceandmystics.miceandmystics.model.Enums;

import com.eyecreate.miceandmystics.miceandmystics.MiceAndMysticsApplication;
import com.eyecreate.miceandmystics.miceandmystics.R;

public enum CharacterType {
    Warrior(R.string.char_type_warrior),
    Leader(R.string.char_type_leader),
    Mystic(R.string.char_type_mystic),
    Scamp(R.string.char_type_scamp),
    Tinkerer(R.string.char_type_tinkerer),
    Archer(R.string.char_type_archer),
    Healer(R.string.char_type_healer),
    Maginos(R.string.char_type_maginos);

    private int displayName;

    CharacterType(int displayName) {
        this.displayName = displayName;
    }

    public String displayName() { return MiceAndMysticsApplication.getInstance().getString(displayName); }

    @Override public String toString() { return displayName(); }
}
