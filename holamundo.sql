create database holamundo;
use holamundo;
create table usuario(
email varchar(50) primary key,
nombre varchar(20),
contrasena varchar(20),
titulado boolean,
genero varchar(20),
fechaNace date,
cp int
);
INSERT INTO usuario
VALUES
('ana.gomez@example.com', 'Ana Gómez', 'abc123', FALSE, 'Femenino', '1995-08-25', 28002),
('maria.lopez@example.com', 'María López', '123abc', TRUE, 'Femenino', '1988-12-10', 28003),
('pedro.sanchez@example.com', 'Pedro Sánchez', 'pass456', TRUE, 'Masculino', '1985-03-22', 28004),
('luisa.martin@example.com', 'Luisa Martín', 'luisa789', FALSE, 'Femenino', '1993-07-30', 28005);
