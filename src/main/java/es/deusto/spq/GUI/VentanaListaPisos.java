package es.deusto.spq.GUI;




import java.util.List;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.BadLocationException;

import es.deusto.spq.client.Cliente;
import es.deusto.spq.data.Piso;
import es.deusto.spq.data.Usuario;
import java.util.ArrayList;
import javax.swing.JLabel;




public class VentanaListaPisos extends JFrame {

    private static final long serialVersionUID = 1L;

    
    private JTextField textBuscarPiso;
	private JScrollPane scroll;
	
	public VentanaListaPisos(List<Piso> pisos, List<Piso> pisos2, String hostname, String port, Usuario u1) {
    	
        setSize(620, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        
        textBuscarPiso = new JTextField();
        textBuscarPiso.setBounds(10, 11, 397, 20);
        new TextPrompt("Introduce cuidad para buscar piso", textBuscarPiso);
        getContentPane().add(textBuscarPiso);
        
        
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(432, 10, 100, 23);
        getContentPane().add(btnBuscar);
        
        JLabel labelFotoUsuario = new JLabel("");
        labelFotoUsuario.setBounds(562, 10, 25, 25);
        getContentPane().add(labelFotoUsuario);
        labelFotoUsuario.setIcon(new ImageIcon("resources/descarga.png"));
        
        JButton botonVolverAtras = new JButton("Salir");
        botonVolverAtras.setBounds(480, 417, 120, 23);
        getContentPane().add(botonVolverAtras);
        
        JButton botonComparar = new JButton("Comparar Pisos");
        botonComparar.setBounds(259, 417, 140, 23);
		getContentPane().add(botonComparar);
		
		JButton botonInicio = new JButton("Inicio");
		botonInicio.setBounds(40, 417, 120, 23);
		getContentPane().add(botonInicio);
        
        JPanel panelListaPisos= new JPanel();
        panelListaPisos.setBounds(10, 46, 594, 350);
        getContentPane().add(panelListaPisos);
        JTextArea textoPisos = new JTextArea();
        textoPisos.setColumns(50);
        textoPisos.setRows(20);
        textoPisos.setEditable(false);
        Font font1 = new Font("Arial", Font.ITALIC, 12);
        textoPisos.setFont(font1);
        textoPisos.setBounds(10, 46, 594, 229);
        scroll = new JScrollPane(textoPisos);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelListaPisos.add(scroll);
		
        
        if(pisos2.size() > 0) {
        for(int i = 0; i < pisos2.size(); i++) {
        	textoPisos.append("Nombre del piso:" + pisos2.get(i).getNombre().toUpperCase() + "\n" + "Precio: " + pisos2.get(i).getCoste()+ "\n"+ "Valoración: " + pisos2.get(i).getValoracion()+ "/5"+ "\n\n");
         
        }
        }else {
        	scroll.setVisible(false);
        	panelListaPisos.setVisible(false);
        	JLabel labelNoPisos = new JLabel("No se encontraron pisos.");
        	labelNoPisos.setBounds(200, 50, 150, 50);
        	getContentPane().add(labelNoPisos);
        }
        textoPisos.addMouseListener(new MouseAdapter() {

			@Override
       	    public void mousePressed(MouseEvent e) {
			if(e.getClickCount() == 2) {
          	 try {
					int line = textoPisos.getLineOfOffset( textoPisos.getCaretPosition() );
					int start = textoPisos.getLineStartOffset( line );
					int end = textoPisos.getLineEndOffset( line );
					String text = textoPisos.getDocument().getText(start, end - start);
					if(text.contains("piso")) {
						String[] partes = text.split(":");
						String nombre = partes[1];
						nombre = nombre.toLowerCase();
						nombre = nombre.replaceAll("\n", "");
						for(int i = 0; i < pisos.size(); i++) {
							if(nombre.contentEquals(pisos.get(i).getNombre().toLowerCase())) {
								System.out.println(pisos.get(i));
								new VentanaInformacion(pisos.get(i), u1, hostname, port);
								dispose();
							}
						}
						
					}else if(text.contains("Precio")) {
						line = line - 1;
						start = textoPisos.getLineStartOffset( line );
						end = textoPisos.getLineEndOffset( line );
						text = textoPisos.getDocument().getText(start, end - start);
						String[] partes = text.split(":");
						String nombre = partes[1];
						nombre = nombre.toLowerCase();
						nombre = nombre.replaceAll("\n", "");
						for(int i = 0; i < pisos.size(); i++) {
							if(nombre.contentEquals(pisos.get(i).getNombre().toLowerCase())) {
								new VentanaInformacion(pisos.get(i), u1, hostname, port);
								 dispose();
							}
						}
					}else if(text.contains("Valora")) {
						line = line - 2; 
						start = textoPisos.getLineStartOffset( line );
						end = textoPisos.getLineEndOffset( line );
						text = textoPisos.getDocument().getText(start, end - start);
						String[] partes = text.split(":");
						String nombre = partes[1];
						nombre = nombre.toLowerCase();
						nombre = nombre.replaceAll("\n", "");
						for(int i = 0; i < pisos.size(); i++) {
							if(nombre.contentEquals(pisos.get(i).getNombre().toLowerCase())) {
								new VentanaInformacion(pisos.get(i), u1, hostname, port);
								 dispose();
							}
						}
						
					}
				} catch (BadLocationException e1) {
					
				}
       	    }
			}
			});
        
        
        labelFotoUsuario.addMouseListener (new MouseAdapter() {
        	 @Override
        	    public void mousePressed(MouseEvent e) {
        		 dispose();
        		 // aqui deberia redirigir a la ventana con el perfil
        	      
        	    }
		});
        botonVolverAtras.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		dispose();
        		new Cliente(hostname, port);
        	}
        });
        btnBuscar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String cuidad = textBuscarPiso.getText();
        		ArrayList<Piso> pisosBuscados = new ArrayList<Piso>();
        		for(int i = 0 ; i < pisos.size(); i++) {
        			if(pisos.get(i).getLocalizacion().equals(cuidad)) {
        				pisosBuscados.add(new Piso(pisos.get(i)));
        			}
        		}
        		dispose();
        		new VentanaListaPisos(pisos, pisosBuscados, hostname, port, u1);
        		
        	}
        });
        botonInicio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
                new VentanaListaPisos(pisos, pisos, hostname, port, u1);
                dispose();
			}
		});
        botonComparar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaComparar(pisos.get(0), pisos.get(1), hostname, port, u1);
				dispose();
				
			}
		});
        
        setResizable(false);
        setVisible(true);
        
    }
  
}