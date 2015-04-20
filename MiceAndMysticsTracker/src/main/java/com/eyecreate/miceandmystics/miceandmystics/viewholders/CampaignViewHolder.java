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
import com.eyecreate.miceandmystics.miceandmystics.CampaignDetailsActivity;
import com.eyecreate.miceandmystics.miceandmystics.R;
import com.eyecreate.miceandmystics.miceandmystics.adapters.CampaignAdapter;

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            itemView.setOnTouchListener(new View.OnTouchListener() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    v
                            .findViewById(R.id.campaign_content)
                            .getBackground()
                            .setHotspot(event.getX(), event.getY());

                    return(false);
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        Intent openCampaignDetails = new Intent(view.getContext(), CampaignDetailsActivity.class);
        openCampaignDetails.putExtra("campaignName",campaignName.getText().toString());
        view.getContext().startActivity(openCampaignDetails);
    }

    public void bindModel(com.eyecreate.miceandmystics.miceandmystics.model.Campaign campaign) {
        campaignName.setText(campaign.getCampaignName());
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
