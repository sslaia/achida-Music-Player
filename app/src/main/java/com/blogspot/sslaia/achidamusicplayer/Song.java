package com.blogspot.sslaia.achidamusicplayer;

import java.util.Comparator;

public class Song {
    // Song title
    private String mSongTitle;

    // Artist name
    private String mArtistName;

    // Album name
    private String mAlbumName;

    // Album cover
    private int mAlbumCoverImage;

    // Song file name
    private int mSongFileName;

    // Lyric file name
    private int mLyricFileName;

    // Create a new Song object
    // @param songTitle is the title
    // @param artistName is the artist name
    // @param albumName is the name of the album
    public Song(String songTitle, String artistName, String albumName,
                int albumCoverImage, int songFileName, int lyricFileName) {
        mSongTitle = songTitle;
        mArtistName = artistName;
        mAlbumName = albumName;
        mAlbumCoverImage = albumCoverImage;
        mSongFileName = songFileName;
        mLyricFileName = lyricFileName;
    }

    // Get the title of the song
    public String getSongTitle() {
        return mSongTitle;
    }

    // Get the artist name of the song
    public String getArtistName() {
        return mArtistName;
    }

    // Get the album name
    public String getAlbumName() {
        return mAlbumName;
    }

    // Get the album cover image
    public int getAlbumCoverImage() {
        return mAlbumCoverImage;
    }

    // Get the song file name
    public int getSongFileName() {
        return mSongFileName;
    }

    // Get the lyric file name
    public int getLyricFileName() {
        return mLyricFileName;
    }

    // Comparator for sorting the song list by artist name
    public static Comparator<Song> artistNameComparator = new Comparator<Song>() {

        public int compare(Song s1, Song s2) {
            String artistName1 = s1.getArtistName().toUpperCase();
            String artistName2 = s2.getArtistName().toUpperCase();

            //ascending order
            return artistName1.compareTo(artistName2);

            //descending order
            //return artistName2.compareTo(artistName1);
        }};

    // Comparator for sorting the song list by album name
    public static Comparator<Song> albumNameComparator = new Comparator<Song>() {

        public int compare(Song s1, Song s2) {
            String albumName1 = s1.getAlbumName().toUpperCase();
            String albumName2 = s2.getAlbumName().toUpperCase();

            //ascending order
            return albumName1.compareTo(albumName2);

            //descending order
            //return albumName2.compareTo(albumName1);
        }};

    // Comparator for sorting the song list by song name
    public static Comparator<Song> songTitleComparator = new Comparator<Song>() {

        public int compare(Song s1, Song s2) {
            String songTitle1 = s1.getSongTitle().toUpperCase();
            String songTitle2 = s2.getSongTitle().toUpperCase();

            //ascending order
            return songTitle1.compareTo(songTitle2);

            //descending order
            //return songTitle2.compareTo(songTitle1);
        }};

}
