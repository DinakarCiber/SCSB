SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `recapcas`;

CREATE TABLE IF NOT EXISTS recapcas.`users` (
  `username` char(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` char(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` char(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `firstname` char(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lastname` char(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `active` int(1) DEFAULT '0',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `recapcas`.`users`(`username`,`password`,`email`,`firstname`,`lastname`,`active`) VALUES('superadmin','17c4520f6cfd1ab53d8745e84681eb49','superadmin@testemail.com','Super','Admin',1);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
