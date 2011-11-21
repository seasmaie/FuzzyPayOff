package Fachkonzept;

public class ROV {

	private Fuzzy fuzzy;
	private double realoptionvalue;

	public ROV(Fuzzy fuzzy) {
		this.fuzzy = fuzzy;
        calcROV();
	}

     public ROV() { }
	
	private void calcROV() {
		double alpha = fuzzy.getAlpha();
		double beta = fuzzy.getBeta();
		double mean = fuzzy.getFuzzyMean();
		double zeroFuzzy = fuzzy.getFuzzy(0);
		realoptionvalue = mean * (beta * zeroFuzzy / 2) / ((beta - alpha) / 2);
	}

	public Fuzzy getFuzzy() {
		return fuzzy;
	}

	public void setFuzzy(Fuzzy fuzzy) {
		this.fuzzy = fuzzy;
		calcROV();
	}

	public double getRealoptionvalue() {
		return realoptionvalue;
	}


}

