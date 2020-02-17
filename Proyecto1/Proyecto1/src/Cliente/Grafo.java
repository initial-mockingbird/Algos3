package Cliente;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/** interface Grafo la cual provee los metodos base que deben tener 
 *  todas las representaciones de grafos.
 */
public interface Grafo {
    /** cargarGrafo se encarga de traducir un archivo a un tipo de representacion.
     * 
     * @param archivo
     * @return True|False para indicar exito|fracaso en la creacion.
     */
    public Boolean cargarGrafo(String archivo);
    /** numeroDeVertices retorna la cantidad de vertices que posee el grafo.
     * 
     * @return numero de vertices del grafo.
     */
    public int numeroDeVertices();
    /** numeroDeLados retorna la cantidad de lados que posee el grafo.
     * 
     * @return cantidad de lados del grafo.
     */
    public int numeroDeLados();
    /** agregarVertice carga un Vertice v en el grafo (si este no pertenece
     *  al conjunto de vertices) de otra forma no modifica el grafo.
     * 
     * @param v vertice a Cargar
     * @return True|False si se inserto|no se inserto el Vertice.
     */
    public Boolean agregarVertice(Vertice v);
    /** 0btenerVertice obtiene un Vertice del grafo dado su identificador, suelta 
     *  NoSuchElement Exception si el id no se encuentra.
     * 
     * @param id identificador del vertice.
     * @return Vertice el cual fue representado por el identificador.
     * @throws NoSuchElementException
     */
    public Vertice obtenerVertice(int id) throws NoSuchElementException;
    /** estaVertice dice si un vertice (representado por su identificaodr)
     * esta en el grafo.
     * @param id identificador del vertice.
     * @return True|False si esta|no esta.
     */
    public Boolean estaVertice( int id);
    /** eliminarVertice elimina un Vertice (representado por su identificador)
     * si el vertice no pertenece al conjunto de vertices del grafo, entonces
     * no se modifica la estrucutra.
     * @param id identificador del vertice.
     * @return True|False si se modifico| no se modifico.
     */
    public Boolean eliminarVertice( int id);
    /** vertices retorna una lista con los vertices del grafo
     * 
     * @return lista de vertices del grafo.
     */
    public LinkedList<Vertice> vertices();
    /** lados retorna una lista con los lados del grafo.
     * 
     * @return lista con los lados del grafo.
     */
    public LinkedList<Lado> lados();
    /** grado calcula el grado de un vertice (representado por su identificador), 
     *  si el vertice no pertenece al grafo, suelta una excepcion.
     * @param id identificador del vertice.
     * @return grado del vertice referido por el identificador.
     * @throws NoSuchElementException
     */
    public int grado(int id) throws NoSuchElementException;
    /** adyacentes devuelve una lista con los vertices adyacentes a un
     *  vertice referido por su identificador. Devuelve una excepcion
     *  si el vertice referido por la excepcion no se encuentra.
     * 
     * @param id identificador del vertice
     * @return lista de vertices adyacentes
     * @throws NoSuchElementException
     */
    public LinkedList<Vertice> adyacentes(int id) throws NoSuchElementException;
    /** incidentes devuelve una lista con todos los lados que inciden sobre
     *  un vertice referido por su identificador. Suelta una excepcion si no hay 
     *  ningun vertice asociado/referido con el identificador.
     * @param id identificador del vertice
     * @return lista con los lados incidentes.
     * @throws NoSuchElementException
     */
    public LinkedList<Lado> incidentes(int id) throws NoSuchElementException;
    /** Devuelve una copia de la estrucutra.
     * 
     * @return shallow copy de la estrucutra.
     */
    public Grafo clone ();
    /** Devuelve una representacion de tipo string de la estructura.
     * 
     * @return representacion de tipo string de la estructura.
     */
    public String toString();
}