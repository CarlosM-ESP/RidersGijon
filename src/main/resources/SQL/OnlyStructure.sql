DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `apellido1` varchar(255) DEFAULT NULL,
  `apellido2` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `matricula` varchar(255) DEFAULT NULL,
  `nick` varchar(255) DEFAULT NULL,
  `nif` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `user_type` varchar(255) DEFAULT NULL,
  `vehiculo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `rol`;
CREATE TABLE `rol` (
  `idrol` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idrol`),
  UNIQUE KEY `UK_43kr6s7bts1wqfv43f7jd87kp` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `userrol`;
CREATE TABLE `userrol` (
  `iduserrol` int NOT NULL AUTO_INCREMENT,
  `idrol` int DEFAULT NULL,
  `id_user` int DEFAULT NULL,
  PRIMARY KEY (`iduserrol`),
  KEY `FK3enep7x3t0lx6liuy2p0fxlks` (`idrol`),
  KEY `FKdihujlq00mbv6q1m5p9km2ca3` (`id_user`),
  CONSTRAINT `FK3enep7x3t0lx6liuy2p0fxlks` FOREIGN KEY (`idrol`) REFERENCES `rol` (`idrol`),
  CONSTRAINT `FKdihujlq00mbv6q1m5p9km2ca3` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `pedido`;
CREATE TABLE `pedido` (
  `id_pedido` int NOT NULL AUTO_INCREMENT,
  `comentarios` varchar(255) DEFAULT NULL,
  `dir_destinatario` varchar(255) DEFAULT NULL,
  `dir_remitente` varchar(255) DEFAULT NULL,
  `fecha_pedido` date DEFAULT NULL,
  `fecha_entregado` date DEFAULT NULL,
  `nombre_destinatario` varchar(255) DEFAULT NULL,
  `nombre_remitente` varchar(255) DEFAULT NULL,
  `status` int NOT NULL,
  `telefono_destinatario` varchar(255) DEFAULT NULL,
  `telefono_remitente` varchar(255) DEFAULT NULL,
  `cliente_id_user` int DEFAULT NULL,
  `rider_id_user` int DEFAULT NULL,
  PRIMARY KEY (`id_pedido`),
  KEY `FKlu0gu2dlpnf1le9uwjae7er7a` (`cliente_id_user`),
  KEY `FK2s2burxhqvijf2mfw73eoqjw2` (`rider_id_user`),
  CONSTRAINT `FK2s2burxhqvijf2mfw73eoqjw2` FOREIGN KEY (`rider_id_user`) REFERENCES `user` (`id_user`),
  CONSTRAINT `FKlu0gu2dlpnf1le9uwjae7er7a` FOREIGN KEY (`cliente_id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;