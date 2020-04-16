package es.deusto.spq.server;
import es.deusto.spq.server.PisoDAO;

public class DAOFactory{

    private static DAOFactory instance = new DAOFactory();
    private DAOFactory(){

    }
    public static DAOFactory getInstance(){
        return instance;
    }
// Revisar este metodo crear instancia
    public PisoDAO createPisoDAO(){

        return new PisoDAO();

    }
}