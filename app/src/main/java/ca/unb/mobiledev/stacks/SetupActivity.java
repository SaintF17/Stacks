package ca.unb.mobiledev.stacks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;

import ca.unb.mobiledev.stacks.utils.CategoryObject;

public class SetupActivity extends AppCompatActivity {

    //Arraylist of all initial categories
    private ArrayList<CategoryObject> objects;
    //Arraylist of all selected categories
    public static ArrayList<CategoryObject> activeCategories;
    private CategoryObject rent;
    private CategoryObject entertainment;
    private CategoryObject fuel;
    private CategoryObject groceries;
    private CategoryObject phone;
    private CategoryObject utilities;

    private CheckBox rent_box;
    private CheckBox entertainment_box;
    private CheckBox fuel_box;
    private CheckBox groceries_box;
    private CheckBox phone_box;
    private CheckBox utilities_box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        objects = new ArrayList<>();
        activeCategories = new ArrayList<>();

        rent = new CategoryObject("Rent", findViewById(R.id.text_rent));
        objects.add(rent);
        entertainment = new CategoryObject("Entertainment", findViewById(R.id.text_entertainment));
        objects.add(entertainment);
        fuel = new CategoryObject("Fuel", findViewById(R.id.text_fuel));
        objects.add(fuel);
        groceries = new CategoryObject("Groceries", findViewById(R.id.text_groceries));
        objects.add(groceries);
        phone = new CategoryObject("Phone", findViewById(R.id.text_phone));
        objects.add(phone);
        utilities = new CategoryObject("Utilities", findViewById(R.id.text_utilities));
        objects.add(utilities);

        rent.getText().setVisibility(View.INVISIBLE);
        entertainment.getText().setVisibility(View.INVISIBLE);
        fuel.getText().setVisibility(View.INVISIBLE);
        groceries.getText().setVisibility(View.INVISIBLE);
        phone.getText().setVisibility(View.INVISIBLE);
        utilities.getText().setVisibility(View.INVISIBLE);

        rent_box = findViewById(R.id.Rent);
        entertainment_box = findViewById(R.id.Entertainment);
        fuel_box = findViewById(R.id.Fuel);
        groceries_box = findViewById(R.id.Groceries);
        phone_box = findViewById(R.id.Phone);
        utilities_box = findViewById(R.id.Utilities);

        View.OnClickListener checkBoxListener = v -> {

            if (rent_box.isChecked())
                rent.getText().setVisibility(View.VISIBLE);
            if(!rent_box.isChecked())
                rent.getText().setVisibility(View.INVISIBLE);

            if (entertainment_box.isChecked())
                entertainment.getText().setVisibility(View.VISIBLE);
            if(!entertainment_box.isChecked())
                entertainment.getText().setVisibility(View.INVISIBLE);

            if (fuel_box.isChecked())
                fuel.getText().setVisibility(View.VISIBLE);
            if(!fuel_box.isChecked())
                fuel.getText().setVisibility(View.INVISIBLE);

            if (groceries_box.isChecked())
                groceries.getText().setVisibility(View.VISIBLE);
            if(!groceries_box.isChecked())
                groceries.getText().setVisibility(View.INVISIBLE);

            if (phone_box.isChecked())
                phone.getText().setVisibility(View.VISIBLE);
            if(!phone_box.isChecked())
                phone.getText().setVisibility(View.INVISIBLE);

            if (utilities_box.isChecked())
                utilities.getText().setVisibility(View.VISIBLE);
            if(!utilities_box.isChecked())
                utilities.getText().setVisibility(View.INVISIBLE);

        };
        rent_box.setOnClickListener(checkBoxListener);
        entertainment_box.setOnClickListener(checkBoxListener);
        fuel_box.setOnClickListener(checkBoxListener);
        groceries_box.setOnClickListener(checkBoxListener);
        phone_box.setOnClickListener(checkBoxListener);
        utilities_box.setOnClickListener(checkBoxListener);

        // Button to go to the navigation page
        Button next = findViewById(R.id.catergory_next_btn);
        next.setOnClickListener(view -> {
            //Loop to populate active categories. Probably a cleaner way of doing it but this prevents concurrency exceptions.
            for (CategoryObject o: objects) {
                if((o.getText().getVisibility() == View.VISIBLE))
                    activeCategories.add(o);
            }
            Intent intent = new Intent(SetupActivity.this, NavigationActivity.class);
            startActivity(intent);
        });

    }
}