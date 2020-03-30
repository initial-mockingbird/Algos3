import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.lang.Math;
import java.text.DecimalFormat;
import java.util.PriorityQueue;
import java.util.stream.Stream;
public class Grafo {
	
	ArrayList<Pair<Float,Float>> vertices;
	HashSet<Triple<Integer,Integer,Float>>	 edges;
	ArrayList<ArrayList<Integer>> adjacencyList;
	ArrayList<Pair<Integer,Integer>> edgeList;
	int numVertices;
	int numAristas;
	Grafo(){
		this.vertices = new ArrayList<Pair<Float,Float>>();
		this.edges = new HashSet<Triple<Integer,Integer,Float>>();
		this.adjacencyList = new ArrayList<ArrayList<Integer>>();
		this.edgeList = new ArrayList<Pair<Integer,Integer>>();
		this.numVertices = 0;
		this.numAristas = 0;
	}
	
	public void Lector(String archivo)
			throws IOException
	{
		
		 BufferedReader lector = new BufferedReader(new FileReader(archivo));
		 
		 String linea = lector.readLine();
		 
		 this.numVertices = Integer.valueOf(linea).intValue();
		 int k = 0;
		 ArrayList<Integer> adMember;
		 while (!(linea==null)){
			 linea = lector.readLine();
			 
			 if (k<numVertices){
				 String[] coords = linea.split("\\s+");
				 Pair<Float, Float> par = new Pair<Float, Float>(Float.valueOf(coords[0]),Float.valueOf(coords[1]) );
				 vertices.add(par);
				 adMember = new ArrayList<Integer>();
				 adMember.add(k);
				 this.adjacencyList.add(adMember);
			 } else if (k == numVertices+1){
				 numAristas = Integer.valueOf(linea).intValue();
			 } else if ((k > numVertices+1) && (k < numVertices+numAristas+2))  {
				 String[] coords = linea.split("\\s+");
				 int inicio = Integer.valueOf(coords[0]).intValue();
				 int finals =  Integer.valueOf(coords[1]).intValue();
				 this.adjacencyList.forEach((listAd) -> {if (listAd.get(0).equals(inicio)) listAd.add(finals);});
				 this.adjacencyList.forEach((listAd) -> {if (listAd.get(0).equals(finals)) listAd.add(inicio);});
				 this.edgeList.add(new Pair<Integer,Integer>(inicio,finals));
			 }
			 k += 1;
		 }
		 lector.close();
		 this.adjacencyList.sort( (ArrayList<Integer> iAdj,ArrayList<Integer> jAdj) -> {if (iAdj.get(0)<jAdj.get(0)) return -1; if (iAdj.get(0)==jAdj.get(0)) return 0; else return 1;} );
	}

	public ArrayList<Integer> succIndex(Integer v){
		ArrayList<Integer> ans = this.adjacencyList.get(v.intValue());
		ans.remove(ans.get(0));
		return ans;
	}


	public float costo(int a,int b){
		Pair<Float,Float> inicio = vertices.get(a);
		Pair<Float,Float> finals = vertices.get(b);
		return (float) Math.pow((Math.pow((inicio.fst()-finals.fst()),2)+Math.pow((inicio.snd()-finals.snd()),2)),0.5);
	}

	public float costo(Pair<Float,Float> inicio, Pair<Float,Float> finals ){
		return (float) Math.pow((Math.pow((inicio.fst()-finals.fst()),2)+Math.pow((inicio.snd()-finals.snd()),2)),0.5);
	}

	public Pair<float[],int[]> Dijkstra(int inicio){
		float[] costo =  new float[this.numVertices];
		int[] predecessors = new int[this.numVertices];
		Comparator<Integer> costComparator = (Integer i, Integer k) ->  {return (int) Math.signum(costo[i]-costo[k]);}; 
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>( costComparator );
		Iterator<Integer> j;

		for (int i=0;i<this.numVertices;i++){

			costo[i] = (i==inicio) ? 0 : Float.POSITIVE_INFINITY;
			queue.add(i);
		}
		predecessors[inicio] = inicio;

		while (queue.size()>0){
			int w = queue.remove();
			j = this.succIndex(w).iterator();
			while(j.hasNext()){
				int succ = j.next();
				if ((costo[w]+costo(w,succ)<costo[succ])){
					costo[succ] = costo[w]+costo(w,succ);
					predecessors[succ] = w;
				}
				if ((costo[succ]+costo(w,succ)<costo[w])){
					costo[w] = costo[succ]+costo(w,succ);
					predecessors[w] = succ;
				}
			}
		}

		return new Pair<float[],int[]>(costo,predecessors);
	}

	@Override
	public String toString() {
		Iterator<ArrayList<Integer >> i = adjacencyList.iterator();
		String adjacencyAssociates="";
		while(i.hasNext()){
			
			ArrayList<Integer> associations = i.next();
			adjacencyAssociates +=  associations.toString() + "\n";
		}


		return "\nVertices:\n"+ this.vertices.toString() + "\nAristas: \n" + adjacencyAssociates;
	}


	public Pair<float[],int[]> Bellman(int inicio){
		Iterator<Pair<Integer,Integer>> k;
		float[] costo = new float[this.numVertices];
		int[] predecessors = new int[this.numVertices];
		for (int i=0;i<this.numVertices;i++){
			costo[i] = (i==inicio) ? 0 : Float.POSITIVE_INFINITY;
		}
		predecessors[inicio] = inicio;
		boolean cambio = true;
		int i = 0;
		while( (i<vertices.size()) && cambio){
			cambio = false;
			k = this.edgeList.iterator();
			while(k.hasNext()){
				Pair<Integer,Integer> edges = k.next();
				int n = edges.fst();
				int m = edges.snd();
				if (costo[n]+this.costo(n, m)<costo[m]){
					costo[m] = costo[n]+this.costo(n, m);
					predecessors[m] = n;
					cambio = true;
				}
				if (costo[m]+this.costo(n, m)<costo[n]){
					costo[n] = costo[m]+this.costo(n, m);
					predecessors[n] = m;
					cambio = true;
				}
			}
			i++;
			}

		return new Pair<float[],int[]>(costo,predecessors);
	}


	public void shortestPath(String[] args)
		throws IllegalArgumentException
	{	
		String mode = "Dijkstra";
		int origen = Integer.valueOf(args[0]).intValue();
		Pair<float[],int[]> ans;
		if (args.length>1){
			mode = args[1];
		}
		if (mode.equals("Dijkstra")){
			ans = this.Dijkstra(origen);
		}
		else if (mode.equals("Bellman")){
			ans = this.Bellman(origen);
		} else {
			throw new IllegalArgumentException("Uso: Mesero <Instancia> <Origen> [Dijkstra|Bellman]");
		}

		String respuesta = "";
		float[] costo = ans.fst();
		int[] caminos = ans.snd();
		for (int i=0;i<this.numVertices;i++){
			respuesta +="Nodo: " + i +": ";
			int j = i;
			int k = 0;
			LinkedList<String> chain = new LinkedList<String>();
			chain.addFirst(String.valueOf(i));
			chain.addFirst("->");
			while(caminos[j]!= j){
				chain.addFirst(String.valueOf(caminos[j]));
				j = caminos[j];
				k++;
				
				if (caminos[j]!= j){
					chain.addFirst("->");
				}
				
			}
			respuesta += chain.stream().reduce("", (String u,String v) -> {return u+v;} );
			DecimalFormat costico = new DecimalFormat("0.0#");
			respuesta += "\t\t"+k+ " lados"+ " (costo: " + costico.format(costo[i]) +")\n";
		}
		System.out.println(respuesta);
	}
}
