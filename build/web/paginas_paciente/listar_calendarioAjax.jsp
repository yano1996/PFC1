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



    <table>
        <thead>
            <tr>
                <td><div class="center-align dia semana">D</div></td>
                <td><div class="center-align dia semana">S</div></td>
                <td><div class="center-align dia semana">T</div></td>
                <td><div class="center-align dia semana">Q</div></td>
                <td><div class="center-align dia semana">Q</div></td>
                <td><div class="center-align dia semana">S</div></td>
                <td><div class="center-align dia semana">S</div></td>
            </tr>
        </thead>

        <tbody>
            <% int cont=0; %>
            <c:forEach var="array" items="${listamesesarray}">
                
             <% if (cont == 7){ %>   
            <TR>
                <% }cont = cont + 1; %>
                
                <td class="center-align letra">${array}</td>
                
            <% if (cont == 7){ %>   
            </TR>
                <% cont = 0; } %>
            </c:forEach>
        </tbody>
    </table>