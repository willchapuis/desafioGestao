package view;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import connection.ModuloConexao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.bean.EspacoCafe;
import connection.ModuloConexao;

public class CadEspaco extends JInternalFrame {

	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	private JTextField txtEspaco1;
	private JTextField txtEspaco2;
	private List<EspacoCafe> lstEspacos;

//	check if window is ON - true | OFF - false
	private static boolean status;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() {
	 * 
	 * try { CadEspaco frame = new CadEspaco(); frame.setVisible(true); }
	 * catch (Exception e) { e.printStackTrace(); }
	 * 
	 * } }); }
	 */

	/**
	 * Create the frame.
	 */
	public CadEspaco() {
		
		//EspacoCafeDAO_OLD ed = new EspacoCafeDAO_OLD();
		//lstEspacos = ed.listar();
		
		// componentes
		setClosable(true);
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				dispose();
				status = false;
			}
		});
		setTitle("Cadastrar Espaco de Cafe");
		setBounds(100, 100, 350, 170);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Formulario de Cadastro", TitledBorder.LEADING, TitledBorder.TOP, null,
				SystemColor.textHighlight));
		panel.setBounds(10, 11, 320, 118);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblEspaco1 = new JLabel("Nome - Espaco 1");
		lblEspaco1.setBounds(10, 21, 100, 14);
		panel.add(lblEspaco1);

		JLabel lblEspaco2 = new JLabel("Nome - Espaco 2");
		lblEspaco2.setBounds(10, 46, 100, 14);
		panel.add(lblEspaco2);

		txtEspaco1 = new JTextField("Espaco A");
		txtEspaco1.setBounds(120, 18, 190, 20);
		panel.add(txtEspaco1);
		txtEspaco1.setColumns(10);

		txtEspaco2 = new JTextField("Espaco B");
		txtEspaco2.setColumns(10);
		txtEspaco2.setBounds(120, 43, 190, 20);
		panel.add(txtEspaco2);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btSalvarActionPerformed(evt);
			}
		});
		btnSalvar.setBounds(120, 74, 90, 30);
		panel.add(btnSalvar);
		// fim dos componentes
		
		conn = ModuloConexao.getConnection();
		
		preencherText();

	}

	public static boolean getStatus() {
		return status;
	}

	public static void abrir() {
		status = true;
	}
	
	private void preencherText() {
		String sql = "select * from espaco_cafe where id_espaco_cafe=?";
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "1");
			rs = stmt.executeQuery();
			if (rs.next()) {
				txtEspaco1.setText(rs.getString(2));
			} else {

			}
		} catch (Exception e) {
			System.out.println("erro ao consultar: "+e.getMessage());
		}
	}
	
	private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {
		
		EspacoCafe e = new EspacoCafe();
		
        e.setNome(txtEspaco1.getText());
        lstEspacos.set(0, e);
        
        e.setNome(txtEspaco2.getText());
        lstEspacos.set(1, e);
        
    }

}
