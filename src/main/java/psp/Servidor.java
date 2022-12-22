package psp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Servidor {
    /*
    Crea un programa Java que implemente un juego en red para dos o más jugadores.
    Cada jugador se conectará al servidor y entonces empezará el juego.
     El servidor pedirá al inicio por teclado cuántos jugadores va a haber, y
     entonces deberá esperar a que estén todos conectados para empezar la partida.

Los jugadores tienen inicialmente cero puntos, y juegan por turno.
Cada jugador esperará a que el servidor le indique que le toca jugar,
 y entonces el jugador (el usuario) elegirá si quiere sumar o restar.
 El servidor generará un número aleatorio entre 1 y 10, y lo enviará al jugador,
  quien lo sumará o restará a sus puntos según hubiese elegido antes (la decisión de sumar o restar no se envía por la red).

Si después de hacer la suma o resta los puntos se salen del rango 1..10, el jugador pierde.
 El jugador deberá comunicar al servidor si ha perdido o no, y si ha perdido, se desconectará y terminará su ejecución.

El servidor continuará dando turno a los jugadores que queden, hasta que solamente quede uno.
Cuando sólo quede uno, le comunicará que ha ganado. Entonces el programa terminará.
     */
    /*
    BORRADOR:
    El protocolo entre el servidor y los jugadores debe ser el siguiente:
    El servidor enviará un mensaje al jugador que le toque. Si no quedan más jugadores el mensaje
    será una cadena "Has ganado" y tras esto ambos cerrarán la conexión.
    Si aún quedan jugadores en la partida, lo que el servidor enviará será un número. Con este número el
    jugador hará su jugada y responderá al servidor indicando si ha perdido o si aún está participando. En caso
    de haber perdido el servidor lo sacará de la lista de jugadores y el jugador se desconectará.
     */
    public static void main(String[] args) throws IOException {
        Random random = new Random();
        List<Socket> socketsJugadores = new ArrayList<>();
        int contadorNumeroJugadores = 0;
        BufferedReader bufferedReader;
        PrintWriter printWriter;

        ServerSocket serverSocket = new ServerSocket(5000);

        System.out.println("¿Cuántos jugadores quieres que haya?");
        int numeroJugadoresDeseado = new Scanner(System.in).nextInt();

        while (contadorNumeroJugadores < numeroJugadoresDeseado) {
            Socket socketConectado = serverSocket.accept();
            System.out.println("Se ha conectado un nuevo jugador");
            socketsJugadores.add(socketConectado);
            contadorNumeroJugadores++;
        }
        System.out.println("Ya se han conectado todos los jugadores, empieza el juego");

        while (socketsJugadores.size() > 1) {
            for (int i = 0; i < socketsJugadores.size(); i++) {

                bufferedReader = new BufferedReader(new InputStreamReader(socketsJugadores.get(i).getInputStream()));
                printWriter = new PrintWriter(socketsJugadores.get(i).getOutputStream());
                printWriter.println(random.nextInt(1, 11));
                printWriter.flush();

                String respuestaDelCliente = bufferedReader.readLine();
                if (!respuestaDelCliente.equals("OK")) {
                    socketsJugadores.get(i).close();
                    socketsJugadores.remove(i);
                }
            }
        }
        printWriter = new PrintWriter(socketsJugadores.get(0).getOutputStream());
        printWriter.println("Has ganado");
        printWriter.flush();
        System.out.println("Se ha terminado el juego");


    }
}