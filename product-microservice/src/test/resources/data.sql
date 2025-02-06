-- Inserts para a tabela category
INSERT INTO category (name)
VALUES ('Eletrônicos');

INSERT INTO category (name)
VALUES ('Livros');

INSERT INTO category (name)
VALUES ('Roupas');

-- Inserts para a tabela product
INSERT INTO product (name, description, price, category_id, stock)
VALUES ('Smartphone', 'Smartphone de última geração', 1999.99, 1, 50);

INSERT INTO product (name, description, price, category_id, stock)
VALUES ('Notebook', 'Notebook com alta performance', 2999.99, 1, 30);

INSERT INTO product (name, description, price, category_id, stock)
VALUES ('Livro de Ficção', 'Livro de ficção científica', 49.99, 2, 100);

INSERT INTO product (name, description, price, category_id, stock)
VALUES ('Camiseta', 'Camiseta de algodão', 29.99, 3, 200);
