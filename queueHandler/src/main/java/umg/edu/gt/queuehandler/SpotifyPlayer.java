
//ProgramacionIII20260228
//Carlos Ramos
//0905 23 14141

package umg.edu.gt.queuehandler;

import umg.edu.gt.data_structure.queue.manual.QueueLinked;

//Simulador del Reproductor de SPoytl

public class SpotifyPlayer {

    // Priod¡ridad de dos colas
	
    private QueueLinked<Song> highPriorityQueue;   // prioridad 1 = alta
    private QueueLinked<Song> normalPriorityQueue; // prioridad 2 = normal

    // Historial de cuando reproducen las canciones
    private QueueLinked<String> history;

    private int totalPlayed;
    private int totalSeconds;

    private boolean skipRequested;

    public SpotifyPlayer() {
        highPriorityQueue   = new QueueLinked<>();
        normalPriorityQueue = new QueueLinked<>();
        history             = new QueueLinked<>();
        totalPlayed         = 0;
        totalSeconds        = 0;
        skipRequested       = false;
    }

    
    public void addSong(Song song) {
        if (isDuplicate(song)) {
            System.out.println("[LOG] Cancion duplicada ignorada: " + song.getTitle()
                    + " - " + song.getArtist());
            return;
        }
        if (song.getPriority() == 1) {
            highPriorityQueue.enqueue(song);
            System.out.println("[LOG] Agregada (ALTA prioridad): " + song);
        } else {
            normalPriorityQueue.enqueue(song);
            System.out.println("[LOG] Agregada (prioridad NORMAL): " + song);
        }
    }

   
     // se agrego addNext()

    public void addNext(Song song) {
       
        QueueLinked<Song> temp = new QueueLinked<>();
        
        temp.enqueue(song);
        
        while (!highPriorityQueue.isEmpty()) {
            temp.enqueue(highPriorityQueue.dequeue());
        }
        
        while (!temp.isEmpty()) {
            highPriorityQueue.enqueue(temp.dequeue());
        }
        System.out.println("[LOG] Cancion insertada al FRENTE: " + song.getTitle()
                + " - " + song.getArtist());
    }

  
     // parte del skip()

    public void skip() {
        skipRequested = true;
        System.out.println("[LOG] >> Skip solicitado!");
    }

    // Cuando Inicia la Reproduccion simulacion
    
    public void play() {
        System.out.println("\n[LOG] Starting playlist...");
        System.out.println("=================================================");

        while (hasNext()) {
            Song current = getNextSong();
            playSong(current);
        }

        System.out.println("=================================================");
        System.out.println("[LOG] Playlist finished.");
        System.out.println("[LOG] Total canciones reproducidas : " + totalPlayed);
        System.out.println("[LOG] Tiempo total reproducido     : " + totalSeconds + " segundos");
        printHistory();
    }

    //Simulacion segundo a segundo
    
    private void playSong(Song song) {
        skipRequested = false;
        System.out.println("\n[LOG] Now playing: " + song.getTitle()
                + " - " + song.getArtist()
                + " (" + song.getDuration() + "s)");

        int secondsPlayed = 0;

        for (int i = 1; i <= song.getDuration(); i++) {

            if (skipRequested) {
                System.out.println("[LOG] Cancion saltada en " + secondsPlayed + "s");
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            secondsPlayed = i;

            String bar = buildProgressBar(i, song.getDuration());
            System.out.println("[LOG] Playing: " + song.getTitle()
                    + " | " + bar + " " + i + "s / " + song.getDuration() + "s");
        }

        if (!skipRequested) {
            System.out.println("[LOG] Finished: " + song.getTitle());
            totalSeconds += song.getDuration();
        } else {
            totalSeconds += secondsPlayed;
        }

        totalPlayed++;
        history.enqueue(song.getTitle() + " - " + song.getArtist());
    }

    

    private boolean hasNext() {
        return !highPriorityQueue.isEmpty() || !normalPriorityQueue.isEmpty();
    }

    private Song getNextSong() {
        if (!highPriorityQueue.isEmpty()) {
            return highPriorityQueue.dequeue();
        }
        return normalPriorityQueue.dequeue();
    }

   //validacion de duplicados.
     
    private boolean isDuplicate(Song song) {
        return checkInQueue(highPriorityQueue, song)
            || checkInQueue(normalPriorityQueue, song);
    }

    private boolean checkInQueue(QueueLinked<Song> queue, Song song) {
        QueueLinked<Song> temp = new QueueLinked<>();
        boolean found = false;

        while (!queue.isEmpty()) {
            Song s = queue.dequeue();
            if (s.getTitle().equalsIgnoreCase(song.getTitle())
                    && s.getArtist().equalsIgnoreCase(song.getArtist())) {
                found = true;
            }
            temp.enqueue(s);
        }
     
        while (!temp.isEmpty()) {
            queue.enqueue(temp.dequeue());
        }
        return found;
    }


     // diseño de [####------]
    
    private String buildProgressBar(int current, int total) {
        int barLength = 10;
        int filled    = (int) Math.round((double) current / total * barLength);
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < barLength; i++) {
            bar.append(i < filled ? "#" : "-");
        }
        bar.append("]");
        return bar.toString();
    }

   //historial.
 
    private void printHistory() {
        System.out.println("\n[LOG] === Historial de reproduccion ===");
        QueueLinked<String> temp = new QueueLinked<>();
        int num = 1;
        while (!history.isEmpty()) {
            String entry = history.dequeue();
            System.out.println("[LOG]  " + num++ + ". " + entry);
            temp.enqueue(entry);
        }
        while (!temp.isEmpty()) {
            history.enqueue(temp.dequeue());
        }
    }
}