package app;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Math;

public interface GrafoPrime {

    /**
     * Funcion auxiliar para obtener los sucesores de un vertice.
     * @param arista Conjunto que posee los lados del grafo.
     * @param v Vertice al cual se le obtienen los sucesores
     * @return HashSet<VerticePrime> resuelvan Conjunto de sucesores de v.
     */
    static HashSet<VerticePrime> succ(HashSet<LadoWrap> arista, VerticePrime v){
        Iterator<LadoWrap> i = arista.iterator();
        LadoWrap e;
        HashSet<VerticePrime> succs = new HashSet<VerticePrime>();
        boolean isDirigido = arista.iterator().next().esDirigido();
        while (i.hasNext()) {
            e = i.next();
            if (e.get_inicioPrime().equals(v)) {
                succs.add(e.get_finalPrime());
            }
            if ((e.get_finalPrime().equals(v)) && (!isDirigido)){
                succs.add(e.get_inicioPrime());
            }
        }
        return succs;
    }

    /**
     * Funcion auxiliar para obtener costos
     * @param v Vertice inicial del lado.
     * @param w Vertice Final del lado.
     * @return (double) Costo del lado.
     */
    static double costo(VerticePrime v, VerticePrime w,HashSet<LadoWrap> arista) {
        if (v.get_color().equals("")) {
            return (double) 0;
        }
        if (w.get_color().equals("")) {
            return (double) 0;
        }
        double res = 0;
        Iterator<LadoWrap> i = arista.iterator();
        while(i.hasNext()){
            res += i.next().get_costo(v, w);
        }
        return res;
    }

    /**
     * Notemos que si planteamos el multigrafo como un digrafo, en el cual los nuevos vertices van a ser de la forma (teorica)
     * (Vertice,color) y los nuevos arcos siguen las reglas definidas en la funcion result (en la separacion del producto cartesiano)
     * Entonces el grafo resultante es un digrafo con costos no negativos al cual le podemos aplicar A* facilmente.
     * @param vertices Conjunto de vertices
     * @param arista Conjunto de lados
     * @param inicio Nodo Dummy inicio
     * @param fin Nodo dummy final
     * @return Respuesta segun el formato pedido en el pdf.
     */
    static String A_Estrella(HashSet<VerticePrime> vertices, HashSet<LadoWrap> arista, VerticePrime inicio,
            VerticePrime fin) {

        HashMap<VerticePrime, Integer> vert2index = new HashMap<VerticePrime, Integer>(vertices.size() * 2);
        Iterator<VerticePrime> j;
        VerticePrime v;
        VerticePrime w;
        VerticePrime succ;
        HashSet<VerticePrime> succSet;
        double[] costo = new double[vertices.size()];
        double[] tiempo = new double[vertices.size()];
        VerticePrime[] p = new VerticePrime[vertices.size()];
        // Se inicializa el arreglo costo con inf
        Arrays.fill(costo, (double) 9999999);
        Arrays.fill(tiempo, (double) 9999999);
        String res = "";
        /*
         * Para poder usar un arreglo de costos y predecesores, necesitamos Mapear los
         * objetos a una posicion.
         */
        j = vertices.iterator();
        Integer i = 0;
        while (j.hasNext()) {
            vert2index.put(j.next(), i);
            i++;
        }

        // Se inicializan los valores del vertice inicial
        costo[vert2index.get(inicio)] = 0;
        p[vert2index.get(inicio)] = inicio;
        tiempo[vert2index.get(inicio)] = inicio.get_vertice().getPeso();
        // Se crea un priority queue de caminos Abiertos
        PriorityQueue<VerticePrime> queue = new PriorityQueue<VerticePrime>(vertices.size(),
                (VerticePrime x, VerticePrime y) -> {
                    return (int) Math.signum(costo[vert2index.get(x)] - costo[vert2index.get(y)]);
                });
        queue.add(inicio);
        HashSet<VerticePrime> cerrados = new HashSet<VerticePrime>();

        do{
            // Se saca el vertice de menor costo del priority queue.
            w = queue.poll();
            cerrados.add(w);
            if (w.equals(fin)){
                break;
            }

            // Iteramos sobre los sucesores.
            succSet = succ(arista, w);
            j = succSet.iterator();
            while(j.hasNext()){
                succ = j.next();
                if (!cerrados.contains(succ)){
                    costo[vert2index.get(succ)] = costo[vert2index.get(w)] + costo(w, succ,arista);
                    tiempo[vert2index.get(succ)] = tiempo[vert2index.get(w)] + costo(w, succ,arista) - GrafoPrime.heuristica(w, succ.get_vertice());
                    p[vert2index.get(succ)] = w;
                    queue.add(succ);
                }
            }

        }while(queue.size()>0); 


        w = p[vert2index.get(fin)];
        v = p[vert2index.get(w)];
        while (!v.equals(inicio)) {
            res = "Tome la linea: " + w.get_color() + " desde: " + v + " hasta: " + w + "\n" + res;
            w = p[vert2index.get(w)];
            v = p[vert2index.get(w)];
        }
        res += "Tiempo total: " + tiempo[vert2index.get(fin)] + "\n";
        return res;
    }

    /**
     * Funcion auxiliar para calcular el grafo inducido.
     * @param nombreArchivo
     * @return Conjunto de lineas operativas.
     * @throws IOException
     */
    static HashSet<String> leer_lineas(String nombreArchivo) throws IOException {
        
        HashSet<String> linea = new HashSet<String>();
        String sig;
        BufferedReader Lector = new BufferedReader(
            new FileReader(nombreArchivo));
        do{
            sig = Lector.readLine();
            linea.add(sig);
        }while(sig != null);
        Lector.close();
        return linea;
    }


    /**
     * Dada a la falta de informacion con respecto a los datos de la heuristica 
     * (Solo se proveen posiciones (x,y), sin velocidades aproximadas con respecto a 
     * los transportes, mientras que los tramos entre dos vertices estan dados en unidad
     * de tiempo). Decidimos tomar la heuristica mas generica posible, y aplicar distancia 
     * euclidiana a las posiciones. Por lo tanto, trabajamos bajo la posiblemente incorrecta
     * premisa de que todos los transportes viajan a la misma velocidad media: 1 unidad de distancia
     * por unidad de tiempo.
     * @param v Vertice inicial, envuelto en el wrapper por conveniencia.
     * @param w Vertice final, sin el wrapper por conveniencia
     * @return (double) heuristica para los dos vertices.
     */
    static double heuristica(VerticePrime v, Vertice w){
        double x0 = v.get_vertice().getX();
        double x1 = w.getX();
        double y0 = v.get_vertice().getY();
        double y1 = w.getY();
        double alCuadrado = Math.pow((x1-x0), 2) + Math.pow((y1-y0),2  );
        return Math.pow(alCuadrado, (double) 0.5);
    }
}