-- Tabla Usuarios
CREATE TABLE IF NOT EXISTS Usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_id VARCHAR(20) NOT NULL,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    direccion VARCHAR(150),
    pais VARCHAR(80),
    ciudad VARCHAR(80),
    fecha_nacimiento DATE,
    correo VARCHAR(150) NOT NULL,
    telefono VARCHAR(80),
    admin TINYINT(1) DEFAULT 0,
    tipo_usuario VARCHAR(50) NOT NULL,
    reservacion_id BIGINT, -- Esta columna es necesaria para la clave foránea
    FOREIGN KEY (reservacion_id) REFERENCES Reservaciones(id) -- Clave foránea que referencia Reservaciones
);

-- Tabla Reservaciones
CREATE TABLE IF NOT EXISTS Reservaciones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    salon_id BIGINT,
    total DECIMAL(10,2),
    FOREIGN KEY (salon_id) REFERENCES Salon(id)
);

-- Tabla Productos_Servicios
CREATE TABLE IF NOT EXISTS Productos_Servicios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    cantidad INT,
    precio DOUBLE(10,2) NOT NULL,
    disponibilidad TINYINT(1) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    tiempo_servicio TIME
);
