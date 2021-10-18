ALTER TABLE `101games`.`tb_vantagem` 
ADD INDEX `fk_vantagem_cenario_idx` (`cenario` ASC) VISIBLE;
;
ALTER TABLE `101games`.`tb_vantagem` 
ADD CONSTRAINT `fk_vantagem_cenario`
  FOREIGN KEY (`cenario`)
  REFERENCES `101games`.`tb_cenario` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;