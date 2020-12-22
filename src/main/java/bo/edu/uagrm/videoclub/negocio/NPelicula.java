package bo.edu.uagrm.videoclub.negocio;

import bo.edu.uagrm.videoclub.datos.DPelicula;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author CACERES PINTO DILIO GUIDO
 */
public class NPelicula {

    private DPelicula dato;

    public NPelicula() {
        dato = new DPelicula();
    }

    public void insertar(String nombre, String sinopsis, String anio, String duracion, BigDecimal precio, int idGenero) {
        dato.setNombre(nombre);
        dato.setSinopsis(sinopsis);
        dato.setAnio(anio);
        dato.setDuracion(duracion);
        dato.setPrecio(precio);
        dato.getGenero().setIdGenero(idGenero);
        dato.insertar();
    }

    public void modificar(int idPelicula, String nombre, String sinopsis, String anio, String duracion, BigDecimal precio, int idGenero) {
        dato.setIdPelicula(idPelicula);
        dato.setNombre(nombre);
        dato.setSinopsis(sinopsis);
        dato.setAnio(anio);
        dato.setDuracion(duracion);
        dato.setPrecio(precio);
        dato.getGenero().setIdGenero(idGenero);
        dato.modificar();
    }

    public void eliminar(int idPelicula) {
        dato.setIdPelicula(idPelicula);
        dato.eliminar();
    }

    public Map<String, Object> obtener(int id) {
        dato.setIdPelicula(id);
        dato.obtener();
        if (dato.getIdPelicula() == 0) {
            return null;
        }
        Map<String, Object> ele = new HashMap<>();
        ele.put("idPelicula", dato.getIdPelicula());
        ele.put("nombre", dato.getNombre());
        ele.put("sinopsis", dato.getSinopsis());
        ele.put("anio", dato.getAnio());
        ele.put("duracion", dato.getDuracion());
        ele.put("precio", dato.getPrecio());
        Map<String, Object> genero = new HashMap<>();
        genero.put("idGenero", dato.getGenero().getIdGenero());
        ele.put("genero", genero);
        return ele;
    }

    public List<Map<String, Object>> listar() {
        List<Map<String, Object>> lista = new ArrayList<>();
        for (DPelicula index : dato.listar()) {
            Map<String, Object> ele = new HashMap<>();
            ele.put("idPelicula", index.getIdPelicula());
            ele.put("nombre", index.getNombre());
            ele.put("sinopsis", index.getSinopsis());
            ele.put("anio", index.getAnio());
            ele.put("duracion", index.getDuracion());
            ele.put("precio", index.getPrecio());
            Map<String, Object> genero = new HashMap<>();
            genero.put("idGenero", index.getGenero().getIdGenero());
            ele.put("genero", genero);
            lista.add(ele);
        }
        return lista;
    }
}
