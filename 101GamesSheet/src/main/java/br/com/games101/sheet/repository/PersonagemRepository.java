package br.com.games101.sheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.games101.sheet.entity.Personagem;

public interface PersonagemRepository extends JpaRepository<Personagem,Long>{

}
