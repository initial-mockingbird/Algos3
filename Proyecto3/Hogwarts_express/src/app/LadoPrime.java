package app;



public class LadoPrime {
    protected VerticePrime fst;
    protected VerticePrime snd;
    protected double costo;
    
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


}