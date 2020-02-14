import java.util.*; 
/**<p>Clase que resuelve el problema de Federico.</p>
 */
public class SimCity extends Grafo {


	SimCity(){
		super();
	}


	/** Funcion que se llamara desde afuera para solventar el problema.
	 **/
	public String reduceCity(){

		HashSet<Integer> vertices = super.get_vertices();
		Iterator<Integer> v = vertices.iterator();
		System.out.println("El grafo es: "+super.grafo);
		// Por cada vertice que tenga el grafo.
		while(v.hasNext()){
			int raiz = v.next();
			// saca los ciclos
			if (super.get_vertices().contains(raiz)){
				ArrayList<Integer> cycle = this.get_cycle(raiz);
				// y reduce.
				while(!cycle.isEmpty()){
					System.out.println("Ciclo es: "+cycle);
					this.UnionCities(cycle);
					System.out.println("El grafo despues de eliminar el ciclo es: " +super.grafo);
					cycle = this.get_cycle(raiz);
				}
			}
		}

		// Y una vez terminado, retorna el numero de vertices que tengas.
		System.out.println("Grafo al finalizar: "+super.grafo);
		return String.valueOf(super.get_vertices().size());
	}


	/** Dado un nodo, obtiene un ciclo elemental.
	 * 
	 * @param root vertice al cual obtener el ciclo.
	 */

	private ArrayList<Integer>  get_cycle(int root) {

		ArrayList<Integer> respuesta = new ArrayList<Integer>();
		ArrayList<Integer> cycle= new ArrayList<Integer>();
		HashSet<Integer> visitados = new HashSet<Integer>();
		cycle.add(root);
		visitados.add(root);
		respuesta = this.dfs(visitados,cycle);
		return respuesta;
	}

	/** El verdadero heroe, quien consigue el ciclo.
	 * 
	 * @param visitados vertices visitados.
	 * @param cadena   cadena a la cual insertamos los vertices hasta encontrar un ciclo.
	 */

	private ArrayList<Integer> dfs(HashSet<Integer> visitados, ArrayList<Integer> cadena){

		// Iterador sobre los vecinos del ultimo elemento de la cadena.
		Iterator<Integer> explorar = super.get_neighbours(cadena.get(cadena.size()-1)).iterator();
		
		// Mientras el ultimo elemento de la cadena tenga vecinos:
		while(explorar.hasNext()){
			int vecino = explorar.next();

			// El elemento vecino que voy a anadir cierra el ciclo?
			if (cadena.get(0) == vecino){
				// Si es asi, cierro el ciclo, y lo retorno.
				cadena.add(vecino);
				return cadena;
			}

			// Sino, solo consideramos el ciclo elemental mas grande.
			if (!(visitados.contains(vecino))){
				cadena.add(vecino);
				visitados.add(vecino);
				// Recursion pabita.
				ArrayList<Integer> respuesta = dfs(visitados,cadena);
				// si encontramos el ciclo, lo retornamos.
				if (cadena.get(0) == cadena.get(cadena.size()-1)){
					return respuesta;
				}
				cadena.remove(new Integer (vecino));
				visitados.remove(new Integer (vecino));
				respuesta.clear();	
			}
			
		}

		return new ArrayList<Integer>();
	}


	/**Y Para unir ciudades, basta con cambiar todas las ocurrencias de los vertices en la lista de arcos
	 * por el id que se va a unir
	 * 
	 * @param cycle Ciclo que contiene los edificos a combinar.
	 */
	private void UnionCities(ArrayList<Integer> cycle){
		int target = cycle.get(0);
		for (int i=0;i<cycle.size();i++){
			cycle.remove(new Integer (target) );
		}
		// cambiamos todos los vertices de llegada del ciclo por la megaEstructura que los algomera.
		super.grafo.forEach((ArrayList<Integer> arc) -> {if ( cycle.contains(arc.get(1)) ) arc.set(1,target);} );
		// cambiamos todos los vertices de salida del ciclo por la megaEstructura que los aglomera.
		super.grafo.forEach((ArrayList<Integer> arc) -> {if ( cycle.contains(arc.get(0)) ) arc.set(0,target);} );
		// eliminamos los bucles generados por las dos operaciones.
		super.grafo.removeIf((ArrayList<Integer> arc) -> (arc.get(0)==arc.get(1)) );
		// actualizamos las dimensiones del grafo.
		super.dim.set(0,super.dim.get(0)-cycle.size());
		super.dim.set(1,super.grafo.size());
		
	}

} 