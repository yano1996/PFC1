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
import model.Restricao;
import model.TipoVacina;
import model.Usuario_has_restricao;
import model.Vacina_has_restricao;
import util.ConectaBanco;

/**
 *
 * @author nelson_amaral
 */
public class Vacina_has_restricaoDAO {

    private final String CADASTRO_ID_VACINA_HAS_RESTRICAO = "INSERT INTO vacina_has_restricao(fk_vacina, fk_restricoes, status) VALUES (?, ?, true)";
    private final String EXCLUIR_ID_VACINA_HAS_RESTRICAO = "UPDATE vacina_has_restricao SET status = false WHERE fk_vacina = ? AND fk_restricoes = ?";
    private final String BUSCA_RESTRICOES = "SELECT * FROM vacina_has_restricao WHERE fk_vacina = ? AND status != false";
    private final String CONSULTAR_VACINA_COM_RESTRICOES_IGUAL_USUARIO = "SELECT vhr.fk_vacina FROM vacina_has_restricao vhr,usuario_has_restricao uhr WHERE uhr.fk_restricao=vhr.fk_restricoes AND vhr.fk_vacina=? AND uhr.fk_usuario=? GROUP BY vhr.fk_vacina";

    public void cadastroVacina_Has_Restricao(Vacina_has_restricao v_h_r) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(CADASTRO_ID_VACINA_HAS_RESTRICAO);
            pstmt.setInt(1, v_h_r.getVacina_id());
            pstmt.setInt(2, v_h_r.getRestricao_id());

            pstmt.execute();
        } catch (SQLException erro) {
            System.out.println("Erro SQL(Vacina_has_restricaoDAO)" + erro);
            throw new RuntimeException(erro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException erroSQL) {
                    System.out.println("Erro SQL(Vacina_has_restricaoDAO)" + erroSQL);
                    throw new RuntimeException(erroSQL);
                }
            }
        }
    }

    public void excluirVacina_Has_Restricao(Vacina_has_restricao v_h_r) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(EXCLUIR_ID_VACINA_HAS_RESTRICAO);
            pstmt.setInt(1, v_h_r.getVacina_id());
            pstmt.setInt(2, v_h_r.getRestricao_id());
            pstmt.execute();
        } catch (SQLException erro) {
            System.out.println("Erro SQL(Vacina_has_restricaoDAO)" + erro);
            throw new RuntimeException(erro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException erroSQL) {
                    System.out.println("Erro SQL(Vacina_has_restricaoDAO)" + erroSQL);
                    throw new RuntimeException(erroSQL);
                }
            }
        }
    }

    public ArrayList<Vacina_has_restricao> exibirVacinaHasRestricao(int id) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(BUSCA_RESTRICOES);
            ArrayList<Vacina_has_restricao> listaRestricao = new ArrayList<>();
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Vacina_has_restricao v_h_r = new Vacina_has_restricao();
                v_h_r.setRestricao_id(rs.getByte("fk_restricoes"));
                v_h_r.setVacina_id(rs.getInt("fk_vacina"));
                listaRestricao.add(v_h_r);
            }
            return listaRestricao;
        } catch (SQLException erro) {
            System.out.println("Erro SQL(Vacina_has_restricaoDAO)" + erro);
            throw new RuntimeException(erro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException erroSQL) {
                    System.out.println("Erro SQL(Vacina_has_restricaoDAO)" + erroSQL);
                    throw new RuntimeException(erroSQL);
                }
            }
        }

    }

    public int ConsultarVacinaComrestricoesIguaisAoUsuario(Vacina_has_restricao v_h_rz, Usuario_has_restricao u_h_r) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int id = 0;
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(CONSULTAR_VACINA_COM_RESTRICOES_IGUAL_USUARIO);
            pstmt.setInt(1, v_h_rz.getVacina_id());
            pstmt.setInt(2, u_h_r.getUsuario_id());
            rs = pstmt.executeQuery();
            
            if(rs.next()){
            id = rs.getInt("fk_vacina");
            }
            
            return id;
            
        } catch (SQLException erro) {
            System.out.println("Erro SQL(Vacina_has_restricaoDAO)" + erro);
            throw new RuntimeException(erro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException erroSQL) {
                    System.out.println("Erro SQL(Vacina_has_restricaoDAO)" + erroSQL);
                    throw new RuntimeException(erroSQL);
                }
            }
        }

    }
}
