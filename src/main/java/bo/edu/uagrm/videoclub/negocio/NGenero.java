package bo.edu.uagrm.videoclub.negocio;

import bo.edu.uagrm.videoclub.datos.DGenero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author CACERES PINTO DILIO GUIDO
 */
public class NGenero {

    private DGenero dato;

    public NGenero() {
        dato = new DGenero();
    }

    public void insertar(String nombre, String descripcion) {        
        dato.setNombre(nombre);
        dato.setDescripcion(descripcion);
        dato.insertar();
    }    

    public void modificar(int idGenero, String nombre, String descripcion) {
        dato.setIdGenero(idGenero);
        dato.setNombre(nombre);
        dato.setDescripcion(descripcion);
        dato.modificar();
    }

    public void eliminar(int idGenero) {        
        dato.setIdGenero(idGenero);
        dato.eliminar();
    }

    public Map<String, Object> obtener(int id) {        
        dato.setIdGenero(id);
        dato.obtener();        
        Map<String, Object> ele = new HashMap<>();
        ele.put("idGenero", dato.getIdGenero());
        ele.put("nombre", dato.getNombre());
        ele.put("descripcion", dato.getDescripcion());
        return ele;
    }

    public List<Map<String, Object>> listar() {
        List<Map<String, Object>> lista = new ArrayList<>();
        for (DGenero index : dato.listar()) {
            Map<String, Object> ele = new HashMap<>();
            ele.put("idGenero", index.getIdGenero());
            ele.put("nombre", index.getNombre());
            ele.put("descripcion", index.getDescripcion());
            lista.add(ele);
        }
        return lista;
    }

}
