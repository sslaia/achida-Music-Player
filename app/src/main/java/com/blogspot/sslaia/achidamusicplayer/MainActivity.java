package com.blogspot.sslaia.achidamusicplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // This method is necessary to pass the score to other activity
    public static final String SONG_DATA = "com.blogspot.sardinias.achidamusicplayer.SONG_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create an ArrayList of songs
        final ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song("Andante, andante", "ABBA", "ABBA Giant Collection", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("Angel eyes", "ABBA", "ABBA Giant Collection", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("Move on", "ABBA", "ABBA Giant Collection", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("Dancing queen", "ABBA", "ABBA Giant Collection", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("Chiquitita", "ABBA", "ABBA Gold", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("Does your mother know", "ABBA", "ABBA Gold", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("One man one woman", "ABBA", "ABBA Gold", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("People need love", "ABBA", "ABBA Gold", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("Our last summer", "ABBA", "ABBA For Ever Young", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("Gimme gimme gimme", "ABBA", "ABBA For Ever Young", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("Happy new year", "ABBA", "ABBA For Ever Young", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("Hasta manana", "ABBA", "ABBA For Ever Young", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("SOS", "ABBA", "ABBA Super Truper", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("Super trouper", "ABBA", "ABBA  Super Truper", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("Hole in your soul", "ABBA", "ABBA  Super Truper", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("Take a chance", "ABBA", "ABBA  Super Truper", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("Honey, honey", "ABBA", "ABBA Thank You For the Music", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("Thank you for the music", "ABBA", "ABBA Thank You For the Music", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("I do, I do, I do, I do", "ABBA", "ABBA Thank You For the Music", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));
        songs.add(new Song("I have a dream", "ABBA", "ABBA Thank You For the Music", R.drawable.achida_music, R.raw.abba_chiquitita, R.raw.lyric_text));

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

        // For when search form is used
        EditText songSearch = findViewById(R.id.search_form);
        songSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence searchInput, int arg1, int arg2, int arg3) {
                adapter.getFilter().filter(searchInput);
                listView.setAdapter(adapter);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // Nothing
            }
            @Override
            public void afterTextChanged(Editable arg0) {
                // Nothing
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

                // Notify the mode we are in
                Toast.makeText(MainActivity.this, "Sorted base on Artists", Toast.LENGTH_SHORT).show();

//                // Sort the songs based on artist name
//                sortByArtists();
            }
        });

        // The method will be executed when ALBUMS area is clicked
        TextView albums = findViewById(R.id.sort_albums);
        albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Notify the mode we are in
                Toast.makeText(MainActivity.this, "Sorted base on Albums", Toast.LENGTH_SHORT).show();

//                // Sort the songs based on album name
//                sortByAlbums();
            }
        });

//        // Method to sort songs by artist name
//        private void sortByArtist() {
//            Collections.sort(mSong, new Comparator<Song>() {
//                @Override
//                public int compare(Song o1, Song o2) {
//                    return o1.getArtistName().compareTo(o2.getArtistName());
//                }
//            });
//
//            mListViewAdapter.notifyDataSetChanged();
//        }

//        // Method to sort songs by album name
//        private void sortByAlbum() {
//            Collections.sort(mSong, new Comparator<Song>() {
//                @Override
//                public int compare(Song o1, Song o2) {
//                    return o1.getAlbumName().compareTo(o2.getArtistName());
//                }
//            });
//
//            mListViewAdapter.notifyDataSetChanged();
//        }
    }
}
