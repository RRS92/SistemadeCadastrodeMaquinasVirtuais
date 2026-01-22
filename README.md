# Sistema de Cadastro de Máquinas Virtuais

##Visão Geral

Este projeto consiste em um sistema de cadastro e gerenciamento de máquinas virtuais, desenvolvido como parte de um desafio técnico para a vaga de Desenvolvedor Full Stack.

O sistema permite:

- Cadastrar máquinas virtuais;
- Listar e visualizar máquinas cadastradas;
- Atualizar configurações das VMs;
- Excluir máquinas virtuais;
- Ligar (start) e desligar (stop) máquinas virtuais.

Toda a aplicação foi construída seguindo boas práticas de desenvolvimento, separação de responsabilidades e comunicação via API REST, com backend em Java + Spring Boot e frontend em Angular.

---

##Arquitetura Geral

- **Backend**: API REST responsável pelas regras de negócio, persistência de dados e controle do ciclo de vida das VMs.
- **Frontend**: Aplicação web em Angular que consome a API e fornece a interface de usuário.
- **Banco de Dados**: PostgreSQL.


