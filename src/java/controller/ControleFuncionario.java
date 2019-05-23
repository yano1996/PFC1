/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FuncionarioDAO;
import dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Funcionario;
import model.PerfilDeAcesso;
import model.Usuario;

/**
 *
 * @author Victor_Aguiar
 */
@WebServlet(name = "ControleFuncionario", urlPatterns = {"/ControleFuncionario"})
public class ControleFuncionario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         try {
            String acao = request.getParameter("acao");
            switch (acao) {
                case "Cadastrar": {
                    cadastro(request, response);
                    break;
                }
                case "Exibir": {
                 exibir(request, response);
                    break;
                }
                case "Editar": {
                    editar(request, response);
                    break;
                }
                case "Confirmar": {
                    comfirma(request, response);
                    break;
                }
                case "Deletar": {
                    excluir(request, response);
                    break;
                }
                case "Atualizar": {
                    confirmaAtualizacao(request, response);
                    break;
                }
                default:
                    break;
            }
        } catch (Exception erro) {
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    
    }
    
    private void cadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Funcionario funcionario =new Funcionario();
        ArrayList<Usuario> listaUsuario = new ArrayList<>();
        ArrayList<Funcionario> listaFuncionario = new ArrayList<>();
        
        listaFuncionario =  funcionarioDAO.exibeFuncionarios();
////        listaUsuario = usuarioDAO.exibirUsuarios();
////        
////        request.setAttribute("listaUsuario", listaUsuario);
        request.setAttribute("listaFuncionario", listaFuncionario);
        RequestDispatcher rd = request.getRequestDispatcher("paginas_adm/cadastroFuncionario.jsp");
        rd.forward(request, response);
        
    }
     private void comfirma(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         Funcionario funcionario = new Funcionario();
         FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
         
         funcionario.setCofen(request.getParameter("txtConfen"));
         String perfil = request.getParameter("txtPerfil");
         if(perfil.equals("ADMINISTRADOR")){
         funcionario.setPerfil(PerfilDeAcesso.ADMINISTRADOR);
         }else if(perfil.equals("MEDICO")){
         funcionario.setPerfil(PerfilDeAcesso.MEDICO);
         }else if(perfil.equals("ENFERMEIRO")){
         funcionario.setPerfil(PerfilDeAcesso.ENFERMEIRO);
         }
         funcionario.setSenha(request.getParameter("txtSenha"));
         funcionario.setUsuario_id(Integer.parseInt(request.getParameter("txtUsuarioFK")));
         funcionario.setStatus(true);
         funcionarioDAO.cadastrarFuncionario(funcionario);
         
         exibir(request, response);
         
        
     }
     private void exibir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
         ArrayList<Funcionario> listaFuncionario = new ArrayList<>();
         UsuarioDAO usuarioDAO = new UsuarioDAO();
         listaFuncionario = funcionarioDAO.exibeFuncionarios();
         for(Funcionario f: listaFuncionario){
             Usuario usuario = new Usuario();
             usuario.setUsuario_id(f.getUsuario_id());
             usuario = usuarioDAO.buscaUsuarioUnico(usuario);
             f.setNome(usuario.getNome());
         }
         
         request.setAttribute("lista", listaFuncionario);
         RequestDispatcher rd = request.getRequestDispatcher("paginas_adm/exibirFuncionario.jsp");
         rd.forward(request, response);
         
         
     }
     
     private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         
        Funcionario funcionario = new Funcionario();
         FuncionarioDAO funcionariodao = new FuncionarioDAO();
         
         funcionario.setStatus(true);
         funcionario.setId_funcionario(Integer.parseInt(request.getParameter("id_funcionrio")));
         funcionario = funcionariodao.exibeFuncionariosUnico(funcionario);
         
         
         request.setAttribute("nome", request.getParameter("txtNome"));
         request.setAttribute("funcionario", funcionario);
         RequestDispatcher rd = request.getRequestDispatcher("paginas_adm/atualizaFuncionario.jsp");
         rd.forward(request, response);
         
     }
     
      private void confirmaAtualizacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         Funcionario funcionario = new Funcionario();
         FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
         funcionario.setId_funcionario(Integer.parseInt(request.getParameter("id_funcionrio")));
         funcionario.setCofen(request.getParameter("txtCofen"));
         String perfil = request.getParameter("txtPerfil");
         if (perfil.equals("ADMINISTRADOR")) {
             funcionario.setPerfil(PerfilDeAcesso.ADMINISTRADOR);
         } else if (perfil.equals("MEDICO")) {
             funcionario.setPerfil(PerfilDeAcesso.MEDICO);
         } else if (perfil.equals("ENFERMEIRO")) {
             funcionario.setPerfil(PerfilDeAcesso.ENFERMEIRO);
         }
         funcionario.setSenha(request.getParameter("txtSenha"));
         funcionario.setStatus(true);
         funcionarioDAO.atualizaFuncionario(funcionario);

         exibir(request, response);
     }  
     private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
         Funcionario funcionario = new Funcionario();
         funcionario.setId_funcionario(Integer.parseInt((request.getParameter("id_funcionrio"))));
         funcionario.setStatus(false);
         funcionarioDAO.excluirFuncionario(funcionario);
         
         exibir(request, response);
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
