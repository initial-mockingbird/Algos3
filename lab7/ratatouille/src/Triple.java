
public class Triple <T1,T2,T3>{
	T1 fst;
	T2 snd;
	T3 trd;
	Triple(T1 nfst, T2 nsnd, T3 ntrd){
		this.fst = nfst;
		this.snd = nsnd;
		this.trd = ntrd;
	}
	
	public T1 fst(){
		return this.fst;
	}
	
	public T2 snd(){
		return this.snd;
	}
	
	public T3 trd(){
		return this.trd;
	}
}
