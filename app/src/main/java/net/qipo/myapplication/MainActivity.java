package net.qipo.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
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

        // 销毁当前活动
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activity类中提供了一个finish方法，调用该方法就可以销毁当前活动了
                finish();
            }
        });

        // 显式活动打开一个活动
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

        // 隐式Intent打开一个活动
        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener((v)-> {
            // 我们使用了Intent的另一个构造函数，直接将action的字符串传了进去，表明我们想要启动能够响应这个字符串的活动。
            // 我们之前在xml中指定的是默认的category，在调用startActivity方法的时候会自动将这个category添加到Intent中
            Intent intent = new Intent("net.qipo.myapplication.ACTION_START");
            // 如果xml中不包含net.qipo.myapplication.MY_CATEGORY,程序就会崩溃，没有任何一个活动可以响应我们的Intent
            // 一个活动只能指定一个action,但是可以包含多个category，将其加上就可以响应
            intent.addCategory("net.qipo.myapplication.MY_CATEGORY");
            startActivity(intent);
        });

        // 打开一个网页
        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener((v) -> {
            // Intent.ACTION_VIEW，这是一个Android系统的内置动作，其常量值为android.intent.action.VIEW
            // 通过Uri.parse()方法将一个字符串解析为Uri对象
            // 再调用Intent的setData方法将这个Uri对象传递进去
            Intent intent = new Intent(Intent.ACTION_VIEW);
            // 这个方法接受一个Uri对象，主要用于指定当前Intent正在操作的数据。
            intent.setData(Uri.parse("http://baidu.com"));
            startActivity(intent);
        });

        // 拨打电话
        Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener((v) -> {
            // 除了http协议，我们可以指定很多其他协议，比如geo标识地理位置，tel标识拨打电话
            // Intent.ACTION_DIAL，这是android系统的内置动作
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        });

        // 向下一个活动传递数据
        Button button8 = findViewById(R.id.button8);
        button8.setOnClickListener((v) -> {
            String data = "hello world";
            // 通过putExtra方法传递一个字符串，注意这里putExtra接收两个参数，第一个参数是键，第二个参数才是真正要传递的数据
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("extra_data", data);
            startActivity(intent);

        });

        // 返回数据给上一个活动
        Button button9 = findViewById(R.id.button9);
        button9.setOnClickListener((v) -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            // 该方法第二个参数是请求码，用于在之后的回调中判断数据的来源，请求码只要是一个唯一值就可以。
            startActivityForResult(intent, 1);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // requestCode：第一个参数是我们在启动活动时传入的请求码
        // resultCode: 第二个参数即我们在返回数据时传入的处理结果
        // data: 第三个参数即携带着返回数据的Intent
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String resultedData = data.getStringExtra("data_result");
                    Log.d(TAG, "onActivityResult: " + resultedData);
                }
                break;
                default:
        }
    }
}
