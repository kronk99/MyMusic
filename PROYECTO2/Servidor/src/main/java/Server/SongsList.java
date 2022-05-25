package Server;

import java.util.ArrayList;
import java.util.List;

public class SongsList {

    private List<Songs> songsList = new ArrayList<Songs>();

    public SongsList() {

        this.songsList = new ArrayList<Songs>();
    }

    public SongsList(List<Songs> songsList) {

        super();
        this.songsList = songsList;
    }

    public List<Songs> getSongsList() {
        return songsList;
    }

    public void setSongsList(List<Songs> songsList) {
        this.songsList = songsList;
    }
}
