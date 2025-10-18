CREATE TABLE USUARIO (
    ID        INTEGER NOT NULL PRIMARY KEY,
    EMAIL     VARCHAR(100) NOT NULL,
    SENHA     VARCHAR(100) NOT NULL,
    VALIDADE  DATE NOT NULL
);



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

alter table cademp alter column fonemp type varchar(12);

alter table cadfun alter column telfun type varchar(12);
alter table cadfun alter column celfun type varchar(12);

