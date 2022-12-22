package psp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);
        System.out.println("Bienvenido al juego");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        int puntos = 0;
        int resultado = 0;

        while (true) {
            String mensaje = bufferedReader.readLine();
            System.out.println("Te toca");

            if (mensaje.equals("Has ganado")) {
                System.out.println("¡¡Enhorabuena!! ¡¡Has ganado!!");
                socket.close();
                break;
            }

            System.out.printf("Actualmente tienes %d puntos, deseas sumar o restar?\n", puntos);
            String opcion = new Scanner(System.in).next();
            if (opcion.equals("+")) {
                resultado = puntos + Integer.parseInt(mensaje);
            } else if (opcion.equals("-")) {
                resultado = puntos - Integer.parseInt(mensaje);
            }

            if (resultado < 1 || resultado > 10) {
                System.out.printf("Has perdido, tienes %d puntos\n", resultado);
                printWriter.println("Nonono");
                printWriter.flush();
                socket.close();
                break;
            } else {
                puntos = resultado;
                System.out.printf("Continuas en el juego, ahora tienes %d puntos\n", puntos);
                printWriter.println("OK");
                printWriter.flush();
            }
        }
    }
}
