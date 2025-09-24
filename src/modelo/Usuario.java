package modelo;

/**
 * Classe abstrata que representa um usuário do sistema
 * Demonstra conceitos de encapsulamento e herança
 * 
 * @author Equipe AutoMax
 * @version 1.0
 */
public abstract class Usuario {
    // Atributos privados (encapsulamento)
    private String nome;
    private String cpf;
    private String email;
    private String login;
    private String senha;

    /**
     * Construtor parametrizado
     * @param nome Nome completo do usuário
     * @param cpf CPF do usuário
     * @param email Email do usuário
     * @param login Login para acesso ao sistema
     * @param senha Senha para acesso ao sistema
     */
    public Usuario(String nome, String cpf, String email, String login, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.login = login;
        this.senha = senha;
    }

    // Métodos getters e setters (encapsulamento)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Método abstrato que será implementado pelas classes filhas (polimorfismo)
     * Cada tipo de usuário exibirá seu perfil de forma diferente
     */
    public abstract void exibirPerfil();

    /**
     * Método para exibir informações detalhadas do usuário
     * Pode ser sobrescrito pelas classes filhas
     */
    public void exibirInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Email: " + email);
        System.out.println("Login: " + login);
        System.out.println("Tipo: " + this.getClass().getSimpleName());
    }

    /**
     * Método para validar se a senha está correta
     * @param senhaInformada Senha informada para verificação
     * @return true se a senha estiver correta, false caso contrário
     */
    public boolean validarSenha(String senhaInformada) {
        return this.senha.equals(senhaInformada);
    }

    /**
     * Método sobrescrito toString para representação em string do objeto
     */
    @Override
    public String toString() {
        return nome + " (" + this.getClass().getSimpleName() + ")";
    }

    /**
     * Método sobrescrito equals para comparação de objetos
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario usuario = (Usuario) obj;
        return login.equals(usuario.login);
    }

    /**
     * Método sobrescrito hashCode
     */
    @Override
    public int hashCode() {
        return login.hashCode();
    }
}