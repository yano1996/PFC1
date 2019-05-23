/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.RestricaoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Vacina;
import dao.VacinaDAO;
import dao.Vacina_has_restricaoDAO;
import model.Restricao;
import model.TipoVacina;
import model.Vacina_has_restricao;

/**
 *
 * @author nelson_amaral
 */
@WebServlet(name = "ControleVacina", urlPatterns = {"/ControleVacina"})
public class ControleVacina extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String acao = request.getParameter("acao");
            switch (acao) {
                case "Cadastrar Vacinas": {
                    cadastro(request, response);
                    break;
                }
                case "Consultar Vacinas": {
                    consultarVacinas(request, response);
                    break;
                }
                case "Deletar": {
                    deletar(request, response);
                    break;
                }
                case "Editar Vacina": {
                    editarVacina(request, response);                  
                    break;
                }
                case "Confirma": {
                    confirma(request, response);                    
                    break;
                }
                default:
                    break;
            }
        } catch (Exception erro) {
            System.out.println("Erro (ControleVacina)" + erro);
            RequestDispatcher rd = request.getRequestDispatcher("paginas_usuario/erro.jsp");
            rd.forward(request, response);
        }
    }
        private void cadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
           //Instanciando Modelo
            Vacina vacina = new Vacina();

            //Recebendo Valores Do Formulario cadastrar_vacina.jsp
            vacina.setNome(request.getParameter("txtNome"));
            String tipo = (request.getParameter("txtTipo"));
            
                if(tipo.equals("Vacinas_vivas_atenuadas")){
                vacina.setTipo(TipoVacina.Vacinas_vivas_atenuadas);
            }else if(tipo.equals("Vacinas_mortas_inactivadas")){
                vacina.setTipo(TipoVacina.Vacinas_mortas_inactivadas);
            }else if(tipo.equals("Vacinas_sub_unitarias")){
                vacina.setTipo(TipoVacina.Vacinas_sub_unitarias);
            }
            
            vacina.setStatus(true);
            
            
            //Instanciando DAO
            VacinaDAO vacinaDAO = new VacinaDAO();

            //Chamando o method "cadastaNovoVacina" 
            vacina.setId_vacina(vacinaDAO.cadastaNovoVacina(vacina));
            
            Vacina_has_restricaoDAO v_h_rDAO = new Vacina_has_restricaoDAO();
            String[] restricaofk = request.getParameterValues("txtRestricaoFK");
            if (restricaofk != null) {
                for (String r : restricaofk) {
                    Vacina_has_restricao v_h_r = new Vacina_has_restricao();
                    v_h_r.setRestricao_id(Integer.parseInt(r));
                    v_h_r.setVacina_id(vacina.getId_vacina());
                    v_h_rDAO.cadastroVacina_Has_Restricao(v_h_r);

                }
            }


            request.setAttribute("msg", "Cadastrado com Sucesso");
            consultarVacinas(request, response);
        }

        private void consultarVacinas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            VacinaDAO vacinadao = new VacinaDAO();
            ArrayList<Vacina> listaVacina = new ArrayList<>();
            listaVacina = vacinadao.consultarVacinas();
            request.setAttribute("listavacina", listaVacina);
            RequestDispatcher rd = request.getRequestDispatcher("/paginas_vacina/consultar_vacina.jsp");
            rd.forward(request, response);
        }
    
        private void deletar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            Vacina vacina = new Vacina();
            vacina.setId_vacina(Integer.parseInt(request.getParameter("id_vacina")));
            VacinaDAO vacinadao = new VacinaDAO();
            vacinadao.excluirVacina(vacina.getId_vacina());
            consultarVacinas(request, response);
        }
        
        private void editarVacina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            Vacina VancinaModelo = new Vacina();
            VacinaDAO vacinadao = new VacinaDAO();
            VancinaModelo = vacinadao.buscarVacinaUnica(Integer.parseInt(request.getParameter("id_vacina")));
            request.setAttribute("vacina", VancinaModelo);
            RequestDispatcher rd = request.getRequestDispatcher("/paginas_vacina/atualizar_vacina.jsp");
            rd.forward(request, response);
        }
        
        private void confirma(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            Vacina vacina = new Vacina();

            vacina.setNome(request.getParameter("txtNome"));
            vacina.setTipo(TipoVacina.valueOf(request.getParameter("txtTipo")));
            vacina.setId_vacina(Integer.parseInt(request.getParameter("txtId")));

            VacinaDAO vacinaDAO = new VacinaDAO();
            vacinaDAO.atualizar_vacina(vacina);

            consultarVacinas(request, response);
        }
        private void atualizarRestricoes(){
            
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
