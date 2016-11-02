package com.eyecreate.miceandmystics.miceandmystics.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.eyecreate.miceandmystics.miceandmystics.MiceAndMysticsApplication;
import com.eyecreate.miceandmystics.miceandmystics.R;
import com.eyecreate.miceandmystics.miceandmystics.model.Character;
import com.eyecreate.miceandmystics.miceandmystics.model.Enums.CampaignType;
import com.eyecreate.miceandmystics.miceandmystics.viewholders.CampaignViewHolder;

import io.realm.RealmResults;

public class CampaignAdapter extends RecyclerView.Adapter<CampaignViewHolder> {

    LayoutInflater inflater;
    RealmResults<com.eyecreate.miceandmystics.miceandmystics.model.Campaign> campaigns;

    public CampaignAdapter() {
        campaigns = MiceAndMysticsApplication.getRealmInstance().where(com.eyecreate.miceandmystics.miceandmystics.model.Campaign.class).findAll();
    }

    @Override
    public CampaignViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(inflater == null) inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new CampaignViewHolder(inflater.inflate(R.layout.item_campaign,parent,false),this);
    }

    @Override
    public void onBindViewHolder(CampaignViewHolder holder, int position) {
        holder.bindModel(campaigns.get(position));
    }

    public void fullRefresh() {
        notifyDataSetChanged();
    }

    public void addItem(String campaignName,CampaignType type) {
        MiceAndMysticsApplication.getRealmInstance().beginTransaction();
        com.eyecreate.miceandmystics.miceandmystics.model.Campaign campaign = new com.eyecreate.miceandmystics.miceandmystics.model.Campaign();
        campaign.setCampaignName(campaignName);
        campaign.setCampaignType(type.name());
        MiceAndMysticsApplication.getRealmInstance().copyToRealm(campaign);
        MiceAndMysticsApplication.getRealmInstance().commitTransaction();
        fullRefresh();
    }

    public void removeItem(String campaignName) {
        com.eyecreate.miceandmystics.miceandmystics.model.Campaign campaign = MiceAndMysticsApplication.getRealmInstance()
                .where(com.eyecreate.miceandmystics.miceandmystics.model.Campaign.class)
                .equalTo("campaignName",campaignName)
                .findFirst();
        for(Character character:campaign.getCurrentCharacters()) {
            CampaignDetailsAdapter.removeItemsAndAbilities(character);
        }
        MiceAndMysticsApplication.getRealmInstance().beginTransaction();
        campaign.getCurrentCharacters().deleteAllFromRealm();
        campaign.getPartyStoryAchievements().deleteAllFromRealm();
        campaign.deleteFromRealm();
        MiceAndMysticsApplication.getRealmInstance().commitTransaction();
        fullRefresh();
    }

    @Override
    public int getItemCount() {
        return campaigns.size();
    }
}
