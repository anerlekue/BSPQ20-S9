package es.deusto.spq.GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import es.deusto.spq.client.Controller;
import es.deusto.spq.client.UtilsImagenes;
import es.deusto.spq.data.Piso;
import es.deusto.spq.data.Imagen;

/**
 * Esta clase se utiliza para crear nuevos pisos y añadirlos a la base de datos
 * 
 * @author jonuraga
 *
 */

public class VentanaFormularioPisos {

	private JFrame frame;
	private JTextField tfNombrePiso;
	private JTextField tfCiudad;
	private JTextField tfNumHuespedes;
	private JTextField tfNumHabs;
	private JTextField tfCoste;
	private JButton bSubirFotos;


	BufferedImage image;
	String imagePath;

	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	//
	// @Override
	// public void run() {
	// try {
	// VentanaFormularioPisos window = new VentanaFormularioPisos();
	// window.frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// }
	// });
	// }

	/**
	 * Constructor de la ventana que lo que hace es llamar al método initialize()
	 */

	public VentanaFormularioPisos() {
		initialize();
	}

	/**
	 * Método que inicializa las variables de la ventana
	 */
	private void initialize() {

		frame = new JFrame("Nuevo piso");
		frame.setBounds(100, 100, 500, 650);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		JPanel globalPanel = new JPanel();
		frame.getContentPane().add(globalPanel, BorderLayout.CENTER);
		globalPanel.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre del piso: ");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(75, 150, 100, 20);
		globalPanel.add(lblNombre);

		JLabel lblCiudad = new JLabel("Ciudad: ");
		lblCiudad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCiudad.setBounds(75, 200, 100, 20);
		globalPanel.add(lblCiudad);

		JLabel lblNumInquilinos = new JLabel("Nº de huéspedes: ");
		lblNumInquilinos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNumInquilinos.setBounds(75, 250, 100, 20);
		globalPanel.add(lblNumInquilinos);

		JLabel lblNumHabs = new JLabel("Nº de habitaciones: ");
		lblNumHabs.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNumHabs.setBounds(75, 300, 120, 20);
		globalPanel.add(lblNumHabs);

		JLabel lblCoste = new JLabel("Coste mensual: ");
		lblCoste.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCoste.setBounds(75, 350, 120, 20);
		globalPanel.add(lblCoste);

		JLabel lblTitulo = new JLabel("NUEVO PISO");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitulo.setBounds(175, 50, 150, 40);
		globalPanel.add(lblTitulo);

		JButton btnAtras = new JButton("Atrás");
		btnAtras.setFocusPainted(false);
		btnAtras.setBounds(90, 500, 90, 25);
		globalPanel.add(btnAtras);

		JButton btnAddPiso = new JButton("Añadir piso");
		btnAddPiso.setFocusPainted(false);
		btnAddPiso.setBounds(230, 500, 150, 25);
		globalPanel.add(btnAddPiso);

		tfNombrePiso = new JTextField();
		tfNombrePiso.setColumns(10);
		tfNombrePiso.setBounds(250, 150, 150, 25);
		globalPanel.add(tfNombrePiso);

		tfCiudad = new JTextField();
		tfCiudad.setColumns(10);
		tfCiudad.setBounds(250, 200, 150, 25);
		globalPanel.add(tfCiudad);

		tfNumHuespedes = new JTextField();
		tfNumHuespedes.setColumns(10);
		tfNumHuespedes.setBounds(250, 250, 150, 25);
		globalPanel.add(tfNumHuespedes);

		tfNumHabs = new JTextField();
		tfNumHabs.setColumns(10);
		tfNumHabs.setBounds(250, 300, 150, 25);
		globalPanel.add(tfNumHabs);

		tfCoste = new JTextField();
		tfCoste.setColumns(10);
		tfCoste.setBounds(250, 350, 150, 25);
		globalPanel.add(tfCoste);

		bSubirFotos = new JButton("Subir foto");
		bSubirFotos.setBounds(250, 400, 100, 50);
		globalPanel.add(bSubirFotos);

		imagePath = " ";

		frame.setVisible(true);

		bSubirFotos.addActionListener(ev -> {
			JFileChooser fc = new JFileChooser(System.getProperty("user.home"));
			fc.addChoosableFileFilter(
					new FileNameExtensionFilter("Image files", new String[] { "png", "jpg", "jpeg", "gif" }));
			if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				try {
					image = ImageIO.read(fc.getSelectedFile());
					imagePath = fc.getSelectedFile().toString();
					
				} catch (Exception ex) {
					
					ex.printStackTrace();
				}
			}
		});

		btnAddPiso.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MetodosGUI mGUI = new MetodosGUI();
				mGUI.comprobarVacio(tfNombrePiso);
				mGUI.comprobarVacio(tfCiudad);
				mGUI.comprobarVacio(tfNumHuespedes);
				mGUI.comprobarVacio(tfNumHabs);
				mGUI.comprobarVacio(tfCoste);
				if (mGUI.comprobarVacio(tfNombrePiso) && mGUI.comprobarVacio(tfCiudad)
						&& mGUI.comprobarVacio(tfNumHuespedes) && mGUI.comprobarVacio(tfNumHabs)
						&& mGUI.comprobarVacio(tfCoste)) {
					Piso pisoNuevo = new Piso();

					if (image != null) {
						
						List<String> imagenes = new ArrayList<String>();
						imagenes.add(Controller.getInstance().getUsuario().getNickname() + tfNombrePiso.getText());
					
						pisoNuevo.setImagenes2(imagenes);
	

					}
					

					pisoNuevo.setNombre(tfNombrePiso.getText());
					pisoNuevo.setLocalizacion(tfCiudad.getText());
					pisoNuevo.setCoste(Double.parseDouble(tfCoste.getText()));
					pisoNuevo.setAlquilado(false);
					pisoNuevo.setValoracion(0);
					pisoNuevo.setNumeroInquilinos(Integer.parseInt(tfNumHuespedes.getText()));
					pisoNuevo.setnHab(Integer.parseInt(tfNumHabs.getText()));
					String idS = tfNumHuespedes.getText() + tfNumHabs.getText() + tfCoste.getText();
					int id = Integer.parseInt(idS);
					pisoNuevo.setId(id);
					Response response = Controller.getInstance().guardarNuevoPiso(pisoNuevo);
					if (response.getStatus() == Status.OK.getStatusCode()) {
						if(image != null){
							UtilsImagenes.imageIoWrite(Controller.getInstance().getUsuario().getNickname() + pisoNuevo.getNombre(), image);
						}
						
						JOptionPane.showMessageDialog(null, "Piso guardado correctamente", "Aviso",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

	}

	/**
	 * Este método se utiliza para hacer visible la ventana
	 * 
	 * @param b true para que la ventana sea visible o false en caso contrario
	 */

	public void setVisible(boolean b) {
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new VentanaFormularioPisos();
	}
}
