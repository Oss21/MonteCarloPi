package main;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;


@SuppressWarnings("serial")
public class GUI extends JFrame {

	private JLabel labSemilla, labNumPuntos, labTxtValPi, labValPi;
	private JTextField txtFieldSemilla, txtFieldNumPuntos;
	private JButton butCalcPi, butCargarPrueba;

	/**
	 * Create the frame.
	 */
	public GUI() {

		/**
		 * Create the frame.
		 */		
		setTitle("Calcular PI :: Montecarlo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		setLocationRelativeTo(null);
		setBounds(100, 100, 300, 250);
		GridBagConstraints c = new GridBagConstraints();
		
		/**
		 * Create objects
		 */
		
		labSemilla = new JLabel("Semilla");
		labNumPuntos = new JLabel("Número de puntos");
		labTxtValPi = new JLabel("El valor calculado de PI es: ");
		labValPi = new JLabel("");
		
		txtFieldSemilla = new JTextField();
		txtFieldNumPuntos = new JTextField();
		
		butCalcPi = new JButton("Calcular");
		butCargarPrueba = new JButton("Prueba");		
		
		/**
		 * Organize layout
		 */
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.LINE_START;
		add(labSemilla, c);
		c.anchor = GridBagConstraints.CENTER;
		
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(txtFieldSemilla, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		add(labNumPuntos, c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(txtFieldNumPuntos, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		add(butCalcPi, c);
		
		c.gridx = 1;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		add(butCargarPrueba, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		add(labTxtValPi, c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		add(labValPi, c);
	}

	public long getSemilla() {
		
		long semilla = 0;
		
		try {
			semilla = Long.parseLong(txtFieldSemilla.getText());			
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Inserte un formato adecuado para la semilla", "Error", 0);
		}		
		
		return semilla;
	}
	
	public double getNumPuntos() {
		
		double puntos = 0;
		
		try {
			puntos = Double.parseDouble(txtFieldNumPuntos.getText());			
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Inserte un formato adecuado para la cantidad de números", "Error", 0);
		}		
		
		return puntos;
	}
	
	public void setLabValPi(double pi) {
		labValPi.setText(String.valueOf(pi));
	}
	
	public JButton getButCalcPi() {
		return butCalcPi;
	}
	
	public JButton getButCargarPrueba() {
		return butCargarPrueba;
	}
}