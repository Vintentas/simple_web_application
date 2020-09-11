-- -----------------------------------------------------
-- Schema db_example2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS db_example2 DEFAULT CHARACTER SET latin1 ;
USE db_example2 ;


-- -----------------------------------------------------
-- Table `db_example2`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS user (
  id INT(11) NOT NULL AUTO_INCREMENT,
  last_name VARCHAR(255) NULL DEFAULT NULL,
  name VARCHAR(255) NULL DEFAULT NULL,
  email VARCHAR(255) NULL DEFAULT NULL,
  password VARCHAR(255) NULL DEFAULT NULL,
  role_id INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX id_UNIQUE (id ASC))



