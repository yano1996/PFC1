/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.CardenetaUsuario;
import model.StatusVacinaCalendario;
import util.ConectaBanco;

/**
 *
 * @author nelson_amaral
 */
public class CardenetaUsuarioDAO {

    private final String CADASTRAR_HISTORICO_USUARIO = "INSERT INTO cardeneta_usuario(caderneta_data,caderneta_hora,fk_usuario,fk_calendarioob,fk_campanha,fk_funcionario,fk_vacina,fk_posto) VALUES (?, ?, ?, ?, 01, ?, ?, 01);";
    private final String EXIBIR_HISTORICO_USUARIO = "SELECT * FROM cardeneta_usuario;";
    private final String BUSCA_HISTORICO_USUARIO = "SELECT * FROM cardeneta_usuario WHERE fk_usuario=?;";

    
    public void criarHistoricoUsuario(CardenetaUsuario cardenetaUsuario){
        Connection conexao = null;
        PreparedStatement pstmt = null;
        
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(CADASTRAR_HISTORICO_USUARIO);
            
            pstmt.setDate(1, new java.sql.Date(cardenetaUsuario.getDateCadastro().getTime()));
            pstmt.setDate(2, new java.sql.Date(cardenetaUsuario.getHoraCadastro().getTime()));
            
            pstmt.setInt(3, cardenetaUsuario.getUsuario().getUsuario_id());
            pstmt.setInt(4, cardenetaUsuario.getCalendarioObrigatorio().getId_calendarioObr());
          //pstmt.setInt(5, cardenetaUsuario.getCampanha().getId_campanha());
            pstmt.setInt(5, cardenetaUsuario.getFuncionario().getId_funcionario());
            pstmt.setInt(6, cardenetaUsuario.getVacina().getId_vacina());
          //pstmt.setInt(9, usuarioHistorico.getPost().getId_posto);
            pstmt.execute();
        } catch (SQLException erro) {
            System.out.println("Erro SQL(UsuarioHistoricoDAO)" + erro);
            throw new RuntimeException(erro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException erroSQL) {
                    System.out.println("Erro SQL(UsuarioHistoricoDAO)" + erroSQL);
                    throw new RuntimeException(erroSQL);
                }
            }
        }
    }
    
    public ArrayList<CardenetaUsuario> exibirHistoricoUsuario(){
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<CardenetaUsuario> listaCardenetaUsuario = new ArrayList<>();
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(EXIBIR_HISTORICO_USUARIO);
            
            rs = pstmt.executeQuery();
            while (rs.next()) {
                CardenetaUsuario cardenetaUsuario = new CardenetaUsuario();
                
                cardenetaUsuario.setDateCadastro(rs.getDate("caderneta_data"));
                cardenetaUsuario.setHoraCadastro(rs.getDate("aderneta_hora"));
                
                cardenetaUsuario.getUsuario().setUsuario_id(rs.getInt("usuarioHistorico_FK_usuario"));
                cardenetaUsuario.getCalendarioObrigatorio().setId_calendarioObr(rs.getInt("usuarioHistorico_FK_calendarioObr"));
            //  cardenetaUsuario.getCampanha().setId_campanha(rs.getInt("fk_campanha"));
                cardenetaUsuario.getFuncionario().setId_funcionario(rs.getInt("usuarioHistorico_FK_Funcionario"));
                cardenetaUsuario.getVacina().setId_vacina(rs.getInt("fk_vacina"));
            //  cardenetaUsuario.getPosto().setId_posto(rs.getInt("fk_posto"));
            
                listaCardenetaUsuario.add(cardenetaUsuario);
            }
            return listaCardenetaUsuario;
        } catch (SQLException erro) {
            System.out.println("Erro SQL(UsuarioHistoricoDAO)" + erro);
            throw new RuntimeException(erro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException erroSQL) {
                    System.out.println("Erro SQL(UsuarioHistoricoDAO)" + erroSQL);
                    throw new RuntimeException(erroSQL);
                }
            }
        }
    }
    
    public ArrayList<CardenetaUsuario> buscaHistoricoUsuarioUnico(int id){
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<CardenetaUsuario> listaCardenetaUsuario = new ArrayList<>();
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(BUSCA_HISTORICO_USUARIO);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                CardenetaUsuario cardenetaUsuario = new CardenetaUsuario();
                cardenetaUsuario.setDateCadastro(rs.getDate("caderneta_data"));
                cardenetaUsuario.setHoraCadastro(rs.getDate("aderneta_hora"));
                
                cardenetaUsuario.getUsuario().setUsuario_id(rs.getInt("usuarioHistorico_FK_usuario"));
                cardenetaUsuario.getCalendarioObrigatorio().setId_calendarioObr(rs.getInt("usuarioHistorico_FK_calendarioObr"));
            //  cardenetaUsuario.getCampanha().setId_campanha(rs.getInt("fk_campanha"));
                cardenetaUsuario.getFuncionario().setId_funcionario(rs.getInt("usuarioHistorico_FK_Funcionario"));
                cardenetaUsuario.getVacina().setId_vacina(rs.getInt("fk_vacina"));
            //  cardenetaUsuario.getPosto().setId_posto(rs.getInt("fk_posto"));
            
                listaCardenetaUsuario.add(cardenetaUsuario);
            }
            return listaCardenetaUsuario;
        } catch (SQLException erro) {
            System.out.println("Erro SQL(UsuarioHistoricoDAO)" + erro);
            throw new RuntimeException(erro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException erroSQL) {
                    System.out.println("Erro SQL(UsuarioHistoricoDAO)" + erroSQL);
                    throw new RuntimeException(erroSQL);
                }
            }
        }
    }
    
    
}
