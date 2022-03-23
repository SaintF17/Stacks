package ca.unb.mobiledev.stacks.utils;

import android.widget.EditText;

import java.util.ArrayList;

public class CategoryObject {
    private String name;
    private EditText budgetText;

    // Store the related items in a category in an arr of ItemObjects
    // would this be easier if implemented as a <T> instead (or linked list?)
    private ArrayList<ItemObject> itemObjects;

    public CategoryObject(String name, EditText text) {
        this.name = name;
        this.budgetText = text;
        itemObjects = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EditText getText() {
        return budgetText;
    }

    public void setText(EditText text) {
        this.budgetText = text;
    }
}
