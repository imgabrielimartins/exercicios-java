DROP TABLE IF EXISTS emprestimo;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS item;

CREATE TABLE item (
    id         INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    codigo     INTEGER      NOT NULL UNIQUE,
    titulo     VARCHAR(200) NOT NULL,
    tipo       VARCHAR(10)  NOT NULL CHECK (tipo IN ('livro', 'revista')),
    autor      VARCHAR(100),            
    edicao     INTEGER,                 
    disponivel BOOLEAN      NOT NULL DEFAULT TRUE
);

CREATE TABLE usuario (
    id           INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome         VARCHAR(100) NOT NULL,
    tipo         VARCHAR(10)  NOT NULL CHECK (tipo IN ('aluno', 'professor')),
    limite_itens INTEGER      NOT NULL CHECK (limite_itens > 0)
);

CREATE TABLE emprestimo (
    id                      INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    item_id                 INTEGER       NOT NULL REFERENCES item(id),
    usuario_id              INTEGER       NOT NULL REFERENCES usuario(id),
    data_retirada           DATE          NOT NULL,
    data_devolucao_prevista DATE          NOT NULL,
    data_devolucao          DATE,        
    valor_multa             NUMERIC(8, 2) NOT NULL DEFAULT 0 CHECK (valor_multa >= 0),
    CHECK (data_devolucao_prevista >= data_retirada)
);

INSERT INTO item (codigo, titulo, tipo, autor, edicao, disponivel) VALUES
    (1, 'Java: Como Programar',  'livro',   'Paul Deitel', NULL, FALSE),  
    (2, 'Clean Code',            'livro',   'Robert Martin', NULL, TRUE),
    (3, 'Revista Programar',     'revista', NULL, 42, TRUE),              
    (4, 'Superinteressante',     'revista', NULL, 395, TRUE),
    (5, 'Estruturas de Dados',   'livro',   'Nina Edelweiss', NULL, TRUE);

INSERT INTO usuario (nome, tipo, limite_itens) VALUES
    ('Ana',    'aluno',     3),
    ('Carlos', 'professor', 5);

INSERT INTO emprestimo
    (item_id, usuario_id, data_retirada, data_devolucao_prevista, data_devolucao, valor_multa)
VALUES
    (1, 1, '2026-11-10', '2026-11-24', NULL, 0),
    (3, 2, '2026-10-01', '2026-10-08', '2026-10-11', 3.00);

SELECT * FROM item;
SELECT * FROM usuario;
SELECT * FROM emprestimo;