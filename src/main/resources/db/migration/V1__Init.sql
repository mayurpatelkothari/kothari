CREATE TABLE `regisation` (
  `id` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(255) NULL,
  `role` VARCHAR(45) NULL,
  `created` DATETIME NULL,
  PRIMARY KEY (`id`));