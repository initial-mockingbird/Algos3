import java.util.*;

public class DFS extends Grafo {

    LinkedList<Arco> arcos;

    DFS() {
        super();
        arcos = new LinkedList<Arco>();
    }
    
    public void performDFS(Integer v){
        Camino raiz = new Camino(v);
        LinkedList<Camino> inicio = new LinkedList<Camino>();
        inicio.add(raiz);
        this.performDFS(inicio);
    }

    public void performDFS(LinkedList<Camino> caminos){
        Iterator<Camino> parada = caminos.iterator();
        int k = 0;
        // si todos los caminos estan cerrados terminamos.
        while(parada.hasNext()){
            if (parada.next().is_open()){
                k+=1;
            }
        }
        if (k==0){
            return;
        }
       Iterator<Camino> almost_reverse = caminos.descendingIterator();
       LinkedList<Camino> middleMan = new LinkedList<Camino>();
       while(almost_reverse.hasNext()){
        middleMan.add(almost_reverse.next());
       }
       Iterator<Camino> reverse = middleMan.listIterator(0);
       // iteramos desde el vertice mas actual hasta el mas viejo.
       while(reverse.hasNext()){
           Camino siguiente = reverse.next();
           // lo cerramos
           siguiente.close();
           int inicio = siguiente.get(siguiente.size()-2); 
           int fin = siguiente.get_last();
           arcos.add(new Arco(inicio,fin,0));
           // calculamos los sucesores del cada vertice.
           HashSet<Integer> sucesores = new HashSet<Integer> (super.get_neighbours(siguiente.get_last()));
           Iterator<Integer> succ = sucesores.iterator();
           LinkedList<Camino> candidatos = new LinkedList<Camino>();
           // Mientras hayan sucesores, expanda la cadena, e insertela.
           while(succ.hasNext()){
               Camino post_chain = new Camino(siguiente.get_camino());
               post_chain.expand(succ.next());
               candidatos.add(post_chain);
           }
           // Procedemos a la rutina de eliminacion.
           Iterator<Camino> eliminacion = candidatos.listIterator(0);
           while(eliminacion.hasNext()){
               
           }

       }

    }


}