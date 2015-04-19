package com.eyecreate.miceandmystics.miceandmystics.model.Enums;

public enum CharacterNames {

    Collin(new CharacterType[]{CharacterType.Warrior,CharacterType.Leader}),
    Filch(new CharacterType[]{CharacterType.Scamp}),
    Lily(new CharacterType[]{CharacterType.Archer}),
    Maginos(new CharacterType[]{CharacterType.Mystic,CharacterType.Maginos}),
    Nez(new CharacterType[]{CharacterType.Tinkerer,CharacterType.Warrior}),
    Tilda(new CharacterType[]{CharacterType.Healer});

    private CharacterType[] types;

    CharacterNames(CharacterType[] types) {
        this.types = types;
    }

    public CharacterType[] characterTypes() { return types; }
}
