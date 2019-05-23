CREATE TABLE restricao (
  restricao_id serial primary key,
  restricao_nome TEXT NOT NULL,
  restricao_tipo TEXT NOT NULL,
  restricao_status BOOLEAN NOT NULL
);

CREATE TABLE usuario (
  usuario_id serial primary key,
  usuario_nome TEXT NOT NULL,
  usuario_idade INTEGER NOT NULL,
  usuario_cpf TEXT NOT NULL,
  usuario_rg TEXT NOT NULL,
  usuario_senha varchar not null,
  usuario_telefone BIGINT NOT NULL,
  usuario_celular BIGINT NOT NULL,
  usuario_tiposague TEXT NOT NULL,
  usuario_peso NUMERIC NOT NULL,
  usuario_altura NUMERIC NOT NULL,
  usuario_nascimento DATE NOT NULL,
  usuario_acesso TEXT NOT NULL,
  usuario_status boolean NOT NULL
);


CREATE TABLE funcionario (
  funcionario_id serial primary key,
  funcionario_cofen BIGINT NOT NULL,
  funcionario_condicao BOOLEAN NOT NULL,
  funcionario_FK_usuario INT NOT NULL,
  CONSTRAINT funcionario_usuario FOREIGN KEY (funcionario_FK_usuario) REFERENCES usuario (usuario_id)
);

CREATE TABLE endereco (
  endereco_id serial primary key,
  endereco_logradouro TEXT NOT NULL,
  endereco_numero INTEGER NOT NULL,
  endereco_cidade TEXT NOT NULL,
  endereco_bairro TEXT NOT NULL,
  endereco_complemento TEXT NOT NULL,
  endereco_cep INTEGER NOT NULL,
  endereco_uf TEXT NOT NULL,
  endereco_status boolean not null
);

CREATE TABLE posto (
  posto_id serial primary key,
  posto_nome TEXT NOT NULL,
  posto_telefone INTEGER NOT NULL,
  posto_FK_endereco INTEGER NOT NULL,
  constraint posto_FKIndex1 foreign key (posto_FK_endereco) references endereco (endereco_id)
);

CREATE TABLE vacina (
  vacina_id SERIAL PRIMARY KEY,
  vacina_nome TEXT NOT NULL,
  vacina_dose INT NOT NULL,
  vacina_intervalo INT NOT NULL,
  vacina_tipo TEXT NOT NULL,
  vacina_calendarioObr BOOLEAN NOT NULL,
  vacina_status BOOLEAN NOT NULL
);


CREATE TABLE calendario_obrigatorio(
  calendarioObr_id SERIAL PRIMARY KEY,
  calendarioObr_tempoParaInjetar INT NOT NULL,
  calendarioObr_status BOOLEAN NOT NULL,
  calendarioObr_DataCadastro DATE NOT NULL,
  calendarioObr_HoraCadastro TIME NOT NULL,

  calendarioObr_FK_funcionario INT NOT NULL, 
  calendarioObr_FK_vacina INT NOT NULL,
  CONSTRAINT calendario_obrigatorio_vacina FOREIGN KEY (calendarioObr_FK_vacina) REFERENCES vacina(vacina_id),
  CONSTRAINT calendario_obrigatorio_funcionario FOREIGN KEY (calendarioObr_FK_funcionario)REFERENCES funcionario(funcionario_id)
);


CREATE TABLE usuario_historico(
  usuarioHistorico_id SERIAL PRIMARY KEY,
  usuarioHistorico_dataInsercaoDoMendicamento DATE,
  usuarioHistorico_comentarioEventos TEXT,
  usuarioHistorico_statusVacinaCalendario TEXT NOT NULL,
  usuarioHistorico_horaAplicacao TIME, 
  usuarioHistorico_dataAplicacao DATE,

  usuarioHistorico_FK_usuario INT NOT NULL,
  usuarioHistorico_FK_calendarioObr INT NOT NULL,
  usuarioHistorico_FK_Funcionario INT,  
  usuarioHistorico_FK_Posto INT,

  CONSTRAINT usuarioHistorico_usuario FOREIGN KEY (usuarioHistorico_FK_usuario) REFERENCES usuario (usuario_id),
  CONSTRAINT usuarioHistorico_calendariopadrao FOREIGN KEY (usuarioHistorico_FK_calendarioObr) REFERENCES calendario_obrigatorio (calendarioObr_id),
  CONSTRAINT usuarioHistorico_funcionario FOREIGN KEY (usuarioHistorico_FK_Funcionario) REFERENCES funcionario (funcionario_id),
  CONSTRAINT usuarioHistorico_posto FOREIGN KEY (usuarioHistorico_FK_Posto) REFERENCES posto (posto_id)
);



CREATE TABLE campanha (
  campanha_id serial primary key,
  campanha_nome TEXT NOT NULL,
  campanha_inicio DATE NOT NULL,
  campanha_final DATE NOT NULL,
  vacina_id integer not null,
  campanha_status BOOLEAN NOT NULL,
  constraint campanha_vacina_vategoria foreign key (vacina_id) references vacina (vacina_id)
);


CREATE TABLE campanha_has_restricao (
  campanha_id INTEGER NOT NULL,
  restricao_id INTEGER NOT NULL,
  constraint campanha_has_restricao_FKIndex1 foreign key (campanha_id) references campanha (campanha_id),
  constraint campanha_has_restricao_FKIndex2 foreign key (restricao_id) references restricao (restricao_id)
);


CREATE TABLE campanha_has_usuario (
  campanha_id INTEGER NOT NULL,
  usuario_id INTEGER NOT NULL,
  constraint campanha_has_usuario_FKIndex1 foreign key (campanha_id) references campanha (campanha_id),
  constraint campanha_has_usuario_FKIndex2 foreign key (usuario_id) references usuario (usuario_id)
);


CREATE TABLE endereco_has_usuario (
  endereco_id INTEGER NOT NULL,
  usuario_id INTEGER NOT NULL,
  constraint endereco_has_usuario_FKIndex1 foreign key (endereco_id) references endereco (endereco_id),
  constraint endereco_has_usuario_FKIndex2 foreign key (usuario_id) references usuario (usuario_id)
);

CREATE TABLE usuario_has_restricao (
  usuario_id INTEGER NOT NULL,
  restricao_id INTEGER NOT NULL,
  constraint usuario_has_restricao_FKIndex1 foreign key (usuario_id) references usuario (usuario_id),
  constraint usuario_has_restricao_FKIndex2 foreign key (restricao_id) references restricao (restricao_id)
);

CREATE TABLE vacina_has_restricao(
  vacina_id INTEGER NOT NULL,
  restricao_id INTEGER NOT NULL,
  status BOOLEAN NOT NULL,
  CONSTRAINT vacina_has_restricao_FKIndex1 FOREIGN KEY (vacina_id) REFERENCES vacina (vacina_id),
  CONSTRAINT vacina_has_restricao_FKIndex2 FOREIGN KEY (restricao_id) REFERENCES restricao (restricao_id)
)


