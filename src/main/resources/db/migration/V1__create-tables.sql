CREATE TABLE topico (
                        id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        titulo VARCHAR(100) NOT NULL,
                        mensagem LONGTEXT NOT NULL,
                        data_criacao INT,
                        status BOOLEAN,
                        curso VARCHAR(100),
                        autor VARCHAR(50)
);
