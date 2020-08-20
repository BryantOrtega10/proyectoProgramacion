-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-08-2020 a las 08:44:57
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `opcion`
--

CREATE TABLE `opcion` (
  `opc_id` int(11) NOT NULL,
  `opc_opcion` varchar(45) DEFAULT NULL,
  `fk_pregunta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `opcion`
--

INSERT INTO `opcion` (`opc_id`, `opc_opcion`, `fk_pregunta`) VALUES
(9, 'A.Ecuador', 3),
(10, 'B.Bolivia', 3),
(11, 'C.Venezuela', 3),
(12, 'D.Colombia', 3),
(13, 'A.Machu picchu', 4),
(14, 'B.Cuzco ', 4),
(15, 'C.Lima', 4),
(16, 'D.Lima', 4),
(17, 'A.Constantino', 5),
(18, 'B.Julio Cesar', 5),
(19, 'C.Carlo Magno', 5),
(20, 'D.Cesar Augusto', 5),
(21, 'A.Museo del prado', 6),
(22, 'B.British Museum ', 6),
(23, 'C.Louvre', 6),
(24, 'D.Galeria Uffizi', 6),
(25, 'A.Siglo xv', 7),
(26, 'B.Siglo xx', 7),
(27, 'C.Siglo xix', 7),
(28, 'D.Siglo xxi', 7),
(29, 'A.Davinci', 8),
(30, 'B.Fernando Botero', 8),
(31, 'C.Van Gogh', 8),
(32, 'D.Vann Goht', 8),
(33, 'A.Cien años de soledad', 9),
(34, 'B.La rebelion en la granja ', 9),
(35, 'C.Cronicas de una muerte anunciada', 9),
(36, 'D.Ojos de perro azul', 9),
(37, 'A.Homero', 10),
(38, 'B.Marco Tulio', 10),
(39, 'C.Seneca', 10),
(40, 'D.Herodoto', 10),
(41, 'A.Dante Alieghieri', 11),
(42, 'B.Maquiavelo', 11),
(43, 'C.Giovanni Bocaccio', 11),
(44, 'D.Homero', 11),
(45, 'Leo T', 12),
(46, 'Vía Láctea', 12),
(47, 'Andrómeda I', 12),
(48, 'Gran Nube de Magallanes ', 12),
(49, '8', 13),
(50, '9', 13),
(51, '7', 13),
(52, '10', 13),
(53, 'Tifania y Oberón', 14),
(54, 'Encélado y Tetis', 14),
(55, 'Europa y Calisto', 14),
(56, 'Fobos y Deimos', 14),
(57, 'Antimonio', 15),
(58, 'Estroncio', 15),
(59, 'Estaño', 15),
(60, 'Selenio', 15),
(61, 'Sublimación', 16),
(62, 'Condensación', 16),
(63, 'Fusión', 16),
(64, 'Vaporización', 16),
(65, 'Cuando ambas son positivas', 17),
(66, 'Cuando ambas son negativas', 17),
(67, 'A y B son correctas', 17),
(68, 'Cuando tienen cargas diferentes', 17),
(69, 'Sombras tenebrosas', 18),
(70, 'El Titanic', 18),
(71, 'El Señor de los Anillos', 18),
(72, 'Los Juegos del Hambre', 18),
(73, 'Infiltrados ', 19),
(74, 'Quisiera ser millonario', 19),
(75, 'Mentes poderosas', 19),
(76, 'La forma del agua', 19),
(77, 'Dunkerque ', 20),
(78, 'En tierra hostil', 20),
(79, 'Salvar al soldado Ryan', 20),
(80, 'El pianista', 20),
(81, 'Una piña', 21),
(82, 'Un zorro', 21),
(83, 'Un ojo', 21),
(84, 'Una manzana', 21),
(85, 'Pantalla', 22),
(86, 'Micrófono', 22),
(87, 'Teclado', 22),
(88, 'Escaner', 22),
(89, 'Hp', 23),
(90, 'Asus', 23),
(91, 'Intel', 23),
(92, 'Dell', 23),
(93, 'Tetris', 24),
(94, 'Pac-Man', 24),
(95, 'Space Invaders', 24),
(96, 'Donkey Kong', 24),
(97, 'Luigi', 25),
(98, 'Bowser', 25),
(99, 'Waluigi', 25),
(100, 'Yoshi', 25),
(101, '6', 26),
(102, '2', 26),
(103, '1', 26),
(104, '4', 26);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pregunta`
--

CREATE TABLE `pregunta` (
  `pre_id` int(11) NOT NULL,
  `pre_pregunta` text DEFAULT NULL,
  `fk_tema` int(11) NOT NULL,
  `pre_opcion_correcta` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `pregunta`
--

INSERT INTO `pregunta` (`pre_id`, `pre_pregunta`, `fk_tema`, `pre_opcion_correcta`) VALUES
(3, '¿En que pais nacio Simon Bolivar?', 7, 'C'),
(4, '¿Cuál era la capital del Imperio Inca?', 7, 'B'),
(5, '¿Quién fue el primer emperador romano?', 7, 'D'),
(6, '¿En que museo está la Mona Lisa?', 8, 'C'),
(7, '¿En que siglo nació Van Gogh?', 8, 'C'),
(8, '¿De quien es la obra de la noche estrellada?', 8, 'C'),
(9, '¿Que obra no es Gabriel Garcia Marquez?', 9, 'B'),
(10, '¿Quién escribio \"La iliada\"?', 9, 'A'),
(11, '¿Quién escribio \"la divina comedia\"?', 9, 'A'),
(12, '¿Cómo se llama la galaxia en la que esta nuestro sistema solar?', 10, 'B'),
(13, '¿Cuántos planetas hay en nuestro sistema solar’', 10, 'A'),
(14, '¿Cómo se llaman las lunas de marte?', 10, 'D'),
(15, '¿Como se llama el elemento cuyo símbolo químico es Sn?', 11, 'C'),
(16, '¿Cuál es el cambio de estado por el cual un gas pasa a ser un líquido?', 11, 'B'),
(17, '¿Cuándo dos cargas eléctricas se repelen?', 11, 'C'),
(18, '¿En cuál de estas películas actuó Johnny Depp?', 12, 'A'),
(19, '¿Cuál de estas películas no tiene un premio Oscar?', 12, 'C'),
(20, '¿Cuál de estas películas es acerca de la segunda guerra mundial?', 12, 'B'),
(21, '¿Qué símbolo tienen los equipos de Apple?', 13, 'D'),
(22, '¿Cuál de los siguientes periféricos no es de entrada?', 13, 'A'),
(23, '¿Cuál de las siguientes marcas no es de computadoras?', 13, 'C'),
(24, '¿Qué videojuego salio primero?', 15, 'C'),
(25, '¿Cómo se llama el hermano de Mario Bros?', 15, 'A'),
(26, '¿Cuántas entradas para mandos tiene el Nintendo 64?', 15, 'D');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ronda`
--

CREATE TABLE `ronda` (
  `ron_id` int(11) NOT NULL,
  `fk_tema_sala` int(11) NOT NULL,
  `fk_pregunta` int(11) NOT NULL,
  `estado` varchar(50) NOT NULL,
  `fechaCreacion` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `ronda`
--

INSERT INTO `ronda` (`ron_id`, `fk_tema_sala`, `fk_pregunta`, `estado`, `fechaCreacion`) VALUES
(14, 24, 3, 'terminado', '2020-08-20 06:36:59'),
(15, 23, 20, 'terminado', '2020-08-20 06:37:35'),
(16, 26, 25, 'terminado', '2020-08-20 06:38:02');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ronda_usuario`
--

CREATE TABLE `ronda_usuario` (
  `fk_ronda` int(11) NOT NULL,
  `fk_usuario` int(11) NOT NULL,
  `puntos` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `ronda_usuario`
--

INSERT INTO `ronda_usuario` (`fk_ronda`, `fk_usuario`, `puntos`) VALUES
(14, 11, 0),
(14, 10, 0),
(14, 12, 310),
(15, 11, 0),
(15, 10, 440),
(15, 12, 0),
(16, 12, 560),
(16, 11, 0),
(16, 10, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sala`
--

CREATE TABLE `sala` (
  `sal_id` int(11) NOT NULL,
  `sal_nombre` varchar(45) DEFAULT NULL,
  `sal_rondas` int(11) DEFAULT NULL,
  `sal_ronda_actual` int(11) NOT NULL DEFAULT 0,
  `sal_estado` varchar(45) DEFAULT NULL,
  `sal_estado_int` varchar(50) DEFAULT 'enSala',
  `fk_usuario_owner` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `sala`
--

INSERT INTO `sala` (`sal_id`, `sal_nombre`, `sal_rondas`, `sal_ronda_actual`, `sal_estado`, `sal_estado_int`, `fk_usuario_owner`) VALUES
(3, 'Pruebas', 1, 0, 'activa', 'enSala', 5),
(5, 'Pruebas F', 3, 3, 'Terminada', 'Terminada', 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sala_usuario`
--

CREATE TABLE `sala_usuario` (
  `sala_usuario_id` int(11) NOT NULL,
  `fk_usuario` int(11) NOT NULL,
  `fk_sala` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `sala_usuario`
--

INSERT INTO `sala_usuario` (`sala_usuario_id`, `fk_usuario`, `fk_sala`) VALUES
(8, 10, 5),
(9, 11, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tema`
--

CREATE TABLE `tema` (
  `tem_id` int(11) NOT NULL,
  `tem_nombre` varchar(45) DEFAULT NULL,
  `tem_icono` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tema`
--

INSERT INTO `tema` (`tem_id`, `tem_nombre`, `tem_icono`) VALUES
(7, 'Historia', 'historia.png'),
(8, 'Arte', 'arte.png'),
(9, 'Literatura', 'menu.png'),
(10, 'Astronomía ', 'astronomia.png'),
(11, 'Ciencia', 'ciencia.png'),
(12, 'Cine', 'cine.png'),
(13, 'Tecnología', 'tecnologia.png'),
(15, 'Videojuegos', 'videojuegos.png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tema_sala`
--

CREATE TABLE `tema_sala` (
  `tem_sal_id` int(11) NOT NULL,
  `fk_sala` int(11) NOT NULL,
  `fk_tema` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tema_sala`
--

INSERT INTO `tema_sala` (`tem_sal_id`, `fk_sala`, `fk_tema`) VALUES
(12, 3, 8),
(13, 3, 7),
(14, 3, 9),
(21, 5, 10),
(22, 5, 11),
(23, 5, 12),
(24, 5, 7),
(25, 5, 13),
(26, 5, 15);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `usu_id` int(11) NOT NULL,
  `usu_login` varchar(45) DEFAULT NULL,
  `usu_pass` blob DEFAULT NULL,
  `usu_email` varchar(50) NOT NULL,
  `usu_rol` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`usu_id`, `usu_login`, `usu_pass`, `usu_email`, `usu_rol`) VALUES
(5, 'pruebas', 0x1f534c79b15c944ebb344da6206314df, 'bryant@pruebas.com', 'cliente'),
(6, 'prueba2', 0x1f534c79b15c944ebb344da6206314df, 'prueba2@prueba.com', 'cliente'),
(7, 'tercero', 0x1f534c79b15c944ebb344da6206314df, 'prr@pruebas.com', 'cliente'),
(9, 'admin', 0x1f534c79b15c944ebb344da6206314df, 'admin@admin.com', 'admin'),
(10, 'camila', 0x1f534c79b15c944ebb344da6206314df, 'camila@gmail.com', 'cliente'),
(11, 'oscar', 0x1f534c79b15c944ebb344da6206314df, 'oscar@gmail.com', 'cliente'),
(12, 'bryant', 0x1f534c79b15c944ebb344da6206314df, 'bryant@gmail.com', 'cliente');

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
  ADD KEY `fk_pregunta_tema1_idx` (`fk_tema`);

--
-- Indices de la tabla `ronda`
--
ALTER TABLE `ronda`
  ADD PRIMARY KEY (`ron_id`),
  ADD KEY `fk_pregunta` (`fk_pregunta`),
  ADD KEY `fk_tema_sala` (`fk_tema_sala`);

--
-- Indices de la tabla `ronda_usuario`
--
ALTER TABLE `ronda_usuario`
  ADD KEY `ronda_usuario_ibfk_1` (`fk_ronda`),
  ADD KEY `ronda_usuario_ibfk_2` (`fk_usuario`);

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
  ADD PRIMARY KEY (`sala_usuario_id`),
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
  ADD PRIMARY KEY (`tem_sal_id`),
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
-- AUTO_INCREMENT de la tabla `opcion`
--
ALTER TABLE `opcion`
  MODIFY `opc_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=105;

--
-- AUTO_INCREMENT de la tabla `pregunta`
--
ALTER TABLE `pregunta`
  MODIFY `pre_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de la tabla `ronda`
--
ALTER TABLE `ronda`
  MODIFY `ron_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `sala`
--
ALTER TABLE `sala`
  MODIFY `sal_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `sala_usuario`
--
ALTER TABLE `sala_usuario`
  MODIFY `sala_usuario_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `tema`
--
ALTER TABLE `tema`
  MODIFY `tem_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `tema_sala`
--
ALTER TABLE `tema_sala`
  MODIFY `tem_sal_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `usu_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `opcion`
--
ALTER TABLE `opcion`
  ADD CONSTRAINT `fk_opcion_pregunta1` FOREIGN KEY (`fk_pregunta`) REFERENCES `pregunta` (`pre_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `pregunta`
--
ALTER TABLE `pregunta`
  ADD CONSTRAINT `fk_pregunta_tema1` FOREIGN KEY (`fk_tema`) REFERENCES `tema` (`tem_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `ronda`
--
ALTER TABLE `ronda`
  ADD CONSTRAINT `ronda_ibfk_1` FOREIGN KEY (`fk_pregunta`) REFERENCES `pregunta` (`pre_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ronda_ibfk_2` FOREIGN KEY (`fk_tema_sala`) REFERENCES `tema_sala` (`tem_sal_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `ronda_usuario`
--
ALTER TABLE `ronda_usuario`
  ADD CONSTRAINT `ronda_usuario_ibfk_1` FOREIGN KEY (`fk_ronda`) REFERENCES `ronda` (`ron_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ronda_usuario_ibfk_2` FOREIGN KEY (`fk_usuario`) REFERENCES `usuario` (`usu_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `sala`
--
ALTER TABLE `sala`
  ADD CONSTRAINT `fk_sala_usuario1` FOREIGN KEY (`fk_usuario_owner`) REFERENCES `usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `sala_usuario`
--
ALTER TABLE `sala_usuario`
  ADD CONSTRAINT `fk_sala_usuario_sala1` FOREIGN KEY (`fk_sala`) REFERENCES `sala` (`sal_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_sala_usuario_usuario` FOREIGN KEY (`fk_usuario`) REFERENCES `usuario` (`usu_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tema_sala`
--
ALTER TABLE `tema_sala`
  ADD CONSTRAINT `fk_tema_sala_sala1` FOREIGN KEY (`fk_sala`) REFERENCES `sala` (`sal_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_tema_sala_tema1` FOREIGN KEY (`fk_tema`) REFERENCES `tema` (`tem_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
