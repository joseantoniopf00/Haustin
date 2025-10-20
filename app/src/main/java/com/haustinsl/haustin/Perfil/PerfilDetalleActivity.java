package com.haustinsl.haustin.Perfil;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.haustinsl.haustin.R;

public class PerfilDetalleActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_detalle);

        TextView textView = findViewById(R.id.textView);
        textView.setText("Detalles del perfil de Ale Sosola");
    }
}