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

<!DOCTYPE html>
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


    <%
        Date data = new Date();

        SimpleDateFormat formatarDate = new SimpleDateFormat("MM-yyyy");

    %>

    <script>

     let janeiro = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31];
     let fevereiro = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28];
     let marco = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30];
     let abril = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30];
     let maio = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31];
     let junho = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30];
     let julho = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31];
     let agosto = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31];
     let setembro = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30];
     let outubro = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31];
     let novembro = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30];
     let dezembro = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31];
     

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
            
           // alert(mescalculado + "-" + anocalculado);
            
            if(mescalculado == 1){
                
                ListaCalendario(janeiro);

            }else if(mescalculado == 2){
                
                ListaCalendario(fevereiro);
            
            }else if(mescalculado == 3){
                
                ListaCalendario(marco);
                
            }else if(mescalculado == 4){
                
                ListaCalendario(abril);
                
            }else if(mescalculado == 5){
                
                ListaCalendario(maio);
                
            }else if(mescalculado == 6){
                
                ListaCalendario(junho);
                
            }else if(mescalculado == 7){
                
                ListaCalendario(julho);
                
            }else if(mescalculado == 8){
                
                ListaCalendario(agosto);
                
            }else if(mescalculado == 9){
                
                ListaCalendario(setembro);
                
            }else if(mescalculado == 10){
                
                ListaCalendario(outubro);
                
            }else if(mescalculado == 11){
                
                ListaCalendario(novembro);
                
            }else if(mescalculado == 12){
                
                ListaCalendario(dezembro);
                
            }
            
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
            
             if(mescalculado == 1){
                
                ListaCalendario(janeiro);

            }else if(mescalculado == 2){
                
                ListaCalendario(fevereiro);
            
            }else if(mescalculado == 3){
                
                ListaCalendario(marco);
                
            }else if(mescalculado == 4){
                
                ListaCalendario(abril);
                
            }else if(mescalculado == 5){
                
                ListaCalendario(maio);
                
            }else if(mescalculado == 6){
                
                ListaCalendario(junho);
                
            }else if(mescalculado == 7){
                
                ListaCalendario(julho);
                
            }else if(mescalculado == 8){
                
                ListaCalendario(agosto);
                
            }else if(mescalculado == 9){
                
                ListaCalendario(setembro);
                
            }else if(mescalculado == 10){
                
                ListaCalendario(outubro);
                
            }else if(mescalculado == 11){
                
                ListaCalendario(novembro);
                
            }else if(mescalculado == 12){
                
                ListaCalendario(dezembro);
                
            }
            
            //alert(mescalculado + "-" + anocalculado);
            document.getElementById("data").value = mescalculado + "-" + anocalculado;
        }

    </script>

    <div id="demo"></div>
    
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
