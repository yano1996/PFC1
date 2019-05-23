/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.IntervaloVacinacaoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CalendarioObrigatorio;
import model.IntervaloVacinacao;

/**
 *
 * @author Victor_Aguiar
 */
@WebServlet(name = "ControleIntervaloDose", urlPatterns = {"/ControleIntervaloDose"})
public class ControleIntervaloDose extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
            try {
            String acao = request.getParameter("acao");

            switch (acao) {
                case "listarintervalos": {
                    listarintervalos_unicosCalendarioOB(request, response);
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

    public void listarintervalos_unicosCalendarioOB(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        
        IntervaloVacinacao intervalovacinacao = new IntervaloVacinacao();
        IntervaloVacinacaoDAO intervalovacinacaodao = new IntervaloVacinacaoDAO();
        CalendarioObrigatorio calendarioob = new CalendarioObrigatorio();
        
        calendarioob.setId_calendarioObr(Integer.parseInt(request.getParameter("calendarioob_id")));
        
        intervalovacinacao.setCalendarioObr(calendarioob);
        intervalovacinacao.setAtivoOuNao(true);
        
        ArrayList<IntervaloVacinacao> listaintervalos = new ArrayList<>();
        
        listaintervalos = intervalovacinacaodao.buscaUnicaIntervaloVacinacao(intervalovacinacao);
        
        request.setAttribute("listaintervalos", listaintervalos);
        RequestDispatcher rd = request.getRequestDispatcher("paginas_calendarioobr/tabela_listar_intervalos_calendarioobAJAX.jsp");
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
