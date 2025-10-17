package com.haustinsl.haustin;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class AlquilarActivity extends Activity {

    private Button btnPasar, btnMeInteresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alquilar);

        // Enlazar elementos
        btnPasar = findViewById(R.id.btnPasar);
        btnMeInteresa = findViewById(R.id.btnMeInteresa);

        // Configurar listeners
        btnPasar.setOnClickListener(v -> {
            Toast.makeText(AlquilarActivity.this,
                    "Pasando a siguiente propiedad",
                    Toast.LENGTH_SHORT).show();
            // Aquí iría la lógica para pasar a la siguiente propiedad
        });

        btnMeInteresa.setOnClickListener(v -> {
            Toast.makeText(AlquilarActivity.this,
                    "¡Genial! Te contactaremos pronto",
                    Toast.LENGTH_SHORT).show();
            // Aquí iría la lógica para guardar el interés
        });
    }
}