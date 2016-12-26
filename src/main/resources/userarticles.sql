-- DDL STATEMENT FOR CREATE USER TABLE
CREATE TABLE `user` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`email` VARCHAR(80) NOT NULL,
	`password` VARCHAR(25) NOT NULL,
	`created` DATETIME NOT NULL,
	`updated` DATETIME NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `UNIQUE_EMAIL` (`email`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=3
;

-- Sample data

INSERT INTO `user` (`id`, `email`, `password`, `created`, `updated`) VALUES (1, 'armulchandani@gmail.com', '1234', '2016-12-09 15:22:02', NULL);
INSERT INTO `user` (`id`, `email`, `password`, `created`, `updated`) VALUES (2, 'm_anubhav@hotmail.com', '1234', '2016-12-09 15:22:03', NULL);


-- DDL STATEMENT FOR CREATE ARTICLE TABLE
CREATE TABLE `article` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`title` CHAR(50) NOT NULL,
	`content` VARCHAR(1000) NOT NULL,
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


