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

import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //Declaro el atributo player
    private ExoPlayer player;

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

    }
}