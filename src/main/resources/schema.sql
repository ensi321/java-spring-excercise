DROP TABLE IF EXISTS `price`;

CREATE TABLE `price` (
                         `id` int IDENTITY(1,1) PRIMARY KEY,
                         `date` date DEFAULT NULL,
                         `ticker` varchar(45) DEFAULT NULL,
                         `open` float DEFAULT NULL,
                         `high` float DEFAULT NULL,
                         `low` float DEFAULT NULL,
                         `close` float DEFAULT NULL,
                         `adj_close` float DEFAULT NULL,
                         `volume` int DEFAULT NULL
);
