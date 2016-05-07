package util;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;

public class BitSetList {
	private HashMap<String, HashMap<String,BitSet>> posblist = null;
	private HashMap<String, HashMap<String,BitSet>> negblist = null;
 	private ArrayList<String> possequences = null;
 	private ArrayList<String> negsequences = null;
 	private ArrayList<String> alphabet = null;
 	
	public BitSetList(ArrayList<String> posseq, ArrayList<String> negseq) {
		super();
		this.alphabet = new ArrayList<>();
		this.possequences = posseq;
		this.negsequences = negseq;
		this.posblist = initBitSetList(posseq);
		this.negblist = initBitSetList(negseq);
		for(Iterator<String> it = posblist.keySet().iterator(); it.hasNext();){
			String k  = it.next();
			this.alphabet.add(k);
		}
	}
	
	private HashMap<String, HashMap<String,BitSet>> initBitSetList(ArrayList<String> sequences){
		HashMap<String, HashMap<String,BitSet>> blist = new HashMap<String, HashMap<String,BitSet>>();
		for(int i = 0; i < sequences.size(); i++){
			String seq = sequences.get(i);
			String[] list = seq.split("");
			for(int j = 0; j < list.length; j++){
				String str = list[j].trim();
				String id = String.valueOf(i);
				if(!blist.containsKey(str)){
					HashMap<String, BitSet> b = new HashMap<String, BitSet>();
					blist.put(str, b);
				}
				if(!blist.get(str).containsKey(id)){
					BitSet bs = new BitSet(seq.length());
					blist.get(str).put(id,bs);
				}
				blist.get(str).get(id).set(j);
			}
		}
		return blist;
	}

	public ArrayList<String> getAlphabet() {
		return alphabet;
	}

	public void setItems(ArrayList<String> alphabet) {
		this.alphabet = alphabet;
	}

	public HashMap<String, HashMap<String, BitSet>> getPosblist() {
		return posblist;
	}

	public HashMap<String, HashMap<String, BitSet>> getNegblist() {
		return negblist;
	}

	public ArrayList<String> getPossequences() {
		return possequences;
	}

	public ArrayList<String> getNegsequences() {
		return negsequences;
	}
	
}
