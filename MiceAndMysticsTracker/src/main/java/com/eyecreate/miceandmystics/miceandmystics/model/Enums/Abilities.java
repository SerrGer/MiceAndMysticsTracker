package com.eyecreate.miceandmystics.miceandmystics.model.Enums;

import com.eyecreate.miceandmystics.miceandmystics.MiceAndMysticsApplication;
import com.eyecreate.miceandmystics.miceandmystics.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Abilities {

    First_Aid(R.string.ability_firstaid,new CharacterType[]{CharacterType.Mystic,CharacterType.Archer,CharacterType.Scamp,CharacterType.Leader,CharacterType.Healer,CharacterType.Tinkerer,CharacterType.Warrior}),
    Chain_Lightning(R.string.ability_chainlighting,new CharacterType[]{CharacterType.Mystic}),
    Mystic_Blast(R.string.ability_mysticblast,new CharacterType[]{CharacterType.Mystic}),
    Major_Heal(R.string.ability_majorheal,new CharacterType[]{CharacterType.Healer}),
    Feint(R.string.ability_feint,new CharacterType[]{CharacterType.Warrior}),
    Battle_Squeak(R.string.ability_battlesqueak,new CharacterType[]{CharacterType.Warrior}),
    Battle_Leader(R.string.ability_battleleader,new CharacterType[]{CharacterType.Leader}),
    Set_Trap(R.string.ability_settrap,new CharacterType[]{CharacterType.Tinkerer}),
    Fortify(R.string.ability_fortify,new CharacterType[]{CharacterType.Healer}),
    Cure(R.string.ability_cure,new CharacterType[]{CharacterType.Healer}),
    Knife_Strike(R.string.ability_knifestrike,new CharacterType[]{CharacterType.Scamp}),
    Find(R.string.ability_find,new CharacterType[]{CharacterType.Scamp}),
    Dodge(R.string.ability_dodge,new CharacterType[]{CharacterType.Scamp}),
    Thundersqueak(R.string.ability_thundersqueak,new CharacterType[]{CharacterType.Warrior,CharacterType.Tinkerer,CharacterType.Healer}),
    Backslash(R.string.ability_backslash,new CharacterType[]{CharacterType.Warrior}),
    Mystic_Bolt(R.string.ability_mysticbolt,new CharacterType[]{CharacterType.Mystic}),
    Inspire(R.string.ability_inspire,new CharacterType[]{CharacterType.Leader}),
    Keen_Eye(R.string.ability_keeneye,new CharacterType[]{CharacterType.Scamp,CharacterType.Archer}),
    Meeps(R.string.ability_meeps,new CharacterType[]{CharacterType.Maginos}),
    Invisibility(R.string.ability_invisibility,new CharacterType[]{CharacterType.Mystic}),
    Accurate_Shot(R.string.ability_accurateshot,new CharacterType[]{CharacterType.Archer}),
    Aimed_Shot(R.string.ability_aimedshot,new CharacterType[]{CharacterType.Archer}),
    Power_Shot(R.string.ability_powershot,new CharacterType[]{CharacterType.Archer}),
    Preemptive_Shot(R.string.ability_prempshot,new CharacterType[]{CharacterType.Archer}),
    Resolve(R.string.ability_resolve,new CharacterType[]{CharacterType.Warrior}),
    Protect(R.string.ability_protect,new CharacterType[]{CharacterType.Warrior}),
    Give_Order(R.string.ability_giveorder,new CharacterType[]{CharacterType.Leader}),
    Energy_Rush(R.string.ability_energyrush,new CharacterType[]{CharacterType.Scamp});

    private int displayName;
    private CharacterType[] appliesTo;

    Abilities(int displayName,CharacterType[] appliesTo) {
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

    public static Abilities[] getMatchingCharacterAbilities(CharacterType[] matchingTypes) {
        List<Abilities> matchingList = new ArrayList<Abilities>();
        for(Abilities ability:Abilities.values()) {
            if(ability.doesApply(matchingTypes)) {
                matchingList.add(ability);
            }
        }
        return matchingList.toArray(new Abilities[matchingList.size()]);
    }

    public String displayName() { return MiceAndMysticsApplication.getInstance().getString(displayName); }

    @Override public String toString() { return displayName(); }
}
