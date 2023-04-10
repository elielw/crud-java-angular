CREATE TABLE IF NOT EXISTS clientes (
	id_cliente INT AUTO_INCREMENT,
	nome VARCHAR(10) NOT NULL,
	endereco VARCHAR(100) NOT NULL,
	bairro VARCHAR(100) NOT NULL,

	CONSTRAINT clientes_pk PRIMARY KEY (id_cliente)
);

CREATE TABLE IF NOT EXISTS telefones (
	id_telefone INT AUTO_INCREMENT,
	telefone VARCHAR(20) NOT NULL,
	id_cliente INT NOT NULL,

	CONSTRAINT telefones_pk PRIMARY KEY (id_telefone),
	CONSTRAINT telefone_cliente_fk FOREIGN KEY (id_cliente) references clientes(id_cliente)
);
