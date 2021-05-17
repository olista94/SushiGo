/**
 * Representa el juego del sushiGo, con sus reglas. 
 * Se recomienda una implementación modular.
 */
package com.uvigo.proyectosushigo.IU;

import com.uvigo.poyectosushigo.CORE.*;
import static com.uvigo.proyectosushigo.IU.ES.*;


public class Juego {
    private static String nombreJugador;
    private static int numJugadores;
    private static int numCartasMano = 0;
    private static Jugador [] jugadores;
    private static Baraja baraja;
    private static final int NUM_RONDAS = 3;//Numero maximo de rondas
    
    private static int ronda = 1;//Empieza en la ronda 1
    private static int posCarta;
       
    
    
    public static void inicio (){
        //Bienvenid@s a SUSHI GO        
        System.out.println("********** BIENVENID@S A SUSHI GO **********\n");
        
        // Preguntamos el numero de jugadores comprobando que el numero de jugadores esté entre 2 y 5
        do{
            numJugadores = pideNumero("Introduce el número de jugadores: (entre 2 y 5) ");
        }
        while(numJugadores < 2 || numJugadores > 5);
      
        
        // Creamos el array con el numero de jugadores
        jugadores = new Jugador[numJugadores];
        
        
        // Preguntamos el nombre a cada jugador pidiendole el nombre al usuario. y lo creamos
        for(int i = 0;i < numJugadores;i++){
            nombreJugador = pideCadena("Introduzca el nombre del jugador "+(i+1)+":");
            jugadores[i] = new Jugador(nombreJugador);
        }        
        
        
        // Calculamos el numero de cartas por mano
        calcularNumCartas(numJugadores); 
        

        // Creamos la baraja y la mezclamos
        baraja = new Baraja();
        baraja.mezclar();
        
        //Partida en si misma
        while (ronda <= NUM_RONDAS) { // ronda empieza en 1
            
            System.out.println("\n************** \t\t\t   **************");
            System.out.println("**************  NUMERO DE RONDA: " + ronda + " **************");
            System.out.println("************** \t\t\t   **************\n");
            
            
            //Damos cartas a jugadores
            for (int i = 0; i < numJugadores; i++) { //Jugadores

                for (int j = 0; j < numCartasMano; j++) { //numCartasMano
                    jugadores[i].recibeCarta(baraja.repartirCarta());
                }
            }

            for (int j = 0; j < numCartasMano; j++) { // Hasta que se acaben las cartas de la mano (tantos turnos como cartas en mano)

                System.out.println("\n****** Turno: " + j + " ******");

                for (int i = 0; i < numJugadores; i++) {//Muestra la mano de cada jugador

                    if (j != 0) { //No muestra la mesa en el turno 0
                        System.out.println("\nLa mesa de " + jugadores[i].getNombreJugador() + " esta formada por: ");

                        System.out.println(jugadores[i].getCartasMesa().toString());
                
                    }

                    System.out.println("\nLas cartas del jugador " + jugadores[i].getNombreJugador() + " son: ");
                    jugadores[i].getMano().toString();
                    System.out.println(jugadores[i].getMano());

                    // El jugador [i] elige la carta deseada y la hecha en la mesa
                    elegirCarta(posCarta, i);
                }

                Mano aux = jugadores[numJugadores - 1].entregarMano();

                for (int i = numJugadores - 1; i > 0; i--) { //Rotamos las manos entre los jugadores
                    jugadores[i].recibeMano(jugadores[i - 1].entregarMano());
                }
                jugadores[0].recibeMano(aux);
                

            }//Fin de una ronda
            System.out.println("\n******Fin de la ronda "+ ronda + " ******");
           
            //Puntuacion de ronda jugada      
            puntuacionRonda(jugadores,ronda);
            
            ronda++;

        }
        //Ganador-ganadores
        ganador();

    }
    

    public static void elegirCarta(int posCarta, int jug) {
        // El jugador elige la carta deseada mediante su posicion
        do {
            posCarta = pideNumero("Jugador " + jugadores[jug].getNombreJugador()
                    + " elige una carta de tu mano (entre la posicion entre 0 y "
                    + (jugadores[jug].getMano().getNumCartas() - 1) + ")");

        } while (posCarta < 0 || posCarta >= jugadores[jug].getMano().getNumCartas());
        
        
        // Coloca la carta seleccionada en la mesa
        if (jugadores[jug].getMano().getNumCartas() != 0) {
            Carta cartaSelect = jugadores[jug].getMano().quitarCarta(posCarta);

            jugadores[jug].colocarCarta(cartaSelect);
        }
    }
    
    public static void puntuacionRonda(Jugador [] jug, int ronda){ 
        Jugador jugadorRollitos = null;
        jugadorRollitos = jug[0];
        int numJug = 0;
        
        // Calculamos la puntuacion de cada ronda; asi obtenemos el numero de rollitos
        for (int i = 0; i < numJugadores; i++) {
            System.out.println("Puntuacion de " + jug[i].getNombreJugador() + " en ronda " + ronda + ": " + jug[i].sumarPuntos(ronda) + " puntos y tiene " + jug[i].getRollitos() + " rollitos.");
            // Numero de jugadores con mas rollitos
            if (jugadorRollitos.getRollitos() <= jug[i].getRollitos()) {
                jugadorRollitos = jug[i]; // Jugador con mas rollitos             
            }
        }
        System.out.println("");
        for (int i = 0; i < numJugadores; i++) {
            if (jugadorRollitos.getRollitos() > 0) { // Si tiene rollitos
                    if (jug[i].getRollitos() == jugadorRollitos.getRollitos()) {
                        numJug++; // Cuantos jugadores tienen los mismos rollitos que el que mas tiene
                    }
            }   
        }
      
        for (int i = 0; i < numJugadores; i++) {
            if(jug[i].getRollitos() == jugadorRollitos.getRollitos()){
                jug[i].setPuntos(ronda - 1, numJug);
            }
            // Monstramos la puntuacion total (ptos. + ptos. rollitos)
            System.out.println("Puntuacion total de " + jug[i].getNombreJugador() + " en ronda " + ronda + ": " + jug[i].getPuntos(ronda-1));

            int sumaRondas = 0;

            for (int j = 0; j < ronda; j++) {
                sumaRondas += jug[i].getPuntos(j);
            }

            jug[i].getCartasMesa().descartarCartas(); //Vacia la mesa de cartas
            System.out.println("La puntuacion total hasta el momento del jugador "
                    + jug[i].getNombreJugador() + " es " + sumaRondas + "\n ");
        }
    }
    
    public static int calcularNumCartas(int numJugadores){ // Calculamos el numero de cartas por mano
         
        switch(numJugadores){
            case 2:
                numCartasMano=9;
                break;
            case 3:
                numCartasMano=8;
                break;
            case 4:
                numCartasMano=7;
                break;
            case 5:
                numCartasMano=6;
                break;
	}
        return numCartasMano;
    }
        
    public static void ganador (){ //Muestra la puntuacion de cada ronda de cada jugador y el ganador o ganadores de la partida
        int puntuacionMax = 0;
        Jugador ganador = null;

        // Puntuacion de cada ronda
        for (int i = 0; i < NUM_RONDAS; i++) {
            //Jugador jugadore = jugadores[i];
            System.out.println("\nRonda " + (i + 1));
            // Jugadores
            for (int j = 0; j < numJugadores; j++) {
                System.out.println("Puntuacion de " + jugadores[j].getNombreJugador() + ": " + jugadores[j].getPuntos(i));
            }            
        }

        // Puntuacion final
        for (int i = 0; i < numJugadores; i++) {
            int aux = 0;
            for (int j = 0; j < NUM_RONDAS; j++) {
                aux += jugadores[i].getPuntos(j);
            }
            if (aux > puntuacionMax) {
                puntuacionMax = aux;
                ganador = jugadores[i];
            }
            System.out.println("La puntuacion final de " + jugadores[i].getNombreJugador() + " es " + aux);
        }
        
        // Quien o quienes ganan la partida       
        System.out.print("\nEl ganador del juego es " + ganador.getNombreJugador());

        //En caso de haber mas de 1 ganador 
        for (int g = 0; g < numJugadores; g++) {
            
            int aux = 0;
            
            for (int i = 0; i < NUM_RONDAS; i++) {
                aux += jugadores[g].getPuntos(i);
            }
            
            if(aux == puntuacionMax  && jugadores[g].getNombreJugador() != ganador.getNombreJugador()){
                    System.out.println(" y " + jugadores[g].getNombreJugador() + " \n");
            }
        }
        System.out.println("\nENHORABUENA  ");    
    }
   
}
