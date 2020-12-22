package bo.edu.uagrm.videoclub.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guido
 */
public class Conexion {

    private final String URL_DATABASE = "jdbc:postgresql://172.28.3.61/videoclub";
    private final String USER = "gel";
    private final String PASS = "gel*123";
    private Connection conn;

    public Conexion() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection abrir() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(URL_DATABASE, USER, PASS);
        }
        return conn;
    }

    public void cerrar() throws SQLException {
        if (conn != null) {
            conn.close();
        }
        conn = null;
    }
}
