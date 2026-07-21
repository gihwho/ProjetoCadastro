-- Migration para adicionar a coluna de ranking para adicionar a coluna de ranking na tabela de cadastro

ALTER TABLE tb_cadastro_ninja
ADD COLUMN rank VARCHAR(255);