package app;

import java.util.Objects;

/**
 * Integrantes: Daniel Pinto Daniela Ramirez
 */

class Arco extends Lado{

    /*Arco tiene los mismos atributos que Lado ya que este extiende a la clase abstracta Lado ademas de tener sus propios atributos
    * v1 que seria el Vertice inicial de el arco y v2 el Vertice final de el arco*/

    Arco(Vertice Verticei, Vertice Verticef, String Tipo, Double peso){
        super.v1 = Verticei;
        super.v2 = Verticef;
        super.Tipo = Tipo;
        super.p = peso;
    }
    Arco(String Verticei, String Verticef, String Tipo, Double peso){
        super.v1 = new Vertice(Verticei);
        super.v2 = new Vertice(Verticef);
        super.Tipo = Tipo;
        super.p = peso;
    }

    /**
     * Otra forma de crear un arco.
     * @return Arco: arco con los parametros especificados.
     */
    public Arco crearArco(Vertice Verticei, Vertice Verticef, String Tipo, Double peso){
        return new Arco(Verticei, Verticef,Tipo,peso);
    }


    /**
     * Retorna el Extremo inicial de el arco.
     * @return v1 (Vertice) extremo inicial del arco.
     */
    public Vertice getExtremoInicial(){
        return super.v1;
    }

    /**
     * Retorna el Extremo final de el arco
     * @return v2 (Vertice): Extremo final del arco. 
     */
    public Vertice getExtremoFinal(){
        return super.v2;
    }


    /**
     * Indica si el vertice es el vertice inicial del arco a
     * @param v (Vertice): Posible extremo inicial de a.
     * @return True|False si es Extremo Inicial | No lo es.
     */
    public Boolean esExtremoInicial(Vertice v){
        Boolean Respuesta;
        Respuesta = true;

        if (v.getId() == v1.getId()){
            return Respuesta;
        }
        else{
            Respuesta = false;
            return Respuesta;
        }
    }

    /**
     * Indica si el vertice es el vertice final del arco a
     * @param v (Vertice): Posible extremo final de a.
     * @return True|False si es Extremo final | No lo es.
     */
    public Boolean esExtremoFinal(Vertice v){
        Boolean Resultado;
        Resultado = true;

        if (v.getId() == v2.getId()){
            return Resultado;
        }
        else{
            Resultado = false;
            return Resultado;
        }
    }

    public String get_tipo(){
        return super.Tipo;
    }

    /** 
     * Regresa un String con la informacion de el Arco
     * @return Representacion como string del arco.
     */
    @Override
    public String toString(){
        return "Peso: "+String.valueOf(super.p)+"  Extremo Inicial: "+super.v1.id+"  Extremo Final: "+super.v2.id;
    }
    
    /**
     * {@inheritDoc}
     * La comparacion se lleva a cabo mediante los vertices y el tipo.
     */
    @Override
    public boolean equals(Object a)
    {
        if (!(a.getClass() == super.getClass())){
            return false;
        }
        return  (super.v1.equals(( (Arco) a).getExtremoInicial()))
        && (super.v2.equals(( (Arco) a).getExtremoFinal())) && ((Arco) a ).getTipo().equals(super.Tipo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode(){
        return Objects.hash(super.v1,super.v2,super.Tipo);
    }

}