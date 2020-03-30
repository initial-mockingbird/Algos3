package app;

import java.util.Objects;

public class VerticePrime {
    Vertice v;
    String color;

    /**
     * Wrapper around un vertice para dar la estructura (Vertice, color)
     * Sin tener que lidiar con el tema de tuplas.
     * @param nv
     * @param ncolor
     */
    VerticePrime(Vertice nv, String ncolor){
        this.v = nv;
        this.color = ncolor;
    }

    /**
     * Obtiene el vertice al cual se encapsulo.
     * @return vertice v
     */
    public Vertice get_vertice(){
        return this.v;
    }

    /**
     * Obtiene el color
     * @return String color.
     */
    public String get_color(){
        return this.color;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object a){
        if (!(a.getClass() == this.getClass())){
            return false;
        }
        boolean b = this.v.equals( ((VerticePrime)a).get_vertice()) && this.color.equals(((VerticePrime)a).get_color());
        return b;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode(){
        return Objects.hash(this.v,this.color);
    }


    /**
     * Retorna un String con la informacion del Vertice
     * @return Result (String): representacion de tipo String del vertice.
     */
    @Override
    public String toString(){
        return  ("Vertice "+this.get_vertice().getId()+":  Nombre: "+this.get_vertice().getNombre());
    }
}