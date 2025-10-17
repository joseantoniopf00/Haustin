package com.haustinsl.haustin;  // ← Package corregido

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private ImageView logoImageView;
    private TextView mainTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enlazar elementos del layout
        logoImageView = findViewById(R.id.logoImageView);
        //mainTextView = findViewById(R.id.mainTextView);

        // Opcional: agregar interacción al logo
        logoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "Bienvenido Sola",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Opcional: interacción con el texto
        /*
        mainTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "El inicio de algo grande",
                        Toast.LENGTH_SHORT).show();
            }
        }); */
    }
}