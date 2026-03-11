package dao;

import database.conexaoBD;
import model.Equipe;
import model.Projeto;
import model.StatusProjeto;
import model.Usuario;
import dao.UsuarioDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {


    public void criar(Projeto projeto){

        String sql = "INSERT INTO projetos (nome, descricao, data_inicio, data_termino, status, gerente_id) VALUES (?,?,?,?,?,?)";
        conexaoBD ConexaoBD = new conexaoBD();

        try(Connection connection = ConexaoBD.conectar();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            statement.setString(1, projeto.getNome());
            statement.setString(2, projeto.getDescricao());
            statement.setDate(3, Date.valueOf(projeto.getDataInicio()));
            statement.setDate(4, Date.valueOf(projeto.getDataTermino()));
            statement.setString(5, projeto.getStatus().name());
            statement.setInt(6, projeto.getGerente().getId());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                projeto.setId(rs.getInt(1));
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }


    public List<Projeto> listar(){

        List<Projeto> projetos = new ArrayList<>();

        String sql = "SELECT * FROM projetos";

        conexaoBD ConexaoBD = new conexaoBD();
        try(Connection connection = ConexaoBD.conectar();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql)){

            while(rs.next()){

                Projeto projeto = new Projeto();

                projeto.setId(rs.getInt("id"));
                projeto.setNome(rs.getString("nome"));
                projeto.setDescricao(rs.getString("descricao"));
                projeto.setDataInicio(rs.getDate("data_inicio").toLocalDate());
                projeto.setDataTermino(rs.getDate("data_termino").toLocalDate());
                projeto.setStatus(StatusProjeto.valueOf(rs.getString("status")));

                // Criando o objeto gerente
                Usuario gerente = new Usuario();
                gerente.setId(rs.getInt("gerente_id"));
                projeto.setGerente(gerente);
                projetos.add(projeto);

            }

        } catch(Exception e){
            e.printStackTrace();
        }

        return projetos;
    }


    public void atualizar(Projeto projeto){

        String sql = "UPDATE projetos SET nome=?, descricao=?, status=? WHERE id=?";

        conexaoBD ConexaoBD = new conexaoBD();
        try(Connection connection = ConexaoBD.conectar();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1,projeto.getNome());
            statement.setString(2,projeto.getDescricao());
            statement.setString(3,projeto.getStatus().name());
            statement.setInt(4,projeto.getId());

            statement.executeUpdate();

        } catch(Exception e){
            e.printStackTrace();
        }

    }


    public void deletar(int id){

        String sql = "ON DELETE CASCADE FROM projetos WHERE id=?";

        conexaoBD ConexaoBD = new conexaoBD();
        try(Connection connection = ConexaoBD.conectar();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1,id);

            statement.executeUpdate();

        } catch(Exception e){
            e.printStackTrace();
        }

    }


    public void adicionarEquipe(int projetoId, int equipeId){

        String sql = "INSERT INTO projeto_equipes(projeto_id,equipe_id) VALUES (?,?)";

        conexaoBD ConexaoBD = new conexaoBD();
        try(Connection connection = ConexaoBD.conectar();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1,projetoId);
            statement.setInt(2,equipeId);

            statement.executeUpdate();

        } catch(Exception e){
            e.printStackTrace();
        }

    }

    public void removerEquipe(int projetoId, int equipeId){

        String sql = "DELETE FROM projeto_equipes WHERE projeto_id=? AND equipe_id=?";

        conexaoBD ConexaoBD = new conexaoBD();
        try(Connection connection = ConexaoBD.conectar();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1,projetoId);
            statement.setInt(2,equipeId);

            statement.executeUpdate();

        } catch(Exception e){
            e.printStackTrace();
        }

    }


    public int existeProjeto(int id) {

        String sql = "SELECT * FROM projetos WHERE id = ?";
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

    public Projeto getProjetoPorId(int id) {

        String sql = "SELECT * FROM projetos WHERE id = ?";
        conexaoBD ConexaoBD = new conexaoBD();

        try (Connection connection = ConexaoBD.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()){
                if(rs.next()){
                    Projeto projeto = new Projeto();
                    Usuario gerente = new Usuario();
                    projeto.setId(rs.getInt("id"));
                    projeto.setNome(rs.getString("nome"));
                    projeto.setDescricao(rs.getString("descricao"));
                    projeto.setDataInicio(rs.getDate("data_inicio").toLocalDate());
                    projeto.setDataTermino(rs.getDate("data_termino").toLocalDate());
                    gerente.setId(rs.getInt("gerente_id"));
                    projeto.setGerente(gerente);
                    return projeto;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Equipe> listarEquipesProjeto(int projetoId){

        List<Equipe> equipes = new ArrayList<>();

        String sql = """
            SELECT e.id, e.nome, e.descricao
            FROM equipes e
            JOIN projeto_equipes pe ON e.id = pe.equipe_id
            WHERE pe.projeto_id = ?
            """;

        conexaoBD ConexaoBD = new conexaoBD();

        try(Connection connection = ConexaoBD.conectar();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, projetoId);

            ResultSet rs = statement.executeQuery();

            while(rs.next()){

                Equipe equipe = new Equipe();

                equipe.setId(rs.getInt("id"));
                equipe.setNome(rs.getString("nome"));
                equipe.setDescricao(rs.getString("descricao"));

                equipes.add(equipe);

            }

        } catch(Exception e){
            e.printStackTrace();
        }

        return equipes;

    }

}
