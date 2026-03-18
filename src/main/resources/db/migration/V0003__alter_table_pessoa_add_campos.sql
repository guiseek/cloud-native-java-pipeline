ALTER TABLE pessoa
    ADD COLUMN telefone_principal VARCHAR(20);

ALTER TABLE pessoa
    ADD COLUMN telefone_secundario VARCHAR(20);

ALTER TABLE pessoa
    ADD COLUMN email VARCHAR(150);

ALTER TABLE pessoa
    ADD COLUMN cep VARCHAR(8);

ALTER TABLE pessoa
    ADD COLUMN endereco VARCHAR(200);

ALTER TABLE pessoa
    ADD COLUMN complemento VARCHAR(100);

ALTER TABLE pessoa
    ADD COLUMN bairro VARCHAR(100);

ALTER TABLE pessoa
    ADD COLUMN cidade VARCHAR(100);

ALTER TABLE pessoa
    ADD COLUMN uf VARCHAR(2);

ALTER TABLE pessoa_aud
    ADD COLUMN telefone_principal VARCHAR(20);

ALTER TABLE pessoa_aud
    ADD COLUMN telefone_secundario VARCHAR(20);

ALTER TABLE pessoa_aud
    ADD COLUMN email VARCHAR(150);

ALTER TABLE pessoa_aud
    ADD COLUMN cep VARCHAR(8);

ALTER TABLE pessoa_aud
    ADD COLUMN endereco VARCHAR(200);

ALTER TABLE pessoa_aud
    ADD COLUMN complemento VARCHAR(100);

ALTER TABLE pessoa_aud
    ADD COLUMN bairro VARCHAR(100);

ALTER TABLE pessoa_aud
    ADD COLUMN cidade VARCHAR(100);

ALTER TABLE pessoa_aud
    ADD COLUMN uf VARCHAR(2);

