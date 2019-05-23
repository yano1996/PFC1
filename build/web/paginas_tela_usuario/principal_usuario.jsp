<%-- 
    Document   : home_comum
    Created on : 11/11/2018, 14:14:08
    Author     : nelson_amaral
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- IMPORTANDO CABECALHO DA PAGINA -->
<jsp:include page="../paginas_utilitarias/cabecalho_03.jsp" />

<%
    //recupera o usuario da sessao
    Usuario usuario = (Usuario) session.getAttribute("usuarioAutenticado");

    if (usuario != null);

%>

<!DOCTYPE html>
<html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Calendario</title>

        <style>

            td {
                height: 25px;
                width: 30px;
                background: #f5f5f5;
                font: 12px verdana;							  
                text-align: center;
            }

            .mes {
                font: bold 12px verdana;
                background: #f5f5f5;
                text-align: center;
                line-height: 25px;							
                height: 25px;
                width: 240px;
            }

            .semana {
                font: bold 12px verdana;
            }

            .dia {
                height: 23px;
                width: 30px;
                padding-top:6px;
                vertical-align:middle;
                position:relative;
            }

            .evento {
                position: relative;
                font: 9px verdana;
                color: #C30;
                position:absolute;
                right: -2px;
                top: -2px;					  
            }

        </style>

    </head>


    <%        Date data = new Date();

        SimpleDateFormat formatarDate = new SimpleDateFormat("MM-yyyy");

    %>

    <script>
        function dataanterior() {

            var data = document.getElementById("data").value;

            var dataformatada = '01-' + data;

            const date = new Date(dataformatada);

            var mescalculado = date.getDate() - 1;
            var anocalculado = date.getFullYear();

            if (mescalculado == 0) {
                mescalculado = 12;
                anocalculado = date.getFullYear() - 1;
            }

            let usuario_id = document.getElementById("usuario_id").value;

            ListaCalendario(19, mescalculado, anocalculado, usuario_id);

            document.getElementById("data").value = mescalculado + "-" + anocalculado;

        }

        function dataposterior() {

            var data = document.getElementById("data").value;

            var dataformatada = '01-' + data;

            const date = new Date(dataformatada);

            var mescalculado = date.getDate() + 1;
            var anocalculado = date.getFullYear();

            if (mescalculado > 12) {
                mescalculado = 01;
                anocalculado = date.getFullYear() + 1;
            }

            let usuario_id = document.getElementById("usuario_id").value;

            ListaCalendario(19, mescalculado, anocalculado, usuario_id);

            //alert(mescalculado + "-" + anocalculado);
            document.getElementById("data").value = mescalculado + "-" + anocalculado;
        }

    </script>

    <div id="demo"></div>

    <!-- Id para aux da busca para a lista de vacinas do mes do usuario -->
    <input id="usuario_id" value="<%= usuario.getUsuario_id()%>" hidden>

    <body>
        <div class="row">
            <div class="col s12">
                <div class="card z-depth-2">
                    <div class="card-content">
                        <div class="col s4"></div>


                        <div class="col s7">
                            <div class="row">
                                <div class="col s2">
                                    <a class="btn" onclick="dataanterior()"><i class=""></i></a>
                                </div>
                                <div class="col s3">
                                    <center><input class="center-align" id="data" value="<%= formatarDate.format(data)%>"></center>
                                </div>
                                <div class="col s2">
                                    <a class="btn" onclick="dataposterior()"><i class=""></i></a>
                                </div>

                            </div>
                        </div>

                        <div id="CalendarioCarregado"></div>


                    </div>
                </div>
            </div>
        </div>
    </body>
</html> 

<!-- IMPORTANDO RODAPE DA PAGINA -->
<jsp:include page="../paginas_utilitarias/rodape_02.jsp" />

<script src="../framework/js/Busca_Ajax/Busca_Calendario_Principal/Buscar-meses_calendario.js" type="text/javascript"></script>

</html>
