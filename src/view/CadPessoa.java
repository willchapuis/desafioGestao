package view;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import java.awt.Color;

import model.bean.Pessoa;
import model.bean.SalaEvento;
import model.dao.PessoaDAO;

import java.sql.*;
import connection.ModuloConexao;

public class CadPessoa extends JInternalFrame {
	
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	private List<Pessoa> lstPessoas;
	private List<SalaEvento> lstSalas;

	// check if window is ON - true | OFF - false
	private static boolean status;
	
	private JTextField txtNome;
	private JTextField txtSobrenome;
	private JTable tablePessoa;
	private JButton btnAdicionar;
	private JButton btnRemover;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() {
	 * 
	 * try { CadPessoa frame = new CadPessoa(); frame.setVisible(true); }
	 * catch (Exception e) { e.printStackTrace(); }
	 * 
	 * } }); }
	 */

	/**
	 * Create the frame.
	 */
	public CadPessoa() {
		
		//PessoaDAO_OLD pd = new PessoaDAO_OLD();
		//lstPessoas = pd.listar();
		
		// componentes
		setClosable(true);
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				dispose();
				status = false;
			}
		});
		setTitle("Cadastrar Pessoa");
		setBounds(100, 100, 400, 300);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Formulario de Cadastro", TitledBorder.LEADING, TitledBorder.TOP, null,
				SystemColor.textHighlight));
		panel.setBounds(10, 140, 370, 80);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 21, 100, 14);
		panel.add(lblNome);

		JLabel lblSobrenome = new JLabel("Sobrenome");
		lblSobrenome.setBounds(10, 46, 100, 14);
		panel.add(lblSobrenome);

		txtNome = new JTextField();
		txtNome.setBounds(120, 18, 240, 20);
		panel.add(txtNome);
		txtNome.setColumns(10);

		txtSobrenome = new JTextField();
		txtSobrenome.setBounds(120, 43, 240, 20);
		panel.add(txtSobrenome);
		txtSobrenome.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 11, 365, 119);
		getContentPane().add(scrollPane);
		
		tablePessoa = new JTable();
		tablePessoa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tablePessoa);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(80, 230, 110, 30);
		getContentPane().add(btnAdicionar);
		
		btnRemover = new JButton("Remover");
		btnRemover.setBounds(210, 230, 110, 30);
		getContentPane().add(btnRemover);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btAdicionarActionPerformed(evt);
			}
		});
		// fim dos componentes
		
		conn = ModuloConexao.getConnection();

	}

	// metodo para receber o status da janela
	public static boolean getStatus() {
		return status;
	}

	// metodo para setar o status da janela como ativa
	public static void abrir() {
		status = true;
	}
	
	private void btAdicionarActionPerformed(java.awt.event.ActionEvent evt) {
		Pessoa p = new Pessoa();
        PessoaDAO dao = new PessoaDAO();
        Integer numSalas, numPessoas;
        
        conn = ModuloConexao.getConnection();
        numSalas = dao.getNumRows("sala_evento");
        ModuloConexao.closeConnection(conn, stmt, rs);
        
        conn = ModuloConexao.getConnection();
        numPessoas = dao.getNumRows("pessoa");
        ModuloConexao.closeConnection(conn, stmt, rs);
        
        //pAnt = dao.getPessoa(numPessoas)
        
        //pNew.setNome(txtNome.getText());
        //pNew.setSobrenome(txtSobrenome.getText());
        
        
        
        //dao.insert(p);
    }
	
	// metodo para adicionar pessoas
	private void adicionar() {
		String sql = "insert into pessoa (nome, sobrenome, id_sala_etapa1, id_sala_etapa2, id_espaco) values (?, ?, ?, ?, ?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, txtNome.getText());
			stmt.setString(2, txtSobrenome.getText());
			
			
			
			int adicionado = stmt.executeUpdate();
			if (adicionado > 0) {
				JOptionPane.showMessageDialog(null, "Pessoa adicionada com sucesso");
				txtNome.setText(null);
				txtSobrenome.setText(null);
			}
			
		} catch (Exception e) {
			System.out.println("erro ao adicionar: "+e.getMessage());
		}
	}
}
