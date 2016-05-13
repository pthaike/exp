package util;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;

public class Pattern {
	
	private String pattern = null;
	private HashMap<String, BitSet> posbsls = null;
	private HashMap<String, BitSet> negbsls = null;
	public Pattern(String pattern, HashMap<String, BitSet> posbsls, HashMap<String, BitSet> negbsls) {
		super();
		this.pattern = pattern;
		this.posbsls = posbsls;
		this.negbsls = negbsls;
	}
	public String getPattern() {
		return pattern;
	}
	public HashMap<String, BitSet> getPosbsls() {
		return posbsls;
	}
	public HashMap<String, BitSet> getNegbsls() {
		return negbsls;
	}
	
}
