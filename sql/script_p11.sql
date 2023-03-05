SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

DROP DATABASE IF EXISTS practica11;
CREATE DATABASE practica11;
use practica11;

CREATE TABLE `hipotecas` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `interes` double NOT NULL,
  `capital` double NOT NULL,
  `meses` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `hipotecas` (`id`, `user_id`, `interes`, `capital`, `meses`) VALUES
(1, 1, 3, 200000, 60),
(2, 2, 3, 50000, 60),
(3, 5, 3, 200000, 180),
(4, 1, 5, 180000, 180),
(5, 2, 3, 20000, 40),
(6, 1, 3, 200000, 60),
(7, 2, 3, 200000, 60);

CREATE TABLE `users` (
  `id` int NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `users` (`id`, `username`, `password`) VALUES
(1, 'jordiop', '0'),
(2, 'biel', '0'),
(3, 'aaa', '3'),
(4, 'aaaaaaaaa', 'a'),
(5, 'hlodkie', '0');


ALTER TABLE `hipotecas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`),
  ADD KEY `user_id` (`user_id`);

ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD UNIQUE KEY `username` (`username`);


ALTER TABLE `hipotecas`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

ALTER TABLE `users`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;


ALTER TABLE `hipotecas`
  ADD CONSTRAINT `hipotecas_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
