package GUI;

import Fachkonzept.Fuzzy;
import Fachkonzept.ROV;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ROV_GUI extends JFrame{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel;
    JLabel worstLabel, bestLabel, mostlikelyLabel, rovLabel, rovOutput;
    JMenuBar menubar;
    JMenu file, edit, options;
    JTextField worstText, bestText, mostlikelyText;
    DecimalFormat df = new DecimalFormat("0.00");
    private Fuzzy fuzzy;
    private ROV rov;
    private boolean wcValid, mlcValid, bcValid;

    public ROV_GUI() {
    	fuzzy = new Fuzzy();
        rov = new ROV();
        setTitle("Fuzzy-PayOff-Method");
        setLocation(200,200);
        setLayout(new BorderLayout());
        setSize(300, 300);
        menubar = new JMenuBar();
            file = new JMenu("Datei");
            edit = new JMenu("Bearbeiten");
            options = new JMenu("Einstellungen");
        menubar.add(file);
        menubar.add(edit);
        menubar.add(options);
        panel = new JPanel(null);
        worstLabel = new JLabel("Worst-Case-Szenario:");
        worstLabel.setBounds(10, 10, 150, 30);
        worstText = new JTextField();
        worstText.setBounds(160, 10, 100, 30);
        worstText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                rovOutput(1);
            }
        });


        mostlikelyLabel = new JLabel("Most-Likely-Szenario:");
        mostlikelyLabel.setBounds(10, 40, 150, 30);
        mostlikelyText = new JTextField();
        mostlikelyText.setBounds(160, 40, 100, 30);
        mostlikelyText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                rovOutput(2);
            }
        });
        bestLabel = new JLabel("Best-Case-Szenario:");
        bestLabel.setBounds(10, 70, 150, 30);
        bestText = new JTextField();
        bestText.setBounds(160, 70, 100, 30);
        bestText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                rovOutput(3);
            }
        });
        rovLabel = new JLabel("Real Option Value:");
        rovLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        rovLabel.setBounds(10, 150, 200, 50);
        rovOutput = new JLabel();
        rovOutput.setFont(new Font("SansSerif", Font.BOLD, 16));
        rovOutput.setBounds(210, 150, 150, 50);
        panel.add(worstLabel);
        panel.add(worstText);
        panel.add(mostlikelyLabel);
        panel.add(mostlikelyText);
        panel.add(bestLabel);
        panel.add(bestText);
        panel.add(rovLabel);
        panel.add(rovOutput);
        add("North",menubar);
        add("Center",panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void rovOutput(int field) {
        //Worstcase-Eingabe
        if (field == 1)
            try {
                String wc = worstText.getText();
                if (wc.contains(",")) wc = wc.replace(",", ".");
                fuzzy.setAlpha(Double.parseDouble(wc));
                rov.setFuzzy(fuzzy);
                wcValid = true;
                if (mlcValid && bcValid)
                    rovOutput.setText(df.format(rov.getRealoptionvalue()));
            }
            catch (NumberFormatException ex) {
                wcValid = false;
                rovOutput.setText("");
            }

        //Most-Likely-Eingabe
        if (field == 2)
            try {
                String mlc = mostlikelyText.getText();
                if (mlc.contains(",")) mlc = mlc.replace(",", ".");
                fuzzy.setA(Double.parseDouble(mlc));
                rov.setFuzzy(fuzzy);
                mlcValid = true;
                if (wcValid && bcValid)
                    rovOutput.setText(df.format(rov.getRealoptionvalue()));
            }
            catch (NumberFormatException ex) {
                mlcValid = false;
                rovOutput.setText("");
            }

        //Bestcase-Eingabe
        if (field == 3)
            try {
                String bc = bestText.getText();
                if (bc.contains(",")) bc = bc.replace(",", ".");
                fuzzy.setBeta(Double.parseDouble(bc));
                rov.setFuzzy(fuzzy);
                bcValid = true;
                if (mlcValid && wcValid)
                    rovOutput.setText(df.format(rov.getRealoptionvalue()));
            }
            catch (NumberFormatException ex) {
                bcValid = false;
                rovOutput.setText("");
            }
    }
}

