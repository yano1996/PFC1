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
import model.Endereco;
import model.Restricao;
import util.ConectaBanco;

/**
 *
 * @author Victor Aguiar
 */
public class RestricaoDAO {

    private final String RESTRICAO_CADASTRAR = "INSERT INTO restricao (restricao_nome,restricao_tipo,restricao_status) values (?,?,?) RETURNING restricao_id";
    private final String CONSULTAR_RESTRICAO = "SELECT * FROM restricao WHERE restricao_status !=false";
    private final String CONSULTAR_RESTRICAO_UNICA = "SELECT * FROM restricao WHERE restricao_status=true AND restricao_id = ?";
    private final String DELETAR_RESTRICAO = "UPDATE restricao SET restricao_status = false WHERE restricao_id=? RETURNING restricao_status";
    private final String ATUALIZAR_RESTRICAO = "UPDATE restricao SET restricao_nome=?, restricao_tipo=? WHERE restricao_id=? ";

    public int cadastaNovoRestricao(Restricao restricao) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(RESTRICAO_CADASTRAR);
            pstmt.setString(1, restricao.getRestricao_nome());
            pstmt.setString(2, restricao.getRestricao_tipo());
            pstmt.setBoolean(3, restricao.isRestricao_status());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                restricao.setRestricao_id(rs.getInt("restricao_id"));
            }
            return restricao.getRestricao_id();
        } catch (SQLException erro) {
            System.out.println("Erro SQL(RestricaoDAO)" + erro);
            throw new RuntimeException(erro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException erroSQL) {
                    System.out.println("Erro SQL(RestricaoDAO)" + erroSQL);
                    throw new RuntimeException(erroSQL);
                }
            }
        }

    }

    public Restricao consultarUnicaRestricao(int id) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Restricao restricao = new Restricao();
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(CONSULTAR_RESTRICAO_UNICA);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                if (rs != null) {
                    restricao.setRestricao_id(rs.getInt("restricao_id"));
                    restricao.setRestricao_nome(rs.getString("restricao_nome"));
                    restricao.setRestricao_tipo(rs.getString("restricao_tipo"));
                    restricao.setRestricao_status(rs.getBoolean("restricao_status"));
                }
            }
            return restricao;
        } catch (SQLException erro) {
            System.out.println("Erro SQL(RestricaoDAO)" + erro);
            throw new RuntimeException(erro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException erroSQL) {
                    System.out.println("Erro SQL(RestricaoDAO)" + erroSQL);
                    throw new RuntimeException(erroSQL);
                }
            }
        }

    }

    public ArrayList<Restricao> consultarRestricao() {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(CONSULTAR_RESTRICAO);
            ArrayList<Restricao> listarestricao = new ArrayList<>();
            rs = pstmt.executeQuery();

            while (rs.next()) {
                if (rs != null) {
                    Restricao restricao = new Restricao();
                    restricao.setRestricao_id(rs.getInt("restricao_id"));
                    restricao.setRestricao_nome(rs.getString("restricao_nome"));
                    restricao.setRestricao_tipo(rs.getString("restricao_tipo"));
                    restricao.setRestricao_status(rs.getBoolean("restricao_status"));

                    listarestricao.add(restricao);
                }
            }
            return listarestricao;
        } catch (SQLException erro) {
            System.out.println("Erro SQL(RestricaoDAO)" + erro);
            throw new RuntimeException(erro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException erroSQL) {
                    System.out.println("Erro SQL(RestricaoDAO)" + erroSQL);
                    throw new RuntimeException(erroSQL);
                }
            }
        }
    }

    public boolean deletarRestricao(Restricao restricao) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean status = false;
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(DELETAR_RESTRICAO);

            pstmt.setInt(1, restricao.getRestricao_id());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                status = rs.getBoolean("restricao_status");
            }

            return status;
        } catch (SQLException erro) {
            System.out.println("Erro SQL(RestricaoDAO)" + erro);
            throw new RuntimeException(erro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException erroSQL) {
                    System.out.println("Erro SQL(RestricaoDAO)" + erroSQL);
                    throw new RuntimeException(erroSQL);
                }
            }
        }
    }

    public void atualizaRestricao(Restricao restricao) {
        Connection conexao = null;
        PreparedStatement pstmt = null;

        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(ATUALIZAR_RESTRICAO);

            pstmt.setString(1, restricao.getRestricao_nome());
            pstmt.setString(2, restricao.getRestricao_tipo());
            pstmt.setInt(3, restricao.getRestricao_id());
            pstmt.execute();

        } catch (SQLException erro) {
            System.out.println("Erro SQL(RestricaoDAO)" + erro);
            throw new RuntimeException(erro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException erroSQL) {
                    System.out.println("Erro SQL(RestricaoDAO)" + erroSQL);
                    throw new RuntimeException(erroSQL);
                }
            }
        }
    }

}
