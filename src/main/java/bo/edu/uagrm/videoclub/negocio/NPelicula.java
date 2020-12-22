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

    public void insertar(Map<String, Object> param) {
        dato.setNombre((String) param.get("nombre"));
        dato.setSinopsis((String) param.get("sinopsis"));
        dato.setAnio((String) param.get("anio"));
        dato.setDuracion((String) param.get("duracion"));
        dato.setPrecio((BigDecimal) param.get("precio"));       
        Map<String, Object> genero = (Map<String, Object>) param.get("genero");
        dato.getGenero().setIdGenero((Integer) genero.get("idGenero"));
        dato.insertar();
    }

    public void modificar(Map<String, Object> param) {
        dato.setIdPelicula((Integer) param.get("idPelicula"));
        dato.setNombre((String) param.get("nombre"));
        dato.setSinopsis((String) param.get("sinopsis"));
        dato.setAnio((String) param.get("anio"));
        dato.setDuracion((String) param.get("duracion"));
        dato.setPrecio((BigDecimal) param.get("precio"));        
        Map<String, Object> genero = (Map<String, Object>) param.get("genero");
        dato.getGenero().setIdGenero((Integer) genero.get("idGenero"));
        dato.modificar();
    }

    public void eliminar(Map<String, Object> param) {
        dato.setIdPelicula((Integer) param.get("idPelicula"));
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
