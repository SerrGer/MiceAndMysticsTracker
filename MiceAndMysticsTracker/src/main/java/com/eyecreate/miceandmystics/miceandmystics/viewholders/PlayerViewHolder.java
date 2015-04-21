package com.eyecreate.miceandmystics.miceandmystics.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.eyecreate.miceandmystics.miceandmystics.R;
import com.eyecreate.miceandmystics.miceandmystics.adapters.PlayerManagerAdapter;
import com.eyecreate.miceandmystics.miceandmystics.model.Player;

public class PlayerViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

    TextView playerName;
    PlayerManagerAdapter adapter;

    public PlayerViewHolder(View itemView,PlayerManagerAdapter adapter) {
        super(itemView);
        this.adapter = adapter;
        playerName = (TextView)itemView.findViewById(R.id.player_name);
        itemView.setOnLongClickListener(this);
    }

    public void bindHolder(Player player) {
        playerName.setText(player.getPlayerName());
    }

    @Override
    public boolean onLongClick(View view) {
        adapter.removePlayer(playerName.getText().toString(),view.getContext());
        return true;
    }
}
