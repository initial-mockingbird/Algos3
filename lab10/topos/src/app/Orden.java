package app;

public class Orden {
    public static void main(String[] args) throws Exception {
        Grafo g = new Grafo();
        g.cargarGrafo(args[0]);
        System.out.println(g.Dependencies());
    }
}