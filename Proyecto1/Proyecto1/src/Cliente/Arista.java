package Cliente;
import java.util.HashSet;
import java.util.Objects;
/**
 * Integrantes: Daniel Pinto Daniela Ramirez
 */

class Arista extends Lado{

    /*Arista tiene los mismos atributos que Lado ya que este extiende a la clase abstracta Lado ademas de tener sus propios atributos
    * Vu que seria el primer Vertice de la arista y Vv el segundo Vertice de la arista*/
    Vertice Vu;
    Vertice Vv;

    Arista(Vertice Verticei, Vertice Verticef, int Tipo, Double peso){
        this.Vu = Verticei;
        this.Vv = Verticef;
        this.Tipo = Tipo;
        this.p = peso;
    }
    Arista(String Verticei, String Verticef, int Tipo, Double peso){
        this.Vu = new Vertice(Verticei);
        this.Vv = new Vertice(Verticef);
        this.Tipo = Tipo;
        this.p = peso;
    }
    /**
     * Otra forma de crear una Arista.
     * @return Arista: Arista con los parametros especificados.
     */
    public Arista crearArista(Vertice Verticei, Vertice Verticef, int Tipo, Double peso){
        return new Arista(Verticei, Verticef,Tipo,peso);
    }


    /**
     * Retorna el primer Vertice de la arista.
     * @return Vu (Vertice): primer vertice de la arista.
     */
    public Vertice getExtremo1(){
        return this.Vu;
    }

    /**
     * Retorna el segundo Vertice de la arista.
     * @return Vv (Vertice): segundo vertice de la arista.
     */
    public Vertice getExtremo2(){
        return this.Vv;
    }

    /** 
     * Regresa un String con la informacion de la Arista
     * @return Representacion como string de la Arista.
     */
    @Override
    public String toString(){
        
        return "Arista "+this.Tipo+": Peso: "+String.valueOf(this.p)+"  Extremo 1: "+this.Vu.id+"  Extremo 2: "+this.Vv.id;
    }
    
    /**
     * {@inheritDoc}
     * La comparacion se lleva a cabo mediante los vertices.
     */
    @Override
    public boolean equals(Object a)
    {
        if (!(a.getClass() == this.getClass())){
            return false;
        }

        // Probaremos que ambos objetos son iguales por igualdad de conjuntos.
        HashSet<Vertice> actual = new HashSet<Vertice>();
        actual.add(this.Vu);
        actual.add(this.Vv);
        HashSet<Vertice> aComparar = new HashSet<Vertice>();
        actual.add( ((Arista) a).getExtremo1() );
        actual.add( ((Arista) a).getExtremo2() );
        return actual.equals(aComparar);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode(){
        return Objects.hash(this.Vu,this.Vv);
    }

}