package com.example.loginjaviergallegos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    //Contador de intentos fallidos
    private int intentosFallidos = 0;

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
                // Acceso correcto, reinicio el contador y el mensaje depende del interruptor
                intentosFallidos = 0;
                if (swRecordar.isChecked()) {
                    tvMensaje.setText(R.string.acceso_correcto_recordar);
                } else {
                    tvMensaje.setText(R.string.acceso_correcto);
                }
                tvMensaje.setTextColor(ContextCompat.getColor(this, R.color.correcto));

                // Cuando correo y contraseña son válidos:
                Intent intent = new Intent(this, MainActivity2.class);
                intent.putExtra(MainActivity2.EXTRA_CORREO, correo);
                startActivity(intent);
                finish();

            } else {
                // Datos incorrectos cuento el fallo y mensaje en rojo
                intentosFallidos++;
                tvMensaje.setText(R.string.acceso_incorrecto);
                tvMensaje.setTextColor(ContextCompat.getColor(this, R.color.incorrecto));
                intentosFallidos++;

                if (intentosFallidos >= 3) {
                    //Al tercer fallo bloqueo el botón y aviso con un mensaje y el Toast
                    btnContinuar.setEnabled(false);
                    tvMensaje.setText(R.string.intentos_agotados);
                    Toast.makeText(this, R.string.intentos_agotados, Toast.LENGTH_SHORT).show();
                } else {
                    tvMensaje.setText(R.string.acceso_incorrecto);
                }
                //El mensaje de erro siempre va en rojo
                tvMensaje.setTextColor(ContextCompat.getColor(this, R.color.incorrecto));
            }
        });
    }
}