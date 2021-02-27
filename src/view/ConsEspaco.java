package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class ConsEspaco extends JInternalFrame {

//	check if window is ON - true | OFF - false
	private static boolean status;
	private JTable tableL;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() {
	 * 
	 * try { ConsEspaco frame = new ConsEspaco(); frame.setVisible(true); }
	 * catch (Exception e) { e.printStackTrace(); }
	 * 
	 * } }); }
	 */

	/**
	 * Create the frame.
	 */
	public ConsEspaco() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				dispose();
				status = false;
			}
		});
		setClosable(true);
		setTitle("Consultar Espaco de Cafe");
		setBounds(100, 100, 330, 399);
		getContentPane().setLayout(null);

		JScrollPane scrollPaneL = new JScrollPane();
		scrollPaneL.setBounds(10, 50, 300, 308);
		getContentPane().add(scrollPaneL);

		tableL = new JTable();
		scrollPaneL.setViewportView(tableL);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 11, 190, 30);
		getContentPane().add(comboBox);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(220, 11, 90, 30);
		getContentPane().add(btnConsultar);

	}

	public static boolean getStatus() {
		return status;
	}

	public static void abrir() {
		status = true;
	}
}
