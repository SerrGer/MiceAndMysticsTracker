package com.eyecreate.miceandmystics.miceandmystics.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.eyecreate.miceandmystics.miceandmystics.R;
import com.eyecreate.miceandmystics.miceandmystics.model.Campaign;

public class CampaignHeaderViewHolder extends RecyclerView.ViewHolder {

    TextView campaignName,campaignType;

    public CampaignHeaderViewHolder(View itemView) {
        super(itemView);
        campaignName = (TextView)itemView.findViewById(R.id.campaign_name);
        campaignType = (TextView)itemView.findViewById(R.id.campaign_type);
    }

    public void bindModel(Campaign campaign) {
        campaignName.setText(campaign.getCampaignName());
        campaignType.setText(campaign.getCampaignType());
    }
}
