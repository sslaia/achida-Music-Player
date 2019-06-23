package com.blogspot.sslaia.achidamusicplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import org.w3c.dom.Text;

public class WriteReview extends AppCompatActivity {
    // This method is necessary to pass the score to other activity
    public static final String SONG_DATA = "com.blogspot.sardinias.achidamusicplayer.SONG_DATA";

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        // Get the data from the previous screen (title, artist, lyric)
        Intent i = getIntent();
        Bundle b = i.getBundleExtra(SONG_DATA);
        String songTitle = b.getString("title");
        String artistName = b.getString("artist");

        // Fill in the title and artist name for writing review
        TextView songTV = findViewById(R.id.review_song_title);
        songTV.setText(songTitle);

        TextView artistTV = findViewById(R.id.review_artist_name);
        artistTV.setText(artistName);
        }

    // This method is called when submitButton is clicked
    public void submitButton(View view) {
        // Just to open next screen to display thank you message
        // Create an Intent for next activity
        Intent i = new Intent(WriteReview.this, ThankYou.class);
        startActivity(i);
    }

}
