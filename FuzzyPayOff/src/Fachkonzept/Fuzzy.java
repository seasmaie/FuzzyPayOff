package Fachkonzept;

public class Fuzzy {

	private double alpha, a, b, beta;

	public Fuzzy() {};
	
	//Dreieck-Fuzzy
	public Fuzzy(double alpha, double a, double beta) {
		this.alpha = alpha;
		this.a = a;
		this.beta = beta;
	}

	public double getAlpha() {
		return alpha;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public double getBeta() {
		return beta;
	}

	public void setBeta(double beta) {
		this.beta = beta;
	}

	public double getFuzzy(double t) {
		double A_t = -1;
		if (a - alpha <= t && t <= a)
			A_t = 1 - (a - t) / alpha;
		else
		if (a <= t && t <= a + beta)
			A_t =  1 - (t - a) / beta;
		else A_t = 0;
		return A_t;
	}
	
	/*
	public double getTrapezoidalFuzzy(double t) {
		double A_t = -1;
		if (a - alpha <= t && t <= a)
			A_t = 1 - (a - t) / alpha;
		else
		if (a <= t && t <= b)
			A_t = 1;
		else
		if (a <= t && t <= b + beta)
			A_t =  1 - (t - b) / beta;
		else A_t = 0;
		return A_t;
	}*/
	
	public double getFuzzyMean() {
		double mean = -1;
		if (alpha < 0 && a < 0 && beta <= 0)
			mean = 0;
		else
        if (alpha < 0 && a <= 0)
				mean = beta / 6;
        else
        if (alpha <= 0)
    			mean = a + beta / 6;
        else
    			mean = a + (beta - alpha) / 6;
		return mean;
	}
}
