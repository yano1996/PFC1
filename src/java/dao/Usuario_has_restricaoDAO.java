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
import model.Usuario_has_restricao;
import util.ConectaBanco;

/**
 *
 * @author nelson_amaral
 */
public class Usuario_has_restricaoDAO {

    private final String CADASTRO_ID_USUARIO_HAS_RESTRICAO = "INSERT INTO usuario_has_restricao(fk_usuario, fk_restricao)VALUES (?, ?);";
    private final String BUSCA_RESTRICOES = "SELECT * FROM usuario_has_restricao WHERE fk_usuario=?";

    public void cadastroUsuario_has_restricaoDAO(Usuario_has_restricao u_h_r) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(CADASTRO_ID_USUARIO_HAS_RESTRICAO);
            pstmt.setInt(1, u_h_r.getUsuario_id());
            pstmt.setInt(2, u_h_r.getRestricao_id());

            pstmt.execute();
        } catch (SQLException erro) {
            System.out.println("Erro SQL(Usuario_has_restricaoDAO)" + erro);
            throw new RuntimeException(erro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException erroSQL) {
                    System.out.println("Erro SQL(Usuario_has_restricaoDAO)" + erroSQL);
                    throw new RuntimeException(erroSQL);
                }
            }
        }
    }

    public ArrayList<Usuario_has_restricao> exibirUsuario_has_restricao(int id) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(BUSCA_RESTRICOES);
            pstmt.setInt(1, id);
            ArrayList<Usuario_has_restricao> listaRestricao = new ArrayList<>();
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Usuario_has_restricao u_h_r = new Usuario_has_restricao();
                u_h_r.setRestricao_id(rs.getByte("fk_restricao"));
                u_h_r.setUsuario_id(rs.getInt("fk_usuario"));
                listaRestricao.add(u_h_r);
            }
            return listaRestricao;
        } catch (SQLException erro) {
            System.out.println("Erro SQL(Usuario_has_restricaoDAO)" + erro);
            throw new RuntimeException(erro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException erroSQL) {
                    System.out.println("Erro SQL(Usuario_has_restricaoDAO)" + erroSQL);
                    throw new RuntimeException(erroSQL);
                }
            }
        }

    }
}
