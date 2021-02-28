package view;

import java.awt.EventQueue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JButton;


import connection.ModuloConexao;
import model.bean.EspacoCafe;

public class ConsEspaco extends JInternalFrame {
	
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

//	check if window is ON - true | OFF - false
	private static boolean status;
	private JTable tableL;
	private JComboBox cboEspaco;
	private JButton btnConsultar;
	private JScrollPane scrollPaneL;

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
		
		
		
		// componentes
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

		scrollPaneL = new JScrollPane();
		scrollPaneL.setBounds(10, 50, 300, 308);
		getContentPane().add(scrollPaneL);

		tableL = new JTable();
		scrollPaneL.setViewportView(tableL);

		cboEspaco = new JComboBox();
		cboEspaco.setBounds(10, 11, 190, 30);
		getContentPane().add(cboEspaco);

		btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(220, 11, 90, 30);
		getContentPane().add(btnConsultar);
		// fim dos componentes
		
		conn = ModuloConexao.getConnection();

	}

	public static boolean getStatus() {
		return status;
	}

	public static void abrir() {
		status = true;
	}
	
	private void receberInfoBanco() {
		String sql = "select * from espaco_cafe where id_espaco_cafe=?";
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "1");
			rs = stmt.executeQuery();
			if (rs.next()) {
				cboEspaco.setSelectedItem(rs.getString(2));
			} else {
				System.out.println("erro ao buscar informacoes no banco no metodo: ConsEspaco.receberInfoBanco()");
			}
		} catch (Exception e) {
			System.out.println("erro ao consultar: "+e.getMessage());
		}
	}
	
	private void consultar() {
		String sql = "select * from pessoa where id_espaco_cafe=?";
	}
}
