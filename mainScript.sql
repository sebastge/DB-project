-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema TreningsDagbok
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema TreningsDagbok
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `TreningsDagbok` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema project
-- -----------------------------------------------------
USE `TreningsDagbok` ;

-- -----------------------------------------------------
-- Table `TreningsDagbok`.`trainingSession`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TreningsDagbok`.`trainingSession` (
  `sessionID` INT NOT NULL,
  `sessionDate` INT(8) NOT NULL,
  `sessionTime` INT(4) NOT NULL,
  `sessionDuration` INT(3) NOT NULL,
  `personalForm` INT(1) NULL,
  `sessionIntent` VARCHAR(45) NULL,
  `templateName` VARCHAR(45) NULL,
  PRIMARY KEY (`sessionID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TreningsDagbok`.`exercise`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TreningsDagbok`.`exercise` (
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `replacement` VARCHAR(45) NULL,
  `trainingSession_sessionID` INT NOT NULL,
  PRIMARY KEY (`name`, `trainingSession_sessionID`),
  INDEX `fk_exercise_trainingSession_idx` (`trainingSession_sessionID` ASC),
  CONSTRAINT `fk_exercise_trainingSession`
    FOREIGN KEY (`trainingSession_sessionID`)
    REFERENCES `TreningsDagbok`.`trainingSession` (`sessionID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TreningsDagbok`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TreningsDagbok`.`category` (
  `categoryID` INT NOT NULL,
  `categoryName` VARCHAR(45) NOT NULL,
  `categoryDescription` VARCHAR(45) NULL,
  PRIMARY KEY (`categoryID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TreningsDagbok`.`category_has_exercise`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TreningsDagbok`.`category_has_exercise` (
  `category_categoryID` INT NOT NULL,
  `exercise_name` VARCHAR(45) NOT NULL,
  `exercise_trainingSession_sessionID` INT NOT NULL,
  PRIMARY KEY (`category_categoryID`, `exercise_name`, `exercise_trainingSession_sessionID`),
  INDEX `fk_category_has_exercise_exercise1_idx` (`exercise_name` ASC, `exercise_trainingSession_sessionID` ASC),
  INDEX `fk_category_has_exercise_category1_idx` (`category_categoryID` ASC),
  CONSTRAINT `fk_category_has_exercise_category1`
    FOREIGN KEY (`category_categoryID`)
    REFERENCES `TreningsDagbok`.`category` (`categoryID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_category_has_exercise_exercise1`
    FOREIGN KEY (`exercise_name` , `exercise_trainingSession_sessionID`)
    REFERENCES `TreningsDagbok`.`exercise` (`name` , `trainingSession_sessionID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TreningsDagbok`.`outdoorSession`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TreningsDagbok`.`outdoorSession` (
  `temp` INT NOT NULL,
  `weatherType` VARCHAR(45) NULL,
  `trainingSession_sessionID` INT NOT NULL,
  PRIMARY KEY (`trainingSession_sessionID`),
  CONSTRAINT `fk_outdoorSession_trainingSession1`
    FOREIGN KEY (`trainingSession_sessionID`)
    REFERENCES `TreningsDagbok`.`trainingSession` (`sessionID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TreningsDagbok`.`indoorSession`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TreningsDagbok`.`indoorSession` (
  `ventilation` INT NULL,
  `bystanders` INT NULL,
  `trainingSession_sessionID` INT NOT NULL,
  PRIMARY KEY (`trainingSession_sessionID`),
  CONSTRAINT `fk_indoorSession_trainingSession1`
    FOREIGN KEY (`trainingSession_sessionID`)
    REFERENCES `TreningsDagbok`.`trainingSession` (`sessionID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TreningsDagbok`.`cardioStrength`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TreningsDagbok`.`cardioStrength` (
  `goalLoad` INT NOT NULL,
  `goalReps` INT NOT NULL,
  `goalSets` INT NOT NULL,
  `exercise_name` VARCHAR(45) NOT NULL,
  `exercise_trainingSession_sessionID` INT NOT NULL,
  PRIMARY KEY (`exercise_name`, `exercise_trainingSession_sessionID`),
  CONSTRAINT `fk_cardioStrength_exercise1`
    FOREIGN KEY (`exercise_name` , `exercise_trainingSession_sessionID`)
    REFERENCES `TreningsDagbok`.`exercise` (`name` , `trainingSession_sessionID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TreningsDagbok`.`cardioStrengthResult`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TreningsDagbok`.`cardioStrengthResult` (
  `historyID` INT NOT NULL,
  `resultLoad` VARCHAR(45) NOT NULL,
  `resultReps` VARCHAR(45) NOT NULL,
  `resultSets` VARCHAR(45) NOT NULL,
  `cardioStrength_exercise_name` VARCHAR(45) NOT NULL,
  `cardioStrength_exercise_trainingSession_sessionID` INT NOT NULL,
  PRIMARY KEY (`historyID`, `cardioStrength_exercise_name`, `cardioStrength_exercise_trainingSession_sessionID`),
  INDEX `fk_cardioStrengthResult_cardioStrength1_idx` (`cardioStrength_exercise_name` ASC, `cardioStrength_exercise_trainingSession_sessionID` ASC),
  CONSTRAINT `fk_cardioStrengthResult_cardioStrength1`
    FOREIGN KEY (`cardioStrength_exercise_name` , `cardioStrength_exercise_trainingSession_sessionID`)
    REFERENCES `TreningsDagbok`.`cardioStrength` (`exercise_name` , `exercise_trainingSession_sessionID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TreningsDagbok`.`endurance`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TreningsDagbok`.`endurance` (
  `goalLength` INT NOT NULL,
  `goalDuration` INT NOT NULL,
  `exercise_name` VARCHAR(45) NOT NULL,
  `exercise_trainingSession_sessionID` INT NOT NULL,
  PRIMARY KEY (`exercise_name`, `exercise_trainingSession_sessionID`),
  CONSTRAINT `fk_endurance_exercise1`
    FOREIGN KEY (`exercise_name` , `exercise_trainingSession_sessionID`)
    REFERENCES `TreningsDagbok`.`exercise` (`name` , `trainingSession_sessionID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TreningsDagbok`.`enduranceResult`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TreningsDagbok`.`enduranceResult` (
  `historyID` INT NOT NULL,
  `resultLength` INT NOT NULL,
  `resultDuration` INT NOT NULL,
  `endurance_exercise_name` VARCHAR(45) NOT NULL,
  `endurance_exercise_trainingSession_sessionID` INT NOT NULL,
  PRIMARY KEY (`historyID`, `endurance_exercise_name`, `endurance_exercise_trainingSession_sessionID`),
  INDEX `fk_enduranceResult_endurance1_idx` (`endurance_exercise_name` ASC, `endurance_exercise_trainingSession_sessionID` ASC),
  CONSTRAINT `fk_enduranceResult_endurance1`
    FOREIGN KEY (`endurance_exercise_name` , `endurance_exercise_trainingSession_sessionID`)
    REFERENCES `TreningsDagbok`.`endurance` (`exercise_name` , `exercise_trainingSession_sessionID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TreningsDagbok`.`category_has_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TreningsDagbok`.`category_has_category` (
  `category_categoryID` INT NOT NULL,
  `category_categoryID1` INT NOT NULL,
  PRIMARY KEY (`category_categoryID`, `category_categoryID1`),
  INDEX `fk_category_has_category_category2_idx` (`category_categoryID1` ASC),
  INDEX `fk_category_has_category_category1_idx` (`category_categoryID` ASC),
  CONSTRAINT `fk_category_has_category_category1`
    FOREIGN KEY (`category_categoryID`)
    REFERENCES `TreningsDagbok`.`category` (`categoryID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_category_has_category_category2`
    FOREIGN KEY (`category_categoryID1`)
    REFERENCES `TreningsDagbok`.`category` (`categoryID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;
