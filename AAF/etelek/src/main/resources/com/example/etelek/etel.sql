-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2024. Nov 06. 20:59
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
-- Adatbázis: `etel`
--
CREATE DATABASE IF NOT EXISTS `etel` DEFAULT CHARACTER SET utf8 COLLATE utf8_hungarian_ci;
USE `etel`;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `etelek`
--

CREATE TABLE `etelek` (
  `az` int(11) NOT NULL,
  `nev` varchar(64) COLLATE utf8_hungarian_ci NOT NULL,
  `ar` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `etelek`
--

INSERT INTO `etelek` (`az`, `nev`, `ar`) VALUES
(1, 'Sonkás-sajtos pizza', 2300),
(2, 'Rántott szelet körettel', 4500),
(3, 'Brassói aprópecsenye', 3200),
(4, 'Újházi tyúkhúsleves', 2000),
(5, 'Gyros tál', 2050),
(6, 'Sertés raguleves', 2800),
(7, 'Csirkés cézár saláta', 3500),
(8, 'Bolognai spagetti', 3600),
(9, 'Carbonara tészta', 3900),
(10, 'Hawaii pizza', 2990),
(11, 'Big Mac McMenü', 2250);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `rendeles`
--

CREATE TABLE `rendeles` (
  `raz` int(11) NOT NULL,
  `datum` varchar(5) COLLATE utf8_hungarian_ci NOT NULL,
  `az` int(11) NOT NULL,
  `adag` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `rendeles`
--

INSERT INTO `rendeles` (`raz`, `datum`, `az`, `adag`) VALUES
(1, '08.16', 5, 5),
(2, '10.24', 2, 3),
(3, '11.18', 8, 3),
(4, '02.11', 1, 5),
(5, '01.12', 8, 1),
(6, '02.13', 2, 1),
(7, '08.08', 4, 1),
(8, '09.24', 2, 5),
(9, '01.10', 4, 3),
(10, '07.06', 4, 3),
(11, '05.04', 3, 2),
(12, '11.06', 10, 3),
(13, '02.08', 2, 5),
(14, '01.23', 8, 3),
(15, '04.18', 2, 5),
(16, '11.05', 4, 2),
(17, '10.23', 6, 2),
(18, '09.19', 2, 2),
(19, '04.20', 5, 5);

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `etelek`
--
ALTER TABLE `etelek`
  ADD PRIMARY KEY (`az`);

--
-- A tábla indexei `rendeles`
--
ALTER TABLE `rendeles`
  ADD PRIMARY KEY (`raz`),
  ADD KEY `etelaz` (`az`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `etelek`
--
ALTER TABLE `etelek`
  MODIFY `az` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT a táblához `rendeles`
--
ALTER TABLE `rendeles`
  MODIFY `raz` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `rendeles`
--
ALTER TABLE `rendeles`
  ADD CONSTRAINT `etelaz` FOREIGN KEY (`az`) REFERENCES `etelek` (`az`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
