-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-08-2020 a las 19:03:39
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `preguntados`
--
CREATE DATABASE IF NOT EXISTS `preguntados` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `preguntados`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `opcion`
--

DROP TABLE IF EXISTS `opcion`;
CREATE TABLE `opcion` (
  `opc_id` int(11) NOT NULL,
  `opc_opcion` varchar(45) DEFAULT NULL,
  `fk_pregunta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pregunta`
--

DROP TABLE IF EXISTS `pregunta`;
CREATE TABLE `pregunta` (
  `pre_id` int(11) NOT NULL,
  `pre_pregunta` varchar(45) DEFAULT NULL,
  `fk_tema` int(11) NOT NULL,
  `fk_opcion_correcta` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sala`
--

DROP TABLE IF EXISTS `sala`;
CREATE TABLE `sala` (
  `sal_id` int(11) NOT NULL,
  `sal_nombre` varchar(45) DEFAULT NULL,
  `sal_estado` varchar(45) DEFAULT NULL,
  `fk_usuario_owner` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sala_usuario`
--

DROP TABLE IF EXISTS `sala_usuario`;
CREATE TABLE `sala_usuario` (
  `fk_usuario` int(11) NOT NULL,
  `fk_sala` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tema`
--

DROP TABLE IF EXISTS `tema`;
CREATE TABLE `tema` (
  `tem_id` int(11) NOT NULL,
  `tem_nombre` varchar(45) DEFAULT NULL,
  `tem_icono` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tema_sala`
--

DROP TABLE IF EXISTS `tema_sala`;
CREATE TABLE `tema_sala` (
  `fk_sala` int(11) NOT NULL,
  `fk_tema` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `usu_id` int(11) NOT NULL,
  `usu_login` varchar(45) DEFAULT NULL,
  `usu_pass` blob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `opcion`
--
ALTER TABLE `opcion`
  ADD PRIMARY KEY (`opc_id`),
  ADD KEY `fk_opcion_pregunta1_idx` (`fk_pregunta`);

--
-- Indices de la tabla `pregunta`
--
ALTER TABLE `pregunta`
  ADD PRIMARY KEY (`pre_id`),
  ADD KEY `fk_pregunta_tema1_idx` (`fk_tema`),
  ADD KEY `fk_pregunta_opcion1_idx` (`fk_opcion_correcta`);

--
-- Indices de la tabla `sala`
--
ALTER TABLE `sala`
  ADD PRIMARY KEY (`sal_id`),
  ADD KEY `fk_sala_usuario1_idx` (`fk_usuario_owner`);

--
-- Indices de la tabla `sala_usuario`
--
ALTER TABLE `sala_usuario`
  ADD KEY `fk_sala_usuario_usuario_idx` (`fk_usuario`),
  ADD KEY `fk_sala_usuario_sala1_idx` (`fk_sala`);

--
-- Indices de la tabla `tema`
--
ALTER TABLE `tema`
  ADD PRIMARY KEY (`tem_id`);

--
-- Indices de la tabla `tema_sala`
--
ALTER TABLE `tema_sala`
  ADD KEY `fk_tema_sala_sala1_idx` (`fk_sala`),
  ADD KEY `fk_tema_sala_tema1_idx` (`fk_tema`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`usu_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `usu_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `opcion`
--
ALTER TABLE `opcion`
  ADD CONSTRAINT `fk_opcion_pregunta1` FOREIGN KEY (`fk_pregunta`) REFERENCES `pregunta` (`pre_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `pregunta`
--
ALTER TABLE `pregunta`
  ADD CONSTRAINT `fk_pregunta_opcion1` FOREIGN KEY (`fk_opcion_correcta`) REFERENCES `opcion` (`opc_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_pregunta_tema1` FOREIGN KEY (`fk_tema`) REFERENCES `tema` (`tem_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `sala`
--
ALTER TABLE `sala`
  ADD CONSTRAINT `fk_sala_usuario1` FOREIGN KEY (`fk_usuario_owner`) REFERENCES `usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `sala_usuario`
--
ALTER TABLE `sala_usuario`
  ADD CONSTRAINT `fk_sala_usuario_sala1` FOREIGN KEY (`fk_sala`) REFERENCES `sala` (`sal_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_sala_usuario_usuario` FOREIGN KEY (`fk_usuario`) REFERENCES `usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tema_sala`
--
ALTER TABLE `tema_sala`
  ADD CONSTRAINT `fk_tema_sala_sala1` FOREIGN KEY (`fk_sala`) REFERENCES `sala` (`sal_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_tema_sala_tema1` FOREIGN KEY (`fk_tema`) REFERENCES `tema` (`tem_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
