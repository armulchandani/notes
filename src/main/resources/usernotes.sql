-- DDL STATEMENT FOR CREATE USER TABLE
CREATE TABLE `user` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`email` VARCHAR(80) NOT NULL,
	`password` VARCHAR(25) NOT NULL,
	`created` TIME NOT NULL,
	`updated` TIME NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `UNIQUE_EMAIL` (`email`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=2
;



-- DDL STATEMENT FOR CREATE NOTE TABLE
CREATE TABLE `note` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`title` CHAR(50) NOT NULL,
	`note` VARCHAR(1000) NOT NULL,
	`created` DATETIME NOT NULL,
	`updated` DATETIME NULL DEFAULT NULL,
	`user` INT(11) NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `FK_USER` (`user`),
	CONSTRAINT `FK_USER` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE CASCADE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=2
;


