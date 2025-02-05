-- Inserts para a tabela category
INSERT INTO category (id, name)
VALUES (1, 'Eletrônicos');

INSERT INTO category (id, name)
VALUES (2, 'Livros');

INSERT INTO category (id, name)
VALUES (3, 'Roupas');

-- Inserts para a tabela product
INSERT INTO product (id, name, description, price, category_id, stock)
VALUES (1, 'Smartphone', 'Smartphone de última geração', 1999.99, 1, 50);

INSERT INTO product (id, name, description, price, category_id, stock)
VALUES (2, 'Notebook', 'Notebook com alta performance', 2999.99, 1, 30);

INSERT INTO product (id, name, description, price, category_id, stock)
VALUES (3, 'Livro de Ficção', 'Livro de ficção científica', 49.99, 2, 100);

INSERT INTO product (id, name, description, price, category_id, stock)
VALUES (4, 'Camiseta', 'Camiseta de algodão', 29.99, 3, 200);