package ca.unb.mobiledev.stacks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import ca.unb.mobiledev.stacks.ui.home.HomeFragment;
import ca.unb.mobiledev.stacks.utils.CategoryObject;

// Class that handles manual input of expenses.
public class InputActivityOCR extends AppCompatActivity {
    private ListView listview;
    private TextView title;
    private EditText itemName;
    private EditText amount;
    private Button addExpenses;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_ocr);

        listview = findViewById(R.id.listview);
        itemName = findViewById(R.id.input_item_name);
        amount = findViewById(R.id.input_item_amount);
        addExpenses = findViewById(R.id.add_expenses);

        //user can't edit (automatic input)
        itemName.setKeyListener(null);
        amount.setKeyListener(null);
        addExpenses.setVisibility(View.GONE);

        CategoryObjectAdapter adapter;
        ArrayList<CategoryObject> myListItems  = new ArrayList<CategoryObject>();
        myListItems.addAll(SetupActivity.activeCategories);
        adapter = new CategoryObjectAdapter(myListItems);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                itemName.setText(getIntent().getStringExtra("item"));
                amount.setText(Double.toString(getIntent().getDoubleExtra("price", 0.0)));
                addExpenses.setVisibility(View.VISIBLE);
                position = i;
            }
        });
            addExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetupActivity.activeCategories.get(position).setExpense(getIntent().getDoubleExtra("price", 0.0));
                finish();
            }
        });
    }
}