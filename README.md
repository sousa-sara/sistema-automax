# Sistema de Gestão de Projetos - Loja de Carros AutoMax

## 🎯 Objetivo do Projeto
Sistema desenvolvido em Java para gerenciar projetos, equipes e usuários de uma loja de carros, aplicando todos os conceitos fundamentais de **Programação Orientada a Objetos (POO)** conforme especificado na UC de Programação de Soluções Computacionais.

## 🚀 Funcionalidades Implementadas

### ✅ Gestão de Usuários
- Cadastro completo com nome, CPF, email, login e senha
- Três tipos de perfil: **Administrador**, **Gerente** e **Colaborador**
- Sistema de autenticação seguro
- Controle de permissões por tipo de usuário

### ✅ Gestão de Projetos
- Cadastro com nome, descrição, datas e status
- Status disponíveis: Planejado, Em Andamento, Concluído, Cancelado, Pausado
- Vinculação de gerente responsável
- Acompanhamento de progresso
- Relatórios detalhados

### ✅ Gestão de Equipes
- Criação de equipes com nome e descrição
- Adição/remoção de membros
- Análise de composição por tipo de usuário
- Estatísticas de distribuição e eficiência

### ✅ Sistema de Relatórios
- Relatórios estatísticos de usuários, projetos e equipes
- Gráficos em formato texto
- Análises de produtividade e progresso

## 🏗️ Conceitos de POO Aplicados

### 🔒 **Encapsulamento**
- Todos os atributos são **privados**
- Acesso controlado via **getters e setters**
- Métodos auxiliares privados para operações internas

### 🏛️ **Herança**
```java
Usuario (classe pai)
├── Administrador (herda de Usuario)
├── Gerente (herda de Usuario)  
└── Colaborador (herda de Usuario)
```

### 🎭 **Polimorfismo**
- **Sobrescrita** do método `exibirPerfil()` em cada tipo de usuário
- **Sobrecarga** de construtores em várias classes
- Métodos `toString()`, `equals()` e `hashCode()` sobrescritos

### 📦 **Coleções**
- **ArrayList** para gerenciar listas de usuários, projetos e equipes
- **ArrayList** para membros de equipes e tarefas de colaboradores

### ⚠️ **Tratamento de Exceções**
- Blocos **try/catch** em operações críticas
- Validação de entrada de dados (CPF, emails, etc.)
- Tratamento de erros de conversão e acesso a coleções

## 🔧 Como Executar

### Pré-requisitos
- Java JDK 8 ou superior
- IDE Java (IntelliJ IDEA, Eclipse, VS Code, etc.)

### Passos para Execução
1. **Clone o repositório:**
   ```bash
   git clone [url-do-repositorio]
   cd sistema-automax
   ```

2. **Abra o projeto em sua IDE preferida**

3. **Execute a classe principal:**
   ```bash
   java main.Main
   ```

4. **Faça login com as credenciais padrão:**
   - **Login:** admin
   - **Senha:** 123456

5. **Explore o sistema através do menu interativo!**

## 👥 Equipe de Desenvolvimento
- **[SARA GABRIELLE SOUSA ALMEIDA]** - RA: [Seu RA]
- **[MAYARA RAMOS PIRES]** - RA: [RA do Colega]
- **[RENATA BARONI DE PAULA]** - RA: [RA do Colega]
- **[LUCA MARTINS DE SOUZA CUSTODIO]** - RA: [RA do Colega]
- **[ROBSON CAETANO REIS DE OLIVEIRA]** - RA: [RA do Colega]
- **[RAQUEL BRITO DOS SANTOS]** - RA: [RA do Colega]

## 📁 Estrutura do Projeto

```
src/
├── main/
│   └── Main.java                    # Classe principal com menu
├── modelo/                          # Classes de domínio
│   ├── Usuario.java                 # Classe pai abstrata
│   ├── Administrador.java           # Herda de Usuario
│   ├── Gerente.java                 # Herda de Usuario  
│   ├── Colaborador.java             # Herda de Usuario
│   ├── Projeto.java                 # Entidade Projeto
│   └── Equipe.java                  # Entidade Equipe
└── servico/                         # Classes de serviço
    ├── GerenciadorUsuario.java      # CRUD de usuários
    ├── GerenciadorProjeto.java      # CRUD de projetos
    └── GerenciadorEquipe.java       # CRUD de equipes
```

## 🎮 Exemplo de Uso

### Menu Principal
```
=== SISTEMA AUTOMAX - GESTÃO DE PROJETOS ===
1. Gerenciar Usuários
2. Gerenciar Projetos  
3. Gerenciar Equipes
4. Relatórios
5. Meu Perfil
6. Logout

Escolha uma opção: 
```

### Funcionalidades por Perfil

#### 🔧 **Administrador**
- ✅ Gerenciar todos os usuários
- ✅ Criar e gerenciar projetos
- ✅ Criar e gerenciar equipes
- ✅ Acessar todos os relatórios

#### 👨‍💼 **Gerente**
- ✅ Criar projetos
- ✅ Gerenciar equipes
- ✅ Visualizar relatórios de projetos
- ✅ Acompanhar seus projetos

#### 👤 **Colaborador**
- ✅ Visualizar projetos
- ✅ Ver suas tarefas
- ✅ Enviar relatórios de progresso
- ✅ Participar de equipes

## 🎯 Cenários da Loja de Carros

### Exemplos de Projetos:
- 🚗 **"Campanha Verão 2025"** - Promoção de vendas
- 🏢 **"Reforma Showroom"** - Modernização das instalações  
- 📚 **"Treinamento Vendedores"** - Capacitação da equipe
- 📱 **"App de Agendamento"** - Digitização de processos

### Exemplos de Equipes:
- 💼 **"Vendas"** - Gerente + Vendedores + Assistentes
- 🔧 **"Pós-Venda"** - Supervisor + Técnicos
- 📈 **"Marketing"** - Coordenador + Designers + Social Media

## 📊 Relatórios Disponíveis

### Relatório de Usuários
- Distribuição por tipo de perfil
- Estatísticas de domínio de email
- Gráfico de percentuais

### Relatório de Projetos  
- Status dos projetos (gráfico em tabela)
- Projetos por gerente
- Progresso médio geral
- Projetos em destaque

### Relatório de Equipes
- Distribuição por tamanho
- Top 5 maiores equipes
- Análise de composição
- Eficiência média

## 🛠️ Tecnologias Utilizadas
- ☕ **Java 8+** - Linguagem principal
- 📋 **Scanner** - Entrada de dados via console
- 🗂️ **ArrayList** - Estruturas de dados
- 🎯 **POO** - Paradigma de programação

## 🔍 Validações Implementadas
- ✅ Validação básica de CPF
- ✅ Verificação de email único
- ✅ Controle de login duplicado
- ✅ Validação de status de projetos
- ✅ Tratamento de erros de entrada

## 📈 Melhorias Futuras (Opcionais)
- 🗄️ Persistência em banco de dados MySQL
- 🖥️ Interface gráfica com JavaFX/Swing
- 📧 Sistema de notificações
- 📅 Integração com calendário
- 📊 Relatórios em PDF

## 📝 Observações Técnicas

### Padrões de Código Adotados:
- **CamelCase** para nomes de variáveis e métodos
- **PascalCase** para nomes de classes
- **Comentários JavaDoc** em todos os métodos públicos
- **Tratamento de exceções** em operações críticas
- **Encapsulamento** rigoroso de dados

### Design Patterns Utilizados:
- **Service Layer** - Classes de serviço para lógica de negócio
- **Data Transfer** - Métodos que retornam cópias de listas
- **Factory Method** - Criação de diferentes tipos de usuário

## 🎓 Critérios Atendidos

| Critério | Status | Implementação |
|----------|--------|---------------|
| 3+ Classes próprias | ✅ | 6 classes modelo + 3 serviços |
| Encapsulamento | ✅ | Atributos privados + getters/setters |
| Construtores | ✅ | Todos com construtores parametrizados |
| Herança | ✅ | Usuario → Admin/Gerente/Colaborador |
| Polimorfismo | ✅ | Override exibirPerfil() + sobrecarga |
| Coleções | ✅ | ArrayList em todas as classes |
| Interação Console | ✅ | Scanner + menus interativos |
| Try/Catch | ✅ | Validações e operações críticas |
| Documentação | ✅ | README + comentários JavaDoc |

---

## 📞 Suporte
Para dúvidas sobre o projeto, entre em contato com a equipe de desenvolvimento.

**Desenvolvido com ❤️ para a UC de Programação de Soluções Computacionais**
