# Gestão Portuária

![Java](https://img.shields.io/badge/Java-17-blue)
![Jersey](https://img.shields.io/badge/Jersey-Framework-blue)
![Maven](https://img.shields.io/badge/Maven-Build-orange)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![JWT](https://img.shields.io/badge/JWT-Authentication-green)
![Hibernate Validator](https://img.shields.io/badge/Hibernate_Validator-Validation-red)
![ModelMapper](https://img.shields.io/badge/ModelMapper-Mapping-yellow)
![Flyway](https://img.shields.io/badge/Flyway-Database_Migration-blue)
![Jakarta Bean Validation](https://img.shields.io/badge/Jakarta_Bean_Validation-Validation-red)

## Descrição
O projeto é uma aplicação de gestão para o cadastro e rastreamento de contêineres e suas movimentações. Esta aplicação permite que os usuários autenticados (administradores e usuários comuns) gerenciem clientes, contêineres e suas respectivas movimentações de forma eficaz e segura.

## Funcionalidades

### Autenticação de Usuários
- Autenticação segura via JWT.
- Suporte para perfis de usuários `ADMIN` e `USER`.

### Gerenciamento de Clientes
- Cadastro, atualização, remoção e busca detalhada de clientes.
- Listagem paginada de clientes para melhor gerenciamento em grande escala.

### Gerenciamento de Contêineres
- Operações de cadastro, edição e exclusão de contêineres.
- Busca de contêineres por ID e listagem por cliente.
- Categorização dos contêineres por tipo, status e categoria de movimentação.

### Gerenciamento de Movimentações
- Registro e gerenciamento de movimentações associadas a contêineres.
- Tipos de movimentações incluem embarque, descarga, gate in/out, entre outros.
- Listagem detalhada e filtrada por contêiner ou cliente.

### Gerenciamento de Usuários
- Administração completa de usuários incluindo cadastro, edição e remoção.
- Busca e listagem paginada de usuários.

## Tecnologias Utilizadas
- **Java 17**: Linguagem de programação utilizada.
- **Jersey**: Framework escolhido para a criação de serviços RESTful.
- **Maven**: Ferramenta de gerenciamento de projeto e dependências.
- **PostgreSQL**: Banco de dados relacional para armazenamento persistente.
- **JWT**: Implementação de autenticação e segurança de sessões.
- **Hibernate Validator**: Framework para validação de dados de entrada baseada em anotações.
- **ModelMapper**: Utilitário para mapeamento automático entre modelos de dados.
- **Flyway**: Ferramenta para gestão e versionamento de banco de dados.
- **Jakarta Bean Validation**: Suporte à validação de beans no lado do servidor.

## Estrutura do Projeto
O projeto segue a organização padrão Maven com diretórios para código fonte (`src/main/java`) e recursos (`src/main/resources`).

## Variáveis de Ambiente
Para executar a aplicação, as seguintes variáveis de ambiente devem ser configuradas:

| Variável               | Descrição                              |
|------------------------|----------------------------------------|
| `DB_NAME`              | NOME DO BANCO DE DADOS                 |
| `DB_PASS`              | PASSWORD DO BANCO DE DADOS             |
| `DB_URL`               | URL DE CONEXÃO                         |
| `DB_USER`              | USUÁRIO DE BANCO DE DADOS              |
| `USER_ADMIN_LOGIN`     | USUÁRIO ADMINISTRADOR                  |
| `USER_ADMIN_PASSWORD`  | PASSWORD DO USUÁRIO ADMINISTRADOR      |
| `JWT_KEY`              | KEY PARA CODIFICAÇÃO JWT               |
| `HOST`                 | HOST DA APLICAÇÃO                      |

## Configuração e Execução
1. Clone o repositório:
   ```bash
   git clone git@github.com:PatrickcBrito88/desafio-jerseys.git

2. Entre no diretório do projeto e construa-o com Maven:
   ```bash
   mvn clean install

3. Execute o projeto
    ```bash
   java -jar target/desafio-jerseys-1.0-SNAPSHOT.jar
