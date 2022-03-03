package ca.unb.mobiledev.stacks.utils;

import android.widget.EditText;

public class CategoryObject {
    private String name;
    private EditText text;

    public CategoryObject(String name, EditText text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EditText getText() {
        return text;
    }

    public void setText(EditText text) {
        this.text = text;
    }
}
