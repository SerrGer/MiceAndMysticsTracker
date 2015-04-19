package com.eyecreate.miceandmystics.miceandmystics.model;

import io.realm.RealmObject;

public class BackpackItem extends RealmObject {
    //TODO:Look into seeing if it's worth hard coding these instead of manual entry.
    private String itemName;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
