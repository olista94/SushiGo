/*
* Representa la baraja del sushiGo, 94 cartas, cada una representa a una comida 
* Estructura: se utilizará un TAD adecuado -> PILA (Actividad 5)
* Funcionalidad: estando la baraja desordenada, devolverá la carta situada encima del montón de cartas
 */
package com.uvigo.poyectosushigo.CORE;

import java.util.ArrayList;
import java.util.Collections;
import pila.*;


public class Baraja {
	
    private final int NUM_CARTAS = 94;
    private final Pila <Carta> baraja;
    
    
    public Baraja() {
        baraja = new EnlazadaPila<>();

        for (int i = 0; i < 5; i++) {
            baraja.push(new Carta("Nigiri de calamar"));
        }
        for (int i = 0; i < 10; i++) {
            baraja.push(new Carta("Nigiri de salmon"));
        }
        for (int i = 0; i < 5; i++) {
            baraja.push(new Carta("Nigiri de tortilla"));
        }
        for (int i = 0; i < 14; i++) {
            baraja.push(new Carta("Tempura"));
        }
        for (int i = 0; i < 14; i++) {
            baraja.push(new Carta("Sashimi"));
        }
        for (int i = 0; i < 14; i++) {
            baraja.push(new Carta("Gyoza"));
        }
        for (int i = 0; i < 6; i++) {
            baraja.push(new Carta("Wasabi"));
        }
        for (int i = 0; i < 6; i++) {
            baraja.push(new Carta("Maki 1"));
        }
        for (int i = 0; i < 12; i++) {
            baraja.push(new Carta("Maki 2"));
        }
        for (int i = 0; i < 8; i++) {
            baraja.push(new Carta("Maki 3"));
        }

    }
    
    public void mezclar(){
        
        // Crea Array para barajar luego
        ArrayList <Carta> aux = new ArrayList<>();
        
        // Vuelca las cartas de la Pila al ArrayList
        while(!baraja.esVacio()){
            aux.add(baraja.pop());
        }
        
        // Mezcla las cartas (tenemos que usar ArrayList)
        Collections.shuffle(aux);
     
        //Vuelca las cartas mezcladas a la Pila baraja: las primeras cartas del ArrayList serán las ultimas de la Pila
        for (int j = 0; j < aux.size(); j++) {
            baraja.push(aux.get(j));
        } 
    }


    public Carta repartirCarta() {

        return baraja.pop();
    }

}
