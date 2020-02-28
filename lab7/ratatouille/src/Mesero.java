import java.io.IOException;

public class Mesero {
    public static void main(String[] args) 
            throws IOException 
    {
        Grafo g = new Grafo ();
        g.Lector(args[0]);
        System.out.println(g.toString());
    }
}