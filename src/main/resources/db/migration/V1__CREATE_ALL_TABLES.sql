CREATE TABLE t_consulta (
    id SERIAL PRIMARY KEY,
    forma_agendamento VARCHAR(10) NOT NULL,
    tipo_consulta VARCHAR(12) NOT NULL,
    id_medico INTEGER NOT NULL,
    id_disponibilidade INTEGER NOT NULL,
    id_paciente INTEGER NOT NULL
);

CREATE UNIQUE INDEX t_consulta__idx ON
    t_consulta (
    id_disponibilidade
ASC );

CREATE TABLE t_disponibilidade (
    id SERIAL PRIMARY KEY,
    data_disponivel DATE NOT NULL,
    horario_disponivel TIMESTAMPTZ NOT NULL,
    agendado BOOLEAN NOT NULL,
    id_medico INTEGER NOT NULL
);

CREATE TABLE t_especialidade (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(64) NOT NULL
);

CREATE TABLE t_medico (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    crm VARCHAR(13) NOT NULL UNIQUE,
    data_nascimento DATE NOT NULL,
    genero CHAR(1) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    telefone VARCHAR(18) NOT NULL,
    presencial BOOLEAN NOT NULL,
    teleconsulta BOOLEAN NOT NULL,
    valor_da_consulta NUMERIC(6, 2) NOT NULL,
    id_usuario INTEGER NOT NULL,
    id_especialidade INTEGER NOT NULL
);

CREATE UNIQUE INDEX t_medico__idx ON
    t_medico (
    id_usuario
ASC );

CREATE TABLE t_paciente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    data_nascimento DATE NOT NULL,
    genero CHAR(1) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    telefone VARCHAR(18) NOT NULL,
    id_usuario INTEGER,
    id_responsavel INTEGER
);

CREATE UNIQUE INDEX t_paciente__idx ON
    t_paciente (
    id_usuario
ASC );

CREATE TABLE t_plano_saude (
    id SERIAL PRIMARY KEY,
    convenio VARCHAR(64) NOT NULL,
    plano VARCHAR(64) NOT NULL,
    num_carteirinha VARCHAR(32) NOT NULL UNIQUE,
    id_paciente INTEGER NOT NULL
);

CREATE UNIQUE INDEX t_plano_saude__idx ON
    t_plano_saude (
    id_paciente
ASC );

CREATE TABLE t_usuario (
    id SERIAL PRIMARY KEY,
    email VARCHAR(128) NOT NULL UNIQUE,
    senha VARCHAR(128) NOT NULL,
    role VARCHAR(8) NOT NULL
);

ALTER TABLE t_consulta
ADD CONSTRAINT consulta_disponibilidade_fk FOREIGN KEY ( id_disponibilidade )
REFERENCES t_disponibilidade ( id );

ALTER TABLE t_consulta
ADD CONSTRAINT consulta_medico_fk FOREIGN KEY ( id_medico )
REFERENCES t_medico ( id );

ALTER TABLE t_consulta
ADD CONSTRAINT consulta_paciente_fk FOREIGN KEY ( id_paciente )
REFERENCES t_paciente ( id );

ALTER TABLE t_disponibilidade
ADD CONSTRAINT disponibilidade_medico_fk FOREIGN KEY ( id_medico )
REFERENCES t_medico ( id );

ALTER TABLE t_medico
ADD CONSTRAINT medico_especialidade_fk FOREIGN KEY ( id_especialidade )
REFERENCES t_especialidade ( id );

ALTER TABLE t_medico
ADD CONSTRAINT medico_usuario_fk FOREIGN KEY ( id_usuario )
REFERENCES t_usuario ( id );

ALTER TABLE t_paciente
ADD CONSTRAINT paciente_responsavel_fk FOREIGN KEY ( id_responsavel )
REFERENCES t_paciente ( id );

ALTER TABLE t_paciente
ADD CONSTRAINT paciente_usuario_fk FOREIGN KEY ( id_usuario )
REFERENCES t_usuario ( id );

ALTER TABLE t_plano_saude
ADD CONSTRAINT plano_saude_paciente_fk FOREIGN KEY ( id_paciente )
REFERENCES t_paciente ( id );