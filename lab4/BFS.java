import java.util.*; 
/**<p>Clase que busca caminos Hamiltonianos usando BFS.</p>
 */
public class BFS{
	
	/** Dado un grafo, usando BFS encuentra caminos hamiltonianos si hay alguno.
	*
	*	@param g Grafo sobre el cual se aplicara bfs para encontrar el camino hamiltoniano.
	*
	**/

	public String bfs(Grafo g){
		ArrayList<ArrayList<Integer>> cadenas = new ArrayList<ArrayList<Integer>>(1);
		Iterator<Integer> vertices = g.get_vertices().iterator();
		String respuesta="";

		while(vertices.hasNext()){
			ArrayList<Integer> raiz = new ArrayList<Integer>();
			raiz.add(vertices.next());
			cadenas.add(raiz);
			respuesta = bfs(g,cadenas);
			if (respuesta!="No hay respuesta :("){
				return respuesta;
			}
			cadenas.remove(raiz);
		}
		return respuesta;
	}

	/** El procedimiento BFS de por si.
	*
	*	@param g Grafo sobre el cual se hara bfs/
	*	@param cadenas lista de todas las cadenas de largo a lo sumo <code>i</code>
	*	que se desprenden del nodo raiz (el primer elemento de todas las cadenas).
	*	y en donde <code>i</code> es el nivel actual del bfs.
	*
	**/
	private String bfs(Grafo g,ArrayList<ArrayList<Integer>> cadenas){
		Iterator<ArrayList<Integer>> recorrido_cadena = cadenas.iterator();
		ArrayList<ArrayList<Integer>> siguientes_cadenas  = new ArrayList<ArrayList<Integer>>(cadenas);
		ArrayList<Integer> tamanos_fin;
		ArrayList<Integer> cadena_actual;
		HashSet<Integer> vecinos;
		int last,longitud_cadena,vecino_actual,posible_res;

		// primero buscamos el ultimo elemento de cada cadena:
		while(recorrido_cadena.hasNext()){

			cadena_actual = recorrido_cadena.next();

			longitud_cadena = cadena_actual.size();
			last =cadena_actual.get(longitud_cadena-1);

			// Una vez tengamos el ultimo elemento, vemos sus vecinos:
			vecinos = g.get_Neighbours(last);
			Iterator<Integer> check_Vecinos = vecinos.iterator();

			while(check_Vecinos.hasNext()){
				vecino_actual = check_Vecinos.next();

				// si el elemento no esta en la cadena, lo metemos en nuestra lista de cadenas
				if (cadena_actual.indexOf(vecino_actual) == -1){
					ArrayList<Integer> nueva_cadena = new ArrayList<Integer>(cadena_actual);
					nueva_cadena.add(vecino_actual);
					siguientes_cadenas.add(new ArrayList<Integer>(nueva_cadena));
					siguientes_cadenas.remove(cadena_actual);
				}
			}
			// En este punto ya agregamos todos los adyacentes elegibles para una cadena
		}

		// Para ver si un camino es hamiltoniano, como estamos construyendo puros caminos
		// elementales (puesto que siempre agregamos un adyacente que no se repita en la cadena).
		// basta con ver que la longitud de la cadena sea igual a la cardinalidad de los vertices.

		// Sacamos la longitud a cada uno de los caminos.
		tamanos_fin =  this.get_Csize(siguientes_cadenas);
		// Vemos si alguna longitud es n.
		posible_res = tamanos_fin.indexOf(g.get_vertices().size());

		if (posible_res!= -1){
			ArrayList<Integer> respuesta = siguientes_cadenas.get(posible_res);
			return respuesta.toString();
		}

		if(siguientes_cadenas.equals(cadenas)){
			return "No hay respuesta :(";
		}

		String respuesta = this.bfs(g,siguientes_cadenas);
		return respuesta;
	}


	/** Funcion auxiliar que, dado una lista de caminos, retorna una lista
	*	cuyos elementos son la longitud de cada camino.
	*
	*	@param cadena Lista de caminos a la cual se le sacara la longitud.
	*
	**/
	private ArrayList<Integer> get_Csize(ArrayList<ArrayList<Integer>> cadena){

		// creamos una lista de tamanos.
		ArrayList<Integer> tamanos = new ArrayList<Integer>(cadena.size());
		Iterator<ArrayList<Integer>> i = cadena.iterator();

		// Y la llenamos con los tamanos de cada una de las cadenas.
		while(i.hasNext()){
			tamanos.add(i.next().size());
		}
		
		return tamanos;


	}
}