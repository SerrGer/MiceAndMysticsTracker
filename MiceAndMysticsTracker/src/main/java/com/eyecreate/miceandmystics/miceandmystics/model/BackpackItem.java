package com.eyecreate.miceandmystics.miceandmystics.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class BackpackItem extends RealmObject {
    //TODO:Look into seeing if it's worth hard coding these instead of manual entry.
    @PrimaryKey
    private String itemName;

    public BackpackItem() {}

    public BackpackItem(String itemName) {setItemName(itemName);}

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
