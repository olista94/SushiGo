/*
* Representa las cartas que tiene un jugador en la mano (las que dispone para jugar).
* Estructura: Se utilizarán TAD adecuado. -> LISTA (Actividad 7)
* Funcionalidad: añadir carta a la mano, quitar carta de la mano, visualizar cartas de la mano,...
*/
package com.uvigo.poyectosushigo.CORE;

//import java.util.LinkedList;
import lista.*;


public class Mano {
    
    private final Lista<Carta> mano;
    
    public Mano(){
        mano = new ListaEnlazada<>();
    }
    
    public void ordenar(){
        int var = 0;
        Carta[] aux = new Carta[mano.tamaño()];

        while (!mano.esVacio()) {
            aux[var] = mano.recuperar();
            mano.suprimir(aux[var]);
            var++;
        }

        for (int i = 1; i < aux.length; i++) {
            Carta elem = aux[i];
            int j = (i - 1);

            while ((j >= 0) && (elem.getNombreCarta().compareTo(aux[j].getNombreCarta()) < 0)) {
                aux[j + 1] = aux[j--];
            }
            aux[j + 1] = elem;
        }

        for (int i = 0; i < aux.length; i++) {
            mano.insertarFinal(aux[i]);
        }
    }
    
    public void anhadirCarta(Carta carta){
        mano.insertarFinal(carta);
    }
    
    public int getNumCartas(){
        return mano.tamaño();
    }

    public Carta quitarCarta(int pos){
        Carta[] aux = new Carta[mano.tamaño()];
        int i = 0;
        
        for(Carta carta: mano){
            aux[i]=carta;
            i++;
        }
        
        Carta cartaDevuelta = aux[pos];
        mano.suprimir(cartaDevuelta);
        
        return cartaDevuelta;
    }

    @Override
    
    public String toString(){
        ordenar();
        String toRet = "";
        int i = 0;

        for (Carta c : mano) {
            toRet = toRet + "[" + i + "] " + c.toString();
            i++;
        }
        
        return toRet;
    }
}
