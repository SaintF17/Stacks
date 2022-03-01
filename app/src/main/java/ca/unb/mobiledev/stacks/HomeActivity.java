package ca.unb.mobiledev.stacks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;

public class HomeActivity extends AppCompatActivity {

    private EditText rent_txt;
    private EditText entertainment_txt;
    private EditText fuel_txt;
    private EditText groceries_txt;
    private EditText phone_txt;
    private EditText utilities_txt;

    private CheckBox rent_box;
    private CheckBox entertainment_box;
    private CheckBox fuel_box;
    private CheckBox groceries_box;
    private CheckBox phone_box;
    private CheckBox utilities_box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button next = findViewById(R.id.catergory_next_btn);
        next.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, NavigationActivity.class);
            startActivity(intent);
        });
        //--------------------------------------------------------- EDITTEXT
        rent_txt = findViewById(R.id.text_rent);
        entertainment_txt = findViewById(R.id.text_entertainment);
        fuel_txt = findViewById(R.id.text_fuel);
        groceries_txt = findViewById(R.id.text_groceries);
        phone_txt = findViewById(R.id.text_phone);
        utilities_txt = findViewById(R.id.text_utilities);

        rent_txt.setVisibility(View.INVISIBLE);
        entertainment_txt.setVisibility(View.INVISIBLE);
        fuel_txt.setVisibility(View.INVISIBLE);
        groceries_txt.setVisibility(View.INVISIBLE);
        phone_txt.setVisibility(View.INVISIBLE);
        utilities_txt.setVisibility(View.INVISIBLE);

        //--------------------------------------------------------- EDITTEXT
        //--------------------------------------------------------- CHECKBOX
        rent_box = findViewById(R.id.Rent);
        entertainment_box = findViewById(R.id.Entertainment);
        fuel_box = findViewById(R.id.Fuel);
        groceries_box = findViewById(R.id.Groceries);
        phone_box = findViewById(R.id.Phone);
        utilities_box = findViewById(R.id.Utilities);

        View.OnClickListener checkBoxListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (rent_box.isChecked())
                    rent_txt.setVisibility(View.VISIBLE);
                if(!rent_box.isChecked())
                    rent_txt.setVisibility(View.INVISIBLE);

                if (entertainment_box.isChecked())
                    entertainment_txt.setVisibility(View.VISIBLE);
                if(!entertainment_box.isChecked())
                    entertainment_txt.setVisibility(View.INVISIBLE);

                if (fuel_box.isChecked())
                    fuel_txt.setVisibility(View.VISIBLE);
                if(!fuel_box.isChecked())
                    fuel_txt.setVisibility(View.INVISIBLE);

                if (groceries_box.isChecked())
                    groceries_txt.setVisibility(View.VISIBLE);
                if(!groceries_box.isChecked())
                    groceries_txt.setVisibility(View.INVISIBLE);

                if (phone_box.isChecked())
                    phone_txt.setVisibility(View.VISIBLE);
                if(!phone_box.isChecked())
                    phone_txt.setVisibility(View.INVISIBLE);

                if (utilities_box.isChecked())
                    utilities_txt.setVisibility(View.VISIBLE);
                if(!utilities_box.isChecked())
                    utilities_txt.setVisibility(View.INVISIBLE);

            }
        };
        rent_box.setOnClickListener(checkBoxListener);
        entertainment_box.setOnClickListener(checkBoxListener);
        fuel_box.setOnClickListener(checkBoxListener);
        groceries_box.setOnClickListener(checkBoxListener);
        phone_box.setOnClickListener(checkBoxListener);
        utilities_box.setOnClickListener(checkBoxListener);
    }
}