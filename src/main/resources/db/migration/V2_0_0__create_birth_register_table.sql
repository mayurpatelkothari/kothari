CREATE TABLE `birthregister` (
  `id` VARCHAR(45) NOT NULL,
  `firstname` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  `sex` VARCHAR(45) NULL,
  `dateofbirth` VARCHAR(45) NULL,
  `nameoffather` VARCHAR(45) NULL,
  `nameofmother` VARCHAR(45) NULL,
  `birthpalce` VARCHAR(45) NULL,
  `parmanentaddress` VARCHAR(45) NULL,
  `registrationno` VARCHAR(45) NULL,
  `remarks` VARCHAR(45) NULL,
  `dateofregistration` VARCHAR(45) NULL,
  `zone` VARCHAR(45) NULL,
  `ward` VARCHAR(45) NULL,
  `month` VARCHAR(45) NULL,
  `sun` VARCHAR(45) NULL,
  `birthmonth` VARCHAR(45) NULL,
  `regisation` VARCHAR(45) NULL,
  `created` DATETIME NULL,
  `updated` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_REGI_idx` (`regisation` ASC),
  CONSTRAINT `FK_REGI`
    FOREIGN KEY (`regisation`)
    REFERENCES `regisation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
