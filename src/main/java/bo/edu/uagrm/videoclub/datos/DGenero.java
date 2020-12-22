package bo.edu.uagrm.videoclub.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CACERES PINTO DILIO GUIDO
 */
public class DGenero {

    private Conexion cnx;
    private int idGenero;
    private String nombre;
    private String descripcion;

    public DGenero() {
        cnx = new Conexion();
        idGenero = 0;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void insertar() {
        String sql = "INSERT INTO datos.genero( nombre, descripcion) VALUES (?, ?)";
        Connection conn;
        try {
            conn = cnx.abrir();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, descripcion);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                cnx.cerrar();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    public void modificar() {
        String sql = "UPDATE datos.genero SET nombre = ?, descripcion = ? WHERE id_genero = " + idGenero;
        Connection conn;
        try {
            conn = cnx.abrir();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, descripcion);
            stmt.executeUpdate();
        } catch (SQLException ex) {

        } finally {
            try {
                cnx.cerrar();
            } catch (SQLException ex) {

            }
        }
    }

    public void eliminar() {
        String sql = "DELETE FROM datos.genero WHERE id_genero = " + idGenero;
        Connection conn;
        try {
            conn = cnx.abrir();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException ex) {

        } finally {
            try {
                cnx.cerrar();
            } catch (SQLException ex) {

            }
        }
    }

    public void obtener() {
        String sql = "SELECT * FROM datos.genero WHERE id_genero = " + idGenero;
        Connection conn;
        try {
            conn = cnx.abrir();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                idGenero = rs.getInt("id_genero");
                nombre = rs.getString("nombre");
                descripcion = rs.getString("descripcion");
            }
        } catch (SQLException ex) {

        } finally {
            try {
                cnx.cerrar();
            } catch (SQLException ex) {
                //Logger.getLogger(DGenero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<DGenero> listar() {
        String sql = "SELECT * FROM datos.genero ORDER BY id_genero";
        List<DGenero> lista = new ArrayList<>();
        Connection conn;
        try {
            conn = cnx.abrir();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                idGenero = rs.getInt("id_genero");
                nombre = rs.getString("nombre");
                descripcion = rs.getString("descripcion");
                lista.add(this);
            }
            while (rs.next()) {
                DGenero item = new DGenero();
                item.setIdGenero(rs.getInt("id_genero"));
                item.setNombre(rs.getString("nombre"));
                item.setDescripcion(rs.getString("descripcion"));
                lista.add(item);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                cnx.cerrar();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return lista;
    }

}
