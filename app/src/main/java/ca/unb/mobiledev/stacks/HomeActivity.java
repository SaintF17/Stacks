package ca.unb.mobiledev.stacks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button next = findViewById(R.id.catergory_next_btn);
        next.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, NavigationActivity.class);
            startActivity(intent);
        });
    }
}