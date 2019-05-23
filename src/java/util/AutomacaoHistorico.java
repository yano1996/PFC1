/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dao.CalendarioObrigatorioDAO;
import dao.RestricaoDAO;
import dao.CardenetaUsuarioDAO;
import dao.Usuario_has_restricaoDAO;
import java.util.ArrayList;
import model.CalendarioObrigatorio;
import model.Restricao;
import model.StatusVacinaCalendario;
import model.Usuario;
import model.CardenetaUsuario;
import model.Usuario_has_restricao;

/**
 *
 * @author nelson.amaral
 */
//public class AutomacaoHistorico {
//
//    public void criarUsuarioHistorico(Usuario usuario, int id_funcionario) {
//        TratamentoDeData tratamentoDeData = new TratamentoDeData();
//        CalendarioObrigatorioDAO calendarioObrigatorioDAO = new CalendarioObrigatorioDAO();
//        ArrayList<CalendarioObrigatorio> listaCalObr = new ArrayList<>();
//        CardenetaUsuarioDAO usuarioHistoricoDAO = new CardenetaUsuarioDAO();
//
//        int tempoVivido = tratamentoDeData.diferencaEntreData(usuario.getNascimento());
//
//        listaCalObr = calendarioObrigatorioDAO.buscaVacinaCalendarioObr();
//
//        for (CalendarioObrigatorio cal : listaCalObr) {
//            if (cal.getTempoParaInjetar() < tempoVivido) {
//                CardenetaUsuario usuarioHistorico = new CardenetaUsuario();
//
//                usuarioHistorico.setStatus(StatusVacinaCalendario.MEDICAMENTO_NAO_INJETADO);
//                usuarioHistorico.setCalendarioObrigatorio(cal);
//                usuarioHistorico.setUsuario(usuario);
//                usuarioHistorico.getFuncionario().setId_funcionario(id_funcionario);
//
//                usuarioHistoricoDAO.criarHistoricoUsuario(usuarioHistorico);
//            }
//        }
//
//    }
//
//    public void exibirUsuarioHistorico(Usuario usuario) {
//        CardenetaUsuarioDAO usuarioHistoricoDAO = new CardenetaUsuarioDAO();
//        Usuario_has_restricaoDAO u_h_rDAO = new Usuario_has_restricaoDAO();
//        RestricaoDAO restricaoDAO = new RestricaoDAO();
//        CalendarioObrigatorioDAO calendarioObrigatorioDAO = new CalendarioObrigatorioDAO();
//        
//        
//        ArrayList<CalendarioObrigatorio> listaCalendarioObrSemRestricao= new ArrayList<>();
//        ArrayList<CalendarioObrigatorio> listaCalendarioObrDoUsuarioQFalta= new ArrayList<>();
//        ArrayList<CalendarioObrigatorio> listaCalendarioObr = new ArrayList<>();
//        ArrayList<CardenetaUsuario> listaUsuarioHistorico = new ArrayList<>();
//        ArrayList<Usuario_has_restricao> listaU_H_R = new ArrayList<>();
//        ArrayList<Restricao> listaRestricao = new ArrayList<>();
//        ArrayList<Restricao> listaRestricaoDoUsuario = new ArrayList<>();
//
//        //Busca o historico do usuario na base de dados
//        listaUsuarioHistorico = usuarioHistoricoDAO.buscaHistoricoUsuarioUnico(usuario.getUsuario_id());
//
//        //Buscando os N x N das restricoes do usuario na base de dados
//        listaU_H_R = u_h_rDAO.exibirUsuario_has_restricao(usuario.getUsuario_id());
//
//        //busca todas as restrições dentro do sistema
//        listaRestricao = restricaoDAO.consultarRestricao();
//        
//        //busca as vacinas existentes no calendario Obrigatorio
//        listaCalendarioObr = calendarioObrigatorioDAO.buscaVacinaCalendarioObr();
//
//        for (int cont = 0; cont < listaRestricao.size(); cont++) {
//
//            //comparar se o id da restricao de vacina no calendario obr é igual ao da lista de restricao 
//            if (listaU_H_R.get(cont).getRestricao_id() == listaRestricao.get(cont).getRestricao_id()) {
//                listaRestricaoDoUsuario.add(listaRestricao.get(cont));
//            }
//
//        }
//
//        for (int cont = 0; cont < listaCalendarioObr.size(); cont++) {
//            
//            //busca os item do calendario do usuario que não foram cadastrados
//            if(listaUsuarioHistorico.get(cont).getCalendarioObrigatorio().getId_calendarioObr() != listaCalendarioObr.get(cont).getId_calendarioObr()){
//                listaCalendarioObrDoUsuarioQFalta.add(listaCalendarioObr.get(cont));
//            }
//        }
//        
//        
//        //Varre as vacinas do calendário obrigatorio que ainda falta
//        for (int cont = 0; cont < listaCalendarioObrDoUsuarioQFalta.size(); cont++) {
//            boolean tem_retricao = false;
//            ArrayList<Restricao> listaRestricaoCalendarioObr = new ArrayList<>();
//            listaRestricaoCalendarioObr = listaCalendarioObrDoUsuarioQFalta.get(cont).getVacina().getRestricoes();
//            
//            //varre as restriçoes da vacina referenciada no calendário obrigatorio
//            for (int contVacina = 0; contVacina < listaRestricaoCalendarioObr.size(); contVacina++) {
//                
//                
//                //Verifica se algumas das restrições da vacina afeta o usuário
//                for (int i = 0; i < listaRestricaoDoUsuario.size(); i++) {
//                    if (listaRestricaoDoUsuario.get(i).getRestricao_id() == listaRestricaoCalendarioObr.get(contVacina).getRestricao_id()) {
//                        tem_retricao = true;
//                        break;
//                    }
//                }
//                if (tem_retricao == true) {
//                    break;
//                }
//            }
//            if (tem_retricao == false) {
//                listaCalendarioObrSemRestricao.add(listaCalendarioObrDoUsuarioQFalta.get(cont));
//            }
//        }
//    }
//}
