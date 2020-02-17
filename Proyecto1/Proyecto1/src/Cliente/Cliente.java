package Cliente;

import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
import java.nio.*;

/**Estudiantes:
 *	 Daniel Pinto 
 *	 Daniela Ramirez
 * --------- PROYECTO 1 LABORATORIO DE ALGORITMOS Y ESTRUCTURAS III ----------------- 
 */

public class Cliente{

	public static void main(String[] args) throws IOException {

            int n;                                                          // Corresponde a la cantidad de vértices en el grafo
            int m;                                                          // Corresponde a la cantidad de aristas en el grafo
            String o;                                                       // Correponde de orientación que va a ser el grafo
            
            BufferedReader Lector = new BufferedReader(
                new FileReader(args[0]));
            
            String linea = Lector.readLine();                               // Lee la primera línea que corresponde a la orientación del grafo
            o =  linea.trim();  
            linea = Lector.readLine();                                      // Lee la segunda línea que corresponde al número de vértices
            n =  Integer.parseInt(linea);  
            linea = Lector.readLine();                                      // Lee la tercera línea que corresponde al número de lados
            m =  Integer.parseInt(linea);

            // Ahora dependiendo del tipo de orietación, creamos un grafo Dirigido o un grafo no Dirigido
            if(o.equals("D")) {

                GrafoDirigido grafo = null;
                Scanner reader = new Scanner(System.in);

                /* Creamos, construimos y cargamos el grafo */
                grafo = new GrafoDirigido();                                                       //Inicializa Grafo no Dirigido
                grafo.crearGrafoDirigido();                                                          //Llama al constructor de Grafo no Dirigido
                grafo.cargarGrafo(args[0]);
                
                
                // Menu de Grafo Dirigido
                while(true){
                    System.out.println(
                        "Menu de opciones sobre el grafo Dirigido: \n" +
                        "Presione q (seguido de \"Enter\") para saber el numero de Vertices del grafo \n" +
                        "Presione w (seguido de \"Enter\") para saber el numero de Arcos del grafo \n" +
                        "Presione e (seguido de \"Enter\") para agregar un nuevo Vertice al grafo  \n" +
                        "Presione r (seguido de \"Enter\") para agregar un nuevo Arco al grafo  \n" +
                        "Presione t (seguido de \"Enter\") para obtener un Vertice del grafo \n" +
                        "Presione y (seguido de \"Enter\") para obtener un Arco del grafo \n" +
                        "Presione u (seguido de \"Enter\") para saber si un Vertice esta en el grafo \n" +
                        "Presione i (seguido de \"Enter\") para saber si un Arco esta en el grafo \n" +
                        "Presione o (seguido de \"Enter\") para eliminar un Vertice del grafo \n" +
                        "Presione p (seguido de \"Enter\") para eliminar un Arco del grafo \n" +
                        "Presione a (seguido de \"Enter\") para imprimir una lista de los Arcos en el grafo \n" +
                        "Presione s (seguido de \"Enter\") para imprimir la lista de Vertices del grafo \n" +
                        "Presione d (seguido de \"Enter\") para imprimir el grado de un Vertice del grafo \n" +
                        "Presione f (seguido de \"Enter\") para imprimir el grado interno de un Vertice del grafo \n" +
                        "Presione g (seguido de \"Enter\") para imprimir el grado externo de un Vertice del grafo \n" +
                        "Presione h (seguido de \"Enter\") para obtener los Vertices adyacentes a un Vertice dado \n" +
                        "Presione j (seguido de \"Enter\") para obtener la lista de sucesores de un Vertice dado \n" +
                        "Presione k (seguido de \"Enter\") para obtener la lista de predecesores de un Vertice \n" +
                        "Presione l (seguido de \"Enter\") para obtener la lista de Arcos incidentes a un Vertice dado \n" +
                        "Presione z (seguido de \"Enter\") para clonar el grafo a un grafo nuevo \n" +
                        "Presione x (seguido de \"Enter\") para imprimir una representacion del grafo \n" +
                        "Presione n (seguido de \"Enter\") para salir");

                    String c = reader.next();
                    System.out.println(" ");
                    if (c.equals("q")) {
                        System.out.println("El numero de Vertices es: " + grafo.numeroDeVertices()+"\n");
                    } else if (c.equals("w")) {
                        System.out.println("El numero de Arco es: " + grafo.numeroDeLados()+"\n");
                    } else if (c.equals("e")) {
                        Boolean Result = false;
                        int Identificador;
                        String Nombre;
                        Double Coord_x;
                        Double Coord_y;
                        Double peso;

                        System.out.println("Escriba el Identificador del nuevo vertice: ");
                        Identificador = Integer.valueOf(reader.next()).intValue();
                        System.out.println("Escriba el Nombre del nuevo Vertice: ");
                        Nombre = reader.next();
                        System.out.println("Escriba la coordenada x del nuevo Vertice: ");
                        Coord_x = Double.valueOf(reader.next());
                        System.out.println("Escriba la coordenada y del nuevo Vertice: ");
                        Coord_y = Double.valueOf(reader.next());
                        System.out.println("Escriba el peso del nuevo Vertice");
                        peso = Double.valueOf(reader.next());

                        /* Crearemos un vertice dependiendo de los datos proporcionados en el archivo .txt
                        */
                            Vertice Ve = new Vertice(Identificador, Nombre, Coord_x, Coord_y, peso);
                            Result = grafo.agregarVertice(Ve);
                            
                            if(Result){
                                System.out.println("Se agrego el Vertice al grafo.\n");
                            }
                            else{
                                System.out.println("El Vertice ya existia en el grafo, por ende no se agrego nada.\n");
                            }
                        
                    } else if (c.equals("r")) {
                        Boolean Result = false;
                        int Vi;
                        int Vf;
                        int Tipo;
                        Double peso;

                        System.out.println("Escriba el Vertice inicial del Arco: ");
                        Vi = Integer.valueOf(reader.next()).intValue();
                        System.out.println("Escriba el Vertice final del Arco: ");
                        Vf = Integer.valueOf(reader.next()).intValue();
                        System.out.println("Escriba el tipo del nuevo Arco: ");
                        Tipo = Integer.valueOf(reader.next()).intValue();
                        System.out.println("Escriba el peso del nuevo Arco: ");
                        peso = Double.valueOf(reader.next());

                        /* Crearemos un arco dependiendo de los datos proporcionados en el archivo .txt
                        */

                            Arco Ar = new Arco(grafo.obtenerVertice(Vi), grafo.obtenerVertice(Vf), Tipo, peso);
                            Result = grafo.agregarArco(Ar);
                            
                            
                            if(Result){
                                System.out.println("Se agrego El Arco al grafo.\n");
                            }
                            else{
                                System.out.println("El Arco ya existia en el grafo, por ende no se agrego nada.\n");
                            }

                    } else if (c.equals("t")) {
                        int Identificador;
                        System.out.println("Ingrese el Identificador del Vertice a buscar: ");
                        Identificador = Integer.valueOf(reader.next()).intValue();
                            System.out.println(grafo.obtenerVertice(Identificador).toString()+"\n");
  
                    } else if (c.equals("y")) {
                        int Vi;
                        int Vf;
                        int Tipo;
                        System.out.println("Ingrese el vertice inicial: ");
                        Vi = Integer.valueOf(reader.next()).intValue();
                        System.out.println("Ingrese el vertice final: ");
                        Vf = Integer.valueOf(reader.next()).intValue();
                        System.out.println("Ingrese el tipo de arco: ");
                        Tipo = Integer.valueOf(reader.next()).intValue();
                            System.out.println(grafo.obtenerArco(Vi, Vf, Tipo).toString()+"\n");
                    } else if (c.equals("u")) {
                        int Identificador;
                        System.out.println("Ingrese el Identificador del Vertice a buscar: ");
                        Identificador = Integer.valueOf(reader.next()).intValue();
                        if(grafo.estaVertice(Identificador)){
                            System.out.println("El Vertice si esta en el Grafo.\n");
                        }
                        else{
                            System.out.println("El Vertice no esta en el grafo.\n");
                        }
                    }else if (c.equals("i")){
                        int Vi;
                        int Vf;
                        int tipo;
                        System.out.println("Ingrese el Vertice inicial del Arco: ");
                        Vi = Integer.valueOf(reader.next()).intValue();
                        System.out.println("Ingrese el Vertice final del Arco: ");
                        Vf = Integer.valueOf(reader.next()).intValue();
                        System.out.println("Ingrese el tipo del Arco: ");
                        tipo = Integer.valueOf(reader.next()).intValue();
                        if(grafo.estaArco(Vi, Vf, tipo)){
                            System.out.println("El Arco si esta en el Grafo.\n");
                        }
                        else{
                            System.out.println("El Arco no esta en el grafo.\n");
                        }
                    }else if (c.equals("o")){
                        Boolean Result;
                        int Identificador;
                        System.out.println("Ingrese el Identificador de el Vertice a eliminar: ");
                        Identificador = Integer.valueOf(reader.next()).intValue();
                        Result = grafo.eliminarVertice(Identificador);
                        if(Result){
                            System.out.println("El vertice se elimino del grafo.\n");
                        }
                        else{
                            System.out.println("El vertice no existia en el grafo, por ende no se elimino nada.\n");
                        }
                    }else if (c.equals("p")){
                        Boolean Result;
                        int Vi;
                        int Vf;
                        int tipo;
                        System.out.println("Ingrese el vertice inicial del arco a eliminar: ");
                        Vi = Integer.valueOf(reader.next()).intValue();
                        System.out.println("Ingrese el vertice final del arco a eliminar: ");
                        Vf = Integer.valueOf(reader.next()).intValue();
                        System.out.println("Ingrese el tipo del arco a eliminar: ");
                        tipo = Integer.valueOf(reader.next()).intValue();
                        Result = grafo.eliminarArco(Vi, Vf, tipo);
                        if(Result){
                            System.out.println("El Arco se elimino del grafo.\n");
                        }
                        else{
                            System.out.println("El Arco no existia en el grafo, por ende no se elimino nada.\n");
                        }
                    }else if (c.equals("a")){
                        System.out.println("Arcos en el grafo: ");
                        
                        LinkedList<Lado> Arr = grafo.lados();
                        for(int i=0; i<Arr.size(); i++){
                            System.out.println(Arr.get(i).toString());
                        
                        System.out.println("\n");
                    } if (c.equals("s")){
                        System.out.println("Vertices en el grafo: ");
                        
                        LinkedList<Vertice> Arr1 = grafo.vertices();
                        for(int i=0; i<Arr1.size(); i++){
                            System.out.println(Arr1.get(i).toString());
                        
                        System.out.println("\n");
                    } if (c.equals("d")){
                        int Identificador;
                        System.out.println("Ingrese el Vertice al cual se le desea conocer el Grado: ");
                        Identificador = Integer.valueOf(reader.next()).intValue();
                            System.out.println("El grado del Vertice es: "+grafo.grado(Identificador)+"\n");

                    }else if(c.equals("f")){
                        int Vi;
                        int tipo;
                        System.out.println("Ingrese el vertice inicial al cual se le desea conocer el Grado interior: ");
                        Vi = Integer.valueOf(reader.next()).intValue();
                            System.out.println("El grado interior del Vertice es: "+grafo.gradoInterior(Vi)+"\n");

                    }else if(c.equals("g")){
                        int Vi;
                        System.out.println("Ingrese el vertice inicial al cual se le desea conocer el Grado exterior: ");
                        Vi = Integer.valueOf(reader.next()).intValue();
                            System.out.println("El grado exterior del Vertice es: "+grafo.gradoExterior(Vi)+"\n");

                    }else if (c.equals("h")){
                        int Identificador;
                        System.out.println("Ingrese el Vertice al cual se le desea conocer sus vertices adyacentes: ");
                        Identificador = Integer.valueOf(reader.next()).intValue();
                            LinkedList<Vertice> Arr2 = grafo.adyacentes(Identificador);
                            for(int i=0; i<Arr2.size(); i++){
                                System.out.println(Arr2.get(i).toString());
                            }
                                                    
                            System.out.println("\n");

                    }else if(c.equals("j")){
                        int Identificador;
                        System.out.println("Ingrese el Vertice al cual se le desea conocer sus Sucesores: ");
                        Identificador = Integer.valueOf(reader.next()).intValue();
                            LinkedList<Vertice> Arr3 = grafo.sucesores(Identificador);
                            for(int i=0; i<Arr3.size(); i++){
                                System.out.println(Arr3.get(i).toString());
                            }
                            
                            System.out.println("\n");
                    }else if(c.equals("k")){
                        int Identificador;
                        System.out.println("Ingrese el Vertice al cual se le desea conocer sus Predecesores: ");
                        Identificador = Integer.valueOf(reader.next()).intValue();
                            LinkedList<Vertice> Arr4 = grafo.predecesores(Identificador);
                            for(int i=0; i<Arr4.size(); i++){
                                System.out.println(Arr4.get(i).toString());
                            }
                           
                            System.out.println("\n");
                    }else if (c.equals("l")){
                        int Identificador;
                        System.out.println("Ingrese el Vertice al cual se le desea conocer sus Arcos Incidentes: ");
                        Identificador = Integer.valueOf(reader.next()).intValue();
                            LinkedList<Lado> Arr5 = grafo.incidentes(Identificador);
                            for(int i=0; i<Arr5.size(); i++){
                                System.out.println(Arr5.get(i).toString());
                            }
                            System.out.println("\n");

                    }else if (c.equals("z")){
                        GrafoDirigido GrafoClone = grafo.clone();
                       
                        System.out.println("Se creo un Grafo clonado del Grafo Principal");
                    }else if (c.equals("x")){
                        System.out.println(grafo.toString()+"\n");
                    }else if (c.equals("n")){
                        break;
                    }
                }
            } else if(o.equals("N")){

               
                reader = new Scanner(System.in);

                /* Creamos, construimos y cargamos el grafo */
                GrafoNoDirigido grafo2 = new GrafoNoDirigido();                                                          //Inicializa Grafo no Dirigido
                grafo2.cargarGrafo(args[0]);

                // Menu de Grafo No Dirigido
                while(true){

                    System.out.println(
                        "Menu de opciones sobre el grafo No Dirigido: \n" +
                        "Presione q (seguido de \"Enter\") para saber el numero de Vertices del grafo \n" +
                        "Presione w (seguido de \"Enter\") para saber el numero de Aristas del grafo \n" +
                        "Presione e (seguido de \"Enter\") para agregar un nuevo Vertice al grafo  \n" +
                        "Presione r (seguido de \"Enter\") para agregar una nueva Arista al grafo  \n" +
                        "Presione t (seguido de \"Enter\") para obtener un Vertice del grafo \n" +
                        "Presione y (seguido de \"Enter\") para obtener una Arista del grafo \n" +
                        "Presione u (seguido de \"Enter\") para saber si un Vertice esta en el grafo \n" +
                        "Presione i (seguido de \"Enter\") para saber si una Arista esta en el grafo \n" +
                        "Presione o (seguido de \"Enter\") para eliminar un Vertice del grafo \n" +
                        "Presione p (seguido de \"Enter\") para eliminar una Arista del grafo \n" +
                        "Presione a (seguido de \"Enter\") para imprimir una lista de las Aristas en el grafo \n" +
                        "Presione s (seguido de \"Enter\") para imprimir la lista de Vertices del grafo \n" +
                        "Presione d (seguido de \"Enter\") para imprimir el grado de un Vertice del grafo \n" +
                        "Presione f (seguido de \"Enter\") para obtener los Vertices adyacentes a un Vertice dado \n" +
                        "Presione g (seguido de \"Enter\") para obtener la lista de Aristas incidentes a un Vertice dado \n" +
                        "Presione h (seguido de \"Enter\") para clonar el grafo a un grafo nuevo \n" +
                        "Presione j (seguido de \"Enter\") para imprimir una representacion del grafo \n" +
                        "Presione n (seguido de \"Enter\") para salir");
                        c = reader.next();
                    System.out.println(" ");
                    if (c.equals("q")) {
                        System.out.println("El numero de Vertices es: " + grafo.numeroDeVertices()+"\n");
                    } else if (c.equals("w")) {
                        System.out.println("El numero de Aristas es: " + grafo.numeroDeLados()+"\n");
                    }else if (c.equals("e")) {
                        Boolean Result = false;
                        int Identificador;
                        String Nombre;
                        Double Coord_x;
                        Double Coord_y;
                        Double peso;

                        System.out.println("Escriba el Identificador del nuevo vertice: ");
                        Identificador = Integer.valueOf(reader.next()).intValue();
                        System.out.println("Escriba el Nombre del nuevo Vertice: ");
                        Nombre = reader.next();
                        System.out.println("Escriba la coordenada x del nuevo Vertice: ");
                        Coord_x = Double.valueOf(reader.next());
                        System.out.println("Escriba la coordenada y del nuevo Vertice: ");
                        Coord_y = Double.valueOf(reader.next());
                        System.out.println("Escriba el peso del nuevo Vertice");
                        peso = Double.valueOf(reader.next());

                        /* Crearemos un vertice dependiendo de los datos proporcionados en el archivo .txt
                        */
                            Vertice Ve = new Vertice(Identificador, Nombre, Coord_x, Coord_y, (peso));
                            Result = grafo2.agregarVertice(Ve);
                                                        
                            if(Result){
                                System.out.println("Se agrego el Vertice al grafo.\n");
                            }
                            else{
                                System.out.println("El Vertice ya existia en el grafo, por ende no se agrego nada.\n");
                            }
                    }else if (c.equals("r")) {
                        Boolean Result = false;
                        int u;
                        int v;
                        int Tipo;
                        Double peso;

                        System.out.println("Escriba el primer vertice de la artista: ");
                        u = Integer.valueOf(reader.next()).intValue();
                        System.out.println("Escriba el segundo vertice de la arista: ");
                        v = Integer.valueOf(reader.next()).intValue();
                        System.out.println("Escriba el tipo de la nueva arista: ");
                        Tipo = Integer.valueOf(reader.next()).intValue();
                        System.out.println("Escriba el peso de la nueva arista: ");
                        peso = Double.valueOf(reader.next());

                        /* Crearemos un arco dependiendo de los datos proporcionados en el archivo .txt
                        */
                            Arista  Ar = new Arista(grafo2.obtenerVertice(u), grafo2.obtenerVertice(v), Tipo, peso);
                            Result = grafo2.agregarArista(Ar);
                                                        
                            if(Result){
                                System.out.println("Se agrego la arista al grafo.\n");
                            }
                            else{
                                System.out.println("La arista ya existia en el grafo, por ende no se agrego nada.\n");
                            }
                    }else if (c.equals("t")) {
                        int Identificador;
                        System.out.println("Ingrese el identificador del Vertice a buscar: ");
                        Identificador = Integer.valueOf(reader.next()).intValue();
                            System.out.println(grafo2.obtenerVertice(Identificador).toString()+"\n");
                    }else if (c.equals("y")) {
                        String Vu;
                        String Vv;
                        int Tipo;
                        System.out.println("Ingrese el vertice inicial: ");
                        Vu = reader.next();
                        System.out.println("Ingrese el vertice final: ");
                        Vv = reader.next();
                        System.out.println("Ingrese el tipo de arco: ");
                        Tipo = Integer.valueOf(reader.next()).intValue();
                            System.out.println(grafo2.obtenerArista(Vu+Vv+ String.valueOf(Tipo) ).toString()+"\n");
                        
                    }else if (c.equals("u")) {
                        int Identificador;
                        System.out.println("Ingrese el Identificador del Vertice a buscar: ");
                        Identificador = Integer.valueOf(reader.next()).intValue();
                        if(grafo2.estaVertice(Identificador)){
                            System.out.println("El Vertice si esta en el Grafo.\n");
                        }
                        else{
                            System.out.println("El Vertice no esta en el grafo.\n");
                        }
                    }else if (c.equals("i")){
                        int Vu;
                        int Vv;
                        int tipo;
                        System.out.println("Ingrese el Vertice inicial de la arista: ");
                        Vu = Integer.valueOf(reader.next()).intValue();
                        System.out.println("Ingrese el Vertice final de la arista: ");
                        Vv = Integer.valueOf(reader.next()).intValue();
                        System.out.println("Ingrese el tipo de la arista: ");
                        tipo = Integer.valueOf(reader.next()).intValue();
                        if(grafo2.estaArista(Vu, Vv, tipo)){
                            System.out.println("La arista si esta en el Grafo.\n");
                        }
                        else{
                            System.out.println("La arista no esta en el grafo.\n");
                        }
                    }else if (c.equals("o")){
                        Boolean Result;
                        int Identificador;
                        System.out.println("Ingrese el Identificador de el Vertice a eliminar: ");
                        Identificador = Integer.valueOf(reader.next()).intValue();
                        Result = grafo2.eliminarVertice(Identificador);
                        if(Result){
                            System.out.println("El vertice se elimino del grafo.\n");
                        }
                        else{
                            System.out.println("El vertice no existia en el grafo, por ende no se elimino nada.\n");
                        }
                    }else if (c.equals("p")){
                        Boolean Result;
                        String Vu;
                        String Vv;
                        int tipo;
                        System.out.println("Ingrese el vertice inicial de la arista a eliminar: ");
                        Vu = reader.next();
                        System.out.println("Ingrese el vertice final de la arista a eliminar: ");
                        Vv = reader.next();
                        System.out.println("Ingrese el tipo de la arista a eliminar: ");
                        tipo = Integer.valueOf(reader.next()).intValue();
                        Result = grafo2.eliminarArista(Vu+ Vv+String.valueOf(tipo));
                        if(Result){
                            System.out.println("La arista se elimino del grafo.\n");
                        }
                        else{
                            System.out.println("La arista no existia en el grafo, por ende no se elimino nada.\n");
                        }
                    }else if (c.equals("a")){
                        System.out.println("Aristas en el grafo: ");
                        
                        LinkedList<Lado> Ar = grafo2.lados();
                        for(int i=0; i<Ar.size(); i++){
                            System.out.println(Ar.get(i).toString());
                        
                        System.out.println("\n");
                    } if (c.equals("s")){
                        System.out.println("Vertices en el grafo: ");
                        
                        LinkedList<Vertice> Arr = grafo2.vertices();
                        for(int i=0; i<Arr.size(); i++){
                            System.out.println(Arr.get(i).toString());
                        
                        System.out.println("\n");
                    } if (c.equals("d")){
                        int Identificador;
                        System.out.println("Ingrese el Vertice al cual se le desea conocer el Grado: ");
                        Identificador = Integer.valueOf(reader.next()).intValue();
                            System.out.println("El grado del Vertice es: "+grafo2.grado(Identificador)+"\n");
                    }else if (c.equals("f")){
                        int Identificador;
                        System.out.println("Ingrese el identificador del Vertice al cual se le desea conocer sus vertices adyacentes: ");
                        Identificador = Integer.valueOf(reader.next()).intValue();
                            LinkedList<Vertice> Arr6 = grafo2.adyacentes(Identificador);
                            for(int i=0; i<Arr6.size(); i++){
                                System.out.println(Arr6.get(i).toString());
                            }
                            
                            System.out.println("\n");

                    }else if (c.equals("g")){
                        int Identificador;
                        System.out.println("Ingrese el Vertice al cual se le desea conocer sus aristas incidentes: ");
                        Identificador = Integer.valueOf(reader.next()).intValue();
                            LinkedList<Lado> Arr7 = grafo2.incidentes(Identificador);
                            for(int i=0; i<Arr7.size(); i++){
                                System.out.println(Arr7.get(i).toString());
                            }
                            
                            System.out.println("\n");


                        
                    }else if (c.equals("h")){
                        GrafoNoDirigido GrafoClone = grafo2.clone();
                       
                        System.out.println("Se creo un grafo clonado del Grafo Principal");
                    }else if (c.equals("j")){
                        System.out.println(grafo2.toString()+"\n");
                    }else if (c.equals("n")){
                        break;
                    }

                }             

            }  
        }
    }
}
            }

    }
}
       
