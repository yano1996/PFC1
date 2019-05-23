<%-- 
    Document   : exibir_usuario
    Created on : 25/11/2018, 05:52:21
    Author     : nelson_amaral
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- IMPORTANDO CABECALHO DA PAGINA -->
<jsp:include page="../paginas_utilitarias/cabecalho.jsp" />

<!DOCTYPE html>
<main>
    <!-- -->
    <script>
        function VariavelAUXnomeID(id) {

            var id = id;



            window.open('ControleVacina_has_restricao?acao=Listar&id=' + id, 'popup', 'width=700,height=500,scrolling=auto,top=0,left=0');
        }
    </script>
    <div class="container">
        <div class="row">            
            <div class="card z-depth-2">
                <div class="card-content">
                    <span class="card-title titulo-tabela letra center-align">Consultar Vacinas</span>

                    <center><div style="overflow: scroll; width: auto; height: 350px; border:solid 1px">
                            <table class="striped">
                                <TR>
                                    <td class="center-align letra">Vacina</td>
                                    <td class="center-align letra">Tipo</td> 
                                    <td class="center-align letra">Opção</td>   
                                </TR>
                                <c:forEach var="vacinas" items="${listavacina}">

                                    <TR>
                                        <td class="center-align"><c:out value="${vacinas.nome}"/></td>
                                        <td class="center-align"><c:out value="${vacinas.tipo}"/></td>

                                        <td class="center-align">
                                            <form action="ControleVacina" method="GET">
                                                <button class="btn waves-effect waves-teal red" type="submit" name="acao" value="Deletar"><i class="material-icons">delete</i></button>
                                                <button class="btn waves-effect waves-teal blue" type="submit" name="acao" value="Editar Vacina"><i class="material-icons">edit</i></button>
                                                <button class="btn waves-effect waves-teal yellow" type="button" name="acao" onclick="VariavelAUXnomeID(${vacinas.id_vacina})" value="Atualiza_restricao">Atualizar as Restrições</button>
                                                <input type="text" name="id_vacina" value="${vacinas.id_vacina}" hidden/>
                                            </form>
                                        </td>
                                    </TR>
                                </c:forEach>
                            </table>
                        </div></center>
                    <div class="col s12 row">
                        <form action="ControleRestricoes" method="POST">
                            <br>
                            <input type="submit" class="btn" name="acao" value="Cadastrar Vacinas"> 
                        </form>
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
