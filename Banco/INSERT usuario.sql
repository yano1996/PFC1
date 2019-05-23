INSERT INTO endereco(
	endereco_id, endereco_logadouro, endereco_numero, endereco_cidade, endereco_bairro, endereco_complemento, endereco_cep, endereco_uf, endereco_status)
	VALUES (1001, 'a', 3, 'b', 'c', 'd', 1, 'e', true);

INSERT INTO usuario(
	usuario_id, usuario_nome, usuario_idade, usuario_cpf, usuario_rg, usuario_senha, usuario_telefone, usuario_celular, usuario_tiposangue, usuario_peso, usuario_altura, usuario_nascimento, usuario_status ,usuario_email, fk_endereco)
	VALUES (1001, 'Q_Q', 11, '1234', '222222222', '2', 11111111, 1111111, 'erty', 1, 1, '1999-11-11', true,'1@1.com', 1001);


	INSERT INTO funcionario(
	funcionario_id, funcionario_confen, funcionario_senha, funcionario_acesso, funcionario_status, fk_usuario)
	VALUES (1001, '2', '2', 'ADMINISTRADOR', true, 1001);