package app;

import java.util.HashSet;
import java.util.Iterator;
import java.io.IOException;

public class GrafoDirigidoPrime implements GrafoPrime {

    /**
     * 
     * @param g: Grafo al cual calcular el camino de costo minimo.
     * @param inicio: Vertice inial.
     * @param fin: Vertice final
     * @param archivo_lineas: archivo que contiene una lista de lineas operativas.
     * @return res: el resultado del problema segun lo pedido en el pdf.
     * @throws IOException
     */
    public static String result(Grafo g, Vertice inicio, Vertice fin, String archivo_lineas)
            throws IOException {
        HashSet<Arco> listaLados = ((GrafoDirigido)g).arcos();
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

        HashSet<LadoWrap> nlistaLados = new HashSet<LadoWrap>();
        VerticePrime[] aux = nVertices.toArray(new VerticePrime[nVertices.size()]);
        ArcoPrime toInsert;
        /* Creando un producto cartesiano con los nuevos vertices*/
        for (int j = 0; j < aux.length; j++) {
            for (int k = 0; k < aux.length; k++) {
                if (aux[j].get_vertice().equals(aux[k].get_vertice())) {
                   toInsert = (new ArcoPrime(aux[j], aux[k], aux[j].get_vertice().getPeso() + GrafoPrime.heuristica(aux[j],fin) ));
                } else {
                    toInsert = (new ArcoPrime(aux[j], aux[k], (double) 0));
                }
                nlistaLados.add(new LadoWrap(toInsert, true));

            }

            /* Uniendo las fuentes y sumideros */
            /* fuentes */
            if (aux[j].get_vertice().equals(inicio)) {
                toInsert = new ArcoPrime(dummyFuente, aux[j], (double) 0);
                nlistaLados.add(new LadoWrap(toInsert, true));
            }
            /* sumideros */
            if (aux[j].get_vertice().equals(fin)) {
                toInsert = new ArcoPrime(aux[j], dummySumidero, (double) 0);
                nlistaLados.add(new LadoWrap(toInsert, true));
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

        /*
        * Seteando los costos de los arcos.
        */
        nlistaLados.forEach(l -> {  if(!l.get_inicio().equals(l.get_final())){l.set_costo(costo_arco((GrafoDirigido) g,l)+ GrafoPrime.heuristica(l.get_inicioPrime() ,l.get_final() ) )  ; } } );

        res += "Para el grafo completo tenemos:\n" + GrafoPrime.A_Estrella(nVertices, nlistaLados, dummyFuente, dummySumidero);

        lineas = GrafoPrime.leer_lineas(archivo_lineas);

        nlistaLados.removeIf(p -> {
            if (lineas.contains(p.get_inicioPrime().get_color())) {
                return false;
            }

            if (p.get_inicioPrime().get_color().equals("") || p.get_finalPrime().get_color().equals("")){
                return false;
            }

            return true;
        });

        res += "Para el grafo inducido tenemos:\n" + GrafoPrime.A_Estrella(nVertices, nlistaLados, dummyFuente, dummySumidero);
        return res;
    }

    /**
     * Metodo auxiliar para obtener el costo de un arco.
     * @param g grafo al cual se refiere.
     * @param l arco en el wrap adecuado.
     * @return (double) costo del arco correspondiente.
     */
    public static double costo_arco(GrafoDirigido g,LadoWrap l){
        if (!l.get_inicio().equals(l.get_final()))
        { 
            return g.obtenerArco(l.get_inicio().getId(), l.get_final().getId(), l.get_inicioPrime().get_color() ).getPeso();
        }
        return 0;
    }

}