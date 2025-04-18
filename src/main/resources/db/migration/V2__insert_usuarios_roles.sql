INSERT INTO usuario (email, password) VALUES
('cliente@email.com', '$2a$10$H1Vfl2e4idX1TB4VBbMSJun0a7ZoT.effKjl0z0xhmlzvhhFgm3nm'),
('admin@email.com', '$2a$10$cjFKFBoSP8eRUVEU1ikRjuCJlPwmMPnxSMJIGnigbVK55jSls1K9y'),
('cocina@email.com', '$2a$10$G1u6sfZ.CVdVpS5n7uDZ0er25P4JAkIAu3Gx2SgyAbbcoFKvGC3q2');

INSERT INTO usuario_roles (usuario_id, roles) VALUES
(1, 'CLIENTE'),
(2, 'ADMIN'),
(3, 'COCINA');