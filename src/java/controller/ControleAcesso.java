/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FuncionarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.PerfilDeAcesso;
import model.Usuario;
import dao.UsuarioDAO;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Funcionario;
import util.ValidaFormes;

/**
 *
 * @author nelson_amaral
 */
@WebServlet(name = "ControleAcesso", urlPatterns = {"/ControleAcesso"})
public class ControleAcesso extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String acao = request.getParameter("acao");
            switch (acao) {
                case "Acesso":
                    acesso(request, response);
                    break;
                case "Voltar":
                    voltar(request, response);
                    break;
                case "Sair":
                    sair(request, response);
                    break;
                default:
                    break;
            }
        } catch (Exception erro) {
            System.out.println("Erro (ControleAcesso): " + erro);
            RequestDispatcher rd = request.getRequestDispatcher("paginas_erro/erro.jsp");
            rd.forward(request, response);
        }
    }
    private void acesso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String login = ValidaFormes.Formulario(request.getParameter("txtRg"));
        String senha = ValidaFormes.Formulario(request.getParameter("txtSenha"));
            if(login.length() < 7){
                
            acessoFuncionario(request, response,login,senha);
            
        }else if(login.length() < 10){
            
            acessoUsuario(request, response,login,senha);
            
        }else{
            
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            request.setAttribute("msg", "Rg ou Senha Incorreto!");
            rd.forward(request, response);
        }
        
    }
    
    private void acessoFuncionario (HttpServletRequest request, HttpServletResponse response,String cofen, String senha) throws ServletException, IOException {
        Funcionario funcionario = new Funcionario();
        funcionario.setCofen(cofen);
        funcionario.setSenha(senha);

        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        UsuarioDAO usuarioDAO =new UsuarioDAO();
        Usuario usuario = new Usuario();
        
  
        Funcionario funcionarioAutenticado = funcionarioDAO.autenticaFuncionario(funcionario);
        
        //se o usuario existe no banco de dados.
        if (funcionarioAutenticado != null) {
            
            usuario.setUsuario_id(funcionarioAutenticado.getUsuario_id());
        
            funcionarioAutenticado.setNome(usuarioDAO.buscaUsuarioUnico(usuario).getNome());
            
            //cria uma sessao para o usuario
            HttpSession sessaoUsuario = request.getSession();
            sessaoUsuario.setAttribute("usuarioAutenticado", funcionarioAutenticado);

            PerfilDeAcesso nivelacesso = funcionarioAutenticado.getPerfil();
            switch (nivelacesso) {
                case MEDICO:
                    //redireciona para a pagina princiapal
                    response.sendRedirect("paginas_erro/desenvolvimentoMedico.jsp");
                    break;
                case ENFERMEIRO:
                    //redireciona para a pagina princiapal
                    response.sendRedirect("paginas_erro/desenvolvimento.jsp");
                    break;
                case ADMINISTRADOR:
                    //redireciona para a pagina princiapal
                    response.sendRedirect("paginas_adm/principal.jsp");
                    break;
                case USUARIO:
                    //redireciona para a pagina princiapal
                    response.sendRedirect("paginas_tela_usuario/principal_usuario.jsp");
                    break;
                default:
                    break;
            }

        } else {
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            request.setAttribute("msg", "Rg ou Senha Incorreto!");
            rd.forward(request, response);
        }
    }
    
    private void acessoUsuario(HttpServletRequest request, HttpServletResponse response,String rg, String senha) throws ServletException, IOException {
        Usuario usuario = new Usuario();

        usuario.setRg(rg);
        usuario.setSenha(senha);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario UsuarioAutenticado = usuarioDAO.autenticaUsuario(usuario);
        
        //se o usuario existe no banco de dados.
        if (UsuarioAutenticado != null) {
            
            //cria uma sessao para o usuario
            HttpSession sessaoUsuario = request.getSession();
            sessaoUsuario.setAttribute("usuarioAutenticado", UsuarioAutenticado);
            
//            Date data = new Date();
//            
//            SimpleDateFormat dia = new SimpleDateFormat("dd");
//            SimpleDateFormat mes = new SimpleDateFormat("M");
//            SimpleDateFormat ano = new SimpleDateFormat("yyyy");
//            
//            int usuario_id = UsuarioAutenticado.getUsuario_id();
//
//            //redireciona para a pagina princiapal
//            response.sendRedirect("ControlerCalendarioObr?acao=listarmesescalendario&dia="+dia.format(data)+"&mes="+mes.format(data)+"&ano="+ano.format(data)+"&usuario_id="+usuario_id);
//            
              response.sendRedirect("paginas_tela_usuario/principal_usuario.jsp");
        } else {
            
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            request.setAttribute("msg", "Rg ou Senha Incorreto!");
            rd.forward(request, response);
            
        }
    }
    
    private void voltar(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession sessaoUsuario = request.getSession();

        sessaoUsuario.setAttribute("usuarioAutenticado", sessaoUsuario.getAttribute("usuarioAutenticado"));

        //redireciona para a pagina princiapal
        response.sendRedirect("paginas_adm/principal.jsp");
    }
    
    private void sair(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession sessaoUsuario = request.getSession();
        sessaoUsuario.removeAttribute("usuarioAutenticado");
        response.sendRedirect("index.jsp");
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
