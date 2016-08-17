package com.eyecreate.miceandmystics.miceandmystics.viewholders;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.eyecreate.miceandmystics.miceandmystics.MiceAndMysticsApplication;
import com.eyecreate.miceandmystics.miceandmystics.R;
import com.eyecreate.miceandmystics.miceandmystics.model.Campaign;
import com.eyecreate.miceandmystics.miceandmystics.model.Enums.CampaignType;

public class CampaignHeaderViewHolder extends RecyclerView.ViewHolder {

    TextView campaignName,campaignType,campaignChapter;
    EditText chapterEdit;

    public CampaignHeaderViewHolder(View itemView) {
        super(itemView);
        campaignName = (TextView)itemView.findViewById(R.id.campaign_name);
        campaignType = (TextView)itemView.findViewById(R.id.campaign_type);
        campaignChapter = (TextView) itemView.findViewById(R.id.campaign_chapter);
        chapterEdit = (EditText) itemView.findViewById(R.id.edit_chapter);
    }

    public void bindModel(final Campaign campaign) {
        campaignName.setText(campaign.getCampaignName());
        campaignType.setText(CampaignType.valueOf(campaign.getCampaignType()).displayName());
        campaignChapter.setText(campaignChapter.getResources().getText(R.string.campaign_chapter)+" : ");
        chapterEdit.setText(String.valueOf(campaign.getCampaignChapter() == 0 ? 1:campaign.getCampaignChapter()));
        chapterEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try{
                    Integer.parseInt(editable.toString());
                }
                catch(NumberFormatException e) {
                    return;
                }
                MiceAndMysticsApplication.getRealmInstance().beginTransaction();
                campaign.setCampaignChapter(Integer.parseInt(editable.toString()));
                MiceAndMysticsApplication.getRealmInstance().commitTransaction();
            }
        });
    }
}
