package psp;

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
    public static void main(String[] args) {




    }
}