package com.eyecreate.miceandmystics.miceandmystics.viewholders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.alertdialogpro.AlertDialogPro;
import com.eyecreate.miceandmystics.miceandmystics.R;
import com.eyecreate.miceandmystics.miceandmystics.adapters.CharacterDetailsAdapter;
import com.eyecreate.miceandmystics.miceandmystics.model.BackpackItem;

public class CharacterItemViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

    CharacterDetailsAdapter adapter;
    TextView itemName;
    String uuid;

    public CharacterItemViewHolder(View itemView,CharacterDetailsAdapter adapter) {
        super(itemView);
        this.adapter = adapter;
        itemName = (TextView) itemView.findViewById(R.id.item_name);
        itemView.setOnLongClickListener(this);
    }

    public void bindModel(BackpackItem item) {
        itemName.setText(item.getItemName());
        uuid = item.getUuid();
    }

    @Override
    public boolean onLongClick(View view) {
        AlertDialog removeDialog = new AlertDialogPro.Builder(view.getContext(),R.style.dialogTheme)
                .setMessage("Do you want to remove item: "+itemName.getText()+"?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapter.removeItem(uuid);
                    }
                })
                .create();
        removeDialog.show();
        return true;
    }
}
