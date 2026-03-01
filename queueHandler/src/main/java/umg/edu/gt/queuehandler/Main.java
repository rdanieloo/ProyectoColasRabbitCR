
//ProgramacionIII20260228
//CarlosRamos
//0905 23 14141

package umg.edu.gt.queuehandler;

public class Main {

    public static void main(String[] args) {

        SpotifyPlayer player = new SpotifyPlayer();

        System.out.println("=================================================");
        System.out.println("         SIMULACION SPOTIFY - PRIORIDAD");
        System.out.println("=================================================");

        //Canciones ALTA prioridad (1) 
        player.addSong(new Song("Lo Mio No Pasa",   "Alex Zurdo",    14,  1));
        player.addSong(new Song("Brillas",      "Leon Larregui",    18, 1));
        player.addSong(new Song("Te Busco",      "Alex Zurdo",    12, 1));
        player.addSong(new Song("Rara Vez",   "Milo J",    9,  1));
        

        //Canciones NORMALES (2) 
        player.addSong(new Song("Ahora Te Puedes Marchar",  "Luis Miguel",    15, 2));
        player.addSong(new Song("La Noche Mas Linda", "Adalberto Santiago",  20, 2));
        player.addSong(new Song("La Chica De Humo","Emmanuel",  10, 2));
        player.addSong(new Song("Sin Ti",      "Alex Zurdo",    25, 2));

        //Prueba: cancion DUPLICADA 
        player.addSong(new Song("Te Busco", "Alex Zurdo", 12, 1));

        //Prueba: addNext 
        player.addNext(new Song("Te He Prometido", "Leo Dan", 6, 1));

        // Inicia la reproduccion 
        player.play();
    }
}