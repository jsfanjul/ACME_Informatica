-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-04-2021 a las 14:06:28
-- Versión del servidor: 10.4.17-MariaDB
-- Versión de PHP: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_practicas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `albaranes`
--

CREATE TABLE `albaranes` (
  `alb_id` int(11) NOT NULL,
  `alb_fecha_prov` date NOT NULL,
  `alb_prov_id` int(11) NOT NULL,
  `alb_fecha_entrada` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `albaranes`
--

INSERT INTO `albaranes` (`alb_id`, `alb_fecha_prov`, `alb_prov_id`, `alb_fecha_entrada`) VALUES
(1, '2021-04-08', 4, '2021-04-09'),
(2, '2021-04-15', 2, '2021-04-16'),
(3, '2021-04-15', 1, '2021-04-16');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `albaranes_detalle`
--

CREATE TABLE `albaranes_detalle` (
  `albd_id` int(11) NOT NULL,
  `albd_alb_id` int(11) NOT NULL,
  `albd_prod_id` int(11) NOT NULL,
  `albd_cantidad` int(11) NOT NULL,
  `albd_precio_entrada` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `albaranes_detalle`
--

INSERT INTO `albaranes_detalle` (`albd_id`, `albd_alb_id`, `albd_prod_id`, `albd_cantidad`, `albd_precio_entrada`) VALUES
(2, 1, 1, 3, 22),
(3, 1, 12, 5, 100),
(13, 2, 2, 3, 77),
(14, 2, 2, 1000, 4),
(15, 2, 2, 2, 56),
(16, 2, 2, 3, 112),
(18, 3, 17, 100, 2000);

--
-- Disparadores `albaranes_detalle`
--
DELIMITER $$
CREATE TRIGGER `RESTAR_STOCK_ALBARANES` BEFORE DELETE ON `albaranes_detalle` FOR EACH ROW UPDATE productos SET productos.prod_stock_teorico = productos.prod_stock_teorico - old.albd_cantidad WHERE productos.prod_id = old.albd_prod_id
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `SUMAR_STOCK_ALBARAN` AFTER INSERT ON `albaranes_detalle` FOR EACH ROW UPDATE productos SET productos.prod_stock_teorico = productos.prod_stock_teorico + new.albd_cantidad WHERE productos.prod_id = new.albd_prod_id
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `cat_id` int(11) NOT NULL,
  `cat_categoria` varchar(50) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`cat_id`, `cat_categoria`) VALUES
(1, 'Memorias'),
(2, 'Pantallas'),
(3, 'Placas base'),
(4, 'Procesadores'),
(5, 'Discos duros'),
(6, 'SSD');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `cli_id` int(11) NOT NULL,
  `cli_nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `cli_cif` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `cli_tipo_via` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `cli_localidad` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `cli_codigo_postal` varchar(5) COLLATE utf8_spanish_ci NOT NULL,
  `cli_provincia` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `cli_pais` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `cli_contacto` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `cli_telefono` varchar(50) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`cli_id`, `cli_nombre`, `cli_cif`, `cli_tipo_via`, `cli_localidad`, `cli_codigo_postal`, `cli_provincia`, `cli_pais`, `cli_contacto`, `cli_telefono`) VALUES
(1, 'Indra', 'B87387438', 'Avenida', 'Getafe', '28998', 'Madrid', 'España', 'Pedro', '647327821'),
(2, 'Everis', 'B64828283', 'Calle', 'Requena', '46340', 'Valencia', 'España', 'Pedro Ruiz', '647372823'),
(3, 'Accenture', 'I46737828', 'Street', 'Dublín', 'D13', 'Dublín', 'Irlanda', 'A Julie Sweet', '647586987'),
(4, 'Deloitte', 'I85785737', 'Road', 'New York', '10001', 'New York', 'Estados Unidos', 'Punit Renjen', '658797483'),
(5, 'KPMG', 'B64828283', 'Calle', 'Amstelveen', '020', 'Amstelveen', 'Países Bajos', 'William B. Thomas', '654734839'),
(6, 'Ernst & Young', 'F74757533', 'Avenue', 'Londres', 'E1 6P', 'Londres', 'Reino Unido', 'Carmine Di Sibio', '745675463'),
(7, 'IBM', 'F74757537', 'Avenue', 'Endicott', '10025', 'New York', 'Estados Unidos', 'Charles Ranlett Flint', '635474865'),
(8, 'SAS Institute', 'Y65743446', 'Street', 'Cary', '56743', 'Carolina del Norte', 'Estados Unidos', 'James Goodnight', '0085747434'),
(9, 'Bain & Company', 'C74574838', 'Square', 'Boston', '10033', 'Massachusetts', 'Estados Unidos', 'Bill Bain', '684785676'),
(10, 'Dell', 'T47382939', 'Street', 'Round Rock', '34753', 'Texas', 'Estados Unidos', 'Michael Dell', '902100130'),
(11, 'Cisco', 'Y74639271', 'Square', 'San José', '46382', 'California', 'Estados Unidos', 'Chuck Robbins', '9475837482'),
(12, 'Oracle', 'H74839283', 'Street', 'Austin', '87438', 'Texas', 'Estados Unidos', 'Lawrence J. Ellison', '0016505067000'),
(13, 'Apple', 'J12345678', 'Street', 'Cupertino', '78435', 'California', 'Estados Unidos', 'Steve Jobs', '900812703');

--
-- Disparadores `clientes`
--
DELIMITER $$
CREATE TRIGGER `CLIENTES_AD` AFTER DELETE ON `clientes` FOR EACH ROW INSERT INTO eliminaciones_clientes(ID, NOMBRE, CIF, TIPO_VIA, LOCALIDAD, CODIGO_POSTAL, PROVINCIA, PAIS, CONTACTO, TELEFONO, USUARIO_QUE_BORRO, FECHA_BORRADO) 
VALUES(old.cli_id, old.cli_nombre, old.cli_cif, old.cli_tipo_via, old.cli_localidad, old.cli_codigo_postal, old.cli_provincia, old.cli_pais, old.cli_contacto, old.cli_telefono, CURRENT_USER(), NOW())
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `CLIENTES_AI` AFTER INSERT ON `clientes` FOR EACH ROW INSERT INTO inserciones_clientes(NUEVA_ID, NUEVO_NOMBRE, NUEVO_CIF, NUEVO_TIPO_VIA, NUEVA_LOCALIDAD, NUEVO_CODIGO_POSTAL, NUEVA_PROVINCIA, NUEVO_PAIS, NUEVO_CONTACTO, NUEVO_TELEFONO, USUARIO_QUE_INSERTO, FECHA_INSERCION) VALUES(new.cli_id, new.cli_nombre, new.cli_cif, new.cli_tipo_via, new.cli_localidad, new.cli_codigo_postal, new.cli_provincia, new.cli_pais, new.cli_contacto, new.cli_telefono, CURRENT_USER(), NOW())
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `CLIENTES_BU` BEFORE UPDATE ON `clientes` FOR EACH ROW INSERT INTO modificaciones_clientes(ID, ANT_NOMBRE, NUE_NOMBRE, ANT_CIF, NUE_CIF, ANT_TIPO_VIA, NUE_TIPO_VIA, ANT_LOCALIDAD, NUE_LOCALIDAD, ANT_CODIGO_POSTAL, NUE_CODIGO_POSTAL, ANT_PROVINCIA, NUE_PROVINCIA, ANT_PAIS, NUE_PAIS, ANT_CONTACTO, NUE_CONTACTO, ANT_TELEFONO, 
NUE_TELEFONO, USUARIO_QUE_MODIF, FECHA_MODIF) VALUES(old.cli_id, old.cli_nombre, new.cli_nombre, old.cli_cif, new.cli_cif, old.cli_tipo_via, new.cli_tipo_via, old.cli_localidad, new.cli_localidad, old.cli_codigo_postal, 
new.cli_codigo_postal, old.cli_provincia, new.cli_provincia, old.cli_pais, new.cli_pais, 
old.cli_contacto, new.cli_contacto, old.cli_telefono, new.cli_telefono, CURRENT_USER(), NOW())
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `eliminaciones_clientes`
--

CREATE TABLE `eliminaciones_clientes` (
  `ID` int(11) DEFAULT NULL,
  `NOMBRE` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `CIF` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `TIPO_VIA` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `LOCALIDAD` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `CODIGO_POSTAL` varchar(5) COLLATE utf8_spanish_ci DEFAULT NULL,
  `PROVINCIA` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `PAIS` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `CONTACTO` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `TELEFONO` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `USUARIO_QUE_BORRO` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `FECHA_BORRADO` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `eliminaciones_clientes`
--

INSERT INTO `eliminaciones_clientes` (`ID`, `NOMBRE`, `CIF`, `TIPO_VIA`, `LOCALIDAD`, `CODIGO_POSTAL`, `PROVINCIA`, `PAIS`, `CONTACTO`, `TELEFONO`, `USUARIO_QUE_BORRO`, `FECHA_BORRADO`) VALUES
(14, 'Sage', 'T93847392', 'Square', 'Newcastle upon Tyne', '87343', 'Londres', 'Reino Unido', 'Steve Hare', '900878902', 'root@localhost', '2021-03-29 00:25:11'),
(15, 'PC Componentes', 'A12345678', 'Calle', 'Getafe', '28100', 'Madrid', 'España', 'Inés', '6374657683', 'root@localhost', '2021-04-05 09:17:08');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `eliminaciones_proveedores`
--

CREATE TABLE `eliminaciones_proveedores` (
  `ID` int(11) DEFAULT NULL,
  `NOMBRE` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `CIF` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `TIPO_VIA` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `LOCALIDAD` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `CODIGO_POSTAL` varchar(5) COLLATE utf8_spanish_ci DEFAULT NULL,
  `PROVINCIA` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `PAIS` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `CONTACTO` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `TELEFONO` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `USUARIO_QUE_BORRO` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `FECHA_BORRADO` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `eliminaciones_proveedores`
--

INSERT INTO `eliminaciones_proveedores` (`ID`, `NOMBRE`, `CIF`, `TIPO_VIA`, `LOCALIDAD`, `CODIGO_POSTAL`, `PROVINCIA`, `PAIS`, `CONTACTO`, `TELEFONO`, `USUARIO_QUE_BORRO`, `FECHA_BORRADO`) VALUES
(12, 'GCOOP', 'Y75983944', 'Calle', 'La Pampa', '4395', 'Buenos Aires', 'Argentina', 'Alejandro Rogríguez', '00541152547755', 'root@localhost', '2021-03-29 02:08:39'),
(13, 'a', 'B12345678', 'b', 'c', '28309', 'd', 'e', 'f', '637378328', 'root@localhost', '2021-04-05 09:22:13');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturas`
--

CREATE TABLE `facturas` (
  `fac_id` int(11) NOT NULL,
  `fac_fecha_venta` date NOT NULL,
  `fac_cli_id` int(11) NOT NULL,
  `fac_fecha_salida` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `facturas`
--

INSERT INTO `facturas` (`fac_id`, `fac_fecha_venta`, `fac_cli_id`, `fac_fecha_salida`) VALUES
(1, '2021-04-07', 2, '2021-04-08'),
(2, '2021-04-14', 3, '2021-04-15'),
(3, '2021-04-07', 1, '2021-04-08'),
(4, '2021-04-23', 5, '2021-04-24');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturas_detalle`
--

CREATE TABLE `facturas_detalle` (
  `facd_id` int(11) NOT NULL,
  `facd_fac_id` int(11) NOT NULL,
  `facd_prod_id` int(11) NOT NULL,
  `facd_cantidad` int(11) NOT NULL,
  `facd_precio_venta` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `facturas_detalle`
--

INSERT INTO `facturas_detalle` (`facd_id`, `facd_fac_id`, `facd_prod_id`, `facd_cantidad`, `facd_precio_venta`) VALUES
(7, 1, 17, 20, 56),
(8, 1, 21, 1, 77),
(11, 2, 26, 21, 2000),
(12, 2, 21, 30, 400),
(13, 3, 17, 3, 200),
(15, 4, 17, 5, 444);

--
-- Disparadores `facturas_detalle`
--
DELIMITER $$
CREATE TRIGGER `RESTAR_STOCK_FACTURA` AFTER INSERT ON `facturas_detalle` FOR EACH ROW UPDATE productos SET productos.prod_stock_teorico = productos.prod_stock_teorico - new.facd_cantidad WHERE productos.prod_id = new.facd_prod_id
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `SUMAR_STOCK_FACTURA` BEFORE DELETE ON `facturas_detalle` FOR EACH ROW UPDATE productos SET productos.prod_stock_teorico = productos.prod_stock_teorico + old.facd_cantidad WHERE prod_id = old.facd_prod_id
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inserciones_clientes`
--

CREATE TABLE `inserciones_clientes` (
  `NUEVA_ID` int(11) DEFAULT NULL,
  `NUEVO_NOMBRE` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUEVO_CIF` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUEVO_TIPO_VIA` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUEVA_LOCALIDAD` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUEVO_CODIGO_POSTAL` varchar(5) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUEVA_PROVINCIA` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUEVO_PAIS` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUEVO_CONTACTO` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUEVO_TELEFONO` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `USUARIO_QUE_INSERTO` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `FECHA_INSERCION` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `inserciones_clientes`
--

INSERT INTO `inserciones_clientes` (`NUEVA_ID`, `NUEVO_NOMBRE`, `NUEVO_CIF`, `NUEVO_TIPO_VIA`, `NUEVA_LOCALIDAD`, `NUEVO_CODIGO_POSTAL`, `NUEVA_PROVINCIA`, `NUEVO_PAIS`, `NUEVO_CONTACTO`, `NUEVO_TELEFONO`, `USUARIO_QUE_INSERTO`, `FECHA_INSERCION`) VALUES
(1, 'Indra', 'B87387438', 'Avenida', 'Getafe', '28998', 'Madrid', 'España', 'Pedro', '647327821', 'root@localhost', '2021-03-28 21:11:21'),
(2, 'Everis', 'B64828283', 'Calle', 'Requena', '46340', 'Valencia', 'España', 'Pedro Ruiz', '647372823', 'root@localhost', '2021-03-28 21:11:21'),
(3, 'Accenture', 'I46737828', 'Street', 'Dublín', 'D13', 'Dublín', 'Irlanda', 'A Julie Sweet', '647586987', 'root@localhost', '2021-03-28 21:11:21'),
(4, 'Deloitte', 'I85785737', 'Road', 'New York', '10001', 'New York', 'Estados Unidos', 'Punit Renjen', '658797483', 'root@localhost', '2021-03-28 21:11:21'),
(5, 'KPMG', 'B64828283', 'Calle', 'Amstelveen', '020', 'Amstelveen', 'Países Bajos', 'William B. Thomas', '654734839', 'root@localhost', '2021-03-28 21:11:21'),
(6, 'Ernst & Young', 'F74757533', 'Avenue', 'Londres', 'E1 6P', 'Londres', 'Reino Unido', 'Carmine Di Sibio', '745675463', 'root@localhost', '2021-03-28 21:11:21'),
(7, 'IBM', 'F74757537', 'Avenue', 'Endicott', '10025', 'New York', 'Estados Unidos', 'Charles Ranlett Flint', '635474865', 'root@localhost', '2021-03-28 21:11:21'),
(8, 'McKinsey & Company', 'U75783824', 'Street', 'Chicago', '30041', 'Chicago', 'Estados Unidos', 'James O. McKinsey', '746346523', 'root@localhost', '2021-03-28 21:11:21'),
(9, 'Bain & Company', 'C74574838', 'Square', 'Boston', '10033', 'Massachusetts', 'Estados Unidos', 'Bill Bain', '684785676', 'root@localhost', '2021-03-28 21:11:21'),
(10, 'Dell', 'T47382939', 'Street', 'Round Rock', '34753', 'Texas', 'Estados Unidos', 'Michael Dell', '902100130', 'root@localhost', '2021-03-28 21:11:21'),
(11, 'Cisco', 'Y74639271', 'Square', 'San José', '46382', 'California', 'Estados Unidos', 'Chuck Robbins', '9475837482', 'root@localhost', '2021-03-28 21:11:21'),
(12, 'Oracle', 'H74839283', 'Street', 'Austin', '87438', 'Texas', 'Estados Unidos', 'Lawrence J. Ellison', '0016505067000', 'root@localhost', '2021-03-28 21:11:21'),
(13, 'SAP', 'J85734837', 'Round', 'Walldorf', '87342', 'Walldorf', 'Alemania', 'Dietmar Hopp', '0935084394', 'root@localhost', '2021-03-28 21:11:21'),
(14, 'Sage', 'T93847392', 'Square', 'Newcastle upon Tyne', '87343', 'Londres', 'Reino Unido', 'Steve Hare', '900878902', 'root@localhost', '2021-03-29 00:10:30'),
(15, 'PC Componentes', 'A12345678', 'Calle', 'Getafe', '28100', 'Madrid', 'España', 'Inés', '6374657683', 'root@localhost', '2021-04-05 09:15:35');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inserciones_proveedores`
--

CREATE TABLE `inserciones_proveedores` (
  `NUEVA_ID` int(11) DEFAULT NULL,
  `NUEVO_NOMBRE` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUEVO_CIF` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUEVO_TIPO_VIA` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUEVA_LOCALIDAD` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUEVO_CODIGO_POSTAL` varchar(5) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUEVA_PROVINCIA` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUEVO_PAIS` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUEVO_CONTACTO` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUEVO_TELEFONO` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `USUARIO_QUE_INSERTO` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `FECHA_INSERCION` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `inserciones_proveedores`
--

INSERT INTO `inserciones_proveedores` (`NUEVA_ID`, `NUEVO_NOMBRE`, `NUEVO_CIF`, `NUEVO_TIPO_VIA`, `NUEVA_LOCALIDAD`, `NUEVO_CODIGO_POSTAL`, `NUEVA_PROVINCIA`, `NUEVO_PAIS`, `NUEVO_CONTACTO`, `NUEVO_TELEFONO`, `USUARIO_QUE_INSERTO`, `FECHA_INSERCION`) VALUES
(1, 'Tecnoportátil', 'B637438498', 'Calle', 'Sils', '17410', 'Girona', 'España', 'María Pérez', '972168620', 'root@localhost', '2021-03-29 01:47:22'),
(2, 'DAS', 'B637438498', 'Calle', 'Alicante', '03003', 'Alicante', 'España', 'Pedro Ruiz', '900828020', 'root@localhost', '2021-03-29 01:47:22'),
(3, 'Chip Technology', 'B637438498', 'Paseo', 'Getafe', '28009', 'Madrid', 'España', 'Ana Aparicio', '654875893', 'root@localhost', '2021-03-29 01:47:22'),
(4, 'Vinzeo', 'M74737426', 'Avenida', 'Alcobendas', '28100', 'Madrid', 'España', 'Abel González', '902380480', 'root@localhost', '2021-03-29 01:47:22'),
(5, 'Megasur', 'B63748392', 'Calle', 'Escúzar', '18004', 'Granada', 'España', 'Teresa garcía', '958509010', 'root@localhost', '2021-03-29 01:47:22'),
(6, 'Globomatik', 'A64478347', 'Calle', 'Viator', '04240', 'Almería', 'España', 'Anne Rodríguez', '950081876', 'root@localhost', '2021-03-29 01:47:22'),
(7, 'Lilotronik', 'I84737466', 'Habenhause', 'Bremen', '28277', 'Bremen', 'Alemania', 'Thomas Müler', '4218498160', 'root@localhost', '2021-03-29 01:47:22'),
(8, 'Lotterer GmbH & Co. KG', 'M65748374', 'Am', 'Langenau', '89129', 'Ulm', 'Alemania', 'Albert Kedhira', '734596500', 'root@localhost', '2021-03-29 01:47:22'),
(9, 'Ambar Distributors', 'H34253641', 'Avenue', 'Doral', '33126', 'Florida', 'Estados Unidos', 'Peter House', '7862995141', 'root@localhost', '2021-03-29 01:47:22'),
(10, 'Sennari', 'M75636477', 'Street', 'Doral', '10775', 'Florida', 'Estados Unidos', 'Albert Mane', '305-591-7310', 'root@localhost', '2021-03-29 01:47:22'),
(11, 'Fujitsu', 'H65843744', 'Street', 'Minato', '88447', 'Tokio', 'Japón', 'Takahito Tokita', '873875785', 'root@localhost', '2021-03-29 01:47:22'),
(12, 'GCOOP', 'Y75983944', 'Calle', 'La Pampa', '4395', 'Buenos Aires', 'Argentina', 'Alejandro Rogríguez', '00541152547755', 'root@localhost', '2021-03-29 01:47:22'),
(13, 'a', 'B12345678', 'b', 'c', '28309', 'd', 'e', 'f', '637378328', 'root@localhost', '2021-04-05 09:20:13');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `modificaciones_clientes`
--

CREATE TABLE `modificaciones_clientes` (
  `ID` int(11) DEFAULT NULL,
  `ANT_NOMBRE` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUE_NOMBRE` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ANT_CIF` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUE_CIF` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ANT_TIPO_VIA` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUE_TIPO_VIA` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ANT_LOCALIDAD` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUE_LOCALIDAD` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ANT_CODIGO_POSTAL` varchar(5) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUE_CODIGO_POSTAL` varchar(5) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ANT_PROVINCIA` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUE_PROVINCIA` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ANT_PAIS` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUE_PAIS` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ANT_CONTACTO` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUE_CONTACTO` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ANT_TELEFONO` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUE_TELEFONO` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `USUARIO_QUE_MODIF` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `FECHA_MODIF` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `modificaciones_clientes`
--

INSERT INTO `modificaciones_clientes` (`ID`, `ANT_NOMBRE`, `NUE_NOMBRE`, `ANT_CIF`, `NUE_CIF`, `ANT_TIPO_VIA`, `NUE_TIPO_VIA`, `ANT_LOCALIDAD`, `NUE_LOCALIDAD`, `ANT_CODIGO_POSTAL`, `NUE_CODIGO_POSTAL`, `ANT_PROVINCIA`, `NUE_PROVINCIA`, `ANT_PAIS`, `NUE_PAIS`, `ANT_CONTACTO`, `NUE_CONTACTO`, `ANT_TELEFONO`, `NUE_TELEFONO`, `USUARIO_QUE_MODIF`, `FECHA_MODIF`) VALUES
(13, 'SAP', 'Apple', 'J85734837', 'J12345678', 'Round', 'Street', 'Walldorf', 'Cupertino', '87342', '78435', 'Walldorf', 'California', 'Alemania', 'Estados Unidos', 'Dietmar Hopp', 'Steve Jobs', '0935084394', '900812703', 'root@localhost', '2021-03-28 22:03:47'),
(8, 'McKinsey & Company', 'SAS Institute', 'U75783824', 'Y65743446', 'Street', 'Street', 'Chicago', 'Cary', '30041', '56743', 'Chicago', 'Carolina del Norte', 'Estados Unidos', 'Estados Unidos', 'James O. McKinsey', 'James Goodnight', '746346523', '0085747434', 'root@localhost', '2021-03-29 00:23:53');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `modificaciones_proveedores`
--

CREATE TABLE `modificaciones_proveedores` (
  `ID` int(11) DEFAULT NULL,
  `ANT_NOMBRE` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUE_NOMBRE` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ANT_CIF` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUE_CIF` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ANT_TIPO_VIA` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUE_TIPO_VIA` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ANT_LOCALIDAD` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUE_LOCALIDAD` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ANT_CODIGO_POSTAL` varchar(5) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUE_CODIGO_POSTAL` varchar(5) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ANT_PROVINCIA` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUE_PROVINCIA` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ANT_PAIS` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUE_PAIS` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ANT_CONTACTO` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUE_CONTACTO` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ANT_TELEFONO` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NUE_TELEFONO` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `USUARIO_QUE_MODIF` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `FECHA_MODIF` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `modificaciones_proveedores`
--

INSERT INTO `modificaciones_proveedores` (`ID`, `ANT_NOMBRE`, `NUE_NOMBRE`, `ANT_CIF`, `NUE_CIF`, `ANT_TIPO_VIA`, `NUE_TIPO_VIA`, `ANT_LOCALIDAD`, `NUE_LOCALIDAD`, `ANT_CODIGO_POSTAL`, `NUE_CODIGO_POSTAL`, `ANT_PROVINCIA`, `NUE_PROVINCIA`, `ANT_PAIS`, `NUE_PAIS`, `ANT_CONTACTO`, `NUE_CONTACTO`, `ANT_TELEFONO`, `NUE_TELEFONO`, `USUARIO_QUE_MODIF`, `FECHA_MODIF`) VALUES
(1, 'Tecnoportátil', 'Siemens', 'B637438498', 'A63546647', 'Calle', 'Avenue', 'Sils', 'Munich', '17410', '76434', 'Girona', 'Munich', 'España', 'Alemania', 'María Pérez', 'Richard Bauss', '972168620', '0037474833', 'root@localhost', '2021-03-29 02:02:23');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `prod_id` int(11) NOT NULL,
  `prod_marca` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `prod_modelo` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `prod_caracteristicas` mediumtext COLLATE utf8_spanish_ci NOT NULL,
  `prod_foto` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `prod_precio_venta` float NOT NULL,
  `prod_stock_teorico` int(11) NOT NULL,
  `prod_observaciones` mediumtext COLLATE utf8_spanish_ci NOT NULL,
  `prod_cat_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`prod_id`, `prod_marca`, `prod_modelo`, `prod_caracteristicas`, `prod_foto`, `prod_precio_venta`, `prod_stock_teorico`, `prod_observaciones`, `prod_cat_id`) VALUES
(1, 'LG', 'Monitor 4K UHD LG UltraFine', 'Ratio de contraste de color 3000:1 gracias al panel VA de 4ms (GTG)\r\nTecnología AMD FreeSync™ para disfrutar de toda la acción sin parpadeos incluso a bajos FPS gracias al LFC.\r\nColores más vivos y mejor contraste con HDR10\r\nDiseño virtualmente sin bordes para una experiencia de uso, mas inmersiva y fluida en configuraciones multi-monitor\r\nAmplia contenidos en la pantalla, sin que pierdan resolución ni nitidez, gracias a la tecnología Super Resolution+\r\nPanel VA de 4ms & 3000:1', 'Monitor 4K UHD LG UltraFine.png', 399, 0, 'El monitor LG UHD 4K te permite disfrutar de los contenidos 4K y HDR.\r\nEl contenido HDR de múltiples servicios de streaming, siendo el resultado el más auténtico, con colores vivos en un amplio rango cuando se reproduce en un monitor LG UHD 4K con tecnología HDR10 en soporte del espacio de color DCI-P3 90%.\r\nTiene un diseño elegante y flexible.\r\nEl soporte de un solo clic es fácil de instalar sin ayuda de otros equipos, ajustándose de forma flexible la altura e inclinación de la pantalla grande para colocarla en la posición óptima para el usuario.', 2),
(2, 'Intel Core', 'Placa Madre B365M-DS3H', 'Admite procesadores Intel Core de octava y novena generación\r\nDDR4 sin búfer, sin ECC de doble canal\r\nNuevo diseño hibrido digital PWM\r\nCapacitadores de audio de alta calidad y con protección de ruido con separación de audio led\r\nM.2 ultra rápida con PCIe Gen3 x4 y interfaz SATA\r\nRGB FUSION admite tiras de LED RGB en 7 colores\r\nLAN exclusiva para juegos 8118 de GIGABYTE con gestión de ancho de banda\r\nListo para CEC 2019, ahorre energía tan fácil como un clic\r\nSmart Fan 5 con sensores de temperatura múltiple y cabezales de ventilación hibrida con FAN STOP\r\nDiseño de resistencias anti-azufre\r\nProtección contra sobretensiones LAN Ultra Durable ™ 15KV\r\nMemoria Intel® Optane\r\nGIGABYTE UEFI DualBIOS', 'Gygabyte B365-DS3H.png', 80, 0, 'Peso y dimensiones:\r\nAncho: 226mm\r\nProfundidad: 205mm\r\n\r\nConexión:\r\nEthernet: Si\r\nWifi: No\r\nTipo de interfaz ethernet: Gigabit Ethernet\r\n\r\nInterno IO:\r\nUSB 2.0, conectores: 2\r\nConectores USB 3.0 ( 3.1 Gen 1 ): 1\r\nNúmero de conectores SATA III: 6\r\nS/PDIF, conector de salida: Si\r\nConector de audio en panel frontal: Si\r\nConector de panel delantero: Si\r\nConector de potencia ATX (24 pines): Si\r\nConector de ventilador CPU: Si\r\nNumero de conectores a ventilador de chasis: 2\r\nConector TPM: Si\r\nCabecera de puerto serie: 1\r\nPuerto serial vía cabezal interno: Si\r\nPuerto paralelo vía cabezal interno: Si\r\nConector eléctrico de 12 V: Si\r\nConector para tiras LED RGB: Si', 3),
(3, 'SanDisk', 'Memoria USB Ultra Flair', 'Capacidad de memoria de hasta 64GB\r\nUSB 3.0\r\nTransferencias de archivos en un instante Disfruta del rendimiento de la tecnología USB 3.0, que alcanza velocidades de 150 MB/s1, con la unidad flash USB 3.0 SanDisk Ultra Flair. Transfiere archivos mas rápido que con las unidades USB 2.0 estándar.\r\nElegante y robusta La unidad flash USB 3.0 SanDisk Ultra Flair, que cuenta con una carcasa metálica duradera, es elegante y esta lista para resistir los golpes inesperados.\r\nProtege tus archivos con contraseña Comparte tus películas o tu música sin preocuparte por la privacidad de tus archivos personales. La protección con contraseña fácil de usar te permite controlar quien tiene acceso a ciertos archivos.\r\nGarantía La unidad flash USB 3.0 SanDisk Ultra Flair cuenta con el respaldo de una garantía limitada de cinco años.', 'SanDisk Ultra Flair 64GB Azul.png', 19, 0, 'Peso y dimensiones\r\nAncho: 13,2mm\r\nProfundidad: 42,4mm\r\nAltura: 6,6mm\r\n\r\nSeguridad\r\nLector de huella digital: No\r\nAcceso con PIN-seguro: No\r\n\r\nContenido del embalaje\r\nCantidad por paquete: 1 pieza\r\n\r\nDiseño\r\nFactor de forma: Sin tapa\r\nColor del producto: Azul, Plata\r\nLlavero: Si\r\n\r\nDesempeño\r\nCapacidad: 64 GB\r\nInterfaz del dispositivo: USB tipo A\r\nVersión USB: 3.0 (3.1 Gen 1)\r\nVelocidad de lectura: 150 MB/s\r\nConectar y usar (Plug and Play): Si', 1),
(4, 'Intel Core', 'Procesador I9-10900K 3.70GHz', 'Execute Disable Bit: Si\r\nEstados de inactividad: Si\r\nTecnología Thermal Monitoring de Intel: Si\r\nNúmero máximo de buses PCI Express: 16\r\nConfiguración de CPU (máximo): 1\r\nOpciones integradas disponibles: No\r\nCaracterísticas técnicas de la solución térmica: PCG 2015D\r\nRevisión PCI Express CEM: 3.0\r\nCódigo de Sistema de Armonización (SA): 8542310001', 'Procesador Intel Core I9.png', 460, 0, 'Intel Hyper-Threading: Si\r\nTecnología de protección de identidad de Intel (Intel® IPT): Si\r\nTecnología Intel® Turbo Boost: 2.0\r\nTecnología Intel® Quick Sync Video: Si\r\nIntel Tecnología InTru™ 3D: Si\r\nTecnología Clear Video HD de Intel® (Intel CVT HD): Si\r\nIntel AES Nuevas instrucciones (Intel® AES-NI): Si\r\nTecnología SpeedStep mejorada de Intel: Si\r\nIntel Thermal Velocity Boost: Si\r\nVT-x de Intel® con Extended Page Tables (EPT): Si\r\nIntel Secure Key: Si\r\nIntel OS Guard: Si\r\nTecnología Intel Clear Video: Si\r\nIntel® 64: Si\r\nTecnología de virtualización Intel (VT-x): Si\r\nTecnología de virtualización de Intel para E / S dirigida (VT-d): Si\r\nTecnología 3.0 Intel Turbo Boost Max: Si\r\nCompatible con la tecnología Intel Optane: Si\r\nIntel® Boot Guard: Si', 4),
(5, 'Samsung', 'Disco duro Samsung 860 EVO Basic SSD 1TB SATA 3', 'Tecnología V-NAND 3D 250GB\r\nInterfaz SATA III de mayor rendimiento (6 GB/s)\r\nLectura secuencial de hasta 550 MB/s y escritura secuencial de 520 MB/seg.', 'Samsung 860 EVO Basic SSD 1TB SATA 3.png', 123, 0, 'Descubra la nueva edición de la gama Samsung SATA SSD, la gama Samsung 860 EVO. Especialmente diseñada para el uso diario del PC, con tecnología V-NAND y un controlador robusto basado en algoritmos, este SSD rápido y confiable esta disponible en una variedad de formatos y capacidades. El 860 EVO ofrece el mejor rendimiento de lectura y escritura, incluso en multitareas. El 860 EVO puede entregar velocidades de escritura de hasta 520 MB/s con tecnología inteligente TurboWrite, y velocidades de lectura de hasta 550 MB/s.\r\n\r\nAlmacene su información de forma segura y reproduzca videos 4k y datos 3D con facilidad. Con hasta 8 veces más TBW que los 850 EVO anteriores, el 860 EVO ofrece mayor resistencia y confiabilidad con una garantía de 5 años. La última tecnología V-NAND le ofrece hasta 2.400 TBW. Obtenga una comunicación mas rápida y fluida con su sistema host. El algoritmo ECC y el nuevo controlador MJX generan velocidades mas altas y mejoran la compatibilidad con Linux.', 6),
(7, 'Seagate', 'Disco duro Interno Seagate Barracuda 8TB SATA 3', 'Tamaño del HDD: 3.5 pulgadas\r\nCapacidad del HDD: 8000 GB\r\nVelocidad de rotación del HDD: 5400 RPM\r\nInterfaz: Serial ATA III\r\nTipo: Unidad de disco duro\r\nComponente para: Servidor/estación de trabajo\r\nTamaño de unidad de almacenamiento de búfer: 256 MB\r\nVelocidad de transferencia de impulso sostenido del HDD: 220 MiB/s', 'Seagate Barracuda 8TB SATA 3.png', 175, 0, 'Peso y dimensiones\r\nAncho: 101,8mm\r\nAltura: 26,1mm\r\nProfundidad: 147mm\r\nPeso: 650g\r\n\r\nControl de energía\r\nConsumo energético: 6,8W\r\nConsumo de energía (inactivo): 0,8W\r\nConsumo de energía (ahorro): 0,8W\r\nConsumo de energía (espera): 4,5W\r\nVoltaje de operación: 5 / 12V\r\nCorriente de arranque: 1,8A\r\nCondiciones ambientales\r\nIntervalo de temperatura operativa: 0 - 60 °C\r\nIntervalo de temperatura de almacenaje: -40 - 70 °C', 5),
(8, 'Newskill', 'Newskill Icarus 27 pulgadas QHD 165Hz G-Sync', 'Su diseño frameless lo dota de una sensación de pantalla única para un modelo de estas dimensiones: minimalista pero sin perder su toque gamer. Un monitor para ordenador pensado por y para los aficionados a los videojuegos.\r\nNewskill Icarus 27” QHD incorpora un panel VA con una tasa de refresco de hasta 165Hz para proporcionar unas imágenes más rápidas y fluidas.\r\nEste monitor gamer cuenta con un panel VA de alta resolución QHD 2560x1440 píxeles. Brillo, contraste, ángulos de visión y una excelente gama de colores han sido perfectamente calibrados para que el panel sea capaz de transmitir la sensación de ver un color en la vida real.\r\nEl sistema flicker free elimina parpadeos aleatorios en la visualización. ', 'Monitor gaming 144 HZ.png', 280, 0, 'Stand Fijo\r\nDimensiones del panel/Proporción: 27\'\' 16:9\r\nTecnología de panel: VA\r\nResoluciones/Frecuencias/Interaces:\r\nDisplayPort 1.2 x 2  - 1920x1080p (FHD)@165Hz  2560×1440p (2K)@165Hz\r\nHDMI 2.0 - 1920x1080p (FHD)@144Hz  2560×1440p (2K)@144Hz\r\nUSB x1 (upgrade only)\r\nEarphone x1\r\nBrillo máximo: 300cd/m2(typ.), 250cd/m2(min) (HDR Compatible)\r\nContraste: 4000:1\r\nTiempo de respuesta: 1ms MPRT, 3ms (G-to-G) (Overdrive)\r\nÁngulos de visión:178º, V:178º', 2),
(9, 'Luxscreen', 'Pantalla auto enrollable', 'Pantalla de proyección eléctrica de 120\" formato 16:9 compatible con HD, FULLHD y 4K\r\nBorde perimetral negro para una mayor concentración de contraste, superficie de la tela blanco mate que la dotan de una fiel calidad de color escala RGB\r\nIncluye mando a distancia inalámbrico, cajetín de acero reforzado en color blanco perla.', 'Pantalla de proyección motorizada 120.png', 119, 0, 'Garantía del fabricante 2 años con servicio post venta en España\r\nSe precisa un hueco de 3 metros para la instalación del producto. Parte posterior de la tela negra para evitar perder luminosidad y profundidad a la imagen', 2),
(10, 'Luxscreen', 'Pantalla auto enrollable', 'Ganancia de brillo 1.1, necesario para mejorar la tasa lumínica del proyector\r\nAcabado blanco mate, en formato de varias capas que componen una mayor uniformidad para la proyección y mejora de la colorimetría\r\nBorde perimetral negro, para una mayor concentración de contraste y brillo\r\nLa parte posterior de la tela es negra, lo que hace que la imagen sea más intensa y no se pierda ningún brillo\r\nUn mayor grosor para que la proyección sea uniforme y libre de arrugas', 'Tela de proyección 140.png', 20, 0, 'Instalación recomendada mediante argolla, tipo cáncamo adhesivo o bien velcro\r\nLa tela de proyección Luxscreen disponen de un grosor y calidad óptimas para una mayor calidad de imagen, su borde perimetral negro mejoran la transmisión del contraste y su parte posterior blanca dotan a la proyección de un mayor realismo , mejor colorimetría y una ganancia de brillo, están construidas en varias capas de pintura especial para proyección y no se tratan de simples telas tal como se denominan otros productos similares de tipo Simple Screen.\r\nPara evitar cualquier pérdida de brillo o que la imagen se traspase, las telas de proyección Luxscreen tienen la parte posterior totalmente negras, opacas lo que producen una imagen más luminosa y un contraste mejorado.', 2),
(11, 'Samsung ', 'Smart TV Samsung Crystal UHD 50TU7125', 'Alto Rango Dinámico\r\nPantalla LCD\r\n125cm o 50\" de tamaño de pantalla\r\nPantalla plana\r\nCalidad de la imagen UHD 4K\r\nSmart TV\r\nSistema Operativo Tizen\r\nProcesador Crystal Processor 4K\r\nResolución 3840x2160 píxeles\r\nFrecuencia de la imagen 50/60Hz', 'TV LED - Samsung Crystal.png', 479, 0, 'TV LED 50\"\r\n4K Real y HDR10+\r\nCompatible con Asistentes de Voz\r\nUn mando para todos tus dispositivos conectados', 2),
(12, 'Samsung', 'Memoria USB 3.1', 'Memoria USB 32GB\r\nUSB 3.1\r\n200MB/s de velocidad de lectura\r\nColor Gris\r\nAnchura 1.56cm\r\nAltura 4cm\r\nProfundidad 1.17cm\r\nColor Gris\r\nPeso 10g', 'Memoria USB Samsung 32GB.png', 11, 0, 'Guarda y lleva tus archivos a todas partes con la memoria USB Samsung de 32 GB MUF-32BE4, APC Bar Plus. Con velocidades de vértigo para transferir archivos como nunca antes.\r\nHabilitado para USB 3.1 podrás almacenar, compartir y transferir archivos con una velocidad de hasta 200MB/s, tres veces más rápido que las unidades USB 2.0 tradicionales. Mueve un vídeo 4K UHD de 3 GB desde tu Bar Plus a tu ordenador en solo 10 segundos.\r\nMantén tus archivos a salvo donde quiera que vayas. El USB Bar Plus es una unidad en la que confiar el almacenamiento de los datos, contando con cinco niveles de protección: resistente al agua, a los golpes, a las altas temperaturas, resistencia magnética y a los rayos-X.\r\n', 1),
(13, 'Kingston', 'Memoria USB 3.0', 'La unidad Flash USB DataTraveler® 100 G3 de Kingston cumple las especificaciones de USB 3.0 de próxima generación con el fin de aprovecharse esta tecnología en los nuevos notebooks, ordenadores de sobremesa y dispositivos digitales.\r\nGracias a DT100G3, almacenar y transferir documentos, presentaciones, música, vídeo y demás tipos de archivos es más rápido y fácil que nunca.\r\nEl DT100G3 presenta un diseño elegante y económico que permite conducir la transición a USB 3.0 de un modo satisfactorio con una inversión mínima. DT100G3, está disponible en capacidades de entre 8 y 64 GB, también es compatible con versiones anteriores de USB 2.0 y cuenta con una garantía de cinco años.\r\nCapacidad: 32 GB\r\nDimensiones: (60mm x 21.2mm x10mm)', 'Kingston datatraveler.png', 5, 0, 'Kingston DataTraveler 100 G3 32GB USB3.0 se puede obtener una garantía de durabilidad para que el equipo de almacenamiento portátil no pueda quedarse nunca obsoleto.', 1),
(14, 'Kauriea', 'Pendrive Metal Flash USB', 'Gran capacidad de almacenamiento: de 1000 GB.\r\nAmplia compatibilidad con Windows 98, segunda edición / NT / ME / 2000 / XP / 7 / 8.1 / 10 y superior, también es compatible con Linux y Mac OS 10.3 y superior.\r\nResistente al agua y duradera: es completamente resistente al agua, puede secarla y usarla sin problemas. Con carcasa de metal de alta calidad para mayor durabilidad.\r\nPlug and Play: acceso simple de arrastrar y soltar en su computadora o dispositivo móvil, sin necesidad de instalar software o preocuparse de que su unidad USB no sea compatible.\r\nDiseño pequeño y llavero', 'Kauriea Memoria USB.png', 28, 0, 'Tamaño: 8.2 x 8 x 0.7 cm; 10 gramos\r\nCapacidad de memoria: 1TB\r\nInterfaz del hardware: USB 1.0, USB, USB 3.0, USB 2.0\r\nPeso del producto: 10g', 1),
(15, 'SanDisk', 'Sandisk Cruzer Ultra', 'Altas velocidades de transferencia de hasta 80 MB/s\r\nTransferir archivos hasta cuatro veces más rápido que las unidades USB 2.0\r\nCapacidad de 32GB\r\nProtege los archivos privados con el software SanDisk SecureAccess\r\nUSB 3.0 con compatibilidad USB 2.0\r\nDimensiones 56.8 mm x 21.3 mm x 10.8 mm', 'Sandisk Cruzer Ultra 32GB.png', 7, 0, 'El SanDisk Ultra Flash Drive USB 3.0 combina altas velocidades de datos y gran capacidad de almacenamiento en un pendrive compacto y elegante. Con velocidades de transferencia de hasta 80MB/s, esta unidad flash USB 3.0 puede transferir archivos hasta cuatro veces más rápido que las unidades USB 2.0. Con una capacidad de almacenamiento de 32GB, la unidad puede satisfacer las necesidades con archivos y documentos multimedia voluminosos.\r\nIncluye el software SecureAccess de SanDisk, que te permite configurar una carpeta privada protegida por contraseña en la unidad USB. Tus archivos se aseguran con el cifrado AES de 128 bits para ayudar a mantenerlos en secreto, incluso si compartes el pen con los demás.', 1),
(16, 'MSI', 'X470 Gaming Plus Max', 'Admite AMD Ryzen™ / Ryzen™ de 1.a, 2.a y 3.a generación con gráficos Radeon ™ Vega y procesadores de escritorio AMD Ryzen™ de 2.a generación con gráficos Radeon ™ / Athlon ™ con Radeon ™ Vega Graphics para Socket AM4\r\nAdmite memoria DDR4, hasta 4133 (OC) MHz\r\nExperiencia de juego increíblemente rápida: TURBO M.2, tecnología AMD StoreMI, AMD Turbo USB 3.2 Gen2\r\nCore Boost: con un diseño premium y un diseño de potencia optimizado para admitir más núcleos y proporcionar un mejor rendimiento.\r\nMystic Light y Mystic Light Sync: 7 colores / 7 efectos en un solo clic. Sincronice otras soluciones RGB para la personalización.\r\nAudio Boost: recompensa tus oídos con una calidad de sonido de calidad de estudio para la experiencia de juego más envolvente\r\nMULTI-GPU: con ranuras STEEL ARMOR PCI-E. Admite AMD Crossfire™ de 3 vías', 'MSI X470 Gaming Plus Max.png', 95, 0, 'Las placas base MSI cuentan con toneladas de diseño conveniente e inteligente, como una conveniente zona de bloqueo de encabezado de clavija, ubicación amigable SATA y USB, etc., para que los usuarios de bricolaje puedan elegir cualquier plataforma de juego que deseen.', 3),
(17, 'Gigabyte', 'Gigabyte B450M DS3H V2', 'Soporta AMD 3rd Gen Ryzen™ / 2nd Gen Ryzen™ / 1st Gen Ryzen™ / 2nd Gen Ryzen™ con Radeon™ Vega Graphics / 1st Gen Ryzen™ con Radeon™ Vega Graphics / Athlon™ con Radeon™ Vega Graphics Processors\r\nDDR4 de doble canal sin ECC sin búfer, 4 DIMM\r\nCaracterística Solución VRM digital con MOSFET de bajo RDS (activado)\r\nPuertos HDMI, DVI-D para múltiples pantallas\r\nUltrarrápido PCIe Gen3 x4 M.2 con PCIe NVMe y soporte SATA\r\nCapacitadores de audio de alta calidad y con protección de ruido con separación de audio led\r\nLAN para juegos 8118 exclusiva de GIGABYTE con gestión de ancho de banda\r\nRGB FUSION admite tiras de LED RGB en 7 colores\r\nSmart Fan 5 cuenta con 5 sensores de temperatura y 2 cabezales de ventilador híbridos con FAN STOP\r\nAPP Center, que incluye las utilidades EasyTune™ y Cloud Station™\r\nListo para CEC 2019, ahorre energía con un simple clic', 'Gigabyte B450M DS3H V2.png', 73, 95, 'Placa base AMD B450 ultra duradera con solución VRM digital, gestión de ancho de banda y LAN para juegos GIGABYTE, PCIe Gen3 x4 M.2, resistencia anti-azufre, cabezal de tira de LED RGB, listo para CEC 2019', 3),
(18, 'MSI', 'MSI Z490A PRO', 'Admite procesadores Intel® Core™ / Pentium® Celeron® de décima generación para zócalo LGA 1200\r\nAdmite memoria DDR4, hasta 4800MHz\r\nReady 4 the Future: soporte Lightning Gen 4 Solution para el futuro\r\nSolución térmica de primera calidad: el diseño de disipador térmico extendido y M.2 Shield Frozr están diseñados para un sistema de alto rendimiento y trabajos ininterrumpidos\r\nReady 4 the Future: soporte Lightning Gen 4 Solution para el futuro\r\nLAN 2.5G: Solución de red actualizada para uso profesional y multimedia. Ofrece una conexión de red segura, estable y rápida.\r\nTurbo M.2: la ejecución en PCI-E Gen3 x4 maximiza el rendimiento de los SSD basados en NVMe\r\nIntel Turbo USB 3.2 Gen 2: Desarrollado por el controlador Intel USB 3.2 Gen2, Turbo USB garantiza una conexión ininterrumpida con más estabilidad y velocidades USB más rápidas\r\nAudio Boost: recompensa tus oídos con calidad de sonido de estudio\r\nMulti-GPU: con ranuras PCI-E de armadura de acero. Admite AMD Crossfire ™ de 2 vías', 'MSI Z490A PRO.png', 157, 0, 'La serie PRO ayuda a los usuarios a trabajar de manera más inteligente al brindar una experiencia eficiente y productiva. Con una funcionalidad estable y un ensamblaje de alta calidad, las placas base de la serie PRO proporcionan no solo flujos de trabajo profesionales optimizados, sino también menos resolución de problemas y longevidad.\r\n\r\nLas placas base MSI Z490 son compatibles con la interfaz PCI-Express 4.0 de los procesadores Intel Core de 11ª generación. A través de una actualización de la BIOS, las placas base MSI Z490 ofrecen un gran ancho de banda y rendimiento para las unidades SSD M.2 PCIe 4.0 y las tarjetas gráficas', 3),
(20, 'Gigabyte', 'Gigabyte B550M DS3H', 'Admite procesadores AMD Ryzen™ de tercera generación\r\nDoble canal ECC / No ECC DDR4 sin búfer, 4 DIMM\r\nSolución VRM digital pura de 5 + 3 fases con MOSFET de bajo RDS (encendido)\r\nRanura Ultra16 Durable ™ PCIe 4.0 Ready x16\r\nConectores duales Ultra-Fast NVMe PCIe 4.0 / 3.0 M.2\r\nCondensadores de audio de alta calidad y protección contra ruido de audio para la mejor calidad de audio\r\nLAN GbE Gaming 8118 exclusiva de GIGABYTE con gestión de ancho de banda\r\nSoporte trasero HDMI y DVI\r\nRGB FUSION 2.0 con diseño de espectáculo de luces LED direccionables multizona, compatible con tiras LED direccionables y LED RGB\r\nSmart Fan 5 presenta múltiples sensores de temperatura, encabezados de ventilador híbridos con FAN STOP\r\nQ-Flash Plus Update BIOS sin instalar la CPU, la memoria y la tarjeta gráfica\r\nDiseño de resistencias anti-azufre', 'Gigabyte B550M DS3H.png', 87, 0, 'La placa base Gigabyte B550M DS3H soporte los procesadores AMD Ryzen de tercera generación.\r\nGráfica integrada\r\nZócalos de Expansión\r\nInterfaz de almacenamiento\r\nConectores Internos E/S', 3),
(21, 'AMD', 'AMD Ryzen 5 1600 Stepping AF 3.6GHz BOX', '# de núcleos de CPU 6\r\n# de hilos 12\r\nReloj base 3.2GHz\r\nReloj de aumento máx. Hasta 3.6GHz\r\nCaché L1 total 576KB\r\nCaché L2 total 3MB\r\nCaché L3 total 16MB\r\nDesbloqueados Sí\r\nCMOS 12nm\r\nPackage AM4\r\nVersión de PCI Express PCIe 3.0 x16\r\nSolución térmica (PIB) Wraith Spire\r\nTDP/TDP predeterminado 65W\r\nTemp. máx. 95 °C\r\nCompatible con SO\r\nWindows 10 edición de 64·bits\r\nRHEL x86 edición de 64·bits\r\nUbuntu x86 edición de 64·bits\r\nEl soporte del sistema operativo (SO) variará según el fabricante.\r\nMemoria\r\nVelocidad máxima de memoria 2667 MHz\r\nTipo de memoria DDR4\r\nCanales de memoria 2', 'AMD Ryzen 5 1600.png', 120, 0, 'Los AMD Ryzen 1600 con stepping AF se han desarrollado bajo la arquitectura ZEN+, la misma que la de la serie 2000 de AMD Ryzen y bajo un proceso de fabricación de 12nm vs los 14nm de la serie 1000 habitual. Esto quiere decir que con un poco de Overclock alcanza el rendimiento de un AMD Ryzen 2600 costando un 30% menos.', 4),
(22, 'AMD', 'AMD Athlon 3000G 3.5GHz', 'N.° de núcleos de CPU 2\r\nN.° de subprocesos 4\r\nN.° de núcleos de GPU 3\r\nReloj base 3.5GHz\r\nCaché L1 total 192KB\r\nCaché L2 total 1MB\r\nCaché L3 total 4MB\r\nDesbloqueados Sí\r\nCMOS 14nm\r\nPaquete AM4\r\nVersión de PCI Express PCIe 3.0\r\nTDP/TDP predeterminado 35W\r\nTemp. máx. 95°C\r\nCompatible con SO\r\nWindows 10 edición de 64·bits\r\nRHEL x86 edición de 64·bits\r\nUbuntu x86 edición de 64·bits\r\nEl soporte del sistema operativo (SO) variará según el fabricante.\r\nMemoria\r\nVelocidad máxima de memoria 2667MHz\r\nCanales de memoria 2\r\nEspecificaciones de gráficos\r\nFrecuencia de gráficos 1000 MHz\r\nModelo de gráficos Radeon? Vega 3 Graphics\r\nCant. de núcleos de los gráficos 3', 'AMD Athlon 3000G.png', 80, 0, 'El procesador básico más avanzado que AMD ha creado nunca, para usuarios que buscan una respuesta rápida y gráficos Radeon Vega integrados, con la arquitectura de procesador innovadora que necesitan para aprovechar la actualización de la tarjeta de gráficos. Navega por Internet con rapidez, transmite videos en tiempo real sin un solo corte y juega los títulos de sports más populares en alta definición a 720p.', 4),
(23, 'AMD', 'AMD Ryzen 7 3800X 3.9GHz BOX', 'Plataforma con Socket AM4 Preparada Para AMD Ryzen. La plataforma informática estándar de AMD, actualizada con el nuevo chipset X570 compatible con PCIe® 4.0.\r\nSoluciones de Refrigeración AMD de Máxima Calidad. Todos los procesadores Ryzen 7 de 3ª generación traen de fábrica Wraith Prism, el sistema de refrigeración premium de AMD con control de iluminación LED, para los usuarios a quienes les importa el funcionamiento, el sonido y la apariencia de su PC.\r\nTecnología AMD StoreMI. Software que combina la velocidad de SSD con la capacidad de disco duro en una sola unidad rápida y fácil de administrar, gratuita con la placa madre AMD Serie 400.\r\nUtilidad AMD Ryzen Master. La utilidad de overclocking sencilla y a la vez potente para los procesadores AMD Ryzen.\r\nArquitectura de Núcleo Zen. Eficiencia energética mejorada, velocidades de reloj más altas y más núcleos que nunca.', 'AMD Ryzen 7 3800X.png', 334, 0, 'Fabricados para rendir. Diseñados para ganar. Más velocidad. Más memoria. Mayor ancho de banda. Exígelos al máximo, exprime hasta la última gota de rendimiento, llévalos al límite. Los procesadores AMD Ryzen de 3ª generación se diseñaron para superar todas las expectativas y marcar un nuevo camino en materia de procesadores de alto rendimiento.\r\n\r\nLos procesadores AMD Ryzen de 3.ª generación nacen de la tecnología de fabricación más avanzada del mundo para garantizar un rendimiento ganador y un sistema con un funcionamiento asombrosamente refrigerado y silencioso.\r\n\r\nLos procesadores Ryzen de 3ª generación integran la primera plataforma de conectividad PCIe® 4.0 del mundo para poner en tus manos las tecnologías de motherboards, tarjetas gráficas y almacenamiento más avanzadas que existen.', 4),
(24, 'Intel Core', 'Intel Core i5-9400F 2.9GHz', 'Execute Disable Bit: Si\r\nTecnología Thermal Monitoring de Intel: Si\r\nNúmero máximo de buses PCI Express: 16\r\nVersión de entradas de PCI Express: 3.0\r\nConfiguraciones PCI Express: 2x8,1x8+2x4\r\nEscalabilidad: 1S\r\nConfiguración de CPU (máximo): 1\r\nOpciones integradas disponibles: No\r\nCaracterísticas técnicas de la solución térmica: PCG 2015C\r\nSegmento de mercado: DT', 'Intel Core i5-9400F.png', 125, 0, 'Con la potencia y la capacidad de respuesta de la tecnología Intel® Turbo Boost 2.01 2 puede pasar más tiempo haciendo cosas y menos esperando. Cree, edite y comparta contenido 4K con facilidad y disfrute de experiencias absorbentes en 360 grados a pantalla completa 4K.', 4),
(25, 'Toshiba', 'Toshiba Canvio Basics', 'Tipo de dispositivo: Disco Duro Externo\r\nConexiones: USB 3.0\r\nTamaño: 2.5\"\r\nCapacidad de memoria: 1TB\r\nVelocidad de transferencia: 5 GB/s\r\nColor: Negro\r\nPeso: 200g', 'Disco Duro Toshiba 1TB.png', 55, 0, 'El disco duro de Toshiba Canvio Basics es la solución perfecta para guardar tus archivos multimedia: fotos, vídeos, música, película, documentos. Se acabó tener que elegir entre unos y otros porque ahora lo tendrás todo siempre al alcance de la mano.\r\nCon el disco duro Toshiba Canvio Basics se podrá transferir con rapidez los archivos gracias al SuperSpeed USB 3.0. Pero si se tiene un hardware más antiguo, con este disco duro podrás conectarte ya que es compatible con USB 2.0. En color negro, tiene una velocidad de transferencia de datos de 5 Gbps y se alimenta por bus USB.\r\nEl dispositivo está listo para su uso con Microsoft Windows y no requiere ninguna instalación de software. ', 5),
(26, 'Seagate', 'Seagate Expansion Plus', 'Tipo de dispositivo: Disco duro externo\r\nTipo de disco duro: HDD\r\nTipo de unidad: Externo\r\nConexiones: USB\r\nCapacidad memoria: 5TB', 'Seagate 5TB.png', 130, 0, 'Almacenamiento de datos para hasta 5,000 horas de video digital, hasta 1,250 películas en DVD de dos horas, hasta 1,600,000 fotos digitales y hasta 83,300 horas de música digital.\r\nIncluye el software descargable Seagate Dashboard para copias de seguridad programable.\r\nSeagate Dashboard es un programa potente pero fácil de usar para realizar copias de seguridad de su contenido y compartir y almacenar archivos multimedia en las redes sociales. Además podrás programar la copia de seguridad y sin preocuparte y olvidarte de hacerla tú.', 5),
(27, 'Western Digital', 'WD Elements', 'Tipo de dispositivo: Disco duro externo\r\nConexiones: USB 3.0\r\nTamaño: 2.5\"\r\nCapacidad memoria: 2TB\r\nRevolución por minuto: 5400 rpm\r\nVelocidad de transferencia: 625MB/s\r\nCaracterísticas especiales: Plug & Play', 'Disco duro 2TB - WD Elements.png', 69, 0, 'Western Digital ha creado WD Elements, un fantástico disco duro, de 2,5, que te permitirá transportar todos tus documentos, fotos, música o películas, allí donde quieras.\r\nCuenta con 2TB de capacidad, que te ofrecen un enorme espacio de almacenamiento, al tiempo que gracias a su conexión USB 3.0, disfrutaras de unas velocidades de transferencia increíblemente altas. Pero si tu ordenador no dispone de USB 3.0, no te preocupes: es totalmente compatible con la conexión USB 2.0 para que lo puedas utilizar en la gran mayoría de equipos.', 5),
(28, 'Western Digital', 'WD Elements', 'Tipo de dispositivo: Disco duro externo 8 TB\r\nTipo de disco duro: HDD\r\nTipo de unidad: Externo\r\nConexiones: USB 3.0\r\nTamaño: 2.5\"\r\nCapacidad memoria: 8TB', 'Western Digital WD Elements 8TB.png', 149, 0, 'Una opción ideal para guardar tus documentos, fotos, videos y otros contenidos.\r\nSi el disco duro interno está casi lleno, el ordenador se ralentiza. No borras archivos. Libera espacio en el disco duro interno transfiriendo archivos a tu disco duro de sobremesa WD Elements y vuelve a disfrutar de tu ordenador.\r\nFunciona por defecto con el sistema operativo Windows®. Basta con conectarlo al puerto USB para añadir almacenamiento de forma instantánea.\r\nCon un mismo disco tendrás compatibilidad con los nuevos dispositivos USB 3.0 y con los dispositivos USB 2.0 ya existentes.', 5),
(29, 'Kingston', 'A400 SSD', 'Arranques, cargas y transferencias de archivos todos con mayor rapidez\r\nMás fiable y duradera que las unidades de disco duro\r\nVarias capacidades, para almacenar las aplicaciones o sustituir del todo unidades de disco duro.', 'Kingston A400 SSD 120GB.png', 24, 0, 'La unidad A400 de estado sólido de Kingston ofrece enormes mejoras en la velocidad de respuesta, sin actualizaciones adicionales del hardware. Brinda lapsos de arranque, carga y de transferencia de archivos increíblemente más breves en comparación con las unidades de disco duro mecánico.\r\nApoyada en su controlador de la más reciente generación, que ofrece velocidades de lectura y escritura de hasta 500MB/s y 450MB/s, respectivamente, esta unidad SSD es 10 veces más rápida que los discos duros tradicionales y provee un mejor rendimiento, velocidad de respuesta ultrarrápida en el procesamiento multitareas y una aceleración general del sistema. Son más fiables y duraderas que las unidades de disco duro, estando disponibles en varias capacidades que van de 120GB hasta 480GB.', 6),
(30, 'Kingston', 'A400', 'Tipo de dispositivo: Disco duro SSD\r\nTipo de disco duro: SSD\r\nTipo de unidad: Interno\r\nConexiones: SATA 6GB/s\r\nInterfaz de disco duro: SATA 6Gb/s\r\nTamaño: 2.5\"\r\nCapacidad memoria: 480GB', 'Kingston Technology A400 480GB.png', 55, 0, 'Se puede potenciar la capacidad de respuesta y espacio con el disco duro SSD de 480 GB Kingston Technology A400.\r\nEl disco duro SSD de 480GB Kingston Technology A400 es hasta 10 veces más veloz que un disco duro de 7200RPM. Alcanza velocidades de lectura de hasta 500MB/s y de escritura de hasta 450MB/s para insuflar vida a tu sistema.\r\nLa durabilidad del disco duro esta garantizada ya que su robusta memoria Flash soporta vibraciones convirtiéndolo en una buena opción para el ordenador portátil.', 6),
(31, 'Seagate', 'NAS', 'Las unidades de disco duro internas IronWolf son la solución adecuado para entornos NAS multiusuario de hasta 8 bahías que precisan de un rendimiento potente\r\nAlmacene más y trabaje más rápido con una unidad de disco duro optimizada para NAS\r\nIronWolf, diseñada especialmente para dispositivos NAS, ofrece un menor desgaste, una reducción total o casi total del ruido y la vibración, un aumento en el rendimiento del uso compartido de archivos\r\nSupervise fácilmente el estado de las unidades con el sistema IronWolf Health Management integrado y disfrute de una fiabilidad a largo plazo con un MTBF de 1 millón de horas\r\nDisfrute de la tranquilidad a largo plazo que le proporcionan los 3 años de servicios Rescue Data Recovery Service Plan para recuperación de datos', 'Seagate IronWolf.png', 114, 0, 'La unidad IronWolf, está diseñada especialmente para entornos NAS multiusuario, es la solución adecuada para los equipos que necesitan almacenar más y trabajar más rápido. Diseñada para velocidades de hasta 210MB/s, estas unidades internas se han diseñado específicamente para ofrecer un menor desgaste, una vibración/ruido prácticamente inexistente, retardo cero y tiempo de inactividad inexistente, el rendimiento de uso compartido de archivos se ha aumentado y el consumo de energía se ha reducido. Supervise de forma sencilla el estado de las unidades de disco duro con el sistema IronWolf Health Management integrado y disfrutando de una fiabilidad a largo plazo con un tiempo medio entre fallos de 1 millón de horas y un plan de protección.', 6),
(32, 'Kioxia', 'Exceria SSD Sata', 'Mejora de rendimiento instantánea: Aumente su productividad con la serie de unidades SSD EXCERIA SATA y disfrute de un arranque y una transferencia de archivos más rápidos, así como una mayor capacidad de respuesta del sistema. Despídase de los discos lentos y disfrute de una experiencia informática digna de su tiempo.\r\nRendimiento asequible: Las series de SSD de EXCERIA SATA equilibran el precio y el rendimiento, para que pueda usar ese dinero para otras mejoras.\r\nMejorada para la portabilidad: En comparación con las unidades de disco duro, la serie de SSD EXCERIA SATA también ofrece una mayor durabilidad y un menor consumo de energía, lo que se traduce en una mayor duración de la batería y, por tanto, más tiempo de uso.\r\nMemoria Flash 3D de vanguardia: Cada unidad SSD EXCERIA está fabricada con BiCS FLASH™ y una estructura de celda apilada verticalmente que ofrece almacenamiento de vanguardia.', 'Kioxia EXCERIA 480GB SSD SATA.png', 50, 0, 'Mejorar una unidad de disco duro (HDD) debería ser fácil y asequible. Y ahí es donde la serie de unidades SSD EXCERIA SATA entra en juego. Diseñada para aumentar la velocidad de su ordenador de sobremesa o portátil con respecto a las unidades de disco duro convencionales, la serie de unidades SSD EXCERIA SATA aprovecha la tecnología de BiCS FLASH™ para ofrecer, de una forma equilibrada, rendimiento, fiabilidad y valor que transformarán su sistema de sobremesa o portátil.', 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `prov_id` int(11) NOT NULL,
  `prov_nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `prov_cif` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `prov_tipo_via` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `prov_localidad` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `prov_codigo_postal` varchar(5) COLLATE utf8_spanish_ci NOT NULL,
  `prov_provincia` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `prov_pais` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `prov_contacto` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `prov_telefono` varchar(50) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `proveedores`
--

INSERT INTO `proveedores` (`prov_id`, `prov_nombre`, `prov_cif`, `prov_tipo_via`, `prov_localidad`, `prov_codigo_postal`, `prov_provincia`, `prov_pais`, `prov_contacto`, `prov_telefono`) VALUES
(1, 'Siemens', 'A63546647', 'Avenue', 'Munich', '76434', 'Munich', 'Alemania', 'Richard Bauss', '0037474833'),
(2, 'DAS', 'B637438498', 'Calle', 'Alicante', '03003', 'Alicante', 'España', 'Pedro Ruiz', '900828020'),
(3, 'Chip Technology', 'B637438498', 'Paseo', 'Getafe', '28009', 'Madrid', 'España', 'Ana Aparicio', '654875893'),
(4, 'Vinzeo', 'M74737426', 'Avenida', 'Alcobendas', '28100', 'Madrid', 'España', 'Abel González', '902380480'),
(5, 'Megasur', 'B63748392', 'Calle', 'Escúzar', '18004', 'Granada', 'España', 'Teresa garcía', '958509010'),
(6, 'Globomatik', 'A64478347', 'Calle', 'Viator', '04240', 'Almería', 'España', 'Anne Rodríguez', '950081876'),
(7, 'Lilotronik', 'I84737466', 'Habenhause', 'Bremen', '28277', 'Bremen', 'Alemania', 'Thomas Müler', '4218498160'),
(8, 'Lotterer GmbH & Co. KG', 'M65748374', 'Am', 'Langenau', '89129', 'Ulm', 'Alemania', 'Albert Kedhira', '734596500'),
(9, 'Ambar Distributors', 'H34253641', 'Avenue', 'Doral', '33126', 'Florida', 'Estados Unidos', 'Peter House', '7862995141'),
(10, 'Sennari', 'M75636477', 'Street', 'Doral', '10775', 'Florida', 'Estados Unidos', 'Albert Mane', '305-591-7310'),
(11, 'Fujitsu', 'H65843744', 'Street', 'Minato', '88447', 'Tokio', 'Japón', 'Takahito Tokita', '873875785');

--
-- Disparadores `proveedores`
--
DELIMITER $$
CREATE TRIGGER `PROVEEDORES_AD` AFTER DELETE ON `proveedores` FOR EACH ROW INSERT INTO eliminaciones_proveedores(ID, NOMBRE, CIF, TIPO_VIA, LOCALIDAD, CODIGO_POSTAL, PROVINCIA, PAIS, CONTACTO, TELEFONO, USUARIO_QUE_BORRO, FECHA_BORRADO) VALUES(old.prov_id, old.prov_nombre, old.prov_cif, old.prov_tipo_via, old.prov_localidad, old.prov_codigo_postal, old.prov_provincia, old.prov_pais, old.prov_contacto, old.prov_telefono, CURRENT_USER(), NOW())
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `PROVEEDORES_AI` AFTER INSERT ON `proveedores` FOR EACH ROW INSERT INTO inserciones_proveedores(NUEVA_ID, NUEVO_NOMBRE, NUEVO_CIF, NUEVO_TIPO_VIA, NUEVA_LOCALIDAD, NUEVO_CODIGO_POSTAL, NUEVA_PROVINCIA, NUEVO_PAIS, NUEVO_CONTACTO, NUEVO_TELEFONO, USUARIO_QUE_INSERTO, FECHA_INSERCION) VALUES(new.prov_id, new.prov_nombre, new.prov_cif, new.prov_tipo_via, new.prov_localidad, new.prov_codigo_postal, new.prov_provincia, new.prov_pais, new.prov_contacto, new.prov_telefono, CURRENT_USER(), NOW())
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `PROVEEDORES_BU` BEFORE UPDATE ON `proveedores` FOR EACH ROW INSERT INTO modificaciones_proveedores(ID, ANT_NOMBRE, NUE_NOMBRE, ANT_CIF, NUE_CIF, ANT_TIPO_VIA, NUE_TIPO_VIA, ANT_LOCALIDAD, NUE_LOCALIDAD, ANT_CODIGO_POSTAL, NUE_CODIGO_POSTAL, ANT_PROVINCIA, NUE_PROVINCIA, ANT_PAIS, NUE_PAIS, ANT_CONTACTO, NUE_CONTACTO, ANT_TELEFONO, NUE_TELEFONO, USUARIO_QUE_MODIF, FECHA_MODIF) VALUES(old.prov_id, old.prov_nombre, new.prov_nombre, old.prov_cif, new.prov_cif, old.prov_tipo_via, new.prov_tipo_via, old.prov_localidad, new.prov_localidad, old.prov_codigo_postal, new.prov_codigo_postal, old.prov_provincia, new.prov_provincia, old.prov_pais, new.prov_pais, old.prov_contacto, new.prov_contacto, old.prov_telefono, new.prov_telefono, CURRENT_USER(), NOW())
$$
DELIMITER ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `albaranes`
--
ALTER TABLE `albaranes`
  ADD PRIMARY KEY (`alb_id`),
  ADD KEY `alb_prov_id` (`alb_prov_id`);

--
-- Indices de la tabla `albaranes_detalle`
--
ALTER TABLE `albaranes_detalle`
  ADD PRIMARY KEY (`albd_id`),
  ADD KEY `albd_alb_id` (`albd_alb_id`),
  ADD KEY `albd_prod_id` (`albd_prod_id`);

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`cat_id`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`cli_id`);

--
-- Indices de la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD PRIMARY KEY (`fac_id`),
  ADD KEY `alb_prov_id` (`fac_cli_id`);

--
-- Indices de la tabla `facturas_detalle`
--
ALTER TABLE `facturas_detalle`
  ADD PRIMARY KEY (`facd_id`),
  ADD KEY `albd_prod_id` (`facd_prod_id`),
  ADD KEY `retriccion_facd_fac_id` (`facd_fac_id`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`prod_id`),
  ADD KEY `prod_cat_id` (`prod_cat_id`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`prov_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `albaranes`
--
ALTER TABLE `albaranes`
  MODIFY `alb_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `albaranes_detalle`
--
ALTER TABLE `albaranes_detalle`
  MODIFY `albd_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `cat_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `cli_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `facturas`
--
ALTER TABLE `facturas`
  MODIFY `fac_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `facturas_detalle`
--
ALTER TABLE `facturas_detalle`
  MODIFY `facd_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `prod_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `prov_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `albaranes`
--
ALTER TABLE `albaranes`
  ADD CONSTRAINT `restriccion_alb_prov_id` FOREIGN KEY (`alb_prov_id`) REFERENCES `proveedores` (`prov_id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `albaranes_detalle`
--
ALTER TABLE `albaranes_detalle`
  ADD CONSTRAINT `restirccion_albd_alb_id` FOREIGN KEY (`albd_alb_id`) REFERENCES `albaranes` (`alb_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `restriccion_albd_prod_id` FOREIGN KEY (`albd_prod_id`) REFERENCES `productos` (`prod_id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD CONSTRAINT `restriccion_fac_cli_id` FOREIGN KEY (`fac_cli_id`) REFERENCES `clientes` (`cli_id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `facturas_detalle`
--
ALTER TABLE `facturas_detalle`
  ADD CONSTRAINT `restricciom_facd_prod_id` FOREIGN KEY (`facd_prod_id`) REFERENCES `productos` (`prod_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `retriccion_facd_fac_id` FOREIGN KEY (`facd_fac_id`) REFERENCES `facturas` (`fac_id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `restriccion_prod_cat_id` FOREIGN KEY (`prod_cat_id`) REFERENCES `categorias` (`cat_id`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
