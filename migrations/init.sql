-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema db_hotelsbook
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema db_hotelsbook
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_hotelsbook` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `db_hotelsbook` ;

-- -----------------------------------------------------
-- Table `db_hotelsbook`.`city`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_hotelsbook`.`city` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `country` VARCHAR(100) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_hotelsbook`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_hotelsbook`.`address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(100) CHARACTER SET 'utf8mb3' NOT NULL,
  `number` INT NOT NULL,
  `city_id` INT NOT NULL,
  PRIMARY KEY (`id`, `city_id`),
  CONSTRAINT `fk_address_city1`
    FOREIGN KEY (`city_id`)
    REFERENCES `db_hotelsbook`.`city` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `fk_address_city1_idx` ON `db_hotelsbook`.`address` (`city_id` ASC) VISIBLE;

ALTER TABLE address ADD UNIQUE (id);


-- -----------------------------------------------------
-- Table `db_hotelsbook`.`hotel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_hotelsbook`.`hotel` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) CHARACTER SET 'utf8mb3' NOT NULL,
  `price` VARCHAR(50) CHARACTER SET 'utf8mb3' NOT NULL,
  `picture` VARCHAR(200) CHARACTER SET 'utf8mb3' NOT NULL,
  `description` VARCHAR(500) CHARACTER SET 'utf8mb3' NOT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`id`, `address_id`),
  CONSTRAINT `fk_hotel_address`
    FOREIGN KEY (`address_id`)
    REFERENCES `db_hotelsbook`.`address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `fk_hotel_address_idx` ON `db_hotelsbook`.`hotel` (`address_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `db_hotelsbook`.`room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_hotelsbook`.`room` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `number` INT NOT NULL,
  `description` VARCHAR(200) NULL,
  `number_passenger` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_hotelsbook`.`reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_hotelsbook`.`reservation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date_in` DATE NOT NULL,
  `date_out` DATE NOT NULL,
  `room_id` INT NOT NULL,
  PRIMARY KEY (`id`, `room_id`),
  CONSTRAINT `fk_reservation_room1`
    FOREIGN KEY (`room_id`)
    REFERENCES `db_hotelsbook`.`room` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `fk_reservation_room1_idx` ON `db_hotelsbook`.`reservation` (`room_id` ASC) VISIBLE;

ALTER TABLE reservation
ADD CONSTRAINT unique_reservation_id UNIQUE (id);


-- -----------------------------------------------------
-- Table `db_hotelsbook`.`review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_hotelsbook`.`review` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `calification` DECIMAL(3,0) NOT NULL,
  `description` VARCHAR(45) CHARACTER SET 'utf8mb3' NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_hotelsbook`.`service`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_hotelsbook`.`service` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(200) CHARACTER SET 'utf8mb3' NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_hotelsbook`.`service_has_hotel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_hotelsbook`.`service_has_hotel` (
  `service_id` INT NOT NULL,
  `hotel_id` INT NOT NULL,
  `hotel_address_id` INT NOT NULL,
  PRIMARY KEY (`service_id`, `hotel_id`, `hotel_address_id`),
  CONSTRAINT `fk_service_has_hotel_service1`
    FOREIGN KEY (`service_id`)
    REFERENCES `db_hotelsbook`.`service` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_service_has_hotel_hotel1`
    FOREIGN KEY (`hotel_id` , `hotel_address_id`)
    REFERENCES `db_hotelsbook`.`hotel` (`id` , `address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `fk_service_has_hotel_hotel1_idx` ON `db_hotelsbook`.`service_has_hotel` (`hotel_id` ASC, `hotel_address_id` ASC) VISIBLE;

CREATE INDEX `fk_service_has_hotel_service1_idx` ON `db_hotelsbook`.`service_has_hotel` (`service_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `db_hotelsbook`.`hotel_has_review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_hotelsbook`.`hotel_has_review` (
  `hotel_id` INT NOT NULL,
  `hotel_address_id` INT NOT NULL,
  `review_id` INT NOT NULL,
  PRIMARY KEY (`hotel_id`, `hotel_address_id`, `review_id`),
  CONSTRAINT `fk_hotel_has_review_hotel1`
    FOREIGN KEY (`hotel_id` , `hotel_address_id`)
    REFERENCES `db_hotelsbook`.`hotel` (`id` , `address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_hotel_has_review_review1`
    FOREIGN KEY (`review_id`)
    REFERENCES `db_hotelsbook`.`review` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `fk_hotel_has_review_review1_idx` ON `db_hotelsbook`.`hotel_has_review` (`review_id` ASC) VISIBLE;

CREATE INDEX `fk_hotel_has_review_hotel1_idx` ON `db_hotelsbook`.`hotel_has_review` (`hotel_id` ASC, `hotel_address_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `db_hotelsbook`.`reservation_has_hotel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_hotelsbook`.`reservation_has_hotel` (
  `reservation_id` INT NOT NULL,
  `hotel_id` INT NOT NULL,
  `hotel_address_id` INT NOT NULL,
  PRIMARY KEY (`reservation_id`, `hotel_id`, `hotel_address_id`),
  CONSTRAINT `fk_reservation_has_hotel_reservation1`
    FOREIGN KEY (`reservation_id`)
    REFERENCES `db_hotelsbook`.`reservation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservation_has_hotel_hotel1`
    FOREIGN KEY (`hotel_id` , `hotel_address_id`)
    REFERENCES `db_hotelsbook`.`hotel` (`id` , `address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `fk_reservation_has_hotel_hotel1_idx` ON `db_hotelsbook`.`reservation_has_hotel` (`hotel_id` ASC, `hotel_address_id` ASC) VISIBLE;

CREATE INDEX `fk_reservation_has_hotel_reservation1_idx` ON `db_hotelsbook`.`reservation_has_hotel` (`reservation_id` ASC) VISIBLE;

/*!50003 DROP PROCEDURE IF EXISTS `GetAvailableHotelsByCity` */;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAvailableHotelsByCity`(
    IN start_date DATE,
    IN end_date DATE,
    IN city_id INT
)
BEGIN
    SELECT h.id, h.name, h.price, h.description, h.picture, a.street, a.number, c.name as city_name
    FROM hotel h
    JOIN address a ON h.address_id = a.id
    JOIN city c ON a.city_id = c.id
    LEFT JOIN reservation_has_hotel r_h ON h.id = r_h.hotel_id
    LEFT JOIN reservation r ON r_h.reservation_id = r.id
    WHERE c.id = city_id
      AND (r.date_in IS NULL OR r.date_out IS NULL OR r.date_in > end_date OR r.date_out < start_date);
END ;;
DELIMITER ;

/*!50003 DROP PROCEDURE IF EXISTS `GetAverageCalificationByHotel` */;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAverageCalificationByHotel`(IN hotel_ids VARCHAR(255))
BEGIN
    -- Generar la consulta dinámica
    SET @sql = CONCAT('
        SELECT  h.id AS hotel_id,
			h.name AS hotel_name,
			AVG(r.calification) AS average_calification
        FROM hotel h
        JOIN hotel_has_review hhr ON h.id = hhr.hotel_id
        JOIN review r ON hhr.review_id = r.id
        WHERE h.id IN (', hotel_ids, ')
        GROUP BY h.id, h.name
    ');

    -- Preparar y ejecutar la consulta
    PREPARE stmt FROM @sql;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END ;;
DELIMITER ;

/*!50003 DROP PROCEDURE IF EXISTS `GetHotelsByCity` */;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetHotelsByCity`(
	IN start_date DATE,
    IN end_date DATE,
    IN city_id INT
)
BEGIN
    SELECT h.id, h.name, h.price, h.description, h.picture, a.street, a.number, c.name as city_name,
    CASE 
        WHEN r.date_in IS NULL OR r.date_out IS NULL OR r.date_in > end_date OR r.date_out < start_date THEN start_date
        ELSE r.date_out
    END AS available
    FROM hotel h
    JOIN address a ON h.address_id = a.id
    JOIN city c ON a.city_id = c.id
    LEFT JOIN reservation_has_hotel r_h ON h.id = r_h.hotel_id
	LEFT JOIN reservation r ON r_h.reservation_id = r.id
    WHERE c.id = city_id;
END ;;
DELIMITER ;

/*!50003 DROP PROCEDURE IF EXISTS `GetServicesByHotels` */;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetServicesByHotels`(IN hotelIds TEXT)
BEGIN
    -- Crear una consulta dinámica
    SET @query = CONCAT(
        'SELECT h.id AS hotel_id, h.name AS hotel_name, s.id AS service_id, s.nombre AS service_name
         FROM hotel h
         JOIN service_has_hotel shh ON h.id = shh.hotel_id
         JOIN service s ON shh.service_id = s.id
         WHERE h.id IN (', hotelIds, ')
         ORDER BY h.id, s.nombre;'
    );
    
    -- Ejecutar la consulta dinámica
    PREPARE stmt FROM @query;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END ;;
DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
