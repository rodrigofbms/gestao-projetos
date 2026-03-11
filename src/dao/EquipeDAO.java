package dao;

import database.conexaoBD;
import model.Equipe;
import model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipeDAO {


    public void criar(Equipe equipe) {

        String sql = "INSERT INTO equipes (nome, descricao) VALUES (?,?)";
        conexaoBD ConexaoBD = new conexaoBD();

        try (Connection connection = ConexaoBD.conectar();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, equipe.getNome());
            statement.setString(2, equipe.getDescricao());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                equipe.setId(rs.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Equipe> listar() {

        List<Equipe> equipes = new ArrayList<>();
        String sql = "SELECT * FROM equipes";
        conexaoBD ConexaoBD = new conexaoBD();

        try (Connection conn = ConexaoBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Equipe equipe = new Equipe();
                equipe.setId(rs.getInt("id"));
                equipe.setNome(rs.getString("nome"));
                equipe.setDescricao(rs.getString("descricao"));

                equipes.add(equipe);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return equipes;
    }


    public void atualizar(Equipe equipe) {

        String sql = "UPDATE equipes SET nome=?, descricao=? WHERE id=?";

        conexaoBD ConexaoBD = new conexaoBD();
        try (Connection connection = ConexaoBD.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, equipe.getNome());
            statement.setString(2, equipe.getDescricao());
            statement.setInt(3, equipe.getId());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void deletar(int id) {

        String sql = "DELETE FROM equipes WHERE id=?";

        conexaoBD ConexaoBD = new conexaoBD();
        try (Connection connection = ConexaoBD.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void adicionarMembro(int equipeId, int usuarioId) {

        String sql = "INSERT INTO equipe_membros(equipe_id,usuario_id) VALUES (?,?)";

        conexaoBD ConexaoBD = new conexaoBD();
        try (Connection connection = ConexaoBD.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, equipeId);
            statement.setInt(2, usuarioId);

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void removerMembro(int equipeId, int usuarioId) {

        String sql = "DELETE FROM equipe_membros WHERE equipe_id=? AND usuario_id=?";

        conexaoBD ConexaoBD = new conexaoBD();
        try (Connection connection = ConexaoBD.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, equipeId);
            statement.setInt(2, usuarioId);

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int existeEquipe(int id) {

        String sql = "SELECT * FROM equipes WHERE id = ?";
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


    public Equipe getEquipePorId(int id) {

        String sql = "SELECT * FROM equipes WHERE id = ?";
        conexaoBD ConexaoBD = new conexaoBD();

        try (Connection connection = ConexaoBD.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()){
                if(rs.next()){
                    Equipe equipe = new Equipe();
                    equipe.setId(rs.getInt("id"));
                    equipe.setNome(rs.getString("nome"));
                    equipe.setDescricao(rs.getString("descricao"));
                    return equipe;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Usuario> listarMembrosEquipe(int equipeId){

        List<Usuario> membros = new ArrayList<>();

        String sql = """
            SELECT u.id, u.nome, u.email
            FROM usuarios u
            JOIN equipe_membros em ON u.id = em.usuario_id
            WHERE em.equipe_id = ?
            """;

        conexaoBD ConexaoBD = new conexaoBD();

        try(Connection connection = ConexaoBD.conectar();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, equipeId);

            ResultSet rs = statement.executeQuery();

            while(rs.next()){

                Usuario usuario = new Usuario();

                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));

                membros.add(usuario);

            }

        } catch(Exception e){
            e.printStackTrace();
        }

        return membros;

    }

}