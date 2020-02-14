/**Almacenador de grafos para prop&oacute;sitos de traducci&oacute;n.
 * Esta es una clase padre para traductores de grafos.
 */
public abstract class TraductorGrafo{
	/**Almacena el grafo como una matriz de adyacencias
	 */
	protected int[][] grafo;
	
	/**Agrega un arco entre los v&eacute;rtices dados. Si el arco ya existe, el
	 * m&eacute;todo no hace nada.
	 * 
	 * @param verticeInicial V&eacute;rtice del cual sale el arco
	 * @param verticeFinal   V&eacute;rice al cual llega el arco
	 */
	public abstract void agregarArco(int verticeInicial, int verticeFinal);

	/**Imprime el grafo en el formato de destino.
	 * @return un <code>String</code> mostrando el grafo en la representaci&oacute;n
	 *         deseada
	 */
	public abstract String imprimirGrafoTraducido();


	/** Dice si el grafo es dirigido o no.
	* @return Un mensaje diciendo si es dirigido o no. 
	**/
	public abstract String esDirigido();
}