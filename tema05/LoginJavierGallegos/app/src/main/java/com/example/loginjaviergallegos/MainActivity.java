package com.example.loginjaviergallegos;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.materialswitch.MaterialSwitch;

public class MainActivity extends AppCompatActivity {
    // Datos de nuestro usuario válido
    private static final String CORREO_VALIDO = "javier@correo.com";
    private static final String CLAVE_VALIDA = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Busco cada elemento de la pantalla por su id y lo guardo en una variable
        EditText etCorreo = findViewById(R.id.editTextTextEmailAddress);
        EditText etClave = findViewById(R.id.editTextTextPassword);
        MaterialSwitch swRecordar = findViewById(R.id.switchRecordar);
        Button btnContinuar = findViewById(R.id.button);
        TextView tvMensaje = findViewById(R.id.textView);

        btnContinuar.setOnClickListener(v -> {
            // Leo lo escrito y al correo le quito los espacios de los extremos
            String correo = etCorreo.getText().toString().trim();
            String clave = etClave.getText().toString();

            // Si algun campo está vacio aviso en esa caja y salgo sin comprobar nada más
            if (correo.isEmpty()) {
                etCorreo.setError(getString(R.string.falta_correo));
                return;
            }

            if (clave.isEmpty()) {
                etClave.setError(getString(R.string.falta_clave));
                return;
            }

            if (correo.equals(CORREO_VALIDO) && clave.equals(CLAVE_VALIDA)) {
                // Acceso correcto, el mensaje depende del interruptor
                if (swRecordar.isChecked()) {
                    tvMensaje.setText(R.string.acceso_correcto_recordar);
                } else {
                    tvMensaje.setText(R.string.acceso_correcto);
                }
                tvMensaje.setTextColor(ContextCompat.getColor(this, R.color.correcto));

            } else {
                // Datos incorrectos y mensaje en rojo
                tvMensaje.setText(R.string.acceso_incorrecto);
                tvMensaje.setTextColor(ContextCompat.getColor(this, R.color.incorrecto));
            }
        });
    }
}