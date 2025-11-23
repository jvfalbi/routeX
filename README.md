# 📦 Sistema de Cadastro de Destinatários para Logística (RouteX)

## 1. Sobre o Projeto

Nome do projeto: **Sistema de Cadastro de Destinatários para Logística (RouteX)**

Resumo (5–8 linhas):
O projeto é uma aplicação web **segura** e **estruturada** focada no gerenciamento de cadastros essenciais para operações de logística. A funcionalidade principal é o CRUD (Cadastro, Listagem, Edição, Deleção) de **Destinatários**, que estabelece relacionamentos de **Muitos para Um (N:1)** com **Funcionários (Responsáveis)** e **Veículos** da frota. O sistema utiliza **Spring Security** para autenticação e uma arquitetura em camadas (Controller, Service, Repository) para garantir **manutenibilidade** e **escalabilidade**. O frontend utiliza **Thymeleaf** com estilização **CSS** para uma interface profissional.

---

## 2. Tecnologias

- **Java 17+**
- **Spring Boot 3.4.11** (Web, Data JPA, Security, Validation, DevTools)
- **Thymeleaf** (Frontend Web)
- **MySQL 8+** (Base de Dados Principal)
- **H2 Database** (Para ambiente de testes/desenvolvimento)
- **Maven**

---

## 3. Arquitetura (resumo)

- **Camadas:** O projeto segue o padrão MVC com a arquitetura em camadas: **Controller** (web) → **Service** (lógica de negócio) → **Repository** (acesso a dados).
- **Entidades Principais:** `Destinatario`, `Funcionario`, `Veiculo`.
- **Relacionamentos:** A entidade `Destinatario` é o ponto focal, possuindo dois relacionamentos **Muitos para Um (N:1)** com `Funcionario` (`responsavel`) e `Veiculo`.
- **Validação:** Uso do **Jakarta Validation** (`@NotEmpty`) para validação de dados de entrada.
- **Segurança:** Autenticação baseada em formulário via **Spring Security**.

---

## 4. Requisitos de Ambiente

- JDK 17+
- Maven 3.8+
- MySQL 8+

---

## 5. Configuração do Banco

Crie a base de dados chamada `Logistica` (ou ajuste o `application.properties` para usar outra base existente).

---

## 6. Configuração da Aplicação

Ajuste o arquivo `src/main/resources/application.properties` com suas credenciais do MySQL. Note que o H2 em memória é usado durante a fase de testes.


Swagger UI: URL a ser definida após implementação da API REST


7- ```properties
# CONFIGURAÇÃO DE BANCO DE DADOS (Exemplo com MySQL)
spring.datasource.url=jdbc:mysql://localhost:3306/Logistica?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=SUA_SENHA_MYSQL
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# CONFIGURAÇÃO JPA/HIBERNATE
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# CONFIGURAÇÃO THYMELEAF/SERVIDOR
server.port=8080 
spring.thymeleaf.cache=false

# Garanta que o MySQL está ativo e configurado (Seção 6)
# Execute a aplicação Spring Boot
mvn spring-boot:run
#Acesso direto:http://localhost:8080/login

8. Seeds de Usuários (Admin e User)
Os usuários são definidos em memória na classe SecurityConfig.java.

Usuário	Senha	Papel
admin	admin123	ADMIN, USER
usuario	senha123	USER

Exportar para as Planilhas

9. Segurança (rotas e acesso)
Públicas: /login, /logout, /css/**, /js/**, /images/**

Autenticadas (USER/ADMIN): /destinatarios/**, /funcionarios/**, /veiculos/** (Todas as rotas de CRUD)          Login por formulário em /login; logout padrão (POST para /logout).     

10. Rotas Web (exemplo)
/destinatarios (Lista principal e acesso ao CRUD)

/funcionarios (CRUD e lista de responsáveis)

/veiculos (CRUD e lista de veículos)


19. Autores
Integrante	                       RA	                                 Principais contribuições
Diego Lima Dantas	           3123102326	   Modelagem de Dados (Destinatario, Funcionario, Veiculo) e Estrutura de Repositórios.
Cauan Dantas Braga	           3023101661	   Implementação do CRUD completo para a entidade Destinatario (Controller e Service).
João Vitor Falbi	           3023100887	   Implementação do CRUD completo para a entidade Funcionario e Veiculo (Controller e Services).
Diego Lima Dantas	           3123102326	   Implementação completa do Spring Security (Configuração, Usuários em Memória, Rotas) e README.md.
Calebe Fernandes Ramos	           3023200406	   Desenvolvimento do Frontend Thymeleaf (Criação de form.html e lista.html) e Integração de Dados.
João Vitor Falbi	           3023100887	   Aplicação da Estilização(Aparência Profissional), Configuração do Maven e application.properties.
Guilherme Camelo Pimenta           3023102974      Desenvolvimento do vídeo demonstrando o funcionamento da aplicação e tester.
Antônio Jaibas Fernandes Rodrigues 3025201437      Documentação final do projeto.
