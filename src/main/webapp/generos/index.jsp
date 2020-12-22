<%-- 
    Document   : index
    Created on : Dec 19, 2020, 5:13:37 PM
    Author     : CACERES PINTO DILIO GUIDO
--%>
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
    NGenero negocio = new NGenero();
    Map<String, String> values = splitQuery(request.getQueryString());
    String id = "0";
    if (values.containsKey("id")) {
        id = (String) values.get("id");
        Object item = negocio.obtener(Integer.parseInt(id));
        if (item == null) {
            response.sendRedirect(config.getServletContext().getContextPath() + "/generos/");
        }
        request.setAttribute("item", item);
    }
    String action = request.getParameter("action");
    if ("insertar".equals(action)) {
        insertar(request, negocio);
        response.sendRedirect(config.getServletContext().getContextPath() + "/generos/");
    } else if ("modificar".equals(action)) {
        id = modificar(request, negocio);
        response.sendRedirect(config.getServletContext().getContextPath() + "/generos/?id=" + id);
    } else if ("eliminar".equals(action)) {
        eliminar(request, negocio);
        response.sendRedirect(config.getServletContext().getContextPath() + "/generos/");
    }
    listar(request, negocio);
%>  
<%!
    public void insertar(HttpServletRequest request, NGenero negocio) {
        Map<String, Object> param = new HashMap<>();
        param.put("codigo", request.getParameter("codigo"));
        param.put("nombre", request.getParameter("nombre"));
        param.put("descripcion", request.getParameter("descripcion"));
        negocio.insertar(param);
    }

    public String modificar(HttpServletRequest request, NGenero negocio) {
        Map<String, Object> param = new HashMap<>();
        String idGenero = request.getParameter("idGenero");
        param.put("idGenero", Integer.parseInt(idGenero));
        param.put("codigo", request.getParameter("codigo"));
        param.put("nombre", request.getParameter("nombre"));
        param.put("descripcion", request.getParameter("descripcion"));
        negocio.modificar(param);
        return idGenero;
    }

    public void listar(HttpServletRequest request, NGenero negocio) {
        request.setAttribute("lista", negocio.listar());
    }

    public void eliminar(HttpServletRequest request, NGenero negocio) {
        Map<String, Object> param = new HashMap<>();
        String idGenero = request.getParameter("idGenero");
        param.put("idGenero", Integer.parseInt(idGenero));
        param.put("codigo", request.getParameter("codigo"));
        param.put("nombre", request.getParameter("nombre"));
        param.put("descripcion", request.getParameter("descripcion"));
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
        <title>Gestionar Género</title>
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
                                    <h4>GENEROS</h4>
                                </div>
                                <form name="form" action="<%= config.getServletContext().getContextPath()%>/generos/" method="POST" role="form">
                                    <div class="card-body">
                                        <input type="hidden" name="idGenero" value="${item.idGenero}">
                                        <div class="row">
                                            <div class="col-sm-4">
                                                <div class="form-group">
                                                    <label>Código</label><input name="codigo" type="text" class="form-control" value="${item.codigo}">
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div class="form-group">
                                                    <label>Nombre</label><input name="nombre" type="text" class="form-control" value="${item.nombre}">
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div class="form-group">
                                                    <label>Descripción</label><input name="descripcion" type="text" class="form-control" value="${item.descripcion}">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <div class="form-group">
                                            <div class="controls">
                                                <a style='<%="0".equals(id) ? "display:none" : ""%>' href="../generos/" class="btn btn-primary">Nuevo</a>
                                                <button name="action" value="insertar" type="submit" class="btn btn-primary" style='<%="0".equals(id) ? "" : "display:none"%>'>Insertar</button>
                                                <button name="action" value="modificar" type="submit" class="btn btn-primary" style='<%="0".equals(id) ? "display:none" : ""%>'>Modificar</button>
                                                <button name="action" value="eliminar" type="submit" class="btn btn-danger" style='<%="0".equals(id) ? "display:none" : ""%>'>Eliminar</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
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
                                                <th>CODIGO</th>
                                                <th>NOMBRE</th>
                                                <th>DESCRPICION</th>                
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="genero" items="${lista}">
                                                <tr>
                                                    <td><a href="../generos?id=${genero.idGenero}"><c:out value="${genero.codigo}"/></a></td>
                                                    <td><c:out value="${genero.nombre}"/></td>
                                                    <td><c:out value="${genero.descripcion}"/></td>                   				
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
