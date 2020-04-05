package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.HashSet;
import java.util.Iterator;

/** clase GrafoNoDirigido la cual representa a un grafo no dirigido.
 * 
 */
public class GrafoNoDirigido implements Grafo {
    /** Parametros minimos para representar el Grafo no dirigido.
     * 
     */
    protected HashSet<Arista> listaLados;
    protected HashSet<Vertice> gVertices;
    protected int numNodos;
    protected int numLados;

    GrafoNoDirigido(){
        this.listaLados = new HashSet<Arista>();
        this.gVertices = new HashSet<Vertice>();
        this.numNodos =  0;
        this.numLados = 0;
    }

    GrafoNoDirigido(HashSet<Arista> nlistaLados,HashSet<Vertice> ngVertices,int nnumNodos,int nnumLados){
        this.listaLados = new HashSet<Arista>(nlistaLados);
        this.gVertices = new HashSet<Vertice>(ngVertices);
        this.numNodos =  nnumNodos;
        this.numLados = nnumLados;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int numeroDeVertices(){
        return this.numNodos;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int numeroDeLados(){
        return this.numLados;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean agregarVertice(Vertice v){
        if (this.gVertices.contains(v)){
            return false;
        }
        this.gVertices.add(v);
        return true;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Vertice obtenerVertice(int id)
        throws NoSuchElementException
    {
        Vertice nullV = new Vertice(id,"",0.0,0.0,0.0);
        Iterator<Vertice> vs = this.gVertices.iterator();
        while (vs.hasNext()){
            Vertice next = vs.next();
            if (next.equals(nullV)){
                return next;
            }
        }
        throw new NoSuchElementException("No existe ningun vertice con tal identificador.");
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean estaVertice(int id){
        Vertice nullV = new Vertice(id,"",0.0,0.0,0.0);
        return this.gVertices.contains(nullV);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean eliminarVertice(int id){
        Vertice nullV = new Vertice(id,"",0.0,0.0,0.0);
        if (this.gVertices.contains(nullV)){
            this.gVertices.remove(nullV);
            return true;
        }
        return false;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public LinkedList<Vertice> vertices(){
        return new LinkedList<Vertice>(this.gVertices);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public LinkedList<Lado> lados(){
        return new LinkedList<Lado>(this.listaLados);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int grado(int id)
    throws NoSuchElementException
    {
        int gradoSoFar = 0;
        Vertice nullV = new Vertice(id,"",0.0,0.0,0.0);
        if (!this.gVertices.contains(nullV)){
            throw new NoSuchElementException("No existe ningun vertice con tal identificador.");
        }
        Iterator<Arista> nextLado = this.listaLados.iterator();
        while(nextLado.hasNext()){
            Arista sig = nextLado.next();
            if ( sig.getExtremo1().equals(nullV) && sig.getExtremo1().equals(sig.getExtremo2())){
                gradoSoFar += 2;
            } else if (sig.getExtremo1().equals(nullV) || sig.getExtremo2().equals(nullV) ){
                gradoSoFar += 1;
            }
        }
        return gradoSoFar;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public LinkedList<Vertice> adyacentes(int id)
        throws NoSuchElementException
    {
        Vertice nullV = new Vertice(id,"",0.0,0.0,0.0);
        if (!this.gVertices.contains(nullV)){
            throw new NoSuchElementException("No existe ningun vertice con tal identificador.");
        }
        HashSet<Vertice> ady = new HashSet<Vertice>();
        Iterator<Arista> nextLado = this.listaLados.iterator();
        while(nextLado.hasNext()){
            Arista sig = nextLado.next();
            if (sig.getExtremo1().equals(nullV)){
                ady.add(sig.getExtremo2() );
            } else if (sig.getExtremo2().equals(nullV)){
                ady.add(sig.getExtremo1() );
            }
        }
        return new LinkedList<Vertice>(ady);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public LinkedList<Lado> incidentes(int id)
        throws NoSuchElementException
    {
        Vertice nullV = new Vertice(id,"",0.0,0.0,0.0);
        if (!this.gVertices.contains(nullV)){
            throw new NoSuchElementException("No existe ningun vertice con tal identificador.");
        }
        Iterator<Arista> nextLado = this.listaLados.iterator();
        LinkedList<Lado> inc = new LinkedList<Lado>();
        while(nextLado.hasNext()){
            Arista sig = nextLado.next();
            if (sig.incide(nullV)){
                inc.add(sig);
            } 
        }
        return inc;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public GrafoNoDirigido clone(){
        return new GrafoNoDirigido(listaLados,gVertices,numNodos,numLados);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString(){
        return this.listaLados.toString();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean cargarGrafo(String nombreArchivo){
        int k = 0;
        try { 
        BufferedReader Lector = new BufferedReader(
				new FileReader(nombreArchivo));
        
        String linea = Lector.readLine();
        String[] nlinea;
		while(linea != null){
            
            linea = linea.trim();
            if ((k==0) && (linea.contains("d"))) {
                return false;
            } else if (k==1){
                this.numNodos = Integer.valueOf(linea).intValue();
            } else if (k==2){
                this.numLados = Integer.valueOf(linea).intValue();
            } else if ((k<this.numNodos+3) && (k>0)){
                nlinea = linea.split("\\s+");
                int id = Integer.valueOf(nlinea[0]).intValue();
                String nombre = nlinea[1];
                double x = Double.valueOf(nlinea[2]);
                double y = Double.valueOf(nlinea[3]);
                double p = Double.valueOf(nlinea[4]);
                Vertice v = new Vertice(id,nombre,x,y,p);
                this.gVertices.add(v);
            } else if ((k<this.numNodos+3+this.numLados)&& (k>0) ){
                nlinea = linea.split("\\s+");
                int idv = Integer.valueOf(nlinea[0]).intValue();
                int idw = Integer.valueOf(nlinea[1]).intValue();
                String tipo = nlinea[2];
                double p = Double.valueOf(nlinea[3]);
                Vertice v = obtenerVertice(idv);
                Vertice w = obtenerVertice(idw);
                listaLados.add(new Arista(v,w,tipo,p));
            }
            k += 1;
            linea = Lector.readLine();
            
        }
        Lector.close();
        return true;
        }
        catch (Exception FileNotFoundException){
            return false;
        } 
    }

    public HashSet<Arista> aristas(){
        return this.listaLados;
    }

    public Vertice obtener_nombre(String nombre){
        Iterator<Vertice> j = this.gVertices.iterator();
        Vertice el_Propio;
        while(j.hasNext()){
            el_Propio = j.next();
            if (nombre.equals(el_Propio.getNombre() )){
                return el_Propio;
            }
        }
        throw new NoSuchElementException("No hay un vertice con tal nombre :(");
    }

    public Arista obtenerArista(int id_v, int id_w, String tipo){
        Vertice v = this.obtenerVertice(id_v);
        Vertice w = this.obtenerVertice(id_w);
        Arista aComparar = new Arista(v,w,tipo,(double) 0);
        Iterator<Arista> i = this.listaLados.iterator();
        Arista res;
        while(i.hasNext()){
            res = i.next();
            if (res.equals(aComparar)){
                return res;
            }
        }
        throw new NoSuchElementException("Arista no encontrada :(");
    }

    public static void main(String[] args){
    }

    

}