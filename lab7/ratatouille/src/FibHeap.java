import java.util.ArrayList;

public class FibHeap<T1> {
    ArrayList<FibHeapChildren<T1>> rootList;
    int min;
    FibHeap(){
        this.rootList = new ArrayList<FibHeapChildren<T1>>();
        min = -1;
    }
    

}