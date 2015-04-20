package com.eyecreate.miceandmystics.miceandmystics.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.eyecreate.miceandmystics.miceandmystics.MiceAndMysticsApplication;
import com.eyecreate.miceandmystics.miceandmystics.R;
import com.eyecreate.miceandmystics.miceandmystics.model.*;
import com.eyecreate.miceandmystics.miceandmystics.model.Character;
import com.eyecreate.miceandmystics.miceandmystics.model.Enums.CharacterNames;
import com.eyecreate.miceandmystics.miceandmystics.model.Enums.CharacterType;
import com.eyecreate.miceandmystics.miceandmystics.viewholders.CampaignDetailsViewHolder;
import com.eyecreate.miceandmystics.miceandmystics.viewholders.CampaignHeaderViewHolder;

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
        } else {
            return new CampaignDetailsViewHolder(inflater.inflate(R.layout.item_campaign_details,parent,false),this);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof CampaignHeaderViewHolder) {
            ((CampaignHeaderViewHolder)holder).bindModel(currentCampaign);
        } else {
            ((CampaignDetailsViewHolder)holder).bindModel(currentCampaign.getCurrentCharacters().get(position-1)); //Here's that one again making the position value related to characters again.
        }
    }

    public void fullRefresh() {
        notifyDataSetChanged();
    }

    public void addCharacter(CharacterNames name, Player player) {
        MiceAndMysticsApplication.getRealmInstance().beginTransaction();
        Character character = MiceAndMysticsApplication.getRealmInstance().createObject(Character.class);
        character.setUuid(UUID.randomUUID().toString());
        character.setCharacterName(name.name());
        character.setControllingPlayer(player);
        MiceAndMysticsApplication.getRealmInstance().commitTransaction();
        fullRefresh();
    }

    public void removeCharacter(Character character) {
        MiceAndMysticsApplication.getRealmInstance().beginTransaction();
        character.removeFromRealm();
        MiceAndMysticsApplication.getRealmInstance().commitTransaction();
        fullRefresh();
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) {
            return R.id.campaignDetailMainHeader;
        } else {
            return R.id.campaignDetailItem;
        }
    }

    @Override
    public int getItemCount() {
        return currentCampaign.getCurrentCharacters().size()+1; //The plus one if for header. May expand this for other kinds of details.
    }
}
