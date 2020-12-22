<%-- 
    Document   : index
    Created on : Dec 19, 2020, 5:13:37 PM
    Author     : CACERES PINTO DILIO GUIDO
--%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
        <!-- icheck bootstrap -->
        <link rel="stylesheet" href="plugins/icheck-bootstrap/icheck-bootstrap.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="plugins/admin-lte/css/adminlte.min.css">
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
                                <form name="form" action="<%= config.getServletContext().getContextPath()%>/generos" method="POST" role="form">
                                    <div class="card-body">
                                        <input type="hidden" name="idGenero" value="${item.idGenero}">
                                        <div class="row">                                            
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
                                                <a <c:if test="${item.idGenero == 0}">style='display: none'</c:if> href="generos" class="btn btn-primary">Nuevo</a>
                                                <button name="action" value="insertar" <c:if test="${item.idGenero > 0}">style='display: none'</c:if> type="submit" class="btn btn-primary">Insertar</button>
                                                <button name="action" value="modificar" <c:if test="${item.idGenero == 0}">style='display: none'</c:if> type="submit" class="btn btn-primary">Modificar</button>
                                                <button name="action" value="eliminar" <c:if test="${item.idGenero == 0}">style='display: none'</c:if> type="submit" class="btn btn-danger">Eliminar</button>
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
                                                <th>NOMBRE</th>
                                                <th>DESCRPICION</th>                
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="genero" items="${lista}">
                                                <tr>
                                                    <td><a href="generos?id=${genero.idGenero}"><c:out value="${genero.nombre}"/></a></td>
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
