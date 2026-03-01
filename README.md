Universidad: Mariano Galvez de Guatemala
Campus JUTIAPA
Fecha: 28 Febrero 2026
Estudiante: Carlos Daniel Ramos Moran
Carne: 0905 23 14141
Curso: Programacion 3
Tarea: Queue de canciones

Descripcion del proyecto:

Este proyecto simula una lista de reproduccion musical similar a Spotify.
Se implemento una estructura de datos tipo Cola (FIFO)

El proyecto esta dividido en dos partes:
- queue: es la libreria de la cola que se construyo manualmente
- queueHandler: es la simulacion del reproductor que usa esa librería


Como compilar la libreria queue:

Antes de compilar el handler, hay que instalar la libreria en Maven local.
Abrir una terminal en la carpeta queue y ejecutar:

    cd queue
    mvn clean install

Si todo sale bien se debe ver: BUILD SUCCESS


Como compilar el queueHandler:

Una vez instalada la libreria, se compila el handler:

    cd queueHandler
    mvn clean package

Si todo sale bien se debe ver: BUILD SUCCESS
El archivo JAR queda guardado en: queueHandler/target/queueHandler.jar


Como ejecutar el programa desde consola:

    cd queueHandler/target
    java -jar queueHandler.jar

Estructura del repositorio:

    /
    queue/
        src/main/java/umg/edu/gt/data_structure/queue/manual/
            Node.java
            QueueLinked.java
            QueueEmptyException.java
        pom.xml

    queueHandler/
        src/main/java/umg/edu/gt/queuehandler/
            Song.java
            SpotifyPlayer.java
            Main.java
        pom.xml

    evidencias/

    README.md


Explicacion del diseño:

Parte A - Libreria de Cola Propia:

Se creo una cola generica llamada QueueLinked que funciona con cualquier
tipo de dato. Esta basada en nodos enlazados, lo que significa que cada
elemento apunta al siguiente.

Las clases que se crearon son:

Node: es el nodo que guarda el valor y una referencia al siguiente nodo.
Se dejo con acceso de paquete para que nadie pueda acceder a los nodos
desde afuera de la libreria.

QueueLinked: es la cola principal. Tiene dos referencias privadas:
head que apunta al primer elemento (el que sale primero) y tail que
apunta al ultimo elemento (el que entro ultimo). Tambien tiene una
variable size que guarda cuantos elementos hay.

QueueEmptyException: es una excepcion propia que se lanza cuando alguien
intenta hacer dequeue o peek en una cola que esta vacia.

La razon por la que enqueue y dequeue son O(1) es que no recorren
la lista. enqueue siempre inserta apuntando directo a tail, y dequeue
siempre elimina desde head directamente.

Simulacion de Reproduccion:

La clase SpotifyPlayer reproduce cada cancion una por una.
Usa Thread.sleep(1000) para simular que pasa un segundo real entre
cada mensaje que aparece en consola. Los mensajes siguen exactamente
el formato que pide la tarea.

No se uso ninguna libreria externa para los logs. Todo se imprime
con System.out.println usando el prefijo [LOG].

Ejemplo de lo que aparece en consola:

    [LOG] Starting playlist...
    [LOG] Now playing: Blinding Lights - The Weeknd (8s)
    [LOG] Playing: Blinding Lights | [#---------] 1s / 8s
    [LOG] Playing: Blinding Lights | [##--------] 2s / 8s
    [LOG] Finished: Blinding Lights
    [LOG] Playlist finished.


Sistema de Prioridad:

Se usaron dos colas internas separadas para manejar la prioridad
sin usar PriorityQueue del JDK.

    highPriorityQueue   - guarda canciones con prioridad 1 (alta)
    normalPriorityQueue - guarda canciones con prioridad 2 (normal)

Cuando se agrega una cancion, el metodo addSong decide en cual
de las dos colas entra segun su prioridad.

Cuando se va a reproducir la siguiente cancion, el metodo getNextSong
siempre revisa primero si hay algo en highPriorityQueue. Solo pasa
a normalPriorityQueue cuando la de alta prioridad esta vacia.

Dentro de cada cola se respeta el orden FIFO. Esto significa que
las canciones se reproducen en el mismo orden en que fueron agregadas.

Ejemplo:

    Canciones de alta prioridad agregadas:   A1, A2
    Canciones de prioridad normal agregadas: N1, N2, N3

    Orden real de reproduccion:
        1. A1
        2. A2
        3. N1
        4. N2
        5. N3



Extensiones implementadas

Se implementaron las siguientes mejoras:

1. skip()
Interrumpe la cancion que se esta reproduciendo en ese momento.
Se usa una variable booleana que el loop de reproduccion revisa
en cada segundo.

2. addNext()
Inserta una cancion para que sea la siguiente en reproducirse.
Reconstruye la cola de alta prioridad poniendo la nueva cancion
al frente y luego restaura las demas en su orden original.

3. Historial de canciones
Se creo una segunda estructura QueueLinked de tipo String que
guarda el nombre de cada cancion despues de que termina de reproducirse.

4. Contador de canciones reproducidas
Una variable llamada totalPlayed que aumenta en 1 cada vez que
termina una cancion.

5. Tiempo total acumulado
Una variable llamada totalSeconds que suma la duracion de cada
cancion reproducida y muestra el total al final.

6. Validacion de duplicados
Antes de agregar una cancion, el metodo isDuplicate revisa si ya
existe una cancion con el mismo titulo y artista en alguna de las
dos colas. Si ya existe, la ignora y muestra un mensaje.

7. Barra de progreso visual
El metodo buildProgressBar genera una barra como esta:
[####------] que muestra visualmente que porcentaje de la cancion
ya se reprodujo.


Decisiones tecnicas importantes:

Se usaron genericos en QueueLinked para que la misma cola pueda
usarse con canciones, con strings para el historial, o con cualquier
otro tipo de dato en el futuro.

Node se dejo sin modificador public para que los nodos internos
no sean visibles desde fuera del paquete. Esto protege la estructura
interna de la cola.

Los metodos que necesitan recorrer la cola, como el de duplicados
y el de addNext, usan una cola temporal para no perder ni cambiar
el orden de los datos originales.

Evidencias

En este apartado comparto las evidencias de mi tarea

Universidad: Mariano Galvez de Guatemala
Campus JUTIAPA
Fecha: 28 Febrero 2026
Estudiante: Carlos Daniel Ramos Moran
Carne: 0905 23 14141
Curso: Programacion 3
Tarea: Queue de canciones

Descripcion del proyecto:

Este proyecto simula una lista de reproduccion musical similar a Spotify.
Se implemento una estructura de datos tipo Cola (FIFO)

El proyecto esta dividido en dos partes:
- queue: es la libreria de la cola que se construyo manualmente
- queueHandler: es la simulacion del reproductor que usa esa librería


-*- Como compilar la libreria queue:

Antes de compilar el handler, hay que instalar la libreria en Maven local.
Abrir una terminal en la carpeta queue y ejecutar:

    cd queue
    mvn clean install

Si todo sale bien se debe ver: BUILD SUCCESS

 
-*- Como compilar el queueHandler:

Una vez instalada la libreria, se compila el handler:

    cd queueHandler
    mvn clean package

Si todo sale bien se debe ver: BUILD SUCCESS
El archivo JAR queda guardado en: queueHandler/target/queueHandler.jar


-*- Como ejecutar el programa desde consola:

    cd queueHandler/target
    java -jar queueHandler.jar

Estructura del repositorio:

    /
    queue/
        src/main/java/umg/edu/gt/data_structure/queue/manual/
            Node.java
            QueueLinked.java
            QueueEmptyException.java
        pom.xml

    queueHandler/
        src/main/java/umg/edu/gt/queuehandler/
            Song.java
            SpotifyPlayer.java
            Main.java
        pom.xml

    evidencias/

    README.md


-*- Explicacion del diseño:

-Libreria de Cola Propia:

Se creo una cola generica llamada QueueLinked que funciona con cualquier
tipo de dato. Esta basada en nodos enlazados, lo que significa que cada
elemento apunta al siguiente.

Las clases que se crearon son:

Node: es el nodo que guarda el valor y una referencia al siguiente nodo.
Se dejo con acceso de paquete para que nadie pueda acceder a los nodos
desde afuera de la libreria.

QueueLinked: es la cola principal. Tiene dos referencias privadas:
head que apunta al primer elemento (el que sale primero) y tail que
apunta al ultimo elemento (el que entro ultimo). Tambien tiene una
variable size que guarda cuantos elementos hay.

QueueEmptyException: es una excepcion propia que se lanza cuando alguien
intenta hacer dequeue o peek en una cola que esta vacia.

La razon por la que enqueue y dequeue son O(1) es que no recorren
la lista. enqueue siempre inserta apuntando directo a tail, y dequeue
siempre elimina desde head directamente.

-Simulacion de Reproduccion y duracion:

La clase SpotifyPlayer reproduce cada cancion una por una.
Usa Thread.sleep(1000) para simular que pasa un segundo real entre
cada mensaje que aparece en consola. Los mensajes siguen exactamente
el formato que pide la tarea.

No se uso ninguna libreria externa para los logs. Todo se imprime
con System.out.println usando el prefijo [LOG].

Ejemplo de lo que aparece en consola:

    [LOG] Starting playlist...
    [LOG] Now playing: Blinding Lights - The Weeknd (8s)
    [LOG] Playing: Blinding Lights | [#---------] 1s / 8s
    [LOG] Playing: Blinding Lights | [##--------] 2s / 8s
    [LOG] Finished: Blinding Lights
    [LOG] Playlist finished.


-*- Sistema de Prioridad:

Lo que realice fue que se usaron dos colas internas separadas para manejar la prioridad
sin usar PriorityQueue del JDK.

    highPriorityQueue   - guarda canciones con prioridad 1 (alta)
    normalPriorityQueue - guarda canciones con prioridad 2 (normal)

Cuando se agrega una cancion, el metodo addSong decide en cual
de las dos colas entra segun su prioridad.

Cuando se va a reproducir la siguiente cancion, el metodo getNextSong
siempre revisa primero si hay algo en highPriorityQueue. Solo pasa
a normalPriorityQueue cuando la de alta prioridad esta vacia.

Dentro de cada cola se respeta el orden FIFO. Esto significa que
las canciones se reproducen en el mismo orden en que fueron agregadas.

Ejemplo:

    Canciones de alta prioridad agregadas:   A1, A2
    Canciones de prioridad normal agregadas: N1, N2, N3

    Orden real de reproduccion:
        1. A1
        2. A2
        3. N1
        4. N2
        5. N3



-Extensiones implementadas

Se implementaron las siguientes mejoras:

1. skip()
Interrumpe la cancion que se esta reproduciendo en ese momento.
Se usa una variable booleana que el loop de reproduccion revisa
en cada segundo.

2. addNext()
Inserta una cancion para que sea la siguiente en reproducirse.
Reconstruye la cola de alta prioridad poniendo la nueva cancion
al frente y luego restaura las demas en su orden original.

3. Historial de canciones
Se creo una segunda estructura QueueLinked de tipo String que
guarda el nombre de cada cancion despues de que termina de reproducirse.

4. Contador de canciones reproducidas
Una variable llamada totalPlayed que aumenta en 1 cada vez que
termina una cancion.

5. Tiempo total acumulado
Una variable llamada totalSeconds que suma la duracion de cada
cancion reproducida y muestra el total al final.

6. Validacion de duplicados
Antes de agregar una cancion, el metodo isDuplicate revisa si ya
existe una cancion con el mismo titulo y artista en alguna de las
dos colas. Si ya existe, la ignora y muestra un mensaje.

7. Barra de progreso visual
El metodo buildProgressBar genera una barra como esta:
[####------] que muestra visualmente que porcentaje de la cancion
ya se reprodujo.


-Decisiones tecnicas importantes:

Se usaron genericos en QueueLinked para que la misma cola pueda
usarse con canciones, con strings para el historial, o con cualquier
otro tipo de dato en el futuro.

Node se dejo sin modificador public para que los nodos internos
no sean visibles desde fuera del paquete. Esto protege la estructura
interna de la cola.

Los metodos que necesitan recorrer la cola, como el de duplicados
y el de addNext, usan una cola temporal para no perder ni cambiar
el orden de los datos originales.

Evidencias

En este apartado comparto las evidencias de mi tarea
