import java.util.*; 
/**Almacena un grafo que puede crecer din&aacute;micamente para prop&oacute;sitos
 * de traduci&oacute;n a Matriz de Adyacencias. Esta clase est&aacute; pensada para ser
 * usada al leer grafos en formato Lista de Adyacencias desde un archivo.
 */
public class TraductorDesdeMatriz extends TraductorGrafo{
	
	//ToDo: Debe colocar aqu&iacute; estructuras de java.util.collections apropiadas
	
	/**Crea un grafo con el n&uacute;mero de v&eacute;rtices dado
	 * 
	 * @param graph El grafo como matriz de adyacencias
	 * @param orden Arreglo que conserva el orden en el que deberian imprimirse los vertices
	 * @param tamano El n&uacute;mero de v&eacute;rtices del grafo
	 * @param result Representacion como string de la lista de adyacencias.
	 */

	private int [][] graph;
	private int [] orden;
	private int tamano;
	private String result="";

	/**Crea un grafo minimal*/
	TraductorDesdeMatriz(int vertices){
		graph = new int[vertices][vertices];
		tamano = vertices;
		orden = new int[vertices];
	}

	/**Crea la estructura que guardara el orden de los nodos.*/
	public void setOrden(int[] header){
		for (int i=0;i<tamano;i++){
			this.orden[i]=header[i];
		}
	}
	
	/**{@inheritDoc}
	* Ahora Posee una lista de vertices por conveniencia.
	**/
	public void agregarArco(int verticeInicial, int verticeFinal){
		int [] vFinal = {verticeFinal};
		agregarArco(verticeInicial,vFinal);
	}
	public void agregarArco(int verticeInicial, int[] verticeFinal){
		int i = verticeInicial;
		int[] aux = verticeFinal;
		if (i > tamano){
			throw new IllegalArgumentException("Hay mas lineas que lo estipulado :(");
		}
		for (int j=0;j<tamano;j++){
			this.graph[i][this.orden[j]]=aux[j];
		}
	}

	/** {@inheritDoc} 
	*To verify if a graph is directed or not given its
	* adjacency matrix, it's enough to verify that the
	* matrix A is equal to it's transpose.
	**/
	public String esDirigido(){

		Boolean isDirected = true;
		for (int i=0;i<this.tamano;i++){
			for (int j=0;j<this.tamano;j++){
				if ( (this.graph[i][j] != this.graph[j][i] ) ){
					isDirected = false;
					break;
				}
			}
		}
		if (isDirected == false){
			return "El grafo no representa uno de tipo no dirigido :(";
		} else {
			return "El grafo representa uno dirigido :D";
		}

	}
	
	/**{@inheritDoc}**/
	public String imprimirGrafoTraducido(){
		for (int i=0;i<tamano;i++){
			this.result = this.result + Integer.toString(i)+":";
			for (int j=0;j<tamano;j++){
				if (this.graph[i][j]==1) {
					this.result = result+ " "+Integer.toString(j);
				}
			}
			result += "\n";
		}
		return result;
	}
}