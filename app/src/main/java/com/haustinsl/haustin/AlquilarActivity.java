package com.haustinsl.haustin;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AlquilarActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alquilar);

        TextView textView = findViewById(R.id.textView);
        textView.setText("Estás en la actividad Alquilar");
    }
}