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

public class GrafoDirigidoPrime {

    /**
     * 
     * @param g: Grafo al cual calcular el camino de costo minimo.
     * @param inicio: Vertice inial.
     * @param fin: Vertice final
     * @param archivo_lineas: archivo que contiene una lista de lineas operativas.
     * @return res: el resultado del problema segun lo pedido en el pdf.
     * @throws IOException
     */
    public static String result(GrafoDirigido g, Vertice inicio, Vertice fin, String archivo_lineas)
            throws IOException {
        HashSet<Arco> listaLados = g.arcos();
        Iterator<Arco> i = listaLados.iterator();
        HashSet<VerticePrime> nVertices = new HashSet<VerticePrime>();
        VerticePrime dummyFuente = new VerticePrime(inicio, "");
        VerticePrime dummySumidero = new VerticePrime(fin, "");
        String res = "";
        HashSet<String> lineas;

        /*
        * Construyendo los nuevos vertices del grafo.
        */
        while (i.hasNext()) {
            Arco prox = i.next();
            nVertices.add(new VerticePrime(prox.getExtremoInicial(), prox.getTipo()));
            nVertices.add(new VerticePrime(prox.getExtremoFinal(), prox.getTipo()));
        }

        HashSet<ArcoPrime> nlistaLados = new HashSet<ArcoPrime>();
        VerticePrime[] aux = nVertices.toArray(new VerticePrime[nVertices.size()]);
        ArcoPrime toInsert;

        /* Creando un producto cartesiano con los nuevos vertices*/
        for (int j = 0; j < aux.length; j++) {
            for (int k = 0; k < aux.length; k++) {
                if (aux[j].get_vertice().equals(aux[k].get_vertice())) {
                    nlistaLados.add(new ArcoPrime(aux[j], aux[k], (double) 1));
                } else {
                    nlistaLados.add(new ArcoPrime(aux[j], aux[k], (double) 0));
                }
            }

            /* Uniendo las fuentes y sumideros */
            /* fuentes */
            if (aux[j].get_vertice().equals(inicio)) {
                toInsert = new ArcoPrime(dummyFuente, aux[j], (double) 0);
                nlistaLados.add(toInsert);
            }
            /* sumideros */
            if (aux[j].get_vertice().equals(fin)) {
                toInsert = new ArcoPrime(aux[j], dummySumidero, (double) 0);
                nlistaLados.add(toInsert);
            }
        }
        nVertices.add(dummyFuente);
        nVertices.add(dummySumidero);

        /*
         * Los arcos que sobreviven son aquellos que cumplan alguna de estas
         * propiedades: 1) Los vertices sean los mismos (ambos provienen de la misma
         * estacion). 2) El par (V1,V2) pertenece a nuestro grafo original y ademas,
         * ambos elementos poseen el mismo color. 3) Alguno de los vertices sea la
         * fuente o sumidero del problema.
         */
        nlistaLados.removeIf(p -> {
            if (  (p.get_inicio().equals(p.get_final())
                    && (!p.get_inicioPrime().get_color().equals(p.get_finalPrime().get_color())))
                    || (p.get_inicioPrime().get_color().equals(p.get_finalPrime().get_color()) && 
                        listaLados.contains(new Arco(p.get_inicio(), p.get_final(), p.get_inicioPrime().get_color(), 0.0)))
                    || (p.get_inicioPrime().equals(dummyFuente) || p.get_finalPrime().equals(dummySumidero))) {
                return false;
            } else {
                return true;
            }
        });

        res += "Para el grafo completo tenemos:\n" + dijkstra(nVertices, nlistaLados, dummyFuente, dummySumidero);

        lineas = leer_lineas(archivo_lineas);

        nlistaLados.removeIf(p -> {
            if (lineas.contains(p.get_inicioPrime().get_color())) {
                return false;
            }

            if (p.get_inicioPrime().get_color().equals("") || p.get_finalPrime().get_color().equals("")){
                return false;
            }

            return true;
        });

        res += "Para el grafo inducido tenemos:\n" + dijkstra(nVertices, nlistaLados, dummyFuente, dummySumidero);
        return res;
    }

    /**
     * Funcion auxiliar para obtener los sucesores de un vertice.
     * @param arista Conjunto que posee as aristas del grafo.
     * @param v Vertice al cual se le obtienen los sucesores
     * @return HashSet<VerticePrime> resuelvan Conjunto de sucesores de v.
     */
    private static HashSet<VerticePrime> succ(HashSet<ArcoPrime> arista, VerticePrime v) {
        Iterator<ArcoPrime> i = arista.iterator();
        ArcoPrime e;
        HashSet<VerticePrime> succs = new HashSet<VerticePrime>();
        while (i.hasNext()) {
            e = i.next();
            if (e.get_inicioPrime().equals(v)) {
                succs.add(e.get_finalPrime());
            }
        }
        return succs;
    }

    /**
     * Funcion auxiliar para obtener costos
     * @param v Vertice inicial de la arista
     * @param w Vertice Final de la arista
     * @return (double) Costo de la arista.
     */
    private static double costo(VerticePrime v, VerticePrime w) {
        if (v.get_color().equals(w.get_color())) {
            return (double) 0;
        }
        if (v.get_color().equals("")) {
            return (double) 0;
        }
        if (w.get_color().equals("")) {
            return (double) 0;
        } else {
            return (double) 1;
        }
    }

    /**
     * Notemos que si planteamos el multigrafo como un digrafo, en el cual los nuevos vertices van a ser de la forma (teorica)
     * (Vertice,color) y los nuevos arcos siguen las reglas definidas en la funcion result (en la separacion del producto cartesiano)
     * Entonces el grafo resultante es un digrafo con costos no negativos al cual le podemos aplicar dijkstra facilmente.
     * @param vertices Conjunto de vertices
     * @param arista Conjunto de aristas
     * @param inicio Nodo Dummy inicio
     * @param fin Nodo dummy final
     * @return Respuesta segun el formato pedido en el pdf.
     */
    private static String dijkstra(HashSet<VerticePrime> vertices, HashSet<ArcoPrime> arista, VerticePrime inicio,
            VerticePrime fin) {

        HashMap<VerticePrime, Integer> vert2index = new HashMap<VerticePrime, Integer>(vertices.size() * 2);
        Iterator<VerticePrime> j;
        VerticePrime v;
        VerticePrime w;
        VerticePrime succ;
        HashSet<VerticePrime> succSet;
        double[] costo = new double[vertices.size()];
        VerticePrime[] p = new VerticePrime[vertices.size()];
        Arrays.fill(costo, (double) 9999999);
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

        costo[vert2index.get(inicio)] = 0;
        p[vert2index.get(inicio)] = inicio;

        PriorityQueue<VerticePrime> queue = new PriorityQueue<VerticePrime>(vertices.size(),
                (VerticePrime x, VerticePrime y) -> {
                    return (int) Math.signum(costo[vert2index.get(x)] - costo[vert2index.get(y)]);
                });
        queue.addAll(vertices);

        while (queue.size() > 0) {
            w = queue.poll();
            succSet = succ(arista, w);
            j = succSet.iterator();
            while (j.hasNext()) {
                succ = j.next();
                if (costo[vert2index.get(succ)] > (costo[vert2index.get(w)] + costo(w, succ))) {
                    costo[vert2index.get(succ)] = costo[vert2index.get(w)] + costo(w, succ);
                    p[vert2index.get(succ)] = w;
                    queue.remove(succ);
                    queue.add(succ);
                }
            }

        }

        if (!(costo[vert2index.get(fin)] < 9999)) {
            return "No hay forma de llegar :(\n";
        }

        w = p[vert2index.get(fin)];
        v = p[vert2index.get(w)];
        while (!v.equals(inicio)) {

            res = "Tome la linea: " + w.get_color() + " desde: " + v + " hasta: " + w + "\n" + res;
            w = p[vert2index.get(w)];
            v = p[vert2index.get(w)];
        }
        res += "Numero de transbordos: " + costo[vert2index.get(fin)] + "\n";
        return res;

    }

    /**
     * Funcion auxiliar para calcular el grafo inducido.
     * @param nombreArchivo
     * @return Conjunto de lineas operativas.
     * @throws IOException
     */
    private static HashSet<String> leer_lineas(String nombreArchivo) throws IOException {
        
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





}