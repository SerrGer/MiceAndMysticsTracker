package com.eyecreate.miceandmystics.miceandmystics;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import com.eyecreate.miceandmystics.miceandmystics.adapters.PlayerManagerAdapter;

public class PlayerManagerActivity extends RecyclerViewActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.players_activity));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setLayoutManager(new LinearLayoutManager(this));
        setAdapter(new PlayerManagerAdapter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player_manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_player) {
            CampaignDetailsActivity.newPlayerDialog(this);
            return true;
        } else if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
