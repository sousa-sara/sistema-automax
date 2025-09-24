package modelo;

import java.util.ArrayList;

/**
 * Classe que representa um usuário colaborador
 * Herda de Usuario e implementa comportamentos específicos (herança e polimorfismo)
 * 
 * @author Equipe AutoMax
 * @version 1.0
 */
public class Colaborador extends Usuario {
    // Atributos específicos do colaborador (coleções)
    private ArrayList<String> tarefasAtribuidas;
    private String especialidade;

    /**
     * Construtor da classe Colaborador
     * @param nome Nome completo do colaborador
     * @param cpf CPF do colaborador
     * @param email Email do colaborador
     * @param login Login para acesso ao sistema
     * @param senha Senha para acesso ao sistema
     */
    public Colaborador(String nome, String cpf, String email, String login, String senha) {
        super(nome, cpf, email, login, senha); // Chama o construtor da classe pai
        this.tarefasAtribuidas = new ArrayList<>(); // Inicializa a coleção
        this.especialidade = "Geral"; // Especialidade padrão
    }

    /**
     * Construtor sobrecarregado com especialidade (sobrecarga de métodos)
     * @param nome Nome completo do colaborador
     * @param cpf CPF do colaborador
     * @param email Email do colaborador
     * @param login Login para acesso ao sistema
     * @param senha Senha para acesso ao sistema
     * @param especialidade Especialidade do colaborador
     */
    public Colaborador(String nome, String cpf, String email, String login, String senha, String especialidade) {
        super(nome, cpf, email, login, senha);
        this.tarefasAtribuidas = new ArrayList<>();
        this.especialidade = especialidade;
    }

    /**
     * Implementação do método abstrato da classe pai (polimorfismo)
     * Exibe o perfil específico do colaborador
     */
    @Override
    public void exibirPerfil() {
        System.out.println("Perfil: Colaborador - " + getNome());
        System.out.println("Especialidade: " + especialidade);
        System.out.println("Tarefas atribuídas: " + tarefasAtribuidas.size());
        System.out.println("Permissões: Visualizar projetos e executar tarefas");
    }

    /**
     * Método específico para adicionar tarefa ao colaborador
     * @param tarefa Descrição da tarefa
     */
    public void adicionarTarefa(String tarefa) {
        try {
            if (tarefa != null && !tarefa.trim().isEmpty()) {
                tarefasAtribuidas.add(tarefa);
                System.out.println("Tarefa atribuída a " + getNome() + ": " + tarefa);
            } else {
                System.out.println("Tarefa inválida!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao adicionar tarefa: " + e.getMessage());
        }
    }

    /**
     * Método para marcar tarefa como concluída (tratamento de exceções)
     * @param indiceTarefa Índice da tarefa na lista
     */
    public void concluirTarefa(int indiceTarefa) {
        try {
            if (indiceTarefa >= 0 && indiceTarefa < tarefasAtribuidas.size()) {
                String tarefa = tarefasAtribuidas.get(indiceTarefa);
                if (!tarefa.contains("[CONCLUÍDA]")) {
                    tarefasAtribuidas.set(indiceTarefa, tarefa + " [CONCLUÍDA]");
                    System.out.println("Tarefa concluída: " + tarefa);
                } else {
                    System.out.println("Tarefa já estava concluída!");
                }
            } else {
                System.out.println("Índice de tarefa inválido!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao concluir tarefa: " + e.getMessage());
        }
    }

    /**
     * Método para listar todas as tarefas do colaborador
     */
    public void listarTarefas() {
        System.out.println("TAREFAS DE " + getNome().toUpperCase());
        if (tarefasAtribuidas.isEmpty()) {
            System.out.println("Nenhuma tarefa atribuída.");
        } else {
            for (int i = 0; i < tarefasAtribuidas.size(); i++) {
                String status = tarefasAtribuidas.get(i).contains("[CONCLUÍDA]") ? "OK" : "Aguarde";
                System.out.println(status + " " + (i + 1) + ". " + tarefasAtribuidas.get(i));
            }
        }
        System.out.println();
    }

    /**
     * Método para enviar relatório de progresso
     * @param descricaoProgresso Descrição do progresso realizado
     */
    public void enviarRelatorioProgresso(String descricaoProgresso) {
        System.out.println("RELATÓRIO DE PROGRESSO - " + getNome());
        System.out.println("Data: " + java.time.LocalDate.now());
        System.out.println("Descrição: " + descricaoProgresso);
        System.out.println("Tarefas pendentes: " + contarTarefasPendentes());
        System.out.println("Tarefas concluídas: " + contarTarefasConcluidas());
        System.out.println("Produtividade: " + String.format("%.1f", calcularProdutividade()) + "%");
        System.out.println("─".repeat(40));
    }

    /**
     * Método auxiliar para contar tarefas pendentes
     * @return Número de tarefas pendentes
     */
    private int contarTarefasPendentes() {
        int contador = 0;
        for (String tarefa : tarefasAtribuidas) {
            if (!tarefa.contains("[CONCLUÍDA]")) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Método auxiliar para contar tarefas concluídas
     * @return Número de tarefas concluídas
     */
    private int contarTarefasConcluidas() {
        int contador = 0;
        for (String tarefa : tarefasAtribuidas) {
            if (tarefa.contains("[CONCLUÍDA]")) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Sobrescrita do método exibirInformacoes para incluir informações específicas
     */
    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes(); // Chama o método da classe pai
        System.out.println("Privilégios: Colaborador");
        System.out.println("Pode: Visualizar projetos, executar tarefas, enviar relatórios");
        System.out.println("Especialidade: " + especialidade);
        System.out.println("Total de tarefas: " + tarefasAtribuidas.size());
        System.out.println("Produtividade: " + String.format("%.1f", calcularProdutividade()) + "%");
    }

    /**
     * Método para remover tarefa da lista
     * @param indiceTarefa Índice da tarefa a ser removida
     */
    public void removerTarefa(int indiceTarefa) {
        try {
            if (indiceTarefa >= 0 && indiceTarefa < tarefasAtribuidas.size()) {
                String tarefaRemovida = tarefasAtribuidas.remove(indiceTarefa);
                System.out.println("Tarefa removida: " + tarefaRemovida);
            } else {
                System.out.println("Índice de tarefa inválido!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao remover tarefa: " + e.getMessage());
        }
    }

    // Getters e Setters específicos (encapsulamento)
    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    /**
     * Getter que retorna cópia da lista para preservar encapsulamento
     * @return Cópia da lista de tarefas
     */
    public ArrayList<String> getTarefasAtribuidas() {
        return new ArrayList<>(tarefasAtribuidas);
    }

    /**
     * Método para validar se pode executar uma tarefa específica
     * @param tipoTarefa Tipo da tarefa a ser executada
     * @return true se pode executar, false caso contrário
     */
    public boolean podeExecutarTarefa(String tipoTarefa) {
        return especialidade.equals("Geral") || especialidade.equalsIgnoreCase(tipoTarefa);
    }

    /**
     * Método para calcular produtividade do colaborador
     * @return Percentual de tarefas concluídas
     */
    public double calcularProdutividade() {
        if (tarefasAtribuidas.isEmpty()) {
            return 0.0;
        }
        double concluidas = contarTarefasConcluidas();
        return (concluidas / tarefasAtribuidas.size()) * 100;
    }

    /**
     * Método para obter status atual do colaborador
     * @return String com resumo do status
     */
    public String obterStatusAtual() {
        int pendentes = contarTarefasPendentes();
        int concluidas = contarTarefasConcluidas();
        return String.format("Tarefas: %d pendentes, %d concluídas (%.1f%% produtividade)", 
                           pendentes, concluidas, calcularProdutividade());
    }

    /**
     * Método para marcar todas as tarefas como concluídas
     */
    public void concluirTodasTarefas() {
        try {
            int tarefasConcluidas = 0;
            for (int i = 0; i < tarefasAtribuidas.size(); i++) {
                String tarefa = tarefasAtribuidas.get(i);
                if (!tarefa.contains("[CONCLUÍDA]")) {
                    tarefasAtribuidas.set(i, tarefa + " [CONCLUÍDA]");
                    tarefasConcluidas++;
                }
            }
            System.out.println("OK" + tarefasConcluidas + " tarefas marcadas como concluídas!");
        } catch (Exception e) {
            System.out.println("Erro ao concluir tarefas: " + e.getMessage());
        }
    }

    /**
     * Método para obter número total de tarefas
     * @return Total de tarefas atribuídas
     */
    public int getTotalTarefas() {
        return tarefasAtribuidas.size();
    }
}