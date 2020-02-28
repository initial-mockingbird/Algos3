
public class Pair<T1,T2> {
	T1 fst;
	T2 snd;
	
	Pair(T1 nfst, T2 nsnd){
		this.fst = nfst;
		this.snd = nsnd;
	}
	
	public T1 fst(){
		return this.fst;
	}
	
	public T2 snd(){
		return this.snd;
	}


	@Override
	public String toString() {
		return "( "+fst.toString() + " , " + snd.toString() + " )";
	}
}
