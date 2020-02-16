package Cliente;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.HashSet;
public class GrafoNoDirigido implements Grafo {
    protected HashSet<Arista> listaLados;
    protected HashSet<Vertice> gVertices;
    protected int numNodos;
    protected int numLados;
    
    GrafoNoDirigido(HashSet<Arista> nlistaLados,HashSet<Vertice> ngVertices,int nnumNodos,int nnumLados){
        this.listaLados = new HashSet<Arista>(nlistaLados);
        this.gVertices = new HashSet<Vertice>(ngVertices);
        this.numNodos =  nnumNodos;
        this.numLados = nnumLados;
    }


    public GrafoNoDirigido crearGrafoNoDirigido(){
        return new GrafoNoDirigido();
    }
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
    public Boolean agregarArista(Vertice u, Vertice v, int tipo, double p){
        Arista nueva = crearArista(u,v,tipo,p);
        if (listaLados.contains(nueva)){
            return false;
        }
        listaLados.add(nueva);
        return true;
    }
    public Boolean eliminarArista(String id){
        String middleMan = id.split(" ");
        Vertice nullV = crearVertice(Integer.valueOf(middleMan[0]).intValue(),"",0,0,0);
        Vertice nullW = crearVertice(Integer.valueOf(middleMan[1]).intValue(),"",0,0,0);
        Arista nullA = crearArista(nullV,nullW,Integer.valueOf(middleMan[2]),0);
        if (this.listaLados.contains(nullA)){
            this.listaLados.remove(nullA);
            return true;
        }
        return false;
    }
    public Boolean estaArista(int u, int v, int tipo){
        Vertice nullU = crearVertice(u,"",0,0,0);
        Vertice nullV = crearVertice(v,"",0,0,0);
        Arista uv = crearArista(nullU,nullV,tipo,0);
        return this.listaLados.contains(uv);
    } 

    public Arista obtenerArista(String id){
        String middleMan = id.split(" ");
        Vertice nullV = crearVertice(Integer.valueOf(middleMan[0]).intValue(),"",0,0,0);
        Vertice nullW = crearVertice(Integer.valueOf(middleMan[1]).intValue(),"",0,0,0);
        Arista nullA = crearArista(nullV,nullW,Integer.valueOf(middleMan[2]),0);
        Iterator<Arista> arista = listaLados.iterator();
        while(arista.hasNext()){
            Arista sig = arista.next();
            if (sig.equals(nullA)){
                return sig;
            }
        }
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
    @Override
    public LinkedList<Vertice> adyacentes(int id){
        Vertice nullV = crearVertice(id,"",0,0,0);
        HashSet<Vertice> ady = new HashSet<Vertice>();
        Iterator<Arista> nextLado = this.listaLados.iterator();
        while(nextLado.hasNext()){
            Arista sig = nextLado.next();
            if (sig.getExtremo1().equals(nullV)){
                ady.add(sig.getExtremo2);
            } else if (sig.getExtremo2().equals(nullV)){
                ady.add(sig.getExtremo1);
            }
        }
        return new LinkedList<Vertice>(ady);
    }
    @Override
    public LinkedList<Lado> incidentes(int id);{
        Vertice nullV = crearVertice(id,"",0,0,0);
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
    @Override
    public GrafoNoDirigido clone(){
        return new GrafoNoDirigido(HashSet<Arista> listaLados,HashSet<Vertice> gVertices,int numNodos,int numLados);
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
            if ((k==0) && (linea.contains("D"))) {
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
                listaLados.add(crearArista(u,w,tipo,p));
            }
            k += 1;
        }while(linea != null);
        return true;
    }
}