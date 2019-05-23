<%-- 
    Document   : Exibir_UsuarioPorRgAJAX
    Created on : 20/05/2019, 00:44:59
    Author     : Victor_Aguiar
--%>
<%@page import="model.Usuario"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%  //recupera o usuario da sessao
    Usuario usuario = (Usuario) session.getAttribute("usuarioAutenticado");

%>

<!-- IMPORTANDO CABECALHO DA PAGINA -->
<jsp:include page="../paginas_utilitarias/cabecalho_04.jsp"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col s2"></div>
                <div class="col s8">
                    <h4 class="letra center-align">Restrições</h4>
                    <form action="ControleRestricoes" method="POST">
                        <div class="card z-depth-2">
                            <div class="card-content">

                                <center><div style="overflow: scroll; width: auto; height: 350px; border:solid 1px">
                                        <table class="responsive-table">
                                            <tr>
                                                <td class="center-align letra">Adicionar</td>
                                                <td class="center-align letra">Restricão</td>  
                                                <td class="center-align letra">Tipo</td>      
                                            </tr>
                                            <c:forEach var="listrestricao" items="${listarestricaoz}">
                                                <tr>

                                                    <td class="center-align"><input type='checkbox' name="TXTrestricaoID" value="${listrestricao.restricao_id}"></td>
                                                    <td class="center-align"><c:out value="${listrestricao.restricao_nome}"/></td>
                                                    <td class="center-align"><c:out value="${listrestricao.restricao_tipo}"/></td>

                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </div></center>
                            </div>
                        </div>
                        <div class="row center-align">
                        <input name="TXTusuario_id" value="<%= usuario.getUsuario_id()%>" hidden>
                        <button type="submit" name="acao" class="btn waves-effect" value="Cadastrar Restricao">Adicionar</button>
                        </div>
                    </form> 
                </div>
            </div>
    </body>
</html>

<!-- IMPORTANDO RODAPE DA PAGINA -->
<jsp:include page="../paginas_utilitarias/rodape_03.jsp" />
