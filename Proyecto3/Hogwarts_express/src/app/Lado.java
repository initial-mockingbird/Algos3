package app;



/**
 * Integrantes: Daniel Pinto Daniela Ramirez
 */

public abstract class Lado{

    /*Atributos de la Clase Lado:
    * Tipo: Identificador de Lado
    * v1: Vertice 1
    * v2: Vertice 2
    * p: Peso de el lado
    */

    String Tipo;
    Vertice v1;
    Vertice v2;
    Double p;

    /*Indica si el lado incide en el vertice*/
    public Boolean incide(Vertice v){
        Boolean Respuesta;
        Respuesta = false;
        
        if (v.getId() == this.v1.getId()){
            Respuesta = true;
            return Respuesta;
        }
        if (v.getId() == this.v2.getId()){
            Respuesta = true;
            return Respuesta;
        }
        else{
            return Respuesta;
        }

    }

    /*Retorna el peso de el Lado*/
    public Double getPeso(){
        return this.p;
    }

    /*Retorna el Tipo de el Lado*/
    public String getTipo(){
        return this.Tipo;
    }

    /*Retorna un String con la informacion de el Lado*/
    public String toString(){
        return  "Tipo: "+this.Tipo+" Peso: "+String.valueOf(this.p);
    }
    
   
}