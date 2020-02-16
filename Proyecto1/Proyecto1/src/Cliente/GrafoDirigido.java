package Cliente;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.HashSet;
public class GrafoDirigido implements Grafo {
    protected HashSet<Arco> listaLados;
    protected HashSet<Vertice> gVertices;
    protected int numNodos;
    protected int numLados;

    GrafoDirigido(HashSet<Arco> nlistaLados,HashSet<Vertice> ngVertices,int nnumNodos,int nnumLados){
        this.listaLados = new HashSet<Arco>(nlistaLados);
        this.gVertices = new HashSet<Vertice>(ngVertices);
        this.numNodos =  nnumNodos;
        this.numLados = nnumLados;
    }
    @Override
    public int numeroDeVertices(){
        return this.numNodos;
    }
    @Override
    public int numeroDeLados(){
        return this.numLados;
    }
    @Override
    public Boolean agregarVertice(Vertice v){
        if (this.gVertices.contains(v)){
            return false;
        }
        this.gVertices.add(v);
        return true;
    }
    @Override
    public Vertice obtenerVertice(int id){
        Vertice nullV = crearVertice(id,"",0,0,0);
        Iterator<Vertice> vs = this.gVertices.iterator();
        while (vs.hasNext()){
            Vertice next = vs.next();
            if (next.equals(nullV)){
                return next;
            }
        }
        return  crearVertice(0,"",0,0,0);
    }
    @Override
    public Boolean estaVertice(int id){
        Vertice nullV = crearVertice(id,"",0,0,0);
        return this.gVertices.contains(nullV);
    }
    @Override
    public Boolean eliminarVertice(int id){
        Vertice nullV = crearVertice(id,"",0,0,0);
        if (this.gVertices.contains(nullV)){
            this.gVertices.remove(nullV);
            return true;
        }
        return false;
    }
    @Override
    public LinkedList<Vertice> vertices(){
        return new LinkedList<Vertice>(this.gVertices);
    }
    @Override
    public LinkedList<Lado> lados(){
        return new LinkedList<Vertice>(this.listaLados);
    }
    @Override
    public int grado(int id){
        int gradoSoFar = 0;
        Vertice nullV = crearVertice(id,"",0,0,0);
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
    @Override
    public LinkedList<Vertice> adyacentes(int id){
        Vertice nullV = crearVertice(id,"",0,0,0);
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
    @Override
    public LinkedList<Lado> incidentes(int id);{
        Vertice nullV = crearVertice(id,"",0,0,0);
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
    @Override
    public GrafoNoDirigido clone(){
        return new GrafoNoDirigido(HashSet<Arco> listaLados,HashSet<Vertice> gVertices,int numNodos,int numLados);
    }
    @Override
    public String toString(){
        return this.listaLados.toString();
    }
    @Override
    public Boolean cargarGrafo(String nombreArchivo){
        k = 0;
        BufferedReader Lector = new BufferedReader(
				new FileReader(nombreArchivo));
		
		String linea = Lector.readLine();
		do{
			cargarLista(linea, salida);
            linea = Lector.readLine();
            if ((k==0) && (linea.contains("N"))) {
                return false;
            } else if (k==1){
                this.numNodos = Integer.valueOf(linea).intValue();
            } else if (k==2){
                this.numLados = Integer.valueOf(linea).intValue();
            } else if (k<this.numNodos+3){
                linea = linea.split(" ");
                int id = Integer.valueOf(linea[0]).intValue();
                String nombre = linea[1];
                double x = Double.valueOf(linea[2]);
                double y = Double.valueOf(linea[3]);
                double p = Double.valueOf(linea[4]);
                Vertice v = crearVertice(id,nombre,x,y,p);
                this.gVertices.add(v);
            } else if (k<this.numNodos+3+this.numLados){
                linea = linea.split(" ");
                int idv = Integer.valueOf(linea[0]).intValue();
                int idw = Integer.valueOf(linea[1]).intValue();
                int tipo = Integer.valueOf(linea[2]).intValue();
                double p = Double.valueOf(linea[3]);
                Vertice v = obtenerVertice(idv);
                Vertice w = obtenerVertice(idw);
                listaLados.add(crearArco(u,w,tipo,p));
            }
            k += 1;
        }while(linea != null);
        return true;
    }

    public GrafoDirigido crearGrafoDirigido(){
        return new GrafoDirigido();
    }
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

    public Boolean agregarArco(Vertice u, Vertice v, int tipo, double p){
        Arco nueva = crearArco(u,v,tipo,p);
        if (listaLados.contains(nueva)){
            return false;
        }
        listaLados.add(nueva);
        return true;
    }

    public Boolean estaArco(int u, int v, int tipo){
        Vertice nullU = crearVertice(u,"",0,0,0);
        Vertice nullV = crearVertice(v,"",0,0,0);
        Arco uv = crearArco(nullU,nullV,tipo,0);
        return this.listaLados.contains(uv);
    } 

    public Boolean eliminarArco(int v, int u, int tipo){
        Vertice nullV = crearVertice(v.intValue(),"",0,0,0);
        Vertice nullW = crearVertice(u.intValue(),"",0,0,0);
        Arista nullA = crearArista(nullV,nullW,tipo,0);
        if (this.listaLados.contains(nullA)){
            this.listaLados.remove(nullA);
            return true;
        }
        return false;
    }

    public Arco obtenerArco(int v, int u, int tipo){
        Vertice nullV = crearVertice(v.intValue(),"",0,0,0);
        Vertice nullW = crearVertice(u.intValue(),"",0,0,0);
        Arista nullA = crearArista(nullV,nullW,tipo,0);
        Iterator<Arco> arco = listaLados.iterator();
        while(arco.hasNext()){
            Arco sig = arco.next();
            if (sig.equals(nullA)){
                return sig;
            }
        }
    }

    public int gradoInterior(int v){
        int gradoSoFar = 0;
        Vertice nullV = crearVertice(id,"",0,0,0);
        Iterator<Arco> nextLado = this.listaLados.iterator();
        while(nextLado.hasNext()){
            Arco sig = nextLado.next();
            if (sig.getExtremoFinal().equals(nullV) ){
                gradoSoFar += 1;
            }
        }
        return gradoSoFar;
    }

    public int gradoExterior(int v){
        int gradoSoFar = 0;
        Vertice nullV = crearVertice(id,"",0,0,0);
        Iterator<Arco> nextLado = this.listaLados.iterator();
        while(nextLado.hasNext()){
            Arco sig = nextLado.next();
            if (sig.getExtremoInicial().equals(nullV) ){
                gradoSoFar += 1;
            }
        }
        return gradoSoFar;
    }

    public LinkedList<Vertice> sucesores(int id){
        Vertice nullV = crearVertice(id,"",0,0,0);
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

    public LinkedList<Vertice> predecesores(int id){
        Vertice nullV = crearVertice(id,"",0,0,0);
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
}