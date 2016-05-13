package main;

import java.util.ArrayList;
import java.util.Iterator;

import util.BitSetList;
import util.Configure;
import util.SimElementBitSet;
import util.SimMatrix;
import util.SimSet;
import util.Support;
import util.Tool;

public class Main {
	
	public static void log(String msg, Object obj){
		System.out.println(msg+"===========>"+obj);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> posseq = Tool.readSeq(Configure.posfilename);
		ArrayList<String> negseq = Tool.readSeq(Configure.negfilename);
		
		log("posseq.size()", posseq.size());
		log("negseq.size()", negseq.size());
		long startMili=System.currentTimeMillis();// 当前时间对应的毫秒数
		BitSetList bsls = new BitSetList(posseq, negseq);
		
		log("bsls.getAlphabet().size()",bsls.getAlphabet().size());
		for(String s:bsls.getAlphabet()){
			System.out.println(s);
		}
		
		double m[][] = Tool.similarityMatrix(bsls.getAlphabet().size());
		SimMatrix sm = new SimMatrix(m, bsls.getAlphabet());
		SimSet simset = new SimSet(bsls.getAlphabet(), sm);
		
		log("similarity sets",simset.getSimlist().size());
		
//		for(Iterator<String> it = bsls.getPosblist().keySet().iterator(); it.hasNext();){
//			String str = it.next();
//			System.out.print(str);
//			System.out.println(bsls.getPosblist().get(str));
//		}
		
		//print similarity set
//		for(int i = 0; i < simset.getSimlist().size(); i++){
//			System.out.println(simset.getIth(i).getElem());
//		}
		
		SimElementBitSet smbs = new SimElementBitSet(bsls, simset);
		
//		System.out.println(smbs.getPossmblist().size());
//		for(Iterator<String> it = smbs.getPossmblist().keySet().iterator(); it.hasNext();){
//			String str = it.next();
//			System.out.print(str);
//			System.out.println(smbs.getPossmblist().get(str));
//		}
		
		
		
		Support sp = new Support(smbs, simset, posseq.size(), negseq.size());
		sp.genPattern();
		ArrayList<String> patterns = sp.getPattern();
//		
//		System.out.println("================Similarity set========================");
//		for(int i = 0; i < simset.getSimlist().size(); i++){
//			System.out.println(simset.getIth(i).getElem());
//		}
//		log("simset.getSimlist().size()", simset.getSimlist().size());
//		
		System.out.println("---------------------Patterns--------------");
		for(int i = 0; i < patterns.size(); i++){
			System.out.println(patterns.get(i));
		}
//		System.out.println("end------------"+patterns.size()+"---------Patterns--------------end");
		log("patterns.size()", patterns.size());
		System.out.println("=======================end=========================");
		long endMili=System.currentTimeMillis();
		log("Time", endMili - startMili);
	}
}
