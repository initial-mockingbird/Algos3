import java.util.*
/** Clase que contiene el codigo para recorrer el grafo, y almacena el camino encontrado.
**/
public class DFS {

	public void performDFS(Grafo g){
		HashSet<<Integer> visitados =  new HashSet<Integer>();
		Iterator<HashSet<Integer>> vertices = g.get_vertices().iterator();
		while(vertices.hasNext()){
			visitados.add(vertices.next());
			performDFS(g,visitados);
			visitados.clear()
		}
	}

	private void performDFS(Grafo g, HashSet<Integer> visitados){

	}

}