package app;

import java.util.HashSet;
import java.util.Objects;

public class AristaPrime {
    VerticePrime fst;
    VerticePrime snd;
    double costo;

    /**
     * Clase paralela a Arista la cual sirve para representar el multigrafo
     * Como un digrafo con costos positivos. 
     */ 
    AristaPrime(VerticePrime nv, VerticePrime nw,double ncosto){
        this.fst = nv;
        this.snd = nw;
        this.costo = ncosto;
    }


    /**
     * Getter del vertice inicial
     * @return VerticePrime fst: vertice inicial del Arista.
     */
    public VerticePrime get_inicioPrime(){
        return this.fst;
    }


    /**
     * Getter del vertice final.
     * @return VerticePrime snd: vertice final del Arista.
     */
    public VerticePrime get_finalPrime(){
        return this.snd;
    }


    /**
     * Getter para obtener el valor around el wrapper del vertice inicial. 
     * @return Vertice fst: vertice inicial del Arista.
     */
    public Vertice get_inicio(){
        return this.fst.get_vertice();
    }

    /**
     * Getter para obtener el valor around el wrapper del vertice final.
     * @return Vertice fst: vertice final del Arista.
     */
    public Vertice get_final(){
        return this.snd.get_vertice();
    }

    /**
     * Obtiene el costo del Arista.
     * @return
     */
    public double get_costo(){
        return this.costo;
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
        HashSet<VerticePrime> actual = new HashSet<VerticePrime>();
        actual.add(this.fst);
        actual.add(this.snd);
        HashSet<VerticePrime> aComparar = new HashSet<VerticePrime>();
        aComparar.add( ((AristaPrime) a).get_inicioPrime() );
        aComparar.add( ((AristaPrime) a).get_finalPrime() );
        return actual.equals(aComparar);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode(){ 
        return (int) ((Objects.hash(this.fst,this.snd)+Objects.hash(this.snd,this.fst))/2);
    }
}