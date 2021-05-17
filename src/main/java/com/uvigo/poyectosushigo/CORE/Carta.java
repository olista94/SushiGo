/*
 * Representa una carta, formada por un nombre
 */
package com.uvigo.poyectosushigo.CORE;

public class Carta {

    private final String nombreCarta;
    
    public Carta(String nombreCarta) {
        this.nombreCarta = nombreCarta;
    }

    public String getNombreCarta() {
        return nombreCarta;
    }

    @Override
    public String toString() {
        return nombreCarta + "\n";
    }
}
