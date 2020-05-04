package net.qipo.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class LaunchModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode);

        Button button14 = findViewById(R.id.button14);
        button14.setOnClickListener((v) -> {
            Intent intent = new Intent(this, LaunchModeActivity.class);
            startActivity(intent);
        });
    }
}
