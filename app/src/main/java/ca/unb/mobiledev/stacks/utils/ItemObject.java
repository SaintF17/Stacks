package ca.unb.mobiledev.stacks.utils;

public class ItemObject {
    private String name;
    private float price;

    public ItemObject(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }

    public void setName(String newName) { this.name = newName; }

    // return the item price, raw float therefore needs formatting with $, etc
    public float getPrice(float price) { return price; }

    // change the item price (when editing pre-inputted purchases)
    public void setPrice(float price) { this.price = price; }
}
