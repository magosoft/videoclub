<%-- 
    Document   : index
    Created on : Dec 19, 2020, 5:13:37 PM
    Author     : CACERES PINTO DILIO GUIDO
--%>
<%@page import="java.math.BigDecimal"%>
<%@page import="bo.edu.uagrm.videoclub.negocio.NPelicula"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.io.UnsupportedEncodingException"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.net.URL"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="bo.edu.uagrm.videoclub.negocio.NGenero"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    NPelicula negocio = new NPelicula();
    NGenero negocioGenero = new NGenero();
    Map<String, String> values = splitQuery(request.getQueryString());
    String id = "0";
    if (values.containsKey("id")) {
        id = (String) values.get("id");
        Object item = negocio.obtener(Integer.parseInt(id));
        if (item == null) {
            response.sendRedirect(config.getServletContext().getContextPath() + "/peliculas/");
        }
        request.setAttribute("item", item);
    }
    String action = request.getParameter("action");
    if ("insertar".equals(action)) {
        insertar(request, negocio);
        response.sendRedirect(config.getServletContext().getContextPath() + "/peliculas/");
    } else if ("modificar".equals(action)) {
        id = modificar(request, negocio);
        response.sendRedirect(config.getServletContext().getContextPath() + "/peliculas/?id=" + id);
    } else if ("eliminar".equals(action)) {
        eliminar(request, negocio);
        response.sendRedirect(config.getServletContext().getContextPath() + "/peliculas/");
    }
    listarGeneros(request, negocioGenero);
    listar(request, negocio);
%>  
<%!
    public void insertar(HttpServletRequest request, NPelicula negocio) {
        Map<String, Object> param = new HashMap<>();
        param.put("nombre", request.getParameter("nombre"));
        param.put("sinopsis", request.getParameter("sinopsis"));
        param.put("anio", request.getParameter("anio"));
        param.put("duracion", request.getParameter("duracion"));
        param.put("precio", new BigDecimal(request.getParameter("precio")));

        Map<String, Object> genero = new HashMap<>();
        genero.put("idGenero", Integer.parseInt(request.getParameter("idGenero")));
        param.put("genero", genero);
        negocio.insertar(param);
    }

    public String modificar(HttpServletRequest request, NPelicula negocio) {
        Map<String, Object> param = new HashMap<>();
        String idPelicula = request.getParameter("idPelicula");
        param.put("idPelicula", Integer.parseInt(idPelicula));
        param.put("nombre", request.getParameter("nombre"));
        param.put("sinopsis", request.getParameter("sinopsis"));
        param.put("anio", request.getParameter("anio"));
        param.put("duracion", request.getParameter("duracion"));
        param.put("precio", new BigDecimal(request.getParameter("precio")));

        Map<String, Object> genero = new HashMap<>();
        genero.put("idGenero", Integer.parseInt(request.getParameter("idGenero")));
        param.put("genero", genero);
        negocio.modificar(param);
        return idPelicula;
    }

    public void listar(HttpServletRequest request, NPelicula negocio) {
        request.setAttribute("lista", negocio.listar());
    }

    public void listarGeneros(HttpServletRequest request, NGenero negocio) {
        request.setAttribute("listaGeneros", negocio.listar());
    }

    public void eliminar(HttpServletRequest request, NPelicula negocio) {
        Map<String, Object> param = new HashMap<>();
        String idPelicula = request.getParameter("idPelicula");
        param.put("idPelicula", Integer.parseInt(idPelicula));
        param.put("nombre", request.getParameter("nombre"));
        param.put("sinopsis", request.getParameter("sinopsis"));
        param.put("anio", request.getParameter("anio"));
        param.put("duracion", request.getParameter("duracion"));
        param.put("precio", new BigDecimal(request.getParameter("precio")));

        Map<String, Object> genero = new HashMap<>();
        genero.put("idGenero", Integer.parseInt(request.getParameter("idGenero")));
        param.put("genero", genero);
        negocio.eliminar(param);
    }

    public Map<String, String> splitQuery(String query) throws UnsupportedEncodingException {
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
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Gestionar Peliculas</title>
        <!-- Google Font: Source Sans Pro -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="../plugins/fontawesome-free/css/all.min.css">
        <!-- icheck bootstrap -->
        <link rel="stylesheet" href="../plugins/icheck-bootstrap/icheck-bootstrap.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="../plugins/admin-lte/css/adminlte.min.css">
    </head>
    <body class="hold-transition">
        <div class="wrapper">
            <div class="content-wrapper">
                <section class="content">
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4>PELICULAS</h4>
                                </div>
                                <form name="form" action="<%= config.getServletContext().getContextPath()%>/peliculas/" method="POST" role="form">
                                    <div class="card-body">
                                        <input type="hidden" name="idPelicula" value="${item.idPelicula}">
                                        <div class="row">
                                            <div class="col-sm-4">
                                                <div class="form-group">
                                                    <label>Nombre</label><input name="nombre" type="text" class="form-control" value="${item.nombre}">
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div class="form-group">
                                                    <label>Sinopsis</label><input name="sinopsis" type="text" class="form-control" value="${item.sinopsis}">
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div class="form-group">
                                                    <label>Año</label><input name="anio" type="text" class="form-control" value="${item.anio}">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-4">
                                                <div class="form-group">
                                                    <label>Duración</label><input name="duracion" type="text" class="form-control" value="${item.duracion}">
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div class="form-group">
                                                    <label>Precio</label><input name="precio" type="text" class="form-control" value="${item.precio}">
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div class="form-group">
                                                    <label>Género</label>
                                                    <select name="idGenero" class="form-control select2" style="width: 100%;">
                                                        <c:forEach var="itemGenero" items="${listaGeneros}">
                                                            <option value="${itemGenero.idGenero}" <c:if test="${itemGenero.idGenero == item.genero.idGenero}">selected</c:if> ><c:out value="${itemGenero.nombre}"/></option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>   
                                    </div>
                                    <!-- ./card-body --> 
                                    <div class="card-footer">
                                        <div class="form-group">
                                            <div class="controls">
                                                <a style='<%="0".equals(id) ? "display:none" : ""%>' href="../peliculas/" class="btn btn-primary">Nuevo</a>
                                                <button name="action" value="insertar" type="submit" class="btn btn-primary" style='<%="0".equals(id) ? "" : "display:none"%>'>Insertar</button>
                                                <button name="action" value="modificar" type="submit" class="btn btn-primary" style='<%="0".equals(id) ? "display:none" : ""%>'>Modificar</button>
                                                <button name="action" value="eliminar" type="submit" class="btn btn-danger" style='<%="0".equals(id) ? "display:none" : ""%>'>Eliminar</button>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- ./card-footer --> 
                                </form>
                            </div>
                            <!-- ./card -->               
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">
                                    <!--<h3 class="card-title">Expandable Table</h3>-->
                                </div>
                                <!-- ./card-header -->
                                <div class="card-body">
                                    <table class="table table-bordered table-hover">
                                        <thead>
                                            <tr>                
                                                <th>NOMBRE</th>
                                                <th>AÑO</th>
                                                <th>DURACION</th>   
                                                <th>PRECIO</th> 
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="pelicula" items="${lista}">
                                                <tr>
                                                    <td><a href="../peliculas?id=${pelicula.idPelicula}"><c:out value="${pelicula.nombre}"/></a></td>
                                                    <td><c:out value="${pelicula.anio}"/></td>
                                                    <td><c:out value="${pelicula.duracion}"/></td> 
                                                    <td><c:out value="${pelicula.precio}"/></td>  
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>  
                                </div>                  
                            </div>                
                        </div>              
                    </div>
                </section>
            </div>
        </div>
    </body>
</html>
