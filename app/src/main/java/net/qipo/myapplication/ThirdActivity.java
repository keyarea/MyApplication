package net.qipo.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class ThirdActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Button button15 = this.findViewById(R.id.button15);
        button15.setOnClickListener((v) -> {
            ActivityCollector.finishAll();
        });
    }
}
