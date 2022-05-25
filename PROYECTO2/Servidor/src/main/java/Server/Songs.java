package Server;

public class Songs {

    private String song;
    private String artist;
    private String album;
    private String lyrics;

    // Es necesario para que no genere errores
    public Songs(){

    }

    public Songs(String song, String artist, String album, String lyrics){

        super();
        this.song = song;
        this.artist = artist;
        this.album = album;
        this.lyrics = lyrics;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
}
