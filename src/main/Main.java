package main;

import modelo.*;
import servico.*;
import java.util.Scanner;

/**
 * Sistema de Gestao de Projetos - Loja de Carros AutoMax
 * Classe principal que inicializa o sistema e gerencia o menu
 * 
 * @author Equipe AutoMax
 * @version 1.0
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static GerenciadorUsuario gerenciadorUsuario = new GerenciadorUsuario();
    private static GerenciadorProjeto gerenciadorProjeto = new GerenciadorProjeto();
    private static GerenciadorEquipe gerenciadorEquipe = new GerenciadorEquipe();
    private static Usuario usuarioLogado = null;

    public static void main(String[] args) {
        System.out.println("=".repeat(50));
        System.out.println("   SISTEMA AUTOMAX - GESTAO DE PROJETOS");
        System.out.println("=".repeat(50));
        
        // Cadastrar usuario administrador padrao
        inicializarSistema();
        
        // Loop principal do sistema
        while (true) {
            try {
                if (usuarioLogado == null) {
                    menuLogin();
                } else {
                    menuPrincipal();
                }
            } catch (Exception e) {
                System.out.println("[ERRO] Erro inesperado: " + e.getMessage());
                System.out.println("Retornando ao menu...\n");
            }
        }
    }

    /**
     * Inicializa o sistema com dados basicos
     */
    private static void inicializarSistema() {
        // Criar administrador padrao
        Administrador admin = new Administrador("Admin Sistema", "000.000.000-00", 
                                              "admin@automax.com", "admin", "123456");
        gerenciadorUsuario.adicionarUsuario(admin);
        
        // Criar alguns usuarios de exemplo
        Gerente gerente1 = new Gerente("Joao Silva", "111.111.111-11", 
                                     "joao@automax.com", "joao", "123");
        Colaborador colab1 = new Colaborador("Maria Santos", "222.222.222-22", 
                                           "maria@automax.com", "maria", "123");
        
        gerenciadorUsuario.adicionarUsuario(gerente1);
        gerenciadorUsuario.adicionarUsuario(colab1);
        
        System.out.println("[OK] Sistema inicializado com sucesso!");
        System.out.println("Login padrao: admin / senha: 123456\n");
    }

    /**
     * Menu de login do sistema
     */
    private static void menuLogin() {
        System.out.println("[LOGIN] LOGIN NO SISTEMA");
        System.out.println("1. Fazer Login");
        System.out.println("2. Sair do Sistema");
        System.out.print("Escolha uma opcao: ");
        
        int opcao = lerOpcao();
        
        switch (opcao) {
            case 1:
                realizarLogin();
                break;
            case 2:
                System.out.println("Obrigado por usar o Sistema AutoMax!");
                System.exit(0);
                break;
            default:
                System.out.println("[ERRO] Opcao invalida!\n");
        }
    }

    /**
     * Realiza o processo de login
     */
    private static void realizarLogin() {
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        usuarioLogado = gerenciadorUsuario.autenticar(login, senha);
        
        if (usuarioLogado != null) {
            System.out.println("[OK] Login realizado com sucesso!");
            usuarioLogado.exibirPerfil();
            System.out.println();
        } else {
            System.out.println("[ERRO] Login ou senha incorretos!\n");
        }
    }

    /**
     * Menu principal apos login
     */
    private static void menuPrincipal() {
        System.out.println("[MENU] MENU PRINCIPAL - " + usuarioLogado.getNome());
        System.out.println("1. Gerenciar Usuarios");
        System.out.println("2. Gerenciar Projetos");
        System.out.println("3. Gerenciar Equipes");
        System.out.println("4. Relatorios");
        System.out.println("5. Meu Perfil");
        System.out.println("6. Logout");
        System.out.print("Escolha uma opcao: ");
        
        int opcao = lerOpcao();
        
        switch (opcao) {
            case 1:
                menuUsuarios();
                break;
            case 2:
                menuProjetos();
                break;
            case 3:
                menuEquipes();
                break;
            case 4:
                menuRelatorios();
                break;
            case 5:
                exibirMeuPerfil();
                break;
            case 6:
                usuarioLogado = null;
                System.out.println("[OK] Logout realizado com sucesso!\n");
                break;
            default:
                System.out.println("[ERRO] Opcao invalida!\n");
        }
    }

    /**
     * Menu de gerenciamento de usuarios
     */
    private static void menuUsuarios() {
        if (!(usuarioLogado instanceof Administrador)) {
            System.out.println("[ERRO] Acesso negado! Apenas administradores podem gerenciar usuarios.\n");
            return;
        }
        
        System.out.println("[USUARIOS] GERENCIAR USUARIOS");
        System.out.println("1. Cadastrar Usuario");
        System.out.println("2. Listar Usuarios");
        System.out.println("3. Voltar");
        System.out.print("Escolha uma opcao: ");
        
        int opcao = lerOpcao();
        
        switch (opcao) {
            case 1:
                cadastrarUsuario();
                break;
            case 2:
                gerenciadorUsuario.listarUsuarios();
                break;
            case 3:
                return;
            default:
                System.out.println("[ERRO] Opcao invalida!\n");
        }
    }

    /**
     * Cadastra um novo usuario no sistema
     */
    private static void cadastrarUsuario() {
        System.out.println("[CADASTRO] CADASTRO DE USUARIO");
        
        System.out.print("Nome completo: ");
        String nome = scanner.nextLine();
        
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Login: ");
        String login = scanner.nextLine();
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        System.out.println("Tipo de usuario:");
        System.out.println("1. Administrador");
        System.out.println("2. Gerente");
        System.out.println("3. Colaborador");
        System.out.print("Escolha: ");
        
        int tipo = lerOpcao();
        Usuario novoUsuario = null;
        
        switch (tipo) {
            case 1:
                novoUsuario = new Administrador(nome, cpf, email, login, senha);
                break;
            case 2:
                novoUsuario = new Gerente(nome, cpf, email, login, senha);
                break;
            case 3:
                novoUsuario = new Colaborador(nome, cpf, email, login, senha);
                break;
            default:
                System.out.println("[ERRO] Tipo invalido!\n");
                return;
        }
        
        if (gerenciadorUsuario.adicionarUsuario(novoUsuario)) {
            System.out.println("[OK] Usuario cadastrado com sucesso!\n");
        } else {
            System.out.println("[ERRO] Erro ao cadastrar usuario. Login ja existe!\n");
        }
    }

    /**
     * Menu de gerenciamento de projetos
     */
    private static void menuProjetos() {
        System.out.println("[PROJETOS] GERENCIAR PROJETOS");
        System.out.println("1. Cadastrar Projeto");
        System.out.println("2. Listar Projetos");
        System.out.println("3. Atualizar Status");
        System.out.println("4. Voltar");
        System.out.print("Escolha uma opcao: ");
        
        int opcao = lerOpcao();
        
        switch (opcao) {
            case 1:
                cadastrarProjeto();
                break;
            case 2:
                gerenciadorProjeto.listarProjetos();
                break;
            case 3:
                atualizarStatusProjeto();
                break;
            case 4:
                return;
            default:
                System.out.println("[ERRO] Opcao invalida!\n");
        }
    }

    /**
     * Cadastra um novo projeto
     */
    private static void cadastrarProjeto() {
        if (!(usuarioLogado instanceof Administrador) && !(usuarioLogado instanceof Gerente)) {
            System.out.println("[ERRO] Acesso negado! Apenas administradores e gerentes podem cadastrar projetos.\n");
            return;
        }
        
        System.out.println("[CADASTRO] CADASTRO DE PROJETO");
        
        System.out.print("Nome do projeto: ");
        String nome = scanner.nextLine();
        
        System.out.print("Descricao: ");
        String descricao = scanner.nextLine();
        
        System.out.print("Data de inicio (dd/mm/yyyy): ");
        String dataInicio = scanner.nextLine();
        
        System.out.print("Data de termino prevista (dd/mm/yyyy): ");
        String dataFim = scanner.nextLine();
        
        // Selecionar gerente responsavel
        System.out.println("Gerentes disponiveis:");
        var gerentes = gerenciadorUsuario.listarGerentes();
        
        if (gerentes.isEmpty()) {
            System.out.println("[ERRO] Nenhum gerente cadastrado!\n");
            return;
        }
        
        for (int i = 0; i < gerentes.size(); i++) {
            System.out.println((i + 1) + ". " + gerentes.get(i).getNome());
        }
        
        System.out.print("Escolha o gerente responsavel: ");
        int indiceGerente = lerOpcao() - 1;
        
        if (indiceGerente < 0 || indiceGerente >= gerentes.size()) {
            System.out.println("[ERRO] Gerente invalido!\n");
            return;
        }
        
        Gerente gerenteResponsavel = gerentes.get(indiceGerente);
        Projeto novoProjeto = new Projeto(nome, descricao, dataInicio, dataFim, gerenteResponsavel);
        
        gerenciadorProjeto.adicionarProjeto(novoProjeto);
        System.out.println("[OK] Projeto cadastrado com sucesso!\n");
    }

    /**
     * Atualiza o status de um projeto
     */
    private static void atualizarStatusProjeto() {
        var projetos = gerenciadorProjeto.listarProjetosList();
        
        if (projetos.isEmpty()) {
            System.out.println("[ERRO] Nenhum projeto cadastrado!\n");
            return;
        }
        
        System.out.println("[STATUS] ATUALIZAR STATUS DO PROJETO");
        for (int i = 0; i < projetos.size(); i++) {
            System.out.println((i + 1) + ". " + projetos.get(i).getNome() + 
                             " - Status: " + projetos.get(i).getStatus());
        }
        
        System.out.print("Escolha o projeto: ");
        int indiceProjeto = lerOpcao() - 1;
        
        if (indiceProjeto < 0 || indiceProjeto >= projetos.size()) {
            System.out.println("[ERRO] Projeto invalido!\n");
            return;
        }
        
        System.out.println("Novo status:");
        System.out.println("1. Planejado");
        System.out.println("2. Em Andamento");
        System.out.println("3. Concluido");
        System.out.println("4. Cancelado");
        System.out.print("Escolha: ");
        
        int novoStatus = lerOpcao();
        String[] status = {"Planejado", "Em Andamento", "Concluído", "Cancelado"};
        
        if (novoStatus >= 1 && novoStatus <= 4) {
            projetos.get(indiceProjeto).setStatus(status[novoStatus - 1]);
            System.out.println("[OK] Status atualizado com sucesso!\n");
        } else {
            System.out.println("[ERRO] Status invalido!\n");
        }
    }

    /**
     * Menu de gerenciamento de equipes
     */
    private static void menuEquipes() {
        System.out.println("[EQUIPES] GERENCIAR EQUIPES");
        System.out.println("1. Criar Equipe");
        System.out.println("2. Listar Equipes");
        System.out.println("3. Adicionar Membro");
        System.out.println("4. Voltar");
        System.out.print("Escolha uma opcao: ");
        
        int opcao = lerOpcao();
        
        switch (opcao) {
            case 1:
                criarEquipe();
                break;
            case 2:
                gerenciadorEquipe.listarEquipes();
                break;
            case 3:
                adicionarMembroEquipe();
                break;
            case 4:
                return;
            default:
                System.out.println("[ERRO] Opcao invalida!\n");
        }
    }

    /**
     * Cria uma nova equipe
     */
    private static void criarEquipe() {
        if (!(usuarioLogado instanceof Administrador) && !(usuarioLogado instanceof Gerente)) {
            System.out.println("[ERRO] Acesso negado! Apenas administradores e gerentes podem criar equipes.\n");
            return;
        }
        
        System.out.println("[CADASTRO] CRIAR EQUIPE");
        
        System.out.print("Nome da equipe: ");
        String nome = scanner.nextLine();
        
        System.out.print("Descricao: ");
        String descricao = scanner.nextLine();
        
        Equipe novaEquipe = new Equipe(nome, descricao);
        gerenciadorEquipe.adicionarEquipe(novaEquipe);
        
        System.out.println("[OK] Equipe criada com sucesso!\n");
    }

    /**
     * Adiciona membro a uma equipe
     */
    private static void adicionarMembroEquipe() {
        var equipes = gerenciadorEquipe.listarEquipesList();
        var usuarios = gerenciadorUsuario.listarUsuariosList();
        
        if (equipes.isEmpty()) {
            System.out.println("[ERRO] Nenhuma equipe cadastrada!\n");
            return;
        }
        
        if (usuarios.isEmpty()) {
            System.out.println("[ERRO] Nenhum usuario cadastrado!\n");
            return;
        }
        
        System.out.println("[MEMBRO] ADICIONAR MEMBRO A EQUIPE");
        
        // Selecionar equipe
        System.out.println("Equipes disponiveis:");
        for (int i = 0; i < equipes.size(); i++) {
            System.out.println((i + 1) + ". " + equipes.get(i).getNome());
        }
        
        System.out.print("Escolha a equipe: ");
        int indiceEquipe = lerOpcao() - 1;
        
        if (indiceEquipe < 0 || indiceEquipe >= equipes.size()) {
            System.out.println("[ERRO] Equipe invalida!\n");
            return;
        }
        
        // Selecionar usuario
        System.out.println("Usuarios disponiveis:");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println((i + 1) + ". " + usuarios.get(i).getNome() + 
                             " (" + usuarios.get(i).getClass().getSimpleName() + ")");
        }
        
        System.out.print("Escolha o usuario: ");
        int indiceUsuario = lerOpcao() - 1;
        
        if (indiceUsuario < 0 || indiceUsuario >= usuarios.size()) {
            System.out.println("[ERRO] Usuario invalido!\n");
            return;
        }
        
        equipes.get(indiceEquipe).adicionarMembro(usuarios.get(indiceUsuario));
        System.out.println("[OK] Membro adicionado a equipe com sucesso!\n");
    }

    /**
     * Menu de relatorios
     */
    private static void menuRelatorios() {
        System.out.println("[RELATORIOS] RELATORIOS");
        System.out.println("1. Relatorio de Projetos");
        System.out.println("2. Relatorio de Equipes");
        System.out.println("3. Relatorio de Usuarios");
        System.out.println("4. Voltar");
        System.out.print("Escolha uma opcao: ");
        
        int opcao = lerOpcao();
        
        switch (opcao) {
            case 1:
                gerenciadorProjeto.gerarRelatorio();
                break;
            case 2:
                gerenciadorEquipe.gerarRelatorio();
                break;
            case 3:
                gerenciadorUsuario.gerarRelatorio();
                break;
            case 4:
                return;
            default:
                System.out.println("[ERRO] Opcao invalida!\n");
        }
    }

    /**
     * Exibe informacoes do perfil do usuario logado
     */
    private static void exibirMeuPerfil() {
        System.out.println("[PERFIL] MEU PERFIL");
        usuarioLogado.exibirInformacoes();
        System.out.println();
    }

    /**
     * Le uma opcao do usuario com tratamento de erro
     */
    private static int lerOpcao() {
        try {
            int opcao = Integer.parseInt(scanner.nextLine());
            return opcao;
        } catch (NumberFormatException e) {
            System.out.println("[ERRO] Por favor, digite um numero valido!");
            return -1;
        }
    }
}