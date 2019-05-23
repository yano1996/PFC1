<%-- 
    Document   : listar_calendario
    Created on : 12/05/2019, 16:48:05
    Author     : Victor_Aguiar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%-- 
    Document   : exibir_usuario
    Created on : 25/11/2018, 05:52:21
    Author     : nelson_amaral
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page import="java.util.ArrayList"%>


<center><div style="overflow: scroll; width: auto; height: 240px; border:solid 1px">
        <table>
            <thead>
                <tr>
                    <td><div class="center-align letra">Checkbox</div></td>
                    <td><div class="center-align letra">Nome</div></td>
                    <td><div class="center-align letra">Cpf</div></td>
                    <td><div class="center-align letra">Rg</div></td>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="usuario" items="${listaUsuarioEspecifico}">
                    <tr>
                        <td><div class="center-align blue-text letra"><input type="checkbox" value="${usuario.usuario_id}" name="txtUsuarioFK"></div></td>
                        <td><div class="center-align blue-text letra">${usuario.nome}</div></td>
                        <td><div class="center-align blue-text letra">${usuario.cpf}</div></td>
                        <td><div class="center-align blue-text letra">${usuario.rg}</div></td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div></center>

