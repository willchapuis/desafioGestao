package view;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
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
import model.dao.SalaEventoDAO;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

import model.dao.SalaEventoDAO;

public class CadSala extends JInternalFrame {
	private JTextField txtNome;
	private JSpinner spinnerLotacao;
	private SpinnerNumberModel aux;
	private List<SalaEvento> lstSalas;

//	check if window is ON - true | OFF - false
	private static boolean status;
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
		
		SalaEventoDAO sd = new SalaEventoDAO();
		lstSalas = sd.listar();
		
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				dispose();
				status = false;
			}
		});
		setClosable(true);
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
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Lotacao"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(0).setMinWidth(90);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setMinWidth(60);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
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

	}
	
	public static boolean getStatus() {
		return status;
	}
	
	public static void abrir() {
		status = true;
	}
	
	private void btAdicionarActionPerformed(java.awt.event.ActionEvent evt) {

        SalaEvento s = new SalaEvento();
        s.setNome(txtNome.getText());
        
        //	Para pegar valores digitados no spinner
        try {
        	spinnerLotacao.commitEdit();
        }catch (java.text.ParseException e) {
        	System.out.println("erro ao salvar lotacao: "+e.getMessage());
		}
        
        s.setLotacao((Integer)spinnerLotacao.getValue());
        lstSalas.add(s);
        txtNome.setText("");
        spinnerLotacao.setModel(aux);
        
    }
}
