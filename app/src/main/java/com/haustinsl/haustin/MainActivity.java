package com.haustinsl.haustin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    private ImageView logoImageView;
    private TextView mainTextView;
    private LinearLayout btnVender, btnComprar, btnAlquilar, btnPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enlazar elementos del layout
        logoImageView = findViewById(R.id.logoImageView);
        mainTextView = findViewById(R.id.mainTextView);

        // Enlazar botones de la barra inferior
        btnVender = findViewById(R.id.btnVender);
        btnComprar = findViewById(R.id.btnComprar);
        btnAlquilar = findViewById(R.id.btnAlquilar);
        btnPerfil = findViewById(R.id.btnPerfil);

        // Configurar listeners para la barra de navegación
        btnVender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VenderActivity.class);
                startActivity(intent);
            }
        });

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ComprarActivity.class);
                startActivity(intent);
            }
        });

        btnAlquilar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlquilarActivity.class);
                startActivity(intent);
            }
        });

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
                startActivity(intent);
            }
        });

        // Opcional: mantener la interacción con el logo y texto
        logoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "¡Bienvenido a la aplicación!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        mainTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "El inicio de algo grande",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}