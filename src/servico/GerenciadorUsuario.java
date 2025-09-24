package servico;

import modelo.*;
import java.util.ArrayList;

/**
 * Classe de serviço para gerenciar usuários do sistema
 * Demonstra uso de coleções e tratamento de exceções
 * 
 * @author Equipe AutoMax
 * @version 1.0
 */
public class GerenciadorUsuario {
    // Coleção para armazenar todos os usuários do sistema
    private ArrayList<Usuario> usuarios;

    /**
     * Construtor do gerenciador de usuários
     */
    public GerenciadorUsuario() {
        this.usuarios = new ArrayList<>();
    }

    /**
     * Adiciona um novo usuário ao sistema
     * @param usuario Usuario a ser adicionado
     * @return true se adicionado com sucesso, false se login já existe
     */
    public boolean adicionarUsuario(Usuario usuario) {
        try {
            // Verificar se já existe usuário com o mesmo login
            if (buscarPorLogin(usuario.getLogin()) != null) {
                System.out.println("Já existe um usuário com o login: " + usuario.getLogin());
                return false;
            }

            // Validar CPF (implementação básica)
            //if (!validarCpf(usuario.getCpf())) {
             //   System.out.println("CPF inválido: " + usuario.getCpf());
            //    return false;
            //}

            usuarios.add(usuario);
            System.out.println("Usuário " + usuario.getNome() + " adicionado com sucesso!");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao adicionar usuário: " + e.getMessage());
            return false;
        }
    }

    /**
     * Autentica um usuário no sistema
     * @param login Login do usuário
     * @param senha Senha do usuário
     * @return Usuario autenticado ou null se credenciais inválidas
     */
    public Usuario autenticar(String login, String senha) {
        try {
            for (Usuario usuario : usuarios) {
                if (usuario.getLogin().equals(login) && usuario.validarSenha(senha)) {
                    return usuario;
                }
            }
            return null; // Credenciais inválidas
        } catch (Exception e) {
            System.out.println("Erro na autenticação: " + e.getMessage());
            return null;
        }
    }

    /**
     * Busca usuário por login
     * @param login Login a ser buscado
     * @return Usuario encontrado ou null
     */
    public Usuario buscarPorLogin(String login) {
        try {
            for (Usuario usuario : usuarios) {
                if (usuario.getLogin().equals(login)) {
                    return usuario;
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
            return null;
        }
    }

    /**
     * Busca usuário por CPF
     * @param cpf CPF a ser buscado
     * @return Usuario encontrado ou null
     */
    public Usuario buscarPorCpf(String cpf) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Lista todos os usuários do sistema
     */
    public void listarUsuarios() {
        System.out.println("LISTA DE USUÁRIOS DO SISTEMA");
        System.out.println("Total de usuários: " + usuarios.size());
        System.out.println("═".repeat(60));

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado no sistema.");
        } else {
            for (int i = 0; i < usuarios.size(); i++) {
                Usuario usuario = usuarios.get(i);
                System.out.println((i + 1) + ". " + usuario.getNome());
                System.out.println("   Tipo: " + usuario.getClass().getSimpleName());
                System.out.println("   Email: " + usuario.getEmail());
                System.out.println("   Login: " + usuario.getLogin());
                System.out.println("   ─".repeat(40));
            }
        }
        System.out.println();
    }

    /**
     * Lista apenas os gerentes do sistema
     * @return Lista de gerentes
     */
    public ArrayList<Gerente> listarGerentes() {
        ArrayList<Gerente> gerentes = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Gerente) {
                gerentes.add((Gerente) usuario);
            }
        }
        return gerentes;
    }

    /**
     * Lista apenas os colaboradores do sistema
     * @return Lista de colaboradores
     */
    public ArrayList<Colaborador> listarColaboradores() {
        ArrayList<Colaborador> colaboradores = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Colaborador) {
                colaboradores.add((Colaborador) usuario);
            }
        }
        return colaboradores;
    }

    /**
     * Lista apenas os administradores do sistema
     * @return Lista de administradores
     */
    public ArrayList<Administrador> listarAdministradores() {
        ArrayList<Administrador> administradores = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Administrador) {
                administradores.add((Administrador) usuario);
            }
        }
        return administradores;
    }

    /**
     * Remove um usuário do sistema
     * @param login Login do usuário a ser removido
     * @return true se removido com sucesso, false caso contrário
     */
    public boolean removerUsuario(String login) {
        try {
            Usuario usuario = buscarPorLogin(login);
            if (usuario != null) {
                usuarios.remove(usuario);
                System.out.println("Usuário " + usuario.getNome() + " removido com sucesso!");
                return true;
            } else {
                System.out.println("Usuário com login '" + login + "' não encontrado!");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erro ao remover usuário: " + e.getMessage());
            return false;
        }
    }

    /**
     * Atualiza informações de um usuário
     * @param login Login do usuário a ser atualizado
     * @param novoEmail Novo email
     * @param novaSenha Nova senha
     * @return true se atualizado com sucesso
     */
    public boolean atualizarUsuario(String login, String novoEmail, String novaSenha) {
        try {
            Usuario usuario = buscarPorLogin(login);
            if (usuario != null) {
                if (novoEmail != null && !novoEmail.trim().isEmpty()) {
                    usuario.setEmail(novoEmail);
                }
                if (novaSenha != null && !novaSenha.trim().isEmpty()) {
                    usuario.setSenha(novaSenha);
                }
                System.out.println("Dados do usuário " + usuario.getNome() + " atualizados!");
                return true;
            } else {
                System.out.println("Usuário não encontrado!");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
            return false;
        }
    }

    /**
     * Gera relatório estatístico dos usuários
     */
    public void gerarRelatorio() {
        System.out.println("RELATÓRIO DE USUÁRIOS");
        System.out.println("═".repeat(50));
        
        int totalAdmins = listarAdministradores().size();
        int totalGerentes = listarGerentes().size();
        int totalColaboradores = listarColaboradores().size();
        int total = usuarios.size();

        System.out.println("Total de usuários: " + total);
        System.out.println("Administradores: " + totalAdmins + 
                         " (" + String.format("%.1f", (totalAdmins * 100.0 / total)) + "%)");
        System.out.println("Gerentes: " + totalGerentes + 
                         " (" + String.format("%.1f", (totalGerentes * 100.0 / total)) + "%)");
        System.out.println("Colaboradores: " + totalColaboradores + 
                         " (" + String.format("%.1f", (totalColaboradores * 100.0 / total)) + "%)");
        
        System.out.println("\nESTATÍSTICAS ADICIONAIS:");
        System.out.println("• Usuários por domínio de email:");
        contarPorDominioEmail();
        
        System.out.println("═".repeat(50));
        System.out.println();
    }

    /**
     * Conta usuários por domínio de email (método auxiliar)
     */
    private void contarPorDominioEmail() {
        ArrayList<String> dominios = new ArrayList<>();
        ArrayList<Integer> contadores = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            String email = usuario.getEmail();
            String dominio = email.substring(email.indexOf("@") + 1);
            
            int index = dominios.indexOf(dominio);
            if (index >= 0) {
                contadores.set(index, contadores.get(index) + 1);
            } else {
                dominios.add(dominio);
                contadores.add(1);
            }
        }

        for (int i = 0; i < dominios.size(); i++) {
            System.out.println("  " + dominios.get(i) + ": " + contadores.get(i) + " usuários");
        }
    }

    /**
     * Validação básica de CPF (método com tratamento de exceções)
     * @param cpf CPF a ser validado
     * @return true se válido, false caso contrário
     */
    private boolean validarCpf(String cpf) {
        try {
            if (cpf == null || cpf.trim().isEmpty()) {
                return false;
            }

            // Remove pontos e traços
            cpf = cpf.replaceAll("[.-]", "");

            // Verifica se tem 11 dígitos
            if (cpf.length() != 11) {
                return false;
            }

            // Verifica se todos os dígitos são iguais
            boolean todosIguais = true;
            for (int i = 1; i < cpf.length(); i++) {
                if (cpf.charAt(i) != cpf.charAt(0)) {
                    todosIguais = false;
                    break;
                }
            }

            if (todosIguais) {
                return false;
            }

            // Validação simples - em um sistema real seria mais rigorosa
            return cpf.matches("\\d{11}");

        } catch (Exception e) {
            System.out.println("Erro na validação do CPF: " + e.getMessage());
            return false;
        }
    }

    /**
     * Busca usuários por nome (busca parcial)
     * @param nome Nome ou parte do nome a ser buscado
     * @return Lista de usuários encontrados
     */
    public ArrayList<Usuario> buscarPorNome(String nome) {
        ArrayList<Usuario> encontrados = new ArrayList<>();
        String nomeBusca = nome.toLowerCase().trim();

        for (Usuario usuario : usuarios) {
            if (usuario.getNome().toLowerCase().contains(nomeBusca)) {
                encontrados.add(usuario);
            }
        }

        return encontrados;
    }

    /**
     * Verifica se existe pelo menos um administrador no sistema
     * @return true se existe pelo menos um admin
     */
    public boolean temAdministrador() {
        return !listarAdministradores().isEmpty();
    }

    /**
     * Obtém estatísticas rápidas do sistema
     * @return String com estatísticas resumidas
     */
    public String obterEstatisticasRapidas() {
        return String.format("Usuários: %d | Admins: %d | Gerentes: %d | Colaboradores: %d",
                usuarios.size(),
                listarAdministradores().size(),
                listarGerentes().size(),
                listarColaboradores().size());
    }

    /**
     * Getter para lista de usuários (retorna cópia para preservar encapsulamento)
     * @return Cópia da lista de usuários
     */
    public ArrayList<Usuario> listarUsuariosList() {
        return new ArrayList<>(usuarios);
    }

    /**
     * Obtém total de usuários cadastrados
     * @return Número total de usuários
     */
    public int getTotalUsuarios() {
        return usuarios.size();
    }
}