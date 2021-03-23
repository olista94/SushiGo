/*
 * Representa a un jugador de la partida, identificado por el nombre 
 * Funcionalidad: escoge una carta de su mano; la colocará en su mesa; coge cartas de la baraja para la mano,
 *                entrega las cartas de su mano; coge las cartas de otra mano; calcula su puntuación por ronda;
 *                calcula su puntuación total; cuenta cuantos rollitos tiene en su mesa; ....
 */
package com.uvigo.poyectosushigo.CORE;

public class Jugador {
    private String nombreJugador;
    private Mano Mano;
    private CartasMesa cartasMesa;
    private int[] puntosRonda;

    public Jugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }
    
//    public Jugador(String nombreJugador, int jugadores) {
//        this.nombreJugador = nombreJugador;
//        Mano = new Mano(jugadores);
//        CartasMesa=new CartasMesa(jugadores);
//        puntosRonda = new int[3];
//    }
    
    

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public Mano getMano() {
        return Mano;
    }

    public CartasMesa getCartasMesa() {
        return cartasMesa;
    }

    public int[] getPuntosRonda() {
        return puntosRonda;
    }

    
    
	
}
