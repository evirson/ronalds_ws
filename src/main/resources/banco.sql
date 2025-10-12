drop table cadusr;

create table usuario (

    id integer not null primary key,
    email varchar(100) not null,
    senha varchar(100) not null,
    validate date not null


);
