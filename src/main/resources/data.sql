-- CREATE DATABASE loja-e-commerce;

-- USE loja-e-commerce;

-- CREATE TABLE compra (
--     id BIGINT NOT NULL AUTO_INCREMENT,
--     itens VARCHAR(255),
--     uuid VARCHAR(255),
--     total DECIMAL(19,2),
--     PRIMARY KEY (id)
-- );

-- CREATE TABLE produto (
--     codigo BIGINT NOT NULL AUTO_INCREMENT,
--     descricao VARCHAR(255),
--     estoque BIGINT,
--     preco DECIMAL(19,2),
--     dataInsercao DATETIME,
--     urlImagem VARCHAR(255),
--     tipoProduto ENUM('CAMISA', 'LIVRO', 'GERAL'),
--     PRIMARY KEY (codigo)
-- );

-- CREATE TABLE role (
--     nome VARCHAR(255) NOT NULL,
--     PRIMARY KEY (nome)
-- );

-- CREATE TABLE usuario (
--     email VARCHAR(255) NOT NULL,
--     nome VARCHAR(255),
--     senha VARCHAR(255),
--     PRIMARY KEY (email)
-- );

-- CREATE TABLE usuario_roles (
--     usuario_email VARCHAR(255) NOT NULL,
--     roles_nome VARCHAR(255) NOT NULL,
--     FOREIGN KEY (usuario_email) REFERENCES usuario(email),
--     FOREIGN KEY (roles_nome) REFERENCES role(nome)
-- );

--  INSERT INTO `loja-e-commerce`.produto (descricao, estoque, preco, data_insercao, url_imagem, tipo_produto)
--  VALUES ('Camisa Polo', 10, 59.99, '2022-01-01', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJiBWrxSyRjGwKOk8loIVAc7NKFGNJ9TcaMg&usqp=CAU', 'CAMISA'),
--         ('Livro de Receitas', 5, 29.99, '2022-02-01', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSIrznK5vOA2Siv9k-8FUp2XM-ehNQS0yrBhdeE0vNgYIKnk27cyXkSLGFzox80w7iNcbc&usqp=CAU', 'LIVRO'),
--         ('Caneca de Porcelana', 20, 19.99, '2022-03-01', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTYuUXRoTBS_OOwQF573iQVUNBc385-Azq41loTQYBdKCUdzqs7mH_9NSN8zVhaTSYydBc&usqp=CAU', 'GERAL'),
--         ('Camiseta Regata', 15, 39.99, '2022-04-01', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSP_U3JCRTZNJEjYgv9UFDr7iZLW7JBHiyZCQ&usqp=CAU', 'CAMISA');