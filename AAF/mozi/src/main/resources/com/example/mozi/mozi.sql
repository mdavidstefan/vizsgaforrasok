-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2024. Nov 11. 19:34
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
-- Adatbázis: `mozi`
--
CREATE DATABASE IF NOT EXISTS `mozi` DEFAULT CHARACTER SET utf8 COLLATE utf8_hungarian_ci;
USE `mozi`;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `filmek`
--

CREATE TABLE `filmek` (
  `faz` int(11) NOT NULL,
  `cim` varchar(42) COLLATE utf8_hungarian_ci NOT NULL,
  `ev` int(11) NOT NULL,
  `hossz` int(11) NOT NULL,
  `imdb` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `filmek`
--

INSERT INTO `filmek` (`faz`, `cim`, `ev`, `hossz`, `imdb`) VALUES
(1, 'A sötét lovag', 2008, 152, 9),
(2, 'Forrest Gump', 1994, 142, 8.8),
(3, 'Eredet', 2010, 148, 8.8),
(4, 'Vissza a jövőbe', 1985, 116, 8.5),
(5, 'Rendőrakadémia', 1984, 96, 6.7),
(6, 'A bárányok hallgatnak', 1991, 118, 8.6),
(7, 'A Jedi visszatér', 1983, 131, 8.3),
(8, 'Rettenthetetlen', 1995, 178, 8.3),
(9, 'Ready Player One', 2018, 140, 7.5),
(10, 'Csupasz pisztoly', 1988, 85, 7.6),
(11, 'Életrevalók', 2011, 107, 8.5),
(12, 'Mátrix', 1999, 136, 8.6),
(13, 'Gyalog galopp', 1975, 91, 8.2),
(14, 'Csillagok között', 2014, 169, 8.6),
(15, 'Élősködők', 2019, 132, 8.6),
(16, 'Jurassic Park', 1993, 127, 8.1),
(17, 'Leon, a profi', 1994, 110, 8.5),
(18, 'A nyolcadik utas: a halál', 1979, 117, 8.4),
(19, 'A dolog', 1982, 109, 8.1),
(20, 'Coco', 2017, 105, 8.4),
(21, 'Hatodik érzék', 1999, 107, 8.1),
(22, 'Agymanók', 2015, 95, 8.1),
(23, 'Némó nyomában', 2003, 100, 8.1),
(24, 'Wall-E', 2008, 98, 8.4),
(25, 'Így neveld a sárkányodat', 2010, 98, 8.1),
(26, 'Brian élete', 1979, 94, 8.1);

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `filmek`
--
ALTER TABLE `filmek`
  ADD PRIMARY KEY (`faz`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `filmek`
--
ALTER TABLE `filmek`
  MODIFY `faz` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
