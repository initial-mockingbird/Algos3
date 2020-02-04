import java.util.*; 
/**<p>Clase que describe el TAD grafo por Lista de arcos.</p>
 */
public class Grafo {
	/** Almacena un grafo en formato Lista de arcos. Consta de:
	*
	*	<code>grafo</code>: Representacion de lista de arcos del grafo.
	*	<code>dim</code>: Una lista que en su primera posicion contiene
	*	el numero de vertices, y en su segunda posicion el numero de aristas.
	*/
	private HashSet<HashSet<Integer>> grafo;
	private ArrayList<Integer> dim; 

	/**Crea un grafo minimal*/
	Grafo(){
		this.grafo = new HashSet<HashSet<Integer>>();
		this.dim = new ArrayList<Integer>();
	}
	
	/**	Agrega el numero de vertices/aristas a dim.
	*
	*	@param ve El numero de vertices/aristas que posee el grafo.
	*/

	public void addDim(int ve){
		if (this.dim.size()>2){
			System.err.println("No podemos almacenar mas que el numero de vertices y aristas :(");
			return;
		}
		this.dim.add(ve);
		return;
	}


	
	/**Agrega un arco entre los v&eacute;rtices dados. Si el arco ya existe, el
	 * m&eacute;todo no hace nada. Mientras que si se da el converso de algun arco,
	 * arroja un error.
	 * 
	 * @param verticeInicial V&eacute;rtice del cual sale el arco
	 * @param verticeFinal   V&eacute;rice al cual llega el arco
	 */
	public void agregarArco(int verticeInicial, int verticeFinal){
		HashSet<Integer> arco = new HashSet<Integer>();
		arco.add(verticeInicial);
		arco.add(verticeFinal);
		if (this.grafo.contains(arco)){
			System.err.println("Una arista aparece dos veces.");
			return;
		}
		this.grafo.add(arco);

	}

	public HashSet<HashSet<Integer>> get_arcs(){
		return this.grafo;
	}

	public ArrayList<Integer> get_dim(){
		return this.dim;
	}

	public HashSet<Integer> get_vertices(){
		HashSet<Integer> vertices = new HashSet<Integer>();
		for(int i=0;i<dim.get(0);i++){
			vertices.add(i);
		}
		return vertices;
	}

	public String printVertices(){
		HashSet<Integer> vertices = get_vertices();
		return Arrays.toString(vertices.toArray());
	}

}