# Sistema de Cadastro de Máquinas Virtuais

## Visão Geral

Este projeto consiste em um sistema de cadastro e gerenciamento de máquinas virtuais, desenvolvido como parte de um desafio técnico para a vaga de Desenvolvedor Full Stack.

O sistema permite:

- Cadastrar máquinas virtuais;
- Listar e visualizar máquinas cadastradas;
- Atualizar configurações das VMs;
- Excluir máquinas virtuais;
- Ligar (start) e desligar (stop) máquinas virtuais.

Toda a aplicação foi construída seguindo boas práticas de desenvolvimento, separação de responsabilidades e comunicação via API REST, com backend em Java + Spring Boot e frontend em Angular.

---

## Arquitetura Geral

- **Backend**: API REST responsável pelas regras de negócio, persistência de dados e controle do ciclo de vida das VMs.
- **Frontend**: Aplicação web em Angular que consome a API e fornece a interface de usuário.
- **Banco de Dados**: PostgreSQL.

---

## Backend

**Tecnologias Utilizadas:**

Java 17
Spring Boot 4.x
Spring Web
Spring Data JPA (Hibernate)
Spring Validation
PostgreSQL
Lombok
Maven
Swagger / OpenAPI (springdoc-openapi)

## Modelagem Principal

**Entidade VirtualMachine:**

id (Long)
name (String)
cpu (Integer)
memoryMb (Integer)
diskGb (Integer)
status (Enum: ON / OFF)
createdAt (LocalDateTime)
updatedAt (LocalDateTime)

**Regras de negócio relevantes:**

Toda VM é criada inicialmente com status OFF
Não é permitido ligar uma VM que já está ligada
Não é permitido desligar uma VM que já está desligada

## Endpoints Principais

GET /vms – Lista todas as VMs
GET /vms/{id} – Busca VM por ID
POST /vms – Cria uma nova VM
PUT /vms/{id} – Atualiza uma VM
DELETE /vms/{id} – Remove uma VM
POST /vms/{id}/start – Liga a VM
POST /vms/{id}/stop – Desliga a VM

Todos os endpoints foram testados via **Insomnia** e estão documentados via **Swagger** UI.


