
//ProgramacionIII20260228
//Carlos Ramos
//0905 23 14141

package umg.edu.gt.queuehandler;

//Simulacion de Spotify con ciertas canciones y su pioridad

public class Song {

    private String title;
    private String artist;
    private int duration;
    private int priority;

    public Song(String title, String artist, int duration, int priority) {
        this.title    = title;
        this.artist   = artist;
        this.duration = duration;
        this.priority = priority;
    }

    public String getTitle()  { return title;    }
    public String getArtist() { return artist;   }
    public int getDuration()  { return duration; }
    public int getPriority()  { return priority; }

    @Override
    public String toString() {
        String prioLabel = (priority == 1) ? "ALTA" : "NORMAL";
        return title + " - " + artist + " (" + duration + "s) [Prioridad " + prioLabel + "]";
    }
}