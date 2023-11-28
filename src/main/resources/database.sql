CREATE DATABASE "almacenMusical"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Peru.1252'
    LC_CTYPE = 'Spanish_Peru.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

create table if not exists articulos(
	articulo_id serial PRIMARY KEY,
	nombre varchar(255),
	isLoaned boolean,
    desDuenio varchar(255) NULL, -- en caso fuere un instrumento
    autor varchar(255) NULL, -- en caso fuere una partitura
    duration integer NULL, -- en caso fuere una partitura
    tipoArticulo varchar(20),
	estado integer
);

create table if not exists usuarios(
	usuario_id serial PRIMARY KEY,
	nombre varchar(255),
	estado integer
);

create table if not exists prestamos(
	prestamo_id serial PRIMARY KEY,
	usuario integer,
	articulo integer,
	estado integer,

	foreign key(usuario) references usuarios(usuario_id),
	foreign key(articulo) references articulos(articulo_id)
);

INSERT INTO articulos (nombre, isLoaned, desDuenio, tipoArticulo, estado)
VALUES ('Guitarra eléctrica', false, 'Juan Pérez', 'Instrumento', 1);

INSERT INTO articulos (nombre, isLoaned, desDuenio, tipoArticulo, estado)
VALUES ('Bajo eléctrico', false, 'María García', 'Instrumento', 1);

INSERT INTO articulos (nombre, isLoaned, desDuenio, tipoArticulo, estado)
VALUES ('Batería', false, 'Pedro Rodríguez', 'Instrumento', 1);

INSERT INTO articulos (nombre, isLoaned, desDuenio, tipoArticulo, estado)
VALUES ('Piano', false, 'Ana López', 'Instrumento', 1);

INSERT INTO articulos (nombre, isLoaned, desDuenio, tipoArticulo, estado)
VALUES ('Violin', false, 'Luis Sánchez', 'Instrumento', 1);

INSERT INTO articulos (nombre, isLoaned, desDuenio, tipoArticulo, estado)
VALUES ('Flauta', false, 'Carmen Gómez', 'Instrumento', 1);

INSERT INTO articulos (nombre, isLoaned, desDuenio, tipoArticulo, estado)
VALUES ('Trompeta', false, 'José Fernández', 'Instrumento', 1);

INSERT INTO articulos (nombre, isLoaned, desDuenio, tipoArticulo, estado)
VALUES ('Saxofón', false, 'Daniela Pérez', 'Instrumento', 1);

INSERT INTO articulos (nombre, isLoaned, desDuenio, tipoArticulo, estado)
VALUES ('Órgano', false, 'Martín González', 'Instrumento', 1);

INSERT INTO articulos (nombre, isLoaned, desDuenio, tipoArticulo, estado)
VALUES ('Harp', false, 'Susana Hernández', 'Instrumento', 1);

INSERT INTO articulos (nombre, isLoaned, autor, duration, tipoArticulo, estado)
VALUES ('Concierto para piano y orquesta de Beethoven', false, 'Ludwig van Beethoven', 45, 'Partitura', 1);

INSERT INTO articulos (nombre, isLoaned, autor, duration, tipoArticulo, estado)
VALUES ('La Traviata', false, 'Giuseppe Verdi', 220, 'Partitura', 1);

INSERT INTO articulos (nombre, isLoaned, autor, duration, tipoArticulo, estado)
VALUES ('El Quijote', false, 'Maurice Ravel', 17, 'Partitura', 1);

INSERT INTO articulos (nombre, isLoaned, autor, duration, tipoArticulo, estado)
VALUES ('La Valse', false, 'Maurice Ravel', 18, 'Partitura', 1);

INSERT INTO articulos (nombre, isLoaned, autor, duration, tipoArticulo, estado)
VALUES ('El Danubio Azul', false, 'Johann Strauss II', 18, 'Partitura', 1);

INSERT INTO articulos (nombre, isLoaned, autor, duration, tipoArticulo, estado)
VALUES ('La Novena Sinfonía de Beethoven', false, 'Ludwig van Beethoven', 75, 'Partitura', 1);

INSERT INTO articulos (nombre, isLoaned, autor, duration, tipoArticulo, estado)
VALUES ('La Bohème', false, 'Giacomo Puccini', 160, 'Partitura', 1);

INSERT INTO articulos (nombre, isLoaned, autor, duration, tipoArticulo, estado)
VALUES ('La consagración de la Primavera de Stravinsky', false, 'Igor Stravinsky', 35, 'Partitura', 1);

INSERT INTO articulos (nombre, isLoaned, autor, duration, tipoArticulo, estado)
VALUES ('La vida breve', false, 'Manuel de Falla', 105, 'Partitura', 1);

INSERT INTO articulos (nombre, isLoaned, autor, duration, tipoArticulo, estado)
VALUES ('El amor brujo', false, 'Manuel de Falla', 55, 'Partitura', 1);

INSERT INTO usuarios (nombre, estado)
VALUES ('Daniela Pérez', 1);

INSERT INTO usuarios (nombre, estado)
VALUES ('Antonio Rodríguez', 1);

INSERT INTO usuarios (nombre, estado)
VALUES ('Isabel Díaz', 1);

INSERT INTO usuarios (nombre, estado)
VALUES ('Jorge García', 1);

INSERT INTO usuarios (nombre, estado)
VALUES ('Marta López', 1);

INSERT INTO usuarios (nombre, estado)
VALUES ('Miguel Fernández', 1);

INSERT INTO usuarios (nombre, estado)
VALUES ('Laura Sánchez', 1);

INSERT INTO usuarios (nombre, estado)
VALUES ('Juan Pérez', 1);

INSERT INTO usuarios (nombre, estado)
VALUES ('Luisa Martínez', 1);

INSERT INTO usuarios (nombre, estado)
VALUES ('Pedro Gómez', 1);
