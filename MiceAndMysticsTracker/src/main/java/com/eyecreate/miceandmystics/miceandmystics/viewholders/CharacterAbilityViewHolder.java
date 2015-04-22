package com.eyecreate.miceandmystics.miceandmystics.viewholders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.alertdialogpro.AlertDialogPro;
import com.eyecreate.miceandmystics.miceandmystics.R;
import com.eyecreate.miceandmystics.miceandmystics.adapters.CharacterDetailsAdapter;
import com.eyecreate.miceandmystics.miceandmystics.model.Ability;

public class CharacterAbilityViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

    CharacterDetailsAdapter adapter;
    TextView abilityName;
    String uuid;

    public CharacterAbilityViewHolder(View itemView,CharacterDetailsAdapter adapter) {
        super(itemView);
        this.adapter = adapter;
        abilityName = (TextView) itemView.findViewById(R.id.ability_name);
        itemView.setOnLongClickListener(this);
    }

    public void bindHolder(Ability ability) {
        abilityName.setText(ability.getAbilityName());
        uuid = ability.getUuid();
    }

    @Override
    public boolean onLongClick(View view) {
        AlertDialog removeDialog = new AlertDialogPro.Builder(view.getContext(),R.style.dialogTheme)
                .setMessage("Do you want to remove ability: "+abilityName.getText()+"?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapter.removeAbility(uuid);
                    }
                })
                .create();
        removeDialog.show();
        return true;
    }
}
