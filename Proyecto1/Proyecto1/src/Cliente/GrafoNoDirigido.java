package Cliente;

import java.io.IOException;
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
     * crea un grafo no dirigido minimo.
     * @return grafo no dirigido minimo.
     */
    public GrafoNoDirigido crearGrafoNoDirigido(){
        return new GrafoNoDirigido();
    }
    /**
     * Dada una arista, la inserta en el grafo si no esta en la estructura, de otro modo,
     * la deja sin modificar.
     * @param a arista a insertar
     * @return True|False si la arista es insertada|no insertada.
     */
    public Boolean agregarArista(Arista a){
        if (this.listaLados.contains(a)){
            return false;
        }
        this.listaLados.add(a);
        this.numLados+=1;
        Iterator<Arista> lados = listaLados.iterator();
        HashSet<Vertice> vertices = new HashSet<Vertice>();
        while (lados.hasNext()){
            Arista siguiente = lados.next();
            vertices.add(siguiente.getExtremo1());
            vertices.add(siguiente.getExtremo2());
        }
        this.gVertices = new HashSet<Vertice>(vertices);
        this.numNodos = vertices.size();
        return true;
    }
    /** 
     * Metodo alterno para agregar una arista, la cual toma los parametros del constructor
     * de arista
     * @param u primer vertice.
     * @param v segundo vertice.
     * @param tipo tipo de la arista.
     * @param p peso de la arista.
     * @return True|False si la arista se inserto|no se inserto.
     */
    public Boolean agregarArista(Vertice u, Vertice v, int tipo, double p){
        Arista nueva = new Arista(u,v,tipo,p);
        if (listaLados.contains(nueva)){
            return false;
        }
        listaLados.add(nueva);
        return true;
    }

    /** 
     * Metodo alterno para agregar una arista, la cual toma los parametros del constructor
     * de arista
     * @param u String identificador del primer vertice.
     * @param v String identificador del segundo vertice.
     * @param tipo tipo de la arista.
     * @param p peso de la arista.
     * @return True|False si la arista se inserto|no se inserto.
     */
    public Boolean agregarArista(String u, String v, int tipo, Double p){
        Arista nueva = new Arista(u,v,tipo,p);
        if (listaLados.contains(nueva)){
            return false;
        }
        listaLados.add(nueva);
        return true;
    }

    /**
     * dada una arista representada por su identificador> "idVerticeInicial idVerticeFinal tipo"
     * la elimina de la estrucura si esta en ella, de otra forma, la deja intacta.
     * @param id identificador de la arista.
     * @return True|False si se elimino | no se elimino de la estructura. 
     */
    public Boolean eliminarArista(String id){
        String[] middleMan = id.split(" ");
        Vertice nullV = new Vertice(Integer.valueOf(middleMan[0]).intValue(),"",0.0,0.0,0.0);
        Vertice nullW = new Vertice(Integer.valueOf(middleMan[1]).intValue(),"",0.0,0.0,0.0);
        Arista nullA = new Arista(nullV,nullW,Integer.valueOf(middleMan[2]),0.0);
        if (this.listaLados.contains(nullA)){
            this.listaLados.remove(nullA);
            return true;
        }
        return false;
    }
    /**
     * Dados los datos que representan a una Arista, dice si tal
     * arista pertenece al grafo.
     * @param u identificador del primer vertice.
     * @param v identificador del segundo vertice.
     * @param tipo tipo del arco.
     * @return True|False si la arista pertenece | no pertenece al grafo.
     */
    public Boolean estaArista(int u, int v, int tipo){
        Vertice nullU = new Vertice(u,"",0.0,0.0,0.0);
        Vertice nullV = new Vertice(v,"",0.0,0.0,0.0);
        Arista uv = new Arista(nullU,nullV,tipo,0.0);
        return this.listaLados.contains(uv);
    } 
    /**
     * Dado el identificador que representa a una arista: "idVerticeInical idVerticeFinal tipo", 
     *  obtiene la arista asociada a tal identificador o suelta una excepcion si no existe 
     * ninguna arista asociado a tal identificador.
     * @param id identificador de una arista
     * @return  Arista asociada a la id.
     * @throws NoSuchElementException
     */
    public Arista obtenerArista(String id)
        throws NoSuchElementException
    {
        String[] middleMan = id.split(" ");
        Vertice nullV = new Vertice(Integer.valueOf(middleMan[0]).intValue(),"",0.0,0.0,0.0);
        Vertice nullW = new Vertice(Integer.valueOf(middleMan[1]).intValue(),"",0.0,0.0,0.0);
        if (!this.gVertices.contains(nullV) || !this.gVertices.contains(nullW)){
            throw new NoSuchElementException("Alguno de los identificadores ingresados no es valido.");
        }
        Arista nullA = new Arista(nullV,nullW,Integer.valueOf(middleMan[2]),0.0);
        Iterator<Arista> arista = listaLados.iterator();
        while(arista.hasNext()){
            Arista sig = arista.next();
            if (sig.equals(nullA)){
                return sig;
            }
        }
        throw new NoSuchElementException("no existe un vertice que sea asociado con alguno de los identificadores.");
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
        
        String linea;
        String[] nlinea;
		do{
            linea = Lector.readLine();
            linea = linea.trim();
            if ((k==0) && (linea.contains("D"))) {
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
                int tipo = Integer.valueOf(nlinea[2]).intValue();
                double p = Double.valueOf(nlinea[3]);
                Vertice v = obtenerVertice(idv);
                Vertice w = obtenerVertice(idw);
                listaLados.add(new Arista(v,w,tipo,p));
            }
            k += 1;
        }while(linea != null);
        return true;
        }
        catch (Exception FileNotFoundException){
            return false;
        } 
    }

    public static void main(String[] args){
        GrafoNoDirigido g = new GrafoNoDirigido();
        g.cargarGrafo("/home/dan/Documents/Algos3/Algos3/Proyecto1/Proyecto1/test1.txt");
        System.out.println(g.toString());
    }
}