package com.eyecreate.miceandmystics.miceandmystics.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.eyecreate.miceandmystics.miceandmystics.MiceAndMysticsApplication;
import com.eyecreate.miceandmystics.miceandmystics.R;
import com.eyecreate.miceandmystics.miceandmystics.viewholders.Campaign;
import io.realm.RealmResults;

public class CampaignAdapter extends RecyclerView.Adapter<Campaign> {

    LayoutInflater inflater;
    RealmResults<com.eyecreate.miceandmystics.miceandmystics.model.Campaign> campaigns;

    public CampaignAdapter() {
        campaigns = MiceAndMysticsApplication.getRealmInstance().where(com.eyecreate.miceandmystics.miceandmystics.model.Campaign.class).findAll();
    }

    @Override
    public Campaign onCreateViewHolder(ViewGroup parent, int viewType) {
        if(inflater == null) inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new Campaign(inflater.inflate(R.layout.item_campaign,parent,false),this);
    }

    @Override
    public void onBindViewHolder(Campaign holder, int position) {
        holder.bindModel(campaigns.get(position));
    }

    public void fullRefresh() {
        notifyDataSetChanged();
    }

    public void addItem(String campaignName) {
        MiceAndMysticsApplication.getRealmInstance().beginTransaction();
        com.eyecreate.miceandmystics.miceandmystics.model.Campaign campaign = new com.eyecreate.miceandmystics.miceandmystics.model.Campaign();
        campaign.setCampaignName(campaignName);
        MiceAndMysticsApplication.getRealmInstance().copyToRealm(campaign);
        MiceAndMysticsApplication.getRealmInstance().commitTransaction();
        fullRefresh();
    }

    public void removeItem(String campaignName) {
        com.eyecreate.miceandmystics.miceandmystics.model.Campaign campaign = MiceAndMysticsApplication.getRealmInstance()
                .where(com.eyecreate.miceandmystics.miceandmystics.model.Campaign.class)
                .equalTo("campaignName",campaignName)
                .findFirst();
        MiceAndMysticsApplication.getRealmInstance().beginTransaction();
        campaign.removeFromRealm();
        MiceAndMysticsApplication.getRealmInstance().commitTransaction();
        fullRefresh();
    }

    @Override
    public int getItemCount() {
        return campaigns.size();
    }
}
