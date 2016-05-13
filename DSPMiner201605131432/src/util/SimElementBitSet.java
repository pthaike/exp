package util;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;

/*
 * generate the bitset list from similarity element
 */
public class SimElementBitSet {
	private HashMap<String, HashMap<String, BitSet>> posbsls = null;
	private HashMap<String, HashMap<String, BitSet>> negbsls = null;
	private SimSet simset = null;
	private HashMap<String, HashMap<String,BitSet>> possmblist = null;
	private HashMap<String, HashMap<String,BitSet>> negsmblist = null;
	
	public SimElementBitSet(BitSetList bsls, SimSet simset) {
		super();
		this.posbsls = bsls.getPosblist();
		this.negbsls = bsls.getNegblist();
		this.simset = simset;
		this.possmblist = initBitSet(this.posbsls);
		this.negsmblist = initBitSet(this.negbsls);
	}

	private HashMap<String, HashMap<String, BitSet>> initBitSet(HashMap<String, HashMap<String, BitSet>> onebsls){
		HashMap<String, HashMap<String, BitSet>> blst = new HashMap<String, HashMap<String, BitSet>>();
		SimElement se = null;
		for(int i = 0; i < simset.getSimlist().size(); i++){
			se = simset.getIth(i);
			HashMap<String, BitSet> bs = new HashMap<String,BitSet>();
			for(int j = 0; j < se.getElem().size(); j++){
				String s = se.getElem().get(j);
				if(!onebsls.containsKey(s))
					continue;
				HashMap<String,BitSet> seqBitSet = onebsls.get(s);
				BitSet idbs = null;
				for(Iterator<String> it = seqBitSet.keySet().iterator(); it.hasNext();){
					String id = it.next();
					idbs = (BitSet) seqBitSet.get(id).clone();
					if(!bs.containsKey(id)){
						bs.put(id, idbs);
					}else{
						bs.get(id).or(idbs);
					}
				}
			}
			blst.put(se.getSimElem(), bs);
//			System.out.println(bs);
		}
		return blst;
	}

	public HashMap<String, HashMap<String, BitSet>> getPossmblist() {
		return possmblist;
	}

	public HashMap<String, HashMap<String, BitSet>> getNegsmblist() {
		return negsmblist;
	}
	
}