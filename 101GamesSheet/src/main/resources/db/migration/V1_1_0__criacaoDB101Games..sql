USE `101Games`;

CREATE TABLE IF NOT EXISTS `tb_ficha` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome_UNIQUE` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `tb_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `ficha` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome_UNIQUE` (`nome`),
  UNIQUE KEY `tipo_UNIQUE` (`tipo`),
  KEY `fk_item_ficha_idx` (`ficha`),
  CONSTRAINT `fk_item_ficha` FOREIGN KEY (`ficha`) REFERENCES `tb_ficha` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `tb_item_personagem` (
  `id_personagem` int NOT NULL,
  `id_item` int NOT NULL,
  `quantidade` int NOT NULL,
  PRIMARY KEY (`id_personagem`,`id_item`),
  KEY `fk_item_idx` (`id_item`),
  CONSTRAINT `fk_item` FOREIGN KEY (`id_item`) REFERENCES `tb_item` (`id`),
  CONSTRAINT `fk_personagem` FOREIGN KEY (`id_personagem`) REFERENCES `tb_personagem` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `tb_magia` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `descricao` varchar(3000) NOT NULL,
  `ficha` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome_UNIQUE` (`nome`),
  KEY `fk_magia_ficha_idx` (`ficha`),
  CONSTRAINT `fk_magia_ficha` FOREIGN KEY (`ficha`) REFERENCES `tb_ficha` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `tb_magia_personagem` (
  `id_personagem` int NOT NULL,
  `id_magia` int NOT NULL,
  PRIMARY KEY (`id_personagem`,`id_magia`),
  KEY `fk_item_idx` (`id_magia`),
  KEY `fk_personagem` (`id_personagem`),
  CONSTRAINT `fk_magia_personagem` FOREIGN KEY (`id_magia`) REFERENCES `tb_magia` (`id`),
  CONSTRAINT `fk_personagem_magia` FOREIGN KEY (`id_personagem`) REFERENCES `tb_personagem` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `tb_melhorias_refugio` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `descricao` varchar(4500) DEFAULT NULL,
  `ficha` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_UNIQUE` (`nome`),
  KEY `fk_melhoria_ficha_idx` (`ficha`),
  CONSTRAINT `fk_melhoria_ficha` FOREIGN KEY (`ficha`) REFERENCES `tb_ficha` (`id`),
  CONSTRAINT `fk_refugio_melhoria` FOREIGN KEY (`id`) REFERENCES `tb_refugio` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `tb_pericia` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `ficha` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_pericia_ficha_idx` (`ficha`),
  CONSTRAINT `fk_pericia_ficha` FOREIGN KEY (`ficha`) REFERENCES `tb_ficha` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `tb_pericia_personagem` (
  `id_pericia` int NOT NULL,
  `id_personagem` int NOT NULL,
  PRIMARY KEY (`id_pericia`,`id_personagem`),
  KEY `fk_pericia_personagem_idx` (`id_pericia`),
  KEY `fk_personagem_pericia` (`id_personagem`),
  CONSTRAINT `fk_pericia_personagem` FOREIGN KEY (`id_pericia`) REFERENCES `tb_pericia` (`ID`),
  CONSTRAINT `fk_personagem_pericia` FOREIGN KEY (`id_personagem`) REFERENCES `tb_personagem` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `tb_personagem` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `raca` varchar(20) DEFAULT NULL,
  `jogador` varchar(45) NOT NULL,
  `corpo` int NOT NULL,
  `saude` int NOT NULL,
  `espirito` int NOT NULL,
  `postura` int NOT NULL,
  `mente` int NOT NULL,
  `vontade` int NOT NULL,
  `historia` varchar(4500) NOT NULL,
  `ficha` int DEFAULT NULL,
  `insanidade` int NOT NULL,
  `fome` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `NOME_UNIQUE` (`nome`) /*!80000 INVISIBLE */,
  KEY `fk_personagem_ficha_idx` (`ficha`),
  CONSTRAINT `fk_personagem_ficha` FOREIGN KEY (`ficha`) REFERENCES `tb_ficha` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='tabela central de personagem';

CREATE TABLE IF NOT EXISTS `tb_refugio` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `local` varchar(45) NOT NULL,
  `ficha` varchar(45) NOT NULL,
  `defesa` int NOT NULL,
  `tecnologia` int NOT NULL,
  `espaco` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Idx_nome` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `tb_refugio_personagem` (
  `id_personagem` int NOT NULL,
  `id_refugio` int NOT NULL,
  PRIMARY KEY (`id_personagem`,`id_refugio`),
  KEY `fk_refugio_personagem_idx` (`id_refugio`),
  CONSTRAINT `fk_personagem_refugio` FOREIGN KEY (`id_personagem`) REFERENCES `tb_personagem` (`id`),
  CONSTRAINT `fk_refugio_personagem` FOREIGN KEY (`id_refugio`) REFERENCES `tb_refugio` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `tb_vantagem` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `descricao` varchar(4500) DEFAULT NULL,
  `ficha` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_nome` (`nome`),
  CONSTRAINT `fk_vantagem_ficha` FOREIGN KEY (`id`) REFERENCES `tb_ficha` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `tb_vantagem_personagem` (
  `id_vantagem` int NOT NULL,
  `id_personagem` int NOT NULL,
  PRIMARY KEY (`id_vantagem`,`id_personagem`),
  KEY `fk_personagem_vantagem_idx` (`id_personagem`),
  CONSTRAINT `fk_personagem_vantagem` FOREIGN KEY (`id_personagem`) REFERENCES `tb_personagem` (`id`),
  CONSTRAINT `fk_vantagem_personagem` FOREIGN KEY (`id_vantagem`) REFERENCES `tb_vantagem` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
