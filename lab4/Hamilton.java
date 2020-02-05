import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays; 
import java.util.stream.*;

/**<p>Programa que indica la existencia de un camino Hamiltoniano
 * empleando DFS o BFS</p>
 * 
 * <p>Un archivo Lista de Adyacencia representa un grafo, donde cada
 * l&iacute;nea tiene el formato:</p>
 * <blockquote>
 * <code>n</code>
 * <code>m</code>
 * <code>ni<sub>j</sub> nf<sub>j</sub></code>
 * </blockquote>
 * <p>Donde <code>n</code> es el numero de nodos, <code>m</code> es el numero de 
 *	aristas, <code>ni<sub>j</sub></code> es el indice del vertice al inicio de la
 *	j-esima arista y <code>nf<sub>j</sub></code> es el indice del vertice al final
 *	de la j-esima arista.</p>
 *
 * <p>El programa {@link #main} lee un archivo, detecta (a trav&eacute;s de
 * {@link #esLista(String)}) el formato del archivo, lo carga (a trav&eacute;s de
 * {@link #cargarGrafo(String)}) en un {@link TraductorGrafo}, y lo imprime
 * en el format contrario. Las funciones se ofrecen a nivel de package.</p>
 */

public class Hamilton {


	/**Carga un grafo desde un archivo y lo almacena en un
	 * {@link Grafo}.
	 * 
	 * @param  nombreArchivo nombre o ruta del archivo que se desea cargar.
	 * @return               Un <code>Grafo</code> que contiene el 
	 *                       grafo representado en el archivo dado.
	 * 
	 * @throws IOException              si ocurre alg&uacute;n error durante la
	 *                                  lectura del archivo
	 * @throws IllegalArgumentException si el formato del archivo no es v&aacute;lido
	 */
	static Grafo cargarGrafo(String nombreArchivo)
			throws IOException
	{
		Grafo salida;
		
		BufferedReader Lector = new BufferedReader(
				new FileReader(nombreArchivo));
		
		String linea = Lector.readLine();
		salida = new Grafo();
		do{
			cargarLista(linea, salida);
			linea = Lector.readLine();
		}while(linea != null);
		
		return salida;
	}

	/**Carga la <code>linea</code> de un archivo Lista de arcos dada
	 * en el <code>Grafo</code> dado.
	 * 
	 * @param linea La l&iacute;nea del archivo que se desea cargar.
	 * @param grafo El grafo en el cual ser&aacute; cargada la l&iacute;nea. Este grafo se
	 *              modifica directamente.
	 * @throws IllegalArgumentException si el formato de la l&iacute;nea no es v&aacute;lido
	 */
	private static void cargarLista(String linea, Grafo grafo)
			throws IllegalArgumentException
	{
		String [] sanity = linea.split("\\s+");
		if (sanity.length> 1){
			grafo.agregarArco(Integer.parseInt(sanity[0]),Integer.parseInt(sanity[1]));
		} else {
			grafo.addDim(Integer.parseInt(sanity[0]));
		}
	}

	/** Devuelve un String con un camino hamiltoniano del <code>Grafo g</code> si existiese alguno,
	*	de otra forma explica por que no existe.
	*
	*	@param g El grafo sobre el cual se va a tratar encontrar un camino hamiltoniano.
	*	@param modo Forma de solventar el problema, ya sea por BFS o por DFS.
	*	
	*	@throws IllegalArgumentException si el modo no se encuentra entre BFS o DFS.
	*
	**/
	private static String solventar(Grafo g, String modo)
			throws IllegalArgumentException
	{
		if (modo.equals("BFS")){
			BFS respuesta = new BFS();
			return respuesta.bfs(g);
		}

		if (modo.equals("DFS")){
			DFSV2 respuesta = new DFSV2();
			return respuesta.dfs(g);
		}

		else {
			throw new IllegalArgumentException("Por favor ingrese un modo valido: <BFS|DFS>");
		}

	}

	/**Carga el grafo representado en el archivo dado e imprime un camino hamiltoniano
	 * si existe alguno, en caso contrario, explica por que no hay.
	 * 
	 * @param args Arreglo que contiene el nombre el archivo y como se quiere resolver el
	 * problema (DFS|BFS)
	 * 
	 * @throws IOException              si ocurre alg&uacute;n error durante la
	 *                                  lectura del archivo
	 * @throws IllegalArgumentException si el formato del archivo no es v&aacute;lido
	 */
	public static void main(String[] args)
		throws IOException, IllegalArgumentException
	{
		if(args.length < 2){
			throw new IllegalArgumentException("Uso: java Cliente <nombreArchivo> <BFS|DFS>");
		}

		Grafo g = cargarGrafo(args[0]);
		String respuesta = solventar(g, args[1]);
		System.out.println(respuesta);
		/*
		BFS respuesta = new BFS();
		DFSV2 respuesdfs = new DFSV2();
		System.out.println(respuesta.bfs(g));
		System.out.println(respuesdfs.dfs(g));*/		
	}
}