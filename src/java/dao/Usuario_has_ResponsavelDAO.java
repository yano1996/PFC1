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
import model.Usuario_has_Responsavel;
import model.Usuario_has_restricao;
import util.ConectaBanco;

/**
 *
 * @author Victor_Aguiar
 */
public class Usuario_has_ResponsavelDAO {
     private final String CADASTRO_ID_USUARIO_HAS_RESPONSAVEL = "INSERT INTO usuario_responsavel(usuario_responsavel, usuario_dependente)VALUES (?, ?);";
    private final String BUSCA_USUARIO_HAS_RESPONSAVEL = "SELECT * FROM usuario_responsavel WHERE usuario_responsavel=?";
    
    public void cadastroUsuario_has_responsavel(Usuario_has_Responsavel u_h_r) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(CADASTRO_ID_USUARIO_HAS_RESPONSAVEL);
            pstmt.setInt(1, u_h_r.getId_responsavel());
            pstmt.setInt(2, u_h_r.getId_dependente());

            pstmt.execute();
        } catch (SQLException erro) {
            System.out.println("Erro SQL(Usuario_has_ResponsavelDAO)" + erro);
            throw new RuntimeException(erro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException erroSQL) {
                    System.out.println("Erro SQL(Usuario_has_ResponsavelDAO)" + erroSQL);
                    throw new RuntimeException(erroSQL);
                }
            }
        }
    }
    
    public ArrayList<Usuario_has_Responsavel> exibirUsuario_has_responsavel(Usuario_has_Responsavel u_h_r) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(BUSCA_USUARIO_HAS_RESPONSAVEL);
            ArrayList<Usuario_has_Responsavel> listaRestricao = new ArrayList<>();
            pstmt.setInt(1, u_h_r.getId_responsavel());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Usuario_has_Responsavel u_h_r2 = new Usuario_has_Responsavel();
                u_h_r2.setId_dependente(rs.getByte("usuario_dependente"));
                u_h_r2.setId_responsavel(rs.getInt("usuario_responsavel"));
                listaRestricao.add(u_h_r2);
            }
            return listaRestricao;
        } catch (SQLException erro) {
            System.out.println("Erro SQL(Usuario_has_ResponsavelDAO)" + erro);
            throw new RuntimeException(erro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException erroSQL) {
                    System.out.println("Erro SQL(Usuario_has_ResponsavelDAO)" + erroSQL);
                    throw new RuntimeException(erroSQL);
                }
            }
        }

    }
}