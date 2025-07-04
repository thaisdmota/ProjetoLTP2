CREATE DATABASE agencia_viagens;

USE agencia_viagens;

-- Tabela de Clientes
CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo ENUM('NACIONAL', 'ESTRANGEIRO') NOT NULL,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL,
    cpf VARCHAR(11),
    passaporte VARCHAR(20),
    UNIQUE(email),
    CONSTRAINT chk_documento CHECK (
        (tipo = 'NACIONAL' AND cpf IS NOT NULL AND passaporte IS NULL) OR
        (tipo = 'ESTRANGEIRO' AND passaporte IS NOT NULL AND cpf IS NULL)
    )
);

-- Tabela de Pacotes
CREATE TABLE pacotes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    destino VARCHAR(100) NOT NULL,
    tipo ENUM('LUXO', 'CULTURAL', 'AVENTURA', 'PADRAO') NOT NULL,
    valor_passagem DECIMAL(10,2) NOT NULL,
    valor_diaria DECIMAL(10,2) NOT NULL,
    duracao_dias INT NOT NULL
);
INSERT INTO pacotes (nome, destino, tipo, valor_passagem, valor_diaria, duracao_dias)
VALUES 
('Ano novo no Rio', 'RJ', 'LUXO', 5500.00, 6000.00, 5),
('Aventura na Amazônia', 'AM', 'AVENTURA', 1200.00, 450.00, 4),
('Carnaval em Salvador', 'BA', 'CULTURAL', 900.00, 400.00, 6),
('Lençóis Maranhenses', 'MA', 'AVENTURA', 1000.00, 450.00, 7),
('São Paulo', 'SP', 'PADRAO', 950.00, 400.00, 3),
('Brasília', 'DF', 'CULTURAL', 900.00, 550.00, 4),
('Belo Horizonte', 'MG', 'LUXO', 4200.00, 5200.00, 5);

-- Tabela de Serviços Adicionais
CREATE TABLE servicos_adicionais (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    tipo VARCHAR(50),
    preco DECIMAL(10,2) NOT NULL
);

INSERT INTO servicos_adicionais (nome, preco) VALUES
('Translado aeroporto-hotel', 150.0), ('Acesso à academia', 350.0);

-- Tabela de Reservas
CREATE TABLE reservas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    pacote_id INT NOT NULL,
    dias INT NOT NULL,
    quantidade_pessoas INT NOT NULL,
    valor_final DECIMAL(10,2) NOT NULL,
    data_reserva TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    FOREIGN KEY (pacote_id) REFERENCES pacotes(id)
);

-- Tabela de relação entre Reservas e Serviços Adicionais
CREATE TABLE reserva_servicos (
    reserva_id INT NOT NULL,
    servico_id INT NOT NULL,
    PRIMARY KEY (reserva_id, servico_id),
    FOREIGN KEY (reserva_id) REFERENCES reservas(id),
    FOREIGN KEY (servico_id) REFERENCES servicos_adicionais(id)
);

-- Tabela de Funcionários
CREATE TABLE funcionarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL
);

Select * from clientes;
Select * from pacotes;
Select * from servicos_adicionais;
select * from reservas;
select * from reserva_servicos;