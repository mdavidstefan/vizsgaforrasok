-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2024. Nov 13. 21:35
-- Kiszolgáló verziója: 10.4.6-MariaDB
-- PHP verzió: 7.3.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `iskola`
--
CREATE DATABASE IF NOT EXISTS `iskola` DEFAULT CHARACTER SET utf8 COLLATE utf8_hungarian_ci;
USE `iskola`;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `osztaly`
--

CREATE TABLE `osztaly` (
  `oaz` int(11) NOT NULL,
  `osztaly` varchar(16) COLLATE utf8_hungarian_ci NOT NULL,
  `szakma` varchar(32) COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `osztaly`
--

INSERT INTO `osztaly` (`oaz`, `osztaly`, `szakma`) VALUES
(1, '9/A', 'informatika'),
(2, '9/B', 'gépészet'),
(3, '10/A', 'informatika'),
(4, '10/B', 'gépészet'),
(5, '11/A', 'informatika'),
(6, '11/B', 'gépészet'),
(7, '12/A', 'informatika'),
(8, '12/B', 'gépészet'),
(9, '13/A', 'informatika'),
(10, '13/B', 'gépészet');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `tanulo`
--

CREATE TABLE `tanulo` (
  `taz` int(11) NOT NULL,
  `nev` varchar(64) COLLATE utf8_hungarian_ci NOT NULL,
  `szul` varchar(16) COLLATE utf8_hungarian_ci NOT NULL,
  `oaz` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `tanulo`
--

INSERT INTO `tanulo` (`taz`, `nev`, `szul`, `oaz`) VALUES
(1, 'Csongrádi Bence', '2010.06.03', 4),
(2, 'Sipos Renáta', '2008.12.11', 7),
(3, 'Dinnyés Benedek', '2011.08.04', 4),
(4, 'Almási Attila', '2006.04.26', 8),
(5, 'Fekete Csanád', '2004.03.06', 10),
(6, 'Hegedűs Zsombor', '2005.10.17', 10),
(7, 'Kondacs Ádám', '2014.07.07', 1),
(8, 'Német Andor Attila', '2006.05.11', 8),
(9, 'Szabó Tamás', '2011.05.01', 3),
(10, 'Török Máté', '2007.08.27', 8),
(11, 'Buknicz Csaba Kevin', '2007.02.24', 7),
(12, 'Gyovai Bálint', '2008.06.22', 7),
(13, 'Kunsági Lázár', '2007.06.07', 8),
(14, 'Simon Ádám', '2004.03.08', 10),
(15, 'Tóth János', '2010.11.13', 4),
(16, 'Darabos Milán', '2005.08.26', 9),
(17, 'Jakab István', '2010.05.22', 4),
(18, 'Légrádi Atilla József', '2013.09.15', 2),
(19, 'Nyikos Bence', '2006.11.13', 9),
(20, 'Szabó Gergely Péter', '2005.12.03', 9),
(21, 'Almási Blanka', '2012.04.28', 2),
(22, 'Fekete Imre', '2013.01.05', 2),
(23, 'Kapitány Réka', '2005.01.11', 10),
(24, 'Nagy Emília', '2014.08.20', 1),
(25, 'Szabó Mira Életke', '2008.12.08', 7),
(26, 'Zsombok Eszter', '2004.04.04', 10),
(27, 'Cseh Luca', '2007.08.04', 7),
(28, 'Keresztes Rebeka', '2007.10.26', 7),
(29, 'Leel-Őssy Dóra Diána', '2008.03.19', 6),
(30, 'Szabó Lili Leona', '2013.02.28', 2),
(31, 'Tóth Laura Kincső', '2005.04.21', 9),
(32, 'Chioato Marco', '2006.11.12', 8),
(33, 'Donka Ákos Norbert', '2006.08.15', 9),
(34, 'Juhász Bence Csaba', '2008.11.04', 7),
(35, 'Klemenc Patrik', '2011.03.07', 4),
(36, 'Lakatos Dominik', '2006.01.21', 9),
(37, 'Szöllősi Noé', '2007.07.04', 8),
(38, 'Varga Zalán Tibor', '2008.03.17', 7),
(39, 'Walter Ádám', '2008.04.19', 7),
(40, 'Farkas Tamás', '2005.02.05', 10),
(41, 'Járomi Zsombor György', '2007.11.20', 8),
(42, 'Losonczi Marcell', '2010.12.13', 5),
(43, 'Nyitrai László', '2012.03.27', 3),
(44, 'Sági Benő', '2012.01.06', 2),
(45, 'Varga Botond', '2013.03.24', 2),
(46, 'Csikász Martin', '2006.11.22', 9),
(47, 'Flórián Botond', '2010.05.18', 4),
(48, 'Hajdú Kornél Bálint', '2011.10.18', 3),
(49, 'Kelő-Gere Ádám', '2012.10.26', 3),
(50, 'Madi Róbert', '2008.08.04', 7),
(51, 'Patai Ádám', '2012.05.22', 3),
(52, 'Ádám Hunor', '2011.05.18', 3),
(53, 'Fejszés Gábor', '2005.04.11', 10),
(54, 'Horváth Balázs', '2014.05.01', 1),
(55, 'Kamenár Máté', '2006.01.21', 8),
(56, 'Molnár Dávid', '2014.07.18', 1),
(57, 'Tihanics Zalán', '2014.10.05', 1),
(58, 'Varga Zénó', '2008.02.13', 6),
(59, 'Cseh Anna', '2010.03.10', 5),
(60, 'Guzsik Boglárka', '2005.11.19', 10),
(61, 'Kovács Aliz', '2005.12.20', 9),
(62, 'Németh Lili Hanna', '2005.12.20', 10),
(63, 'Sipos Karina', '2009.11.19', 5),
(64, 'Szmetena Dorina', '2009.06.08', 6),
(65, 'Balog Dávid', '2006.05.23', 8),
(66, 'Horváth András', '2008.11.10', 7),
(67, 'Mészáros Márk', '2009.05.09', 6),
(68, 'Rácz Dávid', '2014.10.21', 1),
(69, 'Veres Iván', '2007.02.07', 8),
(70, 'Bugyi András Patrik', '2011.05.26', 4),
(71, 'Halmosi Kornél', '2010.09.02', 4),
(72, 'Kovács Tamás', '2014.07.25', 1),
(73, 'Nagy Bianka Rebeka', '2011.12.06', 4),
(74, 'Váradi Marcell', '2012.01.06', 3),
(75, 'Anda Máté Zoltán', '2008.11.27', 6),
(76, 'Csernus Mátyás', '2011.02.14', 4),
(77, 'Csordás Márk', '2008.05.01', 6),
(78, 'Hernádi Ádám', '2008.10.22', 7),
(79, 'László Ferenc Tibor', '2005.03.03', 9),
(80, 'Nagy Kristóf', '2014.12.25', 1),
(81, 'Szabó Ákos', '2010.09.10', 4),
(82, 'Tóth Barnabás', '2014.04.08', 1),
(83, 'Hamar Armand Dominik', '2014.10.02', 1),
(84, 'Kéri Tibor Kristóf', '2010.06.28', 5),
(85, 'Ladányi Máté', '2011.11.21', 3),
(86, 'Miklós Zoltán Roland', '2009.11.04', 5),
(87, 'Páldeák Ádám', '2007.05.06', 7),
(88, 'Sánta Zoltán Roland', '2010.06.25', 5),
(89, 'Szabó Péter', '2007.08.11', 7),
(90, 'Zoboki Botond', '2011.11.06', 3),
(91, 'Farkas Katalin', '2007.09.13', 7),
(92, 'Kránicz Anna Mónika', '2007.03.22', 8),
(93, 'Mócza Jázmin Katinka', '2009.09.22', 5),
(94, 'Rácz Xavér', '2007.11.19', 7),
(95, 'Fazekas Norbert Márk', '2006.10.19', 8),
(96, 'Kocsák Krisztián', '2012.10.01', 2),
(97, 'Rózsa Dániel', '2004.07.26', 10),
(98, 'Varga Péter', '2007.04.26', 8),
(99, 'Angyal Máté', '2013.03.14', 2),
(100, 'Farkas Bence', '2007.04.11', 7),
(101, 'Golovics Attila', '2013.06.02', 1),
(102, 'Hevér-Szabó Dávid Béla', '2011.08.03', 4),
(103, 'Kecskés Bálint', '2007.05.09', 8),
(104, 'Kocsis Ágnes Virág', '2007.08.11', 7),
(105, 'Murár Gábor', '2007.09.22', 7),
(106, 'Vági Dorina Mónika', '2004.12.26', 10),
(107, 'Giricz Dániel', '2013.11.22', 1),
(108, 'Kovács Sándor György', '2010.02.18', 4),
(109, 'Mócza Máté', '2011.06.01', 4),
(110, 'Sárik Zoltán', '2014.05.10', 1),
(111, 'Balázs Tibor Ferenc', '2005.07.08', 10),
(112, 'Fórizs Bence', '2011.05.27', 3),
(113, 'Kara Anita', '2009.09.12', 6),
(114, 'Ujvári János Márk', '2008.01.11', 7),
(115, 'Disztl Bernadett', '2012.07.22', 3),
(116, 'Kalmár Anna', '2007.08.12', 8),
(117, 'Panik Emma', '2009.07.26', 6),
(118, 'Szabó Nándor', '2005.05.10', 10),
(119, 'Takó Martin Antal', '2013.08.23', 2),
(120, 'Vég Bettina', '2014.02.07', 1),
(121, 'Czinege Bence', '2007.08.16', 8),
(122, 'Kiszel Gergő', '2012.12.12', 3),
(123, 'Pongó István', '2005.08.07', 9),
(124, 'Tóth Ákos', '2013.04.15', 1),
(125, 'Vincze Tamás', '2013.03.26', 1),
(126, 'Csák Dávid', '2004.07.10', 10),
(127, 'Kardos Bálint Péter', '2010.02.08', 5),
(128, 'Mester Sándor', '2012.05.28', 2),
(129, 'Tóth Csongor Gábor', '2005.08.24', 10),
(130, 'Vesztergombi Richárd', '2006.10.22', 8),
(131, 'Czidor Milán Martin', '2008.05.08', 7),
(132, 'Herczeg Gergely', '2010.12.19', 5),
(133, 'Kovács Levente', '2008.11.11', 7),
(134, 'Mészáros Szabolcs', '2013.07.18', 1),
(135, 'Szabó Bence', '2013.02.09', 1),
(136, 'Csizmadia Bence Zoltán', '2009.12.12', 6),
(137, 'Kis Martin', '2011.07.17', 4),
(138, 'Lovászi Ádám', '2004.05.07', 10),
(139, 'Pulai Dezső', '2014.11.21', 1),
(140, 'Tok Balázs', '2013.06.23', 2),
(141, 'Csontos Lili Edina', '2007.04.23', 8),
(142, 'Juhász Bettina', '2005.01.04', 9),
(143, 'Lics Rebeka', '2008.12.24', 6),
(144, 'Szécsi Nikolett', '2014.11.27', 1),
(145, 'Varga Zsófi', '2007.11.17', 7),
(146, 'Kelemen Dániel', '2006.05.07', 8),
(147, 'Fekete Kristóf', '2012.08.20', 3),
(148, 'Balázs János Ádám', '2008.05.10', 7),
(149, 'Danics Dávid', '2013.08.12', 1),
(150, 'Fodor Martin János', '2010.09.10', 4),
(151, 'Kardos Zoltán', '2012.02.16', 2),
(152, 'Ruskó Bence', '2009.08.03', 6),
(153, 'Tóth Igor', '2007.11.17', 8),
(154, 'Antal Csaba Dávid', '2009.03.08', 6),
(155, 'Farkas Balázs', '2011.06.28', 4),
(156, 'Kis Klaudia Ágnes', '2012.09.12', 3),
(157, 'Sánta Zsombor Csaba', '2005.05.08', 9),
(158, 'Balogh Barnabás', '2007.08.28', 7),
(159, 'Ivicz Szabolcs', '2012.06.20', 3),
(160, 'Molnár Marcell', '2006.06.24', 9),
(161, 'Selmeci Márk', '2008.07.25', 6),
(162, 'Balogh Norbert Krisztián', '2013.10.26', 1),
(163, 'Görcsi Edmond Tibor', '2006.08.24', 9),
(164, 'Kóré Béla Áron', '2009.06.14', 6),
(165, 'Laci Botond József', '2014.12.24', 1),
(166, 'Stefán Levente Péter', '2004.11.27', 10),
(167, 'Csernát Tibor', '2005.07.03', 10),
(168, 'Keller Dávid', '2004.07.14', 10),
(169, 'Perecz Nikolett Julianna', '2010.04.11', 5),
(170, 'Zsákai Roland Ferenc', '2008.08.08', 7),
(171, 'Jakab Roland', '2009.05.03', 5),
(172, 'Kovács Ábel', '2011.09.10', 4),
(173, 'Mózer Szilveszter', '2013.02.03', 2),
(174, 'Szabadszállási Dániel', '2012.06.14', 2),
(175, 'Dorogi Milán Márton', '2012.09.12', 2),
(176, 'Keres Richárd', '2010.09.08', 4),
(177, 'Lakatos Ferenc', '2008.08.06', 6),
(178, 'Mészáros Szabolcs', '2011.06.12', 3),
(179, 'Szűcs Donát', '2005.09.24', 10),
(180, 'Béki Bálint', '2008.07.23', 6),
(181, 'Juhász Máté', '2012.08.06', 2),
(182, 'Mihály Péter András', '2013.09.10', 2),
(183, 'Sztojka Sándor János', '2008.07.05', 6),
(184, 'Andrási Bálint', '2014.07.16', 1),
(185, 'Bódog Norbert', '2009.11.10', 6),
(186, 'Csáki Zalán', '2011.10.22', 3),
(187, 'Gáspár Gergő', '2010.07.28', 5),
(188, 'Lázár Levente', '2007.11.15', 7),
(189, 'Nagy Roland László', '2008.08.14', 6),
(190, 'Szentmiklósi Máté', '2014.06.08', 1),
(191, 'Csuka Imre Milán', '2006.04.22', 9),
(192, 'Győrfi Gábor', '2007.10.04', 7),
(193, 'Kovács Norbert', '2008.08.16', 7),
(194, 'Terenyi Zsolt Tivadar', '2011.03.22', 4),
(195, 'Czifra Pál', '2007.09.18', 7),
(196, 'Kéri Gábor', '2007.08.04', 7),
(197, 'Nagy Zoltán', '2009.09.01', 6),
(198, 'Pullai Károly Mihály', '2012.12.24', 2),
(199, 'Szili Dominik', '2010.08.24', 5),
(200, 'Bézi Patrik Milán', '2011.09.18', 4),
(201, 'Galgóczy Márk Ágoston', '2007.06.04', 7),
(202, 'Mayer Roland', '2010.07.26', 5),
(203, 'Olasz Gergő', '2008.01.17', 7),
(204, 'Szabó Dominik', '2007.02.07', 8),
(205, 'Varga Milán Flórián', '2012.03.18', 3),
(206, 'Bíró Luca', '2011.08.19', 4),
(207, 'Pintér Martina', '2007.02.11', 8),
(208, 'Valkai Gergő', '2014.12.28', 1),
(209, 'Kovács Kornél', '2007.10.03', 8),
(210, 'Nyúzó Szabolcs', '2007.11.12', 7),
(211, 'Schulcz Tibor Norbert', '2010.10.11', 5),
(212, 'Burka Tamás Sándor', '2009.02.12', 6),
(213, 'Kaszala Kristóf', '2005.02.13', 10),
(214, 'Nagy Zsombor', '2005.11.26', 10),
(215, 'Tokodi Tamás', '2007.07.11', 7),
(216, 'Bársony Sándor', '2010.02.13', 4),
(217, 'Bezsenyi Dániel', '2005.06.03', 10),
(218, 'Farkas Viktor', '2008.06.28', 6),
(219, 'Király Tibor Dániel', '2005.02.28', 9),
(220, 'Makai Barnabás', '2013.04.21', 2),
(221, 'Pálinkás Marcell', '2007.01.09', 8),
(222, 'Sárközi Orsolya', '2006.02.07', 8),
(223, 'Vida-Filus Dóra', '2008.01.26', 7),
(224, 'Fodor Bence Lajos', '2010.07.03', 5),
(225, 'Kádár-Németh Levente', '2008.04.25', 6),
(226, 'Márton Adrienn', '2012.03.08', 2),
(227, 'Orosz Ákos László', '2007.08.19', 8),
(228, 'Szabó Alex', '2014.07.28', 1),
(229, 'Bordács Tamás Gergő', '2013.08.19', 2),
(230, 'Kiss József', '2011.04.04', 4),
(231, 'Rapcsák Bence', '2005.03.24', 10),
(232, 'Szeleczki Sándor', '2008.05.12', 7),
(233, 'Tóth Enikő', '2006.06.23', 8),
(234, 'Fazekas László', '2007.12.07', 7),
(235, 'Kovács Dávid', '2006.03.02', 8),
(236, 'Szabadkai János', '2006.04.25', 9),
(237, 'Bende László Bence', '2007.11.14', 8),
(238, 'Lefor Attila István', '2012.10.12', 2),
(239, 'Bencsik Roland', '2012.04.15', 2),
(240, 'Harmat Sándor', '2013.12.03', 1),
(241, 'Sipos Richárd', '2005.01.04', 10),
(242, 'Brán András', '2007.07.04', 8),
(243, 'Nyúl Krisztián Pál', '2009.02.07', 6),
(244, 'Grósz Béla', '2010.06.19', 5),
(245, 'Baksa Zoltán Krisztián', '2012.01.04', 3),
(246, 'Döme László', '2007.10.26', 7),
(247, 'Hámori Tibor', '2005.10.06', 10),
(248, 'Kalocsai  Krisztián', '2006.05.20', 8),
(249, 'Kővári Csaba', '2006.09.18', 9),
(250, 'Mikó János', '2012.06.05', 2),
(251, 'Perina Balázs László', '2011.12.07', 3),
(252, 'Plank Mihály Viktor', '2009.12.23', 6),
(253, 'Szüle János', '2012.06.13', 2),
(254, 'Túri-Kiss Norbert', '2010.11.05', 4),
(255, 'Virág  György', '2014.09.03', 1);

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `osztaly`
--
ALTER TABLE `osztaly`
  ADD PRIMARY KEY (`oaz`);

--
-- A tábla indexei `tanulo`
--
ALTER TABLE `tanulo`
  ADD PRIMARY KEY (`taz`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `osztaly`
--
ALTER TABLE `osztaly`
  MODIFY `oaz` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT a táblához `tanulo`
--
ALTER TABLE `tanulo`
  MODIFY `taz` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=256;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
