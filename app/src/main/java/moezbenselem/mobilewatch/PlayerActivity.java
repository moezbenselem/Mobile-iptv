package moezbenselem.mobilewatch;

import android.media.browse.MediaBrowser;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.exoplayer2.util.MimeTypes;


public class PlayerActivity extends AppCompatActivity {

    PlayerView playerView;
    ExoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player2);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        playerView = (PlayerView) findViewById(R.id.video_player);
        Uri videoUri = Uri.parse(getIntent().getStringExtra("link"));


        // Create a SimpleExoPlayer
        player = new SimpleExoPlayer.Builder(this).build();

        // Set the player to the view
        playerView.setPlayer(player);
        // Create a MediaItem
        MediaItem mediaItem = MediaItem.fromUri(videoUri);

        // Set the MediaItem to the player
        player.setMediaItem(mediaItem);

        // Prepare the player
        player.prepare();

        player.setPlayWhenReady(true);
        player.setRepeatMode(SimpleExoPlayer.REPEAT_MODE_ONE);

    }


    @Override
    public void onBackPressed() {
        if (player != null) {
            player.release();
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        if (player != null) {
            player.release();
        }        super.onDestroy();
    }

}


