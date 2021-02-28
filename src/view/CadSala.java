package view;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.bean.SalaEvento;
import model.dao.SalaEventoDAO_OLD;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

import model.dao.SalaEventoDAO_OLD;

public class CadSala extends JInternalFrame {
	
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	private List<SalaEvento> lstSalas;

//	check if window is ON - true | OFF - false
	private static boolean status;
	
	private JTextField txtNome;
	private JSpinner spinnerLotacao;
	private SpinnerNumberModel aux;
	private JTable table;
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				/*
				 * try { CadSala frame = new CadSala(); frame.setVisible(true); } catch
				 * (Exception e) { e.printStackTrace(); }
				 *
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public CadSala() {
		
		//SalaEventoDAO_OLD sd = new SalaEventoDAO_OLD();
		//lstSalas = sd.listar();
		
		// componentes
		setClosable(true);
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				dispose();
				status = false;
			}
		});
		setTitle("Cadastrar Sala do Evento");
		setBounds(100, 100, 400, 300);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Formulario de Cadastro", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel.setBounds(10, 140, 370, 80);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 21, 100, 14);
		panel.add(lblNome);
		
		JLabel lblLotacao = new JLabel("Lotacao");
		lblLotacao.setBounds(10, 46, 100, 14);
		panel.add(lblLotacao);
		
		txtNome = new JTextField();
		txtNome.setBounds(120, 18, 240, 20);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		spinnerLotacao = new JSpinner();
		aux = new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1));
		spinnerLotacao.setModel(aux);
		spinnerLotacao.setBounds(120, 43, 240, 20);
		panel.add(spinnerLotacao);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 11, 365, 119);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(80, 230, 110, 30);
		getContentPane().add(btnAdicionar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setBounds(210, 230, 110, 30);
		getContentPane().add(btnRemover);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btAdicionarActionPerformed(evt);
			}
		});
		// fim dos componentes

	}
	
	public static boolean getStatus() {
		return status;
	}
	
	public static void abrir() {
		status = true;
	}
	
	private void btAdicionarActionPerformed(java.awt.event.ActionEvent evt) {
		adicionar();
    }
	
	private void adicionar() {
		String sql = "insert into sala_evento (nome, lotacao) values (?, ?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, txtNome.getText());
			stmt.setInt(2, (Integer) spinnerLotacao.getValue());
			
			stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("erro ao adicionar: "+e.getMessage());
		}
	}
}
