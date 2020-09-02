package com.optiv.ndkcrackme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Just blindly retrieving a string
        final TextView s_tv = findViewById(R.id.simple_text);

        s_tv.setText(a());

        // Very simple hardcoded password validation
        final TextView pw_tv = findViewById(R.id.pw_check);
        final EditText pw_et = findViewById(R.id.pw_entry);
        final Button pw_btn = findViewById(R.id.pw_button);

        pw_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String pw = pw_et.getText().toString();
                if (b(pw)) {
                    pw_tv.setText("Password accepted!");
                } else {
                    pw_tv.setText("Password rejected!");
                }
            }
        });

        // Very simple key-value store
        final TextView kv_tv = findViewById(R.id.kv_out);
        final EditText kv_k_et = findViewById(R.id.kv_key);
        final EditText kv_v_et = findViewById(R.id.kv_value);
        final Button kv_rd = findViewById(R.id.read_button);
        final Button kv_wt = findViewById(R.id.write_button);

        c();

        kv_rd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String key = kv_k_et.getText().toString();
                String value = d(key);
                kv_v_et.setText(value);

                if (value.length() == 0)
                    kv_tv.setText("Value NOT found.");
                else
                    kv_tv.setText("Value found.");
            }
        });

        kv_wt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String key = kv_k_et.getText().toString();
                String value = kv_v_et.getText().toString();
                e(key, value);

                kv_tv.setText("Value stored!");
                kv_v_et.setText("");
            }
        });
    }

    public native String a();

    public native boolean b(String password);

    public native void c();
    public native String d(String key);
    public native void e(String key, String value);
}
