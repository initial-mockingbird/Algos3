package app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
public class Grafo {
    int n_People;
    int[] pesos;

    /**
     * Lectura Estandar de datos.
     * @param archivo Archivo a leer
     * @throws FileNotFoundException
     * @throws IOException
     */
    Grafo(String archivo)
    throws FileNotFoundException, IOException
    {
        BufferedReader in = new BufferedReader(new FileReader(archivo));
        String line = in.readLine();
        n_People = Integer.valueOf(line);
        int i =0;
        pesos = new int[n_People];
        while(i < n_People){
            line = in.readLine();
            pesos[i] = Integer.valueOf(line);
            i += 1;
        }

        in.close();
    }

    /**
     * Funcion que usa el usuario para solventar el problema.
     * @return
     */
    public int[] solucion(){
        int[] sol = new int[2];
        int[] data = new int[Math.floorDiv(this.n_People, 2)];
        int i =0;
        int index = 0;
        sol[0] = 0;
        sol[1] = -9999999;
        sol = backtracking(index, data, i, sol);
        Arrays.sort(sol); 
        return sol;
    }


    /**
     * Dado que el problema se reduce a encontrar todos los subconjuntos de tamano
     * 2 del conjunto original (llamemoslo S), y retornar el que cumpla la propiedad: 
     * que la diferencia en valor absoluto de ambos elementos sea minima en el conjunto.
     * @param r (int): Tamano del subconjunto, en este problema en especifico seria 2.
     * @param index (int): Indica en que posicion del arreglo estamos 
     * @param data (int[]): indica la tupla que estamos probando.
     * @param i (int): indica el prox valor a insertar en la tupla.
     * @param sol (int[]): indica una posible solucion al problema
     * @return (int[]): solucion al problema.
     */
    private int[] backtracking(int index, int[] data, int i,int[] sol){

        if (index==(Math.floorDiv(this.n_People, 2))){
            return parada_Peso(index, data, i, sol);
        }

        while(i<this.n_People){
            data[index] = i;
            sol = backtracking(index+1,data.clone(), i+1, sol);
            i += 1;
        }
        return sol;
    }

    /**
     * Funcion auxiliar que determina cual solucion es mejor. Comparte parametros con 
     * backtracking por simplicidad.
     * @param index
     * @param data
     * @param i
     * @param sol
     * @return
     */
    private int[] parada_Peso(int index, int[] data, int i,int[] sol){
        int[] posSol = new int[2];
        posSol[0] = 0;
        posSol[1] = 0;
            for (int j=0;j<this.n_People;j++){
                int pos = Arrays.binarySearch(data, j);
                if (pos>-1){
                    posSol[0] += this.pesos[j];
                }
                else {
                    posSol[1] += this.pesos[j];
                }
            }

            if (Math.abs(posSol[0]-posSol[1])  < Math.abs(sol[0]-sol[1])){
                return posSol;
            }
            else{
                return sol;
            }
    }


    @Override
    public String toString(){
        String aux = "[";
        for (int i=0;i<this.n_People-1;i++){
            aux += String.valueOf(this.pesos[i])+", ";
        }
        aux += String.valueOf(this.pesos[this.n_People-1]) + "]";

        return "Numero de personas: "+n_People+"\n Pesos: \n"+  aux;
    }

}

