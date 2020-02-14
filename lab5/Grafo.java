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
	protected HashSet<ArrayList<Integer>> grafo;
	protected ArrayList<Integer> dim; 

	/**Crea un grafo minimal*/
	Grafo(){
		this.grafo = new HashSet<ArrayList<Integer>>();
		this.dim = new ArrayList<Integer>();
	}
	
	/**	Agrega el numero de vertices/aristas a dim.
	*
	*	@param ve El numero de vertices/aristas que posee el grafo.
	*/
	public void addDim(int ve)
			throws IllegalArgumentException
	{
		if (this.dim.size()>2){
			throw new IllegalArgumentException("No podemos almacenar mas que el numero de vertices y aristas :(");
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
	public void agregarArco(int verticeInicial, int verticeFinal)
			throws IllegalArgumentException
	{
		ArrayList<Integer> arco = new ArrayList<Integer>();
		arco.add(verticeInicial);
		arco.add(verticeFinal);
		this.grafo.add(arco);

	}

	/**Retorna el grafo como Hashset 
	*
	**/

	public HashSet<ArrayList<Integer>> get_arcs(){
		return this.grafo;
	}

	/** Retorna dim (Array_List que contiene el numero de vertices y el numero de aristas).
	*
	**/
	public ArrayList<Integer> get_dim(){
		return this.dim;
	}

	/** Retorna un conjunto con los vertices del grafo.
	*
	**/

	public HashSet<Integer> get_vertices(){
		HashSet<Integer> vertices = new HashSet<Integer>();
		Iterator<ArrayList<Integer>> middle = this.grafo.iterator();

		while(middle.hasNext()){
			vertices.addAll(middle.next());
		}
		return vertices;
	}

	/** Retorna el grafo como una lista de arcos.
	*
	**/
	public ArrayList<ArrayList<Integer>> lista_arcos(){
		ArrayList<ArrayList<Integer>> end = new ArrayList<ArrayList<Integer>>();
		Iterator<ArrayList<Integer>> i = this.grafo.iterator();
		while(i.hasNext()){
			end.add(new ArrayList<Integer>(i.next()));
		}

		return end;
	}

	/** Retorna el grafo como una lista de adyacencias.
	*
	**/
	public ArrayList<ArrayList<Integer>> lista_adyacencias(){
		ArrayList<ArrayList<Integer>> end = new ArrayList<ArrayList<Integer>>();
		Iterator<Integer> j = this.get_vertices().iterator();
		ArrayList<ArrayList<Integer>> list_arcos = this.lista_arcos();

		int p = 0;
			while(j.hasNext()){
				end.add(new ArrayList<Integer>());
				end.get(p).add(j.next());
				p++;
			}
		for (int i = 0;i<this.get_vertices().size();i++){
			for (int k = 0; k<this.grafo.size();k++){
				if (list_arcos.get(k).get(0)==end.get(i).get(0)){
					end.get(i).add(list_arcos.get(k).get(1));
				}
			}
		}

		return end;
	}

	/** Obtiene las adyacencias de un vertice v.
	*
	*	@param v Vertice al cual obtendremos sus adyacencias.
	*
	**/
	public HashSet<Integer> get_neighbours(int v){
		HashSet<Integer> neighbours = new HashSet<Integer>();
		ArrayList<ArrayList<Integer>> list_adyacencias = this.lista_adyacencias();
		if (!this.get_vertices().contains(v)){
			return new HashSet<Integer>();
		}
		int k=0;
		for (int i=0;i<this.dim.get(0);i++){
			if (list_adyacencias.get(i).get(0) == v){
				k = i;
				break;
			}
		}

		neighbours.addAll(list_adyacencias.get(k).subList(1,list_adyacencias.get(k).size()));
		return neighbours;
	}

}