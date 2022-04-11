package ca.unb.mobiledev.stacks.utils;

import android.widget.EditText;

// Should only be used to make mutable budget $ amount
// when first choosing pre-made splash screen categories
public class CategoryObject {
    private String name;
    private EditText budgetText;
    private double expense;
    private double budgetLimit;

    public CategoryObject(String name, EditText text) {
        this.name = name;
        this.budgetText = text;
        expense = 0;
    }

    // Secondary constructor for future use to make
    // objects in lists w/o Edittext, mirrors params
    // of the Repo Category entity
    public CategoryObject(String name, double budgetLimit) {
        this.name = name;
        this.budgetLimit = budgetLimit;
        expense = 0;
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

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense += expense;
    }

    // to use in lieu of edittext
    // i.e. when making RecyclerViews
    public double getBudgetLimit() {
        return budgetLimit;
    }

    public void setBudgetLimit(double budgetLimit) {
        this.budgetLimit = budgetLimit;
    }

}
