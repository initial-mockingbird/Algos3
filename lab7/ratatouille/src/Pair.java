import java.util.function.Supplier;
import java.util.function.UnaryOperator;

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

	public Supplier<Pair<T1,T2>> fmapS(UnaryOperator<T2> f){
		return () -> new Pair<T1,T2>(this.fst, f.apply(this.snd));
	}

	public Pair<T1,T2> clone() {
		return new Pair<T1,T2>(this.fst,this.snd);
	}

	@Override
	public String toString() {
		return "( "+fst.toString() + " , " + snd.toString() + " )";
	}
}
