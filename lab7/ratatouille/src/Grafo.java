import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.lang.Math;

public class Grafo {
	
	ArrayList<Pair<Float,Float>> vertices;
	HashSet<Triple<Integer,Integer,Float>>	 edges;
	
	Grafo(){
		this.vertices = new ArrayList<Pair<Float,Float>>();
		this.edges = new HashSet<Triple<Integer,Integer,Float>>();
	}
	
	public void Lector(String archivo)
			throws IOException
	{
		
		 BufferedReader lector = new BufferedReader(new FileReader(archivo));
		 
		 String linea = lector.readLine();
		 
		 int numVertices = Integer.valueOf(linea).intValue();
		 int numAristas = -1;
		 int k = 0;
		 while (!(linea==null)){
			 linea = lector.readLine();
			 
			 if (k<numVertices){
				 String[] coords = linea.split("\\s+");
				 Pair<Float, Float> par = new Pair<Float, Float>(Float.valueOf(coords[0]),Float.valueOf(coords[1]) );
				 vertices.add(par);
			 } else if (k == numVertices+1){
				 numAristas = Integer.valueOf(linea).intValue();
			 } else if ((k > numVertices+1) && (k < numVertices+numAristas+2))  {
				 String[] coords = linea.split("\\s+");
				 int inicio = Integer.valueOf(coords[0]).intValue();
				 int finals =  Integer.valueOf(coords[1]).intValue();
				 Float costo = (float) (Math.pow((vertices.get(inicio).fst()-vertices.get(finals).fst()),2)+Math.pow((vertices.get(inicio).snd()-vertices.get(finals).snd()),2));
				 costo = (float) Math.pow(costo,0.5);
				 Triple<Integer, Integer, Float> tripleta = new Triple<Integer, Integer, Float>(inicio,finals,costo);
				 edges.add(tripleta);
			 }
			 k += 1;
		 }
		 lector.close();
	}

	@Override
	public String toString() {
		return "Vertices:\n"+ this.vertices.toString() + "\n \n aristas:\n" + this.edges.toString();
	}
}
