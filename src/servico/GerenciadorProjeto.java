package servico;

import modelo.*;
import java.util.ArrayList;

/**
 * Classe de serviço para gerenciar projetos do sistema
 * Demonstra uso de coleções e tratamento de exceções
 * 
 * @author Equipe AutoMax
 * @version 1.0
 */
public class GerenciadorProjeto {
    // Coleção para armazenar todos os projetos do sistema
    private ArrayList<Projeto> projetos;

    /**
     * Construtor do gerenciador de projetos
     */
    public GerenciadorProjeto() {
        this.projetos = new ArrayList<>();
    }

    /**
     * Adiciona um novo projeto ao sistema
     * @param projeto Projeto a ser adicionado
     * @return true se adicionado com sucesso
     */
    public boolean adicionarProjeto(Projeto projeto) {
        try {
            if (projeto == null) {
                System.out.println("Projeto inválido!");
                return false;
            }

            // Verificar se já existe projeto com o mesmo nome
            if (buscarPorNome(projeto.getNome()) != null) {
                System.out.println("Já existe um projeto com o nome: " + projeto.getNome());
                return false;
            }

            projetos.add(projeto);
            System.out.println("Projeto '" + projeto.getNome() + "' adicionado com sucesso!");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao adicionar projeto: " + e.getMessage());
            return false;
        }
    }

    /**
     * Lista todos os projetos do sistema
     */
    public void listarProjetos() {
        System.out.println("LISTA DE PROJETOS DO SISTEMA");
        System.out.println("Total de projetos: " + projetos.size());
        System.out.println("═".repeat(70));

        if (projetos.isEmpty()) {
            System.out.println("Nenhum projeto cadastrado no sistema.");
        } else {
            for (int i = 0; i < projetos.size(); i++) {
                Projeto projeto = projetos.get(i);
                System.out.println((i + 1) + ". " + projeto.getNome());
                System.out.println("   ID: " + projeto.getId());
                System.out.println("   Status: " + projeto.getStatus() + " (" + projeto.calcularProgresso() + "%)");
                System.out.println("   Período: " + projeto.getDataInicio() + " até " + projeto.getDataFimPrevista());
                System.out.println("   Gerente: " + (projeto.getGerenteResponsavel() != null ? 
                                                   projeto.getGerenteResponsavel().getNome() : "Não definido"));
                System.out.println("   Descrição: " + projeto.getDescricao());
                System.out.println("   ─".repeat(50));
            }
        }
        System.out.println();
    }

    /**
     * Busca projeto por nome
     * @param nome Nome do projeto a ser buscado
     * @return Projeto encontrado ou null
     */
    public Projeto buscarPorNome(String nome) {
        try {
            for (Projeto projeto : projetos) {
                if (projeto.getNome().equalsIgnoreCase(nome.trim())) {
                    return projeto;
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println("Erro ao buscar projeto: " + e.getMessage());
            return null;
        }
    }

    /**
     * Busca projeto por ID
     * @param id ID do projeto
     * @return Projeto encontrado ou null
     */
    public Projeto buscarPorId(int id) {
        for (Projeto projeto : projetos) {
            if (projeto.getId() == id) {
                return projeto;
            }
        }
        return null;
    }

    /**
     * Lista projetos por status
     * @param status Status desejado
     * @return Lista de projetos com o status especificado
     */
    public ArrayList<Projeto> listarPorStatus(String status) {
        ArrayList<Projeto> projetosFiltrados = new ArrayList<>();
        
        for (Projeto projeto : projetos) {
            if (projeto.getStatus().equalsIgnoreCase(status)) {
                projetosFiltrados.add(projeto);
            }
        }
        
        return projetosFiltrados;
    }

    /**
     * Lista projetos de um gerente específico
     * @param gerente Gerente responsável
     * @return Lista de projetos do gerente
     */
    public ArrayList<Projeto> listarProjetosPorGerente(Gerente gerente) {
        ArrayList<Projeto> projetosDoGerente = new ArrayList<>();
        
        for (Projeto projeto : projetos) {
            if (projeto.getGerenteResponsavel() != null && 
                projeto.getGerenteResponsavel().equals(gerente)) {
                projetosDoGerente.add(projeto);
            }
        }
        
        return projetosDoGerente;
    }

    /**
     * Atualiza status de um projeto
     * @param idProjeto ID do projeto
     * @param novoStatus Novo status
     * @return true se atualizado com sucesso
     */
    public boolean atualizarStatus(int idProjeto, String novoStatus) {
        try {
            Projeto projeto = buscarPorId(idProjeto);
            if (projeto != null) {
                projeto.atualizarStatus(novoStatus);
                return true;
            } else {
                System.out.println("Projeto com ID " + idProjeto + " não encontrado!");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar status: " + e.getMessage());
            return false;
        }
    }

    /**
     * Remove um projeto do sistema
     * @param idProjeto ID do projeto a ser removido
     * @return true se removido com sucesso
     */
    public boolean removerProjeto(int idProjeto) {
        try {
            Projeto projeto = buscarPorId(idProjeto);
            if (projeto != null) {
                projetos.remove(projeto);
                System.out.println("Projeto '" + projeto.getNome() + "' removido com sucesso!");
                return true;
            } else {
                System.out.println("Projeto com ID " + idProjeto + " não encontrado!");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erro ao remover projeto: " + e.getMessage());
            return false;
        }
    }

    /**
     * Gera relatório completo dos projetos
     */
    public void gerarRelatorio() {
        System.out.println("RELATÓRIO COMPLETO DE PROJETOS");
        System.out.println("═".repeat(60));
        
        int total = projetos.size();
        if (total == 0) {
            System.out.println("Nenhum projeto cadastrado para gerar relatório.");
            return;
        }

        // Estatísticas por status
        int planejados = listarPorStatus("Planejado").size();
        int emAndamento = listarPorStatus("Em Andamento").size();
        int concluidos = listarPorStatus("Concluído").size();
        int cancelados = listarPorStatus("Cancelado").size();
        int pausados = listarPorStatus("Pausado").size();

        System.out.println("ESTATÍSTICAS GERAIS:");
        System.out.println("Total de projetos: " + total);
        System.out.println("┌─────────────────┬─────────┬─────────────┐");
        System.out.println("│ Status          │ Qtd     │ Percentual  │");
        System.out.println("├─────────────────┼─────────┼─────────────┤");
        System.out.printf("│ %-15s │ %-7d │ %-11s │%n", "Planejado", planejados, 
                         String.format("%.1f%%", (planejados * 100.0 / total)));
        System.out.printf("│ %-15s │ %-7d │ %-11s │%n", "Em Andamento", emAndamento, 
                         String.format("%.1f%%", (emAndamento * 100.0 / total)));
        System.out.printf("│ %-15s │ %-7d │ %-11s │%n", "Concluído", concluidos, 
                         String.format("%.1f%%", (concluidos * 100.0 / total)));
        System.out.printf("│ %-15s │ %-7d │ %-11s │%n", "Cancelado", cancelados, 
                         String.format("%.1f%%", (cancelados * 100.0 / total)));
        System.out.printf("│ %-15s │ %-7d │ %-11s │%n", "Pausado", pausados, 
                         String.format("%.1f%%", (pausados * 100.0 / total)));
        System.out.println("└─────────────────┴─────────┴─────────────┘");

        // Projetos por gerente
        System.out.println("\nPROJETOS POR GERENTE:");
        contarProjetosPorGerente();

        // Projetos em destaque
        System.out.println("\nPROJETOS EM DESTAQUE:");
        if (!listarPorStatus("Em Andamento").isEmpty()) {
            System.out.println("• Projetos ativos:");
            for (Projeto projeto : listarPorStatus("Em Andamento")) {
                System.out.println("  - " + projeto.getNome() + " (" + projeto.calcularProgresso() + "%)");
            }
        }

        if (!listarPorStatus("Concluído").isEmpty()) {
            System.out.println("• Últimos projetos concluídos:");
            ArrayList<Projeto> concluidos_lista = listarPorStatus("Concluído");
            int limite = Math.min(3, concluidos_lista.size());
            for (int i = 0; i < limite; i++) {
                System.out.println("  - " + concluidos_lista.get(i).getNome());
            }
        }

        System.out.println("═".repeat(60));
        System.out.println();
    }

    /**
     * Método auxiliar para contar projetos por gerente
     */
    private void contarProjetosPorGerente() {
        ArrayList<Gerente> gerentes = new ArrayList<>();
        ArrayList<Integer> contadores = new ArrayList<>();

        for (Projeto projeto : projetos) {
            Gerente gerente = projeto.getGerenteResponsavel();
            if (gerente != null) {
                int index = gerentes.indexOf(gerente);
                if (index >= 0) {
                    contadores.set(index, contadores.get(index) + 1);
                } else {
                    gerentes.add(gerente);
                    contadores.add(1);
                }
            }
        }

        if (gerentes.isEmpty()) {
            System.out.println("  Nenhum projeto com gerente definido.");
        } else {
            for (int i = 0; i < gerentes.size(); i++) {
                System.out.println("  " + gerentes.get(i).getNome() + ": " + contadores.get(i) + " projetos");
            }
        }
    }

    /**
     * Busca projetos por palavra-chave na descrição
     * @param palavraChave Palavra a ser buscada
     * @return Lista de projetos encontrados
     */
    public ArrayList<Projeto> buscarPorPalavraChave(String palavraChave) {
        ArrayList<Projeto> encontrados = new ArrayList<>();
        String busca = palavraChave.toLowerCase().trim();

        for (Projeto projeto : projetos) {
            if (projeto.getNome().toLowerCase().contains(busca) ||
                projeto.getDescricao().toLowerCase().contains(busca)) {
                encontrados.add(projeto);
            }
        }

        return encontrados;
    }

    /**
     * Calcula estatísticas de progresso geral
     * @return Progresso médio de todos os projetos
     */
    public double calcularProgressoMedio() {
        if (projetos.isEmpty()) {
            return 0.0;
        }

        int somaProgresso = 0;
        for (Projeto projeto : projetos) {
            somaProgresso += projeto.calcularProgresso();
        }

        return (double) somaProgresso / projetos.size();
    }

    /**
     * Lista projetos próximos do prazo (simulação)
     * @param dataAtual Data atual para comparação
     * @return Lista de projetos próximos do prazo
     */
    public ArrayList<Projeto> listarProjetosProximosPrazo(String dataAtual) {
        ArrayList<Projeto> proximosPrazo = new ArrayList<>();
        
        for (Projeto projeto : projetos) {
            if (projeto.estaEmAtraso(dataAtual) && 
                !projeto.getStatus().equals("Concluído") && 
                !projeto.getStatus().equals("Cancelado")) {
                proximosPrazo.add(projeto);
            }
        }
        
        return proximosPrazo;
    }

    /**
     * Getter para lista de projetos (retorna cópia para preservar encapsulamento)
     * @return Cópia da lista de projetos
     */
    public ArrayList<Projeto> listarProjetosList() {
        return new ArrayList<>(projetos);
    }

    /**
     * Obtém total de projetos cadastrados
     * @return Número total de projetos
     */
    public int getTotalProjetos() {
        return projetos.size();
    }

    /**
     * Obtém estatísticas rápidas
     * @return String com estatísticas resumidas
     */
    public String obterEstatisticasRapidas() {
        return String.format("Projetos: %d | Ativos: %d | Concluídos: %d | Progresso Médio: %.1f%%",
                projetos.size(),
                listarPorStatus("Em Andamento").size(),
                listarPorStatus("Concluído").size(),
                calcularProgressoMedio());
    }
}