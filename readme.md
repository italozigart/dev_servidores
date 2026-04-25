# Projeto Catálogo de Produtos (Spring Boot)

## Configuração do Banco de Dados (PostgreSQL)

Para executar o projeto, é necessário ter o PostgreSQL instalado.

## 1. Criar o banco de dados

No PgAdmin (ou outro cliente), execute:

CREATE DATABASE catalogo_db;

Não é necessário criar tabelas manualmente.

## 2. Configurar o application.properties

Acesse o arquivo:

src/main/resources/application.properties

Configure:

spring.datasource.url=jdbc:postgresql://localhost:5432/catalogo_db
spring.datasource.username=postgres
spring.datasource.password=123456

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

Ajuste usuário e senha conforme seu ambiente.

## 3. Executar o projeto

Ao iniciar a aplicação:

- O Spring Boot cria automaticamente as tabelas
- Não é necessário executar scripts SQL

## 4. Usuários padrão

| Usuário | Senha  | Perfil |
|--------|--------|--------|
| admin  | 123456 | ADMIN  |
| aluno  | 123456 | USER   |

## 5. Acesso

http://localhost:8080

## Observações

Para recriar as tabelas:

spring.jpa.hibernate.ddl-auto=create

Depois retornar para:

spring.jpa.hibernate.ddl-auto=update