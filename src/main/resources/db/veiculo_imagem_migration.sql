-- ============================================================
-- Migration Script: VEICULO_IMAGEM
-- Descrição: Criação e alteração da tabela de imagens de veículos
-- Data: 2025-11-19
-- ============================================================

-- Criar tabela VEICULO_IMAGEM se não existir
CREATE TABLE VEICULO_IMAGEM (
    ID INTEGER NOT NULL,
    CODVEI INTEGER NOT NULL,
    DOCUMENTO BLOB SUB_TYPE 0,
    NOMEARQ VARCHAR(255) NOT NULL,
    CONTENT_TYPE VARCHAR(100),
    TAMANHO BIGINT,
    CONSTRAINT PK_VEICULO_IMAGEM PRIMARY KEY (ID),
    CONSTRAINT FK_VEICULO_IMAGEM_VEICULO FOREIGN KEY (CODVEI)
        REFERENCES CADVEI(CODVEI) ON DELETE CASCADE
);

-- Criar índice para melhor performance nas consultas por veículo
CREATE INDEX IDX_VEICULO_IMAGEM_CODVEI ON VEICULO_IMAGEM(CODVEI);

-- Criar generator para auto-incremento
CREATE GENERATOR GT_VEICULO_IMAGEM_ID;
SET GENERATOR GT_VEICULO_IMAGEM_ID TO 0;

-- Criar trigger para auto-incremento
CREATE OR ALTER TRIGGER BI_VEICULO_IMAGEM_ID FOR VEICULO_IMAGEM
ACTIVE BEFORE INSERT POSITION 0
AS
BEGIN
  IF (NEW.ID IS NULL) THEN
    NEW.ID = GEN_ID(GT_VEICULO_IMAGEM_ID, 1);
END;

-- ============================================================
-- ALTERAÇÕES PARA TABELA EXISTENTE (Caso já exista)
-- Execute apenas se a tabela já existir sem os novos campos
-- ============================================================

-- Adicionar coluna CONTENT_TYPE se não existir
-- ALTER TABLE VEICULO_IMAGEM ADD CONTENT_TYPE VARCHAR(100);

-- Adicionar coluna TAMANHO se não existir
-- ALTER TABLE VEICULO_IMAGEM ADD TAMANHO BIGINT;

-- Atualizar tamanho da coluna NOMEARQ se necessário
-- ALTER TABLE VEICULO_IMAGEM ALTER COLUMN NOMEARQ TYPE VARCHAR(255);

-- Tornar NOMEARQ obrigatório se ainda não for
-- ALTER TABLE VEICULO_IMAGEM ALTER COLUMN NOMEARQ SET NOT NULL;

-- Tornar DOCUMENTO obrigatório se ainda não for
-- ALTER TABLE VEICULO_IMAGEM ALTER COLUMN DOCUMENTO SET NOT NULL;

-- ============================================================
-- COMENTÁRIOS NAS COLUNAS (Opcional - para documentação)
-- ============================================================
COMMENT ON COLUMN VEICULO_IMAGEM.ID IS 'Identificador único da imagem';
COMMENT ON COLUMN VEICULO_IMAGEM.CODVEI IS 'Código do veículo (FK para CADVEI)';
COMMENT ON COLUMN VEICULO_IMAGEM.DOCUMENTO IS 'Conteúdo binário da imagem';
COMMENT ON COLUMN VEICULO_IMAGEM.NOMEARQ IS 'Nome original do arquivo';
COMMENT ON COLUMN VEICULO_IMAGEM.CONTENT_TYPE IS 'Tipo MIME da imagem (image/jpeg, image/png, etc)';
COMMENT ON COLUMN VEICULO_IMAGEM.TAMANHO IS 'Tamanho do arquivo em bytes';

-- ============================================================
-- FIM DO SCRIPT
-- ============================================================
