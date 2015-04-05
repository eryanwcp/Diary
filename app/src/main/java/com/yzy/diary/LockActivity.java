package com.yzy.diary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Description: 日记本加锁
 * User: NOLE( 308989414@qq.com )
 * Date: 2015-03-29
 * Time: 20:22
 */
public class LockActivity extends ActionBarActivity {
    SharedPreferences preferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);
        preferences = getSharedPreferences("diary", MODE_PRIVATE);

        if (!preferences.getBoolean("lockable", false)) {
            Intent intent = new Intent(LockActivity.this, ListActivity.class);
            startActivity(intent);
            finish();
        } else {
            Button button = (Button) findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText editText = (EditText) findViewById(R.id.editText);
                    String password = editText.getText().toString();
                    if (password.equals(preferences.getString("key", null))) {
                        Intent intent = new Intent(LockActivity.this, ListActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LockActivity.this, "密码不正确!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}