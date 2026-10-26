package com.example.listajaviergallegos;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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

        // 1. Los datos: al menos 8 elementos
        ArrayList<Plato> platos = new ArrayList<>();
        platos.add(new Plato("Albóndigas en salsa", "Receta casera", R.drawable.albondigas));
        platos.add(new Plato("Canelones", "Receta casera", R.drawable.canelones));
        platos.add(new Plato("Croquetas de serrano", "Receta casera", R.drawable.croquetas));
        platos.add(new Plato("Lentejas caseras", "Receta casera", R.drawable.lentejas_caseras));
        platos.add(new Plato("Pisto manchego con huevo", "Receta manchega", R.drawable.pisto_huevo));
        platos.add(new Plato("Salmorejo", "Receta tradicional", R.drawable.salmorejo));
        platos.add(new Plato("Ternera a la jardinera", "Receta premium", R.drawable.ternera_jadinera));
        platos.add(new Plato("Tortilla de patatas", "Receta premium", R.drawable.tortilla_patatas));
        platos.add(new Plato("Ramen estilo japonés", "Receta oriental", R.drawable.ramen));
        platos.add(new Plato("Sushi", "Receta oriental", R.drawable.suchi));
        platos.add(new Plato("Risotto parmigiano", "Receta italiana", R.drawable.risotto));

        // 2. La lista: cómo se colocan las filas y quién las pinta
        RecyclerView rvLista = findViewById(R.id.rvLista);
        rvLista.setLayoutManager(new LinearLayoutManager(this));
        rvLista.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvLista.setAdapter(new AdaptadorPlatos(platos));

    }
}