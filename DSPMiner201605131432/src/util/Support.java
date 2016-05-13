package util;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Support {
	
	private SimElementBitSet sebs = null;
	private ArrayList<String> pattern = null;
	private SimSet simSet = null;
	private int posnum;
	private int negnum;
	
	public Support(SimElementBitSet sebs, SimSet simSet, int posnum, int negnum) {
		super();
		this.sebs = sebs;
		this.simSet = simSet;
		this.posnum = posnum;
		this.negnum = negnum;
		this.pattern = new ArrayList<String>();
	}
	
	public void genPattern(){
		Queue<Pattern> pquene = new LinkedList<Pattern>();
		ArrayList<String> lenonecandidate = new ArrayList<String>();
		for(int i =0; i < simSet.getSimelems().size(); i ++){
			String p = simSet.getSimelems().get(i);
			if(sebs.getPossmblist().get(p).size()/(double)posnum >= Configure.alpha){
				lenonecandidate.add(p);
			}
			if(sebs.getPossmblist().get(p).size()/(double)posnum >= Configure.alpha &&
					sebs.getNegsmblist().get(p).size()/(double)negnum <= Configure.beta){
				this.pattern.add(p);
			}
			else if(sebs.getPossmblist().get(p).size()/(double)posnum >= Configure.alpha &&
					sebs.getNegsmblist().get(p).size()/(double)negnum >= Configure.beta){
				Pattern cp = new Pattern(p, (HashMap<String, BitSet>)sebs.getPossmblist().get(p).clone(), (HashMap<String, BitSet>)sebs.getNegsmblist().get(p).clone());
				pquene.offer(cp);
			}
		}
		System.out.println("lenonecandidate.size()"+lenonecandidate.size());
		System.out.println("simSet.getSimelems().size()"+simSet.getSimelems().size());
		
		//ʹ�ö�����ʵ�ֹ������
		int count = 0;
		while(!pquene.isEmpty()){
//			System.out.println(pquene.size());
			Pattern candidate = pquene.poll();
			String p = candidate.getPattern();
			HashMap<String, BitSet> posbsls = candidate.getPosbsls();
			HashMap<String, BitSet> negbsls = candidate.getNegbsls();
			if(posbsls == null){
				continue;
			}
			for(int j = 0; j < lenonecandidate.size(); j++){
				long startMili=System.currentTimeMillis();//time
				String e = lenonecandidate.get(j);
//				System.out.println(p+e);
				HashMap<String, BitSet> posbstmp = new HashMap<String, BitSet>();
				HashMap<String, BitSet> negbstmp = new HashMap<String, BitSet>();
				for(Iterator<String> it = posbsls.keySet().iterator(); it.hasNext();){
					String id = it.next();
					HashMap<String, BitSet> tmpidbs = sebs.getPossmblist().get(e);
					if(!tmpidbs.containsKey(id))
						continue;
					BitSet bs = posbsls.get(id);
					BitSet bs2 = tmpidbs.get(id);
					BitSet orbs = new BitSet(bs.length());
					for(int g = Configure.gap[0]; g <= Configure.gap[1]; g++){
						BitSet b = (BitSet) bs.clone();
						rightShift(b, g+1);
						orbs.or(b);
					}
					orbs.and(bs2);
					if(orbs.isEmpty())
						continue;
					posbstmp.put(id, orbs);
				}
				
				for(Iterator<String> it = negbsls.keySet().iterator(); it.hasNext();){
					String id = it.next();
					HashMap<String, BitSet> tmpidbs = sebs.getNegsmblist().get(e);
					if(!tmpidbs.containsKey(id))
						continue;
					BitSet bs = negbsls.get(id);
					BitSet bs2 = tmpidbs.get(id);
					BitSet orbs = new BitSet(bs.length());
					for(int g = Configure.gap[0]; g <= Configure.gap[1]; g++){
						BitSet b = (BitSet) bs.clone();
						rightShift(b, g+1);
						orbs.or(b);
					}
					orbs.and(bs2);
					if(orbs.isEmpty())
						continue;
					negbstmp.put(id, orbs);
				}
				
				if(posbstmp.size()/(double)posnum >= Configure.alpha){
					System.out.println(p+e);
				}
				
				if(posbstmp.size()/(double)posnum >= Configure.alpha && 
						negbstmp.size()/(double)negnum <= Configure.beta){
					pattern.add(p+e);
//					System.out.println("========>"+p+e);
				}else if(posbstmp.size()/(double)posnum >= Configure.alpha && 
						negbstmp.size()/(double)negnum >= Configure.beta){
					Pattern cp = new Pattern(p+e, posbstmp, negbstmp);
					pquene.offer(cp);
				}
				long endMili=System.currentTimeMillis();
//				System.out.println("one candidate Time"+(endMili - startMili));
			}
		}
	}
	
	
	/**
	 * 
	 * @Title: rightShift 
	 * @Description: ���Ʋ��� 
	 * @param b ���Ƶ�BitSet
	 * @param num  ���Ʋ��� 
	 * @return void  �������� 
	 * @throws
	 */
	private void rightShift(BitSet b, int num){
		int count = b.cardinality();
		int length = b.length();
		int index = length-1;
		for (int i = count; i > 0; i--) {
			index = b.previousSetBit(index);
			b.set(index+num);
			b.clear(index);
		}
	}

	public ArrayList<String> getPattern() {
		return pattern;
	}
	
}