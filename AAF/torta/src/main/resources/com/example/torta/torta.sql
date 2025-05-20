-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2024. Nov 20. 21:19
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
-- Adatbázis: `torta`
--
CREATE DATABASE IF NOT EXISTS `torta` DEFAULT CHARACTER SET utf8 COLLATE utf8_hungarian_ci;
USE `torta`;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `rendeles`
--

CREATE TABLE `rendeles` (
  `raz` int(11) NOT NULL,
  `taz` int(11) NOT NULL,
  `szelet` int(11) NOT NULL,
  `hab` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `rendeles`
--

INSERT INTO `rendeles` (`raz`, `taz`, `szelet`, `hab`) VALUES
(1, 1, 2, 0),
(2, 2, 3, 1),
(3, 4, 4, 0),
(4, 8, 1, 0);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `tortak`
--

CREATE TABLE `tortak` (
  `taz` int(11) NOT NULL,
  `fajta` varchar(32) COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `tortak`
--

INSERT INTO `tortak` (`taz`, `fajta`) VALUES
(1, 'Dobos torta'),
(2, 'Csoki torta'),
(3, 'Puncs torta'),
(4, 'Oroszkrém torta'),
(5, 'Feketeerdő torta'),
(6, 'Gesztenye torta'),
(7, 'Lúdláb torta'),
(8, 'Túrótorta');

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `rendeles`
--
ALTER TABLE `rendeles`
  ADD PRIMARY KEY (`raz`),
  ADD KEY `fajta` (`taz`);

--
-- A tábla indexei `tortak`
--
ALTER TABLE `tortak`
  ADD PRIMARY KEY (`taz`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `rendeles`
--
ALTER TABLE `rendeles`
  MODIFY `raz` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT a táblához `tortak`
--
ALTER TABLE `tortak`
  MODIFY `taz` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `rendeles`
--
ALTER TABLE `rendeles`
  ADD CONSTRAINT `fajta` FOREIGN KEY (`taz`) REFERENCES `tortak` (`taz`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
