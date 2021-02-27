package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.*;
import connection.ModuloConexao;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JDesktopPane;
import java.awt.CardLayout;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JFrame implements ActionListener {

	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	private JPanel contentPane;
	
	public static JDesktopPane desktop;
	private JMenuItem mnitemCadPessoa;
	private JMenuItem mnitemCadSalaEvento;
	private JMenuItem mnitemCadEspacoCafe;
	private JMenuItem mnitemConsPessoa;
	private JMenuItem mnitemConsSalaEvento;
	private JMenuItem mnitemConsEspacoCafe;
	
	private JInternalFrame cadPessoa;
	private JInternalFrame cadSala;
	private JInternalFrame cadEspaco;
	private JInternalFrame consPessoa;
	private JInternalFrame consSala;
	private JInternalFrame consEspaco;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setExtendedState(MAXIMIZED_BOTH);
					frame.setVisible(true);
				} catch (Exception e) {
					System.out.println("erro ao abrir tela principal: "+e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		
		// componentes
		setTitle("DesafioGestao");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCadastrar = new JMenu("Cadastrar");
		menuBar.add(mnCadastrar);
		
		mnitemCadPessoa = new JMenuItem("Pessoa");
		mnitemCadPessoa.addActionListener(this);
		mnCadastrar.add(mnitemCadPessoa);
		
		mnitemCadSalaEvento = new JMenuItem("Sala do Evento");
		mnitemCadSalaEvento.addActionListener(this);
		mnCadastrar.add(mnitemCadSalaEvento);
		
		mnitemCadEspacoCafe = new JMenuItem("Espaco de Cafe");
		mnitemCadEspacoCafe.addActionListener(this);
		mnCadastrar.add(mnitemCadEspacoCafe);
		
		JMenu mnConsultar = new JMenu("Consultar");
		menuBar.add(mnConsultar);
		
		mnitemConsPessoa = new JMenuItem("Pessoa");
		mnitemConsPessoa.addActionListener(this);
		mnConsultar.add(mnitemConsPessoa);
		
		mnitemConsSalaEvento = new JMenuItem("Sala do Evento");
		mnitemConsSalaEvento.addActionListener(this);
		mnConsultar.add(mnitemConsSalaEvento);
		
		mnitemConsEspacoCafe = new JMenuItem("Espaco de Cafe");
		mnitemConsEspacoCafe.addActionListener(this);
		mnConsultar.add(mnitemConsEspacoCafe);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JDesktopPane desktop = new JDesktopPane();
		desktop.setBackground(SystemColor.activeCaption);
		contentPane.add(desktop, "name_126310450005800");
		// fim dos componentes
		
		conn = ModuloConexao.conector();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mnitemConsEspacoCafe) {
			actionPerformedMnitemConsEspacoCafe(e);
		}
		if (e.getSource() == mnitemConsSalaEvento) {
			actionPerformedMnitemConsSalaEvento(e);
		}
		if (e.getSource() == mnitemConsPessoa) {
			actionPerformedMnitemConsPessoa(e);
		}
		if (e.getSource() == mnitemCadEspacoCafe) {
			actionPerformedMnitemCadEspacoCafe(e);
		}
		if (e.getSource() == mnitemCadSalaEvento) {
			actionPerformedMnitemCadSalaEvento(e);
		}
		if (e.getSource() == mnitemCadPessoa) {
			actionPerformedMnitemCadPessoa(e);
		}
	}
	
	protected void actionPerformedMnitemCadPessoa(ActionEvent e) {
		try {
			if(CadPessoa.getStatus() == false) {
				cadPessoa = new CadPessoa();
				cadPessoa.setVisible(true);
				add(cadPessoa);
				CadPessoa.abrir();
			}
			else {
				JOptionPane.showMessageDialog(null, "Ja se encontra ativa");
			}
		} catch (Exception eCadP) {
			System.out.println("erro ao abrir tela cadatro de pessoa: "+eCadP.getMessage());
		}
	}
	
	protected void actionPerformedMnitemCadSalaEvento(ActionEvent e) {
		try {
			if(CadSala.getStatus() == false) {
				cadSala = new CadSala();
				cadSala.setVisible(true);
				add(cadSala);
				CadSala.abrir();
			}
			else {
				JOptionPane.showMessageDialog(null, "Ja se encontra ativa");
			}
		} catch (Exception eCadS) {
			System.out.println("erro ao abrir tela cadatro de sala: "+eCadS.getMessage());
		}
	}
	
	protected void actionPerformedMnitemCadEspacoCafe(ActionEvent e) {
		try {
			if(CadEspaco.getStatus() == false) {
				cadEspaco = new CadEspaco();
				cadEspaco.setVisible(true);
				add(cadEspaco);
				CadEspaco.abrir();
			}
			else {
				JOptionPane.showMessageDialog(null, "Ja se encontra ativa");
			}
		} catch (Exception eCadE) {
			System.out.println("erro ao abrir tela cadatro de espaco: "+eCadE.getMessage());
		}
	}
	
	protected void actionPerformedMnitemConsPessoa(ActionEvent e) {
		try {
			if(ConsPessoa.getStatus() == false) {
				consPessoa = new ConsPessoa();
				consPessoa.setVisible(true);
				add(consPessoa);
				ConsPessoa.abrir();
			}
			else {
				JOptionPane.showMessageDialog(null, "Ja se encontra ativa");
			}
		} catch (Exception eConsP) {
			System.out.println("erro ao abrir tela cadatro de espaco: "+eConsP.getMessage());
		}
	}
	
	protected void actionPerformedMnitemConsSalaEvento(ActionEvent e) {
		try {
			if(ConsSala.getStatus() == false) {
				consSala = new ConsSala();
				consSala.setVisible(true);
				add(consSala);
				ConsSala.abrir();
			}
			else {
				JOptionPane.showMessageDialog(null, "Ja se encontra ativa");
			}
		} catch (Exception eConsS) {
			System.out.println("erro ao abrir tela cadatro de espaco: "+eConsS.getMessage());
		}
	}
	
	protected void actionPerformedMnitemConsEspacoCafe(ActionEvent e) {
		try {
			if(ConsEspaco.getStatus() == false) {
				consEspaco = new ConsEspaco();
				consEspaco.setVisible(true);
				add(consEspaco);
				ConsEspaco.abrir();
			}
			else {
				JOptionPane.showMessageDialog(null, "Ja se encontra ativa");
			}
		} catch (Exception eConsE) {
			System.out.println("erro ao abrir tela cadatro de espaco: "+eConsE.getMessage());
		}
	}
}
