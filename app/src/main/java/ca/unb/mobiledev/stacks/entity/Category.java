package ca.unb.mobiledev.stacks.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category_table")  // Represents a SQLite table
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private double budgetAmount;  // double is simplest datatype for now

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(double budgetAmount) {
        this.budgetAmount = budgetAmount;
    }
}
