CREATE TABLE INSUMOS (
  INSUMOS_ID SERIAL PRIMARY KEY,
  INSUMOS_NOME TEXT NOT NULL,
  INSUMOS_DOSE INT NOT NULL,
  INSUMOS_INTERVALO INT NOT NULL,
  INSUMOS_TIPO TEXT NOT NULL,
  INSUMOS_STATUS BOOLEAN NOT NULL,
  FK_TIPOINSUMOS INT NOT NULL,
  CONSTRAINT TIPOINSUMOS_HAS_VACINA FOREIGN KEY (FK_TIPOINSUMOS) REFERENCES TIPO_VACINA (tipoV_id)
);

//TABELA N/N DE VACINA PARA RESTRICAO
CREATE TABLE vacina_has_restricao (
  FK_RESTRICOES INTEGER NOT NULL,
  FK_INSUMOS INTEGER NOT NULL,
  constraint VACINA_HAS_RESTRICAO foreign key (FK_RESTRICOES) references RESTRICAO (RESTRICAO_ID),
	constraint RESTRICAO_HAS_INSUMOS foreign key (FK_INSUMOS) references INSUMOS (INSUMOS_ID)
);

CREATE TABLE TIPO_VACINA (
  TIPOV_id serial PRIMARY KEY,
  TIPOV_NOME VARCHAR NOT NULL,
  TIPOV_NIVEL VARCHAR not NULL,
  TIPOV_STATUS BOOLEAN NOT NULL
);

CREATE TABLE FUNCIONARIO (
  FUNCIONARIO_ID SERIAL PRIMARY KEY,
  FUNCIONARIO_NOME TEXT NOT NULL,
  FUNCIONARIO_IDADE BIGINT NOT NULL,
  FUNCIONARIO_CONFEN BIGINT NOT NULL,
  FUNCIONARIO_CPF TEXT NOT NULL,
  FUNCIONARIO_ENDERECO TEXT NOT NULL,
  FUNCIONARIO_ACESSO TEXT NOT NULL
  );


#FALTA CRIAR // CALENDARIO DE VACINAS OBRIGATORIOS QUE TODOS USUARIO PRECISA TOMAR DEPENDENDO DA RESTRICOESs
CREATE TABLE calendario_obrigatorio(
  calendarioOB_id SERIAL primary key,
  calendarioOB_diasdevida int not null,
  calendarioOB_comentario varchar(150),
  calendarioOB_DataCadastro date not null,
  calendarioOB_HoraCadastro time not null, 
  FK_RESTRICAO int not null, 
  FK_FUNCIONARIO int not null, 
  FK_VACINA int not null, 
  constraint calendarioOB_has_retricoes FOREIGN key (FK_RESTRICAO) references RESTRICAO (RESTRICAO_ID),
  constraint calendarioOB_has_funcionario foreign key (FK_FUNCIONARIO) references FUNCIONARIO (FUNCIONARIO_ID),
  constraint calendarioOB_has_vacina foreign key (FK_Vacina) references INSUMOS (INSUMOS_ID)
);

#TABELA N/N da TABELA calendario_obrigatorio e TABELA RESTRICAO
CREATE TABLE calendarioOB_has_Restricoes(
  FK_CALENDARIOOB int not null,
  FK_RESTRICAO int not null,
  constraint CALENDARIO_HAS_CALENDARIO foreign key (FK_CALENDARIOOB) references CALENDARIO_OBRIGATORIO (CALENDARIOOB_ID),
  constraint RESTRICAO_HAS_RESTRICAO foreign key (FK_RESTRICAO) references RESTRICAO (RESTRICAO_ID)
);


#TABELA PARA RESTRICAO   CRIADO
CREATE TABLE RESTRICAO (
  RESTRICAO_ID SERIAL PRIMARY KEY,
  RESTRICAO_NOME TEXT NOT NULL,
  RESTRICAO_TIPO TEXT NOT NULL,
  RESTRICAO_STATUS BOOLEAN NOT NULL
);


CREATE TABLE CARDENETA_USUARIO(
    FK_USUARIO int not null,
	FK_CALENDARIOOB int,
	FK_CAMPANHA int,
	FK_FUNCIONARIO not null,
	CADERNETA_DATA date not null,
	CADERNETA_HORA time not null,
	CONSTRAINT CARDENETA_FK_USUARIO FOREIGN KEY (FK_USUARIO) REFERENCES USUARIO (USUARIO_ID),
	CONSTRAINT CARDENETA_FK_CALENDARIOOB FOREIGN KEY (FK_CALENDARIOOB) REFERENCES CALENDARIO_OBRIGATORIO (calendarioOB_id),
	CONSTRAINT CARDENETA_FK_CAMPANHA FOREIGN KEY (FK_CAMPANHA) REFERENCES CAMPANHA (CAMPANHA_ID),
	CONSTRAINT CARDENETA_FK_FUNCIONARIO FOREIGN KEY (FK_FUNCIONARIO) REFERENCES FUNCIONARIO (FUNCIONARIO_ID)
);

#TABELA DO USUARIO   CRIADO
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

CREATE TABLE TRATAMENTO_USUARIO_NN(
    FK_USUARIO INT NOT NULL,
	FK_TRATAMENTO INT NOT NULL,
	CONSTRAINT TRATAMENTOUS_HAS_USUARIO FOREIGN KEY (FK_USUARIO) REFERENCES USUARIO (USUARIO_ID),
	CONSTRAINT TRATAMENTOUS_HAS_TRATAMENTO FOREIGN KEY (FK_TRATAMENTO) REFERENCES TRATAMENTO (TRATAMENTO_ID)
);

CREATE TABLE TRATAMENTO (
   TRATAMENTO_ID SERIAL NOT NULL,
   TRATAMENTO_NOME VARCHAR NOT NULL,
   FK_INSUMOS INT NOT NULL,
   FK_RESTRICAO INT NOT NULL,
   CONSTRAINT TRATAMENTO_HAS_INSUMOS FOREIGN KEY (FK_INSUMOS) REFERENCES INSUMOS (INSUMOS_ID),
   CONSTRAINT TRATAMENTO_HAS_RESTRICAO FOREIGN KEY (FK_RESTRICAO) REFERENCES RESTRICAO (RESTRICAO_ID)
);

#TABELA DA CAMPANHA
CREATE TABLE campanha (
  campanha_id serial primary key,
  campanha_nome TEXT NOT NULL,
  campanha_inicio DATE NOT NULL,
  campanha_final DATE NOT NULL,
  vacina_id integer not null,
  campanha_status BOOLEAN NOT NULL,
	constraint campanha_vacina_vategoria foreign key (vacina_id) references vacina (vacina_id)
);

#TABELA N/N DA TABELA CAMPANHA E DA TABELA RESTRICAO
CREATE TABLE campanha_has_restricao (
  campanha_id INTEGER NOT NULL,
  restricao_id INTEGER NOT NULL,
	constraint campanha_has_restricao_FKIndex1 foreign key (campanha_id) references campanha (campanha_id),
	constraint campanha_has_restricao_FKIndex2 foreign key (restricao_id) references restricao (restricao_id)
);

#TABELA N/N DA TABELA CAMPANHA E DA TABELA USUARIO // SERA REGISTRADO OS USUARIOS ATINTGIDOS NA CAMPANHA E PODEMOS REALIZAR RELATORIOS
CREATE TABLE campanha_has_usuario (
  campanha_id INTEGER NOT NULL,
  usuario_id INTEGER NOT NULL,
  constraint campanha_has_usuario_FKIndex1 foreign key (campanha_id) references campanha (campanha_id),
	constraint campanha_has_usuario_FKIndex2 foreign key (usuario_id) references usuario (usuario_id)
);

#TABELA ENDERECO  CRIADO
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

#TABELA N/N DA TABELA ENDERECO A TABELA USUARIO // USUARIO PODE TER VARIOS ENDERECOS CADASTRADOS CRIADO
CREATE TABLE endereco_has_usuario (
  endereco_id INTEGER NOT NULL,
  usuario_id INTEGER NOT NULL,
	constraint endereco_has_usuario_FKIndex1 foreign key (endereco_id) references endereco (endereco_id),
	constraint endereco_has_usuario_FKIndex2 foreign key (usuario_id) references usuario (usuario_id)
);

#CADASTRO DE POSTO DE SAUDE PARA INFORMAR NO CADASTRO CALENDARIO_USUARIO
CREATE TABLE posto (
  posto_id serial primary key,
  posto_nome TEXT NOT NULL,
  posto_telefone INTEGER NOT NULL,
  posto_FK_endereco INTEGER NOT NULL,
  constraint posto_FKIndex1 foreign key (endereco_id) references endereco (endereco_id)
);

#TABELA N/N DA TABELA USUARIO E TABELA RESTRICAO // FEITO PARA ARMAZENAR AS RESTRICOES DA VACINA 
CREATE TABLE usuario_has_restricao (
  usuario_id INTEGER NOT NULL,
  restricao_id INTEGER NOT NULL,
	constraint usuario_has_restricao_FKIndex1 foreign key (usuario_id) references usuario (usuario_id),
	constraint usuario_has_restricao_FKIndex2 foreign key (restricao_id) references restricao (restricao_id)
);