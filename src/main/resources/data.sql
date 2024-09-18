-------------------------------------------------------------------
-- Criando Registros Iniciais -------------------------------------
-------------------------------------------------------------------
-------------------------------------------------------------------
-- Criar Primeiros Registros de Pessoa ----------------------------
INSERT INTO public.pessoa(data_nascimento, cpf, funcionario, gerente, nome)
SELECT * FROM (SELECT TIMESTAMP '1995-08-05 00:00:00', '08005990634', TRUE, TRUE, 'Edson Henrique Thomas Freitas') AS tmp
WHERE NOT EXISTS (
    SELECT cpf FROM public.pessoa WHERE cpf = '08005990634'
) LIMIT 1;

INSERT INTO public.pessoa(data_nascimento, cpf, funcionario, gerente, nome)
SELECT * FROM (SELECT TIMESTAMP '2001-08-10 00:00:00', '44779458170', TRUE, FALSE, 'Olivia Betina Eduarda Souza') AS tmp
WHERE NOT EXISTS (
    SELECT cpf FROM public.pessoa WHERE cpf = '44779458170'
) LIMIT 1;

INSERT INTO public.pessoa(data_nascimento, cpf, funcionario, gerente, nome)
SELECT * FROM (SELECT TIMESTAMP '1985-06-15 00:00:00', '12345678909', TRUE, FALSE, 'Joao da Silva') AS tmp
WHERE NOT EXISTS (
    SELECT cpf FROM public.pessoa WHERE cpf = '12345678909'
) LIMIT 1;

INSERT INTO public.pessoa(data_nascimento, cpf, funcionario, gerente, nome)
SELECT * FROM (SELECT TIMESTAMP '1999-07-02 00:00:00', '87631218676', TRUE, FALSE, 'Vinicius Guilherme Felipe Almada') AS tmp
WHERE NOT EXISTS (
    SELECT cpf FROM public.pessoa WHERE cpf = '87631218676'
) LIMIT 1;

INSERT INTO public.pessoa(data_nascimento, cpf, funcionario, gerente, nome)
SELECT * FROM (SELECT TIMESTAMP '1997-06-16 00:00:00', '61972706683', TRUE, FALSE, 'Eduarda Sonia Mendes') AS tmp
WHERE NOT EXISTS (
    SELECT cpf FROM public.pessoa WHERE cpf = '61972706683'
) LIMIT 1;
-------------------------------------------------------------------
-- Criar Primeiros Registros de Projeto ---------------------------
INSERT INTO public.projeto(orcamento, descricao, data_fim, data_previsao_fim, id_gerente, nome, risco, data_inicio, status)
SELECT * FROM (SELECT 50000.00, 'Portal Web de Venda de Aulas', TIMESTAMP '2024-04-20 00:00:00', TIMESTAMP '2024-05-01 00:00:00', 5, 'Class Me', 'Medio', TIMESTAMP '2023-05-01 00:00:00', 'Encerrado') AS tmp
WHERE NOT EXISTS (
    SELECT nome FROM public.projeto WHERE nome = 'Class Me'
) LIMIT 1;

INSERT INTO public.projeto(orcamento, descricao, data_previsao_fim, id_gerente, nome, risco, data_inicio, status)
SELECT * FROM (SELECT 25000.00, 'Nova Versao do Aplicativo de Vendas', TIMESTAMP '2024-12-10 00:00:00', 5, 'Novo Vende Mais', 'Baixo', TIMESTAMP '2024-06-10 00:00:00', 'Em Andamento') AS tmp
WHERE NOT EXISTS (
    SELECT nome FROM public.projeto WHERE nome = 'Novo Vende Mais'
) LIMIT 1;

INSERT INTO public.projeto(orcamento, descricao, data_previsao_fim, id_gerente, nome, risco, data_inicio, status)
SELECT * FROM (SELECT 100000.00, 'Web Service Lava Car', TIMESTAMP '2025-03-01 00:00:00', 5, 'Auto Wash Net', 'Alto', TIMESTAMP '2024-03-01 00:00:00', 'Em Andamento') AS tmp
WHERE NOT EXISTS (
    SELECT nome FROM public.projeto WHERE nome = 'Auto Wash Net'
) LIMIT 1;
-------------------------------------------------------------------
-- Criar Primeiros Registros de Membros ---------------------------
INSERT INTO public.membros(vinculo_ativo, data_saida_no_projeto, funcao_no_projeto, data_entrada_no_projeto, id_pessoa, id_projeto)
SELECT * FROM (SELECT FALSE, TIMESTAMP '2024-04-20 00:00:00', 'Gerente', TIMESTAMP '2023-05-01 00:00:00',
(SELECT id FROM public.pessoa WHERE cpf = '08005990634'),
(SELECT id FROM public.projeto WHERE nome = 'Class Me')) AS tmp
WHERE NOT EXISTS (
    SELECT id_pessoa FROM public.membros WHERE id_pessoa = (SELECT id FROM public.pessoa WHERE cpf = '08005990634')
) LIMIT 1;

INSERT INTO public.membros(vinculo_ativo, data_saida_no_projeto, funcao_no_projeto, data_entrada_no_projeto, id_pessoa, id_projeto)
SELECT * FROM (SELECT FALSE, TIMESTAMP '2024-04-20 00:00:00', 'QA', TIMESTAMP '2023-05-01 00:00:00',
(SELECT id FROM public.pessoa WHERE cpf = '44779458170'),
(SELECT id FROM public.projeto WHERE nome = 'Class Me')) AS tmp
WHERE NOT EXISTS (
    SELECT id_pessoa FROM public.membros WHERE id_pessoa = (SELECT id FROM public.pessoa WHERE cpf = '44779458170')
) LIMIT 1;
-------------------------------------------------------------------

