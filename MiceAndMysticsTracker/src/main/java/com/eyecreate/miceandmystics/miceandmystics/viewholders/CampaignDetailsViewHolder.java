package com.eyecreate.miceandmystics.miceandmystics.viewholders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.alertdialogpro.AlertDialogPro;
import com.eyecreate.miceandmystics.miceandmystics.CharacterDetailsActivity;
import com.eyecreate.miceandmystics.miceandmystics.R;
import com.eyecreate.miceandmystics.miceandmystics.adapters.CampaignDetailsAdapter;
import com.eyecreate.miceandmystics.miceandmystics.model.Character;
import com.eyecreate.miceandmystics.miceandmystics.model.Enums.CharacterNames;

public class CampaignDetailsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {

    TextView characterName,playerName;
    ImageView characterIcon;
    CampaignDetailsAdapter adapter;
    Character boundCharacter;

    public CampaignDetailsViewHolder(View itemView,CampaignDetailsAdapter adapter) {
        super(itemView);
        this.adapter = adapter;
        characterName = (TextView)itemView.findViewById(R.id.character_name);
        playerName = (TextView)itemView.findViewById(R.id.character_player);
        characterIcon = (ImageView)itemView.findViewById(R.id.character_icon);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void bindModel(Character character) {
        boundCharacter = character;
        characterName.setText(CharacterNames.valueOf(character.getCharacterName()).displayName());
        playerName.setText(character.getControllingPlayer().getPlayerName());
        characterIcon.setImageResource(CharacterNames.valueOf(character.getCharacterName()).getDrawableRes());
    }

    @Override
    public void onClick(View view) {
        Intent editCharacter = new Intent(view.getContext(), CharacterDetailsActivity.class);
        editCharacter.putExtra("characterId",boundCharacter.getUuid());
        view.getContext().startActivity(editCharacter);
    }

    @Override
    public boolean onLongClick(View view) {
        AlertDialog removeDialog = new AlertDialogPro.Builder(view.getContext(),R.style.dialogTheme)
                .setMessage(view.getContext().getString(R.string.remove_character_confirm)+characterName.getText()+"?")
                .setNegativeButton(view.getContext().getString(R.string.dialog_no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton(view.getContext().getString(R.string.dialog_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapter.removeCharacter(boundCharacter);
                    }
                })
                .create();
        removeDialog.show();
        return true;
    }
}
