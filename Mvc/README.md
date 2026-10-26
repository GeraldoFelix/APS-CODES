# Cadastramento de Produto em MVC (Spring Boot + Thymeleaf)

Exemplo para o seminário de Arquitetura de Software.
Entidades: Produto (id, nome, preco, id_usuario) e Usuário (id, nome).
Operações em cada uma: cadastrar, atualizar, deletar e buscar por id.

## Parâmetros para executar

Requisitos: JDK 17 ou superior e Maven.

    mvn spring-boot:run

Abra http://localhost:8081. O banco já inicia com 2 usuários (Ana e Bruno)
e 3 produtos de exemplo.

Para rodar só o teste das regras do Model (sem servidor e sem banco):

    mvn test

## Banco de dados simulado

O projeto usa o H2 em memória: um banco relacional de verdade, mas que vive só
enquanto a aplicação roda. Não precisa instalar nada.

- Dados de exemplo: a classe DadosIniciais (pacote model) cadastra usuários e
  produtos ao iniciar. Para começar com o banco vazio, use
  demo.dados-iniciais=false em application.properties.
- Ver as tabelas: http://localhost:8081/h2-console com
  JDBC URL jdbc:h2:mem:demo, usuário sa e senha vazia.
  Tabelas: USUARIO e PRODUTO (coluna ID_USUARIO).
- Trocar por um banco real (PostgreSQL, como no Washii): só muda a dependência
  no pom.xml e as propriedades spring.datasource.*. Model, Controller e View
  ficam iguais.

## Onde fica cada papel do MVC

    src/main/java/com/exemplo/mvc/
    ├── model/                  MODEL: dados, regras de negócio e acesso ao banco
    │   ├── Produto, Usuario            entidades (com a validação própria)
    │   ├── ProdutoService, UsuarioService   operações e regras entre entidades
    │   └── ProdutoRepository, UsuarioRepository   acesso ao banco (SQL gerado aqui)
    └── controller/             CONTROLLER: recebe a requisição, aciona o Model, escolhe a View
        ├── ProdutoController
        └── UsuarioController
    src/main/resources/templates/   VIEW: páginas HTML (Thymeleaf), só apresentam dados

## Rotas (Controller)

| Operação      | Rota                          | View exibida          |
|---------------|-------------------------------|-----------------------|
| Listar        | GET  /produtos                | produtos.html         |
| Cadastrar     | POST /produtos                | volta para a lista    |
| Buscar por Id | GET  /produtos/{id}           | produto-detalhe.html  |
| Atualizar     | POST /produtos/{id}           | volta para o detalhe  |
| Deletar       | POST /produtos/{id}/deletar   | volta para a lista    |

As rotas de /usuarios seguem o mesmo desenho.

## Regras de negócio (todas no Model)

- Nome do produto e do usuário são obrigatórios.
- O preço não pode ser negativo.
- O usuário informado no produto precisa existir.
- Não é possível deletar um usuário que possui produtos.

O Controller só converte a entrada (ex.: "24,90" vira número) e coordena o fluxo.
