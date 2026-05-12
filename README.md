# Projeto de Gestão de Projetos CLI em Java

Sistema de gerenciamento de projetos desenvolvido em Java com integração ao banco de dados MySQL, executado totalmente via linha de comando (CLI).

O projeto foi criado com o objetivo de praticar conceitos de desenvolvimento backend, programação orientada a objetos, persistência de dados e integração com banco de dados relacional.

---

# Objetivos do Projeto

Este projeto foi desenvolvido para:

- Aplicar conceitos de Programação Orientada a Objetos (POO)
- Praticar integração entre Java e MySQL utilizando JDBC
- Desenvolver operações CRUD completas
- Simular um sistema real de gerenciamento de projetos
- Trabalhar com organização em camadas e separação de responsabilidades
- Desenvolver uma aplicação funcional via terminal (CLI)

---

# Tecnologias Utilizadas

- Java
- MySQL
- JDBC
- SQL
- Git/GitHub

---

# Estrutura do Projeto

```bash
gestao-projetos/
│
├── src/
│   ├── model/
│   ├── database/
│   ├── dao/
│   ├── main
│   └── DDL.txt
│
├── README.md
```

---

# Funcionalidades
- Gestão de Projetos
- Criar novos projetos
- Editar informações de projetos
- Listar projetos cadastrados
- Controle de status
- Excluir projetos

---

# Gestão de Equipes/Usuários
- Criar equipes e usuários
- Associar equipes aos projetos
- Atualizar equipes e usuários
- Remover equipes e usuários
- Controle das equipes

---

# Banco de Dados
- Persistência de dados em MySQL
- Operações CRUD completas
- Relacionamentos entre entidades

---

# Requisitos

Antes de executar o projeto, certifique-se de possuir instalado:

- Java JDK 17+ (ou versão utilizada no projeto)
- MySQL

# Confiuração do Banco de Dados

Antes de rodar o projeto:

- 1.Crie o banco de dados "gestao_projetos"
- 2.Execute o script "DDL.txt" dentro do banco de dados criado
- 3.Configure o usuário e senha no projeto

# Como executar o projeto

1.Clonar o repositório (Execute o seguinte comando):
```
git clone https://github.com/rodrigofbms/gestao-projetos.git
```

2.Acesse a pasta do projeto:
```
cd gestao-projetos
```

3.Execute a aplicação via terminal ou diretamente pela IDE
```
java -jar target/gestao-projetos.jar
```

---

# Conceitos aplicados

- Programação Orientada a Objetos
- Encapsulamento
- Separação em camadas
- JDBC
- CRUD
- SQL
- Persistência de dados
- Arquitetura backend

---
# Autor

Rodrigo Maturino
