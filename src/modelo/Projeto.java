package modelo;

/**
 * Classe que representa um projeto do sistema
 * Demonstra encapsulamento e relacionamento com outras classes
 * 
 * @author Equipe AutoMax
 * @version 1.0
 */
public class Projeto {
    // Atributos privados (encapsulamento)
    private String nome;
    private String descricao;
    private String dataInicio;
    private String dataFimPrevista;
    private String status;
    private Gerente gerenteResponsavel;
    private static int contadorProjetos = 0; // Atributo estático para contar projetos
    private int id;

    /**
     * Construtor parametrizado da classe Projeto
     * @param nome Nome do projeto
     * @param descricao Descrição detalhada do projeto
     * @param dataInicio Data de início do projeto
     * @param dataFimPrevista Data prevista para conclusão
     * @param gerenteResponsavel Gerente responsável pelo projeto
     */
    public Projeto(String nome, String descricao, String dataInicio, String dataFimPrevista, Gerente gerenteResponsavel) {
        this.id = ++contadorProjetos; // Incrementa contador e atribui ID único
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFimPrevista = dataFimPrevista;
        this.status = "Planejado"; // Status inicial padrão
        this.gerenteResponsavel = gerenteResponsavel;
        
        // Adiciona este projeto à lista do gerente (se gerente não for null)
        if (gerenteResponsavel != null) {
            gerenteResponsavel.adicionarProjetoGerenciado(this);
        }
    }

    /**
     * Construtor sobrecarregado sem gerente (sobrecarga de construtores)
     * @param nome Nome do projeto
     * @param descricao Descrição do projeto
     * @param dataInicio Data de início
     * @param dataFimPrevista Data prevista para conclusão
     */
    public Projeto(String nome, String descricao, String dataInicio, String dataFimPrevista) {
        this(nome, descricao, dataInicio, dataFimPrevista, null);
    }

    /**
     * Método para exibir informações completas do projeto
     */
    public void exibirInformacoes() {
        System.out.println("INFORMAÇÕES DO PROJETO");
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Descrição: " + descricao);
        System.out.println("Data de Início: " + dataInicio);
        System.out.println("Data Fim Prevista: " + dataFimPrevista);
        System.out.println("Status: " + status);
        System.out.println("Progresso: " + calcularProgresso() + "%");
        System.out.println("Gerente Responsável: " + 
                          (gerenteResponsavel != null ? gerenteResponsavel.getNome() : "Não definido"));
        System.out.println("─".repeat(40));
    }

    /**
     * Método para atualizar o status do projeto com validação (tratamento de exceções)
     * @param novoStatus Novo status do projeto
     */
    public void atualizarStatus(String novoStatus) {
        try {
            String[] statusValidos = {"Planejado", "Em Andamento", "Concluído", "Cancelado", "Pausado"};
            
            // Validação do status
            boolean statusValido = false;
            for (String statusPermitido : statusValidos) {
                if (statusPermitido.equalsIgnoreCase(novoStatus)) {
                    statusValido = true;
                    novoStatus = statusPermitido; // Padroniza a capitalização
                    break;
                }
            }
            
            if (statusValido) {
                String statusAnterior = this.status;
                this.status = novoStatus;
                System.out.println("Status do projeto '" + nome + "' atualizado:");
                System.out.println("   " + statusAnterior + " → " + novoStatus);
            } else {
                System.out.println("Status inválido: " + novoStatus);
                System.out.println("Status válidos: Planejado, Em Andamento, Concluído, Cancelado, Pausado");
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar status: " + e.getMessage());
        }
    }

    /**
     * Método para verificar se o projeto está em atraso (simulação)
     * @param dataAtual Data atual para comparação
     * @return true se estiver em atraso, false caso contrário
     */
    public boolean estaEmAtraso(String dataAtual) {
        try {
            // Para demonstração, considera em atraso se status não é "Concluído" 
            // e a data atual é posterior à data fim prevista
            if ("Concluído".equals(status) || "Cancelado".equals(status)) {
                return false;
            }
            
            // Implementação simplificada de comparação de datas
            return dataAtual.compareTo(dataFimPrevista) > 0;
        } catch (Exception e) {
            System.out.println("Erro ao verificar atraso: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para calcular progresso do projeto baseado no status
     * @return Percentual de progresso baseado no status
     */
    public int calcularProgresso() {
        switch (status) {
            case "Planejado":
                return 0;
            case "Em Andamento":
                return 50; // Assume 50% quando em andamento
            case "Concluído":
                return 100;
            case "Cancelado":
                return 0;
            case "Pausado":
                return 25; // Assume 25% quando pausado
            default:
                return 0;
        }
    }

    /**
     * Método para gerar relatório resumido do projeto
     */
    public void gerarRelatorioResumido() {
        System.out.println("RELATÓRIO RESUMIDO - " + nome);
        System.out.println("ID: #" + id);
        System.out.println("Status: " + status + " (" + calcularProgresso() + "%)");
        System.out.println("Gerente: " + (gerenteResponsavel != null ? gerenteResponsavel.getNome() : "N/A"));
        System.out.println("Período: " + dataInicio + " até " + dataFimPrevista);
        System.out.println("Descrição: " + descricao);
        System.out.println("─".repeat(40));
    }

    /**
     * Método para iniciar o projeto
     */
    public void iniciarProjeto() {
        if ("Planejado".equals(status)) {
            atualizarStatus("Em Andamento");
            System.out.println("Projeto '" + nome + "' foi iniciado!");
        } else {
            System.out.println("Projeto não pode ser iniciado. Status atual: " + status);
        }
    }

    /**
     * Método para finalizar o projeto
     */
    public void finalizarProjeto() {
        if ("Em Andamento".equals(status) || "Pausado".equals(status)) {
            atualizarStatus("Concluído");
            System.out.println("Projeto '" + nome + "' foi finalizado com sucesso!");
        } else {
            System.out.println("Projeto não pode ser finalizado. Status atual: " + status);
        }
    }

    /**
     * Método para pausar o projeto
     */
    public void pausarProjeto() {
        if ("Em Andamento".equals(status)) {
            atualizarStatus("Pausado");
            System.out.println("⏸Projeto '" + nome + "' foi pausado.");
        } else {
            System.out.println("Projeto não pode ser pausado. Status atual: " + status);
        }
    }

    /**
     * Método para cancelar o projeto
     */
    public void cancelarProjeto() {
        if (!"Concluído".equals(status)) {
            atualizarStatus("Cancelado");
            System.out.println("Projeto '" + nome + "' foi cancelado.");
        } else {
            System.out.println("Projeto concluído não pode ser cancelado.");
        }
    }

    /**
     * Método para reativar projeto pausado
     */
    public void reativarProjeto() {
        if ("Pausado".equals(status)) {
            atualizarStatus("Em Andamento");
            System.out.println("Projeto '" + nome + "' foi reativado!");
        } else {
            System.out.println("Apenas projetos pausados podem ser reativados. Status atual: " + status);
        }
    }

    // Getters e Setters (encapsulamento)
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFimPrevista() {
        return dataFimPrevista;
    }

    public void setDataFimPrevista(String dataFimPrevista) {
        this.dataFimPrevista = dataFimPrevista;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Gerente getGerenteResponsavel() {
        return gerenteResponsavel;
    }

    public void setGerenteResponsavel(Gerente gerenteResponsavel) {
        this.gerenteResponsavel = gerenteResponsavel;
    }

    public static int getContadorProjetos() {
        return contadorProjetos;
    }

    /**
     * Método para verificar se o projeto está ativo
     * @return true se está em andamento, false caso contrário
     */
    public boolean estaAtivo() {
        return "Em Andamento".equals(status) || "Planejado".equals(status);
    }

    /**
     * Método para verificar se o projeto foi finalizado
     * @return true se concluído ou cancelado, false caso contrário
     */
    public boolean foiFinalizado() {
        return "Concluído".equals(status) || "Cancelado".equals(status);
    }

    /**
     * Método sobrescrito toString
     */
    @Override
    public String toString() {
        return "Projeto #" + id + ": " + nome + " (" + status + ")";
    }

    /**
     * Método sobrescrito equals para comparação de projetos
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Projeto projeto = (Projeto) obj;
        return id == projeto.id;
    }

    /**
     * Método sobrescrito hashCode
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    /**
     * Método para obter resumo do projeto
     * @return String com informações resumidas
     */
    public String obterResumo() {
        return String.format("Projeto #%d: %s [%s] - %d%% concluído", 
                           id, nome, status, calcularProgresso());
    }

    /**
     * Método para validar se o projeto pode mudar para um status específico
     * @param novoStatus Status desejado
     * @return true se a mudança é válida
     */
    public boolean podeAlterarPara(String novoStatus) {
        switch (novoStatus) {
            case "Em Andamento":
                return "Planejado".equals(status) || "Pausado".equals(status);
            case "Pausado":
                return "Em Andamento".equals(status);
            case "Concluído":
                return "Em Andamento".equals(status) || "Pausado".equals(status);
            case "Cancelado":
                return !"Concluído".equals(status);
            default:
                return false;
        }
    }
}