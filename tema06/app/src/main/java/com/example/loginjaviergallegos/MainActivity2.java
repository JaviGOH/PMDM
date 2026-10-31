package com.example.loginjaviergallegos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity2 extends AppCompatActivity {

    public static final String EXTRA_CORREO = "correo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        Button btnCerrarSesion = findViewById(R.id.btn_cerrar_sesion);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Activar la barra superior (Toolbar)
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Obtener el correo recibido del Intent y mostrar el saludo
        String correo = getIntent().getStringExtra(EXTRA_CORREO);
        TextView tview_saludo_con_correo = findViewById(R.id.tview_saludo_con_correo);
        if (correo != null) {
            tview_saludo_con_correo.setText(getString(R.string.tview_saludo_con_correo, correo));
        }

        //El listener para cuando pulsemos el boton cerrar sesión ejecute finish() y cierre la segunda view
        btnCerrarSesion.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish(); // Cierra el menú para volver al Login
        });

    }
}