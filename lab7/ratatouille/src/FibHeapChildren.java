public final class FibHeapChildren<T1> {
    T1 value;
    T1 left;
    T1 right;
    T1 child;

    FibHeapChildren(){
        
    }

    FibHeapChildren(T1 nvalue){
        this.value = nvalue;
    }

    FibHeapChildren(T1 nvalue,T1 nprev, T1 nnext,T1 nChild){
        this.value = nvalue;
        this.left = nprev;
        this.right = nnext;
        this.child = nChild;
    }
    FibHeapChildren(FibHeapChildren<T1> children){
        this.value = children.getValue();
        this.left = children.getLeft();
        this.right = children.getRight();
        this.child = children.getChild();
    }
    
    public void setValue(T1 nvalue){
        this.value = nvalue;
    }

    public void setRight (T1 nnext){
        this.right = nnext;
    } 

    public void setLeft (T1 nprev){
        this.left = nprev;
    }

    public void setChild(T1 nChild){
        this.child = nChild;
    }

    public T1 getValue(){
        return this.value;
    }

    public T1 getLeft(){
        return this.left;
    }

    public T1 getRight(){
        return this.right;
    }

    public T1 getChild(){
        return this.child;
    }

}