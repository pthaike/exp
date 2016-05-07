package util;

import java.util.ArrayList;

public class SimElement {
	private ArrayList<String> elem = null;
	public SimElement(){
		elem = new ArrayList<String>();
	}
	public void addelem(String e){
		elem.add(e);
	}
	
	public boolean checkSim(String e, SimMatrix sm){
		int i;
		for(i = 0; i < elem.size(); i++){
			if(sm.getTwoSim(e, elem.get(i)) == 0){
				break;
			}
		}
		if(i < elem.size()){
			return false;
		}
		return true;
	}
	public ArrayList<String> getElem() {
		return elem;
	}
	
	//»ñµÃÄ³¸öelement
	public String getSimElem(){
		if(this.elem.size() == 0){
			return null;
		}
		String res = "(";
		for(int i = 0; i< this.elem.size(); i++){
			res += this.elem.get(i);
			if(i < this.elem.size()-1){
				res += ",";
			}
		}
		res += ")";
		return res;
	}
}


//public class SimElement {
//	private ArrayList<String> elem = null;
//	public SimElement(){
//		elem = new ArrayList<String>();
//	}
//	public void addelem(String e){
//		elem.add(e);
//	}
//	public boolean checkSim(String e, SimMatrix sm){
//		int i;
//		for(i = 0; i < elem.size(); i++){
//			if(sm.getTwoSim(e, elem.get(i)) == 0){
//				break;
//			}
//		}
//		if(i < elem.size()){
//			return false;
//		}
//		return true;
//	}
//	public ArrayList<String> getElem() {
//		return elem;
//	}
//}