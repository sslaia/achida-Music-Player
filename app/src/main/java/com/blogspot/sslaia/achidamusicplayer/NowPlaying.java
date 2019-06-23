package com.blogspot.sslaia.achidamusicplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class NowPlaying extends AppCompatActivity {
    // This method is necessary to pass the score to other activity
    public static final String SONG_DATA = "com.blogspot.sardinias.achidamusicplayer.SONG_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyric);

        // Get the data from the previous screen (title, artist, lyric)
        // the lyric part is not fully implemented yet
        Intent i = getIntent();
        Bundle b = i.getBundleExtra(SONG_DATA);
        final String songTitle = b.getString("title");
        final String artistName = b.getString("artist");
        int songLyric = b.getInt("lyric");

        TextView songTitleNow = findViewById(R.id.lyric_song_title);
        songTitleNow.setText(songTitle);

        TextView artistNameNow = findViewById(R.id.lyric_artist_name);
        artistNameNow.setText(artistName);

        TextView lyricTextNow = findViewById(R.id.lyric_text);
        lyricTextNow.setText(getString(R.string.song_lyric));

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.abba_chiquitita);
        mediaPlayer.start();

        // To be executed when the write review area is clicked
        TextView writeReview = findViewById(R.id.write_review_view);

        writeReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Send intent to write interview on the web.
                // Bundle the song, artist name to pass to the buy page
                Intent writeReview = new Intent(NowPlaying.this, WriteReview.class);
                Bundle b = new Bundle();
                b.putString("title", songTitle);
                b.putString("artist", artistName);
                writeReview.putExtra(SONG_DATA, b);
                startActivity(writeReview);
            }
        });

        // To be executed when the search area is clicked
        TextView searchOnline = findViewById(R.id.search_online_view);

        searchOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            // Send intent to open Google search website
            Intent searchIntent = new Intent(NowPlaying.this, SearchOnline.class);
                Bundle b = new Bundle();
                b.putString("title", songTitle);
                b.putString("artist", artistName);
                searchIntent.putExtra(SONG_DATA, b);
            startActivity(searchIntent);
        }});

        // To be executed when the buy online area is clicked
        TextView buyOnline = findViewById(R.id.buy_online_view);

        buyOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Bundle the song, artist name to pass to the buy page
                Intent songToBuy = new Intent(NowPlaying.this, BuyOnline.class);
                Bundle b = new Bundle();
                b.putString("title", songTitle);
                b.putString("artist", artistName);
                songToBuy.putExtra(SONG_DATA, b);
                startActivity(songToBuy);
            }
        });
    }
}
