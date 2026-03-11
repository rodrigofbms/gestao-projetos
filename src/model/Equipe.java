package model;

import java.util.ArrayList;
import java.util.List;

public class Equipe {

    private int id;
    private String nome;
    private String descricao;
    private List<Usuario> membros;

    public Equipe(String nomeEquipe, String descricao) {
        this.nome = nomeEquipe;
        this.descricao = descricao;
        this.membros = new ArrayList<>();
    }

    public Equipe() {

    }

    public void adicionarMembro(Usuario usuario) {
        membros.add(usuario);
    }

    public void removerMembro(Usuario usuario) {
        membros.remove(usuario);
    }

    public void listarMembros() {
        System.out.println("Membros da equipe " + nome + ":");

        for (Usuario u : membros) {
            System.out.println("- " + u.getNome());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nomeEquipe) {
        this.nome = nomeEquipe;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Usuario> getMembros() {
        return membros;
    }

    public void setMembros(List<Usuario> membros) {
        this.membros = membros;
    }


}
