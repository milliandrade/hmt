-- phpMyAdmin SQL Dump
-- version 5.1.1deb5ubuntu1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Tempo de geração: 08/12/2023 às 02:52
-- Versão do servidor: 8.0.35-0ubuntu0.22.04.1
-- Versão do PHP: 8.1.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `hmt`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `drone`
--

CREATE TABLE `drone` (
  `id` varchar(36) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=ascii;

--
-- Despejando dados para a tabela `drone`
--

INSERT INTO `drone` (`id`) VALUES
('11111111-0000-0000-0000-111111111111'),
('22222222-0000-0000-0000-222222222222'),
('33333333-0000-0000-0000-333333333333'),
('44444444-0000-0000-0000-444444444444');

-- --------------------------------------------------------

--
-- Estrutura para tabela `farm`
--

CREATE TABLE `farm` (
  `name` varchar(40) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL,
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `notify` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=sjis;

--
-- Despejando dados para a tabela `farm`
--

INSERT INTO `farm` (`name`, `description`, `notify`) VALUES
('HMT Farm System', 'teste', 1);

-- --------------------------------------------------------

--
-- Estrutura para tabela `graze`
--

CREATE TABLE `graze` (
  `id` varchar(36) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL,
  `date` date NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time DEFAULT NULL,
  `hence_id` int NOT NULL,
  `alert` tinyint(1) NOT NULL DEFAULT '0',
  `time` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=ascii;

--
-- Despejando dados para a tabela `graze`
--

INSERT INTO `graze` (`id`, `date`, `start_time`, `end_time`, `hence_id`, `alert`, `time`) VALUES
('002a5eaa-ac6e-4527-b7ef-efddfc479f61', '2022-12-26', '15:27:00', '15:33:00', 12, 0, '2022-12-26 15:33:05'),
('0493ee00-1a8a-435e-8434-dfe25b3f0bed', '2022-12-21', '23:26:00', '23:29:00', 12, 0, '2022-12-21 23:29:21'),
('13f331f1-5547-46f7-8498-2271461fad75', '2022-12-26', '15:54:00', '16:02:11', 12, 0, '2022-12-26 15:54:58'),
('5bca1f87-cf16-4198-8a73-3b5aed707583', '2022-12-21', '23:30:00', '23:36:00', 12, 0, '2022-12-21 23:36:01'),
('77a86de0-7621-4efa-84fa-f9c3754eeaf5', '2022-12-26', '16:07:00', '16:13:00', 12, 0, '2022-12-26 16:13:20'),
('9f2d0830-fe9c-456f-acf4-5460ec187f0b', '2022-11-30', '12:26:40', '12:30:40', 12, 0, '2022-11-30 13:00:00'),
('9f2d0830-fe9c-456f-acf4-5460ec187fcc', '2022-12-01', '13:10:10', '13:15:10', 12, 0, '2022-12-01 22:26:40'),
('a10925fe-3375-456b-8a08-5d1958e7e85c', '2022-12-21', '23:15:00', '23:18:00', 12, 0, '2022-12-21 23:18:34'),
('eb3970ac-80d9-4934-8080-e9f629097304', '2022-12-26', '15:44:00', '15:50:18', 12, 0, '2022-12-26 15:44:33'),
('ec119a9b-aef9-40eb-a5e9-f2c44ecbb259', '2022-12-21', '23:04:00', '23:07:00', 12, 0, '2022-12-21 23:07:02');

-- --------------------------------------------------------

--
-- Estrutura para tabela `graze_drone_hence`
--

CREATE TABLE `graze_drone_hence` (
  `graze_id` varchar(36) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL,
  `drone_id` varchar(36) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL,
  `hence_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=ascii;

--
-- Despejando dados para a tabela `graze_drone_hence`
--

INSERT INTO `graze_drone_hence` (`graze_id`, `drone_id`, `hence_id`) VALUES
('9f2d0830-fe9c-456f-acf4-5460ec187f0b', '11111111-0000-0000-0000-111111111111', 12);

-- --------------------------------------------------------

--
-- Estrutura para tabela `hence`
--

CREATE TABLE `hence` (
  `hence_id` int NOT NULL,
  `hence_name` varchar(45) DEFAULT 'cerca-pastagem',
  `map_minX` double NOT NULL,
  `map_minY` double NOT NULL,
  `map_maxX` double NOT NULL,
  `map_maxY` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Despejando dados para a tabela `hence`
--

INSERT INTO `hence` (`hence_id`, `hence_name`, `map_minX`, `map_minY`, `map_maxX`, `map_maxY`) VALUES
(12, 'cerca-pastagem', 10, 10, 20, 20);

-- --------------------------------------------------------

--
-- Estrutura para tabela `ox`
--

CREATE TABLE `ox` (
  `id` varchar(36) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL DEFAULT '00000000-0000-0000-0000-000000000000',
  `name` varchar(40) NOT NULL,
  `birth` date NOT NULL,
  `weight` double NOT NULL,
  `breed` enum('NELORE','ANGUS','GIR','BRAHMAN','BRANGUS','SENEPOL','HEREFORD','CARACU','CHAROL?S','GUZER?','TABAPU?','SIMENTAL','LIMOUSIN') NOT NULL DEFAULT 'NELORE',
  `mom_id` varchar(36) NOT NULL DEFAULT '00000000-0000-0000-0000-000000000000',
  `dad_id` varchar(36) NOT NULL DEFAULT '00000000-0000-0000-0000-000000000000',
  `death` date DEFAULT NULL,
  `gender` enum('MALE','FEMALE') NOT NULL DEFAULT 'MALE',
  `photo` varchar(100) CHARACTER SET ascii COLLATE ascii_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=ascii;

--
-- Despejando dados para a tabela `ox`
--

INSERT INTO `ox` (`id`, `name`, `birth`, `weight`, `breed`, `mom_id`, `dad_id`, `death`, `gender`, `photo`) VALUES
('018b8fc8-a534-49e2-b861-30e98f4f3f29', 'Coragem', '2021-03-16', 455, 'NELORE', '522f75bb-b03a-41c6-812d-b93d0c77e548', '42e65227-67b5-4853-a220-7ea332ff77e1', NULL, 'MALE', 'images/boi36.jpg'),
('1b758239-be85-4695-b743-b2dc294115ca', 'Bono', '2011-09-01', 333, 'BRAHMAN', '260cf96d-fc10-4ea7-bde9-8e8fb1a1cc2e', 'd1a18870-0ef1-4c0d-826e-4a0d059817b1', '2015-12-31', 'MALE', 'images/boi006.jpg'),
('20c5c1ea-c831-4a92-ac42-4b9077c0775a', 'Camelo', '2021-06-26', 450, 'CARACU', '0f40b75a-d50b-4313-8a63-dca08f9f365e', 'b62c19dd-9a94-4a62-81ed-5dd8fb3e9a4a', NULL, 'MALE', 'images/boi022.jpg'),
('31d1b82c-5d79-4757-b554-a3ae871d60de', 'Trovao', '2021-07-14', 400, 'ANGUS', '260cf96d-fc10-4ea7-bde9-8e8fb1a1cc2e', 'd1a18870-0ef1-4c0d-826e-4a0d059817b1', NULL, 'MALE', 'images/boi022.jpg'),
('3c8a3ffc-07d2-4ea2-ae98-aa99350d63c7', 'Chico', '2020-01-01', 444, 'ANGUS', '522f75bb-b03a-41c6-812d-b93d0c77e548', 'c75b7a05-2f8f-4ea7-8feb-e0179846e6de', NULL, 'MALE', 'images/boi008.jpg'),
('497f25c6-b160-4dff-b6d2-96852c02d9c7', 'Mimosa', '2022-07-06', 199, 'BRANGUS', '0f40b75a-d50b-4313-8a63-dca08f9f365e', '1ee40ba9-07fa-482e-8267-517b34e1fb7e', NULL, 'FEMALE', 'images/boi017.jpg'),
('4e1b5f5c-f11e-4b42-97eb-a856d4e582f2', 'Tempestade', '2019-12-18', 423, 'SENEPOL', '0f40b75a-d50b-4313-8a63-dca08f9f365e', '1ee40ba9-07fa-482e-8267-517b34e1fb7e', NULL, 'FEMALE', 'images/ox2.png'),
('555d6901-1382-49af-963c-554240f3999e', 'Zebu', '2022-09-07', 145, 'HEREFORD', '260cf96d-fc10-4ea7-bde9-8e8fb1a1cc2e', 'b62c19dd-9a94-4a62-81ed-5dd8fb3e9a4a', NULL, 'MALE', 'images/boi008.jpg'),
('68e2dada-a0e5-41c8-8e39-cd8e0f519a7c', 'Bilu', '2022-11-27', 222, 'BRAHMAN', '0f40b75a-d50b-4313-8a63-dca08f9f365e', 'd1a18870-0ef1-4c0d-826e-4a0d059817b1', NULL, 'MALE', 'images/boi001.jpg'),
('72cc84f6-89c8-48b6-ac58-56df1d565cd3', 'Nero', '2020-12-01', 456, 'HEREFORD', '0f40b75a-d50b-4313-8a63-dca08f9f365e', 'b62c19dd-9a94-4a62-81ed-5dd8fb3e9a4a', NULL, 'MALE', 'images/boi002.jpg'),
('96ad61e7-bf7c-4956-95e3-d330c3119426', 'Lilica', '2022-12-07', 231, 'BRANGUS', '0f40b75a-d50b-4313-8a63-dca08f9f365e', 'd1a18870-0ef1-4c0d-826e-4a0d059817b1', NULL, 'FEMALE', 'images/boi018.jpg'),
('9cd7c23a-672a-4031-a580-43291caba9b2', 'Malvado', '2021-09-26', 400, 'SENEPOL', '54196440-3555-4ebc-914f-1b00f82c6829', 'd17a8a74-9b66-4a9b-af44-dd1a01069521', NULL, 'MALE', 'images/boi38.jpg'),
('bb8c8efd-2d4e-4fd5-ae8f-fbb04774e60f', 'Exemplo', '2023-10-24', 350, 'BRAHMAN', '54196440-3555-4ebc-914f-1b00f82c6829', 'b62c19dd-9a94-4a62-81ed-5dd8fb3e9a4a', NULL, 'MALE', 'images/boi-rf.png'),
('d36aba30-7a8d-445e-b5dc-1bb9fbc4f818', 'Pincel', '2021-12-30', 399, 'BRANGUS', '0f40b75a-d50b-4313-8a63-dca08f9f365e', 'd1a18870-0ef1-4c0d-826e-4a0d059817b1', NULL, 'MALE', 'images/boi33.jpg'),
('d95ebf19-f26c-4215-8706-0989a47d85c5', 'Branquinha', '2020-09-30', 380, 'ANGUS', '260cf96d-fc10-4ea7-bde9-8e8fb1a1cc2e', '168316d1-164e-4996-9542-50ec8abc3bb3', NULL, 'FEMALE', 'images/ox4.png'),
('df963087-11d2-44a5-bafd-c3f6789e1838', 'Zebra', '2021-05-17', 400, 'BRAHMAN', '522f75bb-b03a-41c6-812d-b93d0c77e548', 'c75b7a05-2f8f-4ea7-8feb-e0179846e6de', NULL, 'MALE', 'images/boi35.jpg'),
('e5dd1bb9-7154-4420-9856-347ccfa16c74', 'tiao', '2023-09-14', 222, 'BRAHMAN', '522f75bb-b03a-41c6-812d-b93d0c77e548', '42e65227-67b5-4853-a220-7ea332ff77e1', NULL, 'MALE', 'images/boi001.jpg'),
('edaf89e2-2a12-4851-94a6-142ea5b3e646', 'Carinho', '2022-01-03', 410, 'NELORE', '260cf96d-fc10-4ea7-bde9-8e8fb1a1cc2e', '42e65227-67b5-4853-a220-7ea332ff77e1', NULL, 'MALE', 'images/boi025.jpg');

-- --------------------------------------------------------

--
-- Estrutura para tabela `oxen_in_graze`
--

CREATE TABLE `oxen_in_graze` (
  `id` int NOT NULL,
  `graze_id` varchar(36) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL,
  `ox_id` varchar(36) NOT NULL,
  `is_present` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=ascii;

--
-- Despejando dados para a tabela `oxen_in_graze`
--

INSERT INTO `oxen_in_graze` (`id`, `graze_id`, `ox_id`, `is_present`) VALUES
(1451, '9f2d0830-fe9c-456f-acf4-5460ec187fcc', '1b758239-be85-4695-b743-b2dc294115ca', 1),
(1452, '9f2d0830-fe9c-456f-acf4-5460ec187fcc', '31d1b82c-5d79-4757-b554-a3ae871d60de', 1),
(1453, '9f2d0830-fe9c-456f-acf4-5460ec187fcc', '3c8a3ffc-07d2-4ea2-ae98-aa99350d63c7', 1),
(1454, '9f2d0830-fe9c-456f-acf4-5460ec187fcc', '497f25c6-b160-4dff-b6d2-96852c02d9c7', 1),
(1455, '9f2d0830-fe9c-456f-acf4-5460ec187fcc', '555d6901-1382-49af-963c-554240f3999e', 1),
(1456, '9f2d0830-fe9c-456f-acf4-5460ec187fcc', '68e2dada-a0e5-41c8-8e39-cd8e0f519a7c', 1),
(1457, '9f2d0830-fe9c-456f-acf4-5460ec187fcc', '72cc84f6-89c8-48b6-ac58-56df1d565cd3', 1),
(1458, '9f2d0830-fe9c-456f-acf4-5460ec187fcc', '96ad61e7-bf7c-4956-95e3-d330c3119426', 1),
(1459, '9f2d0830-fe9c-456f-acf4-5460ec187fcc', '1b758239-be85-4695-b743-b2dc294115ca', 1),
(1460, '9f2d0830-fe9c-456f-acf4-5460ec187f0b', '31d1b82c-5d79-4757-b554-a3ae871d60de', 1),
(1461, '9f2d0830-fe9c-456f-acf4-5460ec187f0b', '3c8a3ffc-07d2-4ea2-ae98-aa99350d63c7', 1),
(1462, '9f2d0830-fe9c-456f-acf4-5460ec187f0b', '497f25c6-b160-4dff-b6d2-96852c02d9c7', 1),
(1463, '9f2d0830-fe9c-456f-acf4-5460ec187f0b', '555d6901-1382-49af-963c-554240f3999e', 1),
(1464, '9f2d0830-fe9c-456f-acf4-5460ec187f0b', '68e2dada-a0e5-41c8-8e39-cd8e0f519a7c', 1),
(1465, '9f2d0830-fe9c-456f-acf4-5460ec187f0b', '72cc84f6-89c8-48b6-ac58-56df1d565cd3', 1),
(1466, '9f2d0830-fe9c-456f-acf4-5460ec187f0b', '96ad61e7-bf7c-4956-95e3-d330c3119426', 1),
(1467, 'ec119a9b-aef9-40eb-a5e9-f2c44ecbb259', '1b758239-be85-4695-b743-b2dc294115ca', 0),
(1468, 'ec119a9b-aef9-40eb-a5e9-f2c44ecbb259', '31d1b82c-5d79-4757-b554-a3ae871d60de', 0),
(1469, 'ec119a9b-aef9-40eb-a5e9-f2c44ecbb259', '3c8a3ffc-07d2-4ea2-ae98-aa99350d63c7', 0),
(1470, 'ec119a9b-aef9-40eb-a5e9-f2c44ecbb259', '497f25c6-b160-4dff-b6d2-96852c02d9c7', 0),
(1471, 'ec119a9b-aef9-40eb-a5e9-f2c44ecbb259', '555d6901-1382-49af-963c-554240f3999e', 0),
(1472, 'ec119a9b-aef9-40eb-a5e9-f2c44ecbb259', '68e2dada-a0e5-41c8-8e39-cd8e0f519a7c', 0),
(1473, 'ec119a9b-aef9-40eb-a5e9-f2c44ecbb259', '72cc84f6-89c8-48b6-ac58-56df1d565cd3', 0),
(1474, 'ec119a9b-aef9-40eb-a5e9-f2c44ecbb259', '96ad61e7-bf7c-4956-95e3-d330c3119426', 0),
(1475, 'a10925fe-3375-456b-8a08-5d1958e7e85c', '31d1b82c-5d79-4757-b554-a3ae871d60de', 1),
(1476, 'a10925fe-3375-456b-8a08-5d1958e7e85c', '497f25c6-b160-4dff-b6d2-96852c02d9c7', 1),
(1477, 'a10925fe-3375-456b-8a08-5d1958e7e85c', '555d6901-1382-49af-963c-554240f3999e', 1),
(1478, 'a10925fe-3375-456b-8a08-5d1958e7e85c', '1b758239-be85-4695-b743-b2dc294115ca', 1),
(1479, 'a10925fe-3375-456b-8a08-5d1958e7e85c', '72cc84f6-89c8-48b6-ac58-56df1d565cd3', 1),
(1480, 'a10925fe-3375-456b-8a08-5d1958e7e85c', '96ad61e7-bf7c-4956-95e3-d330c3119426', 1),
(1481, 'a10925fe-3375-456b-8a08-5d1958e7e85c', '68e2dada-a0e5-41c8-8e39-cd8e0f519a7c', 1),
(1482, 'a10925fe-3375-456b-8a08-5d1958e7e85c', '3c8a3ffc-07d2-4ea2-ae98-aa99350d63c7', 0),
(1483, '0493ee00-1a8a-435e-8434-dfe25b3f0bed', 'edaf89e2-2a12-4851-94a6-142ea5b3e646', 1),
(1484, '0493ee00-1a8a-435e-8434-dfe25b3f0bed', '3c8a3ffc-07d2-4ea2-ae98-aa99350d63c7', 1),
(1485, '0493ee00-1a8a-435e-8434-dfe25b3f0bed', '1b758239-be85-4695-b743-b2dc294115ca', 0),
(1486, '0493ee00-1a8a-435e-8434-dfe25b3f0bed', '31d1b82c-5d79-4757-b554-a3ae871d60de', 0),
(1487, '0493ee00-1a8a-435e-8434-dfe25b3f0bed', '497f25c6-b160-4dff-b6d2-96852c02d9c7', 0),
(1488, '0493ee00-1a8a-435e-8434-dfe25b3f0bed', '555d6901-1382-49af-963c-554240f3999e', 0),
(1489, '0493ee00-1a8a-435e-8434-dfe25b3f0bed', '68e2dada-a0e5-41c8-8e39-cd8e0f519a7c', 0),
(1490, '0493ee00-1a8a-435e-8434-dfe25b3f0bed', '72cc84f6-89c8-48b6-ac58-56df1d565cd3', 0),
(1491, '0493ee00-1a8a-435e-8434-dfe25b3f0bed', '96ad61e7-bf7c-4956-95e3-d330c3119426', 0),
(1492, '5bca1f87-cf16-4198-8a73-3b5aed707583', 'edaf89e2-2a12-4851-94a6-142ea5b3e646', 1),
(1493, '5bca1f87-cf16-4198-8a73-3b5aed707583', '3c8a3ffc-07d2-4ea2-ae98-aa99350d63c7', 1),
(1494, '5bca1f87-cf16-4198-8a73-3b5aed707583', '31d1b82c-5d79-4757-b554-a3ae871d60de', 1),
(1495, '5bca1f87-cf16-4198-8a73-3b5aed707583', '72cc84f6-89c8-48b6-ac58-56df1d565cd3', 1),
(1496, '5bca1f87-cf16-4198-8a73-3b5aed707583', '497f25c6-b160-4dff-b6d2-96852c02d9c7', 1),
(1497, '5bca1f87-cf16-4198-8a73-3b5aed707583', '1b758239-be85-4695-b743-b2dc294115ca', 1),
(1498, '5bca1f87-cf16-4198-8a73-3b5aed707583', '68e2dada-a0e5-41c8-8e39-cd8e0f519a7c', 1),
(1499, '5bca1f87-cf16-4198-8a73-3b5aed707583', '96ad61e7-bf7c-4956-95e3-d330c3119426', 1),
(1500, '5bca1f87-cf16-4198-8a73-3b5aed707583', '555d6901-1382-49af-963c-554240f3999e', 0),
(1501, '002a5eaa-ac6e-4527-b7ef-efddfc479f61', '72cc84f6-89c8-48b6-ac58-56df1d565cd3', 1),
(1502, '002a5eaa-ac6e-4527-b7ef-efddfc479f61', '1b758239-be85-4695-b743-b2dc294115ca', 0),
(1503, '002a5eaa-ac6e-4527-b7ef-efddfc479f61', '20c5c1ea-c831-4a92-ac42-4b9077c0775a', 0),
(1504, '002a5eaa-ac6e-4527-b7ef-efddfc479f61', '31d1b82c-5d79-4757-b554-a3ae871d60de', 0),
(1505, '002a5eaa-ac6e-4527-b7ef-efddfc479f61', '3c8a3ffc-07d2-4ea2-ae98-aa99350d63c7', 0),
(1506, '002a5eaa-ac6e-4527-b7ef-efddfc479f61', '497f25c6-b160-4dff-b6d2-96852c02d9c7', 0),
(1507, '002a5eaa-ac6e-4527-b7ef-efddfc479f61', '4e1b5f5c-f11e-4b42-97eb-a856d4e582f2', 0),
(1508, '002a5eaa-ac6e-4527-b7ef-efddfc479f61', '555d6901-1382-49af-963c-554240f3999e', 0),
(1509, '002a5eaa-ac6e-4527-b7ef-efddfc479f61', '68e2dada-a0e5-41c8-8e39-cd8e0f519a7c', 0),
(1510, '002a5eaa-ac6e-4527-b7ef-efddfc479f61', '96ad61e7-bf7c-4956-95e3-d330c3119426', 0),
(1511, '002a5eaa-ac6e-4527-b7ef-efddfc479f61', 'd95ebf19-f26c-4215-8706-0989a47d85c5', 0),
(1512, '002a5eaa-ac6e-4527-b7ef-efddfc479f61', 'edaf89e2-2a12-4851-94a6-142ea5b3e646', 0),
(1513, 'eb3970ac-80d9-4934-8080-e9f629097304', '9cd7c23a-672a-4031-a580-43291caba9b2', 1),
(1514, 'eb3970ac-80d9-4934-8080-e9f629097304', '555d6901-1382-49af-963c-554240f3999e', 1),
(1515, 'eb3970ac-80d9-4934-8080-e9f629097304', '96ad61e7-bf7c-4956-95e3-d330c3119426', 1),
(1516, 'eb3970ac-80d9-4934-8080-e9f629097304', 'edaf89e2-2a12-4851-94a6-142ea5b3e646', 1),
(1517, 'eb3970ac-80d9-4934-8080-e9f629097304', '20c5c1ea-c831-4a92-ac42-4b9077c0775a', 1),
(1518, 'eb3970ac-80d9-4934-8080-e9f629097304', '4e1b5f5c-f11e-4b42-97eb-a856d4e582f2', 1),
(1519, 'eb3970ac-80d9-4934-8080-e9f629097304', '72cc84f6-89c8-48b6-ac58-56df1d565cd3', 1),
(1520, 'eb3970ac-80d9-4934-8080-e9f629097304', 'd95ebf19-f26c-4215-8706-0989a47d85c5', 1),
(1521, 'eb3970ac-80d9-4934-8080-e9f629097304', 'df963087-11d2-44a5-bafd-c3f6789e1838', 1),
(1522, 'eb3970ac-80d9-4934-8080-e9f629097304', '018b8fc8-a534-49e2-b861-30e98f4f3f29', 1),
(1523, 'eb3970ac-80d9-4934-8080-e9f629097304', 'd36aba30-7a8d-445e-b5dc-1bb9fbc4f818', 1),
(1524, 'eb3970ac-80d9-4934-8080-e9f629097304', '31d1b82c-5d79-4757-b554-a3ae871d60de', 1),
(1525, 'eb3970ac-80d9-4934-8080-e9f629097304', '497f25c6-b160-4dff-b6d2-96852c02d9c7', 1),
(1526, 'eb3970ac-80d9-4934-8080-e9f629097304', '3c8a3ffc-07d2-4ea2-ae98-aa99350d63c7', 1),
(1527, 'eb3970ac-80d9-4934-8080-e9f629097304', '1b758239-be85-4695-b743-b2dc294115ca', 1),
(1528, 'eb3970ac-80d9-4934-8080-e9f629097304', '68e2dada-a0e5-41c8-8e39-cd8e0f519a7c', 1),
(1529, '13f331f1-5547-46f7-8498-2271461fad75', '96ad61e7-bf7c-4956-95e3-d330c3119426', 1),
(1530, '13f331f1-5547-46f7-8498-2271461fad75', '4e1b5f5c-f11e-4b42-97eb-a856d4e582f2', 1),
(1531, '13f331f1-5547-46f7-8498-2271461fad75', '72cc84f6-89c8-48b6-ac58-56df1d565cd3', 1),
(1532, '13f331f1-5547-46f7-8498-2271461fad75', 'd95ebf19-f26c-4215-8706-0989a47d85c5', 1),
(1533, '13f331f1-5547-46f7-8498-2271461fad75', '1b758239-be85-4695-b743-b2dc294115ca', 1),
(1534, '13f331f1-5547-46f7-8498-2271461fad75', '497f25c6-b160-4dff-b6d2-96852c02d9c7', 1),
(1535, '13f331f1-5547-46f7-8498-2271461fad75', '68e2dada-a0e5-41c8-8e39-cd8e0f519a7c', 1),
(1536, '13f331f1-5547-46f7-8498-2271461fad75', 'd36aba30-7a8d-445e-b5dc-1bb9fbc4f818', 1),
(1537, '13f331f1-5547-46f7-8498-2271461fad75', 'edaf89e2-2a12-4851-94a6-142ea5b3e646', 1),
(1538, '13f331f1-5547-46f7-8498-2271461fad75', 'df963087-11d2-44a5-bafd-c3f6789e1838', 1),
(1539, '13f331f1-5547-46f7-8498-2271461fad75', '9cd7c23a-672a-4031-a580-43291caba9b2', 1),
(1540, '13f331f1-5547-46f7-8498-2271461fad75', '20c5c1ea-c831-4a92-ac42-4b9077c0775a', 1),
(1541, '13f331f1-5547-46f7-8498-2271461fad75', '3c8a3ffc-07d2-4ea2-ae98-aa99350d63c7', 1),
(1542, '13f331f1-5547-46f7-8498-2271461fad75', '31d1b82c-5d79-4757-b554-a3ae871d60de', 1),
(1543, '13f331f1-5547-46f7-8498-2271461fad75', '018b8fc8-a534-49e2-b861-30e98f4f3f29', 1),
(1544, '13f331f1-5547-46f7-8498-2271461fad75', '555d6901-1382-49af-963c-554240f3999e', 1),
(1545, '77a86de0-7621-4efa-84fa-f9c3754eeaf5', '96ad61e7-bf7c-4956-95e3-d330c3119426', 1),
(1546, '77a86de0-7621-4efa-84fa-f9c3754eeaf5', '4e1b5f5c-f11e-4b42-97eb-a856d4e582f2', 1),
(1547, '77a86de0-7621-4efa-84fa-f9c3754eeaf5', '72cc84f6-89c8-48b6-ac58-56df1d565cd3', 1),
(1548, '77a86de0-7621-4efa-84fa-f9c3754eeaf5', 'd95ebf19-f26c-4215-8706-0989a47d85c5', 1),
(1549, '77a86de0-7621-4efa-84fa-f9c3754eeaf5', '1b758239-be85-4695-b743-b2dc294115ca', 1),
(1550, '77a86de0-7621-4efa-84fa-f9c3754eeaf5', '497f25c6-b160-4dff-b6d2-96852c02d9c7', 1),
(1551, '77a86de0-7621-4efa-84fa-f9c3754eeaf5', '68e2dada-a0e5-41c8-8e39-cd8e0f519a7c', 1),
(1552, '77a86de0-7621-4efa-84fa-f9c3754eeaf5', 'd36aba30-7a8d-445e-b5dc-1bb9fbc4f818', 1),
(1553, '77a86de0-7621-4efa-84fa-f9c3754eeaf5', 'edaf89e2-2a12-4851-94a6-142ea5b3e646', 1),
(1554, '77a86de0-7621-4efa-84fa-f9c3754eeaf5', 'df963087-11d2-44a5-bafd-c3f6789e1838', 1),
(1555, '77a86de0-7621-4efa-84fa-f9c3754eeaf5', '9cd7c23a-672a-4031-a580-43291caba9b2', 1),
(1556, '77a86de0-7621-4efa-84fa-f9c3754eeaf5', '20c5c1ea-c831-4a92-ac42-4b9077c0775a', 1),
(1557, '77a86de0-7621-4efa-84fa-f9c3754eeaf5', '3c8a3ffc-07d2-4ea2-ae98-aa99350d63c7', 1),
(1558, '77a86de0-7621-4efa-84fa-f9c3754eeaf5', '018b8fc8-a534-49e2-b861-30e98f4f3f29', 0),
(1559, '77a86de0-7621-4efa-84fa-f9c3754eeaf5', '31d1b82c-5d79-4757-b554-a3ae871d60de', 0),
(1560, '77a86de0-7621-4efa-84fa-f9c3754eeaf5', '555d6901-1382-49af-963c-554240f3999e', 0);

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `drone`
--
ALTER TABLE `drone`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `farm`
--
ALTER TABLE `farm`
  ADD PRIMARY KEY (`name`);

--
-- Índices de tabela `graze`
--
ALTER TABLE `graze`
  ADD PRIMARY KEY (`id`),
  ADD KEY `graze_FK` (`hence_id`);

--
-- Índices de tabela `graze_drone_hence`
--
ALTER TABLE `graze_drone_hence`
  ADD KEY `graze_drone_hence_FK` (`graze_id`),
  ADD KEY `graze_drone_hence_FK_2` (`hence_id`),
  ADD KEY `graze_drone_hence_FK_1` (`drone_id`);

--
-- Índices de tabela `hence`
--
ALTER TABLE `hence`
  ADD PRIMARY KEY (`hence_id`);

--
-- Índices de tabela `ox`
--
ALTER TABLE `ox`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `oxen_in_graze`
--
ALTER TABLE `oxen_in_graze`
  ADD PRIMARY KEY (`id`),
  ADD KEY `oxen_in_graze_FK_1` (`graze_id`),
  ADD KEY `oxen_in_graze_FK` (`ox_id`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `oxen_in_graze`
--
ALTER TABLE `oxen_in_graze`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1561;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `graze`
--
ALTER TABLE `graze`
  ADD CONSTRAINT `graze_FK` FOREIGN KEY (`hence_id`) REFERENCES `hence` (`hence_id`);

--
-- Restrições para tabelas `graze_drone_hence`
--
ALTER TABLE `graze_drone_hence`
  ADD CONSTRAINT `graze_drone_hence_FK_2` FOREIGN KEY (`hence_id`) REFERENCES `hence` (`hence_id`);

--
-- Restrições para tabelas `oxen_in_graze`
--
ALTER TABLE `oxen_in_graze`
  ADD CONSTRAINT `oxen_in_graze_FK` FOREIGN KEY (`ox_id`) REFERENCES `ox` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
