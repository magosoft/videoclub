package bo.edu.uagrm.videoclub.presentacion;

import bo.edu.uagrm.videoclub.negocio.NGenero;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author CACERES PINTO DILIO GUIDO
 */
@WebServlet(name = "PGenero", urlPatterns = {"/generos"})
public class PGenero extends HttpServlet {

    private int idGenero;
    private String nombre;
    private String descripcion;
    private final NGenero negocio;

    public PGenero() {
        negocio = new NGenero();
    }

    public void nuevo() {
        request.setAttribute("lista", negocio.listar());
    }

    public void insertar() {
        negocio.insertar(nombre, descripcion);
        request.setAttribute("lista", negocio.listar());
    }

    public void modificar() {
        negocio.modificar(idGenero, nombre, descripcion);
        request.setAttribute("lista", negocio.listar());
    }

    public void eliminar() {
        negocio.eliminar(idGenero);
        request.setAttribute("lista", negocio.listar());
    }
    /*
    PROCESA LAS ACCIONES
    */
    HttpServletRequest request;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.request = request;
        Map<String, Object> item = obtenerPorId(request);
        request.setAttribute("item", item);
        String action = request.getParameter("action");
        if ("insertar".equals(action)) {
            cargarValores(request);
            insertar();
            response.sendRedirect(getServletContext().getContextPath() + "/generos");
        } else if ("modificar".equals(action)) {
            cargarValores(request);
            modificar();
            response.sendRedirect(getServletContext().getContextPath() + "/generos?id=" + idGenero);
        } else if ("eliminar".equals(action)) {
            cargarValores(request);
            eliminar();
            response.sendRedirect(getServletContext().getContextPath() + "/generos");
        } else {
            nuevo();
        }
        getServletContext().getRequestDispatcher("/form-genero.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private Map<String, Object> obtenerPorId(HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String, String> values = splitQuery(request.getQueryString());
        if (values.containsKey("id")) {
            return negocio.obtener(Integer.parseInt((String) values.get("id")));
        }
        Map<String, Object> item = new HashMap<>();
        item.put("idGenero", 0);
        return item;
    }

    private Map<String, String> splitQuery(String query) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<>();
        if (!StringUtils.isEmpty(query)) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
            }
        }
        return query_pairs;
    }

    private void cargarValores(HttpServletRequest request) {
        String id = request.getParameter("idGenero");
        idGenero = id == null ? 0 : Integer.parseInt(id);
        nombre = request.getParameter("nombre");
        descripcion = request.getParameter("descripcion");
    }
}
