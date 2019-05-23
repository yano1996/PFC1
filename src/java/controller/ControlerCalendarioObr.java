/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CalendarioObrigatorioDAO;
import dao.IntervaloVacinacaoDAO;
import dao.UsuarioDAO;
import dao.VacinaDAO;
import dao.Vacina_has_restricaoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CalendarioObrigatorio;
import model.Funcionario;
import model.IntervaloVacinacao;
import model.Usuario;
import model.Usuario_has_restricao;
import model.Vacina;
import model.Vacina_has_restricao;
import util.MesAnosEmDiasCadastroCalendarioObrigatorio;
import util.TratamentoDeData;

/**
 *
 * @author nelson_amaral
 */
@WebServlet(name = "ControlerCalendarioObr", urlPatterns = {"/ControlerCalendarioObr"})
public class ControlerCalendarioObr extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String acao = request.getParameter("acao");

            switch (acao) {
                case "Cadastrar": {
                    cadastroVacinaNoCalendarioObr(request, response);
                    break;
                }
                case "Consultar": {
                    exibeVacinaNoCalendarioObr(request, response);
                    break;
                }
                case "Editar": {
                    editarVacinaCalendario(request, response);
                    break;
                }
                case "Confirmar": {
                    cofirmarCadastro(request, response);
                    break;
                }
                case "Deletar": {
                    excluirVacinaCalendario(request, response);
                    break;
                }
                case "CofirmarAtualizacao": {
                    atualizaVacinaCalendario(request, response);
                    break;
                }
                case "listarmesescalendario": {
                    listarmesescalendario(request, response);
                    break;
                }
                default:
                    break;
            }
        } catch (Exception erro) {
            System.out.println("Erro (ControlerCalendarioObr)" + erro);
            RequestDispatcher rd = request.getRequestDispatcher("paginas_usuario/erro.jsp");
            rd.forward(request, response);
        }
    }

    //exibe todas as vacinas não cadastradas no calendario
    private void cadastroVacinaNoCalendarioObr(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VacinaDAO vacinaDAO = new VacinaDAO();
        ArrayList<Vacina> listaVacina = new ArrayList<>();

        listaVacina = vacinaDAO.ConsultarVacinasCalendario();

        request.setAttribute("listaVacinas", listaVacina);
        RequestDispatcher rd = request.getRequestDispatcher("paginas_calendarioobr/cadastro_vacinas_calendarioobr.jsp");
        rd.forward(request, response);
    }

    //exibe todas as vacinas cadastradas no calendario
    private void exibeVacinaNoCalendarioObr(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        CalendarioObrigatorioDAO calendarioDAO = new CalendarioObrigatorioDAO();

        VacinaDAO vacinaDAO = new VacinaDAO();
        Vacina vacina = new Vacina();

        vacina.setStatus(true);
        vacina.setStatusCalendario(true);

        ArrayList<Vacina> listavacinas = new ArrayList<>();
        ArrayList<CalendarioObrigatorio> listaVsCalendario = new ArrayList<>();
        
        listavacinas = vacinaDAO.ConsultarVacinasCadastradasCalendarioOB(vacina);

        for (Vacina objvacina : listavacinas) {
            
            CalendarioObrigatorio calendarioob = new CalendarioObrigatorio();

            calendarioob.setVacina(objvacina);

            calendarioDAO.buscaVacinaCalendarioObr(calendarioob);

            listaVsCalendario.add(calendarioob);
        }

        request.setAttribute("listaVacinas", listaVsCalendario);
        RequestDispatcher rd = request.getRequestDispatcher("paginas_calendarioobr/exibe_vacinas_calendarioobr.jsp");
        rd.forward(request, response);

    }

    //cadastrar os dados da nova vacina do calendario Obr no Banco
    public void cofirmarCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MesAnosEmDiasCadastroCalendarioObrigatorio anosMesParaDias = new MesAnosEmDiasCadastroCalendarioObrigatorio();
        TratamentoDeData cvDate = new TratamentoDeData();
        CalendarioObrigatorioDAO calendarioDAO = new CalendarioObrigatorioDAO();
        CalendarioObrigatorio calendario = new CalendarioObrigatorio();
        Vacina vacina = new Vacina();
        VacinaDAO vacinadao = new VacinaDAO();
        Funcionario funcionario = new Funcionario();

        int idfuncionario = Integer.parseInt(request.getParameter("idfuncionario"));
        int idVacina = Integer.parseInt(request.getParameter("idVacina"));
        boolean block = Boolean.getBoolean(request.getParameter("txtBlock"));
        String tipo = request.getParameter("txtTipo");
        funcionario.setId_funcionario(idfuncionario);
        vacina.setId_vacina(idVacina);

        calendario.setBlock(block);
        calendario.setDateCadastro(cvDate.buscaDataAtual());
        calendario.setHoraCadastro(cvDate.buscaHoraAtual());
        calendario.setStatus(true);
        calendario.setVacina(vacina);
        calendario.setFuncionario(funcionario);
        calendario.setComentario("");
        calendario.setCiclo(tipo);
        calendario.setDose(Integer.parseInt(request.getParameter("txtDose")));

        calendario.setId_calendarioObr(calendarioDAO.cadastraVacinaCalendarioObr(calendario));

        //alterando status da vacina para (CALENeDARIO_OBRIGATORIO CRIADO PARA A VACINA A RESPEITO)
        if (calendario.getId_calendarioObr() == 0) {
            response.sendRedirect("paginas_erro/desenvolvimento.jsp");
        } else {
            //Setando stats que desejamos que sejá alterado 
            vacina.setStatus(true);

            //chamando a classe dao para alterar o campo 
            vacinadao.atualizar_campo_vacinaCalendarioob(vacina);
        }

        int anosVida = Integer.parseInt(request.getParameter("txtAnosVida"));
        String mes = request.getParameter("txtMes");
        int dias = Integer.parseInt(request.getParameter("txtDias"));
        int dose = Integer.parseInt(request.getParameter("txtDose"));
        int intervalo = anosMesParaDias.mesParaDias(request.getParameter("txtIntervalo"));
        String[] intervalosPersonalizados = request.getParameterValues("objintervaloarray");

        //converte dias, mes em dias para uma unico int
        int DoseInicialdiasDeVida = cvDate.somadia_mes_ano(dias, anosMesParaDias.anoOuDias(mes), anosVida);

        IntervaloVacinacao intervaloVacinacao = new IntervaloVacinacao();
        intervaloVacinacao.setCalendarioObr(calendario);
        intervaloVacinacao.setDose(dose);
        intervaloVacinacao.setAtivoOuNao(true);

        if (intervalo == 0) {

            //Instanciando um arrayList 
            List<String> list = Arrays.asList(intervalosPersonalizados);

            //Criando variavel String[] para receber os dados extraidos do array de string do formulario
            String[] arraydein = null;

            //For para listar e adicionar a variavel criada a cima separado por virguça
            for (String valor : list) {

                //Adicionando utilizando o SPLIT (METODO PARA SEPARA O ARRAY)
                arraydein = valor.split(",");

            }

            //Chamando a função para cadastrar o intervalo personalizado
            intervalopersonalizado(intervaloVacinacao, arraydein, DoseInicialdiasDeVida);

        } else {

            //Chamando a função para cadastrar intervalo fixo
            intervalofixo(intervaloVacinacao, DoseInicialdiasDeVida, intervalo);

        }

        cadastroVacinaNoCalendarioObr(request, response);

    }

    //Altera o campo status calendario como falso dentro da vacina 
    public void excluirVacinaCalendario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //MODELO
        CalendarioObrigatorio calendario = new CalendarioObrigatorio();
        IntervaloVacinacao intervalovacinacao = new IntervaloVacinacao();
        Vacina vacina = new Vacina();
        
        //DAO
        CalendarioObrigatorioDAO caledarioDAO = new CalendarioObrigatorioDAO();
        IntervaloVacinacaoDAO intervalovacinacadao = new IntervaloVacinacaoDAO();
        VacinaDAO vacinadao = new VacinaDAO();
        
        //Recebendo Dados do Formulario
        calendario.setId_calendarioObr(Integer.parseInt(request.getParameter("calendarioOB_ID")));
        
        //Criando list retorno calendarioOBR
        ArrayList<IntervaloVacinacao> listcalendario = new ArrayList<>();
        
        intervalovacinacao.setAtivoOuNao(true);
        intervalovacinacao.setCalendarioObr(calendario);
        
        //Consultando todos os interlavos de vacinacao do determinado calendarioOB
        listcalendario = intervalovacinacadao.buscaUnicaIntervaloVacinacao(intervalovacinacao);
        
        //Lopping para desativar todos os interlavos do calendario especifico
        for (IntervaloVacinacao retornoDAO: listcalendario){
            
            retornoDAO.setAtivoOuNao(false);
            retornoDAO.setCalendarioObr(calendario);
            
            intervalovacinacadao.excluirIntervaloVacinacao(retornoDAO);
            
        }
        
        //Excluindo Logicamente o calendario Obrigatorio da vacina especifica
        calendario.setStatus(false);
        caledarioDAO.excluirVacinaCalendarioObr(calendario);
        
        //Buscando o Id da vacina que esta cadastrado no calendario OBR
        int id_vacina = 0;
        id_vacina = caledarioDAO.buscaIdVacinaCalendarioobrEspecifico(calendario);
        
        //Setando as informção e excluindo logicamente a vacina
        vacina.setStatus(false);
        vacina.setId_vacina(id_vacina);
        vacinadao.atualizar_campo_vacinaCalendarioob(vacina);

        exibeVacinaNoCalendarioObr(request, response);
    }

    //Busca a vacina do calendario Obrigatorio e envia para o forme de atualização
    public void editarVacinaCalendario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        CalendarioObrigatorio calendario = new CalendarioObrigatorio();
//        CalendarioObrigatorioDAO calendarioDAO = new CalendarioObrigatorioDAO();
//        VacinaDAO vacinaDAO = new VacinaDAO();
//        calendario.setId_calendarioObr(Integer.parseInt(request.getParameter("id_Calendario")));
//
//        calendario = calendarioDAO.buscaUnicaVacinaCalendarioObr(calendario.getId_calendarioObr());
//
//        calendario.setVacina(vacinaDAO.buscarVacinaUnica(calendario.getVacina().getId_vacina()));
//
//        request.setAttribute("vacina", calendario);
//        RequestDispatcher rd = request.getRequestDispatcher("paginas_calendarioobr/atualiza_vacina_calendarioobr.jsp");
//        rd.forward(request, response);

    }

    //Atualiza a quantidades de dias vividos necessários para a inserção do medicamentos
    public void atualizaVacinaCalendario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TratamentoDeData cvDate = new TratamentoDeData();
        CalendarioObrigatorioDAO calendarioDAO = new CalendarioObrigatorioDAO();
        CalendarioObrigatorio calendarioObr = new CalendarioObrigatorio();

        calendarioObr.getVacina().setId_vacina(Integer.parseInt(request.getParameter("id_vacina")));

        int dias = Integer.parseInt(request.getParameter("txtDia"));
        int mes = Integer.parseInt(request.getParameter("txtMes"));
        int ano = Integer.parseInt(request.getParameter("txtAno"));

        calendarioObr.setComentario(request.getParameter("txtComentario"));
        calendarioObr.setBlock(Boolean.valueOf(request.getParameter("txtBlock")));

        calendarioDAO.atualizaVacinaCalendarioObr(calendarioObr);

        exibeVacinaNoCalendarioObr(request, response);

    }

    //
    private void listarmesescalendario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {

        //Modelos
        Usuario usuario = new Usuario();
        Vacina vacina = new Vacina();
        CalendarioObrigatorio calendariobbrigatorio = new CalendarioObrigatorio();
        TratamentoDeData tratamentodata = new TratamentoDeData();
        IntervaloVacinacao intervalov = new IntervaloVacinacao();
        
        //Daos
        UsuarioDAO usuariodao = new UsuarioDAO();
        CalendarioObrigatorioDAO calendarioobrdao = new CalendarioObrigatorioDAO();     
        Vacina_has_restricaoDAO v_h_rDAO = new Vacina_has_restricaoDAO();
        
        
        ArrayList<CalendarioObrigatorio> listVacinasPorFaixaDeVida = new ArrayList<>();
        //ArrayList<Vacina_has_restricao> listvacinasrestricoes = new ArrayList<>();
        ArrayList<Vacina> listvacinas = new ArrayList<>();
        
        int dia = Integer.parseInt(request.getParameter("dia"));
        int mes = Integer.parseInt(request.getParameter("mes"));
        int ano = Integer.parseInt(request.getParameter("ano"));
        int usuario_id = Integer.parseInt(request.getParameter("usuario_id"));
        
        usuario.setUsuario_id(usuario_id);
        
        usuario = usuariodao.buscaUsuarioUnico(usuario);
        
        String dataformulada = request.getParameter("dia") + "/" +request.getParameter("mes") + "/" +request.getParameter("ano");
        
        long diasVida_usuario = CalcularNascimentoEmDiasVida(usuario,dataformulada);
        
        calendariobbrigatorio = ConsultaAFaixaDiasDeVidaMesAtual(diasVida_usuario,dia,mes,calendarioobrdao,calendariobbrigatorio);
        
        
         calendariobbrigatorio.setStatus_intervalov(true);
         calendariobbrigatorio.setStatus(true);
         
         listVacinasPorFaixaDeVida = calendarioobrdao.buscarVacinasPorFaixasdeVidaParaocalendario(calendariobbrigatorio);
               
        for (CalendarioObrigatorio calendario : listVacinasPorFaixaDeVida) {
            
            Vacina_has_restricao v_h_r = new Vacina_has_restricao();
            Usuario_has_restricao u_v_r = new Usuario_has_restricao();

            v_h_r.setVacina_id(calendario.getVacina().getId_vacina());
            u_v_r.setUsuario_id(usuario_id);

            
            int retorno_idvacina = v_h_rDAO.ConsultarVacinaComrestricoesIguaisAoUsuario(v_h_r, u_v_r);

            if(retorno_idvacina == 0){
                Vacina vacinalop = new Vacina();
                vacinalop.setId_vacina(calendario.getVacina().getId_vacina());
//                System.out.println("RETORNO"+calendario.getVacina().getId_vacina());
//                System.out.println("ID CALENDARIO"+calendario.getId_calendarioObr()); 
                listvacinas.add(vacinalop);
                
            }
        }
        
        ArrayList<CalendarioObrigatorio> listdevacinausuario = new ArrayList<>();
        
        
        for (Vacina listVacinasUsuarioPodeTomar: listvacinas){
            ArrayList<CalendarioObrigatorio> aux = new ArrayList<>();
            calendariobbrigatorio.setVacina(vacina);
            calendariobbrigatorio.setIntervalov(intervalov);
            calendariobbrigatorio.setStatus(true);
            calendariobbrigatorio.getVacina().setId_vacina(listVacinasUsuarioPodeTomar.getId_vacina());
            calendariobbrigatorio.getIntervalov().setAtivoOuNao(true);
            
//               System.out.println("VACINA_ID: "+listVacinasUsuarioPodeTomar.getId_vacina());
//             System.out.println("FINALMES: "+calendariobbrigatorio.getDiasvidafinalmes());
//              System.out.println("INICIOMES: "+calendariobbrigatorio.getDiasvidainiciomes());
//               System.out.println(calendariobbrigatorio.isStatus());
//                 System.out.println("============================");
            
            aux =  calendarioobrdao.buscarVacinasParaExibirNoCalendarioUsuario(calendariobbrigatorio);
            
            for (CalendarioObrigatorio calend: aux){
                
//                 System.out.println("VACINA_ID: "+calend.getVacina().getId_vacina());
//             System.out.println("FINALMES: "+calend.getDiasvidafinalmes());
//              System.out.println("INICIOMES: "+calend.getDiasvidainiciomes());
//               System.out.println(calend.isStatus());
//                 System.out.println("============================");
                
                listdevacinausuario.add(calend);
            
            }
        }
        
        int diasdomes = tratamentodata.retornaTamanhoDoMesReferente(mes);
        
        request.setAttribute("listavacinasusuario", listdevacinausuario);
        request.setAttribute("totaldiasmes", diasdomes);
        request.setAttribute("diasdevidanoiniciomes", calendariobbrigatorio.getDiasvidainiciomes());
        RequestDispatcher rd = request.getRequestDispatcher("paginas_paciente/listar_calendarioAjax.jsp");
        rd.forward(request, response);

    }
    
    private long CalcularNascimentoEmDiasVida(Usuario usuario, String dataatual) throws ParseException{
        
          // Dando um exemplo: quantos dias se passam desde 07/09/1822 até 05/06/2006?
        DateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
        Date data = new Date();
        df.setLenient(false);
        Date d1 = df.parse (df.format(usuario.getNascimento()));
//        System.out.println (d1);
        Date d2 = df.parse (dataatual);
//        System.out.println (d2);
        long dt = (d2.getTime() - d1.getTime()) + 3600000; // 1 hora para compensar horário de verão
        
        long diasdevida = dt / 86400000L;
          
        return diasdevida;
    }
    
    private CalendarioObrigatorio ConsultaAFaixaDiasDeVidaMesAtual(Long diasvida,int diaatual,int mesatual,CalendarioObrigatorioDAO calendarioobrdao,CalendarioObrigatorio  calendarioobrigatorio) throws ParseException{
       
         TratamentoDeData tratamentodata = new TratamentoDeData();
         
         int diasmes = tratamentodata.retornaTamanhoDoMesReferente(mesatual);
        
         //Calculando faixa para pesquisa no banco por dias
         int subtracao = diasmes - diaatual;
         
         int diasdevidanofinaldomes = subtracao + Integer.valueOf(diasvida.toString());
         
         int diasdevidanoiniciodomes = Integer.valueOf(diasvida.toString()) - diaatual;
         
         calendarioobrigatorio.setDiasvidafinalmes(diasdevidanofinaldomes);
         calendarioobrigatorio.setDiasvidainiciomes(diasdevidanoiniciodomes);
        
         return calendarioobrigatorio;
    }

    //Função utilizada para cadastrar um calendario_obrigatorio
    private void intervalofixo(IntervaloVacinacao intervaloVacinacao, int DoseInicialdiasDeVida, int intervalofixo) throws ServletException, IOException {
        IntervaloVacinacaoDAO intervaloVacinacaoDAO = new IntervaloVacinacaoDAO();

        //Contador para exibir a sequencia das doses a ser cadastrada
        int i = 0;
        
        int doses = intervaloVacinacao.getDose();
        
        int cont = DoseInicialdiasDeVida;
        
        while (i < doses) {
            
            //auto increment variavel i
            i++;
            
            intervaloVacinacao.setDose(i);
            
            if (i == 1) {
                
                intervaloVacinacao.setDias(DoseInicialdiasDeVida);
                
            } else {

                cont = cont + intervalofixo;
                intervaloVacinacao.setDias(cont);
                
            }
            intervaloVacinacaoDAO.cadastraIntervaloVacinacao(intervaloVacinacao);

        }
    }

    //Função utilizada para cadastrar um calendario_obrigatorio
    private void intervalopersonalizado(IntervaloVacinacao intervaloVacinacao, String[] intervalos, int DoseInicialdiasDeVida) throws ServletException, IOException {
        MesAnosEmDiasCadastroCalendarioObrigatorio anosMesParaDias = new MesAnosEmDiasCadastroCalendarioObrigatorio();
        IntervaloVacinacaoDAO intervaloVacinacaoDAO = new IntervaloVacinacaoDAO();

        //Contador para exibir a sequencia das doses a ser cadastrada
        int i = 1;
        
        //Variavel utilizada para somar os valores do intervalo personalizado
        int cont = DoseInicialdiasDeVida;
        
        if (intervalos != null) {

            intervaloVacinacao.setDose(i);
            
            intervaloVacinacao.setDias(DoseInicialdiasDeVida);
            
            intervaloVacinacaoDAO.cadastraIntervaloVacinacao(intervaloVacinacao);

            for (String s : intervalos) {
                //auto increment variavel i
                i++;
                
                intervaloVacinacao.setDose(i);

                //Operacao de somatoria dos intervalos personalizado de cada dose
                cont = cont + anosMesParaDias.anoOuDias(String.valueOf(s));
                
                intervaloVacinacao.setDias(cont);

                intervaloVacinacaoDAO.cadastraIntervaloVacinacao(intervaloVacinacao);

            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
