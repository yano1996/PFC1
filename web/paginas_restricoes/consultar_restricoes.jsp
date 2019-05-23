<%-- 
    Document   : consultar_restricoes
    Created on : 28/03/2019, 18:35:20
    Author     : Victor Aguiar
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- IMPORTANDO CABECALHO DA PAGINA -->
<jsp:include page="../paginas_utilitarias/cabecalho.jsp" />

<!DOCTYPE html>
<main>
    <div class="container">
        <div class="row">            
            <div class="card z-depth-2">
                <div class="card-content">
                    <span class="card-title titulo-tabela center-align letra">Consultar Restrições</span>

                    <center><div style="overflow: scroll; width: auto; height: 350px; border:solid 1px">
                            <table class="responsive-table">
                                <TR>
                                    <td class="center-align letra">Restricão</td>
                                    <td class="center-align letra">Tipo</td>
                                    <td class="center-align letra">Operações</td> 
                                </TR>
                                <c:forEach var="restricao" items="${listarestricaoz}">

                                    <TR>
                                        <td name="nome" class="center-align"><c:out value="${restricao.restricao_nome}"/></td>
                                        <td name="tipo" class="center-align"><c:out value="${restricao.restricao_tipo}"/></td>

                                        <td class="center-align">
                                            <form action="ControleRestricoes" method="POST">
                                                <button class="btn waves-effect waves-teal red" type="submit" name="acao" value="Deletar"><i class="material-icons">delete</i></button>
                                                <button class="btn waves-effect waves-teal blue" type="submit" name="acao" value="Editar"><i class="material-icons">edit</i></button>
                                                <input type="text" name="id_restricao" value="${restricao.restricao_id}" hidden/>
                                                <input type="text" name="nome_restricao" value="${restricao.restricao_nome}" hidden/>
                                                <input type="text" name="tipo_restricao" value="${restricao.restricao_tipo}" hidden/>
                                            </form>
                                        </td>
                                    </TR>
                                </c:forEach>
                            </table>
                        </div>

                        <div class="row">
                            <br>
                            <!--<a class="btn" href="paginas_vacina/cadastrar_vacina.jsp">Cadastrar</a>-->
                        </div>
                </div>
            </div>
        </div>
    </div>
</main>
</body>

<!-- IMPORTANDO RODAPE DA PAGINA -->
<jsp:include page="../paginas_utilitarias/rodape.jsp" />

</html>

