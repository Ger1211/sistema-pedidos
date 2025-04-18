CREATE TABLE usuario (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE usuario_roles (
    usuario_id BIGINT NOT NULL,
    roles VARCHAR(50) NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
);

CREATE TABLE producto (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255),
    descripcion TEXT,
    precio DECIMAL(10, 2),
    stock INT
);