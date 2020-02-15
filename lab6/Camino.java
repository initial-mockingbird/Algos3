import java.util.*; 
public class Camino {
    ArrayList<Integer> camino;
    Boolean estado;
    String tipo;
    Camino(){
        this.camino = new ArrayList<Integer>();
        this.estado = true;
    }
    Camino(ArrayList<Integer> nuevoCamino, Boolean nuevoEstado){
        this.camino = new ArrayList<Integer>(nuevoCamino);
        this.estado = nuevoEstado;
        
    }
    Camino(ArrayList<Integer> nuevoCamino){
        this.camino = new ArrayList<Integer>(nuevoCamino);
        this.estado = true;
        
    }
    Camino(Integer nuevoCamino){
        this.camino = new ArrayList<Integer>();
        this.camino.add(nuevoCamino);
        this.estado = true;
        
    }

    public ArrayList<Integer> get_camino(){
        return this.camino;
    }
    public void set_camino(ArrayList<Integer> nuevo){
        this.camino = new ArrayList<Integer>(nuevo);
    }
    public Boolean is_open(){
        return this.estado;
    }
    public void close(){
        this.estado = false;
    }
    public void open(){
        this.estado = true;
    }

    public void add_vertex(Integer v){
        this.camino.add(v);
    }
    public Integer get(int index){
        return camino.get(index);
    }
    public Integer get_last(){
        return camino.get(camino.size()-1);
    }

    public void expand(Integer v){
        this.camino.add(v);

    }

    public int size(){
        return this.camino.size();
    }

}