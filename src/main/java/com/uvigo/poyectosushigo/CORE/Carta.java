/*
 * Representa una carta, formada por un nombre
 */
package com.uvigo.poyectosushigo.CORE;


public class Carta {
    
    private String nombre;
	
    public Carta(){

            nombre = "";
    }

    public Carta(String no)
    {
            nombre = no;

    }
    public String getNombre()
    {
            return nombre;
    }

    public String toString()
    {
            return ("La carta es: "+nombre);
    }

	
}
