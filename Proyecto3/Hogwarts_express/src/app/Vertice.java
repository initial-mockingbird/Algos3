package app;

/** Integrantes:
*   Daniel Pinto
*   Daniela Ramirez
*/

public class Vertice {

	/**Atributos de la clase Vertice:
    * id: Identificador del vertice
    * Nombre: Nombre de el vertice
    * p: Peso de el Vertice
    * Coord_x: Coordinada que indica dénde se ubica el vértice para propósitos de representaciones gráficas
    * Coord_y: Coordinada que indica dénde se ubica el vértice para propósitos de representaciones gráficas
    */

    int id;
    String Nombre;
    Double Coord_x;
    Double Coord_y;                      
    Double p;                  

    /*Constructor de la clase Vertice*/

    Vertice(int identificador, String nombre, Double x, Double y, Double peso){
        this.id = identificador;
        this.Nombre = nombre;                                                                         
        this.p = peso;
        this.Coord_x = x;
        this.Coord_y = y;  
    }
    Vertice(String v){
        String[] datos = v.split("\\s+");
        this.id = Integer.valueOf(datos[0]);
        this.Nombre = datos[1];                                                                         
        this.p = Double.valueOf(datos[4]);
        this.Coord_x = Double.valueOf(datos[2]);
        this.Coord_y = Double.valueOf(datos[3]); 
    }

    public Vertice crearVertice(int identificador, String nombre, Double x, Double y, Double peso){
        return new Vertice(identificador,nombre,x,y,peso); 
    }

    /**
     * Retorna el peso del vertice.
     * @return p (double): peso del vertice.
     */
    public Double getPeso(){
        return this.p;
    }

    /**
     * Retorna el Identificador del Vertice
     * @return id (int): identificador del vertice.
     */
    public int getId(){
        return this.id;
    }


    /**
     * Retorna el nombre del Vertice
     * @return Nombre (String): nombre del vertice.
     */
    public String getNombre(){
        return this.Nombre;
    }


    /**
     * Retorna la coordenada x del Vértice
     * @return Coord_x (double) coordenada x del vertice.
     */
    public Double getX(){
    	return this.Coord_x;
    }


    /**
     * Retorna la coordenada y del Vértice
     * @return Coord_y (double) coordenada y del vertice.
     */
    public Double getY(){
    	return this.Coord_y;
    }

    /**
     * Retorna un String con la informacion del Vertice
     * @return Result (String): representacion de tipo String del vertice.
     */
    public String toString(){
        return  ("Vertice "+this.id+":  Nombre: "+this.Nombre+"  Peso: "+String.valueOf(this.p)+
        "Coordenada x: "+this.Coord_x+" Coordenada y: "+this.Coord_y);
    }

    /**
     * Compara si dos vertices son iguales por su id.
     * @param v (Vertice): vertice a comparar.
     * @return True|False si son Iguales|Distintos.
     */
    @Override
    public boolean equals(Object v){
        if (v.getClass() == this.getClass()){ 
            return (this.id == ((Vertice) v).getId());
        }
        return false;
    }

    /**
     * Al hacer override al metodo equals debemos hacerlo tambien al metodo hashCode.
     * @return id (int): hashCode del elemento vertice.
     */
    @Override
    public int hashCode(){
        return this.id;
    }
}