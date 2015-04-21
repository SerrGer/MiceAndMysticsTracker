package com.eyecreate.miceandmystics.miceandmystics.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.eyecreate.miceandmystics.miceandmystics.R;
import com.eyecreate.miceandmystics.miceandmystics.model.Achievement;

public class PartyAchievementViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

    TextView achievementName;
    ImageView achievementIcon;

    public PartyAchievementViewHolder(View itemView) {
        super(itemView);
        achievementName = (TextView)itemView.findViewById(R.id.achievement_name);
        achievementIcon = (ImageView)itemView.findViewById(R.id.achievement_icon);
        itemView.setOnLongClickListener(this);
    }

    public void bindHolder(Achievement achievement) {
        achievementName.setText(achievement.getAchievementName());
        achievementIcon.setImageResource(com.eyecreate.miceandmystics.miceandmystics.model.Enums.Achievement.valueOfDisplayName(achievement.getAchievementName()).getDisplayResource());
    }

    @Override
    public boolean onLongClick(View view) {
        //TODO:implement removing
        return false;
    }
}
