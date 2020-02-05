import java.util.*; 
public class DFSV2{
	
	public String dfs(Grafo g){
		ArrayList<ArrayList<Integer>> cadenas = new ArrayList<ArrayList<Integer>>(1);
		HashSet<Integer> vertices = g.get_vertices();
		Iterator<Integer> v = vertices.iterator();
		String res="";

		while(v.hasNext()){
			int raiz = v.next();
			HashSet<Integer> visitados = new HashSet<Integer>();
			ArrayList<Integer> cadena = new ArrayList<Integer>();
			cadena.add(raiz);
			visitados.add(raiz);
			res = dfs(g,visitados,cadena);
			if (res!="No se encontro caminos hamiltonianos :("){
				return res;
			}
			visitados.remove(new Integer (raiz));
			cadena.remove(new Integer (raiz));
		}

		return res;

	}

	private String dfs(Grafo g,HashSet<Integer> visitados, ArrayList<Integer> cadena){

		String respuesta = "No se encontro caminos hamiltonianos :(";

		if (cadena.size()==g.get_dim().get(0)){
			return cadena.toString();
		}

		ArrayList<Integer> cadena_nivel = new ArrayList<Integer>(cadena);
		Iterator<Integer> explorar = g.get_Neighbours(cadena.get(cadena.size()-1)).iterator();

		while(explorar.hasNext()){
			int vecino = explorar.next();
			if (!(visitados.contains(vecino))){
				cadena_nivel.add(vecino);
				visitados.add(vecino);
				respuesta = dfs(g,visitados,cadena_nivel);

				if (respuesta != "No se encontro caminos hamiltonianos :("){
					return respuesta;
				}
				cadena_nivel.remove(new Integer (vecino));
				visitados.remove(new Integer (vecino));	
			}
			
		}

		return respuesta;


	}

}