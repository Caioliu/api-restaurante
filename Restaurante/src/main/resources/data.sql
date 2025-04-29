WITH novos_produtos(nome, preco, produto_tipo) AS (
  VALUES
    ('Refrigerante de Cola',            5.99, 'BEBIDA'),
    ('Suco de Laranja Natural',         4.50, 'BEBIDA'),
    ('Água Mineral sem Gás',            6.75, 'BEBIDA'),
    ('Chá Gelado de Pêssego',           7.25, 'BEBIDA'),
    ('Cerveja Artesanal IPA',           8.50, 'BEBIDA'),
    ('Filé Mignon ao Molho Madeira',   29.90, 'PRATO'),
    ('Risoto de Funghi Porcini',       25.50, 'PRATO'),
    ('Lasanha à Bolonhesa',            22.75, 'PRATO'),
    ('Feijoada Completa',              18.99, 'PRATO'),
    ('Salmão Grelhado com Risoto de Limão Siciliano', 26.50, 'PRATO')
)
INSERT INTO produto (id, preco, nome, produto_tipo)
SELECT nextval('sq_produto'),
       np.preco,
       np.nome,
       np.produto_tipo
FROM novos_produtos np
WHERE NOT EXISTS (
  SELECT 1
  FROM produto p
  WHERE p.nome = np.nome
);