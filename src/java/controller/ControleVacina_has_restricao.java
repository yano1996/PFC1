/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.RestricaoDAO;
import dao.VacinaDAO;
import dao.Vacina_has_restricaoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Restricao;
import model.Vacina;
import model.Vacina_has_restricao;

/**
 *
 * @author nelson_amaral
 */
@WebServlet(name = "ControleVacina_has_restricao", urlPatterns = {"/ControleVacina_has_restricao"})
public class ControleVacina_has_restricao extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String acao = request.getParameter("acao");
            switch (acao) {
                case "Cadastrar":
                    cadastrar(request, response);
                    break;
                case "Listar":
                    listar(request, response);
                    break;
                case "Deletar":
                    delete(request, response);
                    break;
                case "Atualiza_restricao":

                    break;
                case "CadastrarVacinas":

                    break;
                default:
                    break;
            }

        } catch (Exception erro) {
            System.out.println("Erro (ControleVacina_has_restricao)" + erro);
            RequestDispatcher rd = request.getRequestDispatcher("paginas_usuario/erro.jsp");
            rd.forward(request, response);
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Vacina_has_restricao v_h_r = new Vacina_has_restricao();

        v_h_r.setVacina_id(Integer.parseInt(request.getParameter("id")));

        lista2(request, response, v_h_r);

    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Vacina_has_restricao v_h_r = new Vacina_has_restricao();

        v_h_r.setRestricao_id(Integer.parseInt(request.getParameter("id_restricao")));
        v_h_r.setVacina_id(Integer.parseInt(request.getParameter("id_vacina")));

        Vacina_has_restricaoDAO v_h_rDAO = new Vacina_has_restricaoDAO();
        v_h_rDAO.excluirVacina_Has_Restricao(v_h_r);

        lista2(request, response, v_h_r);
    }

    private void lista2(HttpServletRequest request, HttpServletResponse response, Vacina_has_restricao v_h_r) throws ServletException, IOException {

        RestricaoDAO restricaoDAO = new RestricaoDAO();
        Vacina_has_restricaoDAO v_h_rDAO = new Vacina_has_restricaoDAO();

        ArrayList<Restricao> listaRestricaoVacina = new ArrayList<>();
        ArrayList<Restricao> listaRestricao = new ArrayList<>();
        ArrayList<Vacina_has_restricao> listaV_H_R = new ArrayList<>();

        Vacina vacina = new Vacina();
        vacina.setId_vacina(v_h_r.getVacina_id());
        //Pega a relação do id da vacina com o ids dasrestriçoes
        listaV_H_R = v_h_rDAO.exibirVacinaHasRestricao(vacina.getId_vacina());

        //Busca toda as restriçoes relacionadas aquela vacina
        for (Vacina_has_restricao l : listaV_H_R) {
            listaRestricaoVacina.add(restricaoDAO.consultarUnicaRestricao(l.getRestricao_id()));
        }
        vacina.setRestricoes(listaRestricaoVacina);

        //todas as restricoes
        listaRestricao = restricaoDAO.consultarRestricao();

        request.setAttribute("listaRestricoes", listaRestricao);
        request.setAttribute("vacina", vacina);
        RequestDispatcher rdz = request.getRequestDispatcher("/paginas_vacina/vacina_altera_restricoes.jsp");
        rdz.forward(request, response);
    }

    public void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Vacina_has_restricao v_h_rz = new Vacina_has_restricao();

        v_h_rz.setVacina_id(Integer.parseInt(request.getParameter("id_vacina")));

        Vacina_has_restricaoDAO v_h_rDAO = new Vacina_has_restricaoDAO();
        String[] restricaofk = request.getParameterValues("txtRestricaoFK");
        if (restricaofk != null) {
            for (String r : restricaofk) {
                Vacina_has_restricao v_h_r = new Vacina_has_restricao();
                v_h_r.setRestricao_id(Integer.parseInt(r));
                v_h_r.setVacina_id(v_h_rz.getVacina_id());
                v_h_rDAO.cadastroVacina_Has_Restricao(v_h_r);

            }
        }
        lista2(request, response, v_h_rz);
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
