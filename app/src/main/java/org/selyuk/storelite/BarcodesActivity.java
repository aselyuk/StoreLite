package org.selyuk.storelite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BarcodesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcodes);

        setTitle(R.string.title_barcodes);
    }
}