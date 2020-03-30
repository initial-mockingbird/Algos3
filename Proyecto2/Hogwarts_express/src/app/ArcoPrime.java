package app;

import java.util.Objects;

public class ArcoPrime {
    VerticePrime fst;
    VerticePrime snd;
    double costo;

    /**
     * Clase paralela a Arco la cual sirve para representar el multigrafo
     * Como un digrafo con costos positivos. 
     */ 
    ArcoPrime(VerticePrime nv, VerticePrime nw,double ncosto){
        this.fst = nv;
        this.snd = nw;
        this.costo = ncosto;
    }

    /**
     * Getter del vertice inicial
     * @return VerticePrime fst: vertice inicial del arco.
     */
    public VerticePrime get_inicioPrime(){
        return this.fst;
    }

    /**
     * Getter del vertice final.
     * @return VerticePrime snd: vertice final del arco.
     */
    public VerticePrime get_finalPrime(){
        return this.snd;
    }

    /**
     * Getter para obtener el valor around el wrapper del vertice inicial. 
     * @return Vertice fst: vertice inicial del arco.
     */
    public Vertice get_inicio(){
        return this.fst.get_vertice();
    }
    /**
     * Getter para obtener el valor around el wrapper del vertice final.
     * @return Vertice fst: vertice final del arco.
     */
    public Vertice get_final(){
        return this.snd.get_vertice();
    }


    /**
     * Obtiene el costo del arco.
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
        if (!(a.getClass() == this.getClass()) ){
            return false;
        }

        
        return  ((ArcoPrime) a).get_inicioPrime().equals(this.fst) &&
                ((ArcoPrime) a).get_finalPrime().equals(this.snd); 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode(){ 
        return (int) Objects.hash(this.fst,this.snd);
    }
}