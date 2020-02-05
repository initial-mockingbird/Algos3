import java.util.*; 
public class BFS{
	
	public void bfs(Grafo g){
		ArrayList<ArrayList<Integer>> cadenas = new ArrayList<ArrayList<Integer>>(1);
		HashSet<Integer> vertices = g.get_vertices();
		Iterable<Integer> v = vertices.iterable();

		while(v.hasNext()){
			cadenas.get(0).add(v.next());
			bfs(g,cadenas);
		}

	}

	private void bfs(Grafo g,ArrayList<ArrayList<Integer>> cadenas){
		
	}
}