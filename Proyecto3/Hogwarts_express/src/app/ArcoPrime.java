package app;

import java.util.Objects;

public class ArcoPrime extends LadoPrime {

    /**
     * Clase paralela a Arco la cual sirve para representar el multigrafo
     * Como un digrafo con costos positivos. 
     */ 
    ArcoPrime(VerticePrime nv, VerticePrime nw,double ncosto){
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
        if (!(a.getClass() == super.getClass()) ){
            return false;
        }

        
        return  ((ArcoPrime) a).get_inicioPrime().equals(super.fst) &&
                ((ArcoPrime) a).get_finalPrime().equals(super.snd); 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode(){ 
        return (int) Objects.hash(super.fst,super.snd);
    }
}