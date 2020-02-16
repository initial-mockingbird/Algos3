package Cliente;

import java.util.LinkedList;

interface Grafo {
    public Boolean cargarGrafo(String archivo);
    public int numeroDeVertices();
    public int numeroDeLados();
    public Boolean agregarVertice(Vertice v);
    public Vertice obtenerVertice(int id);
    public Boolean estaVertice( int id);
    public Boolean eliminarVertice( int id);
    public LinkedList<Vertice> vertices();
    public LinkedList<Lado> lados();
    public int grado(int id);
    public LinkedList<Vertice> adyacentes(int id);
    public LinkedList<Lado> incidentes(int id);
    public Grafo clone ();
    public String toString();
}