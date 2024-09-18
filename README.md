# Projeto Testa i
Projeto Proposto - Teste Java

# Proposta apresentada aqui:
O Projeto foi iniciado usando https://start.spring.io/ para agilizar o processo.

  Opções selecionadas: Project: Maven | Language: Java | Spring Boot: 3.3.3 | Packaging: Jar | Java: 17.

  Dependências adicionadas: Spring Web, Spring Data JPA, PostgreSQL Driver, Spring Boot DevTools, & Lombok.
  
  IDE utilizada: IntelliJ Community.

  Banco de Dados Relacional: Postgres.

# Alterações propostas (a validar com @ProductOwner e @ScrumMaster):
1) Pensando em padronização, optei por usar nome em português
para as tabelas e suas respectivas colunas, respeitando a proposta no banco de dados, mas
enquanto código estou usando inglês nos nomes de classes, entidades,
e suas respectivas propriedades, etc.


2) Pensando em escalabilidade e usabilidade, na tabela "membros" estou usando um id específico para
o relacionamento - pensando em um futuro cenário para estes recursos (um cenário
de promoção de funcionários dentro de um projeto, controlando a data de entrada e
data de saída, etc).


3) Pensando em segurança, gostaria de alinhar se estaria definido como será o controle de
acessos (CRUD de usuários?, controle de permissões de usuários?, etc).