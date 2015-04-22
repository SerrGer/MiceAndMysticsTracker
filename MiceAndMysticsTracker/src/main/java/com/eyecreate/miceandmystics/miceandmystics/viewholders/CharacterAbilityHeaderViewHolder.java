package com.eyecreate.miceandmystics.miceandmystics.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.eyecreate.miceandmystics.miceandmystics.R;
import com.eyecreate.miceandmystics.miceandmystics.model.Character;

public class CharacterAbilityHeaderViewHolder extends RecyclerView.ViewHolder {

    TextView characterName;

    public CharacterAbilityHeaderViewHolder(View itemView) {
        super(itemView);
        characterName = (TextView) itemView.findViewById(R.id.character_name);
    }

    public void bindHolder(Character currentCharacter) {
        characterName.setText(currentCharacter.getCharacterName());
    }
}
