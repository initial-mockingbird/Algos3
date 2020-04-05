package app;

import java.util.HashSet;
import java.util.Objects;

public class AristaPrime extends LadoPrime{

    /**
     * Clase paralela a Arista la cual sirve para representar el multigrafo
     * Como un digrafo con costos positivos. 
     */ 
    AristaPrime(VerticePrime nv, VerticePrime nw,double ncosto){
        super.fst = nv;
        super.snd = nw;
        super.costo = ncosto;
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
        actual.add(super.fst);
        actual.add(super.snd);
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
        return (int) ((Objects.hash(super.fst,super.snd)+Objects.hash(super.snd,super.fst))/2);
    }
}