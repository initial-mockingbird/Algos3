public final  class FibHeapNode<T1> {
    T1 value;
    FibHeapChildren<T1> child;

    FibHeapNode(T1 nvalue){
        this.value = nvalue;
    }

    FibHeapNode(T1 nvalue,FibHeapChildren<T1> nChild){
        this.value = nvalue;
        this.child = nChild;
    }
    FibHeapNode(FibHeapNode<T1> node){
        this.value = node.getValue();
        this.child = node.getChild();
    }
    
    public void setValue(T1 nvalue){
        this.value = nvalue;
    }

    public void setChild(FibHeapChildren<T1> nChild){
        this.child = nChild;
    } 

    public T1 getValue(){
        return this.value;
    }

    public FibHeapChildren<T1> getChild(){
        return this.child;
    }



}