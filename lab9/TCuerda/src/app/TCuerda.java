package app;

public class TCuerda {
    public static void main(String[] args) throws Exception {
        for (int i=0;i<args.length;i++){
        Grafo g = new Grafo(args[i]);
        int[] sol = new int[2];
        sol = g.solucion();
        //System.out.println(g);
        System.out.println("La respuesta es: ["+sol[0]+", "+sol[1]+"]");
        }
        
    }
}