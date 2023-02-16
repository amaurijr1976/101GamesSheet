package br.com.games101.sheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.games101.sheet.entity.Cenario;

public interface CenarioRepository extends JpaRepository<Cenario,Long> {

}
