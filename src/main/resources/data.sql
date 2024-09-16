-------------------------------------------------------------------
-- Criando Registros Iniciais -------------------------------------
-------------------------------------------------------------------
-------------------------------------------------------------------
-- Criar Primeiros Registros de Pessoa ----------------------------
INSERT INTO public.pessoa(
    id, data_nascimento, cpf, funcionario, gerente, nome
) VALUES
    (1, '1985-06-15', '12345678909', TRUE, FALSE, 'Joao da Silva'),
    (2, '2001-08-10', '44779458170', TRUE, FALSE, 'Olivia Betina Eduarda Souza'),
    (3, '1999-07-02', '87631218676', TRUE, FALSE, 'Vinicius Guilherme Felipe Almada'),
    (4, '1997-06-16', '61972706683', TRUE, FALSE, 'Eduarda Sonia Mendes'),
    (5, '1995-08-05', '08005990634', TRUE, TRUE, 'Edson Henrique Thomas Freitas')
ON CONFLICT (id) DO NOTHING;
-------------------------------------------------------------------
-- Criar Primeiros Registros de Projeto ---------------------------
INSERT INTO public.projeto(
    id, orcamento, descricao, data_fim, data_previsao_fim, id_gerente, nome, risco, data_inicio, status
) VALUES
    (1, 25000.00, 'Nova Versao do Aplicativo de Vendas', NULL, '2024-12-10', 5, 'Novo Vende Mais', 'Baixo', '2024-06-10', 'Em Andamento'),
    (2, 50000.00, 'Portal Web de Venda de Aulas', '2024-04-20', '2024-05-01', 5, 'Class Me', 'Medio', '2023-05-01', 'Encerrado'),
    (3, 100000.00, 'Web Service Lava Car', NULL, '2025-03-01', 5, 'Auto Wash Net', 'Alto', '2024-03-01', 'Em Andamento')
ON CONFLICT (id) DO NOTHING;
-------------------------------------------------------------------
-- Criar Primeiros Registros de Membros ---------------------------
INSERT INTO public.membros(
    id, vinculo_ativo, data_saida_no_projeto, funcao_no_projeto, data_entrada_no_projeto, id_pessoa, id_projeto
) VALUES
    (1, FALSE, '2024-04-20', 'Gerente', '2023-05-01', 5, 2),
    (2, FALSE, '2024-04-20', 'Dev Full Stack', '2023-05-01', 1, 2),
    (3, FALSE, '2024-04-20', 'QA', '2023-05-01', 2, 2),
    (4, FALSE, '2024-04-20', 'Dev Full Stack', '2023-05-01', 3, 2),
    (5, FALSE, '2024-04-20', 'QA', '2023-05-01', 4, 2),
    (6, TRUE, NULL, 'Gerente', '2024-06-10', 5, 1),
    (7, TRUE, NULL, 'Dev Full Stack', '2024-06-10', 1, 1),
    (8, TRUE, NULL, 'QA', '2024-06-10', 2, 1),
    (9, TRUE, NULL, 'Gerente', '2024-03-01', 5, 3),
    (10, TRUE, NULL, 'Dev Full Stack', '2024-03-01', 3, 3),
    (11, TRUE, NULL, 'QA', '2024-03-01', 4, 3)
ON CONFLICT (id) DO NOTHING;
-------------------------------------------------------------------

