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
import model.Funcionario;
import model.PerfilDeAcesso;
import model.Usuario;
import util.ConectaBanco;

/**
 *
 * @author nelson_amaral
 */
public class FuncionarioDAO {
    private final String CADASTRO_FUNCIONARIO = "INSERT INTO funcionario(funcionario_confen, funcionario_senha, funcionario_acesso, funcionario_status, fk_usuario) VALUES (?, crypt(?, gen_salt('bf', 8)), ?, ?, ?);";
    private final String BLOQUEAR_OU_DESBLOQUEAR_FUNCIONARIO = "UPDATE funcionario SET funcionario_status=? WHERE funcionario_id=?";
    private final String BUSCA_FUNCIONARIO = "SELECT * FROM funcionario WHERE funcionario_status=true";
    private final String ATUALIZA_FUNCIONARIO = "UPDATE funcionario SET funcionario_confen=?,funcionario_senha=?,funcionario_acesso=?,funcionario_status=? WHERE funcionario_id=? ;";
    private final String AUTENTICA_FUNCIONARIO = "SELECT * FROM funcionario WHERE funcionario_confen=? AND funcionario_senha=crypt(?, funcionario_senha)";
    private final String BUSCA_FUNCIONARIO_UNICO = "SELECT * FROM funcionario WHERE funcionario_status=? AND funcionario_id=?";
    
    public void cadastrarFuncionario(Funcionario funcionario){
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(CADASTRO_FUNCIONARIO);
            pstmt.setString(1, funcionario.getCofen());
            pstmt.setString(2, funcionario.getSenha());
            pstmt.setString(3, funcionario.getPerfil().toString());
            pstmt.setBoolean(4,funcionario.isStatus());
            pstmt.setInt(5, funcionario.getUsuario_id());
            pstmt.execute();
        } catch (SQLException erro) {
            System.out.println("Erro SQL(FuncionarioDAO)" + erro);
            throw new RuntimeException(erro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException erroSQL) {
                    System.out.println("Erro SQL(FuncionarioDAO)" + erroSQL);
                    throw new RuntimeException(erroSQL);
                }
            }
        }
    }
    
    public void excluirFuncionario (Funcionario funcionario){
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(BLOQUEAR_OU_DESBLOQUEAR_FUNCIONARIO);
            pstmt.setBoolean(1, funcionario.isStatus());
            pstmt.setInt(2, funcionario.getId_funcionario());
            pstmt.execute();
        } catch (SQLException erro) {
            System.out.println("Erro SQL(FuncionarioDAO)" + erro);
            throw new RuntimeException(erro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException erroSQL) {
                    System.out.println("Erro SQL(FuncionarioDAO)" + erroSQL);
                    throw new RuntimeException(erroSQL);
                }
            }
        }
    }
    
    public ArrayList<Funcionario> exibeFuncionarios (){
        Connection conexao = null;
        PreparedStatement pstmt = null;
         ResultSet rsFunc = null;
         ArrayList<Funcionario> listaFuncionario = new ArrayList<>();
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(BUSCA_FUNCIONARIO);
            
            rsFunc = pstmt.executeQuery();
            while(rsFunc.next()){
                Funcionario funcionario = new Funcionario();
                funcionario.setId_funcionario(rsFunc.getInt("funcionario_id"));
                funcionario.setCofen(rsFunc.getString("funcionario_confen"));
                funcionario.setPerfil(PerfilDeAcesso.valueOf(rsFunc.getString("funcionario_acesso")));
                funcionario.setStatus(rsFunc.getBoolean("funcionario_status"));
                funcionario.setUsuario_id(rsFunc.getInt("fk_usuario"));
                
                listaFuncionario.add(funcionario);
            }
            return listaFuncionario;
            
        } catch (SQLException erro) {
            System.out.println("Erro SQL(FuncionarioDAO)" + erro);
            throw new RuntimeException(erro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException erroSQL) {
                    System.out.println("Erro SQL(FuncionarioDAO)" + erroSQL);
                    throw new RuntimeException(erroSQL);
                }
            }
        }
    }
    public void atualizaFuncionario (Funcionario funcionario){
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(ATUALIZA_FUNCIONARIO);
            pstmt.setString(1, funcionario.getCofen());
            pstmt.setString(2, funcionario.getSenha());
            pstmt.setString(3, funcionario.getPerfil().toString());
            pstmt.setBoolean(4, funcionario.isStatus());
            pstmt.setInt(5, funcionario.getId_funcionario());
            pstmt.execute();
        } catch (SQLException erro) {
            System.out.println("Erro SQL(FuncionarioDAO)" + erro);
            throw new RuntimeException(erro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException erroSQL) {
                    System.out.println("Erro SQL(FuncionarioDAO)" + erroSQL);
                    throw new RuntimeException(erroSQL);
                }
            }
        }
    }
    
    public Funcionario autenticaFuncionario(Funcionario funcionario) {
        Funcionario funcionarioAutenticado = null;
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rsfuncionario = null;
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(AUTENTICA_FUNCIONARIO);
            pstmt.setString(1, funcionario.getCofen());
            pstmt.setString(2, funcionario.getSenha());
            rsfuncionario = pstmt.executeQuery();
           while (rsfuncionario.next()) {
                funcionarioAutenticado = new Funcionario();
                funcionarioAutenticado.setId_funcionario(rsfuncionario.getInt("funcionario_id"));
                funcionarioAutenticado.setSenha(rsfuncionario.getString("funcionario_senha"));
                funcionarioAutenticado.setPerfil(PerfilDeAcesso.valueOf(rsfuncionario.getString("funcionario_acesso")));
                funcionarioAutenticado.setUsuario_id(rsfuncionario.getInt("fk_usuario"));
               }
        } catch (SQLException errosql) {
            System.out.println("Erro SQL(FuncionarioDAO)" + errosql);
            throw new RuntimeException(errosql);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException errosql) {
                    System.out.println("Erro SQL(FuncionarioDAO)" + errosql);
                    throw new RuntimeException(errosql);
                }
            }
        }
        return funcionarioAutenticado;
    }
    
     public Funcionario exibeFuncionariosUnico (Funcionario funcionario){
        Connection conexao = null;
        PreparedStatement pstmt = null;
         ResultSet rsFunc = null;
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(BUSCA_FUNCIONARIO_UNICO);
            pstmt.setBoolean(1, funcionario.isStatus());
            pstmt.setInt(2, funcionario.getId_funcionario());
            rsFunc = pstmt.executeQuery();
            while(rsFunc.next()){
                funcionario.setId_funcionario(rsFunc.getInt("funcionario_id"));
                funcionario.setCofen(rsFunc.getString("funcionario_confen"));
                funcionario.setPerfil(PerfilDeAcesso.valueOf(rsFunc.getString("funcionario_acesso")));
                funcionario.setStatus(rsFunc.getBoolean("funcionario_status"));
                funcionario.setUsuario_id(rsFunc.getInt("fk_usuario"));
                
                
            }
            return funcionario;
            
        } catch (SQLException erro) {
            System.out.println("Erro SQL(FuncionarioDAO)" + erro);
            throw new RuntimeException(erro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException erroSQL) {
                    System.out.println("Erro SQL(FuncionarioDAO)" + erroSQL);
                    throw new RuntimeException(erroSQL);
                }
            }
        }
    }
}
