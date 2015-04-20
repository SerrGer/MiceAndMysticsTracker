package com.eyecreate.miceandmystics.miceandmystics;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import com.eyecreate.miceandmystics.miceandmystics.adapters.CampaignDetailsAdapter;
import com.eyecreate.miceandmystics.miceandmystics.model.Campaign;


public class CampaignDetailsActivity extends RecyclerViewActivity {

    Campaign campaign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getIntent().getStringExtra("campaignName"));
        campaign = MiceAndMysticsApplication.getRealmInstance().where(Campaign.class).equalTo("campaignName",getIntent().getStringExtra("campaignName")).findFirst();
        setLayoutManager(new LinearLayoutManager(this));
        setAdapter(new CampaignDetailsAdapter(campaign));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_campaign_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_character) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
