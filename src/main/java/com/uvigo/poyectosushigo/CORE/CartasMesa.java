/*
* Representa las cartas que coloca el jugador en la mesa (únicamente las suyas).
* Estructura: Se utilizarán TADs adecuados para su respresentación. En concreto:Una lista de pilas 
* Funcionalidad: colocar una carta en la mesa, calcular la puntuación de las cartas de la mesa, 
  calcular el número de rollitos, visualizar cartas de la mesa, descartar cartas de la mesa, etc
*/
package com.uvigo.poyectosushigo.CORE;


import lista.*;
import pila.*;

public class CartasMesa {
    
    private final ListaEnlazada<Pila<Carta>> cartasMesa;
    private boolean esApilable;
    private int rollitos = 0; //Para contar rollitos de Maki    
    
    
    public CartasMesa(){
        //cartasMesa = new ListaEnlazada <Carta>();
        cartasMesa = new ListaEnlazada<Pila<Carta>>();
    }
    
    
    /*
      Pasar la carta deseada c
      Comprobar que la ultima carta de una pila es de x tipo
           Si lo es, y la otra carta es compatible. Apilable
     */
    private boolean esApilable(Carta c, Pila<Carta> p) {//Comprueba si una carta es apilable
        boolean apilable = false;

        if (!cartasMesa.esVacio()) {

            if ("Wasabi".equals(p.top().getNombreCarta())) { //Nigiris solo apilables con wasabi debajo            
                if ("Nigiri de tortilla".equals(c.getNombreCarta())
                        || "Nigiri de salmon".equals(c.getNombreCarta())
                        || "Nigiri de calamar".equals(c.getNombreCarta())) {
                    apilable = true;
                }

            } else if ("Maki".equals(p.top().getNombreCarta().substring(0, 4)) && c.getNombreCarta().substring(0, 4).equals("Maki")) { //Makis apilables entre si               
                apilable = true;

            } else if ("Tempura".equals(p.top().getNombreCarta()) && "Tempura".equals(c.getNombreCarta())) { //Tempuras apilables entre si
                apilable = true;

            } else if ("Sashimi".equals(p.top().getNombreCarta()) && "Sashimi".equals(c.getNombreCarta())) { //Sashimi apilables entre si
                apilable = true;

            } else if ("Gyoza".equals(p.top().getNombreCarta()) && "Gyoza".equals(c.getNombreCarta())) { //Gyoza apilables entre si
                apilable = true;

            }
        }

        return apilable;
    }
    
    
    
    public void colocarCartaPila(Carta c){ //Coloca la carta en la mesa en una pila 
        
        if (!cartasMesa.esVacio()) {
            int fin = cartasMesa.tamaño(); //LLega al final de la mesa 
            int cont = 0; //Cuenta en numero de pilas para compararlo con fin
            boolean colocada = false;

            for (Pila<Carta> pila : cartasMesa) {

                if ((esApilable(c, pila) == true) && colocada == false) {

                    System.out.println("Insertada correctamente la carta " + c.getNombreCarta() + " sobre " + pila.top());
                    pila.push(c);
                    colocada = true;
                }
            }
            
            if (colocada == false) {
                Pila<Carta> p = new EnlazadaPila<>();//Pila nueva
                p.push(c);
                cartasMesa.insertarFinal(p);
                System.out.println("Insertada correctamente la carta " + c.getNombreCarta());
            }
            

        } else { // Para turno 0
            Pila<Carta> np = new EnlazadaPila<>();
            np.push(c);
            cartasMesa.insertarFinal(np);
            System.out.println("Insertada correctamente la carta " + c.getNombreCarta());
        }
    }
    
    
    public int rollitos(){ //Indica cuantos rollitos de Maki hay
        return rollitos;
    }
         
    private void volcarPila(Pila<Carta> desechable, Pila<Carta> original){  //metodo auxiliar para darle la vuelta a una pila      
        
         while(!desechable.esVacio()){          
            original.push(desechable.pop());           
        }                 
    }
    
    public int contarPuntuacion(){
        int puntuacion = 0;
        
        int puntosNigiri = 0;
        
        int tempura = 0;
        int sashimi = 0;
        int gyoza = 0;
        int wasabi = 3;//Multiplicador de wasabi
              
        for (Pila<Carta> p : cartasMesa) {//Recorremos todas las pilas de la mesa

            ListaEnlazada<Carta> aux = pila_a_lista(p); //Crea una lista de cartas a partir de una pila

            for (Carta car : aux) {//Recorremos todas las cartas de cada pila
                switch (car.getNombreCarta()) {
                    case "Maki 1":
                        rollitos += 1;
                        break;
                    case "Maki 2":
                        rollitos += 2;
                        break;
                    case "Maki 3":
                        rollitos += 3;
                        break;
                    case "Nigiri de tortilla":
                        puntuacion += 1;
                        puntosNigiri = 1;

                        break;
                    case "Nigiri de salmon":
                        puntuacion += 2;
                        puntosNigiri = 2;

                        break;
                    case "Nigiri de calamar":
                        puntuacion += 3;
                        puntosNigiri = 3;

                        break;
                    case "Wasabi":
                        if (aux.tamaño() == 2) {
                            puntuacion += (wasabi * puntosNigiri) - puntosNigiri;
                        } else {
                            puntuacion += 0;
                        }
                        break;
                    case "Tempura":
                        tempura++;
                        break;
                    case "Sashimi":
                        sashimi++;
                        break;
                    case "Gyoza":
                        gyoza++;
                        break;
                }//fin case
            } //fin for
        }
                
        
        //Puntuacion para tempura
        puntuacion = puntuacion + ((tempura / 2) * 5);
        //Puntuacion para sashimi
        puntuacion = puntuacion + ((sashimi / 3) * 10);       
       
       
       //Puntuacion para gyoza
        if (gyoza == 1) {
            puntuacion++;
        } else if (gyoza == 2) {
            puntuacion += 3;
        } else if (gyoza == 3) {
            puntuacion += 6;
        } else if (gyoza == 4) {
            puntuacion += 10;
        } else if (gyoza >= 5) {
            puntuacion += 15;
        }
              
        
       return puntuacion; 
    }
    
    private ListaEnlazada <Carta> pila_a_lista(Pila<Carta> p){// Metodo para transformar una pila en una lista
        
        ListaEnlazada <Carta> aux = new ListaEnlazada<>();
        while(!p.esVacio()){
            aux.insertarFinal(p.pop());
        }
        return aux;
    }
    
    public void descartarCartas(){//Vaciamos la mesa

        for(Pila <Carta> p: cartasMesa){
            cartasMesa.suprimir(p);
        }       
        
        rollitos = 0;
    }
    
    
   @Override
    public String toString() {
        
        StringBuilder toRet = new StringBuilder();
        
        int nMonton = 0;//Indica la posicion del monton o pila en la mesa
        int indiceCarta = 0; //Indica la posicion de la carta en la pila o monton        
        Pila<Carta> paux = new EnlazadaPila<>(); //Pila auxiliar para recuperar la pila original
        

       // System.out.println("TAMANO MESA "+ cartasMesa.tamaño() + "\n");//Borrar (indica cuantas pilas hay en la mesa)
        
        for(Pila <Carta> pila: cartasMesa){
            //System.out.println("Monton " + nMonton + " con cartas: ");
            toRet.append("-Monton ").append(nMonton).append(" con cartas: \n");
            Carta caux;
            
            
            while (!pila.esVacio()) {
                                
                //System.out.println("El monton " + nMonton + " tiene " + (paux.tamaño()+1) + " elementos ");//Borrar
                caux = pila.pop();                
               
                toRet.append(caux); 
                //System.out.print("[" + indiceCarta + "] " + caux);               
                               
                paux.push(caux);
                
                indiceCarta++;
            }
            
            indiceCarta = 0;// Resetea el indice de carta para la siguiente pila          
            volcarPila(paux,pila); //Regresa las cartas de paux a la pila original

            nMonton++;
        }       

        return toRet.toString();
    }
    
}