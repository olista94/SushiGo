/*
* Representa las cartas que tiene un jugador en la mano (las que dispone para jugar).
* Estructura: Se utilizarán TAD adecuado -> LISTA (Actividad 7)
* Funcionalidad: añadir carta a la mano, quitar carta de la mano, visualizar cartas de la mano,...
*/
package com.uvigo.poyectosushigo.CORE;

public class Mano 
{
   private int numCartas;
   //Lista <Integer> cartasMano = new Lista<>();
   private Carta[]cartasMano;

    public Mano(int numCartas) {
        numCartas = 0;
        cartasMano = new Carta[numCartas];
    }

    public int getNumCartas() {
        return numCartas;
    }
    
    // Añade una carta a la mano y aumeta el numero de cartas de la mano
    public void anhadirCarta(Carta carta){
        cartasMano[numCartas] = carta;
        numCartas++;
    }
   
//    public void quitarCarta(){
//        int i=0;
//        while(i < numCartas){
//            mano[i] = new Carta();
//            i++;        
//        }
//        numCartas = 0;
//    }


}
