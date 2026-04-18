CREATE TABLE contas (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    titular VARCHAR(100) NOT NULL,
    saldo DECIMAL(10,2) NOT NULL,
    ativa BOOLEAN DEFAULT TRUE
);

CREATE TABLE usuarios (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);


CREATE TABLE transferencias (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    conta_origem_id INT NOT NULL,
    conta_destino_id INT NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    data_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    autenticada BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_conta_origem
        FOREIGN KEY (conta_origem_id) REFERENCES contas(id),
    CONSTRAINT fk_conta_destino
        FOREIGN KEY (conta_destino_id) REFERENCES contas(id)
);