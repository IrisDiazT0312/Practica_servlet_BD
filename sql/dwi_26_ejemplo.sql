-- Base de datos usada por ConexionMySQL.java (nombreBD = "dwi_26_ejemplo")
CREATE DATABASE IF NOT EXISTS dwi_26_ejemplo;
USE dwi_26_ejemplo;

-- Tabla usada por DAOAlumno.java (INSERT INTO alumnos VALUES(NL, Nombre, Paterno, Materno))
CREATE TABLE IF NOT EXISTS alumnos (
    NL       INT PRIMARY KEY,
    Nombre   VARCHAR(50) NOT NULL,
    Paterno  VARCHAR(50) NOT NULL,
    Materno  VARCHAR(50) NOT NULL
);

-- Usuario que usa ConexionMySQL.java (usuario = "dwi", password = "dwi")
-- Solo necesario si tu MySQL local no tiene ya este usuario.
-- CREATE USER 'dwi'@'localhost' IDENTIFIED BY 'dwi';
-- GRANT ALL PRIVILEGES ON dwi_26_ejemplo.* TO 'dwi'@'localhost';
-- FLUSH PRIVILEGES;
