package com.eyecreate.miceandmystics.miceandmystics.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.eyecreate.miceandmystics.miceandmystics.MiceAndMysticsApplication;
import com.eyecreate.miceandmystics.miceandmystics.R;
import com.eyecreate.miceandmystics.miceandmystics.model.Ability;
import com.eyecreate.miceandmystics.miceandmystics.model.BackpackItem;
import com.eyecreate.miceandmystics.miceandmystics.model.Character;
import com.eyecreate.miceandmystics.miceandmystics.model.Enums.Abilities;
import com.eyecreate.miceandmystics.miceandmystics.viewholders.CharacterAbilityHeaderViewHolder;
import com.eyecreate.miceandmystics.miceandmystics.viewholders.CharacterAbilityViewHolder;
import com.eyecreate.miceandmystics.miceandmystics.viewholders.CharacterItemViewHolder;
import com.eyecreate.miceandmystics.miceandmystics.viewholders.CharacterItemsHeaderViewHolder;

import java.util.UUID;

public class CharacterDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Character currentCharacter;
    LayoutInflater inflater;

    public CharacterDetailsAdapter(Character currentCharacter) {
        this.currentCharacter = currentCharacter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(inflater == null) inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(viewType == R.id.characterDeatilAbilityHeader) {
          return new CharacterAbilityHeaderViewHolder(inflater.inflate(R.layout.item_character_ability_header,parent,false));
        } else if(viewType == R.id.characterDetailAbility) {
            return new CharacterAbilityViewHolder(inflater.inflate(R.layout.item_character_ability,parent,false),this);
        } else if(viewType == R.id.characterDetailItemHeader) {
            return new CharacterItemsHeaderViewHolder(inflater.inflate(R.layout.item_character_items_header,parent,false));
        } else {
            return new CharacterItemViewHolder(inflater.inflate(R.layout.item_character_item,parent,false),this);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof CharacterAbilityHeaderViewHolder) {
            ((CharacterAbilityHeaderViewHolder) holder).bindHolder(currentCharacter);
        } else if(holder instanceof CharacterAbilityViewHolder) {
            ((CharacterAbilityViewHolder)holder).bindHolder(currentCharacter.getAbilities().get(position-1));
        } else if(holder instanceof CharacterItemViewHolder) {
            ((CharacterItemViewHolder) holder).bindModel(currentCharacter.getStoredItems().get(position-2-currentCharacter.getAbilities().size()));
        }
    }

    @Override
    public int getItemCount() {
        return currentCharacter.getAbilities().size()+1+currentCharacter.getStoredItems().size()+1; //The plus ones represent headers
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) {
            return R.id.characterDeatilAbilityHeader;
        } else if(position < currentCharacter.getAbilities().size()+1) {
            return R.id.characterDetailAbility;
        } else if (position == currentCharacter.getAbilities().size()+1) {
            return R.id.characterDetailItemHeader;
        } else {
            return R.id.characterDetailItem;
        }
    }

    public void addAbility(Abilities ability) {
        MiceAndMysticsApplication.getRealmInstance().beginTransaction();
        Ability realmAbility = MiceAndMysticsApplication.getRealmInstance().createObject(Ability.class);
        realmAbility.setUuid(UUID.randomUUID().toString());
        realmAbility.setAbilityName(ability.name());
        currentCharacter.getAbilities().add(realmAbility);
        MiceAndMysticsApplication.getRealmInstance().commitTransaction();
        notifyDataSetChanged();
    }

    public void addItem(String itemName) {
        MiceAndMysticsApplication.getRealmInstance().beginTransaction();
        BackpackItem item = MiceAndMysticsApplication.getRealmInstance().createObject(BackpackItem.class);
        item.setUuid(UUID.randomUUID().toString());
        item.setItemName(itemName);
        currentCharacter.getStoredItems().add(item);
        MiceAndMysticsApplication.getRealmInstance().commitTransaction();
        notifyDataSetChanged();
    }

    public void removeItem(String itemUuid) {
        BackpackItem item = MiceAndMysticsApplication.getRealmInstance().where(BackpackItem.class).equalTo("uuid",itemUuid).findFirst();
        MiceAndMysticsApplication.getRealmInstance().beginTransaction();
        item.removeFromRealm();
        MiceAndMysticsApplication.getRealmInstance().commitTransaction();
        notifyDataSetChanged();
    }

    public void removeAbility(String abilityUuid) {
        Ability ability = MiceAndMysticsApplication.getRealmInstance().where(Ability.class).equalTo("uuid",abilityUuid).findFirst();
        MiceAndMysticsApplication.getRealmInstance().beginTransaction();
        ability.removeFromRealm();
        MiceAndMysticsApplication.getRealmInstance().commitTransaction();
        notifyDataSetChanged();
    }
}
