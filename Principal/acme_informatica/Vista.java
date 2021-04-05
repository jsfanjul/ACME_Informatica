package acme_informatica;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class Vista extends JFrame {
	
	private JPanel pnlPrincipal;
	
	public Vista() {
		
		setSize(new Dimension(1280, 1000));
		setTitle("ACME INFORMATICA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		pnlPrincipal = new JPanel();
		pnlPrincipal.setBounds(10, 11, 1244, 917);
		getContentPane().add(pnlPrincipal);
		pnlPrincipal.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnuCanciones = new JMenu("Productos");
		mnuCanciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.add(mnuCanciones);
		
		JMenuItem mnuPMantenimiento = new JMenuItem("Mantenimiento");
		mnuPMantenimiento.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnuPMantenimiento.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				pnlPrincipal.removeAll();
				pnlPrincipal.add(new ProductosPNL());
				repaint();
				validate();
			}
		});
		mnuCanciones.add(mnuPMantenimiento);
		
		JMenuItem mnuConsultasStock = new JMenuItem("Consultas Stock");
		mnuConsultasStock.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				pnlPrincipal.removeAll();
				pnlPrincipal.add(new StockPNL());
				repaint();
				validate();
				
			}
			
		});
		mnuConsultasStock.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnuConsultasStock.setBackground(UIManager.getColor("Button.background"));
		mnuCanciones.add(mnuConsultasStock);
		
		JMenu mnuCategorias = new JMenu("Categorias");
		mnuCategorias.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.add(mnuCategorias);
		
		JMenuItem mnuCATMantenimiento = new JMenuItem("Mantenimiento");
		mnuCATMantenimiento.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnuCATMantenimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlPrincipal.removeAll();
				pnlPrincipal.add(new CategoriasPNL());
				repaint();
				validate();
			}
		});
		mnuCategorias.add(mnuCATMantenimiento);
		
		JMenu mnuClientes = new JMenu("Clientes");
		mnuClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.add(mnuClientes);
		
		JMenuItem mnuCMantenimiento = new JMenuItem("Mantenimiento");
		mnuCMantenimiento.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnuCMantenimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pnlPrincipal.removeAll();
				pnlPrincipal.add(new ClientesPNL());
				repaint();
				validate();
			}
		});
		mnuClientes.add(mnuCMantenimiento);
		
		JMenu mnuProveedores = new JMenu("Proveedores");
		mnuProveedores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.add(mnuProveedores);
		
		JMenuItem mnu_mant_prov = new JMenuItem("Mantenimiento");
		mnu_mant_prov.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				pnlPrincipal.removeAll();
				pnlPrincipal.add(new ProveedoresPNL());
				repaint();
				validate();
				
			}
			
		});
		mnu_mant_prov.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnuProveedores.add(mnu_mant_prov);
		
		JMenu mnuAlbaranes = new JMenu("Albaranes");
		mnuAlbaranes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.add(mnuAlbaranes);
		
		JMenuItem mnuAMantenimiento = new JMenuItem("Mantenimiento");
		mnuAMantenimiento.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnuAMantenimiento.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				pnlPrincipal.removeAll();
				pnlPrincipal.add(new Albaran_Seleccion());
				repaint();
				validate();
			}
		});
		mnuAlbaranes.add(mnuAMantenimiento);
		
		JMenu mnuFacturas = new JMenu("Facturas");
		mnuFacturas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.add(mnuFacturas);
		
		JMenuItem mnuFactMantenimiento = new JMenuItem("Mantenimiento");
		mnuFactMantenimiento.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				pnlPrincipal.removeAll();
				pnlPrincipal.add(new Factura_Seleccion());
				repaint();
				validate();
				
			}
			
		});
		mnuFactMantenimiento.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnuFactMantenimiento.setBackground(UIManager.getColor("Button.background"));
		mnuFacturas.add(mnuFactMantenimiento);
		
		JMenuItem mnuFMantenimiento = new JMenuItem("Mantenimiento");
		mnuFMantenimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pnlPrincipal.removeAll();
				//pnlPrincipal.add(new Generos_V_PNL());
				repaint();
				validate();
			}
		});
		mnuAlbaranes.add(mnuAMantenimiento);
	}

	private static final long serialVersionUID = 1L;
	

	public static void main(String[] args) {
		Vista v = new Vista();
		v.setLocationRelativeTo(null);
		v.setVisible(true);
	}
}

