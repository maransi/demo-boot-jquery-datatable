create table pessoa( sequencial   SERIAL NOT NULL PRIMARY KEY,
                      cpf  VARCHAR(15),
                      nome VARCHAR(100),
                      datNasc DATE,
                      endereco VARCHAR(150),
                      cidade VARCHAR(100),
                      estado VARCHAR(5),
                      cep VARCHAR(10),
                      foneRes VARCHAR(30),
                      salario DECIMAL(11,2));

CREATE INDEX idxPessoa_CPF ON pessoa( cpf );

