<%-- 
    Document   : Cabecalho
    Created on : 25/11/2018, 12:08:07
    Author     : victo
--%>

<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    //recupera o usuario da sessao
    Usuario usuario = (Usuario) session.getAttribute("usuarioAutenticado");


%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!-- CORE CSS-->
        <link href="../framework/css/themes/fixed-menu/materialize.css" rel="stylesheet" type="text/css"/>
        <link href="../framework/css/themes/fixed-menu/style.css" rel="stylesheet" type="text/css"/>
        <!-- Custome CSS-->
        <link href="../framework/css/custom/custom.css" rel="stylesheet" type="text/css"/>
        <!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
        <link href="../framework/vendors/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" type="text/css"/>
        <link href="../framework/vendors/jvectormap/jquery-jvectormap.css" rel="stylesheet" type="text/css"/>

        <!-- IMPORTANDO BIBLIOTECA DE ICONES -->
        <link href="../framework/css/Css_Icones_Materialize/ImportICons.css" rel="stylesheet" type="text/css"/>

        <!-- Biblioteca da mascara dos inputs-->
        <script src="../framework/js/jQuery.js" type="text/javascript"></script>
        <!-- Css de letras-->
        <link href="../framework/css/css_letras.css" rel="stylesheet" type="text/css"/>
        <title>ICE</title>
    </head>

    <body>

        <!-- Start Page Loading -->
        <div id="loader-wrapper">
            <div id="loader"></div>
            <div class="loader-section section-left"></div>
            <div class="loader-section section-right"></div>
        </div>
        <!-- End Page Loading -->
        <!-- //////////////////////////////////////////////////////////////////////////// -->
        <!-- START HEADER -->
        <header id="header" class="page-topbar">
            <!-- start header nav-->
            <div class="navbar-fixed">
                <nav class="navbar-color">
                    <div class="nav-wrapper">
                        <ul class="left">
                            <li>
                                <h1 class="logo-wrapper">
                                    <a href="index-2.html" class="brand-logo darken-1">
                                        <span class="logo-text hide-on-med-and-down">ICE</span>
                                    </a>
                                </h1>
                            </li>
                        </ul>
                        <div class="header-search-wrapper hide-on-med-and-down">
                            <i class="material-icons">search</i>
                            <input type="text" name="Search" class="header-search-input z-depth-2" placeholder="Indisponivel" />
                        </div>
                        <ul class="right hide-on-med-and-down">
                            <li>
                                <a href="javascript:void(0);" class="waves-effect waves-block waves-light translation-button" data-activates="translation-dropdown">
                                    <span class="flag-icon flag-icon-gb"></span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0);" class="waves-effect waves-block waves-light toggle-fullscreen">
                                    <i class="material-icons">settings_overscan</i>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0);" class="waves-effect waves-block waves-light profile-button" data-activates="profile-dropdown">
                                    <span class="avatar-status avatar-online">
                                        <img src="../framework/Imagens/logo.png" alt="avatar">
                                        <i></i>
                                    </span>
                                </a>
                            </li>
                            <li>
                                <a href="#" data-activates="chat-out" class="waves-effect waves-block waves-light chat-collapse">
                                    <i class="material-icons">format_indent_increase</i>
                                </a>
                            </li>
                        </ul>

                        <!--             notifications-dropdown 
                                    <ul id="notifications-dropdown" class="dropdown-content">
                                      <li>
                                        <h6>NOTIFICATIONS
                                          <span class="new badge">5</span>
                                        </h6>
                                      </li>
                                      <li class="divider"></li>
                                      <li>
                                        <a href="#!" class="grey-text text-darken-2">
                                          <span class="material-icons icon-bg-circle cyan small">add_shopping_cart</span> A new order has been placed!</a>
                                        <time class="media-meta" datetime="2015-06-12T20:50:48+08:00">2 hours ago</time>
                                      </li>
                                      <li>
                                        <a href="#!" class="grey-text text-darken-2">
                                          <span class="material-icons icon-bg-circle red small">stars</span> Completed the task</a>
                                        <time class="media-meta" datetime="2015-06-12T20:50:48+08:00">3 days ago</time>
                                      </li>
                                      <li>
                                        <a href="#!" class="grey-text text-darken-2">
                                          <span class="material-icons icon-bg-circle teal small">settings</span> Settings updated</a>
                                        <time class="media-meta" datetime="2015-06-12T20:50:48+08:00">4 days ago</time>
                                      </li>
                                      <li>
                                        <a href="#!" class="grey-text text-darken-2">
                                          <span class="material-icons icon-bg-circle deep-orange small">today</span> Director meeting started</a>
                                        <time class="media-meta" datetime="2015-06-12T20:50:48+08:00">6 days ago</time>
                                      </li>
                                      <li>
                                        <a href="#!" class="grey-text text-darken-2">
                                          <span class="material-icons icon-bg-circle amber small">trending_up</span> Generate monthly report</a>
                                        <time class="media-meta" datetime="2015-06-12T20:50:48+08:00">1 week ago</time>
                                      </li>
                                    </ul>-->
                        <!-- profile-dropdown -->
                        <ul id="profile-dropdown" class="dropdown-content">
                            <li>
                                <a href="../ControleAcesso?acao=Sair" class="grey-text text-darken-1">
                                    <i class="material-icons">keyboard_tab</i> Logout</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
            <!-- end header nav-->
        </header>
        <!-- END HEADER -->
        <!-- //////////////////////////////////////////////////////////////////////////// -->
        <!-- START MAIN -->
        <div id="main">
            <!-- START WRAPPER -->
            <div class="wrapper">
                <!-- START LEFT SIDEBAR NAV-->
                <aside id="left-sidebar-nav">
                    <ul id="slide-out" class="side-nav fixed leftside-navigation">
                        <li class="user-details cyan darken-2">
                            <div class="row">
                                <div class="col col s4 m4 l4">
                                    <img src="../framework/Imagens/logo.png" alt="" class="circle responsive-img valign profile-image cyan">
                                </div>
                                <div class="col col s8 m8 l8">
                                    <ul id="profile-dropdown-nav" class="dropdown-content">
                                        <!--                    <li>
                                                              <a href="#" class="grey-text text-darken-1">
                                                                <i class="material-icons">face</i> Profile</a>
                                                            </li>
                                                            <li>
                                                              <a href="#" class="grey-text text-darken-1">
                                                                <i class="material-icons">settings</i> Settings</a>
                                                            </li>
                                                            <li>
                                                              <a href="#" class="grey-text text-darken-1">
                                                                <i class="material-icons">live_help</i> Help</a>
                                                            </li>
                                                            <li class="divider"></li>
                                                            <li>
                                                              <a href="#" class="grey-text text-darken-1">
                                                                <i class="material-icons">lock_outline</i> Lock</a>
                                                            </li>-->
                                        <li>
                                            <a href="../ControleAcesso?acao=Sair" class="grey-text text-darken-1">
                                                <i class="material-icons">keyboard_tab</i> Logout</a>
                                        </li>
                                    </ul>

                                    <a class="btn-flat dropdown-button waves-effect waves-light white-text profile-btn" href="#" data-activates="profile-dropdown-nav"><%= usuario.getNome()%><i class="mdi-navigation-arrow-drop-down right"></i></a>

                                    <p class="user-roal red-text">Usuario</p>
                                </div>
                            </div>
                        </li>
                        <li class="no-padding">
                            <ul class="collapsible" data-collapsible="accordion">
                                <li class="bold">
                                    <a href="principal_usuario.jsp" class="waves-effect waves-cyan active">
                                        <i class="material-icons blue-text">dashboard</i>
                                        <span class="nav-text letra123">Cardeneta</span>
                                    </a>
                                </li>

                                <li class="bold">
                                    <a class="collapsible-header waves-effect waves-cyan">
                                        <i class="material-icons blue-text">perm_identity</i>
                                        <span class="letra123 nav-text">Perfil</span>
                                    </a>
                                    <div class="collapsible-body">
                                        <ul>
                                            <li class="bold">

                                                <a href="#" class="collapsible-header waves-effect waves-cyan">
                                                    <span class="nav-text letra123">Visualizar Dados</span>
                                                    <i class="material-icons blue-text">keyboard_arrow_right</i>
                                                </a>

                                            </li>
                                            <li class="bold">

                                                <a href="#" class="collapsible-header waves-effect waves-cyan">
                                                    <span class="nav-text letra123">Alterar Senha</span>
                                                    <i class="material-icons blue-text">keyboard_arrow_right</i>
                                                </a>

                                            </li>
                                            <li class="bold">

                                                <a href="../ControleRestricoes?acao=ConsultarRestricoesParaUsuario&usuario_id=<%= usuario.getUsuario_id()%>" class="collapsible-header waves-effect waves-cyan">
                                                    <span class="nav-text letra123">Adicionar Restrições</span>
                                                    <i class="material-icons blue-text">keyboard_arrow_right</i>
                                                </a>

                                            </li>
                                        </ul>
                                    </div>
                                </li>


                                <li class="bold">
                                    <a class="collapsible-header waves-effect waves-cyan">
                                        <i class="material-icons blue-text">search</i>
                                        <span class="letra123 nav-text">-</span>
                                    </a>
                                    <div class="collapsible-body">
                                        <ul>
                                            <li>
                                                <form action="#" method="POST">
                                                    <a class="collapsible-header waves-effect waves-cyan black-text"><i class="material-icons blue-text">search</i>
                                                        <input type="submit" name="acao" value="-">
                                                    </a>
                                                </form>
                                                <!-- <a href="../paginas_vacina/cadastrar_vacina.jsp">
                                                     <i class="material-icons blue-text">keyboard_arrow_right</i>
                                                     <span>Vacinas</span>
                                                 </a> -->
                                            </li>

                                            <li class="bold">
                                                <form action="#" method="POST">
                                                    <a class="collapsible-header waves-effect waves-cyan black-text"><i class="material-icons blue-text">search</i>
                                                        <input type="submit" name="acao" value="-">
                                                    </a>
                                                </form>

                                                <!--<a href="../paginas_vacina/consultar_vacina.jsp" class="collapsible-header waves-effect waves-cyan active">
                                                    <i class="material-icons blue-text">dashboard</i>
                                                    <span class="nav-text letra123">ConsultarVacinas</span>
                                                </a>-->
                                            </li>
                                        </ul>
                                    </div>
                                </li>
                                <li class="bold">
                                    <a href="#" class="waves-effect waves-cyan">
                                        <span class="nav-text letra123">-</span>
                                        <i class="material-icons blue-text">keyboard_arrow_right</i>
                                    </a>
                                </li>
                                <li class="bold">
                                    <a class="collapsible-header waves-effect waves-cyan">
                                        <i class="material-icons blue-text">web</i>
                                        <span class="nav-text letra123">-</span>
                                    </a>
                                    <div class="collapsible-body">
                                        <ul>
                                            <li>
                                                <a href="#">
                                                    <i class="material-icons blue-text">keyboard_arrow_right</i>
                                                    <span>-</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <i class="material-icons blue-text">keyboard_arrow_right</i>
                                                    <span>-</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </li>

                                <li class="li-hover">
                                    <p class="ultra-small margin more-text center-align letra123 blue-text">-</p>
                                </li>
                                <li class="bold">
                                    <a href="" class="waves-effect waves-cyan">
                                        <i class="material-icons blue-text">assignment</i>
                                        <span class="nav-text letra123">-</span>
                                    </a>
                                </li>

                                <li class="no-padding">
                                    <ul class="collapsible" data-collapsible="accordion">
                                        <li class="bold">
                                            <a href="">
                                                <i class="material-icons blue-text">photo_filter</i>
                                                <span class="nav-text letra123">Desenvolvedor</span>
                                            </a>
                                        </li>
                                    </ul>
                                </li>

                                <li>
                                    <a target="_blank">
                                        <i class="material-icons blue-text">help_outline</i>
                                        <span class="nav-text letra123">Support</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <a href="#" data-activates="slide-out" class="sidebar-collapse btn-floating btn-medium waves-effect waves-light hide-on-large-only">
                        <i class="material-icons">menu</i>
                    </a>
                </aside>
                <!-- END LEFT SIDEBAR NAV-->
