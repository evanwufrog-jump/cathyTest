CREATE TABLE `currency_name`
(
    `id`   INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NULL,
    `code` VARCHAR(45) NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO `currency_name` values(1, '美金', 'USD');
INSERT INTO `currency_name` values(2, '英鎊', 'GBP');
INSERT INTO `currency_name` values(3, '歐元', 'EUR');