package es.deusto.spq.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import es.deusto.spq.client.Controller;
import es.deusto.spq.data.Factura;
import es.deusto.spq.data.Pago;
import es.deusto.spq.data.Piso;
import es.deusto.spq.data.Usuario;
import es.deusto.spq.server.DAOFactory;


/**
 * Esta ventana se utiliza para realizar la acción de pagar
 * @author jonuraga
 *
 */
public class VentanaPago {
	
	private JFrame frame;
	private JLabel lblEmail, lblPrecioTit, lblPrecio;
	private JTextField tfEmail;
	private JButton btnPagar, btnCancel;
	
	MetodosGUI mgui = new MetodosGUI();
	
	public static void main(String[] args) {
		EventQueue.invokeLater( new Runnable() {
			
			@Override
			public void run() {
				try {
					VentanaPago window = new VentanaPago(new Piso("", 0.0));
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Constructor de la clase que recibe el piso con el precio a pagar y llama al método de inicializar 
	 * @param piso
	 */
	public VentanaPago(Piso piso) {
		initialize(piso);
		frame.setVisible(true);
	}
	
	/**
	 * Con este método se inicializan las variables de la ventana y se manejan las acciones al pulsar los botones
	 * @param piso
	 */

	private void initialize(Piso piso) {
		frame = new JFrame("Pago");
		frame.setBounds(100, 100, 400, 230);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lblEmail = new JLabel("Escriba su email: ");
		lblEmail.setBounds(51, 94, 120, 30);
		panel.add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(183, 89, 173, 40);
		panel.add(tfEmail);
		
		lblPrecioTit = new JLabel("Precio a pagar: ");
		lblPrecioTit.setBounds(97, 52, 120, 30);
		panel.add(lblPrecioTit);
		
		lblPrecio = new JLabel(Double.toString(piso.getCoste().doubleValue()));
		lblPrecio.setBounds(229, 52, 70, 30);
		panel.add(lblPrecio);
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setFocusPainted(false);
		btnCancel.setBounds(113, 141, 98, 40);
		panel.add(btnCancel);
		
		btnPagar = new JButton("Pagar");
		btnPagar.setFocusPainted(false);
		btnPagar.setBounds(223, 141, 91, 40);
		panel.add(btnPagar);
		
		btnCancel.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Usuario u = Controller.getInstance().getUsuario();
				new VentanaInformacion(piso, u);
				
			}
		});
		
		btnPagar.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!mgui.comprobarVacio(tfEmail)) {
					mgui.mensajeError(tfEmail, "Debes rellenar el email");
				}
				if (!mgui.validarEmail(tfEmail.getText())) {
					mgui.mensajeError(tfEmail, "El email introducido no es válido");
				} else {
					Pago p = new Pago();
					p.setEmail(tfEmail.getText());
					p.setPrecio((int) piso.getCoste().doubleValue());
					Response r = Controller.getInstance().realizarPago(p);
					System.out.println(r);
					if (r.getStatus() == Status.OK.getStatusCode()) {
						JOptionPane.showMessageDialog(null, "Pago", "Exitoso", JOptionPane.INFORMATION_MESSAGE);
						Factura f = new Factura("fecha", piso, Controller.getInstance().getUsuario().getNickname(),"arrendador");
						DAOFactory.getInstance().createUsuarioDAO().guardar(f);
						List<Piso> pisos = new ArrayList<Piso>();
						pisos = Controller.getInstance().getPisos();
						
						new VentanaListaPisos(pisos, pisos);
						frame.dispose();
						
						pisos = Controller.getInstance().getPisos();
					} else {
						JOptionPane.showMessageDialog(null, "Pago", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
	}
}
