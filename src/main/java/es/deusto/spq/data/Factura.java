package es.deusto.spq.data;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.InheritanceStrategy;


/**
 * Esta clase es una clase basica de una Factura.
 * @author eneko
 */
@PersistenceCapable
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class Factura {

    @Persistent(valueStrategy=IdGeneratorStrategy.INCREMENT, primaryKey="true")
    private long id;
    private String fecha;
    private Piso piso;
    private double importe;
    private String cliente;
    private String arrendador;
    

    /**
     * Constructor basico de la clase Factura. 
     * @param fecha Fecha de la factura
     * @param piso Piso de la factura
     * @param cliente Cliente de la factura
     * @param arrendador Arrendador de la factura
     */
    public Factura(String fecha, Piso piso, String cliente, String arrendador) {
        
        this.fecha = fecha;
        this.piso = piso;
        this.cliente = cliente;
        this.arrendador = arrendador;
    }
    
    public Factura() {
    	
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public Piso getPiso() {
        return piso;
    }

    public void setPiso(Piso piso) {
        this.piso = piso;
    }

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getArrendador() {
		return arrendador;
	}

	public void setArrendador(String arrendador) {
		this.arrendador = arrendador;
	}

    @Override
    public String toString() {
        return "Factura [arrendador=" + arrendador + ", cliente=" + cliente + ", fecha=" + fecha + ", id=" + id
                + ", importe=" + importe + ", piso=" + piso + "]";
    }

    
    
    
    
}