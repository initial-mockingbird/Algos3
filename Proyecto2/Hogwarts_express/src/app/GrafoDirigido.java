package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Implementacion de un grafo dirigido el cual sigue la documentacion proveida por el pdf.
 */
public class GrafoDirigido implements Grafo{
    protected HashSet<Arco> listaLados;
    protected HashSet<Vertice> gVertices;
    protected int numNodos;
    protected int numLados;

    GrafoDirigido(){
        this.listaLados = new HashSet<Arco>();
        this.gVertices = new HashSet<Vertice>();
        this.numNodos = 0;
        this.numLados = 0;
    }
    GrafoDirigido(HashSet<Arco> nlistaLados,HashSet<Vertice> ngVertices,int nnumNodos,int nnumLados){
        this.listaLados = new HashSet<Arco>(nlistaLados);
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
        this.numNodos = this.gVertices.size();
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
        throw new NoSuchElementException("El identificador del vertice no es valido.");
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
            this.numNodos = this.gVertices.size();
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
            throw new NoSuchElementException("El identificador del vertice no es valido.");
        }
        Iterator<Arco> nextLado = this.listaLados.iterator();
        while(nextLado.hasNext()){
            Arco sig = nextLado.next();
            if (sig.getExtremoInicial().equals(nullV) && sig.getExtremoInicial().equals(sig.getExtremoFinal())){
                gradoSoFar += 2;
            } else if (sig.getExtremoInicial().equals(nullV) || sig.getExtremoFinal().equals(nullV) ){
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
            throw new NoSuchElementException("El identificador del vertice no es valido.");
        }
        HashSet<Vertice> ady = new HashSet<Vertice>();
        Iterator<Arco> nextLado = this.listaLados.iterator();
        while(nextLado.hasNext()){
            Arco sig = nextLado.next();
            if (sig.getExtremoInicial().equals(nullV)){
                ady.add(sig.getExtremoFinal());
            } else if (sig.getExtremoFinal().equals(nullV)){
                ady.add(sig.getExtremoInicial());
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
            throw new NoSuchElementException("El identificador del vertice no es valido.");
        }
        Iterator<Arco> nextLado = this.listaLados.iterator();
        LinkedList<Lado> inc = new LinkedList<Lado>();
        while(nextLado.hasNext()){
            Arco sig = nextLado.next();
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
    public GrafoDirigido clone(){
        return new GrafoDirigido(this.listaLados,this.gVertices,this.numNodos,this.numLados);
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
    public Boolean cargarGrafo(String nombreArchivo)
    {
        int k = 0;
        try {
            BufferedReader lector = new BufferedReader(
                new FileReader(nombreArchivo));
        String linea = lector.readLine();
        String[] nlinea;
		while(linea != null){
            
            if ((k==0) && (!linea.contains("n")) && (!linea.contains("d"))){
                return false;
            }
            if ((k==0) && (linea.contains("n"))) {
                return false;
            } else if (k==1){
                this.numNodos = Integer.valueOf(linea).intValue();
            } else if (k==2){
                this.numLados = Integer.valueOf(linea).intValue();
            } else if ((k<this.numNodos+3) && (k>0) ){
                nlinea = linea.split("\\s+");
                int id = Integer.valueOf(nlinea[0]).intValue();
                String nombre = nlinea[1];
                double x = Double.valueOf(nlinea[2]);
                double y = Double.valueOf(nlinea[3]);
                double p = Double.valueOf(nlinea[4]);
                Vertice v = new Vertice(id,nombre,x,y,p);
                this.gVertices.add(v);
            } else if ((k<this.numNodos+3+this.numLados) && (k>0) ){
                nlinea = linea.split("\\s+");
                int idv = Integer.valueOf(nlinea[0]).intValue();
                int idw = Integer.valueOf(nlinea[1]).intValue();
                String tipo = nlinea[2];
                double p = Double.valueOf(nlinea[3]);
                Vertice v = obtenerVertice(idv);
                Vertice w = obtenerVertice(idw);
                listaLados.add(new Arco(v,w,tipo,p));
            }
            k += 1;
            linea = lector.readLine();
        }
        lector.close();
        return true;
        } 
        catch (Exception FileNotFoundException){
            return false;
        }
    }

    /**
     * crea un grafo  dirigido minimo.
     * @return grafo  dirigido minimo.
     */
    public GrafoDirigido crearGrafoDirigido(){
        return new GrafoDirigido();
    }
    /**
     * Dada un arco, lo inserta en el grafo si no esta en la estructura, de otro modo,
     * la deja sin modificar.
     * @param a arco a insertar
     * @return True|False si la arco es insertada|no insertada.
     */
    public Boolean agregarArco(Arco a){
        if (this.listaLados.contains(a)){
            return false;
        }
        this.listaLados.add(a);
        this.numLados+=1;
        Iterator<Arco> lados = listaLados.iterator();
        HashSet<Vertice> vertices = new HashSet<Vertice>();
        while (lados.hasNext()){
            Arco siguiente = lados.next();
            vertices.add(siguiente.getExtremoInicial());
            vertices.add(siguiente.getExtremoFinal());
        }
        this.gVertices = new HashSet<Vertice>(vertices);
        this.numNodos = vertices.size();
        return true;
    }

    /** 
     * Metodo alterno para agregar un arco, la cual toma los parametros del constructor
     * de arco
     * @param u primer vertice.
     * @param v segundo vertice.
     * @param tipo tipo del arco.
     * @param p peso del arco.
     * @return True|False si el arco se inserto|no se inserto.
     */
    public Boolean agregarArco(Vertice u, Vertice v, String tipo, double p){
        Arco nueva = new Arco(u,v,tipo,p);
        if (listaLados.contains(nueva)){
            return false;
        }
        listaLados.add(nueva);
        return true;
    }

    /** 
     * Metodo alterno para agregar un arco, la cual toma los parametros del constructor
     * de arco
     * @param u String primer vertice.
     * @param v String segundo vertice.
     * @param tipo tipo del arco.
     * @param p peso del arco.
     * @return True|False si el arco se inserto|no se inserto.
     */
    public Boolean agregarArco(String u, String v, String tipo, double p){
        Arco nueva = new Arco(u,v,tipo,p);
        if (listaLados.contains(nueva)){
            return false;
        }
        listaLados.add(nueva);
        return true;
    }

    /**
     * Dados los datos que representan a un arco, dice si tal
     * arco pertenece al grafo.
     * @param u identificador del primer vertice.
     * @param v identificador del segundo vertice.
     * @param tipo tipo del arco.
     * @return True|False si el arco pertenece | no pertenece al grafo.
     */
    public Boolean estaArco(int u, int v, String tipo){
        Vertice nullU = new Vertice(u,"",0.0,0.0,0.0);
        Vertice nullV = new Vertice(v,"",0.0,0.0,0.0);
        Arco uv = new Arco(nullU,nullV,tipo,0.0);
        return this.listaLados.contains(uv);
    } 

    /**
     * dada un arco representada por su identificador> "idVerticeInicial idVerticeFinal tipo"
     * lo elimina de la estrucura si esta en ella, de otra forma, la deja intacta.
     * @param id identificador del arco.
     * @return True|False si se elimino | no se elimino de la estructura. 
     */
    public Boolean eliminarArco(int v, int u, String tipo){
        Vertice nullV = new Vertice(v,"",0.0,0.0,0.0);
        Vertice nullW = new Vertice(u,"",0.0,0.0,0.0);
        Arco nullA = new Arco(nullV,nullW,tipo,0.0);
        if (this.listaLados.contains(nullA)){
            this.listaLados.remove(nullA);
            return true;
        }
        return false;
    }

    /**
     * Dado el identificador que representa a un arco: "idVerticeInical idVerticeFinal tipo", 
     *  obtiene el arco asociada a tal identificador o suelta una excepcion si no existe 
     * ningun arco asociado a tal identificador.
     * @param id identificador de un arco
     * @return  arco asociada a la id.
     * @throws NoSuchElementException
     */
    public Arco obtenerArco(int v, int u, String tipo)
        throws NoSuchElementException
    {
        Vertice nullV = new Vertice(v,"",0.0,0.0,0.0);
        Vertice nullW = new Vertice(u,"",0.0,0.0,0.0);
        if (!this.gVertices.contains(nullV) || !this.gVertices.contains(nullW)){
            throw new NoSuchElementException("Algun identificador de vertice no es valido.");
        }
        Arco nullA = new Arco(nullV,nullW,tipo,0.0);
        Iterator<Arco> arco = listaLados.iterator();
        while(arco.hasNext()){
            Arco sig = arco.next();
            if (sig.equals(nullA)){
                return sig;
            }
        }
        throw new NoSuchElementException("no existe un vertice que sea asociado con alguno de los identificadores.");
    }

    /**
     * Calcula el grado interior de un vertice representado por su identificador. Suelta
     * una excepcion si el identificador no representa a ningun vertice del grafo.
     * @param v identificador del vertice
     * @return grado interior del vertice asociado al identificador.
     * @throws NoSuchElementException
     */
    public int gradoInterior(int v)
        throws NoSuchElementException
    {
        int gradoSoFar = 0;
        Vertice nullV = new Vertice(v,"",0.0,0.0,0.0);
        if (!this.gVertices.contains(nullV)){
            throw new NoSuchElementException("El identificador del vertice no es valido.");
        }
        Iterator<Arco> nextLado = this.listaLados.iterator();
        while(nextLado.hasNext()){
            Arco sig = nextLado.next();
            if (sig.getExtremoFinal().equals(nullV) ){
                gradoSoFar += 1;
            }
        }
        return gradoSoFar;
    }

    /**
     * Calcula el grado exterior de un vertice representado por su identificador. Suelta
     * una excepcion si el identificador no representa a ningun vertice del grafo.
     * @param v identificador del vertice
     * @return grado exterior del vertice asociado al identificador.
     * @throws NoSuchElementException
     */
    public int gradoExterior(int v)
        throws NoSuchElementException
    {
        int gradoSoFar = 0;
        Vertice nullV = new Vertice(v,"",0.0,0.0,0.0);
        if (!this.gVertices.contains(nullV)){
            throw new NoSuchElementException("El identificador del vertice no es valido.");
        }
        Iterator<Arco> nextLado = this.listaLados.iterator();
        while(nextLado.hasNext()){
            Arco sig = nextLado.next();
            if (sig.getExtremoInicial().equals(nullV) ){
                gradoSoFar += 1;
            }
        }
        return gradoSoFar;
    }

    /**
     * Dado un identificador de vertice, devuelve todos los vertices sucesores
     * en una lista o suelta una excepcion si el identificador no posee un vertice asociado.
     * @param id identificador de vertice
     * @return lista con los vertices sucesores.
     * @throws NoSuchElementException
     */
    public LinkedList<Vertice> sucesores(int id)
        throws NoSuchElementException
    {
        Vertice nullV = new Vertice(id,"",0.0,0.0,0.0);
        if (!this.gVertices.contains(nullV)){
            throw new NoSuchElementException("No se encontro el elemento asociado al identificador.");
        }
        Iterator<Arco> nextLado = this.listaLados.iterator();
        LinkedList<Vertice> succ = new LinkedList<Vertice>();
        while(nextLado.hasNext()){
            Arco sig = nextLado.next();
            if (sig.getExtremoInicial().equals(nullV) ){
                succ.add(sig.getExtremoFinal());
            }
        }
        return succ;
    }

    /**
     * Dado un identificador de vertice, devuelve todos los vertices predecesores
     * en una lista o suelta una excepcion si el identificador no posee un vertice asociado.
     * @param id identificador de vertice
     * @return lista con los vertices predecesores.
     * @throws NoSuchElementException
     */
    public LinkedList<Vertice> predecesores(int id)
        throws NoSuchElementException
    {
        Vertice nullV = new Vertice(id,"",0.0,0.0,0.0);
        if (!this.gVertices.contains(nullV)){
            throw new NoSuchElementException("No se encontro el elemento asociado al identificador.");
        }
        Iterator<Arco> nextLado = this.listaLados.iterator();
        LinkedList<Vertice> succ = new LinkedList<Vertice>();
        while(nextLado.hasNext()){
            Arco sig = nextLado.next();
            if (sig.getExtremoFinal().equals(nullV) ){
                succ.add(sig.getExtremoInicial());
            }
        }
        return succ;
    }
    public static void main(String[] args){
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

    public HashSet<Arco> arcos(){
        return this.listaLados;
    }


}