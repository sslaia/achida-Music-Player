package com.blogspot.sslaia.achidamusicplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BuyOnline extends AppCompatActivity {
    // This method is necessary to pass the score to other activity
    public static final String SONG_DATA = "com.blogspot.sardinias.achidamusicplayer.SONG_DATA";

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_online);

        // Get the data from the previous screen (title, artist)
        // to pass to the next screen. Not yet fully implemented.
        Intent i = getIntent();
        Bundle b = i.getBundleExtra(SONG_DATA);
        String songTitle = b.getString("title");
        String artistName = b.getString("artist");

        // Displaying the website for purchase
        webview = findViewById(R.id.buy_online_now);

        // In future the above songTitle and artistName parameter should be passed to the URL
        webview.setWebViewClient(new WebViewClient());
        webview.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        webview.loadUrl("https://play.google.com/store/music/album?id=Bthmyvynarj44rxmgarlylfk3e4&tid=song-To63cayyt6woebxgmrwppqwnray");
    }
}

