package app;
import java.util.HashSet;
import java.util.Objects;
/**
 * Integrantes: Daniel Pinto Daniela Ramirez
 */

class Arista extends Lado{

    /*Arista tiene los mismos atributos que Lado ya que este extiende a la clase abstracta Lado ademas de tener sus propios atributos
    * v1 que seria el primer Vertice de la arista y v2 el segundo Vertice de la arista*/

    Arista(Vertice Verticei, Vertice Verticef, String Tipo, Double peso){
        super.v1 = Verticei;
        super.v2 = Verticef;
        super.Tipo = Tipo;
        super.p = peso;
    }
    Arista(String Verticei, String Verticef, String Tipo, Double peso){
        super.v1 = new Vertice(Verticei);
        super.v2 = new Vertice(Verticef);
        super.Tipo = Tipo;
        super.p = peso;
    }
    /**
     * Otra forma de crear una Arista.
     * @return Arista: Arista con los parametros especificados.
     */
    public Arista crearArista(Vertice Verticei, Vertice Verticef, String Tipo, Double peso){
        return new Arista(Verticei, Verticef,super.Tipo,peso);
    }


    /**
     * Retorna el primer Vertice de la arista.
     * @return v1 (Vertice): primer vertice de la arista.
     */
    public Vertice getExtremo1(){
        return super.v1;
    }

    /**
     * Retorna el segundo Vertice de la arista.
     * @return v2 (Vertice): segundo vertice de la arista.
     */
    public Vertice getExtremo2(){
        return super.v2;
    }

    public String get_tipo(){
        return super.Tipo;
    }

    /** 
     * Regresa un String con la informacion de la Arista
     * @return Representacion como string de la Arista.
     */
    @Override
    public String toString(){
        
        return "Arista "+super.Tipo+": Peso: "+String.valueOf(super.p)+"  Extremo 1: "+super.v1.id+"  Extremo 2: "+super.v2.id;
    }
    
    /**
     * {@inheritDoc}
     * La comparacion se lleva a cabo mediante los vertices.
     */
    @Override
    public boolean equals(Object a)
    {
        if (!(a.getClass() == super.getClass())){
            return false;
        }

        // Probaremos que ambos objetos son iguales por igualdad de conjuntos.
        HashSet<Vertice> actual = new HashSet<Vertice>();
        actual.add(super.v1);
        actual.add(super.v2);
        HashSet<Vertice> aComparar = new HashSet<Vertice>();
        aComparar.add( ((Arista) a).getExtremo1() );
        aComparar.add( ((Arista) a).getExtremo2() );
        return actual.equals(aComparar) && ((Arista) a).getTipo().equals(super.Tipo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode(){ 
        return (int) ((Objects.hash(super.v1,super.v2,super.Tipo)+Objects.hash(super.v2,super.v1,super.Tipo))/2);
    }

}