create database web2;

create table aluno(
    id integer primary key generated always as identity,
    nome varchar(256) not null,
    cpf varchar(11) not null,
    email varchar(512) not null,
    dt_nascimento varchar(8) not null
);

create table curso(
    id integer primary key generated always as identity,   
    nome varchar(256) not null,
    link varchar(1024) not null
);

create table matricula(
    id integer primary key generated always as identity,
    curso_id integer not null,
    aluno_id integer not null,
    data_matricula varchar(8) not null,
    nota real not null,
    constraint matricula_fk_curso references curso_id foreign key curso(id),
    constraint matricula_fk_aluno references aluno_id foreign key aluno(id)
);

create table matricula(
    id integer primary key generated always as identity,
    curso_id integer not null,
    aluno_id integer not null,
    data_matricula varchar(8) not null,
    nota real not null,
    constraint matricula_fk_curso foreign key(curso_id) references curso(id),
    constraint matricula_fk_aluno foreign key(aluno_id) references aluno(id)
);