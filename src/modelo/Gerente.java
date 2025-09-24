package modelo;

import java.util.ArrayList;

/**
 * Classe que representa um usuário gerente
 * Herda de Usuario e implementa comportamentos específicos (herança e polimorfismo)
 * 
 * @author Equipe AutoMax
 * @version 1.0
 */
public class Gerente extends Usuario {
    // Atributo específico do gerente (coleções)
    private ArrayList<Projeto> projetosGerenciados;

    /**
     * Construtor da classe Gerente
     * @param nome Nome completo do gerente
     * @param cpf CPF do gerente
     * @param email Email do gerente
     * @param login Login para acesso ao sistema
     * @param senha Senha para acesso ao sistema
     */
    public Gerente(String nome, String cpf, String email, String login, String senha) {
        super(nome, cpf, email, login, senha); // Chama o construtor da classe pai
        this.projetosGerenciados = new ArrayList<>(); // Inicializa a coleção
    }

    /**
     * Implementação do método abstrato da classe pai (polimorfismo)
     * Exibe o perfil específico do gerente
     */
    @Override
    public void exibirPerfil() {
        System.out.println("Perfil: Gerente - " + getNome());
        System.out.println("Projetos gerenciados: " + projetosGerenciados.size());
        System.out.println("Permissões: Gerenciar projetos e equipes");
    }

    /**
     * Método específico do gerente para adicionar projeto sob sua responsabilidade
     * @param projeto Projeto a ser adicionado
     */
    public void adicionarProjetoGerenciado(Projeto projeto) {
        if (!projetosGerenciados.contains(projeto)) {
            projetosGerenciados.add(projeto);
            System.out.println("Projeto '" + projeto.getNome() + "' adicionado à lista de gerenciamento");
        }
    }

    /**
     * Método específico para listar projetos gerenciados
     */
    public void listarProjetosGerenciados() {
        System.out.println("PROJETOS GERENCIADOS POR " + getNome().toUpperCase());
        if (projetosGerenciados.isEmpty()) {
            System.out.println("Nenhum projeto sob gerenciamento.");
        } else {
            for (int i = 0; i < projetosGerenciados.size(); i++) {
                Projeto projeto = projetosGerenciados.get(i);
                System.out.println((i + 1) + ". " + projeto.getNome() + " - Status: " + projeto.getStatus());
            }
        }
        System.out.println();
    }

    /**
     * Método específico para aprovar mudanças em projetos
     * @param projeto Projeto a ter mudanças aprovadas
     * @param descricaoMudanca Descrição da mudança aprovada
     */
    public void aprovarMudancaProjeto(Projeto projeto, String descricaoMudanca) {
        if (projetosGerenciados.contains(projeto)) {
            System.out.println("Gerente " + getNome() + " aprovou mudança:");
            System.out.println("Projeto: " + projeto.getNome());
            System.out.println("Mudança: " + descricaoMudanca);
        } else {
            System.out.println("Este projeto não está sob gerenciamento de " + getNome());
        }
    }

    /**
     * Sobrescrita do método exibirInformacoes para incluir informações específicas
     */
    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes(); // Chama o método da classe pai
        System.out.println("Privilégios: Gerente de Projetos");
        System.out.println("Pode: Criar projetos, gerenciar equipes, aprovar mudanças");
        System.out.println("Projetos sob responsabilidade: " + projetosGerenciados.size());
    }

    /**
     * Getter para a lista de projetos gerenciados
     * @return Lista de projetos gerenciados
     */
    public ArrayList<Projeto> getProjetosGerenciados() {
        return new ArrayList<>(projetosGerenciados); // Retorna uma cópia para manter encapsulamento
    }

    /**
     * Método para validar se pode criar projetos
     * @return true sempre, pois gerente pode criar projetos
     */
    public boolean podeCriarProjetos() {
        return true;
    }

    /**
     * Método para validar se pode gerenciar uma equipe específica
     * @return true sempre, pois gerente pode gerenciar equipes
     */
    public boolean podeGerenciarEquipes() {
        return true;
    }

    /**
     * Método para calcular a carga de trabalho do gerente
     * @return Número de projetos ativos gerenciados
     */
    public int calcularCargaTrabalho() {
        int projetosAtivos = 0;
        for (Projeto projeto : projetosGerenciados) {
            if ("Em Andamento".equals(projeto.getStatus()) || "Planejado".equals(projeto.getStatus())) {
                projetosAtivos++;
            }
        }
        return projetosAtivos;
    }
}