# Vibbra! MEI - Project Organize Recipes

API RESTful para atender os requisitos funcionais do projeto apresentado.
Empreendedores formalizados como Microempreendedores Individuais que buscam uma forma automática de organizar suas Receitas (geração de Notas Fiscais) para evitar surpresas com pagamento de impostos ao final do ano.

## 1. Avaliação do escopo
Criei um projeto Spring boot versão 2.4, utilizando java 11, banco de dados H2 em memoria para facilitar execução e testes, num ambiente produtivo poderia utilizar qualquer banco de dados relacional do mercado visto que utilizei padronização do hibernate para mapeamento das entidades que é compativel com maioria dos banco de dados relacional. Utilizei lombok para facilitar com alguns codigos basicos sem a necessidade de escrever utilizando suas proprias anotações e padroes. Spring security com JWT para segurança das rotas das aplicações, padroes de retorno conforme HTTP code, Swagger para listagem e documentação dos endpoints.

# Tech Stack

> Spring Boot 2.4.0
> Spring Data JPA 
> Spring Security
> Java 11
> Maven 
> JWT 
> ModelMapper 
> Lombok 
> Swagger 2 
> H2 Database 

## Link's Úteis

> **Documentação API Swagger:** http://localhost:9002/swagger-ui.html
> **Banco de Dados H2 Database:** http://localhost:9002/h2-console

Dados de acesso H2 Databse:
 - **JDBC URL:** jdbc:h2:~/db_vibbra-mei-organizerecipes;DB_CLOSE_ON_EXIT=FALSE
 - **Usuário**: admin
 - **Senha:** 123456

## Como compilar e executar aplicação

Importar o projeto em alguma IDE via Maven Projects. Execute o comando **`mvn clean install`** para buildar e executar os teste unitários. Execute o comando **`Run as Java Aplication`** no arquivo **`StartApplication.java`** para executar a aplicação.

## Link de Teste disponível no Heroku
> https://vibbra-mei-organize-recipes.herokuapp.com/swagger-ui.html

Usuário inicial para testes:

 - user1@test.com
 - 123456@

