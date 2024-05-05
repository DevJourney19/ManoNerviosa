package carpeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Principal {

    public static AtomicBoolean continuar = new AtomicBoolean(true);
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        //Creo la lista donde se almacenaran las 52 cartas 
        List<Carta> mazo = new ArrayList<>();
        //Asignamos especificamente los valores de los vectores
        String numeros[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};
        String elementos[] = {"Corazones", "Diamantes", "Picas", "Trebol"};

        //Hacemos doble for para poder asignar que cada elemento tengo un valor
        for (String n : numeros) {
            for (String e : elementos) {
                //Creamos una nueva carta y la agregamos al mazo
                mazo.add(new Carta(e, n));
            }
        }

        Random random = new Random();

        //Mezclar el mazo
        Collections.shuffle(mazo, random);

        //Iniciar el juego
        /*"Atómico" significa que las operaciones se realizan de manera 
        indivisible, es decir, no pueden ser interrumpidas por otros hilos*/
        int cont = 0;

        startInputThread();

        //Mostrar cartas del mazo
        System.out.println("Primer conteo\n");
        for (int i = 0; i < mazo.size(); i++) {
            //Reseteamos el contador, si llega a tener un valor de 13
            if (cont == 13) {
                cont = 0;
                cont++;
            } else {
                cont++;
            }
            //Mostramos la carta
            System.out.println(mazo.get(i));
            //Damos 2 segundos para cambiar a otra carta
            Thread.sleep(2000);

            if (i == mazo.size() - 1) {
                System.out.println("\nHas llegado al final del mazo.");
            } else if (cont == 13) {
                System.out.println("\nNuevo conteo\n");
            }


            if (continuar.get() && mazo.get(i).numero.equals(String.valueOf(cont))) {
                System.out.println("No seleccionaste el verdadero - Perdiste");
                System.exit(0);
            }
            //Si se da espacio y luego enter se pasa al ---> else           
            if (!continuar.get()) {//Aqui solo se obtiene el acceso para poder escribir sin interrumpir el bucle del for
                if (!continuar.get() && mazo.get(i).numero.equals(String.valueOf(cont))) { //Practicamente ganó     
                    System.out.println("Ganaste");
                    restartInputThread();

                } else if (!continuar.get() && !mazo.get(i).numero.equals(String.valueOf(cont))) {
                    System.out.println("Perdiste");
                    break;
                }
            }

        }

    }

// Método para la lógica del hilo de entrada
    public static void startInputThread() {
        Thread inputThread = new Thread(() -> {
            while (continuar.get()) {
                String espacio = scan.nextLine();
                if (espacio.equals(" ")) {
                    continuar.set(false);
                } else {
                    System.out.println("Lo siento, seleccionaste una tecla erronea");
                    System.exit(0);
                }
            }
        });

        inputThread.start();
    }

// Llama a este método para reiniciar el hilo de entrada
    public static void restartInputThread() {
        continuar.set(true);
        startInputThread();
    }
}
