package app;

public class Triplet<T1,T2,T3>{
    private T1 fst;
    private T2 snd;
    private T3 thrd;

    Triplet(T1 newF, T2 newS, T3 newT ){
        this.fst = newF;
        this.snd = newS;
        this.thrd = newT;
    }
    public T1 fst(){
        return this.fst;
    }

    public T2 snd(){
        return this.snd;
    }

    public T3 thrd(){
        return this.thrd;
    }

    public void set_fst(T1 newF){
        this.fst = newF;
    }

    public void set_snd(T2 newS){
        this.snd = newS;
    }

    public void set_thrd(T3 newT){
        this.thrd = newT;
    }

    
    public Boolean contains(Object o){
        if (o.equals(fst) || o.equals(snd) || o.equals(thrd)){
            return true;
        }
        return false;
    }
}