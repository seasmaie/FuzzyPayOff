
import Fachkonzept.Fuzzy;
import Fachkonzept.ROV;
import GUI.ROV_GUI;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ROV_GUI();
		Fuzzy f = new Fuzzy(5.16, 20.7, 48.46);
		ROV rov = new ROV(f);
		System.out.println(f.getFuzzy(0));
	}

}
