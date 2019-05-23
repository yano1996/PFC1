/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.RestricaoDAO;
import dao.Usuario_has_restricaoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Restricao;
import model.Usuario_has_restricao;

/**
 *
 * @author Victor Aguiar
 */
public class ControleRestricoes extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String acao = request.getParameter("acao");
            switch (acao) {
                case "Cadastrar":
                    cadastro(request, response);
                    break;
                case "Listar":
                    //Vitor Verificar 
                    listarRestricoes(request, response);
                    break;
                case "Deletar":
                    delete(request, response);
                    break;
                case "Editar":
                    //Vitor Verificar Construir forme 
                    editar(request, response);
                    break;
                case "Confirma":
                    //Vitor Verificar Construir forme 
                    atualizaRestricao(request, response);
                    break;
                case "Cadastrar Vacinas":
                    boxVacinas(request, response);
                    break;
                case "ConsultarRestricoesParaUsuario":
                    consultarRestricaoParaUsuario(request, response);
                    break;
                case "Cadastrar Restricao":
                    cadastrarRestricaoUsuario(request, response);
                    break;
                default:
                    break;
            }

        } catch (Exception erro) {
            System.out.println("Erro (ControleRestricao)" + erro);
            RequestDispatcher rd = request.getRequestDispatcher("paginas_erro/erro.jsp");
            rd.forward(request, response);
        }
    }

    private void cadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Restricao restricao = new Restricao();
        RestricaoDAO restricaoDAO = new RestricaoDAO();
        restricao.setRestricao_nome(request.getParameter("txtNome"));
        restricao.setRestricao_tipo(request.getParameter("txtTipo"));
        restricao.setRestricao_status(true);

        restricaoDAO.cadastaNovoRestricao(restricao);

        ArrayList<Restricao> listrestricao = new ArrayList<>();
        listrestricao = restricaoDAO.consultarRestricao();
        request.setAttribute("listarestricaoz", listrestricao);
        RequestDispatcher rd = request.getRequestDispatcher("/paginas_restricoes/consultar_restricoes.jsp");
        rd.forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RestricaoDAO restricaoDAO = new RestricaoDAO();
        Restricao restricao = new Restricao();

        restricao.setRestricao_id(Integer.parseInt(request.getParameter("id_restricao")));
        restricaoDAO.deletarRestricao(restricao);

        ArrayList<Restricao> listrestricaoda = new ArrayList<>();
        listrestricaoda = restricaoDAO.consultarRestricao();
        request.setAttribute("listarestricaoz", listrestricaoda);
        RequestDispatcher rdd = request.getRequestDispatcher("/paginas_restricoes/consultar_restricoes.jsp");
        rdd.forward(request, response);
    }

    //Metodo referencia o select no forme do cadastro da vacina
    private void boxVacinas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RestricaoDAO restricaoDAO = new RestricaoDAO();
        ArrayList<Restricao> listrestricaoz = new ArrayList<>();
        listrestricaoz = restricaoDAO.consultarRestricao();
        request.setAttribute("listarestricao", listrestricaoz);
        RequestDispatcher rdz = request.getRequestDispatcher("/paginas_vacina/cadastrar_vacina.jsp");
        rdz.forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Restricao restricao = new Restricao();

        restricao.setRestricao_nome(request.getParameter("nome_restricao"));
        restricao.setRestricao_tipo(request.getParameter("tipo_restricao"));
        restricao.setRestricao_id(Integer.parseInt(request.getParameter("id_restricao")));

        request.setAttribute("res", restricao);
        RequestDispatcher rdz = request.getRequestDispatcher("paginas_restricoes/atualiza_restricoes.jsp");
        rdz.forward(request, response);
    }

    private void atualizaRestricao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Restricao restricao = new Restricao();
        RestricaoDAO restricaoDAO = new RestricaoDAO();

        restricao.setRestricao_nome(request.getParameter("txtNome"));
        restricao.setRestricao_tipo(request.getParameter("txtTipo"));
        restricao.setRestricao_id(Integer.parseInt(request.getParameter("txtId")));
        restricaoDAO.atualizaRestricao(restricao);

        listarRestricoes(request, response);
    }

    private void listarRestricoes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RestricaoDAO restricaoDAO = new RestricaoDAO();
        ArrayList<Restricao> listrestricao = new ArrayList<>();
        listrestricao = restricaoDAO.consultarRestricao();
        request.setAttribute("listarestricaoz", listrestricao);
        RequestDispatcher rd = request.getRequestDispatcher("/paginas_restricoes/consultar_restricoes.jsp");
        rd.forward(request, response);
    }

    private void consultarRestricaoParaUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RestricaoDAO restricaoDAO = new RestricaoDAO();
        Usuario_has_restricaoDAO u_h_rDAO = new Usuario_has_restricaoDAO();

        ArrayList<Restricao> listrestricao = new ArrayList<>();
        ArrayList<Usuario_has_restricao> listrestricaousuario = new ArrayList<>();

        int usuario_id = Integer.parseInt(request.getParameter("usuario_id"));

        listrestricao = restricaoDAO.consultarRestricao();

        listrestricaousuario = u_h_rDAO.exibirUsuario_has_restricao(usuario_id);

        ArrayList<Restricao> listrestricoesparausuario = new ArrayList<>();

        int contador = 0;
        
        for (Restricao rest: listrestricao) {

            for (Usuario_has_restricao uhr : listrestricaousuario) {

                if (uhr.getRestricao_id() == rest.getRestricao_id()) {

                    contador = 1;

                }

            }
            
            if(contador == 0){
                listrestricoesparausuario.add(rest);
            }
            
          contador=0;
           

        }

        request.setAttribute("listarestricaoz", listrestricoesparausuario);
        RequestDispatcher rd = request.getRequestDispatcher("/paginas_paciente/cadastrar_restricao_usuario.jsp");
        rd.forward(request, response);

    }
    
       private void cadastrarRestricaoUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        Usuario_has_restricaoDAO uhrDAO = new Usuario_has_restricaoDAO();
               
        ArrayList<Restricao> listrestricao = new ArrayList<>();        
        
        String[] restricoes = request.getParameterValues("TXTrestricaoID");
        
        for (String adicionarR: restricoes){
            
            Usuario_has_restricao uhr = new Usuario_has_restricao();
            
            uhr.setUsuario_id(Integer.parseInt(request.getParameter("TXTusuario_id")));
            uhr.setRestricao_id(Integer.parseInt(adicionarR));
            
            uhrDAO.cadastroUsuario_has_restricaoDAO(uhr);
            
        }
                
        request.setAttribute("listarestricaoz", listrestricao);
        RequestDispatcher rd = request.getRequestDispatcher("/paginas_paciente/cadastrar_restricao_usuario.jsp");
        rd.forward(request, response);
        
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
