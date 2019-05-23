/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editsor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Endereco;
import model.Funcionario;
import model.PerfilDeAcesso;
import model.Usuario;
import util.ConectaBanco;

/**
 *
 * @author nelson_amaral
 */
public class UsuarioDAO {

    private final String CADASTRA_NOVO_USUARIO = "INSERT INTO usuario(usuario_nome, usuario_idade, usuario_cpf, usuario_rg, usuario_senha, usuario_telefone, usuario_celular, usuario_tiposangue, usuario_peso, usuario_altura, usuario_nascimento,usuario_responsavel, usuario_status,usuario_email, fk_endereco)  VALUES (?,?, ?, ?,crypt(?, gen_salt('bf', 8)), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING usuario_id";
    private final String AUTENTICA_USUARIO = "SELECT * FROM usuario WHERE usuario_rg = ? AND usuario_senha = crypt(?, usuario_senha)";
    private final String DELETA_USUARIO = "UPDATE usuario SET usuario_status = false WHERE usuario_id = ? RETURNING usuario_status";
    private final String UPDATE_USUARIO = "UPDATE usuario SET usuario_nome=?, usuario_idade=?, usuario_cpf=?, usuario_rg=?, usuario_senha=?, usuario_telefone=?, usuario_celular=?, usuario_tiposangue=?, usuario_peso=?, usuario_altura=?, usuario_nascimento=?, usuario_email=? WHERE usuario_id=?;";
    private final String BUSCAR_USUARIO_UNICO = "SELECT * FROM usuario WHERE usuario_id=?";
    private final String BUSCAR_USUARIO_RG = "SELECT * FROM usuario WHERE usuario_rg=?";
    private final String BUSCAR_USUARIO = "SELECT * FROM usuario WHERE usuario_rg=? AND usuario_status=?";
    private final String BUSCAR_USUARIO_ESPECIFICO = "SELECT u.usuario_id,u.usuario_nome,u.usuario_cpf,u.usuario_rg,u.usuario_senha,u.usuario_telefone,u.usuario_celular,u.usuario_nascimento FROM usuario u WHERE usuario_rg=? AND usuario_status=?";
    
    
    //Valida senha com uma quere 
    
    public int cadastaNovoUsuario(Usuario usuario) throws SQLException {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(CADASTRA_NOVO_USUARIO);
            pstmt.setString(1, usuario.getNome());
            pstmt.setInt(2, usuario.getIdade());
            pstmt.setString(3, usuario.getCpf());
            pstmt.setString(4, usuario.getRg());
            pstmt.setString(5, usuario.getSenha());
            pstmt.setLong(6, usuario.getTelefone());
            pstmt.setLong(7, usuario.getCelular());
            pstmt.setString(8, usuario.getTiposague());
            pstmt.setFloat(9, usuario.getPeso());
            pstmt.setFloat(10, usuario.getAltura());
            pstmt.setDate(11, usuario.getNascimento());
            pstmt.setInt(12,usuario.getResponsavel());
            pstmt.setBoolean(13, true);
            pstmt.setString(14, usuario.getEmail());
            pstmt.setInt(15, usuario.getEndereco().getId_endereco());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                usuario.setUsuario_id(rs.getInt("usuario_id"));
            }
            return usuario.getUsuario_id();
        } catch (SQLException erro) {
            System.out.println("Erro SQL(UsuarioDAO)" + erro);
            throw new RuntimeException(erro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException erroSQL) {
                    System.out.println("Erro SQL(UsuarioDAO)" + erroSQL);
                    throw new RuntimeException(erroSQL);
                }
            }
        }
    }

    public Usuario autenticaUsuario(Usuario usuario) {
        Usuario usuarioAutenticado = null;
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rsUsuario = null;
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(AUTENTICA_USUARIO);
            pstmt.setString(1, usuario.getRg());
            pstmt.setString(2, usuario.getSenha());
            rsUsuario = pstmt.executeQuery();
           while (rsUsuario.next()) {
                usuarioAutenticado = new Usuario();
                usuarioAutenticado.setUsuario_id(rsUsuario.getInt("usuario_id"));
                usuarioAutenticado.setNome(rsUsuario.getString("usuario_nome"));
                usuarioAutenticado.setSenha(rsUsuario.getString("usuario_senha"));
               }
        } catch (SQLException errosql) {
            System.out.println("Erro SQL(UsuarioDAO)" + errosql);
            throw new RuntimeException(errosql);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException errosql) {
                    System.out.println("Erro SQL(UsuarioDAO)" + errosql);
                    throw new RuntimeException(errosql);
                }
            }
        }
        return usuarioAutenticado;
    }

    public ArrayList<Usuario> exibirUsuarios(Usuario usuarioModelo) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(BUSCAR_USUARIO);
            pstmt.setString(1, usuarioModelo.getRg());
            pstmt.setBoolean(2, usuarioModelo.isStatus());
            ArrayList<Usuario> listaUsuario = new ArrayList<>();
            rs = pstmt.executeQuery();
           
            while (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setUsuario_id(rs.getInt("usuario_id"));
                    usuario.setNome(rs.getString("usuario_nome"));
                    usuario.setIdade(rs.getInt("usuario_idade"));
                    usuario.setCpf(rs.getString("usuario_cpf"));
                    usuario.setRg(rs.getString("usuario_rg"));
                    usuario.setTelefone(rs.getLong("usuario_telefone"));
                    usuario.setCelular(rs.getLong("usuario_celular"));
                    usuario.setTiposague(rs.getString("usuario_tiposangue"));
                    usuario.setPeso(rs.getInt("usuario_peso"));
                    usuario.setAltura(rs.getInt("usuario_altura"));
                    usuario.setNascimento(rs.getDate("usuario_nascimento"));
                    usuario.setStatus(rs.getBoolean("usuario_status"));
                    usuario.setEmail(rs.getString("usuario_email"));
                    usuario.setResponsavel(rs.getInt("usuario_responsavel"));
                    listaUsuario.add(usuario);
            }
            return listaUsuario;
        } catch (SQLException erro) {
            System.out.println("Erro SQL(UsuarioDAO)" + erro);
            throw new RuntimeException(erro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException erroSQL) {
                    System.out.println("Erro SQL(UsuarioDAO)" + erroSQL);
                    throw new RuntimeException(erroSQL);
                }
            }
        }

    }
    
   
    public boolean excluirUsuario(Usuario usuario) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean status = false;
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(DELETA_USUARIO);
            pstmt.setInt(1, usuario.getUsuario_id());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                status = rs.getBoolean("usuario_status");
            }

            return status;
        } catch (SQLException erro) {
            System.out.println("Erro SQL(UsuarioDAO)" + erro);
            throw new RuntimeException(erro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException erroSQL) {
                    System.out.println("Erro SQL(UsuarioDAO)" + erroSQL);
                    throw new RuntimeException(erroSQL);
                }
            }
        }
    }

    public void atualizar_usuario(Usuario usuario) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(UPDATE_USUARIO);
            pstmt.setString(1, usuario.getNome());
            pstmt.setInt(2, usuario.getIdade());
            pstmt.setString(3, usuario.getCpf());
            pstmt.setString(4, usuario.getRg());
            pstmt.setString(5, usuario.getSenha());
            pstmt.setLong(6, usuario.getTelefone());
            pstmt.setLong(7, usuario.getCelular());
            pstmt.setString(8, usuario.getTiposague());
            pstmt.setFloat(9, usuario.getPeso());
            pstmt.setFloat(10, usuario.getAltura());
            pstmt.setDate(11, usuario.getNascimento());
            pstmt.setString(12, usuario.getEmail());
            pstmt.setInt(13, usuario.getUsuario_id());
            pstmt.execute();
            
            

        } catch (SQLException erro) {
            System.out.println("Erro SQL(UsuarioDAO)" + erro);
            throw new RuntimeException(erro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException erroSQL) {
                    System.out.println("Erro SQL(UsuarioDAO)" + erroSQL);
                    throw new RuntimeException(erroSQL);
                }
            }
        }
    }

    public Usuario buscaUsuarioUnico(Usuario usuarioModelo) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Endereco endereco =new Endereco();
        Usuario usuario = new Usuario();
        usuario.setEndereco(endereco);
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(BUSCAR_USUARIO_UNICO);
            pstmt.setInt(1, usuarioModelo.getUsuario_id());
            rs = pstmt.executeQuery();
            while (rs.next()) {        
                    usuario.setUsuario_id(rs.getInt("usuario_id"));
                    usuario.setNome(rs.getString("usuario_nome"));
                    usuario.setIdade(rs.getInt("usuario_idade"));
                    usuario.setCpf(rs.getString("usuario_cpf"));
                    usuario.setRg(rs.getString("usuario_rg"));
                    usuario.setSenha(rs.getString("usuario_senha"));
                    usuario.setTelefone(rs.getInt("usuario_telefone"));
                    usuario.setCelular(rs.getLong("usuario_celular"));
                    usuario.setTiposague(rs.getString("usuario_tiposangue"));
                    usuario.setPeso(rs.getInt("usuario_peso"));
                    usuario.setAltura(rs.getInt("usuario_altura"));
                    usuario.setNascimento(rs.getDate("usuario_nascimento"));
                    usuario.setStatus(rs.getBoolean("usuario_status"));
                    usuario.setEmail(rs.getString("usuario_email"));
                    usuario.setResponsavel(rs.getInt("usuario_responsavel"));
                    usuario.getEndereco().setId_endereco(rs.getInt("fk_endereco"));
                    
            }
            return usuario;

        } catch (SQLException erro) {
            System.out.println("Erro SQL(UsuarioDAO)" + erro);
            throw new RuntimeException(erro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException erroSQL) {
                    System.out.println("Erro SQL(UsuarioDAO)" + erroSQL);
                    throw new RuntimeException(erroSQL);
                }
            }
        }
    }
    
    public Usuario buscaUsuarioRG(Usuario usuarioModelo) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Usuario usuario = new Usuario();
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(BUSCAR_USUARIO_RG);
            pstmt.setString(1, usuarioModelo.getRg());
            rs = pstmt.executeQuery();
            while (rs.next()) {        
                    usuario.setUsuario_id(rs.getInt("usuario_id"));
                    usuario.setIdade(rs.getInt("usuario_idade"));     
                    usuario.setRg(rs.getString("usuario_rg"));   
            }
            return usuario;

        } catch (SQLException erro) {
            System.out.println("Erro SQL(UsuarioDAO)" + erro);
            throw new RuntimeException(erro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException erroSQL) {
                    System.out.println("Erro SQL(UsuarioDAO)" + erroSQL);
                    throw new RuntimeException(erroSQL);
                }
            }
        }
    }
    
        public ArrayList<Usuario> exibirUsuarioEspecifico(Usuario usuarioModelo, Funcionario funcionarioModelo) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(BUSCAR_USUARIO_ESPECIFICO);
            
            ArrayList<Usuario> listaUsuario = new ArrayList<>();
            pstmt.setString(1, usuarioModelo.getRg());
            pstmt.setBoolean(2, usuarioModelo.isStatus());
            rs = pstmt.executeQuery();
           
            while (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setUsuario_id(rs.getInt("usuario_id"));
                    usuario.setNome(rs.getString("usuario_nome"));
                    usuario.setCpf(rs.getString("usuario_cpf"));
                    usuario.setRg(rs.getString("usuario_rg"));
                    listaUsuario.add(usuario);
        
            }
            return listaUsuario;
        } catch (SQLException erro) {
            System.out.println("Erro SQL(UsuarioDAO)" + erro);
            throw new RuntimeException(erro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException erroSQL) {
                    System.out.println("Erro SQL(UsuarioDAO)" + erroSQL);
                    throw new RuntimeException(erroSQL);
                }
            }
        }

    }
}
