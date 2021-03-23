/**
 * Representa el juego del sushiGo, con sus reglas. 
 * Se recomienda una implementación modular.
 */
package com.uvigo.proyectosushigo.IU;

import com.uvigo.poyectosushigo.CORE.Jugador;
import static com.uvigo.proyectosushigo.IU.ES.*;

import java.util.Scanner;


public class Juego {
    

    public static void inicio (){
        String nombreJugador;
        int numJugadores;
        Jugador [] Jugadores;
        
        // Comprueba que el numero de jugadores esté entre 2 y 5
        do{
//            System.out.println("Introduce el número de jugadores: (entre 2 y 5) ");
//            entrada = leer.nextLine();
//            numJugadores = Integer.parseInt(entrada);
              numJugadores = pideNumero("Introduce el número de jugadores: (entre 2 y 5) ");
        }
        while(numJugadores < 2 || numJugadores > 5);
        
        Jugadores = new Jugador[numJugadores];
        
        for(int i=0;i<numJugadores;i++){
            //Creamos cada jugador pidiendole el nombre al usuario.
            nombreJugador = pideCadena("Introduzca el nombre del jugador "+(i+1)+":");
            Jugadores[i] = new Jugador(nombreJugador);
        }
        
        for(int i=0;i<numJugadores;i++){
            System.out.println(Jugadores[i]);
        }
        

    }
}
