package com.blogspot.sslaia.achidamusicplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    // This method is necessary to pass the score to other activity
    public static final String SONG_DATA = "com.blogspot.sardinias.achidamusicplayer.SONG_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create an ArrayList of songs
        final ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song("Andante, andante", "ABBA", "Giant Collection", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("Angel eyes", "ABBA", "Giant Collection", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("Move on", "ABBA", "Giant Collection", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("Dancing queen", "ABBA", "Giant Collection", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("Chiquitita", "ABBA", "Gold", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("Does your mother know", "ABBA", "Gold", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("One man one woman", "ABBA", "Gold", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("People need love", "ABBA", "Gold", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("Our last summer", "ABBA", "For Ever Young", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("Gimme gimme gimme", "ABBA", "For Ever Young", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("Happy new year", "ABBA", "For Ever Young", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("Hasta manana", "ABBA", "For Ever Young", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("SOS", "ABBA", "Super Truper", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("Super trouper", "ABBA", "Super Truper", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("Hole in your soul", "ABBA", "Super Truper", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("Take a chance", "ABBA", "Super Truper", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("Honey, honey", "ABBA", "Thank You For the Music", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("Thank you for the music", "ABBA", "Thank You For the Music", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("I do, I do, I do, I do", "ABBA", "Thank You For the Music", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("I have a dream", "ABBA", "Thank You For the Music", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));

        final SongAdapter adapter = new SongAdapter(this, songs);

        final ListView listView = findViewById(R.id.song_list_view);
        listView.setAdapter(adapter);

        // The method will be executed when one of the songs is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String songTitle = songs.get(position).getSongTitle();
                String artistName = songs.get(position).getArtistName();
                int songLyric = songs.get(position).getLyricFileName();

                // Bundle the song, artist name and lyric file to pass to the lyric page
                Intent songToPlay = new Intent(MainActivity.this, NowPlaying.class);
                Bundle b = new Bundle();
                b.putString("title", songTitle);

                b.putString("artist", artistName);
                b.putInt("lyric", songLyric);
                songToPlay.putExtra(SONG_DATA, b);
                startActivity(songToPlay);
            }
        });

        // For when the Now playing area is clicked on
        LinearLayout nowPlaying = findViewById(R.id.nav_bottom_view);

        // First set the now playing song tile
        TextView songTitleNow = findViewById(R.id.song_title_main);
        songTitleNow.setText(songs.get(0).getSongTitle());

        // Play all songs one by one

        nowPlaying.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                int size = songs.size();

                for (int i=0; i < size; i++) {

                    int songToPlay = songs.get(i).getSongFileName();

                    // Play the song selected
                    MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, songToPlay);
                    mediaPlayer.start();
                }
            }
        });

        // The method will be executed when ARTISTS area is clicked
        TextView artists = findViewById(R.id.sort_artists);
        artists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Sort the songs based on artist name
                Collections.sort(songs, Song.artistNameComparator);

                final SongAdapter adapter = new SongAdapter(MainActivity.this, songs);
                final ListView listView = findViewById(R.id.song_list_view);
                listView.setAdapter(adapter);
            }
        });

        // The method will be executed when ALBUMS area is clicked
        TextView albums = findViewById(R.id.sort_albums);
        albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Sort the songs based on album name
                Collections.sort(songs, Song.albumNameComparator);

                final SongAdapter adapter = new SongAdapter(MainActivity.this, songs);
                final ListView listView = findViewById(R.id.song_list_view);
                listView.setAdapter(adapter);
            }
        });

        // The method will be executed when SONG area is clicked
        TextView songTitle = findViewById(R.id.sort_songs);
        songTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Sort the songs based on song title
                Collections.sort(songs, Song.songTitleComparator);

                final SongAdapter adapter = new SongAdapter(MainActivity.this, songs);
                final ListView listView = findViewById(R.id.song_list_view);
                listView.setAdapter(adapter);
            }
        });
    }
}
