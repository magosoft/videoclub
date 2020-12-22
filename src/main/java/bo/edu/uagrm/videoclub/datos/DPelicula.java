package bo.edu.uagrm.videoclub.datos;

import java.math.BigDecimal;
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
public class DPelicula {

    private Conexion cnx;
    private int idPelicula;
    private String nombre;
    private String sinopsis;
    private String anio;
    private String duracion;
    private BigDecimal precio;
    private DGenero genero;

    public DPelicula() {
        cnx = new Conexion();
        idPelicula = 0;
        genero = new DGenero();
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public DGenero getGenero() {
        return genero;
    }

    public void setGenero(DGenero genero) {
        this.genero = genero;
    }

    public void insertar() {
        String sql = "INSERT INTO datos.pelicula(nombre, sinopsis, anio, duracion, precio, id_genero) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn;
        try {
            conn = cnx.abrir();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, sinopsis);
            stmt.setString(3, anio);
            stmt.setString(4, duracion);
            stmt.setBigDecimal(5, precio);
            stmt.setInt(6, genero.getIdGenero());
            stmt.executeUpdate();
        } catch (SQLException ex) {

        } finally {
            try {
                cnx.cerrar();
            } catch (SQLException ex) {

            }
        }
    }

    public void modificar() {
        String sql = "UPDATE datos.pelicula SET nombre = ?, sinopsis = ?, anio = ?, duracion = ?, precio = ?, id_genero = ? WHERE id_pelicula = " + idPelicula;
        Connection conn;
        try {
            conn = cnx.abrir();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, sinopsis);
            stmt.setString(3, anio);
            stmt.setString(4, duracion);
            stmt.setBigDecimal(5, precio);
            stmt.setInt(6, genero.getIdGenero());
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
        String sql = "DELETE FROM datos.pelicula WHERE id_pelicula = " + idPelicula;
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
        String sql = "SELECT * FROM datos.pelicula WHERE id_pelicula = " + idPelicula;
        Connection conn;
        try {
            conn = cnx.abrir();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                idPelicula = rs.getInt("id_pelicula");
                nombre = rs.getString("nombre");
                sinopsis = rs.getString("sinopsis");
                anio = rs.getString("anio");
                duracion = rs.getString("duracion");
                precio = rs.getBigDecimal("precio");
                genero.setIdGenero(rs.getInt("id_genero"));
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

    public List<DPelicula> listar() {
        String sql = "SELECT * FROM datos.pelicula ORDER BY id_pelicula";
        List<DPelicula> lista = new ArrayList<>();
        Connection conn;
        try {
            conn = cnx.abrir();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                idPelicula = rs.getInt("id_pelicula");
                nombre = rs.getString("nombre");
                sinopsis = rs.getString("sinopsis");
                anio = rs.getString("anio");
                duracion = rs.getString("duracion");
                precio = rs.getBigDecimal("precio");
                genero.setIdGenero(rs.getInt("id_genero"));
                lista.add(this);
            }
            while (rs.next()) {
                DPelicula item = new DPelicula();
                item.setIdPelicula(rs.getInt("id_pelicula"));
                item.setNombre(rs.getString("nombre"));
                item.setSinopsis(rs.getString("sinopsis"));
                item.setAnio(rs.getString("anio"));
                item.setDuracion(rs.getString("duracion"));
                item.setPrecio(rs.getBigDecimal("precio"));
                item.getGenero().setIdGenero(rs.getInt("id_genero"));
                lista.add(item);
            }
        } catch (SQLException ex) {
           
        } finally {
            try {
                cnx.cerrar();
            } catch (SQLException ex) {
               
            }
        }
        return lista;
    }
}
