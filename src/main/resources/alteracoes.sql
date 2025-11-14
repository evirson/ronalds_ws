drop table cadusr;

CREATE DOMAIN DBOOLEAN AS SMALLINT
CHECK (VALUE IN (0,1));

CREATE TABLE USUARIOS (
    ID             INTEGER NOT NULL,
    NOME           VARCHAR(150) NOT NULL,
    EMAIL          VARCHAR(150) NOT NULL,
    SENHA_HASH     VARCHAR(255) NOT NULL,
    PERFIL         VARCHAR(50),
    ATIVO          DBOOLEAN DEFAULT TRUE /* DBOOLEAN = SMALLINT CHECK (VALUE IN (0, 1)) */,
    DATA_CADASTRO  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ULTIMO_LOGIN   TIMESTAMP,
    TELEFONE       VARCHAR(20),
    CELULAR        VARCHAR(20)
);

ALTER TABLE USUARIOS ADD UNIQUE (EMAIL);
ALTER TABLE USUARIOS ADD CONSTRAINT PK_USUARIOS_ID PRIMARY KEY (ID);

-- Generator e trigger para PK (Firebird)
CREATE GENERATOR GT_USUARIOS_ID;
SET GENERATOR GT_USUARIOS_ID TO 0;

CREATE OR ALTER TRIGGER BI_USUARIOS_ID FOR USUARIOS
ACTIVE BEFORE INSERT POSITION 0
AS
BEGIN
  IF (NEW.ID IS NULL) THEN NEW.ID = GEN_ID(GT_USUARIOS_ID, 1);
  IF (NEW.ATIVO IS NULL) THEN NEW.ATIVO = 1;
  IF (NEW.DATA_CADASTRO IS NULL) THEN NEW.DATA_CADASTRO = CURRENT_TIMESTAMP;
END


alter table cadfor alter column FONFOR type varchar(12);
alter table cadfor alter column FAXFOR type varchar(12);
alter table cadfor alter column FONREP type varchar(12);
alter table cadfor alter column FAXREP type varchar(12);
alter table cadfor alter column CELREP type varchar(12);

create generator gt_cadfor_codfor;
create generator gt_cadcla_codcla;
create generator gt_cadgru_codgru;
create generator gt_cadsub_codsub;
create generator gt_cadpro_codpro;
create generator gt_cadcpv_codcpv;
create generator gt_cadcta_codcta;
create generator gt_cadcrt_codcrt;
create generator gt_cademp_codemp;
create generator gt_cadfun_codfun;
create generator gt_cadmun_codmun;
create generator gt_cadpag_codpag;
create generator gt_cadrec_codrec;
create generator gt_detcax_codloc;
create generator gt_clafis_codloc;
create generator gt_cadsrv_codsrv;
create generator gt_srvitn_coditn;
create generator gt_estados_codest;
create generator gt_pais_codpai;

alter table cademp alter column fonemp type varchar(12);

alter table cadfun alter column telfun type varchar(12);
alter table cadfun alter column celfun type varchar(12);

drop trigger tg_cadsrv_update;
