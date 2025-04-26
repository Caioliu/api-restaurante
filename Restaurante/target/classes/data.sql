-- Inserir produtos tipo BEBIDA
INSERT INTO produto (id, preco, nome, produto_tipo)
VALUES
    (nextval('sq_produto'), 5.99, 'Refrigerante de Cola', 'BEBIDA'),
    (nextval('sq_produto'), 4.50, 'Suco de Laranja Natural', 'BEBIDA'),
    (nextval('sq_produto'), 6.75, 'Água Mineral sem Gás', 'BEBIDA'),
    (nextval('sq_produto'), 7.25, 'Chá Gelado de Pêssego', 'BEBIDA'),
    (nextval('sq_produto'), 8.50, 'Cerveja Artesanal IPA', 'BEBIDA');

-- Inserir produtos tipo PRATO
INSERT INTO produto (id, preco, nome, produto_tipo)
VALUES
    (nextval('sq_produto'), 29.90, 'Filé Mignon ao Molho Madeira', 'PRATO'),
    (nextval('sq_produto'), 25.50, 'Risoto de Funghi Porcini', 'PRATO'),
    (nextval('sq_produto'), 22.75, 'Lasanha à Bolonhesa', 'PRATO'),
    (nextval('sq_produto'), 18.99, 'Feijoada Completa', 'PRATO'),
    (nextval('sq_produto'), 26.50, 'Salmão Grelhado com Risoto de Limão Siciliano', 'PRATO');
