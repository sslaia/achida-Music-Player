package com.blogspot.sslaia.achidamusicplayer;

public class Song  {
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
}
