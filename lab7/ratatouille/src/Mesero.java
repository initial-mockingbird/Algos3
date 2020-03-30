import java.io.IOException;
import java.util.Arrays;
public class Mesero {
    public static void main(String[] args) 
            throws IOException 
    {
        Grafo g = new Grafo ();
        g.Lector(args[0]);
        g.shortestPath(Arrays.copyOfRange(args, 1, args.length));
    }
}