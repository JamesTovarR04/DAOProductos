-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema EmpresaDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema EmpresaDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `EmpresaDB` DEFAULT CHARACTER SET utf8 ;
USE `EmpresaDB` ;

-- -----------------------------------------------------
-- Table `EmpresaDB`.`Productos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EmpresaDB`.`Productos` (
  `idProducto` INT NOT NULL AUTO_INCREMENT,
  `codigo` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` TINYTEXT NULL,
  `fabricante` VARCHAR(45) NULL,
  `cantidadStock` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`idProducto`),
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC) VISIBLE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
