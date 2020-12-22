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

    public void insertar(Map<String, Object> param) {
        dato.setCodigo((String) param.get("codigo"));
        dato.setNombre((String) param.get("nombre"));
        dato.setDescripcion((String) param.get("descripcion"));
        dato.insertar();
    }

    public void modificar(Map<String, Object> param) {
        dato.setIdGenero((Integer) param.get("idGenero"));
        dato.setCodigo((String) param.get("codigo"));
        dato.setNombre((String) param.get("nombre"));
        dato.setDescripcion((String) param.get("descripcion"));
        dato.modificar();
    }

    public void eliminar(Map<String, Object> param) {
        System.out.println(param);
        dato.setIdGenero((Integer) param.get("idGenero"));
        dato.eliminar();
    }

    public Map<String, Object> obtener(int id) {
        dato.setIdGenero(id);
        dato.obtener();
        if (dato.getIdGenero() == 0) {
            return null;
        }
        Map<String, Object> ele = new HashMap<>();
        ele.put("idGenero", dato.getIdGenero());
        ele.put("codigo", dato.getCodigo());
        ele.put("nombre", dato.getNombre());
        ele.put("descripcion", dato.getDescripcion());
        return ele;
    }

    public List<Map<String, Object>> listar() {
        List<Map<String, Object>> lista = new ArrayList<>();
        for (DGenero index : dato.listar()) {
            Map<String, Object> ele = new HashMap<>();
            ele.put("idGenero", index.getIdGenero());
            ele.put("codigo", index.getCodigo());
            ele.put("nombre", index.getNombre());
            ele.put("descripcion", index.getDescripcion());
            lista.add(ele);
        }
        return lista;
    }
}
