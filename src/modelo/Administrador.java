package modelo;

/**
 * Classe que representa um usuário administrador
 * Herda de Usuario e implementa comportamentos específicos (herança e polimorfismo)
 * 
 * @author Equipe AutoMax
 * @version 1.0
 */
public class Administrador extends Usuario {

    /**
     * Construtor da classe Administrador
     * @param nome Nome completo do administrador
     * @param cpf CPF do administrador
     * @param email Email do administrador
     * @param login Login para acesso ao sistema
     * @param senha Senha para acesso ao sistema
     */
    public Administrador(String nome, String cpf, String email, String login, String senha) {
        super(nome, cpf, email, login, senha); // Chama o construtor da classe pai
    }

    /**
     * Implementação do método abstrato da classe pai (polimorfismo)
     * Exibe o perfil específico do administrador
     */
    @Override
    public void exibirPerfil() {
        System.out.println("Perfil: Administrador - " + getNome());
        System.out.println("Permissões: Acesso total ao sistema");
    }

    /**
     * Método específico do administrador para gerenciar usuários
     * @param acao Ação a ser realizada (criar, editar, excluir)
     * @param usuario Usuário alvo da ação
     */
    public void gerenciarUsuario(String acao, Usuario usuario) {
        System.out.println("Administrador " + getNome() + " executando ação: " + acao);
        if (usuario != null) {
            System.out.println("Usuário alvo: " + usuario.getNome());
        }
    }

    /**
     * Método específico para administradores gerenciarem o sistema
     */
    public void gerenciarSistema() {
        System.out.println("🔧 " + getNome() + " acessando configurações do sistema...");
    }

    /**
     * Sobrescrita do método exibirInformacoes para incluir informações específicas
     */
    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes(); // Chama o método da classe pai
        System.out.println("Privilégios: Administrador do Sistema");
        System.out.println("Pode: Gerenciar usuários, projetos, equipes e configurações");
    }

    /**
     * Método para validar se pode executar operações administrativas
     * @return true sempre, pois administrador tem acesso total
     */
    public boolean podeGerenciarUsuarios() {
        return true;
    }

    /**
     * Método para validar se pode acessar relatórios administrativos
     * @return true sempre, pois administrador tem acesso total
     */
    public boolean podeAcessarRelatoriosAdmin() {
        return true;
    }

    /**
     * Método para resetar senhas de outros usuários
     * @param usuario Usuário que terá a senha resetada
     * @param novaSenha Nova senha a ser definida
     */
    public void resetarSenhaUsuario(Usuario usuario, String novaSenha) {
        if (usuario != null) {
            usuario.setSenha(novaSenha);
            System.out.println("Administrador " + getNome() + " resetou a senha de " + usuario.getNome());
        }
    }

    /**
     * Método para gerar relatório completo do sistema
     */
    public void gerarRelatorioSistema() {
        System.out.println("RELATÓRIO COMPLETO DO SISTEMA");
        System.out.println("Gerado por: Administrador " + getNome());
        System.out.println("Data: " + java.time.LocalDate.now());
        System.out.println("═".repeat(50));
    }
}