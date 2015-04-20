package com.eyecreate.miceandmystics.miceandmystics;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.eyecreate.miceandmystics.miceandmystics.adapters.CampaignDetailsAdapter;
import com.eyecreate.miceandmystics.miceandmystics.model.Campaign;
import com.eyecreate.miceandmystics.miceandmystics.model.Enums.CharacterNames;
import com.eyecreate.miceandmystics.miceandmystics.model.Player;
import io.realm.RealmResults;


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
            newCharacterDialog();
            return true;
        } else if (id == R.id.action_add_player) {
            newPlayerDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void newPlayerDialog() {
        LayoutInflater inflator = (LayoutInflater)(new ContextThemeWrapper(this, R.style.dialogTheme)).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflator.inflate(R.layout.dialog_new_player, null, false);
        final EditText playerEdit = ((EditText)dialogView.findViewById(R.id.player_name));
        AlertDialog addDialog = new AlertDialog.Builder(this,R.style.dialogTheme)
                .setMessage("Please name the new player:")
                .setView(dialogView)
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (playerEdit.getText().length()>0) {
                            MiceAndMysticsApplication.getRealmInstance().beginTransaction();
                            Player player = MiceAndMysticsApplication.getRealmInstance().createObject(Player.class);
                            player.setPlayerName(playerEdit.getText().toString());
                            MiceAndMysticsApplication.getRealmInstance().copyToRealmOrUpdate(player);
                            MiceAndMysticsApplication.getRealmInstance().commitTransaction();
                        } else {
                            Toast.makeText(CampaignDetailsActivity.this,"Can not have a blank name!",Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .create();
        addDialog.show();
    }

    public void newCharacterDialog() {
        LayoutInflater inflator = (LayoutInflater)(new ContextThemeWrapper(this, R.style.dialogTheme)).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflator.inflate(R.layout.dialog_new_character, null, false);
        final Spinner charSpinner = ((Spinner)dialogView.findViewById(R.id.character_type));
        charSpinner.setAdapter(new ArrayAdapter<CharacterNames>(this, R.layout.simple_spinner_item, CharacterNames.values()));
        charSpinner.setSelection(0);
        final Spinner playerSpinner = ((Spinner)dialogView.findViewById(R.id.player_name));
        RealmResults<Player> players = MiceAndMysticsApplication.getRealmInstance().where(Player.class).findAll();
        playerSpinner.setAdapter(new ArrayAdapter<String>(this, R.layout.simple_spinner_item, Player.convertPlayerListToStringArray(players.subList(0,players.size()))));
        playerSpinner.setSelection(0);
        AlertDialog addDialog = new AlertDialog.Builder(this,R.style.dialogTheme)
                .setMessage("Please select your character and controlling player:")
                .setView(dialogView)
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(playerSpinner.getSelectedItem() != null) {
                            ((CampaignDetailsAdapter) getAdapter()).addCharacter(CharacterNames.valueOf(charSpinner.getSelectedItem().toString()), MiceAndMysticsApplication.getRealmInstance().where(Player.class).equalTo("playerName", playerSpinner.getSelectedItem().toString()).findFirst());
                        } else {
                            Toast.makeText(CampaignDetailsActivity.this,"No player selected. Maybe try making one first.",Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .create();
        addDialog.show();
    }
}
