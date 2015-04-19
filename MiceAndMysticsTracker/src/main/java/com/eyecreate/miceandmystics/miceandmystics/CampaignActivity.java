package com.eyecreate.miceandmystics.miceandmystics;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import com.eyecreate.miceandmystics.miceandmystics.adapters.CampaignAdapter;


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
            final EditText newName = new EditText(new ContextThemeWrapper(this,R.style.editTextDialogTheme));
            newName.setTypeface(Typeface.createFromAsset(getAssets(),"ArchitectsDaughter.ttf"));
            AlertDialog addDialog = new AlertDialog.Builder(this,R.style.dialogTheme)
                    .setMessage("Please give your new campaign a unique name:")
                    .setView(newName)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ((CampaignAdapter) getAdapter()).addItem(newName.getText().toString());
                        }
                    })
                    .create();
            addDialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
