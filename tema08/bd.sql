SET NAMES utf8mb4;
CREATE DATABASE IF NOT EXISTS pmdm CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE pmdm;

DROP TABLE IF EXISTS platos;
CREATE TABLE platos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    receta VARCHAR(100) NOT NULL,
    tipo VARCHAR(100) NOT NULL,
    imagen VARCHAR(255) NOT NULL
);

INSERT INTO platos (receta, tipo, imagen) VALUES
('Albóndigas en salsa', 'Receta casera', 'https://picsum.photos/id/1080/300/300'),
('Canelones', 'Receta casera', 'https://picsum.photos/id/292/300/300'),
('Croquetas de serrano', 'Receta casera', 'https://picsum.photos/id/312/300/300'),
('Lentejas caseras', 'Receta casera', 'https://picsum.photos/id/1011/300/300'),
('Pisto manchego con huevo', 'Receta manchega', 'https://picsum.photos/id/1015/300/300'),
('Salmorejo', 'Receta tradicional', 'https://picsum.photos/id/1060/300/300'),
('Ternera a la jardinera', 'Receta premium', 'https://picsum.photos/id/1062/300/300'),
('Tortilla de patatas', 'Receta premium', 'https://picsum.photos/id/1069/300/300'),
('Ramen estilo japonés', 'Receta oriental', 'https://picsum.photos/id/1074/300/300'),
('Sushi', 'Receta oriental', 'https://picsum.photos/id/1084/300/300'),
('Risotto parmigiano', 'Receta italiana', 'https://picsum.photos/id/1025/300/300');