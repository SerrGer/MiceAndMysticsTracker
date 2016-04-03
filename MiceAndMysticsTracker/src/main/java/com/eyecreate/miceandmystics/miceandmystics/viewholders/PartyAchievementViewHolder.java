package com.eyecreate.miceandmystics.miceandmystics.viewholders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.alertdialogpro.AlertDialogPro;
import com.eyecreate.miceandmystics.miceandmystics.R;
import com.eyecreate.miceandmystics.miceandmystics.adapters.CampaignDetailsAdapter;
import com.eyecreate.miceandmystics.miceandmystics.model.Achievement;

public class PartyAchievementViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

    TextView achievementName;
    ImageView achievementIcon;
    CampaignDetailsAdapter adapter;
    Achievement boundAchievement;

    public PartyAchievementViewHolder(View itemView, CampaignDetailsAdapter adapter) {
        super(itemView);
        this.adapter = adapter;
        achievementName = (TextView)itemView.findViewById(R.id.achievement_name);
        achievementIcon = (ImageView)itemView.findViewById(R.id.achievement_icon);
        itemView.setOnLongClickListener(this);
    }

    public void bindHolder(Achievement achievement) {
        this.boundAchievement = achievement;
        achievementName.setText(com.eyecreate.miceandmystics.miceandmystics.model.Enums.Achievement.valueOf(achievement.getAchievementName()).displayName());
        achievementIcon.setImageResource(com.eyecreate.miceandmystics.miceandmystics.model.Enums.Achievement.valueOf(achievement.getAchievementName()).getDisplayResource());
    }

    @Override
    public boolean onLongClick(View view) {
        AlertDialog removeDialog = new AlertDialogPro.Builder(view.getContext(),R.style.dialogTheme)
                .setMessage(view.getContext().getString(R.string.remove_achievement_confirm)+achievementName.getText()+"?")
                .setNegativeButton(view.getContext().getString(R.string.dialog_no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton(view.getContext().getString(R.string.dialog_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapter.removeAchievement(boundAchievement);
                    }
                })
                .create();
        removeDialog.show();
        return true;
    }
}
