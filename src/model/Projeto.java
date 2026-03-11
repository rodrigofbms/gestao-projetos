package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Projeto {

    private int id;
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private StatusProjeto status;
    private Usuario gerente;
    private List<Equipe> equipes;


    public Projeto(String nomeProjeto, String descricao,
                   LocalDate dataInicio, LocalDate dataTerminoPrevista,
                   Usuario gerente) {

        this.nome = nomeProjeto;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTerminoPrevista;
        this.gerente = gerente;
        this.status = StatusProjeto.PLANEJADO;
        this.equipes = new ArrayList<>();
    }

    public Projeto() {

    }


    public void adicionarEquipe(Equipe equipe) {
        equipes.add(equipe);
    }

    public void alterarStatus(StatusProjeto status) {
        this.status = status;
    }

    public void exibirDetalhes() {

        System.out.println("Projeto: " + nome);
        System.out.println("Descrição: " + descricao);
        System.out.println("Data de início: " + dataInicio);
        System.out.println("Data de termino: " + dataTermino);
        System.out.println("Status: " + status);
        System.out.println("Gerente: " + gerente.getNome());

        System.out.println("Equipes envolvidas:");
        for (Equipe e : equipes) {
            System.out.println("- " + e.getNome());
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

    public void setNome(String nomeProjeto) {
        this.nome = nomeProjeto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTerminoPrevista) {
        this.dataTermino = dataTerminoPrevista;
    }

    public StatusProjeto getStatus() {
        return status;
    }

    public void setStatus(StatusProjeto status) {
        this.status = status;
    }

    public Usuario getGerente() {
        return gerente;
    }

    public void setGerente(Usuario gerente) {
        this.gerente = gerente;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<Equipe> equipes) {
        this.equipes = equipes;
    }
}
