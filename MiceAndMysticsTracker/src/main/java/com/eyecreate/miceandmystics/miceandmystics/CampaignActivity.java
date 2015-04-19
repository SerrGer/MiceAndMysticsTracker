package com.eyecreate.miceandmystics.miceandmystics;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import com.eyecreate.miceandmystics.miceandmystics.adapters.CampaignAdapter;
import com.eyecreate.miceandmystics.miceandmystics.model.Enums.CampaignType;


public class CampaignActivity extends RecyclerViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setLayoutManager(new LinearLayoutManager(this));
        setAdapter(new CampaignAdapter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_campaign, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_campaign) {
            LayoutInflater inflator = (LayoutInflater)(new ContextThemeWrapper(this, R.style.dialogTheme)).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View dialogView = inflator.inflate(R.layout.dialog_new_campaign, null, false);
            final Spinner typeSpinner = ((Spinner)dialogView.findViewById(R.id.campaign_type));
            typeSpinner.setAdapter(new ArrayAdapter<CampaignType>(this, R.layout.simple_spinner_item, CampaignType.values()));
            typeSpinner.setSelection(0);
            AlertDialog addDialog = new AlertDialog.Builder(this,R.style.dialogTheme)
                    .setMessage("Please give your new campaign a unique name:")
                    .setView(dialogView)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ((CampaignAdapter) getAdapter()).addItem(((EditText) dialogView.findViewById(R.id.campagin_name)).getText().toString(), CampaignType.valueOfDisplayName(typeSpinner.getSelectedItem().toString()));
                        }
                    })
                    .create();
            addDialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
