package com.blogspot.sslaia.achidamusicplayer;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {

    public SongAdapter (Activity context, ArrayList<Song> songs) {
        super(context, 0, songs);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.song_list,parent,false);

        Song currentSong = getItem(position);

        ImageView albumCover = listItem.findViewById(R.id.album_cover);
        albumCover.setImageResource(currentSong.getAlbumCoverImage());

        TextView songTitle = listItem.findViewById(R.id.song_title);
        songTitle.setText(currentSong.getSongTitle());

        TextView artistName = listItem.findViewById(R.id.artist_name);
        artistName.setText(currentSong.getArtistName());

        TextView albumName = listItem.findViewById(R.id.album_name);
        albumName.setText(currentSong.getAlbumName());

        return listItem;
    }
}
