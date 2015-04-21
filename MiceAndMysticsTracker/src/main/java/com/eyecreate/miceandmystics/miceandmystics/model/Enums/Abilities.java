package com.eyecreate.miceandmystics.miceandmystics.model.Enums;

import java.util.Arrays;

public enum Abilities {

    First_Aid("First Aid",new CharacterType[]{CharacterType.Mystic,CharacterType.Archer,CharacterType.Scamp,CharacterType.Leader,CharacterType.Healer,CharacterType.Tinkerer,CharacterType.Warrior}),
    Chain_Lightning("Chain Lightning",new CharacterType[]{CharacterType.Mystic}),
    Mystic_Blast("Mystic Blast",new CharacterType[]{CharacterType.Mystic}),
    Major_Heal("Major Heal",new CharacterType[]{CharacterType.Healer}),
    Feint("Feint",new CharacterType[]{CharacterType.Warrior}),
    Battle_Squeak("Battle Squeak",new CharacterType[]{CharacterType.Warrior}),
    Battle_Leader("Battle Leader",new CharacterType[]{CharacterType.Leader}),
    Set_Trap("Set Trap",new CharacterType[]{CharacterType.Tinkerer}),
    Fortify("Fortify",new CharacterType[]{CharacterType.Healer}),
    Cure("Cure",new CharacterType[]{CharacterType.Healer}),
    Knife_Strike("Knife Strike",new CharacterType[]{CharacterType.Scamp}),
    Find("Find",new CharacterType[]{CharacterType.Scamp}),
    Dodge("Dodge",new CharacterType[]{CharacterType.Scamp}),
    Thundersqueak("Thundersqueak",new CharacterType[]{CharacterType.Warrior,CharacterType.Tinkerer,CharacterType.Healer}),
    Backslash("Backslash",new CharacterType[]{CharacterType.Warrior}),
    Mystic_Bolt("Mystic Bolt",new CharacterType[]{CharacterType.Mystic}),
    Inspire("Inspire",new CharacterType[]{CharacterType.Leader}),
    Keen_Eye("Keen Eye",new CharacterType[]{CharacterType.Scamp,CharacterType.Archer}),
    Meeps("Meeps",new CharacterType[]{CharacterType.Maginos}),
    Invisibility("Invisibility",new CharacterType[]{CharacterType.Mystic}),
    Accurate_Shot("Accurate Shot",new CharacterType[]{CharacterType.Archer}),
    Aimed_Shot("Aimed Shot",new CharacterType[]{CharacterType.Archer}),
    Power_Shot("Power Shot",new CharacterType[]{CharacterType.Archer}),
    Preemptive_Shot("Preemptive Shot",new CharacterType[]{CharacterType.Archer}),
    Resolve("Resolve",new CharacterType[]{CharacterType.Warrior}),
    Protect("Protect",new CharacterType[]{CharacterType.Warrior}),
    Give_Order("Give Order",new CharacterType[]{CharacterType.Leader}),
    Energy_Rush("Energy Rush",new CharacterType[]{CharacterType.Scamp});

    private String displayName;
    private CharacterType[] appliesTo;

    Abilities(String displayName,CharacterType[] appliesTo) {
        this.displayName = displayName;
        this.appliesTo = appliesTo;
    }

    public boolean doesApply(CharacterType type) {
        return Arrays.asList(appliesTo).contains(type);
    }

    public boolean doesApply(CharacterType[] types) {
        for(CharacterType t: types) {
            if(doesApply(t)) return true;
        }
        return false;
    }

    public String displayName() { return displayName; }

    @Override public String toString() { return displayName; }
    public static Abilities valueOfDisplayName(String valuedDisplayName) {return Abilities.valueOf(valuedDisplayName.replace(" ","_"));}
}
