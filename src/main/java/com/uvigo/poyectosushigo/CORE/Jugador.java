/*
 * Representa a un jugador de la partida, identificado por el nombre 
 * Funcionalidad: escoge una carta de su mano; la colocará en su mesa; coge cartas de la baraja para la mano,
 *                entrega las cartas de su mano; coge las cartas de otra mano; calcula su puntuación por ronda;
 *                calcula su puntuación total; cuenta cuantos rollitos tiene en su mesa; ....
 */
package com.uvigo.poyectosushigo.CORE;

public class Jugador {
    
    private static final int NUM_RONDAS = 3; //En número máximo de rondas por partida
    private String nombreJugador;

    private Mano mano;
    private CartasMesa cartasMesa;

    private int[] puntos;
    private int puntosTotales; //Puntos totales final de partida

    public Jugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
        mano = new Mano();
        cartasMesa = new CartasMesa();
        puntos = new int[NUM_RONDAS];
        puntosTotales = 0;
    }

    public static int getNUM_RONDAS() {
        return NUM_RONDAS;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public Mano getMano() { //Coge cartas de la baraja para la mano
        return mano;
    }

    public CartasMesa getCartasMesa() { //Todas las cartas echadas en la mesa
        return cartasMesa;
    }

    public int getRollitos() { // Obtiene el nº de rollitos que obtiene el jugador en la ronda actual
        return cartasMesa.rollitos();
    }

    public int getPuntos( int ronda) { // Obtiene los puntos de una ronda en concreto
        return puntos [ronda];
    }

    public void recibeCarta(Carta carta) { //coge las cartas de otra mano
        mano.anhadirCarta(carta);
    }

    public void recibeMano(Mano m) { //coge la mano de otro jugador
        mano = m;
    }

    public void colocarCarta(Carta carta) { //escoge una carta de su mano y la coloca carta en la mesa
        cartasMesa.colocarCartaPila(carta);
    }

    public Mano entregarMano() { //entrega las cartas de su mano
        return mano;
    }

    public int sumarPuntos(int ronda) { //calcula su puntuación por ronda (como ronda empieza en 1 se resta 1 unidad para almacenar en pos anterior)
        return puntos[ronda - 1] = cartasMesa.contarPuntuacion();
    }
    
    public void setPuntos(int ronda, int numJug) {
        puntos[ronda] += (6/numJug);
        //puntos[ronda];
    }

    @Override
    public String toString(){
        StringBuilder toRet = new StringBuilder();
        
        //toRet.append("El jug");
        
        return toRet.toString();
    }
}
