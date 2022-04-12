package ca.unb.mobiledev.stacks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ca.unb.mobiledev.stacks.ui.home.HomeFragment;

// Class that handles manual input of expenses.
public class InputActivity extends AppCompatActivity {
    private TextView title;
    private EditText itemName;
    private EditText amount;
    private Button addExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        title = findViewById(R.id.textView5);
        itemName = findViewById(R.id.input_item_name);
        amount = findViewById(R.id.input_item_amount);
        addExpenses = findViewById(R.id.add_expenses);

        title.setText("Add expenses for " + getIntent().getStringExtra("Title"));

        addExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InputActivity.this, NavigationActivity.class);
                /* Will change when db is properly set up. Currently just finds the CategoryObject
                that was clicked on (from the arraylist of active activities) and updates its expense value.
                */
                if(amount.getText().toString().equals("") || amount.getText().toString().contains(".")){
                    Toast.makeText(getApplicationContext(), "Whole numbers only", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    SetupActivity.activeCategories.get(getIntent().getIntExtra("Index", -1))
                            .setExpense(Integer.parseInt(amount.getText().toString()));
                    startActivity(intent);
                }
            }
        });
    }
}