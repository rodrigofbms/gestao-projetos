package dao;

import database.conexaoBD;
import model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {



    public void criar(Usuario usuario){

        String sql = "INSERT INTO usuarios (nome, cpf, email, cargo, login, senha, perfil) VALUES (?,?,?,?,?,?,?)";
        conexaoBD ConexaoBD = new conexaoBD();

        try (Connection connection = ConexaoBD.conectar();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getCpf());
            statement.setString(3, usuario.getEmail());
            statement.setString(4, usuario.getCargo());
            statement.setString(5, usuario.getLogin());
            statement.setString(6, usuario.getSenha());
            statement.setString(7, usuario.getPerfil().name());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                usuario.setId(rs.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<Usuario> listar(){

        List<Usuario> usuarios = new ArrayList<>();

        String sql = "Select * FROM usuarios";
        conexaoBD ConexaoBD = new conexaoBD();

        try (Connection connection = ConexaoBD.conectar();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)){

            while (rs.next()) {

                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setCargo(rs.getString("cargo"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));

                usuarios.add(usuario);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;

    }


    public void atualizar(Usuario usuario) {

        String sql = "UPDATE usuarios SET nome=?, email=?, cargo=? WHERE id=?";
        conexaoBD ConexaoBD = new conexaoBD();

        try (Connection connection = ConexaoBD.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getCargo());
            statement.setInt(4, usuario.getId());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deletar(int id) {

        String sql = "DELETE FROM usuarios WHERE id=?";
        conexaoBD ConexaoBD = new conexaoBD();

        try (Connection conection = ConexaoBD.conectar();
             PreparedStatement statement = conection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int existeUsuario(int id) {

        String sql = "SELECT * FROM usuarios WHERE id = ?";
        conexaoBD ConexaoBD = new conexaoBD();

        try (Connection connection = ConexaoBD.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()){
                if(rs.next()){
                    return rs.getInt("id");
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public Usuario getUsuarioPorId(int id) {

        String sql = "SELECT * FROM usuarios WHERE id = ?";
        conexaoBD ConexaoBD = new conexaoBD();

        try (Connection connection = ConexaoBD.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()){
                if(rs.next()){
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setCpf(rs.getString("cpf"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setCargo(rs.getString("cargo"));
                    usuario.setLogin(rs.getString("login"));
                    usuario.setSenha(rs.getString("senha"));
                    return usuario;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
