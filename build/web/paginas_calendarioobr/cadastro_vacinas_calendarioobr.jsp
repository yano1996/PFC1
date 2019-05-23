<%-- 
  Document   : cadastro_vacinas_calendarioobr
  Created on : 20/04/2019, 13:51:34
  Author     : nelson_amaral
--%>
<%@page import="model.Funcionario"%>
<%@page import="model.Vacina"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.CalendarioObrigatorio"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- IMPORTANDO CABECALHO DA PAGINA -->
<jsp:include page="../paginas_utilitarias/cabecalho.jsp" />

<%
    ArrayList<Vacina> listaVacina = (ArrayList<Vacina>) request.getAttribute("listaVacinas");

    //recupera o usuario da sessao
    Funcionario funcionario = (Funcionario) session.getAttribute("usuarioAutenticado");
    if (funcionario != null) {
        System.out.println("id " + funcionario.getId_funcionario());


%>


<br>

<div id="modalcadastrarvacina" class="modal">
    <br>
    <center><h4 class="black-text">Cadastrar Calendario</h4></center>

    <div class="row"> 
        <div class="col s6">
            <input class="letra center-align" id="nomevacina" disabled>
        </div>
        <div class="col s6">
            <input class="letra center-align" id="tipovacina" disabled>
        </div>
    </div>

    <form action="ControlerCalendarioObr" method="Post">
        <!-- input setado id da vacina para cadastro -->
        <input id="idVacina" name="idVacina" hidden>
        <input name="idfuncionario" value="<%=funcionario.getId_funcionario()%>" hidden>
        <% }%>
        <div class="card z-depth-2">
            <div class="card-content">
                <center><h5 class="black-text">Adicione a data inicial do calendario</h5></center><br>
                <div class="row">

                    <div class="col s1"></div>
                    <div class="col s2 input-field hoverable">
                        <input title="Este campo define a idade inicial que aparecerá para o usuario" type="text" name="txtAnosVida" id="txtAnos" class="hoverable validate" placeholder="000" size="3" maxlength=3 required>
                        <label class="black-text" for="txtAnosVida">Idade<i class="material-icons left">account_box</i></label>                                   
                    </div>
                    <div class='col s4'>
                        <label class="black-text left-align" for="txtMes">Mês<i class="material-icons left">assignment</i></label>
                        <select title="Este campo define o mês inicial que aparecerá para o usuario" class="browser-default" name="txtMes">
                            <option value="" disabled selected>Selecione...</option>
                            <option value="0">0 Mês (0 dias)</option>
                            <option value="1">1 Mês (30 dias)</option>
                            <option value="2">2 Mês (60 dias)</option>
                            <option value="3">3 Mês (90 dias)</option>
                            <option value="4">4 Mês (120 dias)</option>
                            <option value="5">5 Mês (150 dias)</option>
                            <option value="6">6 Mês (180 dias)</option>
                            <option value="7">7 Mês (210 dias)</option>
                            <option value="8">8 Mês (240 dias)</option>
                            <option value="9">9 Mês (270 dias)</option>
                            <option value="10">10 Mês (300 dias)</option>
                            <option value="11">11 Mês (330 dias)</option>
                        </select>
                    </div>
                    <div class='col s4'>
                        <label class="black-text left-align" for="txtDias">Dias<i class="material-icons left">assignment</i></label>
                        <select class="browser-default" title="Este campo define os dias do mês que aparecerá para o usuario" name="txtDias">
                            <option value="" disabled selected>Selecione...</option>
                            <option value="1">1 Dia</option>
                            <option value="2">2 Dias</option>
                            <option value="3">3 Dias</option>
                            <option value="4">4 Dias</option>
                            <option value="5">5 Dias</option>
                            <option value="6">6 Dias</option>
                            <option value="7">7 Dias</option>
                            <option value="8">8 Dias</option>
                            <option value="9">9 Dias</option>
                            <option value="10">10 Dias</option>
                            <option value="11">11 Dias</option>
                            <option value="12">12 Dias</option>
                            <option value="13">13 Dias</option>
                            <option value="14">14 Dias</option>
                            <option value="15">15 Dias</option>
                            <option value="16">16 Dias</option>
                            <option value="17">17 Dias</option>
                            <option value="18">18 Dias</option>
                            <option value="19">19 Dias</option>
                            <option value="20">20 Dias</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>

        <div class="card z-depth-2">
            <div class="card-content">
                <center><h5 class="black-text">Adicionando Quant de Doses e Intervalos para Vacinação</h5></center><br>

                <div class="row">
                    <!-- DIASDEVIDA / COMENTARIO / QUANT DOSE / INTERVALO DOSE / TIPO (CICLICO ou SEQUENCIAL) / BLOCK_RESTRINGI VACINA A SER INSERIDA SEM TOMAR A PRIMEIRA -->
                    <div class="row">

                        <div class="col s2">
                            <label class="black-text" for="txtDose">Dose<i class="material-icons left">account_box</i></label>   
                            <input type="text" name="txtDose" id="txtDose" class="hoverable validate" placeholder="00" size="2" maxlength=2 required>                                
                        </div>
                        <div class='col s4'>
                            <label class="black-text left-align" for="txtMes">Intervalo<i class="material-icons left">assignment</i></label>
                            <select class="browser-default" onchange="CamposPersoanlizados()" id="txtIntervalo" name="txtIntervalo">
                                <option value="" selected>Selecione...</option>
                                <option value="0">Personalizado</option>
                                <option value="1">1 Mês (30 dias)</option>
                                <option value="2">2 Mês (60 dias)</option>
                                <option value="3">3 Mês (90 dias)</option>
                                <option value="4">4 Mês (120 dias)</option>
                                <option value="5">5 Mês (150 dias)</option>
                                <option value="6">6 Mês (180 dias)</option>
                                <option value="7">7 Mês (210 dias)</option>
                                <option value="8">8 Mês (240 dias)</option>
                                <option value="9">9 Mês (270 dias)</option>
                                <option value="10">10 Mês (300 dias)</option>
                                <option value="11">11 Mês (330 dias)</option>
                                <option value="01">1 Ano</option>
                                <option value="02">2 Ano</option>
                                <option value="03">3 Ano</option>
                                <option value="04">4 Ano</option>
                                <option value="05">5 Ano</option>
                                <option value="06">6 Ano</option>
                                <option value="07">7 Ano</option>
                                <option value="08">8 Ano</option>
                                <option value="09">9 Ano</option>
                                <option value="010">10 Ano</option>
                                <!-- Personalizado = 0 -->
                            </select>
                        </div>
                        <div class='col s3'>
                            <label class="black-text left-align" for="txtTipo">Tipo<i class="material-icons left">assignment</i></label>
                            <select class="browser-default" name="txtTipo">
                                <option value="" disabled selected>Selecione...</option>
                                <option value="seq">Sequencial</option>
                                <option value="ciclico">Ciclico</option>
                            </select>
                        </div>

                        <div class="col s3">
                            <label class="black-text left-align" for="txtBlock">Block<i class="material-icons left">assignment</i></label>
                            <select title="Caso o usuario não inserir a primeira dosagem da data inicial do calendario este campo pode bloquear ou liberar a inserir as demais dosagens" class="browser-default" name="txtBlock">
                                <option value="" disabled selected>Selecione...</option>
                                <option value="true">Ativar</option>
                                <option value="false">Desativar</option>
                            </select>

                        </div>

                    </div>

                    <div class="row">

                        <div id="mensagemUsuario"></div>

                        <div id="campospersonalizados">
                            <div class="col s2"></div>
                            <div class="col s6">
                                <label class="black-text left-align" for="txtMes">Adicionando Intervalos Personalizados <a id="NumberDose">DOSE 2</a><i class="material-icons left">assignment</i></label>
                                <select class="browser-default" id="txtIntervalosPersonalizados" name="txtIntervalosPersonalizados">
                                    <option value="" disabled selected>Selecione...</option>
                                    <option value="1">1 Mês (30 dias)</option>
                                    <option value="2">2 Mês (60 dias)</option>
                                    <option value="3">3 Mês (90 dias)</option>
                                    <option value="4">4 Mês (120 dias)</option>
                                    <option value="5">5 Mês (150 dias)</option>
                                    <option value="6">6 Mês (180 dias)</option>
                                    <option value="7">7 Mês (210 dias)</option>
                                    <option value="8">8 Mês (240 dias)</option>
                                    <option value="9">9 Mês (270 dias)</option>
                                    <option value="10">10 Mês (300 dias)</option>
                                    <option value="11">11 Mês (330 dias)</option>
                                    <option value="01">1 Ano</option>
                                    <option value="02">2 Ano</option>
                                    <option value="03">3 Ano</option>
                                    <option value="04">4 Ano</option>
                                    <option value="05">5 Ano</option>
                                    <option value="06">6 Ano</option>
                                    <option value="07">7 Ano</option>
                                    <option value="08">8 Ano</option>
                                    <option value="09">9 Ano</option>
                                    <option value="010">10 Ano</option>
                                </select>
                            </div>
                            <div class="col s3">  
                                <button type="button" class="btn" onclick='AdicionarIntervaloPersonalizadoArray()'>Adicionar</button>
                            </div>
                        </div>
                    </div>

                    <input name='objintervaloarray' id='objintervaloarray' hidden>

                    <div class="row">
                        <center><button type="submit" name="acao" value="Confirmar" class="btn">Cadastrar Calendario OB</button></center>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <script>
        //Setando o campo DIV campospersonalizados bloqueado para visualizaçao
        document.getElementById("campospersonalizados").style.display = 'none';

        //Variavel para aux 
        let contador = 1;
        let cont = 2;

        //Array para gravação de intervalos personalizados 
        let intervalosPersonalizados = [];

        //função para verificar se o usuario selecionou o campo "Personalizado"
        function CamposPersoanlizados() {
            var intervalodoses = document.getElementById("txtIntervalo").value;

            if (intervalodoses == "0") {

                document.getElementById("campospersonalizados").style.display = 'block';

            } else {
                document.getElementById("campospersonalizados").style.display = 'none';
            }
        }

        //Caso o usuario selecina o campo Personalizado, o sistema utilizará está função para adicionar no Array criado logo a cima 
        function AdicionarIntervaloPersonalizadoArray() {

            var intervalodosespersonalizados = document.getElementById("txtIntervalosPersonalizados").value;
            var doses = document.getElementById("txtDose").value;

            if (doses == "") {
                alert("Preencha a quantidade de doses");
            }

            if (contador < doses) {

                contador = contador + 1;

                document.getElementById("txtIntervalosPersonalizados").value = '';

                intervalosPersonalizados.push(intervalodosespersonalizados);


                cont = cont + 1;

                document.getElementById("NumberDose").innerHTML = "<a>DOSE " + cont + "</a>";



                if (contador == doses) {
                    document.getElementById("campospersonalizados").style.display = 'none';
                    document.getElementById("objintervaloarray").value = intervalosPersonalizados;
                    alert(intervalosPersonalizados);
                    document.getElementById("mensagemUsuario").innerHTML = "<center><h6>Intervalos Adicionados Com Sucesso!</h6></center>";
                }


            }

        }

    </script>

    <div class="modal-footer">
        <a href="#!" class="modal-close waves-effect waves-green btn-flat">Voltar</a>
    </div>
</div>

<!-- FUNCAO PARA ABRIR AS MODAl A CIMA -->
<script>

    $(document).ready(function () {
        $('.modal').modal();
    });

</script>

<main>
    <div class="container">
        <div class="row">            
            <div class="card z-depth-2">
                <div class="card-content">
                    <span class="card-title titulo-tabela center-align letra">Listando Vacinas Sem Calendario Obrigatório</span>
                    <br>
                    <div class="col s2"></div>
                    <div class="col s8">
                        <center><div style="overflow: scroll; width: auto; height: 350px; border:solid 1px">
                                <table class="striped">
                                    <TR>
                                        <td class="center-align letra">Vacina</td>
                                        <td class="center-align letra">Tipo</td>
                                        <td class="center-align letra">Cadastrar</td>
                                    </TR>

                                    <%for (Vacina v : listaVacina) {%>
                                    <TR>
                                        <td class="center-align"><%=v.getNome()%></td>
                                        <td class="center-align"><%=v.getTipo()%></td>
                                        <td class="center-align"><a href="#modalcadastrarvacina" onclick="PassarParametrosModal('<%= v.getNome()%>', '<%= v.getTipo()%>', '<%= v.getId_vacina()%>')" class="modal-trigger btn bnt-float"><i class="material-icons">add</i></a></td>
                                    </TR>
                                    <%}%>
                                </table>
                            </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
</body>

<script>

    function PassarParametrosModal(NomeVacina, TipoVacina, Idvacina) {

        document.getElementById("nomevacina").value = NomeVacina;
        document.getElementById("tipovacina").value = TipoVacina;
        document.getElementById("idVacina").value = Idvacina;
    }

</script>

<!-- IMPORTANDO RODAPE DA PAGINA -->
<jsp:include page="../paginas_utilitarias/rodape.jsp" />
</html>