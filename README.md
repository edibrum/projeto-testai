# Projeto Testa i
Projeto Proposto - Teste Java Full Stack

# Proposta apresentada aqui:
O Projeto foi iniciado usando https://start.spring.io/ para agilizar o processo.

  Opções selecionadas: Project: Maven | Language: Java | Spring Boot: 3.3.3 | Packaging: Jar | Java: 17.

  Dependências adicionadas: Spring Web, Spring Data JPA, PostgreSQL Driver, Thymeleaf, Spring Boot DevTools, & Lombok.
  
  IDE utilizada: IntelliJ Community.

  Banco de Dados Relacional: Postgres.

# Alterações propostas (a validar com "@ProductOwner" e "@ScrumMaster):
1) Pensando em padronização, optei por usar nome em português
para as tabelas e suas respectivas colunas, respeitando a proposta, mas
enquanto código estou usando inglês mesmo nos nomes de classes, entidade,
e suas respectivas propriedades.
2) Na tabela "membros" estou usando um id específico para
o relacionamento - pensando na escalabilidade do uso destes recursos (um cenário
de promoção de funcionários dentro de um projeto, controlando a data de entrada e
data de saída, etc).