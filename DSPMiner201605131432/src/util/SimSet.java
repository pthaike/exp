package util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SimSet {
	
	private ArrayList<SimElement> simlist = null;
	private SimMatrix sm = null;
	private ArrayList<String> alphabet = null;
	private ArrayList<String> simelems = null;
	
	
	public SimSet(ArrayList<String> alphabet, SimMatrix sm){
		this.simlist = new ArrayList<>();
		this.simelems = new ArrayList<String>();
		this.sm = sm;
		this.alphabet = alphabet;
		initSimSet();
		simListToString();
	}
	
	//初始化 similarity set
	public void initSimSet(){
		ArrayList<Set<String>> setls = new ArrayList<Set<String>>();
		ArrayList<Set<String>> setls2 = new ArrayList<Set<String>>();
		for(int i = 0; i < alphabet.size(); i++){
			SimElement e = new SimElement();
			e.addelem(alphabet.get(i));
			addSimElem(e);
			Set<String> set = new HashSet<String>();
			set.add(alphabet.get(i));
			setls.add(set);
		}
		for(int i = 0; i < alphabet.size()-1 && !setls.isEmpty(); i++){
			Set<String> s1 = new HashSet<>();
			Set<String> s2 = new HashSet<>();
			for(int j = 0; j < setls.size(); j++){
				s1 = setls.get(j);
				for(int k = j+1; k < setls.size(); k++){
					s2 = setls.get(k);
					Set<String> cup = getTwoSetCup(s1, s2);
					if(setls2.contains(cup))
						continue;
					String [] a = getTwoSet(s1, s2);
					if(null != a){
						int sim = sm.getTwoSim(a[0], a[1]);
						if(sim == 1){
							setls2.add(cup);
							SimElement e = new SimElement();
							for(Iterator<String> it = cup.iterator(); it.hasNext();){
								e.addelem(it.next());
							}
							addSimElem(e);
						}
					}
				}
			}
			//change setls and setls2
			setls = setls2;
//			setls.clear();
//			for(int k = 0; k < setls2.size(); k++){
//				setls.add(setls2.get(k));
//			}
//			setls2.clear();
		}
	}
	
	private void simListToString(){
		for(int i = 0; i < this.simlist.size(); i++){
			this.simelems.add(this.simlist.get(i).getSimElem());
		}
	}
	
	public SimMatrix getSm() {
		return sm;
	}

	public ArrayList<String> getAlphabet() {
		return alphabet;
	}

	public ArrayList<String> getSimelems() {
		return simelems;
	}

	private String[] getTwoSet(Set<String> s1, Set<String> s2){
		Set<String> scup = new HashSet<String>();
		Set<String> scap = new HashSet<String>();
		Set<String> res = new HashSet<String>();
		
		scup = getTwoSetCup(s1,s2);
		
		scap = getTwoSetCap(s1,s2);
		
		res = getTwoSetDiff(scup, scap);
		
		if(res.size() == 2){
			String[] a = new String[2];
			a =  res.toArray(a);
			return a;
		}
		return null;
	}
	
	//并集
	private Set<String> getTwoSetCup(Set<String> s1, Set<String> s2){
		Set<String> res = new HashSet<String>();
		res.addAll(s1);
		res.addAll(s2);
		return res;
	}
	
	//交集
	private Set<String> getTwoSetCap(Set<String> s1, Set<String> s2){
		Set<String> res = new HashSet<String>();
		res.addAll(s1);
		res.retainAll(s2);
		return res;
	}
	
	//差集
	private Set<String> getTwoSetDiff(Set<String> s1, Set<String> s2){
		Set<String> res = new HashSet<String>();
		res.addAll(s1);
		res.removeAll(s2);
		return res;
	}
	
	private void addSimElem(SimElement sm){
		simlist.add(sm);
	}

	public SimElement getIth(int ix) {
		return simlist.get(ix);
	}

	public ArrayList<SimElement> getSimlist() {
		return simlist;
	}
	
	public static void main(String[] args){
		Set<String> s1 = new HashSet<>();
		Set<String> s2 = new HashSet<>();
		s1.add("F");
		s2.add("D");
	}

}
