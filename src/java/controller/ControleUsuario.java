/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EnderecoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.String.format;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Endereco;
import model.PerfilDeAcesso;
import model.Usuario;
import dao.UsuarioDAO;
import dao.Usuario_has_ResponsavelDAO;
import java.sql.SQLException;
import model.Funcionario;
import model.Usuario_has_Responsavel;
import util.ValidaFormes;

/**
 *
 * @author nelson_amaral
 */
@WebServlet(name = "ControleUsuario", urlPatterns = {"/ControleUsuario"})
public class ControleUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String acao = request.getParameter("acao");
            switch (acao) {
                case "Cadastrar": {
                    cadastro(request, response);
                    break;
                }
                case "Consultar Usuarios": {
                    consultarUsuarioPorRG(request, response);
                    break;
                }
                case "Editar": {
                    editar(request, response);
                    break;
                }
                case "Comfirma": {
                    comfirma(request, response);
                    break;
                }
                case "Deletar": {
                    deletar(request, response);
                    break;
                }case "ConsultarUsuarioEspecifico": {
                    consultarusuarioespecifico(request, response);
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

    private void cadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
//        Funcionario funcioario =new Funcionario();
//        funcioario.setId_funcionario(Integer.parseInt(request.getParameter("id_funcionrio")));
//       
        Usuario usuario = new Usuario();
        UsuarioDAO usuariodao = new UsuarioDAO();
        Usuario usuarioResponsavel = new Usuario();
        String msg = "";
        //Verificando se responsavel  é maior de 18 anos
        String rsresponsavel = request.getParameter("txtRgResponsavel");
        usuarioResponsavel.setRg(rsresponsavel);
        usuarioResponsavel = usuariodao.buscaUsuarioRG(usuarioResponsavel);
        usuario.setResponsavel(usuarioResponsavel.getUsuario_id());
        usuario.setRg(request.getParameter("txtRg"));
        
        if (usuarioResponsavel.getUsuario_id() != 0 && usuarioResponsavel.getIdade() < 18 || 0 != usuariodao.buscaUsuarioRG(usuario).getUsuario_id()) {

            msg = "Usuario menor de idade";

        } else {
            
            usuario.setNome(ValidaFormes.Formulario(request.getParameter("txtNome")));
            usuario.setIdade(Integer.parseInt(request.getParameter("txtIdade")));
            
            usuario.setCpf(request.getParameter("txtCpf"));
            
            usuario.setSenha(request.getParameter("txtSenha"));

            String num = request.getParameter("txtTelefone");
            num = num.replaceAll("[^0-9]*", "");
            usuario.setTelefone(Long.parseLong(num));

            num = request.getParameter("txtCelular");
            num = num.replaceAll("[^0-9]*", "");
            usuario.setCelular(Long.parseLong(num));

            usuario.setTiposague(request.getParameter("txtTiposague"));
            usuario.setPeso(Float.parseFloat(request.getParameter("txtPeso")));
            usuario.setAltura(Float.parseFloat(request.getParameter("txtAtura")));
            usuario.setNascimento(Date.valueOf(request.getParameter("txtDate")));
            usuario.setEmail(request.getParameter("txtEmail"));

            Endereco endereco = new Endereco();
            endereco.setLogradouro(request.getParameter("txtLog"));
            endereco.setNumero(Integer.parseInt(request.getParameter("txtNumero")));
            endereco.setCidade(request.getParameter("txtCidade"));
            endereco.setBairro(request.getParameter("txtBairro"));
            endereco.setComplemento(request.getParameter("txtComplemento"));
            endereco.setCep(Long.parseLong(request.getParameter("txtCep")));
            endereco.setUf(request.getParameter("txtUf"));
            EnderecoDAO enderecoDAO = new EnderecoDAO();

            //Verificando se o endereco a ser cadastro já existe
            int id_endereco = enderecoDAO.consultarCepExistente(endereco);
            
            if (id_endereco == 0) {
                endereco.setId_endereco(enderecoDAO.cadastaNovoEndereco(endereco));
            } else {
                endereco.setId_endereco(id_endereco);
            }
            
            usuario.setEndereco(endereco);

            usuario.setUsuario_id(usuariodao.cadastaNovoUsuario(usuario));

            if (usuarioResponsavel.getUsuario_id() != 0) {
                cadastraResponsavel(usuario, usuarioResponsavel);
            }
            
            msg = "cadastrado com sucesso";
        }
        request.setAttribute("msg", msg);

        consultarPacientes(request, response);
    }

    private void consultarPacientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.sendRedirect("paginas_usuario/exibir_usuario.jsp");
        
//        RequestDispatcher rd = request.getRequestDispatcher("paginas_usuario/exibir_usuario.jsp");
//        rd.forward(request, response);
    }
    
      private void consultarUsuarioPorRG(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = new Usuario();
        
        ArrayList<Usuario> listaUsuario = new ArrayList<>();
        
        usuario.setRg(request.getParameter("usuario_rg"));
        usuario.setStatus(true);
        
        System.out.println("RG: "+usuario.getRg());
        
        listaUsuario = usuarioDAO.exibirUsuarios(usuario);
        
        request.setAttribute("lista", listaUsuario);
        RequestDispatcher rd = request.getRequestDispatcher("paginas_usuario/Exibir_UsuarioPorRgAJAX.jsp");
        rd.forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Usuario usuarioModelo = new Usuario();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        usuarioModelo.setUsuario_id(Integer.parseInt(request.getParameter("id")));
        usuarioModelo = usuarioDAO.buscaUsuarioUnico(usuarioModelo);

        request.setAttribute("user", usuarioModelo);
        RequestDispatcher rd = request.getRequestDispatcher("paginas_usuario/atualizar_usuario.jsp");
        rd.forward(request, response);
    }

    private void comfirma(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = new Usuario();

        usuario.setUsuario_id(Integer.parseInt(request.getParameter("txtId")));
        usuario.setNome(request.getParameter("txtNome"));
        usuario.setIdade(Integer.parseInt(request.getParameter("txtIdade")));
        usuario.setCpf(request.getParameter("txtCpf"));
        usuario.setRg(request.getParameter("txtRg"));
        usuario.setSenha(request.getParameter("txtSenha"));
        String num = request.getParameter("txtTelefone");

        num = num.replaceAll("[^0-9]*", "");

        usuario.setTelefone(Long.parseLong(num));

        num = request.getParameter("txtCelular");

        num = num.replaceAll("[^0-9]*", "");

        usuario.setCelular(Long.parseLong(num));

        usuario.setTiposague(request.getParameter("txtTiposague"));
        usuario.setPeso(Float.parseFloat(request.getParameter("txtPeso")));
        usuario.setAltura(Float.parseFloat(request.getParameter("txtAtura")));
        usuario.setNascimento(Date.valueOf(request.getParameter("txtDate")));

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.atualizar_usuario(usuario);

        consultarPacientes(request, response);
    }

    private void deletar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = new Usuario();

        usuario.setUsuario_id(Integer.parseInt(request.getParameter("id")));

        usuarioDAO.excluirUsuario(usuario);
        consultarPacientes(request, response);

    }

    private void cadastraResponsavel(Usuario usuario, Usuario usuariResponsavel) {

        Usuario_has_Responsavel usuario_has_Responsavel = new Usuario_has_Responsavel();
        Usuario_has_ResponsavelDAO usuario_has_ResponsavelDAO = new Usuario_has_ResponsavelDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        usuario_has_Responsavel.setId_dependente(usuario.getUsuario_id());

        usuario_has_Responsavel.setId_responsavel(usuarioDAO.buscaUsuarioRG(usuariResponsavel).getUsuario_id());

        usuario_has_ResponsavelDAO.cadastroUsuario_has_responsavel(usuario_has_Responsavel);
    }
    
     private void consultarusuarioespecifico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
         UsuarioDAO usuarioDAO = new UsuarioDAO();
         
         Usuario usuario = new Usuario();
         Funcionario funcionario = new Funcionario();
         
        ArrayList<Usuario> listaUsuario = new ArrayList<>();
        
        String rsresponsavel = request.getParameter("usuario_rg");
        usuario.setRg(rsresponsavel);
        usuario.setStatus(true);
        
        listaUsuario = usuarioDAO.exibirUsuarioEspecifico(usuario, funcionario);
        
        request.setAttribute("listaUsuarioEspecifico", listaUsuario);
        RequestDispatcher rd = request.getRequestDispatcher("paginas_adm/busca_usuario_Especifico_cadastrarFuncAJAX.jsp");
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
