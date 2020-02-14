import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays; 
import java.util.stream.*;
/**<p>Programa para convertir archivos Lista de Adyacencia a archivos Matriz de
 * Adyacencia.</p>
 * 
 * <p>Un archivo Lista de Arista representa un grafo, el cual posee el formato:</p>
 * <blockquote>
 * <code>v<sub>i</sub>: v<sub>1</sub> v<sub>2</sub> &hellip; v<sub>m</sub></code>
 * </blockquote>
 * <p>donde <code>v<sub>i</sub></code> es un n&uacute;mero de un v&eacute;rtice, y 
 * <code>v<sub>1</sub> v<sub>2</sub> &hellip; v<sub>m</sub></code> son los n&uacute;meros
 * de los v&eacute;rtices adyacentes a <code>v<sub>i</sub></code></p>
 * <p>mientras que un archivo Matriz de adyacencia representa un grafo
 * en el formato</p>
 * <blockquote>
 * <code> &nbsp; v<sub>1</sub> v<sub>2</sub> &hellip; v<sub>n</sub><br>
 * v<sub>1</sub>| a<sub>1</sub> a<sub>2</sub> &hellip; a<sub>n</sub><br>
 * v<sub>2</sub>| a<sub>1</sub> a<sub>2</sub> &hellip; a<sub>n</sub><br>
 * &vellip;<br>
 * v<sub>n</sub>| a<sub>1</sub> a<sub>2</sub> &hellip; a<sub>n</sub></code>
 * </blockquote>
 * <p>donde <code>v<sub>1</sub> v<sub>2</sub> &hellip; v<sub>n</sub></code> son
 * los n&uacute;meros que identifican a los v&eacute;rtices, y <code>a<sub>i</sub></code>
 * indica si existe un arco desde el v&eacute;rtice al inicio de esa fila, y el
 * v&eacute;rtice al que corresponde esa columna.</p>
 * <p>El programa {@link #main} lee un archivo, detecta (a trav&eacute;s de
 * {@link #esLista(String)}) el formato del archivo, lo carga (a trav&eacute;s de
 * {@link #cargarGrafo(String)}) en un {@link TraductorGrafo}, y lo imprime
 * en el format contrario. Las funciones se ofrecen a nivel de package.</p>
 */
public class Cliente{
	/**Detecta el tipo de archivo basado en una muestra de una l&iacute;nea tomada del
	 * archivo.
	 * 
	 * @param  linea              La l&iacute;nea de muestra tomada del archivo
	 * @return <code>true</code>  si est&aacute; en el formato de un archivo lista de
	 *                            adyacencias;<br></br>
	 *         <code>false</code> si est&aacute; en el formato de un archivo Matriz de
	 *                            adyacencias.
	 * 
	 * @throws IllegalArgumentException si la l&iacute;nea no tiene ninguno de los dos
	 *                                  formatos
	 */
	static boolean esLista(String linea)
			throws IllegalArgumentException
	{
		return !linea.startsWith(" ");
	}
	
	/**Carga la <code>linea</code> de un archivo Lista de Adyacencias dada
	 * en el <code>Grafo</code> dado.
	 * 
	 * @param linea La l&iacute;nea del archivo que se desea cargar.
	 * @param grafo El grafo en el cual ser&aacute; cargada la l&iacute;nea. Este grafo se
	 *              modifica directamente.
	 * @throws IllegalArgumentException si el formato de la l&iacute;nea no es v&aacute;lido
	 */
	private static void cargarLista(String linea, TraductorDesdeLista grafo)
			throws IllegalArgumentException
	{
		String[] sanity = linea.split(":"); /* Separamos el vertice*/
		Integer vertice = Integer.parseInt(sanity[0]);
		grafo.agregarVertice(vertice);
		String[] aux = sanity[1].split("\\s+"); /* Separamos sus adyacencias*/
		aux = Arrays.copyOfRange(aux,1,aux.length); /*Olvidamos el primer espacio en blanco*/
		Integer[] almostEdges = Stream.of(aux).map(Integer::valueOf).toArray(Integer[]::new); /*aplicamos map a cada elemento para parsearlo a integer*/
		int[] edges = new int[almostEdges.length];
		for(int i=0;i<almostEdges.length;i++){
			edges[i] = almostEdges[i].intValue();
		}
		grafo.agregarArco(vertice,edges);


	}
	
	/**Carga la <code>linea</code> de un archivo Matriz de Adyacencias dada
	 * en el <code>Grafo</code> dado.
	 * 
	 * @param linea La l&iacute;nea del archivo que se desea cargar.
	 * @param grafo El grafo en el cual ser&aacute; cargada la l&iacute;nea. Este grafo se
	 *              modifica directamente.
	 * @throws IllegalArgumentException si el formato de la l&iacute;nea no es v&aacute;lido
	 */
	private static void cargarMatriz(String linea, TraductorDesdeMatriz grafo)
			throws IllegalArgumentException
	{
		/* We classify the matrix in two cases: */

		/*The first one is when we are **not** reading its header. */
		if ((!linea.startsWith(" ")) && (!linea.startsWith("-"))){
				/* In which case, we get the initial vertex and put everything else into a string*/
				String first = linea.substring(0,1);
				Integer vertex = Integer.parseInt(first);
				String[] aux = (linea.substring(4)).split("\\s+"); /*Olvidamos el primer espacio en blanco*/
				Integer[] almostEdges = Stream.of(aux).map(Integer::valueOf).toArray(Integer[]::new); /*aplicamos map a cada elemento para parsearlo a integer*/
				int[] edges = new int[almostEdges.length];
				for(int i=0;i<almostEdges.length;i++){
					edges[i] = almostEdges[i].intValue();
				}
				grafo.agregarArco(vertex,edges); 
			} else if (linea.startsWith(" ")){
				/*When we are in the header, we just get how many vertices we need to construct the matrix.*/
				String aux0 = linea.trim();
				String[] aux = aux0.split("\\s+");
				Integer[] almostEdges = Stream.of(aux).map(Integer::valueOf).toArray(Integer[]::new); /*aplicamos map a cada elemento para parsearlo a integer*/
				int[] edges = new int[almostEdges.length];
				for(int i=0;i<almostEdges.length;i++){
					edges[i] = almostEdges[i].intValue();
				}
				
				grafo.setOrden(edges);
			}
	}
	
	
	/**Detecta el n&uacute;mero de v&eacute;rtices en un archivo Matriz de Adyacencias 
	 * basado en una muestra de una l&iacute;nea tomada del archivo.
	 * 
	 * @param  linea  La l&iacute;nea del archivo que se desea cargar.
	 * @return        el n&uacute;mero de v&eacute;rtices detectado
	 * @throws IllegalArgumentException si el formato de la l&iacute;nea no es v&aacute;lido
	 */
	private static int detectarVertices(String linea)
	{
			/* Due to the structure of the header: ****V1 *V2..., we can filter the vertices 
				with spaces and get the length of the resultant array.*/
			String[] aux = linea.trim().split("\\s+");
			Integer[] almostEdges = Stream.of(aux).map(Integer::valueOf).toArray(Integer[]::new);
			return almostEdges.length; 
	}
	
	/**Carga un grafo desde un archivo y lo almacena en un
	 * {@link TraductorGrafo}.
	 * 
	 * @param  nombreArchivo nombre o ruta del archivo que se desea cargar.
	 * @return               Un <code>TraductorGrafo</code> que contiene el 
	 *                       grafo representado en el archivo dado.
	 * 
	 * @throws IOException              si ocurre alg&uacute;n error durante la
	 *                                  lectura del archivo
	 * @throws IllegalArgumentException si el formato del archivo no es v&aacute;lido
	 */
	static TraductorGrafo cargarGrafo(String nombreArchivo)
			throws IOException
	{
		TraductorGrafo salida;
		
		BufferedReader Lector = new BufferedReader(
				new FileReader(nombreArchivo));
		
		String linea = Lector.readLine();
		if(esLista(linea)){
			salida = new TraductorDesdeLista();
			do{
				cargarLista(linea, (TraductorDesdeLista)salida);
				linea = Lector.readLine();
			}while(linea != null);
		}else{
			salida = new TraductorDesdeMatriz(detectarVertices(linea));
			do{
				cargarMatriz(linea,( (TraductorDesdeMatriz)salida));
				linea = Lector.readLine();
			}while(linea != null);
		}
		
		return salida;
	}
	
	/**Carga el grafo representado en el archivo dado y lo muestra en su
	 * representaci&oacute;n alternativa.
	 * 
	 * @param args Arreglo que contiene el nombre el archivo como &uacute;nico elemento
	 * 
	 * @throws IOException              si ocurre alg&uacute;n error durante la
	 *                                  lectura del archivo
	 * @throws IllegalArgumentException si el formato del archivo no es v&aacute;lido
	 */
	public static void main(String[] args)
		throws IOException, IllegalArgumentException
	{
		if(args.length < 1){
			System.err.println("Uso: java Cliente <nombreArchivo>");
			return;
		}
		
		TraductorGrafo g = cargarGrafo(args[0]);
		
		System.out.println(g.imprimirGrafoTraducido());
		System.out.println(g.esDirigido());
	}
}