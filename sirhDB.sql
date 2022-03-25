-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-03-2022 a las 03:18:07
-- Versión del servidor: 10.4.19-MariaDB
-- Versión de PHP: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sirh`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador`
--

CREATE TABLE `administrador` (
  `user` varchar(13) NOT NULL,
  `password` varchar(13) NOT NULL,
  `documento` varchar(13) NOT NULL,
  `estado` varchar(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `administrador`
--

INSERT INTO `administrador` (`user`, `password`, `documento`, `estado`) VALUES
('isabella', '1000401084', '1000401084', 'close');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `tipoDocumento` varchar(40) NOT NULL,
  `Cedula` varchar(10) NOT NULL,
  `Nombres` varchar(40) DEFAULT NULL,
  `Apellidos` varchar(40) DEFAULT NULL,
  `Fecha_Nacimiento` date DEFAULT NULL,
  `Telefono` varchar(10) DEFAULT NULL,
  `Direccion` varchar(20) DEFAULT NULL,
  `Cargo` varchar(25) NOT NULL,
  `RH` varchar(3) DEFAULT NULL,
  `EPS` varchar(30) DEFAULT NULL,
  `ARL` varchar(30) DEFAULT NULL,
  `SalarioBase` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`tipoDocumento`, `Cedula`, `Nombres`, `Apellidos`, `Fecha_Nacimiento`, `Telefono`, `Direccion`, `Cargo`, `RH`, `EPS`, `ARL`, `SalarioBase`) VALUES
('Cedula de Ciudadania', '1000401084', 'Isabella', 'Carmona Castro', '2001-11-16', '2587391', 'Calle 20', '', 'O+', 'Sanitas', 'SURA', 5000000),
('Cedula de Extranjeria', '125463', 'Liliana', 'Gomez', '1980-01-10', '3254675', 'Calle 41', '', 'AB+', 'Sanitas', 'SURA', 1200000),
('Cedula de Extranjeria', '34876912', 'Marta', 'Hurtado Bernal', '1971-07-22', '3006218422', 'Carrera 81', '', 'AB+', 'SURA', 'SURA', 1000000),
('PEP', '439391', 'Mariana', 'Perez Vanegas', '1979-10-26', '5687214', 'Carrera 106', '', 'AB-', 'Compensar', 'SURA', 1000000),
('Cedula de Extranjeria', '442113', 'Edgar', 'Martinez Colorado', '1960-08-10', '3146529732', 'Calle 32B', '', 'B+', 'CafeSalud', 'SURA', 1200000),
('Cedula de Ciudadania', '76238632', 'Luisa Fernanda', 'Ramirez Gonzalez', '1978-02-28', '3516732238', 'Avenida 33', '', 'B-', 'Salud Total', 'SURA', 2000000),
('Cedula de Ciudadania', '79321756', 'Fernando', 'Gomez', '1976-08-12', '3004658712', 'Calle 32', '', 'O+', 'Sanitas', 'SURA', 1200000),
('PEP', '98152630', 'Hernan', 'Toro Velez', '1966-12-02', '2817634', 'Calle 54', '', 'A-', 'Colmena', 'SURA', 1200000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `novedades`
--

CREATE TABLE `novedades` (
  `Id_Novedades` int(11) NOT NULL,
  `Descripcion` text DEFAULT NULL,
  `Fecha_Novedad` date DEFAULT NULL,
  `Id_Empleado` varchar(40) DEFAULT NULL,
  `tipoNovedad` varchar(30) NOT NULL,
  `tipoDocumento` varchar(30) NOT NULL,
  `archivoNovedad` longblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salario`
--

CREATE TABLE `salario` (
  `Id_Salario` int(11) NOT NULL,
  `Horas_Trabajadas` float DEFAULT NULL,
  `Valor_Hora` float DEFAULT NULL,
  `porc_ARL` float NOT NULL,
  `porc_EPS` float NOT NULL,
  `Id_Empleados` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `turnos`
--

CREATE TABLE `turnos` (
  `Id_Turno` int(11) NOT NULL,
  `Fecha_Inicio` date DEFAULT NULL,
  `Fecha_Fin` date DEFAULT NULL,
  `Hora_Inicio` time DEFAULT NULL,
  `Hora_Fin` time DEFAULT NULL,
  `Id_Empleado` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `turnos`
--

INSERT INTO `turnos` (`Id_Turno`, `Fecha_Inicio`, `Fecha_Fin`, `Hora_Inicio`, `Hora_Fin`, `Id_Empleado`) VALUES
(1, '2022-02-01', '2022-02-01', '19:00:00', '19:00:00', '125463'),
(2, '2022-02-01', '2022-02-01', '00:00:00', '19:00:00', '1000401084'),
(3, '2022-02-04', '2022-02-04', '00:00:00', '19:00:00', '439391'),
(4, '2022-02-05', '2022-02-05', '00:00:00', '19:00:00', '98152630'),
(5, '2022-02-06', '2022-02-06', '00:00:00', '19:00:00', '439391'),
(6, '2022-02-06', '2022-02-06', '07:30:00', '00:00:00', '1000401084'),
(7, '2022-02-09', '2022-02-09', '09:00:00', '18:00:00', '98152630'),
(8, '2022-02-02', '2022-02-02', '10:00:00', '17:00:00', '442113');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD PRIMARY KEY (`documento`);

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`Cedula`);

--
-- Indices de la tabla `novedades`
--
ALTER TABLE `novedades`
  ADD PRIMARY KEY (`Id_Novedades`),
  ADD KEY `Id_Empleado` (`Id_Empleado`);

--
-- Indices de la tabla `salario`
--
ALTER TABLE `salario`
  ADD PRIMARY KEY (`Id_Salario`),
  ADD KEY `Id_Empleados` (`Id_Empleados`);

--
-- Indices de la tabla `turnos`
--
ALTER TABLE `turnos`
  ADD PRIMARY KEY (`Id_Turno`),
  ADD KEY `Id_Empleado` (`Id_Empleado`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `novedades`
--
ALTER TABLE `novedades`
  MODIFY `Id_Novedades` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `salario`
--
ALTER TABLE `salario`
  MODIFY `Id_Salario` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `turnos`
--
ALTER TABLE `turnos`
  MODIFY `Id_Turno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `novedades`
--
ALTER TABLE `novedades`
  ADD CONSTRAINT `novedades_ibfk_1` FOREIGN KEY (`Id_Empleado`) REFERENCES `empleados` (`Cedula`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `salario`
--
ALTER TABLE `salario`
  ADD CONSTRAINT `salario_ibfk_2` FOREIGN KEY (`Id_Empleados`) REFERENCES `empleados` (`Cedula`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `turnos`
--
ALTER TABLE `turnos`
  ADD CONSTRAINT `turnos_ibfk_2` FOREIGN KEY (`Id_Empleado`) REFERENCES `empleados` (`Cedula`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
