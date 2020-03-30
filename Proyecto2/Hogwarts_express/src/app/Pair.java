package app;

public class Pair<T1,T2>{
    private T1 fst;
    private T2 snd;

    Pair(T1 newF, T2 newS){
        this.fst = newF;
        this.snd = newS;
    }
    public T1 fst(){
        return this.fst;
    }

    public T2 snd(){
        return this.snd;
    }

    public void set_fst(T1 newF){
        this.fst = newF;
    }

    public void set_snd(T2 newS){
        this.snd = newS;
    }

    
    public Boolean contains(Object o){
        if (o.equals(fst) || o.equals(snd)){
            return true;
        }
        return false;
    }
}