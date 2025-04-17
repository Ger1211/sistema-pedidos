INSERT INTO usuario (email, password) VALUES
('cliente@email.com', '$2a$10$H1Vfl2e4idX1TB4VBbMSJun0a7ZoT.effKjl0z0xhmlzvhhFgm3nm'),
('admin@email.com', '$2a$10$cjFKFBoSP8eRUVEU1ikRjuCJlPwmMPnxSMJIGnigbVK55jSls1K9y');

INSERT INTO usuario_roles (usuario_id, roles) VALUES
(1, 'CLIENTE'),
(2, 'ADMIN');