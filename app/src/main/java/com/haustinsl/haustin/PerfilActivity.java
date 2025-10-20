package com.haustinsl.haustin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.haustinsl.haustin.Perfil.PerfilDetalleActivity;
import com.haustinsl.haustin.Perfil.PerfilCompradorDetalleActivity;
import com.haustinsl.haustin.Perfil.PerfilArrendadorDetalleActivity;
import com.haustinsl.haustin.Perfil.PerfilArrendatarioDetalleActivity;
import com.haustinsl.haustin.Perfil.PerfilVendedorDetalleActivity;
import com.haustinsl.haustin.Perfil.HaustinPremiumDetalleActivity;
import com.haustinsl.haustin.Perfil.AyudaAsistenciaDetalleActivity;

public class PerfilActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Configurar clicks en los diferentes layouts
        setupClickListeners();
    }

    private void setupClickListeners() {
        // Pestaña principal del perfil
        LinearLayout perfilHeader = findViewById(R.id.perfilHeader);
        perfilHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, PerfilDetalleActivity.class);
                startActivity(intent);
            }
        });

        // Perfil Comprador
        LinearLayout compradorLayout = findViewById(R.id.compradorLayout);
        compradorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, PerfilCompradorDetalleActivity.class);
                startActivity(intent);
            }
        });

        // Perfil Arrendador
        LinearLayout arrendadorLayout = findViewById(R.id.arrendadorLayout);
        arrendadorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, PerfilArrendadorDetalleActivity.class);
                startActivity(intent);
            }
        });

        // Perfil Arrendatario
        LinearLayout arrendatarioLayout = findViewById(R.id.arrendatarioLayout);
        arrendatarioLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, PerfilArrendatarioDetalleActivity.class);
                startActivity(intent);
            }
        });

        // Perfil Vendedor
        LinearLayout vendedorLayout = findViewById(R.id.vendedorLayout);
        vendedorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, PerfilVendedorDetalleActivity.class);
                startActivity(intent);
            }
        });

        // Haustin Premium
        LinearLayout premiumLayout = findViewById(R.id.premiumLayout);
        premiumLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, HaustinPremiumDetalleActivity.class);
                startActivity(intent);
            }
        });

        // Ayuda y Asistencia
        LinearLayout ayudaLayout = findViewById(R.id.ayudaLayout);
        ayudaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, AyudaAsistenciaDetalleActivity.class);
                startActivity(intent);
            }
        });
    }
}