package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class Grafo {

    protected LinkedList<LinkedList<String>> listAd;
    protected HashSet<String> vertices;
    protected int size;

    Grafo () {
        listAd = new LinkedList<LinkedList<String>>();
        vertices = new HashSet<String> ();
        size = 0;
    }


    public void cargarGrafo(String archivo) throws IOException {
        BufferedReader file = new BufferedReader(new FileReader(archivo));
        String line = file.readLine();
        while(line!= null){
            line = line.replace(":", "");
            List<String> elements = Arrays.asList(line.split("\\s"));
            vertices.addAll(elements);
            listAd.add(new LinkedList<String>(elements));
            line = file.readLine();
        }
        file.close();
        size = vertices.size();
    }

    public LinkedList<String> succ(String v){
        Iterator<LinkedList<String>> i = listAd.iterator();
        LinkedList<String> w;
        while(i.hasNext()){
            w = i.next();
            if (w.getFirst().equals(v)){
                LinkedList<String> s = new LinkedList<String>(w);
                s.pop();
                return s;
            }
        }
        return new LinkedList<String>();
    }

    private LinkedList<String> topos_Order(LinkedList<String> porVisitar, LinkedList<String> soFar,String v){
        porVisitar.remove(v);
        Iterator<String> i = this.succ(v).iterator();
        while(i.hasNext()){
            String w = i.next();
            if (porVisitar.contains(w)){
               topos_Order(porVisitar, soFar,w);
            } 
        }
        soFar.add(0, v);
        return soFar;
    }

    public String Dependencies()
    throws NoSuchElementException
    {
        LinkedList<String> porVisitar = new LinkedList<String>( (HashSet<String>) vertices.clone() );
        LinkedList<String> soFar = new LinkedList<String>();
        LinkedList<String> ans = new LinkedList<String>();
        int l = 0;
        while(!porVisitar.isEmpty()){
            String v = porVisitar.pop();
            LinkedList<String> next = topos_Order(porVisitar, soFar,v);
            l = next.size();
            ans.addAll(next) ;
        }
        int size = ans.size()-l;
        for (int i=0;i<size;i++){
            ans.pop();
        }
        Iterator<String> j = ans.descendingIterator();
        String answer = "";
        while(j.hasNext()){
            answer =  answer +" "+j.next() ;
        }
        return answer;
    }


}