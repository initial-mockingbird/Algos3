import java.util.*; 
/**Almacena un grafo que puede crecer din&aacute;micamente para prop&oacute;sitos
 * de traduci&oacute;n a Matriz de Adyacencias. Esta clase est&aacute; pensada para ser
 * usada al leer grafos en formato Lista de Adyacencias desde un archivo.
 */
public class TraductorDesdeLista extends TraductorGrafo{
	
	//ToDo: Debe colocar aqu&iacute; estructuras de java.util.collections apropiadas
	
	private ArrayList <ArrayList <Integer>> auxGraph;
	private int auxLength; 

	/**Crea un grafo minimal*/
	TraductorDesdeLista(){
		auxGraph = new ArrayList<ArrayList<Integer> >(0);
		auxLength = 0;
	}
	
	/**Agrega un v&eacute;rtice al grafo. Si el v&eacute;rtice ya existe, el m&eacute;todo no hace
	 * nada.
	 * 
	 * @param id El n&uacute;mero del v&eacute;rtice que se desea agregar
	 */
	public void agregarVertice(int id){
		ArrayList<Integer> vertex = new ArrayList<Integer>();
		/*Binary Search on the list.*/
		int low =0;
		int high = auxLength;
		int mid = (low+high)/2;
		while(low != mid){
			
			if (this.auxGraph.get(mid).get(0)>id){
				high = mid;
			} else if (this.auxGraph.get(mid).get(0)<id){
				low = mid;
			} else {
				return;
			}
			mid = (low+high)/2;			
		}
		/*Then we choose where to put the element.*/
		vertex.add(id);
		if (auxLength==0){
			this.auxGraph.add(vertex);
			this.auxLength += 1;
			return;
		}
		if (this.auxGraph.get(mid).get(0)>id){
			this.auxGraph.add(low,vertex);
		} else {
			this.auxGraph.add(high,vertex);
		}
		this.auxLength += 1;
		return;		
	}
	
	/**{@inheritDoc}**/
	public void agregarArco(int verticeInicial, int verticeFinal){
		/*Binary Search para encontrar el nodo*/
		int low =0;
		int high = auxLength;
		int mid = (low+high)/2;
		while(low != mid){			
			if (this.auxGraph.get(mid).get(0)>verticeInicial){
				high = mid;
			} else if (this.auxGraph.get(mid).get(0)<verticeInicial){
				low = mid;
			} else {
				break;
			}
			mid = (low+high)/2;			
		}
		int vertexIndex = mid;
		/*Binary Search para encontrar la posicion a insertar*/
		low =1;
		high = this.auxGraph.get(vertexIndex).size();
		mid = (low+high)/2;
		while(low != mid){	
			if (this.auxGraph.get(vertexIndex).get(mid)>verticeFinal){
				high = mid;
			} else if (this.auxGraph.get(vertexIndex).get(mid)<verticeFinal){
				low = mid;
			} else {
				return;
			}
			mid = (low+high)/2;			
		}
		/*Then we decide on where to put the node.*/
		if (this.auxGraph.get(vertexIndex).size() == 1 ){
			this.auxGraph.get(vertexIndex).add(verticeFinal);
		} else if (this.auxGraph.get(vertexIndex).get(mid)>verticeFinal){
			this.auxGraph.get(vertexIndex).add(low,verticeFinal);
		} else {
			this.auxGraph.get(vertexIndex).add(high,verticeFinal);
		}
		return;		

	}
	/** Adds and array of arcs to the graph. If the arc already exists the it does nothing. 
	 * 
	 * @param verticeInicial The first vertex that is an incident in the arc.
	 * @param verticeFinales The set of vertices that are endpoints of the arc.

	*/
	public void agregarArco(int verticeInicial, int[]verticesFinales){
		for (int i=0;i<verticesFinales.length;i++){
			agregarArco(verticeInicial,verticesFinales[i]);
		}
	}

	/**{@inheritDoc}**/
	public String imprimirGrafoTraducido(){
		int rows = auxLength+2;
		int cols = 2*auxLength+2+1;
		String result = "";
		String [][] matrixRepresentation = new String[rows][cols];
		for (int i=0;i<rows;i++){
			for (int j=0;j<cols;j++){
				if( (i==0) && (j<3) ){
					matrixRepresentation[i][j] = " ";
				} else if (j==(cols-1)) {
					matrixRepresentation[i][j] = "\n";
				} else if (i==1){
					matrixRepresentation[i][j] = "-";
				} else if ((j!=0) && ((j%2)==0)) {
					matrixRepresentation[i][j] = " ";
				} else if ((i==0) && (j!=0)) {
					matrixRepresentation[i][j] = Integer.toString(this.auxGraph.get(j/2 -1).get(0));
				} else if (j==0) {
					matrixRepresentation[i][j] = Integer.toString(this.auxGraph.get(i-2).get(0));
				} else if ((i>1) && (j==1)){
					matrixRepresentation[i][j] = "|";
				}  else if (this.isFound(matrixRepresentation[i][0],matrixRepresentation[0][j])) {
					matrixRepresentation[i][j] = "1";
				} else {
					matrixRepresentation[i][j] = "0";
				}
			}
		}
		for (int i=0;i<rows;i++){
			for (int j=0;j<cols;j++){
				result = result+matrixRepresentation[i][j];
			}
		}
		return result;
	}

	/** In order to check if a pair is found, it's enough to do 
	* 	a binary search on both the vertices and the edges. 
	**/
	private Boolean isFound(String verticeInicial, String verticeFinal){
		int inicio = Integer.parseInt(verticeInicial);
		int fin = Integer.parseInt(verticeFinal);

		/*Binary Search para encontrar el nodo*/
		int low =0;
		int high = auxLength;
		int mid = (low+high)/2;
		while(low != mid){			
			if (this.auxGraph.get(mid).get(0)>inicio){
				high = mid;
			} else if (this.auxGraph.get(mid).get(0)<inicio){
				low = mid;
			} else {
				break;
			}
			mid = (low+high)/2;			
		}

		int vertexIndex = mid;
		if (this.auxGraph.get(vertexIndex).get(0) != inicio){
			return false;
		}
		/*Binary Search para encontrar el nodo final*/
		low =1;
		high = this.auxGraph.get(vertexIndex).size();
		mid = (low+high)/2;
		while(low != mid){	
			if (this.auxGraph.get(vertexIndex).get(mid)>fin){
				high = mid;
			} else if (this.auxGraph.get(vertexIndex).get(mid)<fin){
				low = mid;
			} else {
				return true;
			}
			mid = (low+high)/2;			
		}
		if (this.auxGraph.get(vertexIndex).get(mid)==fin){
			return true;
		}
		return false;	
	}

	/** {@inheritDoc} 
	* To verify if a graph is directed or not given its
	* adjacency list, it's enough to use the aux function 
	* is found to check the converse of each pair.
	**/
	public String esDirigido()
	{
		Boolean isDirected = true;
		String inicio;
		String fin;
		for (int i=0;i<this.auxLength;i++){
			for (int j=1;j<this.auxGraph.get(i).size();j++){
				inicio = String.valueOf(this.auxGraph.get(i).get(0));
				fin = String.valueOf(this.auxGraph.get(i).get(j));
				if(!isFound(fin,inicio)){
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
}