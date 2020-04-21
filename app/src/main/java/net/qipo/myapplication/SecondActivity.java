package net.qipo.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        // 通过getIntent用于获取启动该活动的Intent，然后调用getStringExtra传入响应的键值就可以得到传递的数据。
        // 这里传递的是String,所以用getStringExtra方法
        // 如果传递的是整数数据，则使用getIntExtra方法
        // 如果传递的是布尔型数据，则使用getBooleanExtra方法
        Intent intent = getIntent();
        String data = intent.getStringExtra("extra_data");
        Log.d(TAG, "onCreate: execute data: " + data);
        Toast.makeText(SecondActivity.this, data, Toast.LENGTH_SHORT).show();

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SecondActivity.this, "you click button3", Toast.LENGTH_SHORT).show();
            }
        });

        Button button10 = findViewById(R.id.button10);
        button10.setOnClickListener((v) -> {
            // 可以看到，我们还是构建了一个Intent，只不过仅仅用于传递数据。
            // 我们把传递的数据放在了intent中，然后调用了setResult，这个方法是专门因故向上一个活动返回数据的。
            // setResult第一个参数是用于向上一个活动返回处理结果的，一个只使用RESULT_OK或者RESULT_CANCEL
            // 第二个参数则是把带有数据的Intent传递回去
            // 然后调用finish()方法来销毁当前活动
            // 被销毁之后就会回调上一个活动的onActivityResult方法，因此我们需要在MainActivity中重写这个方法来得到返回的数据
            Intent intent1 = new Intent();
            intent1.putExtra("data_result", "Hello MainActivity");
            setResult(RESULT_OK, intent1);
            finish();
        });
    }
}
