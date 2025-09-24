package modelo;

import java.util.ArrayList;

/**
 * Classe que representa uma equipe de trabalho
 * Demonstra uso de coleções (ArrayList) e relacionamentos entre classes
 * 
 * @author Equipe AutoMax
 * @version 1.0
 */
public class Equipe {
    // Atributos privados (encapsulamento)
    private String nome;
    private String descricao;
    private ArrayList<Usuario> membros; // Coleção para armazenar membros da equipe
    private static int contadorEquipes = 0; // Contador estático para IDs únicos
    private int id;

    /**
     * Construtor parametrizado da classe Equipe
     * @param nome Nome da equipe
     * @param descricao Descrição da equipe
     */
    public Equipe(String nome, String descricao) {
        this.id = ++contadorEquipes; // Incrementa contador e atribui ID único
        this.nome = nome;
        this.descricao = descricao;
        this.membros = new ArrayList<>(); // Inicializa a coleção
    }

    /**
     * Construtor sobrecarregado apenas com nome (sobrecarga)
     * @param nome Nome da equipe
     */
    public Equipe(String nome) {
        this(nome, "Equipe " + nome); // Chama o construtor principal
    }

    /**
     * Método para adicionar membro à equipe
     * @param usuario Usuário a ser adicionado como membro
     * @return true se adicionado com sucesso, false se já era membro
     */
    public boolean adicionarMembro(Usuario usuario) {
        try {
            if (usuario == null) {
                System.out.println("Usuário inválido!");
                return false;
            }

            if (membros.contains(usuario)) {
                System.out.println(" " + usuario.getNome() + " já é membro da equipe " + nome);
                return false;
            }

            membros.add(usuario);
            System.out.println(" " + usuario.getNome() + " adicionado à equipe " + nome);
            return true;
        } catch (Exception e) {
            System.out.println(" Erro ao adicionar membro: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para remover membro da equipe
     * @param usuario Usuário a ser removido
     * @return true se removido com sucesso, false caso contrário
     */
    public boolean removerMembro(Usuario usuario) {
        try {
            if (membros.remove(usuario)) {
                System.out.println("" + usuario.getNome() + " removido da equipe " + nome);
                return true;
            } else {
                System.out.println("" + usuario.getNome() + " não é membro da equipe " + nome);
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erro ao remover membro: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para listar todos os membros da equipe
     */
    public void listarMembros() {
        System.out.println("MEMBROS DA EQUIPE: " + nome.toUpperCase());
        System.out.println("Descrição: " + descricao);
        System.out.println("Total de membros: " + membros.size());
        System.out.println("─".repeat(40));

        if (membros.isEmpty()) {
            System.out.println("Nenhum membro cadastrado na equipe.");
        } else {
            for (int i = 0; i < membros.size(); i++) {
                Usuario membro = membros.get(i);
                System.out.println((i + 1) + ". " + membro.getNome() + 
                                 " (" + membro.getClass().getSimpleName() + ")");
                System.out.println("" + membro.getEmail());
            }
        }
        System.out.println("─".repeat(40));
    }

    /**
     * Método para buscar membro por nome
     * @param nome Nome do membro a ser buscado
     * @return Usuario encontrado ou null se não encontrado
     */
    public Usuario buscarMembroPorNome(String nome) {
        for (Usuario membro : membros) {
            if (membro.getNome().toLowerCase().contains(nome.toLowerCase())) {
                return membro;
            }
        }
        return null;
    }

    /**
     * Método para contar membros por tipo (Administrador, Gerente, Colaborador)
     */
    public void exibirEstatisticasMembros() {
        int administradores = 0;
        int gerentes = 0;
        int colaboradores = 0;

        for (Usuario membro : membros) {
            if (membro instanceof Administrador) {
                administradores++;
            } else if (membro instanceof Gerente) {
                gerentes++;
            } else if (membro instanceof Colaborador) {
                colaboradores++;
            }
        }

        System.out.println("ESTATÍSTICAS DA EQUIPE: " + nome);
        System.out.println("Administradores: " + administradores);
        System.out.println("Gerentes: " + gerentes);
        System.out.println("Colaboradores: " + colaboradores);
        System.out.println("Total: " + membros.size() + " membros");
        System.out.println();
    }

    /**
     * Método para verificar se a equipe tem um determinado tipo de usuário
     * @param tipoUsuario Classe do tipo de usuário (ex: Gerente.class)
     * @return true se tem pelo menos um membro do tipo especificado
     */
    public boolean temMembroDoTipo(Class<? extends Usuario> tipoUsuario) {
        for (Usuario membro : membros) {
            if (tipoUsuario.isInstance(membro)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método para obter todos os gerentes da equipe
     * @return Lista de gerentes na equipe
     */
    public ArrayList<Gerente> obterGerentes() {
        ArrayList<Gerente> gerentes = new ArrayList<>();
        for (Usuario membro : membros) {
            if (membro instanceof Gerente) {
                gerentes.add((Gerente) membro);
            }
        }
        return gerentes;
    }

    /**
     * Método para gerar relatório da equipe
     */
    public void gerarRelatorio() {
        System.out.println("RELATÓRIO DA EQUIPE");
        System.out.println("Nome: " + nome);
        System.out.println("ID: " + id);
        System.out.println("Descrição: " + descricao);
        exibirEstatisticasMembros();
    }

    /**
     * Método para verificar se a equipe está vazia
     * @return true se não tem membros, false caso contrário
     */
    public boolean estaVazia() {
        return membros.isEmpty();
    }

    /**
     * Método para obter o tamanho da equipe
     * @return Número de membros na equipe
     */
    public int tamanho() {
        return membros.size();
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

    /**
     * Getter que retorna uma cópia da lista de membros (preserva encapsulamento)
     * @return Cópia da lista de membros
     */
    public ArrayList<Usuario> getMembros() {
        return new ArrayList<>(membros);
    }

    public static int getContadorEquipes() {
        return contadorEquipes;
    }

    /**
     * Método sobrescrito toString
     */
    @Override
    public String toString() {
        return "Equipe #" + id + ": " + nome + " (" + membros.size() + " membros)";
    }

    /**
     * Método sobrescrito equals para comparação de equipes
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Equipe equipe = (Equipe) obj;
        return id == equipe.id;
    }

    /**
     * Método sobrescrito hashCode
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}