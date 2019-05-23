/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EnderecoDAO;
import dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Endereco;
import model.PerfilDeAcesso;
import model.Usuario;
import util.ValidaFormes;

/**
 *
 * @author nelson_amaral
 */
@WebServlet(name = "ControleUsuarioNaoLogado", urlPatterns = {"/ControleUsuarioNaoLogado"})
public class ControleUsuarioNaoLogado extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String acao = request.getParameter("acao");
            switch (acao) {
                case "Cadastrar": {
                    cadastro(request, response);
                    break;
                }

            }
        } catch (Exception erro) {
            System.out.println("Erro (ControleUsuario)" + erro);
            RequestDispatcher rd = request.getRequestDispatcher("paginas_usuario/erro.jsp");
            rd.forward(request, response);
        }
    }

    private void cadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
        System.out.println("Cadastro");
        Usuario usuario = new Usuario();
        usuario.setNome(ValidaFormes.Formulario(request.getParameter("txtNome")));
        usuario.setIdade(Integer.parseInt(request.getParameter("txtIdade")));
        usuario.setCpf(request.getParameter("txtCpf"));
        usuario.setRg(request.getParameter("txtRg"));
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
        usuario.setSenha(request.getParameter("txtSenha"));
        usuario.setEmail(request.getParameter("txtEmail"));

        Endereco endereco = new Endereco();
        endereco.setLogradouro(request.getParameter("txtLog"));
        endereco.setNumero(Integer.parseInt(request.getParameter("txtNumero")));
        endereco.setCidade(request.getParameter("txtCidade"));
        endereco.setBairro(request.getParameter("txtBairro"));
        endereco.setComplemento(request.getParameter("txtComplemento"));
        endereco.setCep(Integer.parseInt(request.getParameter("txtCep")));
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
        UsuarioDAO usuariodao = new UsuarioDAO();
        usuariodao.cadastaNovoUsuario(usuario);
        request.setAttribute("msg", "Cadastrado com Sucesso");

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
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
