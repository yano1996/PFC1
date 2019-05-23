<%-- 
    Document   : cadastar_vacina
    Created on : 25/11/2018, 11:21:48
    Author     : nelson_amaral
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- IMPORTANDO CABECALHO DA PAGINA -->
<jsp:include page="../paginas_utilitarias/cabecalho.jsp" />

<!DOCTYPE html>
<html>
    <body>
        <div class="container">
            <div class="row">
                <div class="card z-depth-2">
                    <div class="card-content">
                        <span class="card-title titulo-tabela center-align">Cadastrar Vacina</span>
                        <form action="ControleVacina" method="POST" ><br/>
                            <div class="row">
                                <div class="col s6 input-field hoverable">
                                    <input type="text" name="txtNome" id="txtNome" class="hoverable validate" placeholder="Maximo 30 caracteres" size="30" maxlength=30 required>
                                    <label class="black-text" for="txtNome">Vacina<i class="material-icons left">create</i></label>                                   
                                </div>

                                <div class='col s6'>
                                    <label class="black-text left-align" for="txtTipo">Tipo de Vacinas<i class="material-icons left">dehaze</i></label>
                                    <select class="browser-default" name="txtTipo" required>
                                        <option value="" disabled selected>Selecione...</option>
                                        <option value="Vacinas_vivas_atenuadas">Vacinas vivas atenuadas</option>
                                        <option value="Vacinas_mortas_inactivadas">Vacinas mortas inactivadas</option>
                                        <option value="Vacinas_sub_unitarias">Vacinas sub unitárias</option>
                                    </select>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col s2"></div>
                                <div class="col s8 center-align">
                                    <h4 class="center-align">Restrições</h4>

                                    <div class="row">
                                        <div class="col s6">
                                            <label class="black-text letra123">Pesquisa</label>
                                            <nav>
                                                <div class="green-text nav-wrapper">

                                                    <div class="input-field">
                                                        <input class="filtrar-restricao" id="filtrar-restricao" type="search" >
                                                        <label class="label-icon" for="search"><i class="material-icons">search</i></label>
                                                        <i class="material-icons">close</i>
                                                    </div>

                                                </div>
                                            </nav>
                                        </div>
                                    </div>


                                    <center><div style="overflow: scroll; width: auto; height: 350px; border:solid 1px">
                                            <table class="striped">
                                                <TR>
                                                    <td class="center-align">Checkbox</td>
                                                    <td class="center-align">Restricão</td>
                                                    <td class="center-align">Tipo</td>
                                                </TR>

                                                <!-- Listando Restricoes para cadastrar na vacina -->
                                                <c:forEach var="listarestricaoz" items="${listarestricao}">
                                                    <TR class="restricao_nome">
                                                        <td class="center-align"><input type='checkbox' name="txtRestricaoFK" value="${listarestricaoz.restricao_id}"></td>
                                                        <td class="center-align info-restricao">${listarestricaoz.restricao_nome}</td>
                                                        <td class="center-align info-restricao">${listarestricaoz.restricao_tipo}</td>
                                                    </TR>
                                                </c:forEach>
                                            </table>
                                        </div></center>
                                </div>
                            </div>
                            <br>
                            <div class="row center-align">
                                <input class="btn" type="submit" value="Cadastrar Vacinas" name="acao" >
                            </div>

                        </form>

                        <form action="../ControleVacina" method="POST">
                            <input name="acao" value="Consultar Vacinas" hidden>
                            <button type="submit" class="btn blue right">Voltar</button>
                        </form>
                        <br>
                    </div>
                </div>
            </div>
        </div>
    </body>

    <!-- IMPORTANDO RODAPE DA PAGINA -->
    <jsp:include page="../paginas_utilitarias/rodape.jsp" />
    <!-- Importando arquivo de filtro para a tabela a cima -->
    <script src="framework/js/Filtro_Paginas/Filtro_cadastrarVacina/Filtro_NomeRestricao/Filtro_nomerestricao.js" type="text/javascript"></script>
</html>
