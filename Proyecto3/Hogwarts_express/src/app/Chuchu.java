package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Chuchu {

    /**
     * Clase auxiliar que mueve el funcionamiento del programa fuera del main.
     * @param args Los argumentos segun son mostrados en el pdf
     * @return String res: respuesta segun el formato pedido en el pdf.
     * @throws IOException
     */
    public static String solver(String[] args) throws IOException {
        String mapa = args[0];
        String lineas = args[1];
        BufferedReader lector = new BufferedReader(new FileReader(mapa));
        String direccion = lector.readLine();
        if (direccion.equals("n")){
            lector.close();
            GrafoNoDirigido h = new GrafoNoDirigido();
            h.cargarGrafo(mapa);
            Vertice pInicio = h.obtener_nombre(args[2]);
            Vertice pFin = h.obtener_nombre(args[3]);
            return GrafoNoDirigidoPrime.result(h, pInicio, pFin,lineas);
        }
            lector.close();
            GrafoDirigido g = new GrafoDirigido();
            g.cargarGrafo(mapa);
            Vertice pInicio = g.obtener_nombre(args[2]);
            Vertice pFin = g.obtener_nombre(args[3]);
            return GrafoDirigidoPrime.result(g, pInicio, pFin,lineas);
    }

}