package org.selyuk.storelite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnOnClick(View view) {
        String tag = view.getTag().toString();
        String packageName = getPackageName();
        try {
            Class<?> foundClass = Class.forName(packageName + "." + tag + "Activity");
            Intent intent = new Intent(this, foundClass);
            startActivity(intent);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            Log.d("ERROR", Objects.requireNonNull(ex.getMessage()));
        }
    }
}