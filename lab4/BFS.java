import java.util.*; 
public class BFS{
	
	public String bfs(Grafo g){
		ArrayList<ArrayList<Integer>> cadenas = new ArrayList<ArrayList<Integer>>(1);
		HashSet<Integer> vertices = g.get_vertices();
		Iterator<Integer> v = vertices.iterator();
		String res="";

		while(v.hasNext()){
			ArrayList<Integer> raiz = new ArrayList<Integer>();
			raiz.add(v.next());
			cadenas.add(raiz);
			res = bfs(g,cadenas);
			if (res!="No hay respuesta :("){
				return res.substring(0,res.lastIndexOf("-")-1);
			}
			cadenas.remove(raiz);
		}

		return res;

	}

	private String bfs(Grafo g,ArrayList<ArrayList<Integer>> cadenas){
		Iterator<ArrayList<Integer>> recorrido_cadena = cadenas.iterator();
		ArrayList<ArrayList<Integer>> siguientes_cadenas  = new ArrayList<ArrayList<Integer>>(cadenas);
		int numero_cadenas_inicio = cadenas.size();
		ArrayList<Integer> tamanos_inicio = this.get_Csize(cadenas);
		int numero_cadenas_fin;
		ArrayList<Integer> tamanos_fin;
		ArrayList<Integer> cadena_actual;
		int last,longitud_cadena,vecino_actual,posible_res;
		HashSet<Integer> vecinos;

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
		numero_cadenas_fin = siguientes_cadenas.size();
		tamanos_fin =  this.get_Csize(siguientes_cadenas);
		posible_res = tamanos_fin.indexOf(g.get_vertices().size());

		
		if (posible_res!= -1){
			String respuesta="";
			
			ArrayList<Integer> k = siguientes_cadenas.get(posible_res);
			for (int j=0;j<g.get_dim().get(0);j++){
				respuesta = respuesta + Integer.toString(k.get(j)) + " - ";
			}
			return respuesta;
			
			//return Arrays.toString(cadenas.get(posible_res) );
		}

		if(siguientes_cadenas.equals(cadenas)){
			return "No hay respuesta :(";
		}

		String respuesta = this.bfs(g,siguientes_cadenas);
		return respuesta;
	}


	private ArrayList<Integer> get_Csize(ArrayList<ArrayList<Integer>> cadena){
		ArrayList<Integer> tamanos = new ArrayList<Integer>(cadena.size());
		Iterator<ArrayList<Integer>> i = cadena.iterator();

		while(i.hasNext()){
			tamanos.add(i.next().size());
		}
		
		return tamanos;


	}
}