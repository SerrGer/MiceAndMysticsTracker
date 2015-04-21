package com.eyecreate.miceandmystics.miceandmystics.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;
import com.eyecreate.miceandmystics.miceandmystics.MiceAndMysticsApplication;
import com.eyecreate.miceandmystics.miceandmystics.R;
import com.eyecreate.miceandmystics.miceandmystics.model.*;
import com.eyecreate.miceandmystics.miceandmystics.model.Achievement;
import com.eyecreate.miceandmystics.miceandmystics.model.Character;
import com.eyecreate.miceandmystics.miceandmystics.model.Enums.*;
import com.eyecreate.miceandmystics.miceandmystics.viewholders.CampaignDetailsViewHolder;
import com.eyecreate.miceandmystics.miceandmystics.viewholders.CampaignHeaderViewHolder;
import com.eyecreate.miceandmystics.miceandmystics.viewholders.PartyAchievementViewHolder;
import com.eyecreate.miceandmystics.miceandmystics.viewholders.PartyAchievementsHeaderViewHolder;

import java.util.UUID;

public class CampaignDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    LayoutInflater inflater;
    Campaign currentCampaign;

    public CampaignDetailsAdapter(Campaign currentCampaign) {
        this.currentCampaign = currentCampaign;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(inflater == null) inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(viewType==R.id.campaignDetailMainHeader) {
            return new CampaignHeaderViewHolder(inflater.inflate(R.layout.item_campaign_header,parent,false));
        } else if(viewType == R.id.campaignDetailItem) {
            return new CampaignDetailsViewHolder(inflater.inflate(R.layout.item_campaign_details,parent,false),this);
        } else if(viewType == R.id.campaignPartyAchievementsHeader){
            return new PartyAchievementsHeaderViewHolder(inflater.inflate(R.layout.item_partyachievements_header,parent,false));
        } else {
            return new PartyAchievementViewHolder(inflater.inflate(R.layout.item_party_achievement,parent,false),this);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof CampaignHeaderViewHolder) {
            ((CampaignHeaderViewHolder)holder).bindModel(currentCampaign);
        } else if(holder instanceof  CampaignDetailsViewHolder) {
            ((CampaignDetailsViewHolder)holder).bindModel(currentCampaign.getCurrentCharacters().get(position-1)); //Here's that one again making the position value related to characters again.
        } else if(holder instanceof PartyAchievementViewHolder) {
            ((PartyAchievementViewHolder) holder).bindHolder(currentCampaign.getPartyStoryAchievements().get(position-2-currentCampaign.getCurrentCharacters().size())); //Here is more math that removes the headers and characters from the count.
        }
    }

    public void fullRefresh() {
        notifyDataSetChanged();
    }

    private boolean areThereDuplicates(Campaign campaign, CharacterNames character) {
        for(Character chr:campaign.getCurrentCharacters()) {
            if(chr.getCharacterName().equals(character.name())){
                return true;
            }
        }
        return false;
    }

    public void addCharacter(CharacterNames name, Player player) {
        if(!areThereDuplicates(currentCampaign,name)) {
            MiceAndMysticsApplication.getRealmInstance().beginTransaction();
            Character character = MiceAndMysticsApplication.getRealmInstance().createObject(Character.class);
            character.setUuid(UUID.randomUUID().toString());
            character.setCharacterName(name.name());
            character.setControllingPlayer(player);
            currentCampaign.getCurrentCharacters().add(character);
            MiceAndMysticsApplication.getRealmInstance().commitTransaction();
            fullRefresh();
        } else {
            Toast.makeText(inflater.getContext(),"Can not have two of same character in game.",Toast.LENGTH_LONG).show();
        }
    }

    public void removeCharacter(Character character) {
        removeCharacterFromDB(character);
        fullRefresh();
    }

    public static void removeCharacterFromDB(Character character) {
        removeItemsAndAbilities(character);
        MiceAndMysticsApplication.getRealmInstance().beginTransaction();
        character.removeFromRealm();
        MiceAndMysticsApplication.getRealmInstance().commitTransaction();
    }

    private static void removeItemsAndAbilities(Character character) {
        MiceAndMysticsApplication.getRealmInstance().beginTransaction();
        for(Ability ability:character.getAbilities()) {
            ability.removeFromRealm();
        }
        for(BackpackItem item:character.getStoredItems()) {
            item.removeFromRealm();
        }
        MiceAndMysticsApplication.getRealmInstance().commitTransaction();
    }

    public void addPartyAchievement(com.eyecreate.miceandmystics.miceandmystics.model.Enums.Achievement achievement) {
        MiceAndMysticsApplication.getRealmInstance().beginTransaction();
        Achievement realmAchievement = MiceAndMysticsApplication.getRealmInstance().createObject(Achievement.class);
        realmAchievement.setUuid(UUID.randomUUID().toString());
        realmAchievement.setAchievementName(achievement.displayName());
        currentCampaign.getPartyStoryAchievements().add(realmAchievement);
        MiceAndMysticsApplication.getRealmInstance().commitTransaction();
        fullRefresh();
    }

    public void removeAchievement(Achievement achievement) {
        MiceAndMysticsApplication.getRealmInstance().beginTransaction();
        achievement.removeFromRealm();
        MiceAndMysticsApplication.getRealmInstance().commitTransaction();
        fullRefresh();
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) {
            return R.id.campaignDetailMainHeader;
        } else if(position < currentCampaign.getCurrentCharacters().size()+1) {
            return R.id.campaignDetailItem;
        } else if(position == currentCampaign.getCurrentCharacters().size()+1) {
            return R.id.campaignPartyAchievementsHeader;
        } else {
            return R.id.campaignPartyAchievement;
        }
    }

    @Override
    public int getItemCount() {
        return currentCampaign.getCurrentCharacters().size()+1+1+currentCampaign.getPartyStoryAchievements().size(); //The two plus ones are for the headers.
    }
}
