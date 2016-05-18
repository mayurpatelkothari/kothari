CREATE TABLE `regisation` (
  `id` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(255) NULL,
  `role` VARCHAR(45) NULL,
  `openbal` VARCHAR(45) NULL,
  `baltype` VARCHAR(45) NULL,
  `created` DATETIME NULL,
  PRIMARY KEY (`id`));

  
  
  CREATE TABLE `bank` (
  `id` VARCHAR(255) NOT NULL,
  `name` VARCHAR(45) NULL,
  `openbal` DECIMAL(10,0) NULL,
  `created` DATETIME NULL,
  `regid` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  INDEX `dsd_idx` (`regid` ASC),
  CONSTRAINT `dsd`
    FOREIGN KEY (`regid`)
    REFERENCES `regisation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

  
  
  CREATE TABLE `transaction` (
  `id` VARCHAR(255) NOT NULL,
  `ttype` ENUM('CREDIT','DEBIT') NULL,
  `paymenttype` ENUM('CASH','BANK') NULL,
  `bankid` VARCHAR(255) NULL,
  `amount` DECIMAL(10,0) NULL,
  `des` MEDIUMTEXT NULL,
  `regid` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  INDEX `wq_idx` (`regid` ASC),
  INDEX `dswe_idx` (`bankid` ASC),
  CONSTRAINT `wq`
    FOREIGN KEY (`regid`)
    REFERENCES `regisation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `dswe`
    FOREIGN KEY (`bankid`)
    REFERENCES `bank` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
   CREATE TABLE `party` (
  `id` VARCHAR(255) NOT NULL,
  `name` VARCHAR(45) NULL,
  `mobileno` VARCHAR(45) NULL,
  `regid` VARCHAR(255) NULL,
  `created` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `wew_idx` (`regid` ASC),
  CONSTRAINT `wew`
    FOREIGN KEY (`regid`)
    REFERENCES `regisation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



