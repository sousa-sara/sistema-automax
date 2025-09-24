package servico;

import modelo.*;
import java.util.ArrayList;

/**
 * Classe de serviço para gerenciar equipes do sistema
 * Demonstra uso de coleções e tratamento de exceções
 * 
 * @author Equipe AutoMax
 * @version 1.0
 */
public class GerenciadorEquipe {
    // Coleção para armazenar todas as equipes do sistema
    private ArrayList<Equipe> equipes;

    /**
     * Construtor do gerenciador de equipes
     */
    public GerenciadorEquipe() {
        this.equipes = new ArrayList<>();
    }

    /**
     * Adiciona uma nova equipe ao sistema
     * @param equipe Equipe a ser adicionada
     * @return true se adicionada com sucesso
     */
    public boolean adicionarEquipe(Equipe equipe) {
        try {
            if (equipe == null) {
                System.out.println("Equipe inválida!");
                return false;
            }

            // Verificar se já existe equipe com o mesmo nome
            if (buscarPorNome(equipe.getNome()) != null) {
                System.out.println("Já existe uma equipe com o nome: " + equipe.getNome());
                return false;
            }

            equipes.add(equipe);
            System.out.println("Equipe '" + equipe.getNome() + "' criada com sucesso!");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao criar equipe: " + e.getMessage());
            return false;
        }
    }

    /**
     * Lista todas as equipes do sistema
     */
    public void listarEquipes() {
        System.out.println("LISTA DE EQUIPES DO SISTEMA");
        System.out.println("Total de equipes: " + equipes.size());
        System.out.println("═".repeat(60));

        if (equipes.isEmpty()) {
            System.out.println("Nenhuma equipe cadastrada no sistema.");
        } else {
            for (int i = 0; i < equipes.size(); i++) {
                Equipe equipe = equipes.get(i);
                System.out.println((i + 1) + ". " + equipe.getNome());
                System.out.println("   ID: " + equipe.getId());
                System.out.println("   Descrição: " + equipe.getDescricao());
                System.out.println("   Membros: " + equipe.tamanho());
                
                // Exibir composição da equipe
                if (equipe.tamanho() > 0) {
                    int admins = 0, gerentes = 0, colaboradores = 0;
                    for (Usuario membro : equipe.getMembros()) {
                        if (membro instanceof Administrador) admins++;
                        else if (membro instanceof Gerente) gerentes++;
                        else if (membro instanceof Colaborador) colaboradores++;
                    }
                    System.out.println("   Composição: " + admins + " admin(s), " + 
                                     gerentes + " gerente(s), " + colaboradores + " colaborador(es)");
                } else {
                    System.out.println("   Composição: Equipe vazia");
                }
                System.out.println("   ─".repeat(40));
            }
        }
        System.out.println();
    }

    /**
     * Busca equipe por nome
     * @param nome Nome da equipe a ser buscada
     * @return Equipe encontrada ou null
     */
    public Equipe buscarPorNome(String nome) {
        try {
            for (Equipe equipe : equipes) {
                if (equipe.getNome().equalsIgnoreCase(nome.trim())) {
                    return equipe;
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println("Erro ao buscar equipe: " + e.getMessage());
            return null;
        }
    }

    /**
     * Busca equipe por ID
     * @param id ID da equipe
     * @return Equipe encontrada ou null
     */
    public Equipe buscarPorId(int id) {
        for (Equipe equipe : equipes) {
            if (equipe.getId() == id) {
                return equipe;
            }
        }
        return null;
    }

    /**
     * Adiciona membro a uma equipe
     * @param nomeEquipe Nome da equipe
     * @param usuario Usuário a ser adicionado
     * @return true se adicionado com sucesso
     */
    public boolean adicionarMembroEquipe(String nomeEquipe, Usuario usuario) {
        try {
            Equipe equipe = buscarPorNome(nomeEquipe);
            if (equipe != null) {
                return equipe.adicionarMembro(usuario);
            } else {
                System.out.println("Equipe '" + nomeEquipe + "' não encontrada!");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erro ao adicionar membro à equipe: " + e.getMessage());
            return false;
        }
    }

    /**
     * Remove membro de uma equipe
     * @param nomeEquipe Nome da equipe
     * @param usuario Usuário a ser removido
     * @return true se removido com sucesso
     */
    public boolean removerMembroEquipe(String nomeEquipe, Usuario usuario) {
        try {
            Equipe equipe = buscarPorNome(nomeEquipe);
            if (equipe != null) {
                return equipe.removerMembro(usuario);
            } else {
                System.out.println("Equipe '" + nomeEquipe + "' não encontrada!");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erro ao remover membro da equipe: " + e.getMessage());
            return false;
        }
    }

    /**
     * Lista equipes que um usuário participa
     * @param usuario Usuário a ser buscado
     * @return Lista de equipes do usuário
     */
    public ArrayList<Equipe> listarEquipesDoUsuario(Usuario usuario) {
        ArrayList<Equipe> equipesDoUsuario = new ArrayList<>();
        
        for (Equipe equipe : equipes) {
            if (equipe.getMembros().contains(usuario)) {
                equipesDoUsuario.add(equipe);
            }
        }
        
        return equipesDoUsuario;
    }

    /**
     * Lista equipes por tamanho mínimo
     * @param tamanhoMinimo Tamanho mínimo da equipe
     * @return Lista de equipes com pelo menos o tamanho especificado
     */
    public ArrayList<Equipe> listarEquipesPorTamanho(int tamanhoMinimo) {
        ArrayList<Equipe> equipesGrandes = new ArrayList<>();
        
        for (Equipe equipe : equipes) {
            if (equipe.tamanho() >= tamanhoMinimo) {
                equipesGrandes.add(equipe);
            }
        }
        
        return equipesGrandes;
    }

    /**
     * Lista equipes que têm determinado tipo de membro
     * @param tipoUsuario Classe do tipo de usuário
     * @return Lista de equipes que têm pelo menos um membro do tipo especificado
     */
    public ArrayList<Equipe> listarEquipesComTipoMembro(Class<? extends Usuario> tipoUsuario) {
        ArrayList<Equipe> equipesComTipo = new ArrayList<>();
        
        for (Equipe equipe : equipes) {
            if (equipe.temMembroDoTipo(tipoUsuario)) {
                equipesComTipo.add(equipe);
            }
        }
        
        return equipesComTipo;
    }

    /**
     * Remove uma equipe do sistema
     * @param idEquipe ID da equipe a ser removida
     * @return true se removida com sucesso
     */
    public boolean removerEquipe(int idEquipe) {
        try {
            Equipe equipe = buscarPorId(idEquipe);
            if (equipe != null) {
                equipes.remove(equipe);
                System.out.println("Equipe '" + equipe.getNome() + "' removida com sucesso!");
                return true;
            } else {
                System.out.println("Equipe com ID " + idEquipe + " não encontrada!");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erro ao remover equipe: " + e.getMessage());
            return false;
        }
    }

    /**
     * Atualiza informações de uma equipe
     * @param idEquipe ID da equipe
     * @param novoNome Novo nome (null para manter atual)
     * @param novaDescricao Nova descrição (null para manter atual)
     * @return true se atualizada com sucesso
     */
    public boolean atualizarEquipe(int idEquipe, String novoNome, String novaDescricao) {
        try {
            Equipe equipe = buscarPorId(idEquipe);
            if (equipe != null) {
                if (novoNome != null && !novoNome.trim().isEmpty()) {
                    equipe.setNome(novoNome);
                }
                if (novaDescricao != null && !novaDescricao.trim().isEmpty()) {
                    equipe.setDescricao(novaDescricao);
                }
                System.out.println("Equipe atualizada com sucesso!");
                return true;
            } else {
                System.out.println("Equipe não encontrada!");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar equipe: " + e.getMessage());
            return false;
        }
    }

    /**
     * Gera relatório completo das equipes
     */
    public void gerarRelatorio() {
        System.out.println("RELATÓRIO COMPLETO DE EQUIPES");
        System.out.println("═".repeat(60));
        
        int total = equipes.size();
        if (total == 0) {
            System.out.println("Nenhuma equipe cadastrada para gerar relatório.");
            return;
        }

        // Estatísticas gerais
        int totalMembros = 0;
        int equipesVazias = 0;
        int maiorEquipe = 0;
        String nomeMaiorEquipe = "";

        for (Equipe equipe : equipes) {
            int tamanho = equipe.tamanho();
            totalMembros += tamanho;
            
            if (tamanho == 0) {
                equipesVazias++;
            }
            
            if (tamanho > maiorEquipe) {
                maiorEquipe = tamanho;
                nomeMaiorEquipe = equipe.getNome();
            }
        }

        double mediaMembros = total > 0 ? (double) totalMembros / total : 0;

        System.out.println("ESTATÍSTICAS GERAIS:");
        System.out.println("Total de equipes: " + total);
        System.out.println("Total de membros: " + totalMembros);
        System.out.println("Média de membros por equipe: " + String.format("%.1f", mediaMembros));
        System.out.println("Equipes vazias: " + equipesVazias);
        System.out.println("Maior equipe: " + nomeMaiorEquipe + " (" + maiorEquipe + " membros)");

        // Distribuição por tamanho
        System.out.println("\nDISTRIBUIÇÃO POR TAMANHO:");
        int pequenas = 0, medias = 0, grandes = 0;
        for (Equipe equipe : equipes) {
            int tamanho = equipe.tamanho();
            if (tamanho <= 3) pequenas++;
            else if (tamanho <= 7) medias++;
            else grandes++;
        }
        
        System.out.println("┌─────────────────┬─────────┬─────────────┐");
        System.out.println("│ Tamanho         │ Qtd     │ Percentual  │");
        System.out.println("├─────────────────┼─────────┼─────────────┤");
        System.out.printf("│ %-15s │ %-7d │ %-11s │%n", "Pequenas (1-3)", pequenas, 
                         String.format("%.1f%%", (pequenas * 100.0 / total)));
        System.out.printf("│ %-15s │ %-7d │ %-11s │%n", "Médias (4-7)", medias, 
                         String.format("%.1f%%", (medias * 100.0 / total)));
        System.out.printf("│ %-15s │ %-7d │ %-11s │%n", "Grandes (8+)", grandes, 
                         String.format("%.1f%%", (grandes * 100.0 / total)));
        System.out.printf("│ %-15s │ %-7d │ %-11s │%n", "Vazias", equipesVazias, 
                         String.format("%.1f%%", (equipesVazias * 100.0 / total)));
        System.out.println("└─────────────────┴─────────┴─────────────┘");

        // Top 5 equipes por tamanho
        System.out.println("\nTOP 5 EQUIPES POR TAMANHO:");
        ArrayList<Equipe> equipesOrdenadas = new ArrayList<>(equipes);
        // Ordenação simples por tamanho (bubble sort para demonstração)
        for (int i = 0; i < equipesOrdenadas.size() - 1; i++) {
            for (int j = 0; j < equipesOrdenadas.size() - i - 1; j++) {
                if (equipesOrdenadas.get(j).tamanho() < equipesOrdenadas.get(j + 1).tamanho()) {
                    Equipe temp = equipesOrdenadas.get(j);
                    equipesOrdenadas.set(j, equipesOrdenadas.get(j + 1));
                    equipesOrdenadas.set(j + 1, temp);
                }
            }
        }

        int limite = Math.min(5, equipesOrdenadas.size());
        for (int i = 0; i < limite; i++) {
            Equipe equipe = equipesOrdenadas.get(i);
            System.out.println((i + 1) + ". " + equipe.getNome() + 
                             " - " + equipe.tamanho() + " membros");
        }

        // Análise de composição
        System.out.println("\nANÁLISE DE COMPOSIÇÃO:");
        analisarComposicaoEquipes();

        System.out.println("═".repeat(60));
        System.out.println();
    }

    /**
     * Método auxiliar para analisar composição das equipes
     */
    private void analisarComposicaoEquipes() {
        int equipesComGerente = 0;
        int equipesComAdmin = 0;
        int equipesBalanceadas = 0;

        for (Equipe equipe : equipes) {
            boolean temGerente = equipe.temMembroDoTipo(Gerente.class);
            boolean temAdmin = equipe.temMembroDoTipo(Administrador.class);
            boolean temColaborador = equipe.temMembroDoTipo(Colaborador.class);

            if (temGerente) equipesComGerente++;
            if (temAdmin) equipesComAdmin++;
            if (temGerente && temColaborador) equipesBalanceadas++;
        }

        System.out.println("• Equipes com gerente: " + equipesComGerente);
        System.out.println("• Equipes com administrador: " + equipesComAdmin);
        System.out.println("• Equipes balanceadas (gerente + colaboradores): " + equipesBalanceadas);
    }

    /**
     * Busca equipes por palavra-chave no nome ou descrição
     * @param palavraChave Palavra a ser buscada
     * @return Lista de equipes encontradas
     */
    public ArrayList<Equipe> buscarPorPalavraChave(String palavraChave) {
        ArrayList<Equipe> encontradas = new ArrayList<>();
        String busca = palavraChave.toLowerCase().trim();

        for (Equipe equipe : equipes) {
            if (equipe.getNome().toLowerCase().contains(busca) ||
                equipe.getDescricao().toLowerCase().contains(busca)) {
                encontradas.add(equipe);
            }
        }

        return encontradas;
    }

    /**
     * Verifica se um usuário já participa de alguma equipe
     * @param usuario Usuário a ser verificado
     * @return true se participa de pelo menos uma equipe
     */
    public boolean usuarioParticipaDe(Usuario usuario) {
        return !listarEquipesDoUsuario(usuario).isEmpty();
    }

    /**
     * Calcula eficiência das equipes (baseado no número de membros)
     * @return Percentual médio de ocupação das equipes
     */
    public double calcularEficienciaMedia() {
        if (equipes.isEmpty()) {
            return 0.0;
        }

        int tamanhoIdeal = 5; // Considerando 5 como tamanho ideal de equipe
        double somaEficiencia = 0.0;

        for (Equipe equipe : equipes) {
            double eficiencia = Math.min(100.0, (equipe.tamanho() * 100.0) / tamanhoIdeal);
            somaEficiencia += eficiencia;
        }

        return somaEficiencia / equipes.size();
    }

    /**
     * Getter para lista de equipes (retorna cópia para preservar encapsulamento)
     * @return Cópia da lista de equipes
     */
    public ArrayList<Equipe> listarEquipesList() {
        return new ArrayList<>(equipes);
    }

    /**
     * Obtém total de equipes cadastradas
     * @return Número total de equipes
     */
    public int getTotalEquipes() {
        return equipes.size();
    }

    /**
     * Obtém total de membros em todas as equipes
     * @return Número total de membros
     */
    public int getTotalMembros() {
        int total = 0;
        for (Equipe equipe : equipes) {
            total += equipe.tamanho();
        }
        return total;
    }

    /**
     * Obtém estatísticas rápidas
     * @return String com estatísticas resumidas
     */
    public String obterEstatisticasRapidas() {
        return String.format("Equipes: %d | Membros: %d | Média: %.1f | Eficiência: %.1f%%",
                equipes.size(),
                getTotalMembros(),
                equipes.size() > 0 ? (double) getTotalMembros() / equipes.size() : 0,
                calcularEficienciaMedia());
    }
}