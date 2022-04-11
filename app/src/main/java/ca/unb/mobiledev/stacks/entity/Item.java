package ca.unb.mobiledev.stacks.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "item_table")  // Represents a SQLite table
public class Item {

    // currently fully unused, but will be
    // crucial to future features, including
    // items per category & chart details,
    // as well as proper aggregated budget totals

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private float price;
    private String categoryName;

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

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
