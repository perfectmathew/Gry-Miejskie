-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Czas generowania: 29 Gru 2021, 08:25
-- Wersja serwera: 8.0.27
-- Wersja PHP: 7.4.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `podchody`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `exams`
--

CREATE TABLE `exams` (
  `ID` int NOT NULL,
  `Nazwa` varchar(255) NOT NULL,
  `IDGame` int DEFAULT NULL,
  `WysGeograficzna` varchar(255) DEFAULT NULL,
  `SzerGeograficzna` varchar(255) DEFAULT NULL,
  `ProgZaliczenia` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Zrzut danych tabeli `exams`
--

INSERT INTO `exams` (`ID`, `Nazwa`, `IDGame`, `WysGeograficzna`, `SzerGeograficzna`, `ProgZaliczenia`) VALUES
(1, 'Szybki Test', 2, '2,3322323', '2,3322323', 75),
(2, 'Test', 0, 'xxx', 'xxx', 5),
(3, 'Test o piwach', 1, '3,333333', '2,2222', 75);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `GameActivity`
--

CREATE TABLE `GameActivity` (
  `ID` int NOT NULL,
  `Gracz` int NOT NULL,
  `Gra` int NOT NULL,
  `Test` int DEFAULT NULL,
  `CzasRozpoczecia` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Zrzut danych tabeli `GameActivity`
--

INSERT INTO `GameActivity` (`ID`, `Gracz`, `Gra`, `Test`, `CzasRozpoczecia`) VALUES
(1, 1, 1, 1, '2021-12-29 06:37:09');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `GameHistory`
--

CREATE TABLE `GameHistory` (
  `ID` int NOT NULL,
  `Gra` int NOT NULL,
  `Gracz` int NOT NULL,
  `CzasZakonczenia` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Games`
--

CREATE TABLE `Games` (
  `ID` int NOT NULL,
  `Kod` varchar(15) NOT NULL,
  `Nazwa` text NOT NULL,
  `Aktywna` tinyint(1) NOT NULL,
  `obraz` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Zrzut danych tabeli `Games`
--

INSERT INTO `Games` (`ID`, `Kod`, `Nazwa`, `Aktywna`, `obraz`) VALUES
(1, 'NP', 'Naukowe podchody', 1, 'https://media.giphy.com/media/VVgRNcBKp64NO/giphy.gif'),
(2, 'NB', 'Nyskie browary', 1, 'https://media.giphy.com/media/10SvWCbt1ytWCc/giphy.gif'),
(3, 'SS', 'Studencka sesja', 1, 'https://media.giphy.com/media/LwIyvaNcnzsD6/giphy.gif'),
(4, 'ZN', 'Zwiedzanie Nysy', 1, 'https://media.giphy.com/media/1jZvozc11kldsutng6/giphy.gif'),
(5, 'SP', 'Szybkie Palce', 1, 'https://cdn.pixabay.com/photo/2021/09/07/07/11/game-console-6603120_960_720.jpg'),
(6, 'MW', 'Magiczna Wycieczka', 1, 'https://media.giphy.com/media/MEREDFNrSTlErSldr6/giphy.gif');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `players`
--

CREATE TABLE `players` (
  `ID` int NOT NULL,
  `Imie` varchar(32) NOT NULL,
  `Nazwisko` varchar(32) NOT NULL,
  `KodDstepu` varchar(15) NOT NULL,
  `IDGame` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Zrzut danych tabeli `players`
--

INSERT INTO `players` (`ID`, `Imie`, `Nazwisko`, `KodDstepu`, `IDGame`) VALUES
(1, 'Mateusz', 'Wróblewski', 'Z45SC', 2),
(2, 'Skowron', 'Sekielski', '433848', 2),
(3, 'Szymon', 'Kowalski', 'dJ4ovw', 2),
(6, 'Skowron', 'Sekielski', '4QmRH3', 5),
(7, 'Widok', 'Sekielski', 'cWGybJ', 2);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Position`
--

CREATE TABLE `Position` (
  `ID` int NOT NULL,
  `Gra` int DEFAULT NULL,
  `Gracz` int NOT NULL,
  `PozX` text,
  `PozY` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Zrzut danych tabeli `Position`
--

INSERT INTO `Position` (`ID`, `Gra`, `Gracz`, `PozX`, `PozY`) VALUES
(1, 1, 2, '3,33', '3,33');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tasks`
--

CREATE TABLE `tasks` (
  `ID` int NOT NULL,
  `Tresc` text NOT NULL,
  `Obraz` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `OdpA` varchar(255) NOT NULL,
  `OdpB` varchar(255) NOT NULL,
  `OdpC` varchar(255) NOT NULL,
  `OdpD` varchar(255) NOT NULL,
  `PoprawnaOdp` varchar(255) NOT NULL,
  `IDTestu` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Zrzut danych tabeli `tasks`
--

INSERT INTO `tasks` (`ID`, `Tresc`, `Obraz`, `OdpA`, `OdpB`, `OdpC`, `OdpD`, `PoprawnaOdp`, `IDTestu`) VALUES
(3, 'tews', '', 'yrs', 'wewe', 'wewe', 'rftg gffg', 'C', 1),
(4, 'sdsd', 'https://media.giphy.com/media/MEREDFNrSTlErSldr6/giphy.gif', 'wewe', 'wew', 'wewe', 'sdsd', 'D', 3);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Tips`
--

CREATE TABLE `Tips` (
  `ID` int NOT NULL,
  `Test` int NOT NULL,
  `Wkazowka` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `exams`
--
ALTER TABLE `exams`
  ADD PRIMARY KEY (`ID`);

--
-- Indeksy dla tabeli `GameActivity`
--
ALTER TABLE `GameActivity`
  ADD PRIMARY KEY (`ID`);

--
-- Indeksy dla tabeli `GameHistory`
--
ALTER TABLE `GameHistory`
  ADD PRIMARY KEY (`ID`);

--
-- Indeksy dla tabeli `Games`
--
ALTER TABLE `Games`
  ADD PRIMARY KEY (`ID`);

--
-- Indeksy dla tabeli `players`
--
ALTER TABLE `players`
  ADD PRIMARY KEY (`ID`);

--
-- Indeksy dla tabeli `Position`
--
ALTER TABLE `Position`
  ADD PRIMARY KEY (`ID`);

--
-- Indeksy dla tabeli `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`ID`);

--
-- Indeksy dla tabeli `Tips`
--
ALTER TABLE `Tips`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `exams`
--
ALTER TABLE `exams`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT dla tabeli `GameActivity`
--
ALTER TABLE `GameActivity`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT dla tabeli `GameHistory`
--
ALTER TABLE `GameHistory`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `Games`
--
ALTER TABLE `Games`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT dla tabeli `players`
--
ALTER TABLE `players`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT dla tabeli `Position`
--
ALTER TABLE `Position`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT dla tabeli `tasks`
--
ALTER TABLE `tasks`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT dla tabeli `Tips`
--
ALTER TABLE `Tips`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
