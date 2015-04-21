package com.eyecreate.miceandmystics.miceandmystics.viewholders;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieSlice;
import com.eyecreate.miceandmystics.miceandmystics.CampaignDetailsActivity;
import com.eyecreate.miceandmystics.miceandmystics.R;
import com.eyecreate.miceandmystics.miceandmystics.adapters.CampaignAdapter;
import com.eyecreate.miceandmystics.miceandmystics.model.Enums.CharacterNames;

public class CampaignViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {

    CampaignAdapter adapter;
    TextView campaignName;
    PieGraph characterGraph;

    public CampaignViewHolder(View itemView, CampaignAdapter adapter) {
        super(itemView);
        this.adapter = adapter;
        campaignName = (TextView) itemView.findViewById(R.id.campagin_name);
        characterGraph = (PieGraph) itemView.findViewById(R.id.campaign_characters_graph);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent openCampaignDetails = new Intent(view.getContext(), CampaignDetailsActivity.class);
        openCampaignDetails.putExtra("campaignName",campaignName.getText().toString());
        view.getContext().startActivity(openCampaignDetails);
    }

    public void bindModel(com.eyecreate.miceandmystics.miceandmystics.model.Campaign campaign) {
        campaignName.setText(campaign.getCampaignName());
        characterGraph.setInnerCircleRatio(200);
        characterGraph.removeSlices();
        for(com.eyecreate.miceandmystics.miceandmystics.model.Character character:campaign.getCurrentCharacters()){
            PieSlice slice = new PieSlice();
            if(CharacterNames.valueOf(character.getCharacterName()).equals(CharacterNames.Collin)){
                slice.setColor(campaignName.getContext().getResources().getColor(R.color.collin));
                slice.setValue(1);
            } else if (CharacterNames.valueOf(character.getCharacterName()).equals(CharacterNames.Filch)) {
                slice.setColor(campaignName.getContext().getResources().getColor(R.color.filch));
                slice.setValue(1);
            } else if (CharacterNames.valueOf(character.getCharacterName()).equals(CharacterNames.Lily)) {
                slice.setColor(campaignName.getContext().getResources().getColor(R.color.lily));
                slice.setValue(1);
            } else if (CharacterNames.valueOf(character.getCharacterName()).equals(CharacterNames.Maginos)) {
                slice.setColor(campaignName.getContext().getResources().getColor(R.color.maginos));
                slice.setValue(1);
            } else if (CharacterNames.valueOf(character.getCharacterName()).equals(CharacterNames.Nez)) {
                slice.setColor(campaignName.getContext().getResources().getColor(R.color.nez));
                slice.setValue(1);
            } else if (CharacterNames.valueOf(character.getCharacterName()).equals(CharacterNames.Tilda)) {
                slice.setColor(campaignName.getContext().getResources().getColor(R.color.tilda));
                slice.setValue(1);
            }
            characterGraph.addSlice(slice);
        }
        if(characterGraph.getSlices().size()>1) {
            characterGraph.setPadding(10);
        } else {
            characterGraph.setPadding(1);//seems it won't draw 1 without some sort of padding.
        }
    }

    @Override
    public boolean onLongClick(View view) {
        AlertDialog removeDialog = new AlertDialog.Builder(view.getContext(),R.style.dialogTheme)
                .setMessage("Do you want to remove campaign: "+campaignName.getText()+"?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapter.removeItem((String) campaignName.getText());
                    }
                })
                .create();
        removeDialog.show();
        return true;
    }
}
