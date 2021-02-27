package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JTable;

public class ConsSala extends JInternalFrame {

//	check if window is ON - true | OFF - false
	private static boolean status;
	private JTable tablePessoa;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() {
	 * 
	 * try { ConsSala frame = new ConsSala(); frame.setVisible(true); }
	 * catch (Exception e) { e.printStackTrace(); }
	 * 
	 * } }); }
	 */

	/**
	 * Create the frame.
	 */
	public ConsSala() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				dispose();
				status = false;
			}
		});
		setClosable(true);
		setTitle("Consultar Sala do Evento");
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout(null);

		JScrollPane scrollPaneL = new JScrollPane();
		scrollPaneL.setBounds(10, 11, 190, 348);
		getContentPane().add(scrollPaneL);

		JList listSala = new JList();
		listSala.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneL.setViewportView(listSala);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(220, 170, 90, 30);
		getContentPane().add(btnConsultar);

		JScrollPane scrollPaneR = new JScrollPane();
		scrollPaneR.setBounds(330, 11, 250, 348);
		getContentPane().add(scrollPaneR);

		tablePessoa = new JTable();
		scrollPaneR.setViewportView(tablePessoa);

	}

	public static boolean getStatus() {
		return status;
	}

	public static void abrir() {
		status = true;
	}
}
