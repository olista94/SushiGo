/*
* Representa la baraja del sushiGo, 94 cartas, cada una representa a una comida 
* Estructura: se utilizará un TAD adecuado -> PILA (Actividad 5)
* Funcionalidad: estando la baraja desordenada, devolverá la carta situada encima del montón de cartas
 */
package com.uvigo.poyectosushigo.CORE;


public class Baraja {
    
    private int NUM_CARTAS = 94;

    private Carta[] baraja;

    public Baraja() {

        baraja = new Carta[NUM_CARTAS];
        // Nigiri (todos los tipos), Tempura,  Sashimi, Gyoza, Wasabi y Maki

        for(int i=0;i<5; i++){
            baraja[i] = new Carta("Niguiri de calamar");
        }
        for(int i=5;i<15; i++){
            baraja[i] = new Carta("Niguiri de salmon");
        }
        for(int i=15;i<20; i++){
            baraja[i] = new Carta("Niguiri de tortilla");
        }
        for(int i=20;i<34; i++){
            baraja[i] = new Carta("Tempura");
        }
        for(int i=34;i<48; i++){
            baraja[i] = new Carta("Sashimi");
        }
        for(int i=48;i<62; i++){
            baraja[i] = new Carta("Gyoza");
        }
        for(int i=62;i<68; i++){
            baraja[i] = new Carta("Wasabi");
        }
        for(int i=68;i<74; i++){
            baraja[i] = new Carta("Maki de 1 rollo");
        }
        for(int i=74;i<86; i++){
            baraja[i] = new Carta("Maki de 2 rollo");
        }
        for(int i=86;i<94; i++){
            baraja[i] = new Carta("Maki de 3 rollo");
        }
        barajar();
    }
    
    public void barajar()
    {
       int pos;
       Carta aux[]=new Carta[94];
       int i=0;
       while(i < getNUM_CARTAS()){
           do{
               pos=(int)(Math.random()*62);                   
           }while(aux[pos]!=null);
               aux[pos]=baraja[i];
               i++;
       }	
       baraja=aux;
    }
    
    public Carta darCarta()
    {
    	Carta actual = baraja[NUM_CARTAS-1];
    	baraja[NUM_CARTAS-1] = null;
    	NUM_CARTAS--;
    	return actual;
    }
        
    public int getNUM_CARTAS() {
        return NUM_CARTAS;
    }
     
}
