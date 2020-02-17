package Cliente;

import java.util.Objects;

/**
 * Integrantes: Daniel Pinto Daniela Ramirez
 */

class Arco extends Lado{

    /*Arco tiene los mismos atributos que Lado ya que este extiende a la clase abstracta Lado ademas de tener sus propios atributos
    * Vi que seria el Vertice inicial de el arco y Vf el Vertice final de el arco*/
    Vertice Vi;
    Vertice Vf;

    Arco(Vertice Verticei, Vertice Verticef, int Tipo, Double peso){
        this.Vi = Verticei;
        this.Vf = Verticef;
        super.Tipo = Tipo;
        super.p = peso;
    }
    Arco(String Verticei, String Verticef, int Tipo, Double peso){
        this.Vi = new Vertice(Verticei);
        this.Vf = new Vertice(Verticef);
        super.Tipo = Tipo;
        super.p = peso;
    }

    /**
     * Otra forma de crear un arco.
     * @return Arco: arco con los parametros especificados.
     */
    public Arco crearArco(Vertice Verticei, Vertice Verticef, int Tipo, Double peso){
        return new Arco(Verticei, Verticef,Tipo,peso);
    }


    /**
     * Retorna el Extremo inicial de el arco.
     * @return Vi (Vertice) extremo inicial del arco.
     */
    public Vertice getExtremoInicial(){
        return this.Vi;
    }

    /**
     * Retorna el Extremo final de el arco
     * @return Vf (Vertice): Extremo final del arco. 
     */
    public Vertice getExtremoFinal(){
        return this.Vf;
    }


    /**
     * Indica si el vertice es el vertice inicial del arco a
     * @param v (Vertice): Posible extremo inicial de a.
     * @return True|False si es Extremo Inicial | No lo es.
     */
    public Boolean esExtremoInicial(Vertice v){
        Boolean Respuesta;
        Respuesta = true;

        if (v.getId() == Vi.getId()){
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

        if (v.getId() == Vf.getId()){
            return Resultado;
        }
        else{
            Resultado = false;
            return Resultado;
        }
    }


    /** 
     * Regresa un String con la informacion de el Arco
     * @return Representacion como string del arco.
     */
    @Override
    public String toString(){
        return "Peso: "+String.valueOf(super.p)+"  Extremo Inicial: "+this.Vi.id+"  Extremo Final: "+this.Vf.id;
    }
    
    /**
     * {@inheritDoc}
     * La comparacion se lleva a cabo mediante los vertices y el tipo.
     */
    @Override
    public boolean equals(Object a)
    {
        if (a.getClass() == this.getClass()){
            return (this.Tipo == ( (Arco) a).getTipo()) && (this.Vi.equals(( (Arco) a).getExtremoInicial()))
            && (this.Vf.equals(( (Arco) a).getExtremoFinal()));
        }
       return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode(){
        return Objects.hash(this.Vi,this.Vf,super.Tipo);
    }

}