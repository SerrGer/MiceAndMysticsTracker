package com.eyecreate.miceandmystics.miceandmystics.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.alertdialogpro.AlertDialogPro;
import com.eyecreate.miceandmystics.miceandmystics.MiceAndMysticsApplication;
import com.eyecreate.miceandmystics.miceandmystics.R;
import com.eyecreate.miceandmystics.miceandmystics.model.*;
import com.eyecreate.miceandmystics.miceandmystics.model.Character;
import com.eyecreate.miceandmystics.miceandmystics.viewholders.PlayerViewHolder;
import io.realm.RealmResults;

import java.util.Iterator;

public class PlayerManagerAdapter extends RecyclerView.Adapter<PlayerViewHolder> {

    LayoutInflater inflater;
    RealmResults<Player> playerList;

    public PlayerManagerAdapter() {
        playerList = MiceAndMysticsApplication.getRealmInstance().where(Player.class).findAll();
    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(inflater == null) inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new PlayerViewHolder(inflater.inflate(R.layout.item_player,parent,false),this);
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        holder.bindHolder(playerList.get(position));
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public void removePlayer(final String playerName,Context ctx) {
        //This part checks to see if any characters are attached to this player.
        final RealmResults<Character> playerCharacters = MiceAndMysticsApplication.getRealmInstance().where(com.eyecreate.miceandmystics.miceandmystics.model.Character.class)
                .equalTo("controllingPlayer.playerName", playerName).findAll();
        if(playerCharacters.size()>0) {
            AlertDialog removeDialog = new AlertDialogPro.Builder(ctx,R.style.dialogTheme)
                    .setMessage(ctx.getString(R.string.remove_player_confirm_1)+playerName+ctx.getString(R.string.remove_player_confirm_2))
                    .setNegativeButton(ctx.getString(R.string.dialog_no), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .setPositiveButton(ctx.getString(R.string.dialog_yes), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            for (Iterator iter = playerCharacters.iterator(); playerCharacters.iterator().hasNext(); ) {
                                CampaignDetailsAdapter.removeCharacterFromDB((Character) iter.next());
                            }
                            MiceAndMysticsApplication.getRealmInstance().beginTransaction();
                            MiceAndMysticsApplication.getRealmInstance().where(Player.class).equalTo("playerName", playerName).findFirst().deleteFromRealm();
                            MiceAndMysticsApplication.getRealmInstance().commitTransaction();
                            notifyDataSetChanged();
                        }
                    })
                    .create();
            removeDialog.show();
        } else {
            AlertDialog removeDialog = new AlertDialogPro.Builder(ctx, R.style.dialogTheme)
                    .setMessage(ctx.getString(R.string.remove_empty_player_confirm) + playerName + "?")
                    .setNegativeButton(ctx.getString(R.string.dialog_no), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .setPositiveButton(ctx.getString(R.string.dialog_yes), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MiceAndMysticsApplication.getRealmInstance().beginTransaction();
                            MiceAndMysticsApplication.getRealmInstance().where(Player.class).equalTo("playerName",playerName).findFirst().deleteFromRealm();
                            MiceAndMysticsApplication.getRealmInstance().commitTransaction();
                            notifyDataSetChanged();
                        }
                    })
                    .create();
            removeDialog.show();
        }
    }
}
