-- Tabla Usuarios
CREATE TABLE IF NOT EXISTS Usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo_documento VARCHAR(255) NOT NULL,      -- Correspondiente al enum TipoDocumento
    numero_id VARCHAR(255) NOT NULL,
    nombres VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    direccion VARCHAR(255),
    pais VARCHAR(255),
    ciudad VARCHAR(255),
    fecha_nacimiento DATE,
    sexo VARCHAR(255),                        -- Correspondiente al enum Sexo
    correo VARCHAR(255),
    telefono VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    admin BOOLEAN DEFAULT FALSE               -- Valor por defecto es false
);

-- Tabla Reservaciones
CREATE TABLE IF NOT EXISTS Reservaciones (
    reservacion_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha_evento DATE NOT NULL,
    hora TIME NOT NULL,
    salon_id BIGINT,               -- Relación con el Salon
    persona_id BIGINT,             -- Relación con la Persona
    total DECIMAL(10,2),           -- Total de la reservación
    tipo_evento VARCHAR(255),      -- Tipo de evento
    cantidad_personas INT,         -- Cantidad de personas en el evento
    observaciones TEXT,            -- Observaciones para la reservación
    FOREIGN KEY (salon_id) REFERENCES Salon(salon_id),
    FOREIGN KEY (persona_id) REFERENCES Persona(id)
);



-- Tabla Salon
CREATE TABLE IF NOT EXISTS Salon (
    salon_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_salon VARCHAR(255) NOT NULL,
    descripcion TEXT,
    capacidad INT,
    precio DECIMAL(10,2) NOT NULL
);
