# API Monolítica de Gerenciamento de Restaurante - Spring Boot

API desenvolvida em Java utilizando Spring Boot para o gerenciamento de produtos e pedidos em um restaurante. A aplicação segue uma arquitetura monolítica e foi projetada para cobrir as principais operações de um ambiente de atendimento gastronômico, como controle de itens, realização de pedidos e visualização setorizada.

## 📋 Descrição Geral

Este projeto tem como objetivo fornecer uma API RESTful robusta para operações de um restaurante, incluindo o cadastro de produtos, registro de pedidos e gerenciamento por setores (cozinha e copa). A API segue boas práticas de desenvolvimento, utilizando o padrão Repository com JPA para persistência de dados, segurança com autenticação e autorização via Spring Security, e documentação interativa por meio do Swagger.

## 🛠️ Tecnologias e Ferramentas

- **Java**
- **Spring Boot**
- **Spring Security**
- **JPA (Hibernate)**
- **PostgreSQL**
- **Swagger/OpenAPI**

## ⚙️ Funcionalidades

### Gerenciamento de Produtos e Pedidos
- CRUD completo de produtos
- Cadastro de pedidos com múltiplos itens
- Inserção e remoção de produtos em pedidos
- Cálculo automático do valor por item
- Cálculo do valor total do pedido

### Segurança
- Autenticação e autorização com Spring Security
- Criptografia de credenciais em hash no banco de dados
- Controle de acesso com permissões hierárquicas por **roles**

### Visualização Condicional por Setor
- **Cozinha**: visualiza apenas pedidos abertos com pratos
- **Copa**: visualiza apenas pedidos abertos com bebidas
- Setores podem alterar o status dos pedidos conforme sua responsabilidade

## 📖 Documentação

A API conta com documentação interativa via Swagger, permitindo a visualização e teste dos endpoints diretamente pelo navegador. Após rodar a aplicação, acesse: 

http://localhost:8080/swagger-ui.html


## 🗃️ Banco de Dados

A aplicação está configurada para utilizar o **PostgreSQL** como sistema de gerenciamento de banco de dados. As entidades são gerenciadas por meio do **JPA**, facilitando as operações de persistência.

## 📝 Licença

Este projeto está licenciado sob a Licença MIT. Consulte o arquivo [LICENSE](./LICENSE) para mais detalhes.


