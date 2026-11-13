package com.example.reproductor_javiergallegos;

import android.media.browse.MediaBrowser;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;

import android.os.Handler;
import android.os.Looper;

import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //Declaro el atributo player
    private ExoPlayer player;
    private SeekBar seekBar;
    private TextView tvTiempoActual, tvDuracion;
    private final Handler handler = new Handler(Looper.getMainLooper());

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

        //Creo el objeto player(el reproductor en sí) usando ExoPlayer.Builder para construir el reproductor
        player = new ExoPlayer.Builder(this).build();

        //Aquí obtengo la dirección de mi canción
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cancion_motivacional);

        //Creo el elemento multimedia usando la dirección de la canción
        MediaItem mediaItem = MediaItem.fromUri(uri);
        //Aquí asigno la canción a nuestro reproductor player
        player.setMediaItem(mediaItem);
        //Por último preparo el reproductor para reproducir el contenido
        player.prepare();

        seekBar = findViewById(R.id.seekBar);
        tvTiempoActual = findViewById(R.id.tvInicio_cancion);
        tvDuracion = findViewById(R.id.tvFinal_cancion);

        player.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int estado) {
                //Cuando el reproductor está listo ya se conoce la duración de la canción
                if (estado == Player.STATE_READY) {
                    //Pongo como máximo de la barra la duración de la canción (en milisegundos)
                    seekBar.setMax((int) player.getDuration());
                    //Muestro la duración total formateada en mm:ss en el TextView de la derecha
                    tvDuracion.setText(formatear(player.getDuration()));
                }
            }
        });

        //Listener para detectar cuándo el usuario mueve la barra de progreso
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar sb, int progreso, boolean delUsuario) { }

            @Override
            public void onStartTrackingTouch(SeekBar sb) { }

            @Override
            public void onStopTrackingTouch(SeekBar sb) {
                //Al soltar la barra, salto la canción al punto donde la ha dejado el usuario
                player.seekTo(sb.getProgress());
            }
        });

        //Recupero la referencia al botón PLAY y PAUSE
        Button btnPlay = findViewById(R.id.button);
        Button btnPause = findViewById(R.id.button2);

        //Creo el listener para PLAY añadiendo la llamada player.play()
        btnPlay.setOnClickListener(v -> {
            //Compruebo si la canción ha terminado y vuelve a empezar
            if (player.getPlaybackState() == Player.STATE_ENDED) {
                player.seekTo(0);
            }
            player.play();
        });

        //Creo el listener para PAUSE añadiendo la llamada a player.pause()
        btnPause.setOnClickListener(v -> {
            player.pause();
        });
        handler.post(actualizarBarra);
    }

    // Metodo para formatear y pasar de milisegundos a mm:ss
    private String formatear(long ms) {
        //Paso los milisegundos a segundos totales
        long segundos = ms / 1000;
        //Formateo a minutos:segundos, con dos dígitos cada uno (ej. 01:15)
        return String.format(Locale.getDefault(), "%02d:%02d", segundos / 60, segundos % 60);
    }

    //El Runnable para actualizar la barra de reproducción cada medio segundo
    private final Runnable actualizarBarra = new Runnable() {
        @Override
        public void run() {
            //Actualizo la posición de la barra con el punto actual de la canción
            seekBar.setProgress((int) player.getCurrentPosition());
            //Actualizo el tiempo actual formateado en el TextView de la izquierda
            tvTiempoActual.setText(formatear(player.getCurrentPosition()));
            //Vuelvo a programar este mismo Runnable dentro de 500 ms, creando el bucle
            handler.postDelayed(this, 500);
        }
    };

}