import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.lang.Math;

public class Grafo {
	
	ArrayList<Pairs<Float,Float>> vertices;
	HashSet<Triple<Integer,Integer,Float>>	 edges;
	
	Grafo(){
		this.vertices = new ArrayList<Pairs<Float,Float>>();
		this.edges = new HashSet<Triple<Integer,Integer,Float>>();
	}
	
	public void Lector(String archivo)
			throws IOException
	{
		
		 BufferedReader lector = new BufferedReader(new FileReader(archivo));
		 
		 String linea = lector.readLine();
		 
		 int numVertices = Integer.valueOf(linea).intValue();
		 int numAristas;
		 int k = 0;
		 while (!linea.equals(null)){
			 linea = lector.readLine();
			 
			 if (k<numVertices){
				 String[] coords = linea.split("\\s+");
				 Pairs<Float, Float> par = new Pairs<Float, Float>(Float.valueOf(coords[0]),Float.valueOf(coords[0]) );
				 vertices.add(par);
			 } else if (k > numVertices+1){
				 numAristas = Integer.valueOf(linea).intValue();
			 } else {
				 String[] coords = linea.split("\\s+");
				 int inicio = Integer.valueOf(coords[0]).intValue();
				 int finals =  Integer.valueOf(coords[1]).intValue();
				 Float costo = (float) (Math.pow((vertices.get(inicio).fst()-vertices.get(finals).fst()),2)+Math.pow((vertices.get(inicio).snd()-vertices.get(finals).snd()),2));
				 costo = (float) Math.pow(costo,0.5);
				 edges.add(new Triple<Integer, Integer, Float>(inicio,finals,costo));
			 }
			 k += 1;
		 }
		 lector.close();
	}
	
	public Bellford(){
		
	}

}
