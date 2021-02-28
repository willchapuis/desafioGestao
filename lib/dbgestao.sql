create database dbgestao;
use dbgestao;

-- excluir tabelas
DROP TABLE sala_evento;
DROP TABLE espaco_cafe;
DROP TABLE pessoa;

-- excluir linhas da tabela
DELETE FROM pessoa WHERE id_pessoa = 2;

-- alterar linhas da tabela
UPDATE espaco_cafe SET nome = "Espaco Z" WHERE id_espaco_cafe = 2;

-- criar tabelas
CREATE TABLE sala_evento(
id_sala_evento INT NOT NULL AUTO_INCREMENT,
nome VARCHAR(100),
lotacao INT,
primary key (id_sala_evento));

CREATE TABLE espaco_cafe(
id_espaco_cafe INT NOT NULL AUTO_INCREMENT,
nome VARCHAR(100),
primary key (id_espaco_cafe));

CREATE TABLE pessoa(
id_pessoa INTEGER NOT NULL auto_increment,
nome VARCHAR(100),
sobrenome VARCHAR(100),
id_sala_etapa1 int not null,
id_sala_etapa2 int not null,
id_espaco int not null,
primary key (id_pessoa),
CONSTRAINT fk_sala_pessoa_etapa1 foreign key (id_sala_etapa1) references sala_evento (id_sala_evento),
CONSTRAINT fk_sala_pessoa_etapa2 foreign key (id_sala_etapa2) references sala_evento (id_sala_evento));

-- descricao das tabelas
describe sala_evento;
describe espaco_cafe;
describe pessoa;

-- insert test
insert into sala_evento (nome, lotacao) values ("Sala 100", 20);
insert into sala_evento (nome, lotacao) values ("Sala 101", 15);
insert into sala_evento (nome, lotacao) values ("Sala 102", 18);

insert into espaco_cafe (nome) values ("Espaco A");
insert into espaco_cafe (nome) values ("Espaco B");

insert into pessoa (nome, sobrenome, id_sala_etapa1, id_sala_etapa2, id_espaco) values ("Rodrigo", "Abcdef", 1, 2, 1);
insert into pessoa (nome, sobrenome, id_sala_etapa1, id_sala_etapa2, id_espaco) values ("Larissa", "Bcdefg", 2, 3, 2);
insert into pessoa (nome, sobrenome, id_sala_etapa1, id_sala_etapa2, id_espaco) values ("Andre", "Cdefgh", 3, 1, 1);
insert into pessoa (nome, sobrenome, id_sala_etapa1, id_sala_etapa2, id_espaco) values ("Marisa", "Defghi", 1, 2, 2);
insert into pessoa (nome, sobrenome, id_sala_etapa1, id_sala_etapa2, id_espaco) values ("Paulo", "Efghij", 2, 3, 1);
insert into pessoa (nome, sobrenome, id_sala_etapa1, id_sala_etapa2, id_espaco) values ("Bruna", "Fghijk", 3, 1, 2);

-- select test
SELECT * FROM sala_evento;
SELECT * FROM espaco_cafe;
SELECT * FROM pessoa;

SELECT P.nome, P.sobrenome, A.nome as Etapa2, B.nome as Etapa2, E.nome as Espaco from (pessoa P, sala_evento A, sala_evento B, espaco_cafe E) WHERE (P.id_sala_etapa1 = A.id_sala_evento and P.id_sala_etapa2 = B.id_sala_evento and P.id_espaco = E.id_espaco_cafe);

-- selects usados nas consultas implementadas
SELECT * FROM pessoa WHERE id_sala_etapa1 = 1;
SELECT * FROM pessoa WHERE id_sala_etapa2 = 1;
SELECT * FROM pessoa WHERE id_espaco = 1;

SELECT * FROM sala_evento WHERE id_sala_evento = 1;
SELECT * FROM espaco_cafe WHERE id_espaco_cafe = 1;
SELECT * FROM pessoa WHERE id_pessoa = 1;

SELECT pessoa.*, count(*) over () total_rows FROM pessoa;
SELECT count(*) over () total_rows FROM espaco_cafe;
SELECT count(*) over () total_rows FROM sala_evento;

-- SELECT MAX(id_pessoa) FROM pessoa;
-- SELECT MIN(id_pessoa) FROM pessoa;
-- SELECT MIN(id_pessoa) FROM pessoa WHERE id_pessoa > 2;
-- SELECT MIN(id_pessoa) FROM pessoa WHERE id_pessoa > (SELECT MIN(id_pessoa) FROM pessoa);
-- DELETE FROM pessoa WHERE id_pessoa > (SELECT MIN(id_pessoa) FROM pessoa WHERE id_pessoa > (SELECT MIN(id_pessoa) FROM pessoa));