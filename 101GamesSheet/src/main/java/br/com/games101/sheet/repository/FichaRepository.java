package br.com.games101.sheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.games101.sheet.entity.Ficha;

public interface FichaRepository extends JpaRepository<Ficha,Long> {

}
