package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import java.awt.SystemColor;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.ScrollPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class ConsPessoa extends JInternalFrame {
	private JTextField txtEtapa1;
	private JTextField txtEspaco;
	private JTextField txtEtapa2;
	private JTable table;

//	check if window is ON - true | OFF - false
	private static boolean status;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() {
	 * 
	 * try { ConsPessoa frame = new ConsPessoa(); frame.setVisible(true); }
	 * catch (Exception e) { e.printStackTrace(); }
	 * 
	 * } }); }
	 */

	/**
	 * Create the frame.
	 */
	public ConsPessoa() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				dispose();
				status = false;
			}
		});
		setClosable(true);
		setTitle("Consultar Pessoa");
		setBounds(100, 100, 330, 500);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(115, 225, 90, 30);
		panel.add(btnConsultar);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Locais de Treinamento", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 120, 215)));
		panel_1.setBounds(10, 266, 300, 193);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblEtapa1 = new JLabel("Etapa 1");
		lblEtapa1.setBounds(10, 22, 280, 14);
		panel_1.add(lblEtapa1);

		txtEtapa1 = new JTextField();
		txtEtapa1.setEditable(false);
		txtEtapa1.setBounds(10, 47, 280, 20);
		panel_1.add(txtEtapa1);
		txtEtapa1.setColumns(10);

		JLabel lblEtapa2 = new JLabel("Etapa 2");
		lblEtapa2.setBounds(10, 78, 46, 14);
		panel_1.add(lblEtapa2);

		txtEtapa2 = new JTextField();
		txtEtapa2.setEditable(false);
		txtEtapa2.setBounds(10, 101, 280, 20);
		panel_1.add(txtEtapa2);
		txtEtapa2.setColumns(10);

		txtEspaco = new JTextField();
		txtEspaco.setEditable(false);
		txtEspaco.setBounds(10, 157, 280, 20);
		panel_1.add(txtEspaco);
		txtEspaco.setColumns(10);

		JLabel lblNewLabel = new JLabel("Espaco de Cafe");
		lblNewLabel.setBounds(10, 132, 280, 14);
		panel_1.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 300, 200);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

	}

	public static boolean getStatus() {
		return status;
	}

	public static void abrir() {
		status = true;
	}

}
