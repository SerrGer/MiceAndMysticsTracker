package com.eyecreate.miceandmystics.miceandmystics;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.alertdialogpro.AlertDialogPro;
import com.eyecreate.miceandmystics.miceandmystics.adapters.CharacterDetailsAdapter;
import com.eyecreate.miceandmystics.miceandmystics.model.Character;
import com.eyecreate.miceandmystics.miceandmystics.model.Enums.Abilities;
import com.eyecreate.miceandmystics.miceandmystics.model.Enums.CharacterNames;
import io.realm.RealmResults;

public class CharacterDetailsActivity extends RecyclerViewActivity {

    Character currentCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.character_activity));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(getIntent().hasExtra("characterId")) {
            currentCharacter = MiceAndMysticsApplication.getRealmInstance().where(Character.class).equalTo("uuid",getIntent().getStringExtra("characterId")).findFirst();
        } else if(savedInstanceState.containsKey("characterId")) {
            currentCharacter = MiceAndMysticsApplication.getRealmInstance().where(Character.class).equalTo("uuid",savedInstanceState.getString("characterId")).findFirst();
        }
        setLayoutManager(new LinearLayoutManager(this));
        setAdapter(new CharacterDetailsAdapter(currentCharacter));
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("characterId", currentCharacter.getUuid());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_character_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_add_ability) {
            newAbilityDialog();
            return true;
        } else if(id == R.id.action_add_item) {
            newItemDialog();
            return true;
        } else if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void newAbilityDialog() {
        LayoutInflater inflator = (LayoutInflater)(new ContextThemeWrapper(this, R.style.dialogTheme)).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflator.inflate(R.layout.dialog_new_ability, null, false);
        final Spinner abilitySpinner = ((Spinner)dialogView.findViewById(R.id.ability_name));
        abilitySpinner.setAdapter(new ArrayAdapter<Abilities>(this, R.layout.simple_spinner_item, Abilities.getMatchingCharacterAbilities(CharacterNames.valueOf(currentCharacter.getCharacterName()).characterTypes())));
        abilitySpinner.setSelection(0);
        AlertDialog addDialog = new AlertDialogPro.Builder(this,R.style.dialogTheme)
                .setMessage(getString(R.string.character_ability_request))
                .setView(dialogView)
                .setPositiveButton(getString(R.string.dialog_add), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        RealmResults<Character> duplicates = MiceAndMysticsApplication.getRealmInstance().where(Character.class).equalTo("abilities.abilityName", ((Abilities) abilitySpinner.getSelectedItem()).name()).equalTo("uuid", currentCharacter.getUuid()).findAll();
                        if (duplicates.size() == 0) {
                            ((CharacterDetailsAdapter) getAdapter()).addAbility((Abilities) abilitySpinner.getSelectedItem());
                        } else {
                            Toast.makeText(CharacterDetailsActivity.this, R.string.character_ability_exists, Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .create();
        addDialog.show();
    }

    public void newItemDialog() {
        LayoutInflater inflator = (LayoutInflater)(new ContextThemeWrapper(this, R.style.dialogTheme)).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflator.inflate(R.layout.dialog_new_item, null, false);
        final EditText itemEdit = ((EditText)dialogView.findViewById(R.id.item_name));
        AlertDialog addDialog = new AlertDialogPro.Builder(this,R.style.dialogTheme)
                .setMessage(getString(R.string.character_item_request))
                .setView(dialogView)
                .setPositiveButton(getString(R.string.dialog_add), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (itemEdit.getText().length() > 0) {
                            ((CharacterDetailsAdapter) getAdapter()).addItem(itemEdit.getText().toString());
                        } else if (itemEdit.getText().length() == 0) {
                            Toast.makeText(CharacterDetailsActivity.this, R.string.character_item_blank, Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .create();
        addDialog.show();
    }
}
