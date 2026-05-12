import dao.EquipeDAO;
import dao.ProjetoDAO;
import dao.UsuarioDAO;
import model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static Scanner scanner = new Scanner(System.in);
    static UsuarioDAO usuarioDAO = new UsuarioDAO();
    static EquipeDAO equipeDAO = new EquipeDAO();
    static ProjetoDAO projetoDAO = new ProjetoDAO();

    public static void main(String[] args) {


        int opcao;

        do {

            System.out.println("\n===== SISTEMA DE GESTÃO DE PROJETOS =====");
            System.out.println("1 - Gerenciar Usuários");
            System.out.println("2 - Gerenciar Equipes");
            System.out.println("3 - Gerenciar Projetos");
            System.out.println("0 - Sair");

            opcao = scanner.nextInt();


                if (opcao == 1 || opcao == 2 || opcao == 3 || opcao == 0) {
                    switch (opcao) {

                        case 1:
                            menuUsuarios();
                            break;

                        case 2:
                            menuEquipes();
                            break;

                        case 3:
                            menuProjetos();
                            break;

                    }
                } else {
                    System.out.println("Entrada Inválida - Insira uma opção válida");
                }


        } while (opcao != 0);

        System.out.println("Sistema encerrado.");

    }


    private static void menuUsuarios() {

        int opcao;

        do {

            System.out.println("\n--- MENU USUÁRIOS ---");
            System.out.println("1 - Criar usuário");
            System.out.println("2 - Listar usuários");
            System.out.println("3 - Atualizar usuário");
            System.out.println("4 - Deletar usuário");
            System.out.println("0 - Voltar");

            opcao = scanner.nextInt();
            scanner.nextLine();

            if(opcao == 1 || opcao == 2 || opcao == 3 || opcao == 4 || opcao == 0) {
                switch (opcao) {

                    case 1:
                        criarUsuario();
                        break;

                    case 2:
                        listarUsuarios();
                        break;

                    case 3:
                        atualizarUsuario();
                        break;

                    case 4:
                        deletarUsuario();
                        break;

                }
            }else {
                System.out.println("Número Inserido Inválido - Insira uma opção válida");
            }
        } while (opcao != 0);

        }

    public static void criarUsuario() {

        System.out.println("Nome:");
        if(scanner.hasNext("[a-zA-ZÀ-ÿ ]+")) {
            String nome = scanner.nextLine();
            System.out.println("CPF: (000.000.000-00)");

            if (scanner.hasNext("[0-9.\\-]+")) {
                String cpf = scanner.nextLine();
                System.out.println("Email:");

                if (scanner.hasNext("[a-zA-Z0-9@.]+")) {
                    String email = scanner.nextLine();
                    System.out.println("Cargo:");

                    if (scanner.hasNext("[a-zA-ZÀ-ÿ ]+")) {
                        String cargo = scanner.nextLine();

                        System.out.println("Login:");
                        String login = scanner.nextLine();

                        System.out.println("Senha:");
                        String senha = scanner.nextLine();
                        System.out.println("Perfil (ADMINISTRADOR / GERENTE / COLABORADOR):");

                        if (scanner.hasNext("[a-zA-Z]+")) {
                            PerfilUsuario perfil = PerfilUsuario.valueOf(scanner.nextLine().toUpperCase());

                            Usuario usuario = new Usuario(nome, cpf, email, cargo, login, senha, perfil);
                            usuarioDAO.criar(usuario);
                            System.out.println("Usuário criado!");

                        } else {
                            System.out.println("Perfil inserido inválido!");
                        }

                    } else {
                        System.out.println("Cargo inserido inválido!");
                    }
                }
                else {
                    System.out.println("Email inserido inválido!");
                }
            }
            else {
                System.out.println("CPF inserido inválido!");
            }
        }
        else {
            System.out.println("Nome de usuário inserido inválido!");
        }
    }

    public static void listarUsuarios() {

        List<Usuario> usuarios = usuarioDAO.listar();

        for (Usuario u : usuarios) {

            System.out.print("ID:" + u.getId());
            System.out.print(" Nome:" + u.getNome());
            System.out.println(" Email:" + u.getEmail());
            System.out.println("-----------------------");

        }

    }


    public static void atualizarUsuario() {

        System.out.println("ID do usuário:");
        int id = scanner.nextInt();
        scanner.nextLine();

        if(usuarioDAO.existeUsuario(id)!= 0) {
            System.out.println("Novo nome:");

            if(scanner.hasNext("[a-zA-ZÀ-ÿ ]+")) {
                String nome = scanner.nextLine();
                System.out.println("Novo email:");

                if(scanner.hasNext("[a-zA-Z0-9@.]+")) {
                    String email = scanner.nextLine();
                    System.out.println("Novo cargo:");

                    if(scanner.hasNext("[a-zA-ZÀ-ÿ ]+")) {
                        String cargo = scanner.nextLine();

                        Usuario u = new Usuario();
                        u.setId(id);
                        u.setNome(nome);
                        u.setEmail(email);
                        u.setCargo(cargo);

                        usuarioDAO.atualizar(u);

                        System.out.println("Usuário atualizado!");
                    }else {
                        System.out.println("Cargo inserido inválido");
                    }
                }else {
                    System.out.println("email inserido inválido");
                }
            } else {
                System.out.println("Nome inserido inválido");
            }
        }
        else {
            System.out.println("Id do usuário inválido");
        }
    }


    public static void deletarUsuario() {

        System.out.println("ID do usuário:");
        int id = scanner.nextInt();

        if(usuarioDAO.existeUsuario(id)!= 0) {
            Usuario usuario = usuarioDAO.getUsuarioPorId(id);
            System.out.println("Deseja deletar o usuário (1-Sim / 0-Não)" + " Nome:"
                    + usuario.getNome() + " CPF:" + usuario.getCpf() + " Email:" + usuario.getEmail());

            if(scanner.hasNextInt()) {
                int deletar = scanner.nextInt();
                if(deletar == 1) {

                    usuarioDAO.deletar(id);
                    System.out.println("Usuário removido!");

                } else {
                    System.out.println("Usuário não removido!");
                }
            } else {
                System.out.println("Opção inválida");
            }
        }
        else {
            System.out.println("Id do usuário inválido");
        }
    }

    // =========================
    // MENU EQUIPES
    // =========================

    private static void menuEquipes() {

        int opcao;

        do {

            System.out.println("\n--- MENU EQUIPES ---");
            System.out.println("1 - Criar equipe");
            System.out.println("2 - Listar equipes");
            System.out.println("3 - Atualizar equipe");
            System.out.println("4 - Remover equipe");
            System.out.println("5 - Adicionar membro à equipe");
            System.out.println("6 - Remover membro da equipe");
            System.out.println("7 - Listar membros da equipe");
            System.out.println("0 - Voltar");

            opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1 || opcao == 2 || opcao == 3 || opcao == 4 || opcao == 5 || opcao == 6 || opcao == 7 || opcao == 0) {
                switch (opcao) {

                    case 1:
                        criarEquipe();
                        break;

                    case 2:
                        listarEquipes();
                        break;

                    case 3:
                        atualizarEquipe();
                        break;

                    case 4:
                        deletarEquipe();
                        break;

                    case 5:
                        adicionarUsuarioEquipe();
                        break;

                    case 6:
                        removerUsuarioEquipe();
                        break;

                    case 7:
                        listarMembrosEquipe();
                        break;
                }
            }else {
                System.out.println("Entrada Inválida - Insira uma opção válida");
            }

        } while (opcao != 0);

    }

    public static void criarEquipe() {

        System.out.println("Nome da equipe:");
        if(scanner.hasNext("[a-zA-ZÀ-ÿ ]+")) {
            String nome = scanner.nextLine();
            System.out.println("Descrição:");

            if(scanner.hasNext("[a-zA-ZÀ-ÿ ]+")) {
                String descricao = scanner.nextLine();

                Equipe equipe = new Equipe();
                equipe.setNome(nome);
                equipe.setDescricao(descricao);
                equipeDAO.criar(equipe);

                System.out.println("Equipe criada!");

            } else {
                System.out.println("Nome da descrição inválido");
            }
        } else {
            System.out.println("Nome da equipe inválido");
        }
    }


    public static void listarEquipes() {

        List<Equipe> equipes = equipeDAO.listar();

        for (Equipe e : equipes) {

            System.out.print("ID:" + e.getId());
            System.out.print(" Nome:" + e.getNome());
            System.out.println(" Descrição:" + e.getDescricao());
            System.out.println("-----------------------");

        }

    }

    public static void atualizarEquipe() {

        System.out.println("ID da equipe:");

        if(scanner.hasNextInt()){
            int id = scanner.nextInt();

            if (equipeDAO.existeEquipe(id) != 0) {
                System.out.println("Novo nome:");

                if (scanner.hasNext("[a-zA-ZÀ-ÿ ]+")) {

                    String nome = scanner.nextLine();
                    System.out.println("Nova descrição:");

                    if (scanner.hasNext("[a-zA-ZÀ-ÿ ]+")) {

                        String descricao = scanner.nextLine();

                        Equipe equipe = new Equipe();
                        equipe.setId(id);
                        equipe.setNome(nome);
                        equipe.setDescricao(descricao);
                        equipeDAO.atualizar(equipe);

                        System.out.println("Equipe atualizada!");
                    } else {
                        System.out.println("Descrição da equipe inválido");
                    }

                } else {
                    System.out.println("Nome da equipe inválido");
                }
            } else {
                System.out.println("Id da equipe inválido!");
            }
        } else {
            System.out.println("Entrada Inválida - Insira um número");
        }

    }


    public static void deletarEquipe() {

        System.out.println("ID da equipe:");
        int id = scanner.nextInt();

        if(equipeDAO.existeEquipe(id)!= 0) {
        Equipe equipe = equipeDAO.getEquipePorId(id);
            System.out.println("Deseja deletar a equipe (1-Sim / 0-Não)" + " Nome:"
                    + equipe.getNome() + " Descrição:" + equipe.getDescricao());
            if(scanner.hasNextInt()) {
                int deletar = scanner.nextInt();

                if(deletar == 1) {

                    equipeDAO.deletar(id);
                    System.out.println("Equipe removida!");
                }else {
                    System.out.println("Equipe não removida!");
                }
            }else {
                System.out.println("Opção inválida!");
            }
        }
        else {
            System.out.println("Id da equipe inválido!");
        }
    }


    public static void adicionarUsuarioEquipe(){

        System.out.println("ID da equipe:")
        ;
        if(scanner.hasNextInt()) {

            int equipeId = scanner.nextInt();

            if(equipeDAO.existeEquipe(equipeId) != 0){

            System.out.println("ID do usuário:");

            if (scanner.hasNextInt()) {

                int usuarioId = scanner.nextInt();

                if(usuarioDAO.existeUsuario(usuarioId) != 0){

                equipeDAO.adicionarMembro(equipeId, usuarioId);
                System.out.println("Usuário adicionado à equipe!");

            }else {
                    System.out.println("Id do usuário inválido");
                }

            } else {
                System.out.println("Entrada Inválida - Insira um número");
            }
        }else {
                System.out.println("Id da equipe inválido");
            }
        }else {
            System.out.println("Entrada Inválida - Insira um número");
        }

    }

    public static void removerUsuarioEquipe() {

        System.out.println("ID da equipe:");

        if(scanner.hasNextInt()){

        int equipeId = scanner.nextInt();

            if (equipeDAO.existeEquipe(equipeId) != 0) {

                System.out.println("ID do usuário:");

                if(scanner.hasNextInt()) {
                    int usuarioId = scanner.nextInt();
                    if (usuarioDAO.existeUsuario(usuarioId) != 0) {

                        equipeDAO.removerMembro(equipeId, usuarioId);
                        System.out.println("Usuário removido da equipe!");

                    }else {
                        System.out.println("Id do usuário inválido");
                    }
                }else {
                    System.out.println("Entrada Inválida - Insira um número");
                }
            }else {
                System.out.println("Id da equipe inválido");
            }
    }else {
            System.out.println("Entrada Inválida - Insira um número");
        }

    }


    public static void listarMembrosEquipe(){

        System.out.println("ID da equipe:");
        if(scanner.hasNextInt()) {
            int equipeId = scanner.nextInt();
            if(equipeDAO.existeEquipe(equipeId) != 0) {

                List<Usuario> membros = equipeDAO.listarMembrosEquipe(equipeId);

                System.out.println("\n--- MEMBROS DA EQUIPE ---");

                for (Usuario u : membros) {

                    System.out.print("ID:" + u.getId());
                    System.out.print(" Nome:" + u.getNome());
                    System.out.println(" Email:" + u.getEmail());
                    System.out.println("--------------------");

                }
            }else {
                System.out.println("Id da equipe inválido");
            }

        }else {
            System.out.println("Entrada Inválida - Insira um número");
        }
    }


    // =========================
    // MENU PROJETOS
    // =========================

    private static void menuProjetos() {

        int opcao;

        do {

            System.out.println("\n--- MENU PROJETOS ---");
            System.out.println("1 - Criar projeto");
            System.out.println("2 - Listar projetos");
            System.out.println("3 - Atualizar projeto");
            System.out.println("4 - Remover projeto");
            System.out.println("5 - Adicionar equipe ao projeto");
            System.out.println("6 - Remover equipe do projeto");
            System.out.println("7 - Listar equipes do projeto");
            System.out.println("0 - Voltar");

            opcao = scanner.nextInt();
            scanner.nextLine();

            if(opcao == 1 || opcao == 2 || opcao == 3|| opcao == 4 || opcao == 5 || opcao == 6 || opcao == 7 || opcao == 0) {
                switch (opcao) {

                    case 1:
                        criarProjeto();
                        break;

                    case 2:
                        listarProjetos();
                        break;

                    case 3:
                        atualizarProjeto();
                        break;

                    case 4:
                        deletarProjeto();
                        break;

                    case 5:
                        adicionarEquipeProjeto();
                        break;

                    case 6:
                        removerEquipeProjeto();
                        break;

                    case 7:
                        listarEquipesProjeto();
                        break;

                }
            }else {
                System.out.println("Entrada Inválida - Insira uma opção válida");
            }
        } while (opcao != 0);

    }

    public static void criarProjeto() {

        System.out.println("Nome do projeto:");
        if (scanner.hasNext("[a-zA-ZÀ-ÿ ]+")) {
            String nome = scanner.nextLine();
            System.out.println("Descrição:");

            if (scanner.hasNext("[a-zA-ZÀ-ÿ ]+")) {
                String descricao = scanner.nextLine();
                System.out.println("Data início (YYYY-MM-DD):");

                DateTimeFormatter Dataformato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate Datainicio;
                String DataInicial = scanner.nextLine();

                try{
                    Datainicio = LocalDate.parse(DataInicial,Dataformato);

                } catch (DateTimeParseException e) {
                    System.out.println("Data inválida. O formato deve ser YYYY-MM-DD.");
                    return;
                }

                System.out.println("Data término (YYYY-MM-DD):");
                LocalDate Datatermino;
                String DataFinal = scanner.nextLine();

                try{
                    Datatermino = LocalDate.parse(DataFinal,Dataformato);
                } catch (DateTimeParseException e) {
                    System.out.println("Data inválida. O formato deve ser YYYY-MM-DD.");
                    return;
                }


                System.out.println("ID do gerente:");
                if(scanner.hasNextInt()){
                int gerenteId = scanner.nextInt();

                    if(usuarioDAO.existeUsuario(gerenteId)!= 0) {
                        Usuario gerente = usuarioDAO.getUsuarioPorId(gerenteId);

                        Projeto projeto = new Projeto();
                        projeto.setNome(nome);
                        projeto.setDescricao(descricao);
                        projeto.setDataInicio(Datainicio);
                        projeto.setDataTermino(Datatermino);
                        projeto.setStatus(StatusProjeto.PLANEJADO);
                        projeto.setGerente(gerente);

                        projetoDAO.criar(projeto);

                        System.out.println("Projeto criado!");
                    }
                }else {
                    System.out.println("Id do gerente Inválido!");
                }
            }else {
                System.out.println("Descrição do projeto inválido!");
            }

        }else {
            System.out.println("Nome do projeto inválido!");
        }
    }


    public static void listarProjetos() {

        List<Projeto> projetos = projetoDAO.listar();

        for (Projeto p : projetos) {

            System.out.print("ID:" + p.getId());
            System.out.print(" Nome:" + p.getNome());
            System.out.print(" Descrição:" + p.getDescricao());
            System.out.println(" Gerente ID:" + p.getGerente().getId());
            System.out.println("-----------------------");

        }

    }

    public static void atualizarProjeto() {

        System.out.println("ID do projeto:");

        if(scanner.hasNextInt()) {

            int id = scanner.nextInt();
            scanner.nextLine();

            if(projetoDAO.existeProjeto(id) != 0) {

                System.out.println("Novo nome:");

                if(scanner.hasNext("[a-zA-ZÀ-ÿ ]+")) {

                    String nome = scanner.nextLine();

                    System.out.println("Nova descrição:");

                    if(scanner.hasNext("[a-zA-ZÀ-ÿ ]+")) {

                        String descricao = scanner.nextLine();

                        if (scanner.hasNext("[a-zA-Z_]+")) {
                            System.out.println("Status (PLANEJADO/ EM_ANDAMENTO/ CONCLUIDO / CANCELADO)");
                            StatusProjeto statusProjeto = StatusProjeto.valueOf(scanner.nextLine().toUpperCase());

                            Projeto projeto = new Projeto();
                            projeto.setId(id);
                            projeto.setNome(nome);
                            projeto.setDescricao(descricao);
                            projeto.setStatus(statusProjeto);

                            projetoDAO.atualizar(projeto);

                            System.out.println("Projeto atualizado!");
                        }else {
                            System.out.println("Status do projeto inválido");
                        }
                    }else {
                        System.out.println("Descrição do projeto inválido");
                    }
                }else {
                    System.out.println("Nome do projeto inválido");
                }
            } else {
                System.out.println("Id do projeto inválido");
            }
        }else {
            System.out.println("Entrada Inválida - Insira um número");
        }

    }



    public static void deletarProjeto() {

        System.out.println("ID do projeto:");
        if(scanner.hasNextInt()) {
            int id = scanner.nextInt();
            if(projetoDAO.existeProjeto(id) != 0) {
                Projeto projeto = projetoDAO.getProjetoPorId(id);
                System.out.println("Deseja deletar o projeto (1-Sim / 0-Não)" + " Nome:"
                        + projeto.getNome() + " Descrição:" + projeto.getDescricao());
                if(scanner.hasNextInt()) {
                    int deletar = scanner.nextInt();
                    if(deletar == 1) {
                        projetoDAO.deletar(id);

                        System.out.println("Projeto removido!");
                    }else {
                        System.out.println("Projeto não removido");
                    }
                }else {
                    System.out.println("Opção inválida");
                }
            }else {
                System.out.println("Id do projeto inválido");
            }
        } else {
            System.out.println("Entrada Inválida - Insira um número");

    }

}

    public static void adicionarEquipeProjeto(){

        System.out.println("ID do projeto:");

        if(scanner.hasNextInt()) {

            int projetoId = scanner.nextInt();

            if(projetoDAO.existeProjeto(projetoId) != 0) {

                System.out.println("ID da equipe:");

                if(scanner.hasNextInt()) {

                    int equipeId = scanner.nextInt();

                    if(equipeDAO.existeEquipe(equipeId) != 0) {

                        projetoDAO.adicionarEquipe(projetoId, equipeId);
                        System.out.println("Equipe adicionada ao projeto!");

                    }else {
                        System.out.println("Id da equipe inválido");
                    }
                }else {
                    System.out.println("Entrada Inválida - Insira um número");
                }
            }else {
                System.out.println("Id do projeto inválido");
            }
        }else {
            System.out.println("Entrada Inválida - Insira um número");
        }

    }

    public static void removerEquipeProjeto(){

        System.out.println("ID do projeto:");

        if(scanner.hasNextInt()) {

            int projetoId = scanner.nextInt();

            if (projetoDAO.existeProjeto(projetoId) != 0) {

                System.out.println("ID da equipe:");

                if(scanner.hasNextInt()) {
                    int equipeId = scanner.nextInt();

                    if(equipeDAO.existeEquipe(equipeId) != 0) {

                        projetoDAO.removerEquipe(projetoId, equipeId);
                        System.out.println("Equipe removida do projeto!");

                    }else {
                        System.out.println("Id da equipe inválido");
                    }
                }else {
                    System.out.println("Entrada Inválida - Insira um número");
                }
            }else {
                System.out.println("Id do projeto inválido");
            }
        }else {
            System.out.println("Entrada Inválida - Insira um número");
        }

    }

    public static void listarEquipesProjeto(){

        System.out.println("ID do projeto:");

        if(scanner.hasNextInt()) {

            int projetoId = scanner.nextInt();

            if(projetoDAO.existeProjeto(projetoId) != 0) {

                List<Equipe> equipes = projetoDAO.listarEquipesProjeto(projetoId);

                System.out.println("\n--- EQUIPES DO PROJETO ---");

                for (Equipe e : equipes) {

                    System.out.print("ID:" + e.getId());
                    System.out.print(" Nome:" + e.getNome());
                    System.out.println(" Descrição:" + e.getDescricao());
                    System.out.println("--------------------");

                }

            }else {
                System.out.println("Id do projeto inválido");
            }

        }else {
            System.out.println("Entrada Inválida - Insira um número");
        }
    }


}
