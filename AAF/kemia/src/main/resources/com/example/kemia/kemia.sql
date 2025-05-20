-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2024. Nov 17. 17:49
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
-- Adatbázis: `kemia`
--
CREATE DATABASE IF NOT EXISTS `kemia` DEFAULT CHARACTER SET utf8 COLLATE utf8_hungarian_ci;
USE `kemia`;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `elemek`
--

CREATE TABLE `elemek` (
  `rend` int(11) NOT NULL,
  `jel` varchar(5) COLLATE utf8_hungarian_ci NOT NULL,
  `nev` varchar(32) COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `elemek`
--

INSERT INTO `elemek` (`rend`, `jel`, `nev`) VALUES
(1, 'H', 'hidrogén'),
(5, 'B', 'bór'),
(6, 'C', 'szén'),
(7, 'N', 'nitrogén'),
(8, 'O', 'oxigén'),
(11, 'Na', 'nátrium'),
(16, 'S', 'kén'),
(17, 'Cl', 'klór'),
(20, 'Ca', 'kalcium'),
(47, 'Ag', 'ezüst'),
(56, 'Ba', 'bárium'),
(79, 'Au', 'arany');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `kotes`
--

CREATE TABLE `kotes` (
  `vaz` int(11) NOT NULL,
  `rend` int(11) NOT NULL,
  `db` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `kotes`
--

INSERT INTO `kotes` (`vaz`, `rend`, `db`) VALUES
(1, 56, 1),
(1, 6, 1),
(1, 8, 3),
(2, 56, 1),
(2, 17, 2),
(3, 5, 2),
(3, 8, 3),
(4, 5, 1),
(4, 17, 3),
(5, 16, 1),
(5, 8, 4),
(6, 47, 2),
(6, 16, 1),
(7, 1, 1),
(7, 17, 1),
(8, 20, 1),
(8, 6, 1),
(8, 8, 3),
(9, 16, 1),
(9, 8, 2),
(10, 1, 2),
(10, 16, 1),
(10, 8, 4),
(11, 11, 1),
(11, 17, 1),
(12, 7, 1),
(12, 8, 2),
(13, 8, 3),
(14, 6, 1),
(14, 8, 2),
(15, 1, 2),
(15, 8, 1);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `vegyulet`
--

CREATE TABLE `vegyulet` (
  `vaz` int(11) NOT NULL,
  `nev` varchar(32) COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `vegyulet`
--

INSERT INTO `vegyulet` (`vaz`, `nev`) VALUES
(1, 'bárium-karbonát'),
(2, 'bárium-klorid'),
(3, 'bór-oxid'),
(4, 'bór-triklorid'),
(5, 'cink-szulfát'),
(6, 'ezüst-szulfid'),
(7, 'hidrogén-klorid'),
(8, 'kalcium-karbonát'),
(9, 'kén-dioxid'),
(10, 'kénsav'),
(11, 'nátrium-klorid'),
(12, 'nitrogén-dioxid'),
(13, 'ózon'),
(14, 'szén-dioxid'),
(15, 'víz');

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `elemek`
--
ALTER TABLE `elemek`
  ADD PRIMARY KEY (`rend`);

--
-- A tábla indexei `kotes`
--
ALTER TABLE `kotes`
  ADD KEY `vaz` (`vaz`),
  ADD KEY `rend` (`rend`);

--
-- A tábla indexei `vegyulet`
--
ALTER TABLE `vegyulet`
  ADD PRIMARY KEY (`vaz`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `vegyulet`
--
ALTER TABLE `vegyulet`
  MODIFY `vaz` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `kotes`
--
ALTER TABLE `kotes`
  ADD CONSTRAINT `kotes_ibfk_1` FOREIGN KEY (`vaz`) REFERENCES `vegyulet` (`vaz`),
  ADD CONSTRAINT `kotes_ibfk_2` FOREIGN KEY (`rend`) REFERENCES `elemek` (`rend`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
