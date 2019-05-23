<%-- 
    Document   : listar_calendario
    Created on : 12/05/2019, 16:48:05
    Author     : Victor_Aguiar
--%>

<%@page import="model.CalendarioObrigatorio"%>
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


<%
    int diasiniciomesvida = (Integer) request.getAttribute("diasdevidanoiniciomes");

    int totaldediasdomes = (Integer) request.getAttribute("totaldiasmes");
    int totalformulado = totaldediasdomes + 1;
    
    System.out.println("TOTAL: "+totalformulado);
    
    ArrayList<CalendarioObrigatorio> exibirVacinasCardeneta = (ArrayList<CalendarioObrigatorio>) request.getAttribute("listavacinasusuario");
%>
<table>
    <thead>
        <tr>
            <td class="semana dia letra center-align">Domingo</td>
            <td class="semana dia letra center-align">Segunda</td>
            <td class="semana dia letra center-align">Terça</td>
            <td class="semana dia letra center-align">Quarta</td>
            <td class="semana dia letra center-align">Quinta</td>
            <td class="semana dia letra center-align">Sexta</td>
            <td class="semana dia letra center-align">Sabado</td>
        </tr>
    </thead>

    <tbody>
        <% int quebralinha = 0;%>

        <% for (int cont = 1; cont < totalformulado;) {  int et = 0; %>

        <% if(quebralinha == 7){ %> <TR>  <% }quebralinha = quebralinha+1; %>
           
        <% for (CalendarioObrigatorio a : exibirVacinasCardeneta) { %>
        
            <% if (a.getIntervalov().getDias() == diasiniciomesvida) { %>
                
            <td class="dia"><a href="#" title="<%=a.getIntervalov().getDose() + "" + a.getVacina().getTipo() %>"><i class="material-icons">colorize</i><%=a.getVacina().getNome() %></a></td>
                 
              <% et = 1; }
              
            } %>        
            
             <% if (et != 1){ %> 
             
                <td class="dia"><%=cont %></td>   
                
             <% et = 0; } %>
             
       <% if(quebralinha == 7){ %>
       
        <TR>
            
       <% quebralinha=0;} 
cont = cont + 1;
diasiniciomesvida = diasiniciomesvida+1; } %>

</tbody>
</table>