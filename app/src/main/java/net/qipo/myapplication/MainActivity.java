package net.qipo.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置布局
        setContentView(R.layout.activity_main);
        // 打印日志
        Log.d(TAG, "onCreate execute");
        // 在活动中，可以通过findViewById获得到在布局文件中定义的元素,获得Button的实例
        Button button1 = findViewById(R.id.button1);
        // 我们可以在实例上调用该方法为实例注册一个监听器
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "You click button1",
                        Toast.LENGTH_SHORT).show();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity类中提供了一个finish方法，调用该方法就可以销毁当前活动了
                finish();
            }
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent有多个构造函数的重载，其中一个就是这个，第一个参数为启动活动的上下文，第二个参乎Class则是指定要想启动的目标活动
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                // Activity类中提供了一个Activity方法，这个方法专门用于启动活动，它接受一个Intent参数
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 通过getMenuInflater方法能够得到MenuInflater对象
        // 然后调用MenuInflater对象的inflate方法就可以给当前活动创建菜单了
        getMenuInflater().inflate(R.menu.main, menu);
        // 返回true，表示允许创建的菜单显示出来。如果返回false，创建的菜单将无法显示
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 在该方法中，我们通过item.getItemId来判断我们点击的是哪个菜单项
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(MainActivity.this, "You click add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(MainActivity.this, "You click remove", Toast.LENGTH_SHORT).show();
                break;
                default:
        }
        return true;
    }
}
