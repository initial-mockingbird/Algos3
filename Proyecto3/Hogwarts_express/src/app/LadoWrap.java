package app;

public class LadoWrap extends LadoPrime{
    private AristaPrime arista;
    private ArcoPrime arco;
    private boolean isDirigido;
    private double costo;

    /**
     * Nota, usamos esta clase como wrap para poder trabajar con tranquilidad sobre
     * Cualquier grafo, sin embargo, una mejor estructura para trabajar este tipo
     * de casos es el tipo de dato (monadico): Either a b = Left a || Right b. Sin embargo
     * debido a las limitaciones que posee java para trabajar de manera funcional
     * decidimos tomar esta ruta
     * @param lado
     * @param isDirigido
     */
    LadoWrap(LadoPrime lado, boolean isDirigido){
        if (isDirigido){
            this.arco = (ArcoPrime) lado;
        }
        else {
            this.arista = (AristaPrime) lado;
        }
        this.isDirigido = isDirigido;
    }

    /**
     * Dice si contiene informacion de un grafo dirigido.
     * @return isDirigido (True/false).
     */
   public boolean esDirigido(){
       return this.isDirigido;
   }

   /**
    * Obtiene el lado asociado a la estructura.
    * @return Arco/Arista dependiendo si el grafo es dirigido o no.
    */
   public LadoPrime get_lado(){
       if (this.isDirigido){
           return this.arco;
       }
       else {
           return this.arista;
       }
   }
    /**
     * {@inheritDoc}
     */
    @Override
    public VerticePrime get_inicioPrime(){
        if (this.isDirigido){
            return this.arco.get_inicioPrime();
        } 
        else {
            return this.arista.get_inicioPrime();
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public VerticePrime get_finalPrime(){
        if (this.isDirigido){
            return this.arco.get_finalPrime();
        } 
        else {
            return this.arista.get_finalPrime();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vertice get_inicio(){
        if (this.isDirigido){
            return this.arco.get_inicio();
        } 
        else {
            return this.arista.get_inicio();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Vertice get_final(){
        if (this.isDirigido){
            return this.arco.get_final();
        } 
        else {
            return this.arista.get_final();
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public double get_costo(){
        if (this.isDirigido){
            return this.arco.get_costo();
        } 
        else {
            return this.arista.get_costo();
        }
    }

    public void set_costo(double costo){
        if (this.isDirigido){
            this.arco.costo = costo;
        } 
        else {
            this.arista.costo = costo;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object a)
    {
        if (!(a.getClass() == super.getClass())){
            return false;
        }

        return this.get_lado().equals(((LadoWrap)a).get_lado());
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode(){ 
        return this.get_lado().hashCode();
    }

    public double get_costo(VerticePrime v, VerticePrime w){
        if (this.isDirigido){
            if( this.arco.equals(new ArcoPrime(v, w, (double) 0) )){
                return this.arco.get_costo();
            } else {
                return (double) 0;
            }
        } else {
            if( this.arista.equals(new AristaPrime(v, w, (double) 0) )){
                return this.arista.get_costo();
            } else {
                return (double) 0;
            }
        }
    }


}