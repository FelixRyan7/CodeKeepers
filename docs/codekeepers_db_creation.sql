-- create database codekeepers;

use codekeepers;

CREATE TABLE IF NOT EXISTS articulo (
  id_articulo int NOT NULL AUTO_INCREMENT,
  nombre varchar(40) NOT NULL,
  precio float NOT NULL,
  gasto_envio float NOT NULL,
  tiempo_preparacion int NOT NULL,
  stock int NOT NULL,
  PRIMARY KEY (id_articulo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS cliente (
  id_cliente int NOT NULL AUTO_INCREMENT,
  nombre varchar(255) NOT NULL,
  nif varchar(255) NOT NULL,
  domicilio varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  tipo enum('Premium','Estandar') NOT NULL,
  PRIMARY KEY (id_cliente)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS pedido (
  id_pedido int NOT NULL AUTO_INCREMENT,
  id_articulo int NOT NULL,
  id_cliente int NOT NULL,
  cantidad int NOT NULL,
  precio float NOT NULL,
  PRIMARY KEY (id_pedido),
  FOREIGN KEY (id_articulo) REFERENCES articulo (id_articulo),
  FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO articulo (nombre, precio, gasto_envio, tiempo_preparacion, stock) VALUES
('Articulo1', 19.99, 3.50, 30, 100),
('Articulo2', 29.99, 5.00, 25, 50),
('Articulo3', 14.99, 2.00, 20, 75),
('Articulo4', 39.99, 4.50, 35, 120),
('Articulo5', 24.99, 3.00, 28, 80);

INSERT INTO cliente (nombre, nif, domicilio, email, tipo) VALUES
('Cliente1', '12345678A', 'Calle 123, Ciudad', 'cliente1@example.com', 'Premium'),
('Cliente2', '87654321B', 'Avenida 456, Pueblo', 'cliente2@example.com', 'Estandar'),
('Cliente3', '56789012C', 'Plaza Principal, Villa', 'cliente3@example.com', 'Premium'),
('Cliente4', '23456789D', 'Carrera 789, Poblado', 'cliente4@example.com', 'Estandar'),
('Cliente5', '90123456E', 'Camino 987, Aldea', 'cliente5@example.com', 'Premium');

INSERT INTO pedido (id_articulo, id_cliente, cantidad, precio) VALUES
(1, 1, 2, 39.98),
(2, 3, 1, 29.99),
(3, 2, 3, 44.97),
(4, 5, 1, 39.99),
(5, 4, 2, 49.98);