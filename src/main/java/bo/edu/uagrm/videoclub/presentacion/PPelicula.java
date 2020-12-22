package bo.edu.uagrm.videoclub.presentacion;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CACERES PINTO DILIO GUIDO
 */
@WebServlet(name = "PPelicula", urlPatterns = {"/peliculas"})
public class PPelicula extends HttpServlet {
    private int idPelicula;
    private String nombre;
    private String sinopsis;
    private String anio;
    private String duracion;
    private BigDecimal precio;
    private int idGenero;
    private NPelicula negocio;
    private NGenero negocioGenero;
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PPelicula</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PPelicula at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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

    

}
