package view;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.bean.Pessoa;
import model.dao.PessoaDAO;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class CadPessoa extends JInternalFrame {
	private JTextField txtNome;
	private JTextField txtSobrenome;
	private List<Pessoa> lstPessoas;

	// check if window is ON - true | OFF - false
	private static boolean status;
	private JTable tablePessoa;

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
		
		PessoaDAO pd = new PessoaDAO();
		lstPessoas = pd.listar();
		
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
		tablePessoa.setBorder(new LineBorder(new Color(0, 0, 0)));
		tablePessoa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePessoa.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Sobrenome"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tablePessoa.getColumnModel().getColumn(0).setPreferredWidth(90);
		tablePessoa.getColumnModel().getColumn(0).setMinWidth(90);
		tablePessoa.getColumnModel().getColumn(1).setResizable(false);
		tablePessoa.getColumnModel().getColumn(1).setPreferredWidth(90);
		tablePessoa.getColumnModel().getColumn(1).setMinWidth(90);
		scrollPane.setViewportView(tablePessoa);
		
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

	}

	public static boolean getStatus() {
		return status;
	}

	public static void abrir() {
		status = true;
	}
	
	private void btAdicionarActionPerformed(java.awt.event.ActionEvent evt) {

        Pessoa p = new Pessoa();
        p.setNome(txtNome.getText());
        p.setSobrenome(txtSobrenome.getText());
        lstPessoas.add(p);
        txtNome.setText("");
        txtSobrenome.setText("");
        
    }
}
