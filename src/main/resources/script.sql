create database web2;

create table aluno(
    id integer primary key generated always as identity,
    nome varchar(256) not null,
    cpf varchar(11) not null,
    email varchar(512) not null,
    dt_nascimento date not null
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
    dt_matricula date not null,
    nota real not null,
    constraint matricula_fk_curso foreign key(curso_id) references curso(id),
    constraint matricula_fk_aluno foreign key(aluno_id) references aluno(id)
);

insert into aluno(nome, cpf, email, dt_nascimento) values ('Rafael', '12345678902', 'rafael@gmail.com', '1979-06-21');
insert into curso(nome, link) values ('Sistemas da Informação', 'teste.com.br');
insert into matricula(curso_id, aluno_id, dt_matricula, nota) values (1, 2, '2024-09-29', 9);